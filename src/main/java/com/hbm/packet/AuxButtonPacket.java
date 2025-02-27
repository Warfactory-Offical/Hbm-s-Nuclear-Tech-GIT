package com.hbm.packet;

import com.hbm.entity.mob.EntityDuck;
import com.hbm.items.weapon.ItemMissile.PartSize;
import com.hbm.items.weapon.ItemCrucible;
import com.hbm.config.GeneralConfig;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.tileentity.TileEntityMachineBase;
import com.hbm.tileentity.bomb.TileEntityLaunchTable;
import com.hbm.tileentity.bomb.TileEntityRailgun;
import com.hbm.tileentity.machine.TileEntityBarrel;
import com.hbm.tileentity.machine.TileEntityCoreEmitter;
import com.hbm.tileentity.machine.TileEntityCoreStabilizer;
import com.hbm.tileentity.machine.TileEntityForceField;
import com.hbm.tileentity.machine.TileEntityMachineBattery;
import com.hbm.tileentity.machine.TileEntityMachineMiningLaser;
import com.hbm.tileentity.machine.TileEntityMachineMissileAssembly;
import com.hbm.tileentity.machine.TileEntityMachineReactorLarge;
import com.hbm.tileentity.machine.TileEntityMachineReactorSmall;
import com.hbm.tileentity.machine.TileEntityMachineRadar;
import com.hbm.tileentity.machine.TileEntityReactorControl;
import com.hbm.tileentity.machine.TileEntitySoyuzLauncher;

