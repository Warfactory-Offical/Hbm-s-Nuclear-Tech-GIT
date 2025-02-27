package com.hbm.items.tool;

import api.hbm.item.IDepthRockTool;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.generic.BlockBedrockOre;
import com.hbm.blocks.generic.BlockBedrockOreTE;
import com.hbm.handler.ToolAbility;
import com.hbm.handler.ToolAbility.SilkAbility;
import com.hbm.handler.WeaponAbility;
import com.hbm.items.ModItems;
import com.hbm.util.I18nUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

public class ItemToolAbility extends ItemTool implements IItemAbility, IDepthRockTool {

	private final EnumToolType toolType;
	private EnumRarity rarity = EnumRarity.COMMON;
	//was there a reason for this to be private?
    protected float damage;
    protected double movement;
    private final List<ToolAbility> breakAbility = new ArrayList<ToolAbility>() {
		private static final long serialVersionUID = 153867601249309418L;
	{ add(null); }};
    private final List<WeaponAbility> hitAbility = new ArrayList<WeaponAbility>();

    private boolean rockBreaker = false;
    
	
	public static enum EnumToolType {
		
		PICKAXE(
				Sets.newHashSet(Material.IRON, Material.ANVIL, Material.ROCK),
				Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE)
		),
		AXE(
				Sets.newHashSet(Material.WOOD, Material.PLANTS, Material.VINE),
				Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE)
		),
		SHOVEL(
				Sets.newHashSet(Material.CLAY, Material.SAND, Material.GROUND, Material.SNOW, Material.CRAFTED_SNOW),
				Sets.newHashSet(Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND, Blocks.GRASS, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.CONCRETE_POWDER)
		),
		MINER(
				Sets.newHashSet(Material.GRASS, Material.IRON, Material.ANVIL, Material.ROCK, Material.CLAY, Material.SAND, Material.GROUND, Material.SNOW, Material.CRAFTED_SNOW)
		);
		
		private EnumToolType(final Set<Material> materials) {
			this.materials = materials;
		}
		
		private EnumToolType(final Set<Material> materials, final Set<Block> blocks) {
			this.materials = materials;
			this.blocks = blocks;
		}

		public Set<Material> materials = new HashSet<Material>();
		public Set<Block> blocks = new HashSet<Block>();
	}
	
	public ItemToolAbility(final float damage, final float attackSpeedIn, final double movement, final ToolMaterial material, final EnumToolType type, final String s) {
		super(0, attackSpeedIn, material, type.blocks);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.damage = damage;
		this.movement = movement;
		this.toolType = type;
		if(type == EnumToolType.MINER){
			this.setHarvestLevel("shovel", material.getHarvestLevel());
			this.setHarvestLevel("pickaxe", material.getHarvestLevel());
			this.setHarvestLevel("axe", material.getHarvestLevel());
		} else {
			this.setHarvestLevel(type.toString().toLowerCase(), material.getHarvestLevel());
		}

		ModItems.ALL_ITEMS.add(this);
	}
	
	public ItemToolAbility addBreakAbility(final ToolAbility breakAbility) {
		this.breakAbility.add(breakAbility);
		return this;
	}
	
	public ItemToolAbility addHitAbility(final WeaponAbility weaponAbility) {
		this.hitAbility.add(weaponAbility);
		return this;
	}
	
	//<insert obvious Rarity joke here>
	//Drillgon200: What?
	public ItemToolAbility setRarity(final EnumRarity rarity) {
		this.rarity = rarity;
		return this;
	}
	
	//Drillgon200: Dang it bob, override annotations matter!
	@SuppressWarnings("deprecation")
	@Override
    public EnumRarity getRarity(final ItemStack stack) {
        return this.rarity != EnumRarity.COMMON ? this.rarity : super.getRarity(stack);
    }
	
	@Override
	public boolean hitEntity(final ItemStack stack, final EntityLivingBase target, final EntityLivingBase attacker) {
		if(!attacker.world.isRemote && !this.hitAbility.isEmpty() && attacker instanceof EntityPlayer && canOperate(stack)) {
    		
    		for(final WeaponAbility ability : this.hitAbility) {
				ability.onHit(attacker.world, (EntityPlayer) attacker, target, this);
    		}
    	}
		stack.damageItem(2, attacker);
        return true;
	}
	
	@Override
	public boolean onBlockStartBreak(final ItemStack stack, final BlockPos pos, final EntityPlayer player) {
		final World world = player.world;
    	final IBlockState block = world.getBlockState(pos);
    	
    	if(!world.isRemote && canHarvestBlock(block, stack) && this.getCurrentAbility(stack) != null && canOperate(stack))
    		this.getCurrentAbility(stack).onDig(world, pos.getX(), pos.getY(), pos.getZ(), player, block, this, player.getHeldItemMainhand() == stack ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND);
    	
    	return false;
	}
	
	@Override
	public float getDestroySpeed(final ItemStack stack, final IBlockState state) {
		if(!canOperate(stack))
    		return 1;
    	
    	if(toolType == null)
            return super.getDestroySpeed(stack, state);
    	
    	if(toolType.blocks.contains(state.getBlock()) || toolType.materials.contains(state.getMaterial()))
    		return this.efficiency;
    	
        return super.getDestroySpeed(stack, state);
	}
	
	@Override
	public boolean canHarvestBlock(final IBlockState state, final ItemStack stack) {
		if(!canOperate(stack)) return false;

		if(isForbiddenBlock(state.getBlock())) return false;
    	
		if(this.getCurrentAbility(stack) instanceof SilkAbility)
    		return true;
		
    	return getDestroySpeed(stack, state) > 1;
	}

	public static boolean isForbiddenBlock(final Block b){
		return (b == Blocks.BARRIER || b == Blocks.BEDROCK || b == Blocks.COMMAND_BLOCK || b == Blocks.CHAIN_COMMAND_BLOCK || b == Blocks.REPEATING_COMMAND_BLOCK || b == ModBlocks.ore_bedrock_oil || b instanceof BlockBedrockOre || b instanceof BlockBedrockOreTE );
	}
	
	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(final EntityEquipmentSlot slot) {
		final Multimap<String, AttributeModifier> map = HashMultimap.create();
		if(slot == EntityEquipmentSlot.MAINHAND){
			map.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(UUID.fromString("91AEAA56-376B-4498-935B-2F7F68070635"), "Tool modifier", movement, 1));
			map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", this.damage, 0));
		}
        return map;
	}
	
	//that's slimelad's code
    //creative commons 3 and all that jazz
	@Override
    public void breakExtraBlock(final World world, final int x, final int y, final int z, final EntityPlayer playerEntity, final int refX, final int refY, final int refZ, final EnumHand hand) {
    	final BlockPos pos = new BlockPos(x, y, z);
        if (world.isAirBlock(pos))
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

        if (!ForgeHooks.canHarvestBlock(block.getBlock(), player, world, pos) || refStrength/strength > 10f)
            return;

        final int event = ForgeHooks.onBlockBreakEvent(world, player.interactionManager.getGameType(), player, pos);
        if(event < 0)
            return;

        if (player.capabilities.isCreativeMode) {
            block.getBlock().onBlockHarvested(world, pos, block, player);
            if (block.getBlock().removedByPlayer(block, world, pos, player, false))
                block.getBlock().onPlayerDestroy(world, pos, block);

            if (!world.isRemote) {
                player.connection.sendPacket(new SPacketBlockChange(world, pos));
            }
            return;
        }

        player.getHeldItem(hand).onBlockDestroyed(world, block, pos, player);

        if (!world.isRemote) {
        	
            block.getBlock().onBlockHarvested(world, pos, block, player);

            if(block.getBlock().removedByPlayer(block, world, pos, player, true))
            {
                block.getBlock().onPlayerDestroy(world, pos, block);
                block.getBlock().harvestBlock(world, player, pos, block, world.getTileEntity(pos), stack);
                block.getBlock().dropXpOnBlockBreak(world, pos, event);
            }

            player.connection.sendPacket(new SPacketBlockChange(world, pos));
            
        } else {
            world.playEvent(2001, pos, Block.getStateId(block));
            if(block.getBlock().removedByPlayer(block, world, pos, player, true))
            {
                block.getBlock().onPlayerDestroy(world, pos, block);
            }
            final ItemStack itemstack = player.getHeldItem(hand);
            if (itemstack != null)
            {
                itemstack.onBlockDestroyed(world, block, new BlockPos(x, y, z), player);

                if (itemstack.isEmpty())
                {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                }
            }

            Minecraft.getMinecraft().getConnection().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, new BlockPos(x, y, z), Minecraft.getMinecraft().objectMouseOver.sideHit));
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(final ItemStack stack) {
    	return getCurrentAbility(stack) != null || super.hasEffect(stack);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
    	if(this.breakAbility.size() > 1) {
    		list.add(I18nUtil.resolveKey("tool.ability.list"));
    		
    		for(final ToolAbility ability : this.breakAbility) {
    			
    			if(ability != null) {
    				
    				if(getCurrentAbility(stack) == ability)
    					list.add(" §e§l>" + ability.getFullName());
    				else
    					list.add("  §6" + ability.getFullName());
    			}
    		}

    		list.add(I18nUtil.resolveKey("tool.ability.rightclick"));
    		list.add(I18nUtil.resolveKey("tool.ability.shiftclick"));
    	}
    	
    	if(!this.hitAbility.isEmpty()) {
    		
    		list.add(I18nUtil.resolveKey("tool.ability.weaponlist"));
    		
    		for(final WeaponAbility ability : this.hitAbility) {
				list.add("  " + TextFormatting.RED + ability.getFullName());
    		}
    	}

    	if(this.rockBreaker){
    		list.add("§5["+I18nUtil.resolveKey("trait.unmineable")+"]§d " + I18nUtil.resolveKey("tool.ability.canmine"));
    	}
    }
    
    @Override
    public EnumActionResult onItemUse(final EntityPlayer player, final World worldIn, final BlockPos pos, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
    	final ItemStack stack = player.getHeldItem(hand);
    	if(this.breakAbility.size() < 2 || !canOperate(stack))
    		return EnumActionResult.PASS;
    	if(!worldIn.isRemote){
    		switchMode(player, stack);
    	}
    	return EnumActionResult.SUCCESS;
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(final World world, final EntityPlayer player, final EnumHand hand) {
    	final ItemStack stack = player.getHeldItem(hand);
    	
    	if(world.isRemote || this.breakAbility.size() < 2 || !canOperate(stack))
    		return super.onItemRightClick(world, player, hand);
    	
    	switchMode(player, stack);
    	
    	return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
    }
    
    private void switchMode(final EntityPlayer player, final ItemStack stack){
    	int i = getAbility(stack);
    	i++;
    	
    	if(player.isSneaking())
    		i = 0;
    	
    	setAbility(stack, i % this.breakAbility.size());
    	
    	while(getCurrentAbility(stack) != null && !getCurrentAbility(stack).isAllowed()) {

    		player.sendMessage(
				new TextComponentString("[")
				.appendSibling(new TextComponentTranslation("chat.ability"))
				.appendSibling(new TextComponentString(" "))
				.appendSibling(new TextComponentTranslation(getCurrentAbility(stack).getName()))
				.appendSibling(new TextComponentString(getCurrentAbility(stack).getExtension() + " ")
				.appendSibling(new TextComponentTranslation("chat.blacklist"))
				.appendSibling(new TextComponentString("]")))
				.setStyle(new Style().setColor(TextFormatting.RED)));

        	i++;
        	setAbility(stack, i % this.breakAbility.size());
    	}
    	
    	if(getCurrentAbility(stack) != null) {
    		player.sendMessage(
				new TextComponentString("[")
				.appendSibling(new TextComponentTranslation("chat.abilenabled"))
				.appendSibling(new TextComponentString(" "))
				.appendSibling(new TextComponentTranslation(getCurrentAbility(stack).getName()))
				.appendSibling(new TextComponentString(getCurrentAbility(stack).getExtension() + "]"))
				.setStyle(new Style().setColor(TextFormatting.YELLOW)));
    	} else {
    		player.sendMessage(new TextComponentString("[")
    			.appendSibling(new TextComponentTranslation("chat.abildisabled"))
    			.appendSibling(new TextComponentString("]"))
    			.setStyle(new Style().setColor(TextFormatting.GOLD)));
    	}

    	//Drillgon200: I hope "random.orb" referred to the experience orb sound
        player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.25F, getCurrentAbility(stack) == null ? 0.75F : 1.25F);
    }
    
    private ToolAbility getCurrentAbility(final ItemStack stack) {
    	
    	final int ability = getAbility(stack) % this.breakAbility.size();
    	
    	return this.breakAbility.get(ability);
    }
    
    private int getAbility(final ItemStack stack) {
    	
    	if(stack.hasTagCompound())
    		return stack.getTagCompound().getInteger("ability");
    	
    	return 0;
    }
    
    private void setAbility(final ItemStack stack, final int ability) {

    	if(!stack.hasTagCompound())
    		stack.setTagCompound(new NBTTagCompound());
    	
    	stack.getTagCompound().setInteger("ability", ability);
    }
    
    protected boolean canOperate(final ItemStack stack) {
    	return true;
    }

    public ItemToolAbility setDepthRockBreaker() {
		this.rockBreaker = true;
		return this;
	}
	
	
	@Override
	public boolean canBreakRock(final World world, final EntityPlayer player, final ItemStack tool, final IBlockState block, final BlockPos pos){
		return canOperate(tool) && this.rockBreaker;
	}
}
