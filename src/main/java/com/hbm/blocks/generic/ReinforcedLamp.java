package com.hbm.blocks.generic;
import com.hbm.util.ItemStackUtil;

import java.util.Random;
import java.util.List;
import com.hbm.blocks.ModBlocks;
import com.hbm.handler.RadiationSystemNT;
import com.hbm.interfaces.IRadResistantBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ReinforcedLamp extends Block implements IRadResistantBlock {

	private final boolean isOn;
	
	public ReinforcedLamp(final Material materialIn, final boolean b, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		isOn = b;
		if(b){
			this.setLightLevel(1.0F);
		}
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public void onBlockAdded(final World worldIn, final BlockPos pos, final IBlockState state) {
		if (!worldIn.isRemote)
        {
            if (this.isOn && !(worldIn.isBlockPowered(pos)))
            {
            	worldIn.scheduleUpdate(pos, this, 4);
            }
            else if (!this.isOn && worldIn.isBlockPowered(pos))
            {
            	worldIn.setBlockState(pos, ModBlocks.reinforced_lamp_on.getDefaultState(), 2);
            }
        }
        RadiationSystemNT.markChunkForRebuild(worldIn, pos);
	}
	
	@Override
	public void neighborChanged(final IBlockState state, final World worldIn, final BlockPos pos, final Block blockIn, final BlockPos fromPos) {
		if (!worldIn.isRemote)
        {
            if (this.isOn && !(worldIn.isBlockPowered(pos)))
            {
            	worldIn.scheduleUpdate(pos, this, 4);
            }
            else if (!this.isOn && worldIn.isBlockPowered(pos))
            {
            	worldIn.setBlockState(pos, ModBlocks.reinforced_lamp_on.getDefaultState(), 2);
            }
        }
	}
	
	@Override
	public void updateTick(final World worldIn, final BlockPos pos, final IBlockState state, final Random rand) {
		if (!worldIn.isRemote && this.isOn && !(worldIn.isBlockPowered(pos)))
        {
			worldIn.setBlockState(pos, ModBlocks.reinforced_lamp_off.getDefaultState(), 2);
        }
	}
	
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Item.getItemFromBlock(ModBlocks.reinforced_lamp_off);
	}
	
	@Override
	public ItemStack getPickBlock(final IBlockState state, final RayTraceResult target, final World world, final BlockPos pos, final EntityPlayer player) {
		return ItemStackUtil.itemStackFrom(ModBlocks.reinforced_lamp_off);
	}

	@Override
	public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced) {
		final float hardness = this.getExplosionResistance(null);
		tooltip.add("§2[Radiation Shielding]§r");
		if(hardness > 50){
			tooltip.add("§6Blast Resistance: "+hardness+"§r");
		}
	}
	
	@Override
	public void breakBlock(final World worldIn, final BlockPos pos, final IBlockState state) {
		RadiationSystemNT.markChunkForRebuild(worldIn, pos);
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public boolean isRadResistant(final World worldIn, final BlockPos blockPos){
		return true;
	}
}