import io.netty.buffer.ByteBuf;
import api.hbm.energy.IEnergyConnector.ConnectionPriority;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class AuxButtonPacket implements IMessage {

	int x;
	int y;
	int z;
	int value;
	int id;

	public AuxButtonPacket()
	{
		
	}

	public AuxButtonPacket(final int x, final int y, final int z, final int value, final int id)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.value = value;
		this.id = id;
	}
	
	public AuxButtonPacket(final BlockPos pos, final int value, final int id){
		this(pos.getX(), pos.getY(), pos.getZ(), value, id);
	}

	@Override
	public void fromBytes(final ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		value = buf.readInt();
		id = buf.readInt();
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(value);
		buf.writeInt(id);
	}

	public static class Handler implements IMessageHandler<AuxButtonPacket, IMessage> {

		@Override
		public IMessage onMessage(final AuxButtonPacket m, final MessageContext ctx) {
			ctx.getServerHandler().player.getServer().addScheduledTask(() -> {
				final EntityPlayer p = ctx.getServerHandler().player;
				final BlockPos pos = new BlockPos(m.x, m.y, m.z);
				
				//why make new packets when you can just abuse and uglify the existing ones?
				if(m.value == 999) {
					if(GeneralConfig.duckButton){
						final NBTTagCompound perDat = p.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
						if(!perDat.getBoolean("hasDucked")) {
							final EntityDuck ducc = new EntityDuck(p.world);
							ducc.setPosition(p.posX, p.posY + p.eyeHeight, p.posZ);

							final Vec3d vec = p.getLookVec();
							ducc.motionX = vec.x;
							ducc.motionY = vec.y;
							ducc.motionZ = vec.z;

							p.world.spawnEntity(ducc);
							p.world.playSound(null, p.posX, p.posY, p.posZ, HBMSoundHandler.ducc, SoundCategory.PLAYERS, 1.0F, 1.0F);

							perDat.setBoolean("hasDucked", true);

							p.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, perDat);
						}
					}
					return;
				}
				if(m.id == 1000){
					final boolean clicked = m.value > 0;
					if(ctx.getServerHandler().player.getHeldItemMainhand().getItem() instanceof ItemCrucible){
						ItemCrucible.doSpecialClick = clicked;
					}
					return;
				}
				/*if(m.value == 1000){
					NBTTagCompound perDat = p.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
					int lightning = perDat.getInteger("lightningCharge");
					if(lightning == 0){
						perDat.setInteger("lightningCharge", 1);
					}
				}*/
				
				if(!p.world.isBlockLoaded(pos))
					return;
				//try {
					final TileEntity te = p.world.getTileEntity(pos);
					
					if (te instanceof TileEntityMachineReactorSmall reactor) {

                        if(m.id == 0)
							reactor.retracting = m.value == 1;
						if(m.id == 1) {
							reactor.compress(m.value);
						}
						reactor.markDirty();
					}
					/*if (te instanceof TileEntityRadioRec) {
						TileEntityRadioRec radio = (TileEntityRadioRec)te;
						
						if(m.id == 0) {
							radio.isOn = (m.value == 1);
						}
						
						if(m.id == 1) {
							radio.freq = ((double)m.value) / 10D;
						}
					}
					
					*/if (te instanceof TileEntityForceField field) {

                    field.isOn = !field.isOn;
					}
					
					if (te instanceof TileEntityReactorControl control) {

                        if(m.id == 1)
							control.auto = m.value == 1;
						
						if(control.link != null) {
							final TileEntity reac = p.world.getTileEntity(control.link);
							
							if (reac instanceof TileEntityMachineReactorSmall reactor) {

                                if(m.id == 0)
									reactor.retracting = m.value == 0;
								
								if(m.id == 2) {
									reactor.compress(m.value);
								}
							}
							
							if (reac instanceof TileEntityMachineReactorLarge reactor) {

                                if(m.id == 0) {
									reactor.rods = m.value;
								}
								
								if(m.id == 2) {
									reactor.compress(m.value);
								}
							}
						}
						
					}
					final TileEntity reac = p.world.getTileEntity(new BlockPos(m.x, m.y, m.z));
					if (reac instanceof TileEntityMachineReactorLarge reactor) {

                        if(m.id == 0) {
							reactor.rods = m.value;
						}
						
						if(m.id == 1) {
							reactor.compress(m.value);
						}
					}
					
					if (te instanceof TileEntityMachineMissileAssembly assembly) {

                        assembly.construct();
					}
					
					if (te instanceof TileEntityLaunchTable launcher) {

                        launcher.padSize = PartSize.values()[m.value];
					}
					
					if (te instanceof TileEntityRailgun gun) {

                        if(m.id == 0) {
							if(gun.setAngles(false)) {
								p.world.playSound(null, m.x, m.y, m.z, HBMSoundHandler.buttonYes, SoundCategory.BLOCKS, 1.0F, 1.0F);
								p.world.playSound(null, m.x, m.y, m.z, HBMSoundHandler.railgunOrientation, SoundCategory.BLOCKS, 1.0F, 1.0F);
								PacketDispatcher.wrapper.sendToAll(new RailgunCallbackPacket(m.x, m.y, m.z, gun.pitch, gun.yaw));
							} else {
								p.world.playSound(null, m.x, m.y, m.z, HBMSoundHandler.buttonNo, SoundCategory.BLOCKS, 1.0F, 1.0F);
							}
						}
						
						if(m.id == 1) {
							if(gun.canFire()) {
								gun.fireDelay = TileEntityRailgun.cooldownDurationTicks;
								PacketDispatcher.wrapper.sendToAll(new RailgunFirePacket(m.x, m.y, m.z));
								p.world.playSound(null, m.x, m.y, m.z, HBMSoundHandler.buttonYes, SoundCategory.BLOCKS, 1.0F, 1.0F);
								p.world.playSound(null, m.x, m.y, m.z, HBMSoundHandler.railgunCharge, SoundCategory.BLOCKS, 10.0F, 1.0F);
							} else {
								p.world.playSound(null, m.x, m.y, m.z, HBMSoundHandler.buttonNo, SoundCategory.BLOCKS, 1.0F, 1.0F);
							}
						}
					}
					if (te instanceof TileEntityBarrel barrel) {

                        barrel.mode = (short) ((barrel.mode + 1) % TileEntityBarrel.modes);
						barrel.markDirty();
					}
					if (te instanceof TileEntityCoreEmitter core) {

                        if(m.id == 0) {
							core.watts = m.value;
						}
						if(m.id == 1) {
							core.isOn = !core.isOn;
						}
					}
					
					if (te instanceof TileEntityCoreStabilizer core) {

                        if(m.id == 0) {
							core.watts = m.value;
						}
					}
					
					if (te instanceof TileEntitySoyuzLauncher launcher) {

                        if(m.id == 0)
							launcher.mode = (byte) m.value;
						if(m.id == 1)
							launcher.startCountdown();
					}
					if (te instanceof TileEntityMachineBattery bat) {

                        if(m.id == 0) {
							bat.redLow = (short) ((bat.redLow + 1) % 4);
							bat.markDirty();
						}

						if(m.id == 1) {
							bat.redHigh = (short) ((bat.redHigh + 1) % 4);
							bat.markDirty();
						}

						if(m.id == 2) {
							switch(bat.priority) {
								case LOW: bat.priority = ConnectionPriority.NORMAL; break;
								case NORMAL: bat.priority = ConnectionPriority.HIGH; break;
								case HIGH: bat.priority = ConnectionPriority.LOW; break;
							}
							bat.markDirty();
						}
					}
					if (te instanceof TileEntityMachineMiningLaser laser) {

                        laser.isOn = !laser.isOn;
					}

					if(te instanceof TileEntityMachineRadar radar) {
                        radar.handleButtonPacket(m.value, m.id);
					}
					/// yes ///
					if(te instanceof TileEntityMachineBase base) {
                        base.handleButtonPacket(m.value, m.id);
					}
					
				//} catch (Exception x) { }
			});
			
			
			return null;
		}
	}

}
