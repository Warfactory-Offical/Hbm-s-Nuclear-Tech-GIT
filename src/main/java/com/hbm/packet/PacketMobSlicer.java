package com.hbm.packet;

import java.util.ArrayList;
import java.util.List;

import com.hbm.items.weapon.ItemCrucible;
import com.hbm.items.weapon.ItemSwordCutter;
import com.hbm.lib.Library;
import com.hbm.lib.ModDamageSource;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketMobSlicer implements IMessage {

	public Vec3d pos;
	public Vec3d norm;
	public byte tex;
	
	public PacketMobSlicer() {
	}
	
	public PacketMobSlicer(final Vec3d position, final Vec3d normal, final byte tex) {
		pos = position;
		norm = normal;
		this.tex = tex;
	}
	
	@Override
	public void fromBytes(final ByteBuf buf) {
		pos = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
		norm = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
		this.tex = buf.readByte();
	}

	@Override
	public void toBytes(final ByteBuf buf) {
		buf.writeDouble(pos.x);
		buf.writeDouble(pos.y);
		buf.writeDouble(pos.z);
		buf.writeDouble(norm.x);
		buf.writeDouble(norm.y);
		buf.writeDouble(norm.z);
		buf.writeByte(tex);
	}
	
	public static class Handler implements IMessageHandler<PacketMobSlicer, IMessage> {

		@Override
		public IMessage onMessage(final PacketMobSlicer m, final MessageContext ctx) {
			final EntityPlayerMP p = ctx.getServerHandler().player;
			final ItemStack heldStack = p.getHeldItemMainhand();
			if(!(heldStack.getItem() instanceof ItemSwordCutter))
				return null;
			p.getServer().addScheduledTask(()->{
				final List<EntityLivingBase> attack = new ArrayList<>();
				final Vec3d eye = p.getPositionEyes(1);
				final Vec3d v1 = p.getLookVec();
				final Vec3d v2 = m.pos.subtract(eye);
				for(float i = 0; i <= 1; i += 0.1F){
					final Vec3d dir = new Vec3d(v1.x+(v2.x-v1.x)*i, v1.y+(v2.y-v1.y)*i, v1.z+(v2.z-v1.z)*i).normalize();
					final RayTraceResult r = Library.rayTraceIncludeEntities(p.world, eye, eye.add(dir.scale(3)), p);
					if(r != null && r.typeOfHit == Type.ENTITY && r.entityHit instanceof EntityLivingBase && !attack.contains(r.entityHit)){
						attack.add((EntityLivingBase) r.entityHit);
					}
				}
				if(heldStack.getItem() instanceof ItemCrucible){
					if(ItemCrucible.getCharges(heldStack) == 0)
						return;
					if(!attack.isEmpty())
						ItemCrucible.discharge(heldStack);
				}
				for(final EntityLivingBase victim : attack){
					final Vec3d pos = m.pos.subtract(victim.posX, victim.posY, victim.posZ);
					final float[] data = new float[]{(float)m.norm.x, (float)m.norm.y, (float)m.norm.z, -(float)m.norm.dotProduct(pos), Float.intBitsToFloat(m.tex)};
					final DamageSource source = m.tex == 1 ? ModDamageSource.crucible : ModDamageSource.slicer;
					victim.getCombatTracker().trackDamage(source, victim.getHealth(), victim.getHealth());
					victim.setDead();
					victim.setHealth(0);
					victim.onDeath(source);
					//For ender dragon so it spawns a portal and all that
					victim.onKillCommand();
					
					PacketDispatcher.wrapper.sendToAllTracking(new PacketSpecialDeath(victim, 3, data), victim);
					if(victim instanceof EntityPlayerMP){
						PacketDispatcher.wrapper.sendTo(new PacketSpecialDeath(victim, 3, data), (EntityPlayerMP) victim);
					}
				}
			});
			return null;
		}
		
	}
}
