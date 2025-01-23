package com.hbm.tileentity.network.energy;

import java.util.ArrayList;
import java.util.List;

import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.packet.PacketDispatcher;
import com.hbm.packet.TEPylonSenderPacket;

import api.hbm.energy.IEnergyConductor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.WorldServer;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TileEntityPylonBase extends TileEntityCableBaseNT {
	
	public List<BlockPos> connected = new ArrayList<BlockPos>();
	
	public static boolean canConnect(final TileEntityPylonBase first, final TileEntityPylonBase second) {
		
		if(first.getConnectionType() != second.getConnectionType())
			return false;
		
		if(first == second)
			return false;
		
		final double len = Math.min(first.getMaxWireLength(), second.getMaxWireLength());
		
		final BlockPos firstPos = first.getConnectionPoint();
		final BlockPos secondPos = second.getConnectionPoint();
		
		final Vec3 delta = Vec3.createVectorHelper(
				(secondPos.getX()) - (firstPos.getX()),
				(secondPos.getY()) - (firstPos.getY()),
				(secondPos.getZ()) - (firstPos.getZ())
				);
		
		return len >= delta.length();
	}
	
	public void addConnection(final BlockPos targetPos) {
		if(connected.contains(targetPos))
			return;
		connected.add(targetPos);
		
		if(this.getPowerNet() != null) {
			this.getPowerNet().reevaluate();
			this.network = null;
		}
		
		if (!world.isRemote)
			PacketDispatcher.wrapper.sendToAllAround(new TEPylonSenderPacket(targetPos.getX(), targetPos.getY(), targetPos.getZ(), pos.getX(), pos.getY(), pos.getZ(), true), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 200));
		this.markDirty();
	}

	public void removeConnection(final BlockPos pos) {
		connected.remove(pos);
	}

	public void disconnect(final BlockPos targetPos) {
		final TileEntity te = world.getTileEntity(targetPos);
			
		if(te == this)
			return;
		
		if(te instanceof TileEntityPylonBase pylon) {

            if(pylon.connected.contains(this.pos)){
				pylon.removeConnection(this.pos);
				if (!world.isRemote)
					PacketDispatcher.wrapper.sendToAllAround(new TEPylonSenderPacket(targetPos.getX(), targetPos.getY(), targetPos.getZ(), pos.getX(), pos.getY(), pos.getZ(), false), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 200));
				pylon.markDirty();
			}
		}
	}
	
	public void disconnectAll() {
		
		for(final BlockPos targetPos : connected) {
			
			disconnect(targetPos);
		}
	}
	
	@Override
	protected void connect() {
		
		for(final BlockPos targetPos : getConnectionPoints()) {
			
			final TileEntity te = world.getTileEntity(targetPos);
			
			if(te instanceof IEnergyConductor conductor) {

                if(this.getPowerNet() == null && conductor.getPowerNet() != null) {
					conductor.getPowerNet().joinLink(this);
				}
				
				if(this.getPowerNet() != null && conductor.getPowerNet() != null && this.getPowerNet() != conductor.getPowerNet()) {
					conductor.getPowerNet().joinNetworks(this.getPowerNet());
				}
			}
		}
	}
	
	@Override
	public List<BlockPos> getConnectionPoints() {
		return new ArrayList(connected);
	}
	
	public abstract ConnectionType getConnectionType();
	public abstract Vec3[] getMountPos();
	public abstract int getMaxWireLength();
	
	public BlockPos getConnectionPoint() {
		final Vec3[] mounts = this.getMountPos();
		
		if(mounts == null || mounts.length == 0)
			return pos.add(0.5, 0.5, 0.5);
		
		return mounts[0].toBlockPos().add(pos);
	}
	
	@Override
	public NBTTagCompound writeToNBT(final NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		final int[] conX = new int[connected.size()];
		final int[] conY = new int[connected.size()];
		final int[] conZ = new int[connected.size()];
		
		for(int i = 0; i < connected.size(); i++) {
			conX[i] = connected.get(i).getX();
			conY[i] = connected.get(i).getY();
			conZ[i] = connected.get(i).getZ();
		}
		
		nbt.setIntArray("conX", conX);
		nbt.setIntArray("conY", conY);
		nbt.setIntArray("conZ", conZ);
		return nbt;
	}
	
	@Override
	public void readFromNBT(final NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		this.connected.clear();
		
		final int[] conX = nbt.getIntArray("conX");
		final int[] conY = nbt.getIntArray("conY");
		final int[] conZ = nbt.getIntArray("conZ");

		final BlockPos[] con = new BlockPos[conX.length];
		
		for(int i = 0; i < conX.length; i++) {
			connected.add(new BlockPos(conX[i], conY[i], conZ[i]));
		}
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket(){
		final NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new SPacketUpdateTileEntity(this.getPos(), 0, nbt);
	}

	@Override
	public NBTTagCompound getUpdateTag(){
		final NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return nbt;
	}
	
	@Override
	public void onDataPacket(final NetworkManager net, final SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}

	public static enum ConnectionType {
		SINGLE,
		QUAD
		//more to follow
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return TileEntity.INFINITE_EXTENT_AABB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 65536.0D;
	}
}
