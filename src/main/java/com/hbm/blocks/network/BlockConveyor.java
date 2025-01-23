package com.hbm.blocks.network;

import api.hbm.block.IToolable;
import com.hbm.blocks.ModBlocks;
import com.hbm.entity.item.EntityMovingItem;
import api.hbm.block.IConveyorBelt;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockConveyor extends Block implements IConveyorBelt, IToolable {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final AxisAlignedBB CONVEYOR_BB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);

	public BlockConveyor(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);

		ModBlocks.ALL_BLOCKS.add(this);
	}
	@Override
	public boolean canItemStay(final World world, final int x, final int y, final int z, final Vec3d itemPos) {
		return true;
	}

	@Override
	public Vec3d getTravelLocation(final World world, final int x, final int y, final int z, final Vec3d itemPos, final double speed) {
		final BlockPos pos = new BlockPos(x, y, z);
		final EnumFacing dir = this.getTravelDirection(world, pos, itemPos);
		final Vec3d snap = this.getClosestSnappingPosition(world, pos, itemPos);
		final Vec3d dest = new Vec3d(
				snap.x - dir.getXOffset() * speed,
				snap.y - dir.getYOffset() * speed,
				snap.z - dir.getZOffset() * speed);
		final Vec3d motion = new Vec3d(
				dest.x - itemPos.x,
				dest.y - itemPos.y,
				dest.z - itemPos.z);
		final double len = motion.length();
		final Vec3d ret = new Vec3d(
				itemPos.x + motion.x / len * speed,
				itemPos.y + motion.y / len * speed,
				itemPos.z + motion.z / len * speed);
		return ret;
	}


	public EnumFacing getTravelDirection(final World world, final BlockPos pos, final Vec3d itemPos) {
		return EnumFacing.byIndex(world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public Vec3d getClosestSnappingPosition(final World world, final BlockPos pos, final Vec3d itemPos) {
		final EnumFacing dir = this.getTravelDirection(world, pos, itemPos);

		final double posX = MathHelper.clamp(itemPos.x, pos.getX(), pos.getX() + 1);
		final double posZ = MathHelper.clamp(itemPos.z, pos.getZ(), pos.getZ() + 1);

		double x = pos.getX() + 0.5;
		double z = pos.getZ() + 0.5;
		final double y = pos.getY() + 0.25;

		if (dir.getAxis() == EnumFacing.Axis.X) {
			x = posX;
		} else if (dir.getAxis() == EnumFacing.Axis.Z) {
			z = posZ;
		}

		return new Vec3d(x, y, z);
	}

	@Override
	public void onEntityCollision(final World world, final BlockPos pos, final IBlockState state, final Entity entity) {
		if(!world.isRemote) {

			if(entity instanceof EntityItem && entity.ticksExisted > 10 && !entity.isDead) {

				final EntityMovingItem item = new EntityMovingItem(world);
				item.setItemStack(((EntityItem)entity).getItem());
				final Vec3d entityPos = new Vec3d(entity.posX, entity.posY, entity.posZ);
				final Vec3d snap = this.getClosestSnappingPosition(world, pos, entityPos);
				item.setPositionAndRotation(snap.x, snap.y, snap.z, 0, 0);
				world.spawnEntity(item);
				
				entity.setDead();
			}
		}
	}


	
	@Override
	public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
	}
	
	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public boolean isFullCube(final IBlockState state) {
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
	public boolean isOpaqueCube(final IBlockState state) {
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(final IBlockAccess worldIn, final IBlockState state, final BlockPos pos, final EnumFacing face){
		return BlockFaceShape.CENTER;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos) {
		return CONVEYOR_BB;
	}
	
	@Override
	public BlockStateContainer createBlockState() {
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

	protected int getPathDirection(final int meta) {

		if(meta >= 6 && meta <= 9) return 1;
		if(meta >= 10 && meta <= 13) return 2;
		return 0;
	}

	public boolean onScrew(final World world, final EntityPlayer player, final int x, final int y, final int z, final EnumFacing side, final float fX, final float fY, final float fZ, final EnumHand hand, final IToolable.ToolType tool) {
		if(tool != IToolable.ToolType.SCREWDRIVER)
			return false;
		final BlockPos pos = new BlockPos(x, y, z);
		final IBlockState state = world.getBlockState(pos);

		int meta = getMetaFromState(state);
		int newMeta = meta;

		final int dir = getPathDirection(meta);

		if(!player.isSneaking()) {
			if(meta > 9) meta -= 8;
			if(meta > 5) meta -= 4;

			final EnumFacing facing = EnumFacing.byIndex(meta & 7);
			newMeta = facing.rotateY().getIndex() + dir * 4;
		} else {
			if(dir < 2)
				newMeta += 4;
			else
				newMeta -= 8;
		}

		final IBlockState newState = getStateFromMeta(newMeta);
		world.setBlockState(pos, newState, 3);

		return true;
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
}
