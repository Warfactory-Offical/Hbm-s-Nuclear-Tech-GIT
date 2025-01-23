package com.hbm.blocks.machine;
import com.hbm.util.ItemStackUtil;

import java.util.Random;

import com.hbm.handler.RadiationSystemNT;
import com.hbm.interfaces.IDoor;
import com.hbm.interfaces.IBomb;
import com.hbm.interfaces.IDummy;
import com.hbm.interfaces.IRadResistantBlock;
import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.items.tool.ItemLock;
import com.hbm.tileentity.machine.TileEntityBlastDoor;
import com.hbm.tileentity.machine.TileEntityDummy;

import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

@Optional.InterfaceList({@Optional.Interface(iface = "micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock", modid = "galacticraftcore")})
public class DummyBlockBlast extends BlockContainer implements IDummy, IBomb, IRadResistantBlock, IPartialSealableBlock {

	public static boolean safeBreak = false;

	public DummyBlockBlast(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	public boolean isSealed(final World worldIn, final BlockPos blockPos, final EnumFacing direction){
		if (worldIn != null)
		{
			final TileEntity te = worldIn.getTileEntity(blockPos);
			if(te != null && te instanceof TileEntityDummy && ((TileEntityDummy) te).target != null) {

				final TileEntity actualTileEntity = worldIn.getTileEntity(((TileEntityDummy) te).target);
				if (actualTileEntity != null) {
					if (IDoor.class.isAssignableFrom(actualTileEntity.getClass())) {
						// Doors should be sealed only when closed
						return ((IDoor) actualTileEntity).getState() == IDoor.DoorState.CLOSED;
					}
				}
			}
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityDummy();
	}
	
	@Override
	public void breakBlock(final World world, final BlockPos pos, final IBlockState state) {
		if(!safeBreak) {
    		final TileEntity te = world.getTileEntity(pos);
    		if(te != null && te instanceof TileEntityDummy) {
    		
    			if(!world.isRemote)
    				world.destroyBlock(((TileEntityDummy)te).target, true);
    		}
    	}
    	world.removeTileEntity(pos);
		RadiationSystemNT.markChunkForRebuild(world, pos);
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote)
		{
			return true;
		} else if(player.getHeldItemMainhand().getItem() instanceof ItemLock || player.getHeldItemMainhand().getItem() == ModItems.key_kit) {
			return false;
			
		} else if(!player.isSneaking())
		{
			final TileEntity til = world.getTileEntity(pos);
			if(til != null && til instanceof TileEntityDummy) {
						
				final TileEntityBlastDoor entity = (TileEntityBlastDoor) world.getTileEntity(((TileEntityDummy)til).target);
				if(entity != null) {
					if(entity.canAccess(player)){
						entity.tryToggle();
						return true;
					}	
				}
			}

		}
		return false;
	}

	@Override
	public void explode(final World world, final BlockPos pos) {
		final TileEntity te = world.getTileEntity(pos);
		if(te != null && te instanceof TileEntityDummy) {
			
			final TileEntityBlastDoor entity = (TileEntityBlastDoor) world.getTileEntity(((TileEntityDummy)te).target);
			if(entity != null && !entity.isLocked())
			{
				entity.tryToggle();
			}
		}
	}

	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}
	
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Items.AIR;
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
	public ItemStack getPickBlock(final IBlockState state, final RayTraceResult target, final World world, final BlockPos pos, final EntityPlayer player) {
		return ItemStackUtil.itemStackFrom(ModBlocks.blast_door);
	}

	@Override
	public void onBlockAdded(final World worldIn, final BlockPos pos, final IBlockState state) {
		RadiationSystemNT.markChunkForRebuild(worldIn, pos);
		super.onBlockAdded(worldIn, pos, state);
	}

	@Override
	public boolean isRadResistant(final World worldIn, final BlockPos blockPos){
		// Door should be rad resistant only when closed
		if (worldIn != null) {
			final TileEntity te = worldIn.getTileEntity(blockPos);
			if(te != null && te instanceof TileEntityDummy && ((TileEntityDummy) te).target != null) {

				final TileEntity actualTileEntity = worldIn.getTileEntity(((TileEntityDummy) te).target);
				if (actualTileEntity != null) {
					if (IDoor.class.isAssignableFrom(actualTileEntity.getClass())) {
						return ((IDoor) actualTileEntity).getState() == IDoor.DoorState.CLOSED;
					}
				}
			}
		}

		return true;
	}
}
