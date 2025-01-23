package com.hbm.blocks.generic;

import java.util.List;

import com.hbm.util.I18nUtil;
import com.hbm.handler.RadiationSystemNT;
import com.hbm.interfaces.IAnimatedDoor;
import com.hbm.interfaces.IDoor;
import com.hbm.interfaces.IRadResistantBlock;
import com.hbm.blocks.BlockDummyable;
import com.hbm.lib.ForgeDirection;
import com.hbm.items.ModItems;
import com.hbm.items.tool.ItemLock;
import com.hbm.tileentity.DoorDecl;
import com.hbm.tileentity.TileEntityDoorGeneric;

import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

@Optional.InterfaceList({@Optional.Interface(iface = "micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock", modid = "galacticraftcore")})
public class BlockDoorGeneric extends BlockDummyable implements IRadResistantBlock, IPartialSealableBlock {

	public DoorDecl type;
	private final boolean isRadResistant;
	
	public BlockDoorGeneric(final Material materialIn, final DoorDecl type, final boolean isRadResistant, final String s){
		super(materialIn, s);
		this.type = type;
		this.isRadResistant = isRadResistant;
	}

	public boolean isSealed(final World world, final BlockPos blockPos, final EnumFacing direction){
		if (world != null) {
			final int[] corePos = findCore(world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
			if(corePos != null){
				final TileEntity core = world.getTileEntity(new BlockPos(corePos[0], corePos[1], corePos[2]));
				if (core != null && IDoor.class.isAssignableFrom(core.getClass())) {
					// Doors should be sealed only when closed
					return ((IDoor) core).getState() == IDoor.DoorState.CLOSED;
				}
			}
		}

		return false;
	}

	@Override
	public TileEntity createNewTileEntity(final World worldIn, final int meta){
		if(meta >= 12)
			return new TileEntityDoorGeneric();
		return null;
	}

	@Override
	public int[] getDimensions(){
		return type.getDimensions();
	}

	@Override
	public int getOffset(){
		return 0;
	}
	
	@Override
	public boolean isFullCube(final IBlockState state) {
		return false;
	}
	
	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ){
		if(world.isRemote) {
			return true;
		} else if(player.getHeldItem(hand).getItem() instanceof ItemLock || player.getHeldItem(hand).getItem() == ModItems.key_kit) {
			return false;
			
		} if(!player.isSneaking()) {
			final int[] pos1 = findCore(world, pos.getX(), pos.getY(), pos.getZ());
			if(pos1 == null)
				return false;
			final TileEntityDoorGeneric door = (TileEntityDoorGeneric) world.getTileEntity(new BlockPos(pos1[0], pos1[1], pos1[2]));

			if(door != null && door.canAccess(player)) {
				return door.tryToggle(player);
			}
		}
		return false;
	}
	
	@Override
	public boolean isLadder(final IBlockState state, final IBlockAccess world, final BlockPos pos, final EntityLivingBase entity){
		final TileEntity te = world.getTileEntity(pos);
		final int meta = state.getValue(META);
		final boolean open = hasExtra(meta) || (te instanceof TileEntityDoorGeneric && ((TileEntityDoorGeneric)te).shouldUseBB);
		return type.isLadder(open);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void addCollisionBoxToList(final IBlockState state, final World worldIn, final BlockPos pos, final AxisAlignedBB entityBox, final List<AxisAlignedBB> collidingBoxes, final Entity entityIn, final boolean isActualState){
		final AxisAlignedBB box = state.getCollisionBoundingBox(worldIn, pos);
		if(box!= null && (box.minY == 0 && box.maxY == 0))
			return;
		super.addCollisionBoxToList(state, worldIn, pos, entityBox, collidingBoxes, entityIn, isActualState);
	}
	
	@Override
	public void neighborChanged(final IBlockState state, final World world, final BlockPos pos, final Block blockIn, final BlockPos fromPos){
		if(!world.isRemote){
			final int[] corePos = findCore(world, pos.getX(), pos.getY(), pos.getZ());
			if(corePos != null){
				final TileEntity core = world.getTileEntity(new BlockPos(corePos[0], corePos[1], corePos[2]));
				if(core instanceof TileEntityDoorGeneric door){
                    door.updateRedstonePower(pos);
				}
			}
		}
		super.neighborChanged(state, world, pos, blockIn, fromPos);
	}

	public boolean isPassable(final IBlockAccess worldIn, final BlockPos pos) {
		return true;
	}

	public BlockFaceShape getBlockFaceShape(final IBlockAccess worldIn, final IBlockState state, final BlockPos pos, final EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos){
		final int meta = state.getValue(META);
		final TileEntity te = source.getTileEntity(pos);
		final int[] core = this.findCore(source, pos.getX(), pos.getY(), pos.getZ());
		final boolean open = hasExtra(meta) || (te instanceof TileEntityDoorGeneric && ((TileEntityDoorGeneric)te).shouldUseBB);
		if(core == null){
			return FULL_BLOCK_AABB;
		}
		final TileEntity te2 = source.getTileEntity(new BlockPos(core[0], core[1], core[2]));
		final ForgeDirection dir = ForgeDirection.getOrientation(te2.getBlockMetadata() - BlockDummyable.offset);
		final AxisAlignedBB box = type.getBlockBound(pos.add(-core[0], -core[1], -core[2]).rotate(dir.getBlockRotation().add(Rotation.COUNTERCLOCKWISE_90)), open);
		//System.out.println(te2.getBlockMetadata()-offset);
		switch(te2.getBlockMetadata()-offset){
		case 2:
			return new AxisAlignedBB(1-box.minX, box.minY, 1-box.minZ, 1-box.maxX, box.maxY, 1-box.maxZ);
		case 4:
			return new AxisAlignedBB(1-box.minZ, box.minY, box.minX, 1-box.maxZ, box.maxY, box.maxX);
		case 3:
			return new AxisAlignedBB(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
		case 5:
			return new AxisAlignedBB(box.minZ, box.minY, 1-box.minX, box.maxZ, box.maxY, 1-box.maxX);
		}
		return FULL_BLOCK_AABB;
	}

	@Override
	public void onBlockAdded(final World worldIn, final BlockPos pos, final IBlockState state) {
		if(this.isRadResistant){
			RadiationSystemNT.markChunkForRebuild(worldIn, pos);
		}
		super.onBlockAdded(worldIn, pos, state);
	}
	
	@Override
	public void breakBlock(final World worldIn, final BlockPos pos, final IBlockState state) {
		if(this.isRadResistant){
			RadiationSystemNT.markChunkForRebuild(worldIn, pos);
		}
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public boolean isRadResistant(final World world, final BlockPos blockPos){
		if (!this.isRadResistant)
			return false;

		if (world != null) {
			final int[] corePos = findCore(world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
			if(corePos != null){
				final TileEntity core = world.getTileEntity(new BlockPos(corePos[0], corePos[1], corePos[2]));
				if (core != null && IDoor.class.isAssignableFrom(core.getClass())) {
					// Doors should be rad resistant only when closed
					return ((IDoor) core).getState() == IDoor.DoorState.CLOSED;
				}
			}
		}

		return false;
	}

	@Override
	public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced) {
		final float hardness = this.getExplosionResistance(null);
		if(this.isRadResistant){
			tooltip.add("§2[" + I18nUtil.resolveKey("trait.radshield") + "]");
		}
		if(hardness > 50){
			tooltip.add("§6" + I18nUtil.resolveKey("trait.blastres", hardness));
		}
	}
}
