package com.hbm.blocks.bomb;

import java.util.List;

import com.hbm.util.I18nUtil;
import com.hbm.blocks.ModBlocks;
import com.hbm.config.BombConfig;
import com.hbm.entity.effect.EntityNukeTorex;
import com.hbm.entity.logic.EntityNukeExplosionMK5;
import com.hbm.interfaces.IBomb;
import com.hbm.lib.InventoryHelper;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.bomb.TileEntityNukeN2;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class NukeN2 extends BlockContainer implements IBomb {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public NukeN2(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);

		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityNukeN2();
	}

	@Override
	public void breakBlock(final World worldIn, final BlockPos pos, final IBlockState state) {
		InventoryHelper.dropInventoryItems(worldIn, pos, worldIn.getTileEntity(pos));
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote) {
			return true;
		} else if(!player.isSneaking()) {
			final TileEntityNukeN2 entity = (TileEntityNukeN2) world.getTileEntity(pos);
			if(entity != null) {
				player.openGui(MainRegistry.instance, ModBlocks.guiID_nuke_n2, world, pos.getX(), pos.getY(), pos.getZ());
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void neighborChanged(final IBlockState state, final World worldIn, final BlockPos pos, final Block blockIn, final BlockPos fromPos) {
		final TileEntityNukeN2 entity = (TileEntityNukeN2) worldIn.getTileEntity(pos);
		if(worldIn.isBlockPowered(pos) && !worldIn.isRemote) {
			final int charges = entity.countCharges();
			if(charges > 0) {
				this.onPlayerDestroy(worldIn, pos, worldIn.getBlockState(pos));
				entity.clearSlots();
				worldIn.setBlockToAir(pos);
				igniteTestBomb(worldIn, pos.getX(), pos.getY(), pos.getZ(), (int)(BombConfig.n2Radius*charges/12F));
			}
		}
	}

	@Override
	public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
	}
	
	public boolean igniteTestBomb(final World world, final int x, final int y, final int z, final int r)
	{
		if (!world.isRemote)
		{
			world.playSound(null, x, y, z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0f, world.rand.nextFloat() * 0.1F + 0.9F);

	    	world.spawnEntity(EntityNukeExplosionMK5.statFacNoRad(world, r, x + 0.5, y + 0.5, z + 0.5));
			if(BombConfig.enableNukeClouds) {
				EntityNukeTorex.statFac(world, x + 0.5, y + 0.5, z + 0.5, r);
			}
    	}
    	
		return false;
	}

	@Override
	public void explode(final World world, final BlockPos pos) {
		final TileEntityNukeN2 entity = (TileEntityNukeN2) world.getTileEntity(pos);
		final int charges = entity.countCharges();
		if(charges > 0) {
			this.onPlayerDestroy(world, pos, world.getBlockState(pos));
			entity.clearSlots();
			world.setBlockToAir(pos);
			igniteTestBomb(world, pos.getX(), pos.getY(), pos.getZ(), (int)(BombConfig.n2Radius*charges/12F));
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
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}
	
	@Override
	public int getMetaFromState(final IBlockState state) {
		return state.getValue(FACING).getIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(final int meta) {
		EnumFacing enumfacing = EnumFacing.byIndex(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
	}
	
	
	
	@Override
	public IBlockState withRotation(final IBlockState state, final Rotation rot) {
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}
	
	@Override
	public IBlockState withMirror(final IBlockState state, final Mirror mirrorIn)
	{
	   return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	@Override
	public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced) {
		tooltip.add("§c["+ I18nUtil.resolveKey("trait.extremebomb")+"]§r");
		tooltip.add(" §e"+I18nUtil.resolveKey("desc.radius", BombConfig.n2Radius)+"§r");
		tooltip.add("");
		tooltip.add("§e"+I18nUtil.resolveKey("desc.chargeadds", BombConfig.n2Radius/12)+"§r");
	}

}
