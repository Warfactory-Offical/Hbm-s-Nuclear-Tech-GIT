package com.hbm.tileentity.machine.rbmk;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.machine.rbmk.RBMKBase;
import com.hbm.capability.HbmCapability;
import com.hbm.capability.HbmCapability.IHBMData;
import com.hbm.handler.HbmKeybinds.EnumKeybind;
import com.hbm.items.machine.ItemRBMKRod;
import com.hbm.packet.NBTPacket;
import com.hbm.lib.ForgeDirection;
import com.hbm.inventory.control_panel.IControllable;
import com.hbm.inventory.control_panel.ControlEventSystem;
import com.hbm.inventory.control_panel.ControlEvent;
import com.hbm.inventory.control_panel.DataValue;
import com.hbm.inventory.control_panel.DataValueFloat;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.INBTPacketReceiver;
import com.hbm.tileentity.machine.rbmk.IRBMKLoadable;
import com.hbm.tileentity.TileEntityMachineBase;


import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.common.Optional;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;

@Optional.InterfaceList({@Optional.Interface(iface = "li.cil.oc.api.network.SimpleComponent", modid = "OpenComputers")})
public class TileEntityRBMKCraneConsole extends TileEntityMachineBase implements ITickable, INBTPacketReceiver, SimpleComponent, IControllable {
	
	public int centerX;
	public int centerY;
	public int centerZ;
	
	public int spanF;
	public int spanB;
	public int spanL;
	public int spanR;
	
	public int height;
	
	public boolean setUpCrane = false;

	public double lastTiltFront = 0;
	public double lastTiltLeft = 0;
	public double tiltFront = 0;
	public double tiltLeft = 0;

	public double lastPosFront = 0;
	public double lastPosLeft = 0;
	public double posFront = 0;
	public double posLeft = 0;
	
	private boolean goesDown = false;
	public double lastProgress = 1D;
	public double progress = 1D;
	
	private boolean hasLoaded = false;
	public double loadedHeat;
	public double loadedEnrichment;

	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;

	private boolean craneLeft = false;
	private boolean craneRight = false;
	private boolean craneUp = false;
	private boolean craneDown = false;

	private static final double speed = 0.05D; // 1/coolDown DO NOT CHANGE
	private static final short coolDown = 20; //in Ticks DO NOT CHANGE
	private short ticksSince = 0; //ticks since button press

	public TileEntityRBMKCraneConsole() {
		super(1);
	}

