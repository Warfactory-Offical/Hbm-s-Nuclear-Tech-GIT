package com.hbm.blocks.machine;

import java.util.List;

import com.hbm.handler.RadiationSystemNT;
import com.hbm.interfaces.IDoor;
import com.hbm.interfaces.IBomb;
import com.hbm.interfaces.IMultiBlock;
import com.hbm.interfaces.IRadResistantBlock;
import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.items.tool.ItemLock;
import com.hbm.tileentity.machine.TileEntitySiloHatch;

import com.hbm.util.I18nUtil;
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
public class BlockSiloHatch extends BlockContainer implements IBomb, IMultiBlock, IRadResistantBlock, IPartialSealableBlock {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockSiloHatch(final Material materialIn, final String s) {
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
					// Doors should be sealed only when closed
					return ((IDoor) entity).getState() == IDoor.DoorState.CLOSED;
				}
			}
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntitySiloHatch();
	}

	@Override
	public void explode(final World world, final BlockPos pos) {
		final TileEntitySiloHatch entity = (TileEntitySiloHatch) world.getTileEntity(pos);
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
		} 
		if(!player.isSneaking()) {
			
			final TileEntitySiloHatch entity = (TileEntitySiloHatch) world.getTileEntity(pos);
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
		final TileEntitySiloHatch te = (TileEntitySiloHatch) world.getTileEntity(pos);
		final BlockPos center = pos.offset(placer.getHorizontalFacing(), 3);
		for(int i = -3; i <= 3; i ++){
			for(int j = -3; j <= 3; j ++){
				//Cut out the corners
				if((Math.abs(i) == 3 && Math.abs(j) == 3) || (Math.abs(i) == 2 && Math.abs(j) == 3) || (Math.abs(i) == 3 && Math.abs(j) == 2)){
					continue;
				}
				final BlockPos b = center.add(i, 0, j);
				if(!b.equals(pos)){
					if(!te.placeDummy(b)){
						world.destroyBlock(pos, true);
						return;
					}
				}
			}
		}
		world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
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
	public void onBlockAdded(final World world, final BlockPos pos, final IBlockState state) {
		RadiationSystemNT.markChunkForRebuild(world, pos);
		super.onBlockAdded(world, pos, state);
	}
	
	@Override
	public void breakBlock(final World world, final BlockPos pos, final IBlockState state) {
		RadiationSystemNT.markChunkForRebuild(world, pos);
		super.breakBlock(world, pos, state);
	}

	@Override
	public boolean isRadResistant(final World worldIn, final BlockPos blockPos){

		if (worldIn != null) {
			final TileEntity entity = worldIn.getTileEntity(blockPos);
			if (entity != null) {
				if (IDoor.class.isAssignableFrom(entity.getClass())) {
					// Doors should be rad resistant only when closed
					return ((IDoor) entity).getState() == IDoor.DoorState.CLOSED;
				}
			}
		}

		return true;
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
