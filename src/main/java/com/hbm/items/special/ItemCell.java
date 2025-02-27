package com.hbm.items.special;
import com.hbm.util.ItemStackUtil;

import java.util.List;
import java.util.Random;

import com.hbm.blocks.bomb.BlockCrashedBomb;
import com.hbm.config.BombConfig;
import com.hbm.config.WeaponConfig;
import com.hbm.entity.effect.EntityCloudFleija;
import com.hbm.entity.logic.EntityNukeExplosionMK3;
import com.hbm.forgefluid.HbmFluidHandlerCell;
import com.hbm.forgefluid.HbmFluidHandlerItemStack;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.forgefluid.SpecialContainerFillLists.EnumCell;
import com.hbm.items.ModItems;
import com.hbm.util.ContaminationUtil;
import com.hbm.util.ContaminationUtil.ContaminationType;
import com.hbm.util.ContaminationUtil.HazardType;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCell extends Item {

	public ItemCell(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setMaxDamage(1000);
		this.setContainerItem(this);
		
		ModItems.ALL_ITEMS.add(this);
	}

	@Override
	public boolean onEntityItemUpdate(final EntityItem entityItem) {
		if(entityItem.onGround || entityItem.isBurning()) {
			if(hasFluid(entityItem.getItem(), ModForgeFluids.aschrab) && WeaponConfig.dropCell) {
				if(!entityItem.world.isRemote) {
					entityItem.setDead();
					entityItem.world.playSound(null, entityItem.posX, entityItem.posY, entityItem.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.AMBIENT, 100.0f, entityItem.world.rand.nextFloat() * 0.1F + 0.9F);
					final EntityNukeExplosionMK3 entity = new EntityNukeExplosionMK3(entityItem.world);
					entity.posX = entityItem.posX;
					entity.posY = entityItem.posY;
					entity.posZ = entityItem.posZ;
					if(!EntityNukeExplosionMK3.isJammed(entityItem.world, entity)){
						entity.destructionRange = (int) (BombConfig.aSchrabRadius * (FluidUtil.getFluidContained(entityItem.getItem()).amount / 1000.0F));

						entity.speed = 25;
						entity.coefficient = 1.0F;
						entity.waste = false;

						entityItem.world.spawnEntity(entity);

						final EntityCloudFleija cloud = new EntityCloudFleija(entityItem.world, (int) (BombConfig.aSchrabRadius * (FluidUtil.getFluidContained(entityItem.getItem()).amount / 1000.0F)));
						cloud.posX = entityItem.posX;
						cloud.posY = entityItem.posY;
						cloud.posZ = entityItem.posZ;
						entityItem.world.spawnEntity(cloud);
					}
				}
				return true;
			}
			if(hasFluid(entityItem.getItem(), ModForgeFluids.amat) && WeaponConfig.dropCell) {
				if(!entityItem.world.isRemote) {
					entityItem.setDead();
					entityItem.world.createExplosion(entityItem, entityItem.posX, entityItem.posY, entityItem.posZ, 10.0F * (FluidUtil.getFluidContained(entityItem.getItem()).amount / 1000.0F), true);
				}
				return true;
			}

		}
		return false;
	}

	@Override
	public int getItemStackLimit(final ItemStack stack) {
		return isFullOrEmpty(stack) ? 64 : 1;
	}
	
	@Override
	public void onUpdate(final ItemStack stack, final World worldIn, final Entity entityIn, final int itemSlot, final boolean isSelected) {
		if(!(entityIn instanceof EntityLivingBase))
			return;
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		if(hasFluid(stack, ModForgeFluids.tritium)){
			ContaminationUtil.contaminate((EntityLivingBase)entityIn, HazardType.RADIATION, ContaminationType.CREATIVE, 0.5F / 20F);
		} else if(hasFluid(stack, ModForgeFluids.sas3)){
			ContaminationUtil.contaminate((EntityLivingBase)entityIn, HazardType.RADIATION, ContaminationType.CREATIVE, 1.0f);
			((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100, 0));
		} else if(hasFluid(stack, ModForgeFluids.uf6)){
			ContaminationUtil.contaminate((EntityLivingBase)entityIn, HazardType.RADIATION, ContaminationType.CREATIVE, 2F / 20F);
		} else if(hasFluid(stack, ModForgeFluids.puf6)){
			ContaminationUtil.contaminate((EntityLivingBase)entityIn, HazardType.RADIATION, ContaminationType.CREATIVE, 10F / 20F);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(final ItemStack stack) {
		final FluidStack f = FluidUtil.getFluidContained(stack);
		if(f != null){
			//Why is there a npe here? I have no idea, and I can't replicate it. Stupid try/catch it is.
			try {
				return I18n.format(EnumCell.getEnumFromFluid(f.getFluid()).getTranslateKey());
			} catch(final NullPointerException e){ }
		}
		return I18n.format("item.cell_empty.name");
	}

	@Override
	public void getSubItems(final CreativeTabs tab, final NonNullList<ItemStack> items) {
		if(tab == this.getCreativeTab() || tab == CreativeTabs.SEARCH) {
			for(final Fluid f : EnumCell.getFluids()) {
				final ItemStack stack = ItemStackUtil.itemStackFrom(this, 1, 0);
				stack.setTagCompound(new NBTTagCompound());
				if(f != null)
					stack.getTagCompound().setTag(HbmFluidHandlerCell.FLUID_NBT_KEY, new FluidStack(f, 1000).writeToNBT(new NBTTagCompound()));
				items.add(stack);
			}
		}
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World world, final List<String> tooltip, final ITooltipFlag flagIn) {
		if(ItemCell.hasFluid(stack, ModForgeFluids.amat)){
			tooltip.add("§eExposure to matter will lead to violent annihilation!§r");
			tooltip.add("§c[Dangerous Drop]§r");
		} else if(ItemCell.hasFluid(stack, ModForgeFluids.aschrab)){
			tooltip.add("§eExposure to matter will create a fólkvangr field!§r");
			tooltip.add("§c[Dangerous Drop]§r");
		} else if(ItemCell.hasFluid(stack, ModForgeFluids.tritium)){
			tooltip.add("§a[Radioactive]§r");
			tooltip.add("§e0.5 RAD/s§r");
		} else if(ItemCell.hasFluid(stack, ModForgeFluids.uf6)){
			tooltip.add("§a[Radioactive]§r");
			tooltip.add("§e2.0 RAD/s§r");
		} else if(ItemCell.hasFluid(stack, ModForgeFluids.puf6)){
			tooltip.add("§a[Radioactive]§r");
			tooltip.add("§e10.0 RAD/s§r");
		} else if(ItemCell.hasFluid(stack, ModForgeFluids.sas3)){
			tooltip.add("§a[Radioactive]§r");
			tooltip.add("§e20.0 RAD/s§r");
			tooltip.add("§3[Blinding]§r");
		}
	}

	@Override
	public ICapabilityProvider initCapabilities(final ItemStack stack, final NBTTagCompound nbt) {
		if(stack.getTagCompound() == null)
			stack.setTagCompound(new NBTTagCompound());
		return new HbmFluidHandlerCell(stack, 1000);
	}

	public static boolean isFullCell(final ItemStack stack, final Fluid fluid) {
		if(stack != null) {
            return stack.getItem() instanceof ItemCell && FluidUtil.getFluidContained(stack) != null && FluidUtil.getFluidContained(stack).getFluid() == fluid && FluidUtil.getFluidContained(stack).amount == 1000;
		}
		return false;
	}

	public static boolean isEmptyCell(final ItemStack stack) {
		if(stack != null) {
			if(stack.getItem() == ModItems.cell && stack.getTagCompound() != null) {
				final FluidStack s = FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag(HbmFluidHandlerCell.FLUID_NBT_KEY));
                return s == null || s.amount <= 0;
			} else return stack.getItem() == ModItems.cell && stack.getTagCompound() == null;
		}
		return false;
	}

	public static boolean hasFluid(final ItemStack stack, final Fluid f) {
		if(stack != null) {
            return stack.getItem() == ModItems.cell && FluidUtil.getFluidContained(stack) != null && FluidUtil.getFluidContained(stack).getFluid() == f;
		}
		return false;
	}

	public static ItemStack getFullCell(final Fluid fluid, final int amount) {
		if(EnumCell.contains(fluid)) {
			final ItemStack stack = ItemStackUtil.itemStackFrom(ModItems.cell, amount, 0);
			stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setTag(HbmFluidHandlerCell.FLUID_NBT_KEY, new FluidStack(fluid, 1000).writeToNBT(new NBTTagCompound()));
			return stack;
		}
		return ItemStack.EMPTY;
	}
	
	public static ItemStack getFullCell(final Fluid fluid) {
		return getFullCell(fluid, 1);
	}
	
	public static boolean isFullOrEmpty(final ItemStack stack){
		if(stack.hasTagCompound() && stack.getItem() == ModItems.cell){
			final FluidStack f = FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag(HbmFluidHandlerItemStack.FLUID_NBT_KEY));
			if(f == null)
				return true;
			return f.amount == 1000 || f.amount == 0;
			
		} else return stack.getItem() == ModItems.cell;
    }

	public static boolean hasEmptyCell(final EntityPlayer player){
		final InventoryPlayer inv = player.inventory;
		for(int i = 0; i < inv.getSizeInventory(); i ++){
			if(isEmptyCell(inv.getStackInSlot(i))){
				return true;
			}
		}
		return false;
	}
	
	public static void consumeEmptyCell(final EntityPlayer player){
		final InventoryPlayer inv = player.inventory;
		for(int i = 0; i < inv.getSizeInventory(); i ++){
			if(isEmptyCell(inv.getStackInSlot(i))){
				inv.getStackInSlot(i).shrink(1);
				return;
			}
		}
	}

}
