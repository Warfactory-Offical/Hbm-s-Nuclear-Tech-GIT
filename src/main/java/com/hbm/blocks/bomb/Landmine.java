package com.hbm.blocks.bomb;
import com.hbm.util.ItemStackUtil;

import java.util.Random;
import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.BombConfig;
import com.hbm.entity.effect.EntityNukeTorex;
import com.hbm.entity.logic.EntityNukeExplosionMK5;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.interfaces.IBomb;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.bomb.TileEntityLandmine;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Landmine extends BlockContainer implements IBomb {

	public static boolean safeMode = false;
	private static final Random rand = new Random();
	
	public static final float f = 0.0625F;
	public static final AxisAlignedBB AP_BOX = new AxisAlignedBB(6 * f, 0.0F, 6 * f, 10 * f, 2 * f, 10 * f);
	public static final AxisAlignedBB HE_BOX = new AxisAlignedBB(4 * f, 0.0F, 4 * f, 12 * f, 2 * f, 12 * f);
	public static final AxisAlignedBB SHRAP_BOX = new AxisAlignedBB(4 * f, 0.0F, 4 * f, 12 * f, 2 * f, 12 * f);
	public static final AxisAlignedBB FAT_BOX = new AxisAlignedBB(5 * f, 0.0F, 4 * f, 11 * f, 6 * f, 12 * f);
	
	public Landmine(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityLandmine();
	}
	
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Items.AIR;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos) {
		if(this == ModBlocks.mine_ap){
			return AP_BOX;
		} else if(this == ModBlocks.mine_he){
			return HE_BOX;
		} else if(this == ModBlocks.mine_shrap){
			return SHRAP_BOX;
		} else if(this == ModBlocks.mine_fat){
			return FAT_BOX;
		} else {
			return FULL_BLOCK_AABB;
		}
	}
	
	@Override
	public boolean canPlaceBlockAt(final World worldIn, final BlockPos pos) {
		return worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP) || worldIn.getBlockState(pos.down()).getBlock() instanceof BlockFence;
	}
	
	@Override
	public void neighborChanged(final IBlockState state, final World world, final BlockPos pos, final Block blockIn, final BlockPos fromPos) {
		if (world.isBlockPowered(pos))
        {
        	explode(world, pos);
        }
        
		boolean flag = !world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP) && !(world.getBlockState(pos.down()).getBlock() instanceof BlockFence);

        if (flag) {
			this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
			world.setBlockToAir(pos);
		}
	}
	
	@Override
	public void breakBlock(final World worldIn, final BlockPos pos, final IBlockState state) {
		if (!safeMode) {
			explode(worldIn, pos);
		}
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if (player.getHeldItemMainhand().getItem() == ModItems.defuser || player.getHeldItemOffhand().getItem() == ModItems.defuser || player.getHeldItemMainhand().getItem() == ModItems.defuser_desh || player.getHeldItemOffhand().getItem() == ModItems.defuser_desh) {

			safeMode = true;
			world.setBlockToAir(pos);

			final ItemStack itemstack = ItemStackUtil.itemStackFrom(this, 1);
			final float f = world.rand.nextFloat() * 0.6F + 0.2F;
			final float f1 = world.rand.nextFloat() * 0.2F;
			final float f2 = world.rand.nextFloat() * 0.6F + 0.2F;
			
			final EntityItem entityitem = new EntityItem(world, pos.getX() + f, pos.getY() + f1 + 1, pos.getZ() + f2, itemstack);

			final float f3 = 0.05F;
			entityitem.motionX = (float) world.rand.nextGaussian() * f3;
			entityitem.motionY = (float) world.rand.nextGaussian() * f3 + 0.2F;
			entityitem.motionZ = (float) world.rand.nextGaussian() * f3;

			if(!world.isRemote)
				world.spawnEntity(entityitem);

			safeMode = false;
			return true;
		}

		return false;
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
	public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced) {
		if(this == ModBlocks.mine_fat){
			tooltip.add("§2[Nuclear Mine]§r");
			tooltip.add(" §eRadius: "+BombConfig.fatmanRadius+"m§r");
			tooltip.add("§2[Fallout]§r");
			tooltip.add(" §aRadius: "+ BombConfig.fatmanRadius *(1+BombConfig.falloutRange/100)+"m§r");
		}
	}

	@Override
	public void explode(final World world, final BlockPos pos) {
		if(!world.isRemote) {
			final int x = pos.getX();
			final int y = pos.getY();
			final int z = pos.getZ();
			Landmine.safeMode = true;
			world.destroyBlock(pos, false);
			Landmine.safeMode = false;
			
			if (this == ModBlocks.mine_ap) {
				world.newExplosion(null, x + 0.5, y + 0.5, z + 0.5, 2.5F, false, false);
			}
			if (this == ModBlocks.mine_he) {
				ExplosionLarge.explode(world, x + 0.5, y + 0.5, z + 0.5, 10F, true, false, false);
			}
			if (this == ModBlocks.mine_shrap) {
				ExplosionLarge.explode(world, x + 0.5, y + 0.5, z + 0.5, 1, true, false, false);
				ExplosionLarge.spawnShrapnelShower(world, x + 0.5, y + 0.5, z + 0.5, 0, 1D, 0, 45, 0.2D);
				ExplosionLarge.spawnShrapnels(world, x + 0.5, y + 0.5, z + 0.5, 5);
			}
			if (this == ModBlocks.mine_fat) {

    	    	world.spawnEntity(EntityNukeExplosionMK5.statFac(world, BombConfig.fatmanRadius, x + 0.5, y + 0.5, z + 0.5));
    	    	if(rand.nextInt(100) == 0 || MainRegistry.polaroidID == 11){
	                EntityNukeTorex.statFacBale(world, x + 0.5, y + 0.5, z + 0.5, BombConfig.fatmanRadius);
	            } else {
	                EntityNukeTorex.statFac(world, x + 0.5, y + 0.5, z + 0.5, BombConfig.fatmanRadius);
	            }
			}
		}
	}
}
