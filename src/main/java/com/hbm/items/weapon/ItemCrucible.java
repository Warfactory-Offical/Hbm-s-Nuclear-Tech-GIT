package com.hbm.items.weapon;
import com.hbm.util.ItemStackUtil;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.hbm.config.GeneralConfig;
import com.hbm.interfaces.IPostRender;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.main.ModEventHandlerClient;
import com.hbm.packet.AuxButtonPacket;
import com.hbm.packet.AuxParticlePacketNT;
import com.hbm.packet.PacketDispatcher;
import com.hbm.particle.ParticleCrucibleLightning;
import com.hbm.render.anim.HbmAnimations;
import com.hbm.render.anim.HbmAnimations.Animation;
import com.hbm.render.anim.HbmAnimations.BlenderAnimation;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCrucible extends ItemSwordCutter implements IPostRender {

	public static boolean doSpecialClick = false;
	
	public ItemCrucible(final float damage, final double movement, final ToolMaterial material, final String s) {
		super(damage, movement, material, s);
	}

	@Override
	public void onEquip(final EntityPlayer player, final EnumHand hand) {
		super.onEquip(player, hand);
		if(getCharges(player.getHeldItem(hand)) == 0)
			return;
		if(!(player instanceof EntityPlayerMP))
			return;
		//World world = player.world;
		final NBTTagCompound tag = new NBTTagCompound();
		tag.setString("type", "sound");
		tag.setString("mode", "crucible_loop");
		tag.setInteger("playerId", player.getEntityId());
		PacketDispatcher.wrapper.sendToAllTracking(new AuxParticlePacketNT(tag, 0, 0, 0), player);
		PacketDispatcher.sendTo(new AuxParticlePacketNT(tag, 0, 0, 0), (EntityPlayerMP) player);
		//world.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.cDeploy, SoundCategory.PLAYERS, 1.0F, 1.0F);
	}
	
	@Override
	public void getSubItems(final CreativeTabs tab, final NonNullList<ItemStack> items){
		if(tab == this.getCreativeTab() || tab == CreativeTabs.SEARCH){
			items.add(charge(ItemStackUtil.itemStackFrom(this)));
		}
	}
	
	@Override
	public boolean onEntitySwing(final EntityLivingBase entityLiving, final ItemStack stack) {
		if(!(entityLiving instanceof EntityPlayerMP)){
			super.onEntitySwing(entityLiving, stack);
			return true;
		}
		if(!doSpecialClick){
			final EnumHand hand = stack == entityLiving.getHeldItemMainhand() ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
			
			final NBTTagCompound nbt = new NBTTagCompound();
			nbt.setString("type", "anim");
			nbt.setInteger("hand", hand.ordinal());
			nbt.setString("mode", "cSwing");
			nbt.setString("name", this.getRegistryName().getPath());
			PacketDispatcher.wrapper.sendTo(new AuxParticlePacketNT(nbt, 0, 0, 0), (EntityPlayerMP)entityLiving);
		}
		if(getCharges(stack) > 0)
			entityLiving.world.playSound(null, entityLiving.posX, entityLiving.posY, entityLiving.posZ, HBMSoundHandler.crucibleSwing, SoundCategory.PLAYERS, 1, 1);

		return true;
	}
	
	@Override
	public byte getTexId() {
		return 1;
	}
	
	@Override
	public void onUpdate(final ItemStack stack, final World worldIn, final Entity entityIn, final int itemSlot, final boolean isSelected) {
		if(isSelected && worldIn.isRemote && getCharges(stack) > 0 && entityIn instanceof EntityPlayer){
			updateClient(worldIn, (EntityPlayer) entityIn, itemSlot);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void updateClient(final World w, final EntityPlayer player, final int slot){
		if(player != Minecraft.getMinecraft().player)
			return;
		final Animation anim = HbmAnimations.hotbar[slot];
		if(clicked || (anim != null && anim.animation != null && anim.animation.getBus("SWING") != null)){
			PacketDispatcher.wrapper.sendToServer(new AuxButtonPacket(0, 0, 0, 1, 1000));
		} else {
			PacketDispatcher.wrapper.sendToServer(new AuxButtonPacket(0, 0, 0, 0, 1000));
		}
		boolean flag = false;
		if(anim instanceof BlenderAnimation){
			if(System.currentTimeMillis() - ((BlenderAnimation) anim).wrapper.startTime > ((BlenderAnimation) anim).wrapper.anim.length*0.7F){
				flag = true;
			}
		}
		if(flag && w.rand.nextInt(20) == 0){
			ModEventHandlerClient.firstPersonAuxParticles.add(new ParticleCrucibleLightning(w, 0, (w.rand.nextFloat()-0.5F)*0.2F, 0.7F-w.rand.nextFloat()*0.25F).lifetime(10));
		}
	}
	
	@Override
	public boolean hitEntity(final ItemStack stack, final EntityLivingBase victim, final EntityLivingBase attacker) {
		if(!doSpecialClick){
			discharge(stack);
			if(!attacker.world.isRemote && getCharges(stack) > 0 && !victim.isEntityAlive()) {
				final int count = Math.min((int)Math.ceil(victim.getMaxHealth() / 3D), 250);

				final NBTTagCompound data = new NBTTagCompound();
				data.setString("type", "vanillaburst");
				data.setInteger("count", count * 4);
				data.setDouble("motion", 0.1D);
				data.setString("mode", "blockdust");
				data.setInteger("block", Block.getIdFromBlock(Blocks.REDSTONE_BLOCK));
				PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, victim.posX, victim.posY + victim.height * 0.5, victim.posZ), new TargetPoint(victim.dimension, victim.posX, victim.posY + victim.height * 0.5, victim.posZ, 50));
			}
		}
		return super.hitEntity(stack, victim, attacker);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		String charge = TextFormatting.RED + "Charge [";
		
		final int charges = getCharges(stack);
		for(int i = 0; i < GeneralConfig.crucibleMaxCharges; i++)
			if(charges > i)
				charge += "||||||";
			else
				charge += "   ";
		
		charge += "]";
		
		list.add(charge);
	}
	
	public static int getCharges(final ItemStack stack){
		if(stack.hasTagCompound()){
			return stack.getTagCompound().getInteger("charges");
		}
		return 0;
	}
	
	public static ItemStack charge(final ItemStack stack){
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setInteger("charges", GeneralConfig.crucibleMaxCharges);
		return stack;
	}
	
	public static void discharge(final ItemStack stack){
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setInteger("charges", Math.max(0, getCharges(stack)-1));
	}
	
	@Override
	public boolean showDurabilityBar(final ItemStack stack){
		return true;
	}
	
	@Override
	public double getDurabilityForDisplay(final ItemStack stack){
		return 1-(double)getCharges(stack)/GeneralConfig.crucibleMaxCharges;
	}
	
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(final EntityEquipmentSlot slot, final ItemStack stack){
		final Multimap<String, AttributeModifier> map = HashMultimap.create();
		final boolean charged = getCharges(stack) > 0;
		if(slot == EntityEquipmentSlot.MAINHAND) {
			map.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(UUID.fromString("91AEAA56-376B-4498-935B-2F7F68070635"), "Tool modifier", charged ? movement : movement*0.8F, 1));
			map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", charged ? (double) this.damage : 5, 0));
		}
		return map;
	}

}
