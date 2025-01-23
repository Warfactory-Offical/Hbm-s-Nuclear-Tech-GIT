package com.hbm.blocks.bomb;

import com.hbm.blocks.ModBlocks;
import com.hbm.explosion.ExplosionChaos;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.explosion.ExplosionNukeGeneric;
import com.hbm.interfaces.IBomb;
import com.hbm.lib.InventoryHelper;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.bomb.TileEntityBombMulti;

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
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BombMulti extends BlockContainer implements IBomb {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final AxisAlignedBB MULTI_BB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0);
	
	public final float explosionBaseValue = 8.0F;
	public float explosionValue = 0.0F;
	public int clusterCount = 0;
	public int fireRadius = 0;
	public int poisonRadius = 0;
	public int gasCloud = 0;
	
	public BombMulti(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityBombMulti();
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote)
		{
			return true;
		} else if(!player.isSneaking())
		{
			final TileEntityBombMulti entity = (TileEntityBombMulti) world.getTileEntity(pos);
			if(entity != null)
			{
				player.openGui(MainRegistry.instance, ModBlocks.guiID_bomb_multi, world, pos.getX(), pos.getY(), pos.getZ());
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void neighborChanged(final IBlockState state, final World worldIn, final BlockPos pos, final Block blockIn, final BlockPos fromPos) {
		final TileEntityBombMulti entity = (TileEntityBombMulti) worldIn.getTileEntity(pos);
        if (worldIn.isBlockPowered(pos))
        {
        	if(/*entity.getExplosionType() != 0*/entity.isLoaded())
        	{
        		this.onPlayerDestroy(worldIn, pos, state);
            	igniteTestBomb(worldIn, pos.getX(), pos.getY(), pos.getZ());
        	}
        }
	}
	
	@Override
	public void breakBlock(final World worldIn, final BlockPos pos, final IBlockState state) {
		InventoryHelper.dropInventoryItems(worldIn, pos, worldIn.getTileEntity(pos));
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
	}
	
	public boolean igniteTestBomb(final World world, final int x, final int y, final int z)
	{
		final BlockPos pos = new BlockPos(x, y, z);
    	final TileEntityBombMulti entity = (TileEntityBombMulti) world.getTileEntity(pos);
		if (!world.isRemote)
		{
        	if(entity.isLoaded())
        	{
        		this.explosionValue = this.explosionBaseValue;
        		switch(entity.return2type())
        		{
        		case 1:
        			this.explosionValue += 1.0F;
        			break;
        		case 2:
        			this.explosionValue += 4.0F;
        			break;
        		case 3:
        			this.clusterCount += 50;
        			break;
        		case 4:
        			this.fireRadius += 10;
        			break;
        		case 5:
        			this.poisonRadius += 15;
        			break;
        		case 6:
        			this.gasCloud += 50;
        		}
        		switch(entity.return5type())
        		{
        		case 1:
        			this.explosionValue += 1.0F;
        			break;
        		case 2:
        			this.explosionValue += 4.0F;
        			break;
        		case 3:
        			this.clusterCount += 50;
        			break;
        		case 4:
        			this.fireRadius += 10;
        			break;
        		case 5:
        			this.poisonRadius += 15;
        			break;
        		case 6:
        			this.gasCloud += 50;
        		}

        		entity.clearSlots();
            	world.setBlockToAir(pos);
            	//world.createExplosion(null, x , y , z , this.explosionValue, true);
            	ExplosionLarge.explode(world, x, y, z, explosionValue, true, true, true);
            	this.explosionValue = 0;
        		
        		if(this.clusterCount > 0)
        		{
                	ExplosionChaos.cluster(world, x, y, z, this.clusterCount, 0.5);
        		}
        		
        		if(this.fireRadius > 0)
        		{
                	ExplosionChaos.burn(world, pos, this.fireRadius);
        		}
        		
        		if(this.poisonRadius > 0)
        		{
                	ExplosionNukeGeneric.wasteNoSchrab(world, pos, this.poisonRadius);
        		}
        		
        		if(this.gasCloud > 0)
        		{
        			ExplosionChaos.spawnChlorine(world, x, y, z, this.gasCloud, this.gasCloud / 50, 0);
        		}
        		
        		this.clusterCount = 0;
        		this.fireRadius = 0;
        		this.poisonRadius = 0;
        		this.gasCloud = 0;
        		
        		
        	}
        }
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos) {
		return MULTI_BB;
	}

	@Override
	public void explode(final World world, final BlockPos pos) {
		final TileEntityBombMulti entity = (TileEntityBombMulti) world.getTileEntity(pos);
    	if(/*entity.getExplosionType() != 0*/entity.isLoaded())
    	{
    		this.onPlayerDestroy(world, pos, world.getBlockState(pos));
        	igniteTestBomb(world, pos.getX(), pos.getY(), pos.getZ());
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

}
