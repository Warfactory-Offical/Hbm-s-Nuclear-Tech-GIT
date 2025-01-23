package com.hbm.items.tool;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.hbm.handler.WeaponAbility;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.util.I18nUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemSwordAbility extends ItemSword implements IItemAbility {

	private EnumRarity rarity = EnumRarity.COMMON;
	//was there a reason for this to be private?
	protected float damage;
	protected double attackSpeed;
	protected double movement;
	private final List<WeaponAbility> hitAbility = new ArrayList<>();

	public ItemSwordAbility(final float damage, final double attackSpeed, final double movement, final ToolMaterial material, final String s) {
		super(material);
		this.damage = damage;
		this.movement = movement;
		this.attackSpeed = attackSpeed;
		this.setTranslationKey(s);
		this.setRegistryName(s);

		ModItems.ALL_ITEMS.add(this);
	}

	public ItemSwordAbility(final float damage, final double movement, final ToolMaterial material, final String s) {
		this(damage, -2.4, movement, material, s);
	}

	public ItemSwordAbility addHitAbility(final WeaponAbility weaponAbility) {
		this.hitAbility.add(weaponAbility);
		return this;
	}

	//<insert obvious Rarity joke here>
	public ItemSwordAbility setRarity(final EnumRarity rarity) {
		this.rarity = rarity;
		return this;
	}

	@SuppressWarnings("deprecation")
	@Override
	public EnumRarity getRarity(final ItemStack stack) {
		return this.rarity != EnumRarity.COMMON ? this.rarity : super.getRarity(stack);
	}

	@Override
	public boolean hitEntity(final ItemStack stack, final EntityLivingBase target, final EntityLivingBase attacker) {
		if(!attacker.world.isRemote && !this.hitAbility.isEmpty() && attacker instanceof EntityPlayer && canOperate(stack)) {

			//hacky hacky hack
			if(this == ModItems.mese_gavel)
				attacker.world.playSound(null, target.posX, target.posY, target.posZ, HBMSoundHandler.whack, SoundCategory.HOSTILE, 3.0F, 1.F);

			for(final WeaponAbility ability : this.hitAbility) {
				ability.onHit(attacker.world, (EntityPlayer) attacker, target, this);
			}
		}
		stack.damageItem(1, attacker);
		return super.hitEntity(stack, target, attacker);
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(final EntityEquipmentSlot slot) {
		final Multimap<String, AttributeModifier> map = HashMultimap.create();
		if(slot == EntityEquipmentSlot.MAINHAND) {
			map.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(UUID.fromString("91AEAA56-376B-4498-935B-2F7F68070635"), "Tool modifier", movement, 1));
			map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.damage, 0));
			map.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", this.attackSpeed, 0));
		}
		return map;
	}

	@Override
	public void breakExtraBlock(final World world, final int x, final int y, final int z, final EntityPlayer playerEntity, final int refX, final int refY, final int refZ, final EnumHand hand) {
		final BlockPos pos = new BlockPos(x, y, z);
		if(world.isAirBlock(pos))
			return;

		if(!(playerEntity instanceof EntityPlayerMP player))
			return;

        final ItemStack stack = player.getHeldItem(hand);

		final IBlockState block = world.getBlockState(pos);

		if(!canHarvestBlock(block, stack))
			return;

		final IBlockState refBlock = world.getBlockState(new BlockPos(refX, refY, refZ));
		final float refStrength = ForgeHooks.blockStrength(refBlock, player, world, new BlockPos(refX, refY, refZ));
		final float strength = ForgeHooks.blockStrength(block, player, world, pos);

		if(!ForgeHooks.canHarvestBlock(block.getBlock(), player, world, pos) || refStrength / strength > 10f)
			return;

		final int event = ForgeHooks.onBlockBreakEvent(world, player.interactionManager.getGameType(), player, pos);
		if(event < 0)
			return;

		if(player.capabilities.isCreativeMode) {
			block.getBlock().onBlockHarvested(world, pos, block, player);
			if(block.getBlock().removedByPlayer(block, world, pos, player, false))
				block.getBlock().onPlayerDestroy(world, pos, block);

			if(!world.isRemote) {
				player.connection.sendPacket(new SPacketBlockChange(world, pos));
			}
			return;
		}

		player.getHeldItem(hand).onBlockDestroyed(world, block, pos, player);

		if(!world.isRemote) {

			block.getBlock().onBlockHarvested(world, pos, block, player);

			if(block.getBlock().removedByPlayer(block, world, pos, player, true)) {
				block.getBlock().onPlayerDestroy(world, pos, block);
				block.getBlock().harvestBlock(world, player, pos, block, world.getTileEntity(pos), stack);
				block.getBlock().dropXpOnBlockBreak(world, pos, event);
			}

			player.connection.sendPacket(new SPacketBlockChange(world, pos));

		} else {
			world.playEvent(2001, pos, Block.getStateId(block));
			if(block.getBlock().removedByPlayer(block, world, pos, player, true)) {
				block.getBlock().onPlayerDestroy(world, pos, block);
			}
			final ItemStack itemstack = player.getHeldItem(hand);
			if(itemstack != null) {
				itemstack.onBlockDestroyed(world, block, new BlockPos(x, y, z), player);

				if(itemstack.isEmpty()) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
				}
			}

			Minecraft.getMinecraft().getConnection().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, new BlockPos(x, y, z), Minecraft.getMinecraft().objectMouseOver.sideHit));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		if(!this.hitAbility.isEmpty()) {

			list.add(I18nUtil.resolveKey("tool.ability.weaponlist"));

			for(final WeaponAbility ability : this.hitAbility) {
				list.add("  " + TextFormatting.RED + ability.getFullName());
			}
		}
	}

	protected boolean canOperate(final ItemStack stack) {
		return true;
	}
}
