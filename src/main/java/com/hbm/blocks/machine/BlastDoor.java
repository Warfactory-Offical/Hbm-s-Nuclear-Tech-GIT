package com.hbm.blocks.machine;

import java.util.List;

import com.hbm.util.I18nUtil;
import com.hbm.blocks.ModBlocks;
import com.hbm.interfaces.IDoor;
import com.hbm.interfaces.IBomb;
import com.hbm.interfaces.IMultiBlock;
import com.hbm.interfaces.IRadResistantBlock;
import com.hbm.items.ModItems;
import com.hbm.items.tool.ItemLock;
import com.hbm.tileentity.machine.TileEntityBlastDoor;

import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import net.minecraft.client.util.ITooltipFlag;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

@Optional.InterfaceList({@Optional.Interface(iface = "micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock", modid = "galacticraftcore")})
public class BlastDoor extends BlockContainer implements IBomb, IMultiBlock, IPartialSealableBlock, IRadResistantBlock {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlastDoor(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	public boolean isSealed(final World world, final BlockPos blockPos, final EnumFacing direction){
		if(world != null) {
			final TileEntity entity = world.getTileEntity(blockPos);
			if (entity != null) {
				if (IDoor.class.isAssignableFrom(entity.getClass())) {
					// Doors should be rad sealed when closed
					return ((IDoor) entity).getState() == IDoor.DoorState.CLOSED;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isRadResistant(final World worldIn, final BlockPos blockPos) {
		// Door should be rad resistant only when closed
		if (worldIn != null) {
			final TileEntity entity = worldIn.getTileEntity(blockPos);
			if (entity != null) {
				if (IDoor.class.isAssignableFrom(entity.getClass())) {
					return ((IDoor) entity).getState() == IDoor.DoorState.CLOSED;
				}
			}
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityBlastDoor();
	}

	@Override
	public void explode(final World world, final BlockPos pos) {
		final TileEntityBlastDoor entity = (TileEntityBlastDoor) world.getTileEntity(pos);
		if(entity != null)
		{
			if(!entity.isLocked()) {
				entity.tryToggle();
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		if(world.isRemote)
		{
			return true;
		} else if(player.getHeldItemMainhand().getItem() instanceof ItemLock || player.getHeldItemMainhand().getItem() == ModItems.key_kit) {
			return false;
			
		} if(!player.isSneaking()) {
			
			final TileEntityBlastDoor entity = (TileEntityBlastDoor) world.getTileEntity(pos);
			if(entity != null) {
				if(entity.canAccess(player)){
					entity.tryToggle();
					return true;
				}	
			}
			return false;
		}
		
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(final World world, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
		final TileEntityBlastDoor te = (TileEntityBlastDoor) world.getTileEntity(pos);
		
		world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
		
		//frame
		if(!(te.placeDummy(pos.up(1)) &&
			te.placeDummy(pos.up(2)) &&
			te.placeDummy(pos.up(3)) &&
			te.placeDummy(pos.up(4)) &&
			te.placeDummy(pos.up(5)) &&
			te.placeDummy(pos.up(6))))
			world.destroyBlock(pos, true);
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
	public boolean shouldSideBeRendered(final IBlockState blockState, final IBlockAccess blockAccess, final BlockPos pos, final EnumFacing side) {
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
	public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced) {
		final float hardness = this.getExplosionResistance(null);
		tooltip.add("§2[" + I18nUtil.resolveKey("trait.radshield") + "]");
		if(hardness > 50){
			tooltip.add("§6" + I18nUtil.resolveKey("trait.blastres", hardness));
		}
	}
}
