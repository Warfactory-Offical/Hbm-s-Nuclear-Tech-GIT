package com.hbm.blocks.network.energy;

import java.util.ArrayList;
import java.util.List;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.ILookOverlay;
import com.hbm.blocks.ITooltipProvider;
import com.hbm.main.MainRegistry;
import com.hbm.lib.Library;
import com.hbm.lib.ForgeDirection;
import com.hbm.tileentity.INBTPacketReceiver;
import com.hbm.tileentity.TileEntityLoadedBase;
import com.hbm.util.I18nUtil;

import api.hbm.block.IToolable;
import api.hbm.block.IToolable.ToolType;
import api.hbm.energy.IEnergyUser;
import api.hbm.energy.IEnergyConnectorBlock;
import api.hbm.energy.IEnergyConnector.ConnectionPriority;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

public class CableDiode extends BlockContainer implements IEnergyConnectorBlock, ILookOverlay, IToolable, ITooltipProvider {
	
	public static final PropertyDirection FACING = BlockDirectional.FACING;
	
	public CableDiode(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public int getMetaFromState(final IBlockState state){
		return state.getValue(FACING).getIndex();
	}

	@Override
	public IBlockState getStateFromMeta(final int meta) {
		final EnumFacing enumfacing = EnumFacing.byIndex(meta);
        return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public IBlockState withRotation(final IBlockState state, final Rotation rot){
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(final IBlockState state, final Mirror mirrorIn){
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}
	
	@Override
	public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)));
	}

	@Override
	public boolean canConnect(final IBlockAccess world, final BlockPos pos, final ForgeDirection dir) {
		return true;
	}

	@Override
	public boolean onScrew(final World world, final EntityPlayer player, final int x, final int y, final int z, final EnumFacing side, final float fX, final float fY, final float fZ, final EnumHand hand, final ToolType tool){
	
		final TileEntityDiode te = (TileEntityDiode)world.getTileEntity(new BlockPos(x, y, z));
		
		if(world.isRemote)
			return true;
		
		if(tool == ToolType.SCREWDRIVER) {
			if(te.level < 17)
				te.level++;
			te.markDirty();
			INBTPacketReceiver.networkPack(te, te.packValues(), 20);
			return true;
		}
		
		if(tool == ToolType.HAND_DRILL) {
			if(te.level > 1)
				te.level--;
			te.markDirty();
			INBTPacketReceiver.networkPack(te, te.packValues(), 20);
			return true;
		}
		
		if(tool == ToolType.DEFUSER) {
			int p = te.priority.ordinal() + 1;
			if(p > 2) p = 0;
			te.priority = ConnectionPriority.values()[p];
			te.markDirty();
			INBTPacketReceiver.networkPack(te, te.packValues(), 20);
			return true;
		}
		
		return false;
	}

	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
        this.addStandardInfo(list);
        super.addInformation(stack, worldIn, list, flagIn);
    }

	@Override
	public void printHook(final Pre event, final World world, final int x, final int y, final int z) {
		
		final TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
		
		if(!(te instanceof TileEntityDiode diode))
			return;

        final List<String> text = new ArrayList();
		text.add("Max.: " + Library.getShortNumber(diode.getMaxPower()*20) + "HE/s");
		text.add("Priority: " + diode.priority.name());
		
		ILookOverlay.printGeneric(event, I18nUtil.resolveKey(getTranslationKey() + ".name"), 0xffff00, 0x404000, text);
	}

	@Override
	public TileEntity createNewTileEntity(final World world, final int meta) {
		return new TileEntityDiode();
	}

	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	public static class TileEntityDiode extends TileEntityLoadedBase implements ITickable, IEnergyUser, INBTPacketReceiver {

		@Override
		public void networkUnpack(final NBTTagCompound nbt){
			level = nbt.getInteger("level");
			priority = ConnectionPriority.values()[nbt.getByte("p")];
		}

		public NBTTagCompound packValues(){
			final NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger("level", level);
			nbt.setByte("p", (byte) this.priority.ordinal());
			return nbt;
		}
		
		@Override
		public void readFromNBT(final NBTTagCompound nbt) {
			super.readFromNBT(nbt);
			level = nbt.getInteger("level");
			priority = ConnectionPriority.values()[nbt.getByte("p")];
		}
		
		@Override
		public NBTTagCompound writeToNBT(final NBTTagCompound nbt) {
			super.writeToNBT(nbt);
			nbt.setInteger("level", level);
			nbt.setByte("p", (byte) this.priority.ordinal());
			return nbt;
		}

		@Override
		public SPacketUpdateTileEntity getUpdatePacket(){
			return new SPacketUpdateTileEntity(this.getPos(), 0, this.writeToNBT(new NBTTagCompound()));
		}

		@Override
		public NBTTagCompound getUpdateTag() {
			return this.writeToNBT(new NBTTagCompound());
		}
		
		@Override
		public void onDataPacket(final NetworkManager net, final SPacketUpdateTileEntity pkt) {
			this.readFromNBT(pkt.getNbtCompound());
		}
		
		int level = 1;
		
		private ForgeDirection getDir() {
			final IBlockState state = world.getBlockState(pos);
			return ForgeDirection.getOrientation(state.getBlock().getMetaFromState(state)).getOpposite();
		}

		@Override
		public void update() {
			if(!world.isRemote) {
				this.updateConnectionsExcept(world, pos, getDir());
			}
		}

		@Override
		public boolean canConnect(final ForgeDirection dir) {
			return dir != getDir();
		}
		
		private boolean recursionBrake = false;
		private long subBuffer;
		private long contingent = 0;
		private long lastTransfer = 0;
		private int pulses = 0;
		public ConnectionPriority priority = ConnectionPriority.NORMAL;

		@Override
		public long transferPower(long power) {

			if(recursionBrake)
				return power;
			
			pulses++;
			
			if(lastTransfer != world.getTotalWorldTime()) {
				lastTransfer = world.getTotalWorldTime();
				contingent = getMaxPower();
				pulses = 0;
			}
			
			if(contingent <= 0 || pulses > 10)
				return power;
			
			//this part turns "maxPower" from a glorified transfer weight into an actual transfer cap
			final long overShoot = Math.max(0, power - contingent);
			power = Math.min(power, contingent);
			
			recursionBrake = true;
			this.subBuffer = power;
			
			final ForgeDirection dir = getDir();
			this.sendPower(world, pos.add(dir.offsetX, dir.offsetY, dir.offsetZ), dir);
			final long ret = this.subBuffer;
			
			final long sent = power - ret;
			contingent -= sent;
			
			this.subBuffer = 0;
			recursionBrake = false;
			
			return ret + overShoot;
		}


		@Override
		public long getMaxPower() {
			return (long) Math.pow(10, level) >> 1;
		}

		@Override
		public long getPower() {
			return subBuffer;
		}
		
		@Override
		public void setPower(final long power) {
			this.subBuffer = power;
		}

		@Override
		public ConnectionPriority getPriority() {
			return this.priority;
		}
	}
}
