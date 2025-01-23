package com.hbm.blocks.gas;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.handler.ArmorUtil;
import com.hbm.items.ModItems;
import com.hbm.lib.ForgeDirection;
import com.hbm.main.MainRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.HbmWorldUtility;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockGasBase extends Block {
	
	float red;
	float green;
	float blue;

	public BlockGasBase(final float r, final float g, final float b, final String s) {
		super(ModBlocks.materialGas);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setHardness(0.0F);
		this.setResistance(0.0F);
		this.setTickRandomly(true);
		this.lightOpacity = 0;
		this.red = r;
		this.green = g;
		this.blue = b;
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public boolean isOpaqueCube(final IBlockState state){
		return false;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state){
		return EnumBlockRenderType.INVISIBLE;
	}
	
	@Override
	public boolean canCollideCheck(final IBlockState state, final boolean hitIfLiquid){
		return false;
	}
	
	@Override
	public boolean isCollidable(){
		return false;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(final IBlockState blockState, final IBlockAccess worldIn, final BlockPos pos){
		return NULL_AABB;
	}
	
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune){
		return Items.AIR;
	}
	
	@Override
	public boolean shouldSideBeRendered(final IBlockState blockState, final IBlockAccess blockAccess, final BlockPos pos, final EnumFacing side){
		return false;
	}
	
	@Override
	public boolean isBlockNormalCube(final IBlockState state){
		return false;
	}
	
	@Override
	public boolean isReplaceable(final IBlockAccess worldIn, final BlockPos pos){
		return true;
	}

	@Override
	public void updateTick(final World world, final BlockPos pos, final IBlockState state, final Random rand){
		if(!world.isRemote) {
			if(world.rand.nextInt(2)==0){
				if(!tryMove(world, pos.getX(), pos.getY(), pos.getZ(), getFirstDirection(world, pos.getX(), pos.getY(), pos.getZ())))
					tryMove(world, pos.getX(), pos.getY(), pos.getZ(), getSecondDirection(world, pos.getX(), pos.getY(), pos.getZ()));
			}
		}
	}
	
	public abstract ForgeDirection getFirstDirection(World world, int x, int y, int z);

	public ForgeDirection getSecondDirection(final World world, final int x, final int y, final int z) {
		return getFirstDirection(world, x, y, z);
	}

	public boolean tryMove(final World world, final int x, final int y, final int z, final ForgeDirection dir) {
		final BlockPos newPos = new BlockPos(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);

		if (!world.isBlockLoaded(newPos)) {
			return false;
		} else if (world.isAirBlock(newPos)) {
			world.setBlockToAir(new BlockPos(x, y, z));
			world.setBlockState(newPos, this.getDefaultState());
			return true;
		}

		return false;
	}

	public int getDelay(final World world) {
		return 20;
	}

	public ForgeDirection randomHorizontal(final World world) {
		return ForgeDirection.getOrientation(world.rand.nextInt(4) + 2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(final IBlockState stateIn, final World worldIn, final BlockPos pos, final Random rand){
		super.randomDisplayTick(stateIn, worldIn, pos, rand);
		final EntityPlayer p = MainRegistry.proxy.me();
		if(ArmorUtil.checkArmorPiece(p, ModItems.ashglasses, 3)) {
			final NBTTagCompound data = new NBTTagCompound();
			data.setString("type", "vanillaExt");
			data.setString("mode", "cloud");
			data.setDouble("posX", pos.getX() + 0.5);
			data.setDouble("posY", pos.getY() + 0.5);
			data.setDouble("posZ", pos.getZ() + 0.5);
			data.setFloat("r", red);
			data.setFloat("g", green);
			data.setFloat("b", blue);
			MainRegistry.proxy.effectNT(data);
		}
	}
}