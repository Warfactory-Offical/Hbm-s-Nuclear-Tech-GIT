package com.hbm.blocks.machine;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.TileEntityForceField;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MachineForceField extends BlockContainer {

	public MachineForceField(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityForceField();
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote)
		{
			return true;
		} else if(!player.isSneaking())
		{
			player.openGui(MainRegistry.instance, ModBlocks.guiID_forcefield, world, pos.getX(), pos.getY(), pos.getZ());
			return true;
		} else {
			return true;
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(final IBlockState state, final World world, final BlockPos pos, final Random rand) {
		final TileEntityForceField te = (TileEntityForceField)world.getTileEntity(pos);
		
		if(te.isOn && te.cooldown == 0 && te.power > 0) {
			for(int i = 0; i < 4; i++) {
				final float f = pos.getX();
				final float f1 = pos.getY() + 2F;
				final float f2 = pos.getZ();
				final float f4 = rand.nextFloat();
				final float f5 = rand.nextFloat();
	
				if(te.color == 0xFF0000)
					world.spawnParticle(EnumParticleTypes.LAVA, f + f4, f1, f2 + f5, 0.0D, 0.0D, 0.0D);
			}
		} else if(te.cooldown > 0) {
			for(int i = 0; i < 4; i++) {
				final float f = pos.getX();
				final float f1 = pos.getY() + 2F;
				final float f2 = pos.getZ();
				final float f4 = rand.nextFloat();
				final float f5 = rand.nextFloat();
	
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, f + f4, f1, f2 + f5, 0.0D, 0.0D, 0.0D);
			}
		}
	}
	
	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public boolean isOpaqueCube(final IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isBlockNormalCube(final IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isNormalCube(final IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isNormalCube(final IBlockState state, final IBlockAccess world, final BlockPos pos) {
		return false;
	}
	
	@Override
	public boolean isFullCube(final IBlockState state) {
		return false;
	}

}
