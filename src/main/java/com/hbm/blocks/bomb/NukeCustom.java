package com.hbm.blocks.bomb;

import java.util.List;

import com.hbm.util.I18nUtil;
import com.hbm.blocks.ModBlocks;
import com.hbm.config.BombConfig;
import com.hbm.entity.effect.EntityCloudSolinium;
import com.hbm.entity.effect.EntityCloudFleija;
import com.hbm.entity.effect.EntityNukeTorex;
import com.hbm.entity.grenade.EntityGrenadeZOMG;
import com.hbm.entity.logic.EntityBalefire;
import com.hbm.entity.logic.EntityNukeExplosionMK3;
import com.hbm.entity.logic.EntityNukeExplosionMK5;
import com.hbm.entity.projectile.EntityFallingNuke;
import com.hbm.explosion.ExplosionChaos;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.interfaces.IBomb;
import com.hbm.lib.InventoryHelper;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.bomb.TileEntityNukeCustom;

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
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class NukeCustom extends BlockContainer implements IBomb {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public NukeCustom(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);

		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityNukeCustom();
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

	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if (world.isRemote) {
			return true;
			
		} else if (!player.isSneaking()) {
			
			final TileEntityNukeCustom entity = (TileEntityNukeCustom) world.getTileEntity(pos);
			
			if (entity != null) {
				player.openGui(MainRegistry.instance, ModBlocks.guiID_nuke_custom, world, pos.getX(), pos.getY(), pos.getZ());
			}
			return true;
			
		} else {
			return false;
		}
	}
	
	@Override
	public void neighborChanged(final IBlockState state, final World world, final BlockPos pos, final Block blockIn, final BlockPos fromPos) {
		if (world.isBlockPowered(pos) && !world.isRemote) {
			this.explode(world, pos);
		}
	}
	
	public static void explodeCustom(final World world, final double xCoord, final double yCoord, final double zCoord, float tnt, float nuke, float hydro, float bale, float dirty, float schrab, float sol, float euph) {
		
		dirty = Math.min(dirty, BombConfig.maxCustomDirtyRadius);
		
		/// EUPHEMIUM ///
		if(euph > 0) {
			
			euph = Math.min(euph, BombConfig.maxCustomEuphLvl);
			final EntityGrenadeZOMG zomg = new EntityGrenadeZOMG(world, xCoord, yCoord, zCoord);
			ExplosionChaos.zomg(world, xCoord, yCoord, zCoord, (int)(100 * euph), null, zomg);

		// SOLINIUM ///
		} else if(sol > 0) {
			
			sol += schrab / 2 + bale / 4 + hydro / 8 + nuke / 16 + tnt / 32;
			sol = Math.min(sol, BombConfig.maxCustomSolRadius);

			final EntityNukeExplosionMK3 entity = new EntityNukeExplosionMK3(world);
			entity.setPosition(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5);
    		entity.destructionRange = (int) sol;
    		entity.speed = BombConfig.blastSpeed;
    		entity.coefficient = 1.0F;
    		entity.waste = false;
    		entity.extType = 1;
    		world.spawnEntity(entity);
    	
    		final EntityCloudSolinium cloud = new EntityCloudSolinium(world, (int)sol);
    		cloud.setPosition(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5);
    		world.spawnEntity(cloud);
			
		// SCHRABIDIUM ///
		} else if(schrab > 0) {
			
			schrab += bale / 2 + hydro / 4 + nuke / 8 + tnt / 16;
			schrab = Math.min(schrab, BombConfig.maxCustomSchrabRadius);

			final EntityNukeExplosionMK3 entity = new EntityNukeExplosionMK3(world);
			entity.setPosition(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5);
    		entity.destructionRange = (int) schrab;
    		entity.speed = BombConfig.blastSpeed;
    		entity.coefficient = 1.0F;
    		entity.waste = false;
    		world.spawnEntity(entity);
    	
    		final EntityCloudFleija cloud = new EntityCloudFleija(world, (int)schrab);
    		cloud.setPosition(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5);
    		world.spawnEntity(cloud);
    		
    	/// ANTIMATTER ///
		} else if(bale > 0) {

			bale += hydro / 2 + nuke / 4 + tnt / 8;
			bale = Math.min(bale, BombConfig.maxCustomBaleRadius);

			final EntityBalefire bf = new EntityBalefire(world);
    		bf.setPosition(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5);
			bf.destructionRange = (int) bale;
			world.spawnEntity(bf);
			if(BombConfig.enableNukeClouds) {
				EntityNukeTorex.statFacBale(world, xCoord + 0.5, yCoord + 5, zCoord + 0.5, bale);
			}
			
		/// HYDROGEN ///
		} else if(hydro > 0) {

			hydro += nuke / 2 + tnt / 4;
			hydro = Math.min(hydro, BombConfig.maxCustomHydroRadius);
			dirty *= 0.25F;

			world.spawnEntity(EntityNukeExplosionMK5.statFac(world, (int)hydro, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5).moreFallout((int)dirty));
			if(BombConfig.enableNukeClouds) {
				EntityNukeTorex.statFac(world, xCoord + 0.5, yCoord + 5, zCoord + 0.5, hydro);
			}
			
		/// NUCLEAR ///
		} else if(nuke > 0) {
			
			nuke += tnt / 2;
			nuke = Math.min(nuke, BombConfig.maxCustomNukeRadius);

			world.spawnEntity(EntityNukeExplosionMK5.statFac(world, (int)nuke, xCoord + 0.5, yCoord + 5, zCoord + 0.5).moreFallout((int)dirty));
			if(BombConfig.enableNukeClouds) {
				EntityNukeTorex.statFac(world, xCoord + 0.5, yCoord + 5, zCoord + 0.5, nuke);
			}
			
		/// NON-NUCLEAR ///
		} else if(tnt >= 75) {

			tnt = Math.min(tnt, BombConfig.maxCustomTNTRadius);

			world.spawnEntity(EntityNukeExplosionMK5.statFacNoRad(world, (int)tnt, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5));
			if(BombConfig.enableNukeClouds) {
				EntityNukeTorex.statFac(world, xCoord + 0.5, yCoord + 5, zCoord + 0.5, tnt);
			}
		} else if(tnt > 0) {
			
			ExplosionLarge.explode(world, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, tnt, true, true, true);
		}
	}
	
	@Override
	public void explode(final World world, final BlockPos pos) {
		final TileEntityNukeCustom entity = (TileEntityNukeCustom) world.getTileEntity(pos);
		
		if(!entity.isFalling()) {
			
			entity.clearSlots();
			world.destroyBlock(pos, false);
			NukeCustom.explodeCustom(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, entity.tnt, entity.nuke, entity.hydro, entity.bale, entity.dirty, entity.schrab, entity.sol, entity.euph);
			
		} else {
			
			final EntityFallingNuke bomb = new EntityFallingNuke(world, entity.tnt, entity.nuke, entity.hydro, entity.bale, entity.dirty, entity.schrab, entity.sol, entity.euph);
			bomb.getDataManager().set(EntityFallingNuke.FACING, world.getBlockState(pos).getValue(FACING));
			bomb.setPositionAndRotation(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);
			entity.clearSlots();
			world.setBlockToAir(pos);
			world.spawnEntity(bomb);
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

		if(enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public IBlockState withRotation(final IBlockState state, final Rotation rot) {
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(final IBlockState state, final Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	@Override
	public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced) {
		tooltip.add("§d["+ I18nUtil.resolveKey("trait.modularbomb")+"]§r");
	}
}
