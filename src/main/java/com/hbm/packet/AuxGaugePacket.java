package com.hbm.packet;

import com.hbm.interfaces.Spaghetti;
import com.hbm.items.weapon.ItemMissile.PartSize;
import com.hbm.main.MainRegistry;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.tileentity.bomb.TileEntityCompactLauncher;
import com.hbm.tileentity.bomb.TileEntityLaunchTable;
import com.hbm.tileentity.bomb.TileEntityLaunchPad;
import com.hbm.tileentity.bomb.TileEntityRailgun;
import com.hbm.tileentity.machine.TileEntityAMSBase;
import com.hbm.tileentity.machine.TileEntityAMSEmitter;
import com.hbm.tileentity.machine.TileEntityAMSLimiter;
import com.hbm.tileentity.machine.TileEntityCoreEmitter;
import com.hbm.tileentity.machine.TileEntityCoreInjector;
import com.hbm.tileentity.machine.TileEntityCoreStabilizer;
import com.hbm.tileentity.machine.TileEntityMachineArcFurnace;
import com.hbm.tileentity.machine.TileEntityMachineBoiler;
import com.hbm.tileentity.machine.TileEntityMachineBoilerElectric;
import com.hbm.tileentity.machine.TileEntityMachineBoilerRTG;
import com.hbm.tileentity.machine.TileEntityMachineCentrifuge;
import com.hbm.tileentity.machine.TileEntityMachineCoal;
import com.hbm.tileentity.machine.TileEntityMachineDiesel;
import com.hbm.tileentity.machine.TileEntityMachineElectricFurnace;
import com.hbm.tileentity.machine.TileEntityMachineGasCent;
import com.hbm.tileentity.machine.TileEntityMachineGenerator;
import com.hbm.tileentity.machine.TileEntityMachineReactorLarge;
import com.hbm.tileentity.machine.TileEntityMachineReactorSmall;
import com.hbm.tileentity.machine.TileEntityMachineSeleniumEngine;
import com.hbm.tileentity.machine.TileEntitySlidingBlastDoor;
import com.hbm.tileentity.machine.TileEntityWatzCore;
import com.hbm.tileentity.turret.TileEntityTurretCIWS;
import com.hbm.tileentity.turret.TileEntityTurretCheapo;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Spaghetti("Changing all machiines to use TileEntityMachineBase will reduce the total chaos in this class")
public class AuxGaugePacket implements IMessage {

	int x;
	int y;
	int z;
	int value;
	int id;

	public AuxGaugePacket() {

	}

