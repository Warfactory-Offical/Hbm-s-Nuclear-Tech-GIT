package com.hbm.blocks.machine;

import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.tileentity.deco.TileEntitySpinnyLight;

import com.hbm.util.I18nUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class BlockSpinnyLight extends BlockContainer {

	public static final PropertyDirection FACING = BlockDirectional.FACING;
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	public static AxisAlignedBB[] boxes;
	static {
		boxes = new AxisAlignedBB[EnumFacing.VALUES.length];
		boxes[EnumFacing.UP.ordinal()] = new AxisAlignedBB(0.3, 0, 0.3, 0.7, 0.4F, 0.7);
		boxes[EnumFacing.DOWN.ordinal()] = new AxisAlignedBB(0.3, 0.6, 0.3, 0.7, 1, 0.7);
		boxes[EnumFacing.NORTH.ordinal()] = new AxisAlignedBB(0.3, 0.3, 0.6, 0.7, 0.7, 1);
		boxes[EnumFacing.SOUTH.ordinal()] = new AxisAlignedBB(0.3, 0.3, 0, 0.7, 0.7, 0.4);
		boxes[EnumFacing.EAST.ordinal()] = new AxisAlignedBB(0, 0.3, 0.3, 0.4, 0.7, 0.7);
		boxes[EnumFacing.WEST.ordinal()] = new AxisAlignedBB(0.6, 0.3, 0.3, 1, 0.7, 0.7);
	}

	public BlockSpinnyLight(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);

		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntitySpinnyLight();
	}
	
	@Override
	public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(!playerIn.getHeldItem(hand).isEmpty() && !worldIn.isRemote){
			final int[] ores = OreDictionary.getOreIDs(playerIn.getHeldItem(hand));
			for(final int ore : ores){
				String name = OreDictionary.getOreName(ore);
				//Why are these ones named differently
				if(name.equals("dyeLightBlue"))
					name = "dyeLight_Blue";
				if(name.equals("dyeLightGray"))
					name = "dyeSilver";
				if(name.length() > 3 && name.startsWith("dye")){
					try {
						final EnumDyeColor color = EnumDyeColor.valueOf(name.substring(3).toUpperCase());
						final TileEntitySpinnyLight ent = (TileEntitySpinnyLight)worldIn.getTileEntity(pos);
						ent.color = color;
						ent.markDirty();
						worldIn.notifyBlockUpdate(pos, state, state, 2 | 4);
						if(!playerIn.isCreative())
							playerIn.getHeldItem(hand).shrink(1);
						return true;
					} catch(final IllegalArgumentException e){}
				}
			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced) {
		super.addInformation(stack, player, tooltip, advanced);
		tooltip.add(I18nUtil.resolveKey("desc.spinnylight"));
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos) {
		return boxes[state.getValue(FACING).ordinal()];
	}
	
	@Override
	public boolean isSideSolid(final IBlockState base_state, final IBlockAccess world, final BlockPos pos, final EnumFacing side) {
		return false;
	}
	
	@Override
	public boolean isFullCube(final IBlockState state) {
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(final IBlockAccess worldIn, final IBlockState state, final BlockPos pos, final EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
	
	@Override
	public boolean canPlaceBlockOnSide(final World worldIn, final BlockPos pos, final EnumFacing side) {
		return canPlaceBlock(worldIn, pos, side);
	}

	@Override
	public boolean canPlaceBlockAt(final World worldIn, final BlockPos pos) {
		for(final EnumFacing enumfacing : EnumFacing.values()) {
			if(canPlaceBlock(worldIn, pos, enumfacing)) {
				return true;
			}
		}
		return false;
	}

	//From BlockButton
	@SuppressWarnings("deprecation")
	protected static boolean canPlaceBlock(final World worldIn, final BlockPos pos, final EnumFacing direction) {
		final BlockPos blockpos = pos.offset(direction.getOpposite());
		final IBlockState iblockstate = worldIn.getBlockState(blockpos);
		final boolean flag = iblockstate.getBlockFaceShape(worldIn, blockpos, direction) == BlockFaceShape.SOLID;
		final Block block = iblockstate.getBlock();

		if(direction == EnumFacing.UP) {
			return iblockstate.isTopSolid() || !isExceptionBlockForAttaching(block) && flag;
		} else {
			return !isExceptBlockForAttachWithPiston(block) && flag;
		}
	}

	@Override
	public IBlockState getStateForPlacement(final World worldIn, final BlockPos pos, final EnumFacing facing, final float hitX, final float hitY, final float hitZ, final int meta, final EntityLivingBase placer) {
		return canPlaceBlock(worldIn, pos, facing) ? this.getDefaultState().withProperty(FACING, facing).withProperty(POWERED, Boolean.FALSE) : this.getDefaultState().withProperty(FACING, EnumFacing.DOWN).withProperty(POWERED, Boolean.FALSE);
	}

	private boolean checkForDrop(final World worldIn, final BlockPos pos, final IBlockState state) {
		if(this.canPlaceBlockAt(worldIn, pos)) {
			return true;
		} else {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos);
			return false;
		}
	}

	@Override
	public void neighborChanged(final IBlockState state, final World world, final BlockPos pos, final Block blockIn, final BlockPos fromPos) {
		if (this.checkForDrop(world, pos, state) && !canPlaceBlock(world, pos, state.getValue(FACING)))
        {
            this.dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
            return;
        }
		if(world.isBlockPowered(pos)) {
			if(!state.getValue(POWERED)){
				final TileEntity te = world.getTileEntity(pos);
				world.setBlockState(pos, state.withProperty(POWERED, true));
				if(te != null){
					te.validate();
					world.setTileEntity(pos, te);
				}
			}
		} else {
			if(state.getValue(POWERED)){
				final TileEntity te = world.getTileEntity(pos);
				world.setBlockState(pos, state.withProperty(POWERED, false));
				if(te != null){
					te.validate();
					world.setTileEntity(pos, te);
				}
			}
		}
	}

	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
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
	public boolean shouldSideBeRendered(final IBlockState blockState, final IBlockAccess blockAccess, final BlockPos pos, final EnumFacing side) {
		return false;
	}
	
	@Override
	public int getLightValue(final IBlockState state) {
		return state.getValue(POWERED) ? 5 : 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING, POWERED);
	}

	@Override
	public int getMetaFromState(final IBlockState state) {
		final int powered = state.getValue(POWERED) ? 1 : 0;
		final int facing = state.getValue(FACING).ordinal();
		return facing | (powered << 3);
	}

	@Override
	public IBlockState getStateFromMeta(final int meta) {
		final boolean powered = ((meta >>> 3) & 1) > 0;
		final EnumFacing facing = EnumFacing.VALUES[meta & 7];
		return this.getDefaultState().withProperty(FACING, facing).withProperty(POWERED, powered);
	}

}