	private boolean isCooledDown(){
		if(ticksSince < coolDown){
			ticksSince++;
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void update() {
		
		lastTiltFront = tiltFront;
		lastTiltLeft = tiltLeft;
		if(goesDown) {
			
			if(progress > 0) {
				progress -= 0.04D;
			} else {
				progress = 0;
				goesDown = false;

				if(!world.isRemote) {
					ControlEventSystem.get(world).broadcastToSubscribed(this, ControlEvent.newEvent("rbmk_crane_load"));

					if(this.canTargetInteract()) {
						if(inventory.getStackInSlot(0).isEmpty()) {
							final IRBMKLoadable column = getColumnAtPos();
							inventory.setStackInSlot(0, column.provideNext());
							column.unload();
						} else {
							getColumnAtPos().load(inventory.getStackInSlot(0));
							inventory.setStackInSlot(0, ItemStack.EMPTY);
						}
						this.markDirty();
					}
				}
					
			}
		} else if(progress != 1) {
			
			progress += 0.04D;
			
			if(progress > 1D) {
				progress = 1D;
			}
		}

		if(isCooledDown()){
			if(craneUp != up || craneDown != down || craneLeft != left || craneRight != right) //activating cooldown bc of change in direction
				ticksSince = 1;
			else if(craneUp || craneDown || craneLeft  || craneRight) //activating cooldown bc to keep going if moving
				ticksSince = 1;
			craneUp = up;
			craneDown = down;
			craneLeft = left;
			craneRight = right;
		}

		if(!world.isRemote){
			if(craneUp)
				posFront += speed;

			if(craneDown)
				posFront -= speed;

			if(craneLeft)
				posLeft += speed;

			if(craneRight)
				posLeft -= speed;
		}

		//Player input for next update
		final double xCoord = pos.getX();
		final double yCoord = pos.getY();
		final double zCoord = pos.getZ();

		final ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata() - BlockDummyable.offset);
		final ForgeDirection side = dir.getRotation(ForgeDirection.UP);
		final double minX = xCoord + 0.5 - side.offsetX * 1.5;
		final double maxX = xCoord + 0.5 + side.offsetX * 1.5 + dir.offsetX * 2;
		final double minZ = zCoord + 0.5 - side.offsetZ * 1.5;
		final double maxZ = zCoord + 0.5 + side.offsetZ * 1.5 + dir.offsetZ * 2;
		
		final List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(
				Math.min(minX, maxX),
				yCoord,
				Math.min(minZ, maxZ),
				Math.max(minX, maxX),
				yCoord + 2,
				Math.max(minZ, maxZ)));
		tiltFront = 0;
		tiltLeft = 0;
		
		if(players.size() > 0 && !isCraneLoading()) {
			final EntityPlayer player = players.get(0);
			final IHBMData props = HbmCapability.getData(player);

			processInput(props.getKeyPressed(EnumKeybind.CRANE_UP),
				props.getKeyPressed(EnumKeybind.CRANE_DOWN),
				props.getKeyPressed(EnumKeybind.CRANE_LEFT),
				props.getKeyPressed(EnumKeybind.CRANE_RIGHT)
			);

			if(props.getKeyPressed(EnumKeybind.CRANE_LOAD)) {
				goesDown = true;
			}
		}else{
			up = false;
			down = false;
			left = false;
			right = false;
		}
		
		posFront = MathHelper.clamp(posFront, -spanB, spanF);
		posLeft = MathHelper.clamp(posLeft, -spanR, spanL);
		
		if(!world.isRemote) {
			
			if(!inventory.getStackInSlot(0).isEmpty() && inventory.getStackInSlot(0).getItem() instanceof ItemRBMKRod) {
				this.loadedHeat = ItemRBMKRod.getHullHeat(inventory.getStackInSlot(0));
				this.loadedEnrichment = ItemRBMKRod.getEnrichment(inventory.getStackInSlot(0));
			} else {
				this.loadedHeat = 20;
				this.loadedEnrichment = 20;
			}
			
			final NBTTagCompound nbt = new NBTTagCompound();
			nbt.setBoolean("crane", setUpCrane);
			
			if(setUpCrane) { //no need to send any of this if there's NO FUCKING CRANE THERE
				nbt.setInteger("centerX", centerX);
				nbt.setInteger("centerY", centerY);
				nbt.setInteger("centerZ", centerZ);
				nbt.setInteger("spanF", spanF);
				nbt.setInteger("spanB", spanB);
				nbt.setInteger("spanL", spanL);
				nbt.setInteger("spanR", spanR);
				nbt.setInteger("height", height);
				nbt.setDouble("posFront", posFront);
				nbt.setDouble("posLeft", posLeft);
				nbt.setBoolean("loaded", this.hasItemLoaded());
				nbt.setDouble("loadedHeat", loadedHeat);
				nbt.setDouble("loadedEnrichment", loadedEnrichment);
				nbt.setBoolean("goesDown", goesDown);
			}
			PacketDispatcher.wrapper.sendToAllAround(new NBTPacket(nbt, pos), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 250));
		}
	}

	public void processInput(final boolean inputUP, final boolean inputDOWN, final boolean inputLEFT, final boolean inputRIGHT){
		up = inputUP;
		down = inputDOWN;
		left = inputLEFT;
		right = inputRIGHT;
		if(up == down){
			up = false;
			down = false;
		}else{
			if(up){
				tiltFront = 30;
			}else{
				tiltFront = -30;
			}
		}

		if(left == right){
			left = false;
			right = false;
		}else{
			if(left){
				tiltLeft = 30;
			}else{
				tiltLeft = -30;
			}
		}

		if (!world.isRemote && (up || down || left || right)) {
			ControlEventSystem.get(world).broadcastToSubscribed(this, ControlEvent.newEvent("rbmk_crane_move")
					.setVar("up", new DataValueFloat(up)).setVar("down", new DataValueFloat(down))
					.setVar("left", new DataValueFloat(left)).setVar("right", new DataValueFloat(right)));
		}
	}
	
	public boolean hasItemLoaded() {
		
		if(!world.isRemote)
			return !inventory.getStackInSlot(0).isEmpty();
		else
			return this.hasLoaded;
	}
	
	public boolean isCraneLoading() {
		return this.progress != 1D;
	}
	
	public boolean isAboveValidTarget() {
		return getColumnAtPos() != null;
	}
	
	public boolean canTargetInteract() {
		
		final IRBMKLoadable column = getColumnAtPos();
		
		if(column == null)
			return false;
		
		if(this.hasItemLoaded()) {
			return column.canLoad(inventory.getStackInSlot(0));
		} else {
			return column.canUnload();
		}
	}
	
	public IRBMKLoadable getColumnAtPos() {
		
		final ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata() - BlockDummyable.offset);
		final ForgeDirection left = dir.getRotation(ForgeDirection.DOWN);

		final int x = (int)Math.floor(this.centerX - dir.offsetX * this.posFront - left.offsetX * this.posLeft + 0.5D);
		final int y = this.centerY - 1;
		final int z = (int)Math.floor(this.centerZ - dir.offsetZ * this.posFront - left.offsetZ * this.posLeft + 0.5D);
				
		final Block b = world.getBlockState(new BlockPos(x, y, z)).getBlock();
		
		if(b instanceof RBMKBase) {
			
			final int[] pos = ((BlockDummyable)b).findCore(world, x, y, z);
			if(pos != null) {
				final TileEntityRBMKBase column = (TileEntityRBMKBase)world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
				if(column instanceof IRBMKLoadable) {
					return (IRBMKLoadable) column;
				}
			}
		}
		
		return null;
	}

	@Override
	public void networkUnpack(final NBTTagCompound nbt) {
		
		lastPosFront = posFront;
		lastPosLeft = posLeft;
		lastProgress = progress;
		
		this.setUpCrane = nbt.getBoolean("crane");
		this.centerX = nbt.getInteger("centerX");
		this.centerY = nbt.getInteger("centerY");
		this.centerZ = nbt.getInteger("centerZ");
		this.spanF = nbt.getInteger("spanF");
		this.spanB = nbt.getInteger("spanB");
		this.spanL = nbt.getInteger("spanL");
		this.spanR = nbt.getInteger("spanR");
		this.height = nbt.getInteger("height");
		this.posFront = nbt.getDouble("posFront");
		this.posLeft = nbt.getDouble("posLeft");
		this.hasLoaded = nbt.getBoolean("loaded");
		this.posLeft = nbt.getDouble("posLeft");
		this.loadedHeat = nbt.getDouble("loadedHeat");
		this.loadedEnrichment = nbt.getDouble("loadedEnrichment");
		this.goesDown = nbt.getBoolean("goesDown");
	}
	
	public void setTarget(final int x, final int y, final int z) {
		this.centerX = x;
		this.centerY = y + RBMKDials.getColumnHeight(world) + 1;
		this.centerZ = z;

		this.spanF = 7;
		this.spanB = 7;
		this.spanL = 7;
		this.spanR = 7;
		
		this.height = 7;
		this.setUpCrane = true;
		
		this.markDirty();
	}
	
	@Override
	public void readFromNBT(final NBTTagCompound nbt) {

		this.setUpCrane = nbt.getBoolean("crane");
		this.centerX = nbt.getInteger("centerX");
		this.centerY = nbt.getInteger("centerY");
		this.centerZ = nbt.getInteger("centerZ");
		this.spanF = nbt.getInteger("spanF");
		this.spanB = nbt.getInteger("spanB");
		this.spanL = nbt.getInteger("spanL");
		this.spanR = nbt.getInteger("spanR");
		this.height = nbt.getInteger("height");
		this.posFront = nbt.getDouble("posFront");
		this.posLeft = nbt.getDouble("posLeft");
		
		if(nbt.hasKey("inventory"))
			inventory.deserializeNBT(nbt.getCompoundTag("inventory"));
		super.readFromNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeToNBT(final NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("crane", setUpCrane);
		nbt.setInteger("centerX", centerX);
		nbt.setInteger("centerY", centerY);
		nbt.setInteger("centerZ", centerZ);
		nbt.setInteger("spanF", spanF);
		nbt.setInteger("spanB", spanB);
		nbt.setInteger("spanL", spanL);
		nbt.setInteger("spanR", spanR);
		nbt.setInteger("height", height);
		nbt.setDouble("posFront", posFront);
		nbt.setDouble("posLeft", posLeft);
		nbt.setTag("inventory", inventory.serializeNBT());
		
		return nbt;
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
	
	// opencomputers interface

	@Override
	public String getName() {
		return "rbmk_crane";
	}

	@Override
	public String getComponentName() {
		return "rbmk_crane";
	}

	@Callback(doc = "moveUp(); move the crane up 1 block")
	public Object[] moveUp(final Context context, final Arguments args) {
		if(setUpCrane) {
			if(!isCraneLoading()){
				processInput(true, false, false, false);
				return new Object[] {};
			} else {
				return new Object[] {"Crane is loading and cant be moved"};
			}	
		}
		return new Object[] {"No crane found"};
	}

	@Callback(doc = "moveDown(); move the crane down 1 block")
	public Object[] moveDown(final Context context, final Arguments args) {
		if(setUpCrane) {
			if(!isCraneLoading()){
				processInput(false, true, false, false);
				return new Object[] {};
			} else {
				return new Object[] {"Crane is loading and cant be moved"};
			}	
		}
		return new Object[] {"No crane found"};
	}

	@Callback(doc = "moveLeft(); move the crane left 1 block")
	public Object[] moveLeft(final Context context, final Arguments args) {
		if(setUpCrane) {
			if(!isCraneLoading()){
				processInput(false, false, true, false);
				return new Object[] {};
			} else {
				return new Object[] {"Crane is loading and cant be moved"};
			}	
		}
		return new Object[] {"No crane found"};
	}

	@Callback(doc = "moveRight(); move the crane right 1 block")
	public Object[] moveRight(final Context context, final Arguments args) {
		if(setUpCrane) {
			if(!isCraneLoading()){
				processInput(false, false, false, true);
				return new Object[] {};
			} else {
				return new Object[] {"Crane is loading and cant be moved"};
			}	
		}
		return new Object[] {"No crane found"};
	}

	@Callback(doc = "moveUpLeft(); move the crane up and left 1 block")
	public Object[] moveUpLeft(final Context context, final Arguments args) {
		if(setUpCrane) {
			if(!isCraneLoading()){
				processInput(true, false, true, false);
				return new Object[] {};
			} else {
				return new Object[] {"Crane is loading and cant be moved"};
			}	
		}
		return new Object[] {"No crane found"};
	}

	@Callback(doc = "moveUpRight(); move the crane up and right 1 block")
	public Object[] moveUpRight(final Context context, final Arguments args) {
		if(setUpCrane) {
			if(!isCraneLoading()){
				processInput(true, false, false, true);
				return new Object[] {};
			} else {
				return new Object[] {"Crane is loading and cant be moved"};
			}	
		}
		return new Object[] {"No crane found"};
	}

	@Callback(doc = "moveDownLeft(); move the crane down and left 1 block")
	public Object[] moveDownLeft(final Context context, final Arguments args) {
		if(setUpCrane) {
			if(!isCraneLoading()){
				processInput(false, true, true, false);
				return new Object[] {};
			} else {
				return new Object[] {"Crane is loading and cant be moved"};
			}	
		}
		return new Object[] {"No crane found"};
	}

	@Callback(doc = "moveDownRight(); move the crane down and right 1 block")
	public Object[] moveDownRight(final Context context, final Arguments args) {
		if(setUpCrane) {
			if(!isCraneLoading()){
				processInput(false, true, false, true);
				return new Object[] {};
			} else {
				return new Object[] {"Crane is loading and cant be moved"};
			}	
		}
		return new Object[] {"No crane found"};
	}

	@Callback(doc = "loadUnload(); starts loading/unloading of items")
	public Object[] loadUnload(final Context context, final Arguments args) {
		if (setUpCrane) {
			if(!isCraneLoading()){
				goesDown = true; // Robert, it goes down.
				return new Object[] {"Loading initiated"};
			} else {
				return new Object[] {"Crane is already loading"};
			}
		}
		return new Object[] {"No crane found"};
	}

	@Callback(doc = "getPos(); get the (x, y) crane displacements. 0,0 is at center rbmk column and y is to the front and x is to the right")
	public Object[] getPos(final Context context, final Arguments args) {
		if (setUpCrane) 
			return new Object[] {-posLeft, posFront};
		return new Object[] {"No crane found"};
	}


	// control panel
	@Override
	public Map<String, DataValue> getQueryData() {
		final Map<String, DataValue> data = new HashMap<>();
		if (setUpCrane) {
			data.put("posX", new DataValueFloat((float) -posLeft));
			data.put("posY", new DataValueFloat((float) posFront));
		}
		return data;
	}

	@Override
	public void receiveEvent(final BlockPos from, final ControlEvent e) {
		switch (e.name) {
			case "rbmk_crane_move": {
				final boolean up = e.vars.get("up").getBoolean();
				final boolean down = e.vars.get("down").getBoolean();
				final boolean left = e.vars.get("left").getBoolean();
				final boolean right = e.vars.get("right").getBoolean();

				if (setUpCrane && !isCraneLoading()) {
					processInput(up, down, left, right);
				}
				break;
			}
			case "rbmk_crane_load": {
				if (setUpCrane && !isCraneLoading()) {
					goesDown = true;
				}
				break;
			}
		}
	}

	@Override
	public List<String> getInEvents() {
		return Arrays.asList("rbmk_crane_move", "rbmk_crane_load");
	}

	@Override
	public List<String> getOutEvents() {
		return Arrays.asList("rbmk_crane_move", "rbmk_crane_load");
	}

	@Override
	public void validate() {
		super.validate();
		ControlEventSystem.get(world).addControllable(this);
	}

	@Override
	public void invalidate() {
		super.invalidate();
		ControlEventSystem.get(world).removeControllable(this);
	}

	@Override
	public BlockPos getControlPos() {
		return getPos();
	}

	@Override
	public World getControlWorld() {
		return getWorld();
	}
}