	public AuxGaugePacket(final int x, final int y, final int z, final int value, final int id) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.value = value;
		this.id = id;
	}

	public AuxGaugePacket(final BlockPos pos, final int value, final int id) {
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

	public static class Handler implements IMessageHandler<AuxGaugePacket, IMessage> {

		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(final AuxGaugePacket m, final MessageContext ctx) {

			Minecraft.getMinecraft().addScheduledTask(() -> {
				try {
					final TileEntity te = Minecraft.getMinecraft().world.getTileEntity(new BlockPos(m.x, m.y, m.z));

					if(te instanceof TileEntityAMSLimiter limiter) {
                        if(m.id == 0)
							limiter.locked = m.value == 1;
						else if(m.id == 1)
							limiter.efficiency = m.value;
					} else if(te instanceof TileEntityAMSEmitter emitter) {
                        if(m.id == 0)
							emitter.locked = m.value == 1;
						else if(m.id == 1)
							emitter.efficiency = m.value;
					} else if(te instanceof TileEntityAMSBase base) {

                        if(m.id == 0)
							base.locked = m.value == 1;
						else if(m.id == 1)
							base.color = m.value;
						else if(m.id == 2)
							base.efficiency = m.value;
						else if(m.id == 3)
							base.field = m.value;
					} else if(te instanceof TileEntityTurretCIWS cwis) {

                        cwis.rotation = m.value;
					} else if(te instanceof TileEntityTurretCheapo cwis) {

                        cwis.rotation = m.value;
					} else if(te instanceof TileEntityMachineSeleniumEngine selenium) {

                        if(m.id == 0)
							selenium.pistonCount = m.value;
						if(m.id == 1)
							selenium.powerCap = m.value;
					} else if(te instanceof TileEntityMachineDiesel selenium) {

                        selenium.powerCap = m.value;
					} else if(te instanceof TileEntityMachineReactorSmall reactor) {

                        if(m.id == 0)
							reactor.rods = m.value;
						if(m.id == 1)
							reactor.retracting = m.value == 1;
						if(m.id == 2)
							reactor.coreHeat = m.value;
						if(m.id == 3)
							reactor.hullHeat = m.value;
					} else if(te instanceof TileEntityMachineGasCent cent) {

                        if(m.id == 0)
							cent.progress = m.value;
						if(m.id == 1)
							cent.isProgressing = m.value == 1;
					} else if(te instanceof TileEntityMachineCentrifuge cent) {

                        if(m.id == 0)
							cent.progress = m.value;
						if(m.id == 1)
							cent.isProgressing = m.value == 1;
					} else if(te instanceof TileEntityMachineBoiler boiler) {

                        if(m.id == 0)
							boiler.heat = m.value;
						if(m.id == 1)
							boiler.burnTime = m.value;
					} else if(te instanceof TileEntityMachineBoilerRTG rtgBoiler) {

                        if(m.id == 0)
							rtgBoiler.heat = m.value;
						if(m.id == 1)
							rtgBoiler.rtgPower = m.value;
					} else if(te instanceof TileEntityMachineCoal coalgen) {

                        if(m.id == 0)
							coalgen.burnTime = m.value;
					} else if(te instanceof TileEntityMachineElectricFurnace furn) {

                        if(m.id == 0)
							furn.dualCookTime = m.value;
					} else if(te instanceof TileEntityMachineArcFurnace furn) {

                        if(m.id == 0)
							furn.dualCookTime = m.value;
					} else if(te instanceof TileEntityMachineBoilerElectric boiler) {

                        if(m.id == 0)
							boiler.heat = m.value;
					} else if(te instanceof TileEntityMachineReactorLarge reactor) {

                        if(m.id == 0)
							reactor.size = m.value;
					} else if(te instanceof TileEntityCompactLauncher launcher) {

                        if(m.id == 0)
							launcher.solid = m.value;
						if(m.id == 1)
							launcher.clearingTimer = m.value;
					} else if(te instanceof TileEntityLaunchPad launcher) {

                        launcher.clearingTimer = m.value;
					} else if(te instanceof TileEntityLaunchTable launcher) {

                        if(m.id == 0)
							launcher.solid = m.value;
						if(m.id == 1)
							launcher.padSize = PartSize.values()[m.value];
						if(m.id == 2)
							launcher.clearingTimer = m.value;
					} else if(te instanceof TileEntityRailgun gen) {

                        if(m.id == 0) {
							final Vec3 vec = Vec3.createVectorHelper(5.5, 0, 0);
							vec.rotateAroundZ((float) (gen.pitch * Math.PI / 180D));
							vec.rotateAroundY((float) (gen.yaw * Math.PI / 180D));

							final double fX = gen.getPos().getX() + 0.5 + vec.xCoord;
							final double fY = gen.getPos().getY() + 1 + vec.yCoord;
							final double fZ = gen.getPos().getZ() + 0.5 + vec.zCoord;

							MainRegistry.proxy.spawnSFX(gen.getWorld(), fX, fY, fZ, 0, vec.normalize());
						}

					} else if(te instanceof TileEntityCoreEmitter) {
						if(m.id == 0)
							((TileEntityCoreEmitter) te).beam = m.value;
						if(m.id == 1)
							((TileEntityCoreEmitter) te).watts = m.value;
					} else if(te instanceof TileEntityCoreInjector) {
						if(m.id == 0)
							((TileEntityCoreInjector) te).beam = m.value;
					} else if(te instanceof TileEntityCoreStabilizer) {
						if(m.id == 0)
							((TileEntityCoreStabilizer) te).beam = m.value;
					} else if(te instanceof TileEntityMachineGenerator){
						if(m.id == 0)
							((TileEntityMachineGenerator) te).heat = m.value;
					} else if(te instanceof TileEntityWatzCore core){
                        if(m.id == 0)
							core.powerList = m.value;
						else if(m.id == 1)
							core.heatList = m.value;
						else if(m.id == 2)
							core.decayMultiplier = m.value;
						else if(m.id == 3)
							core.powerMultiplier = m.value;
						else if(m.id == 4)
							core.heatMultiplier = m.value;
						else if(m.id == 5)
							core.heat = m.value;
					} else if(te instanceof TileEntitySlidingBlastDoor){
						((TileEntitySlidingBlastDoor) te).shouldUseBB = m.value == 1;
					}
				} catch(final Exception x) {
				}
			});

			return null;
		}
	}
}
