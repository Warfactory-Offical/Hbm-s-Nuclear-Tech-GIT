package com.hbm.inventory.control_panel;

import java.util.*;
import java.util.Map.Entry;

import com.hbm.main.MainRegistry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import com.hbm.packet.ControlPanelUpdatePacket.VarUpdate;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ControlPanel {

	public IControllable parent;
	public List<Control> controls = new ArrayList<>();
	//Variables that were changed in the last tick
	public List<VarUpdate> changedVars = new ArrayList<>();
	//Global variables, accessible by every control as well as the owner tile entity for state like redstone
	public Map<String, DataValue> globalVars = new LinkedHashMap<>();
	public Map<String, DataValue> globalVarsPrev = new LinkedHashMap<>();
	//Client only transform for getting from panel local space to block space. Used for both rendering and figuring out which control a player clicks
	@SideOnly(Side.CLIENT)
	public Matrix4f transform;
	@SideOnly(Side.CLIENT)
	public Matrix4f inv_transform;

	public float height;
	public float angle; // RADIANS
	public float a_off;
	public float b_off;
	public float c_off;
	public float d_off;

	public ControlPanel(final IControllable parent, final float height, final float angle, final float a_off, final float b_off, final float c_off, final float d_off) {
		this.parent = parent;
		this.height = height;
		this.angle = angle;
		this.a_off = a_off;
		this.b_off = b_off;
		this.c_off = c_off;
		this.d_off = d_off;

	}

	@SideOnly(Side.CLIENT)
	public ControlPanel setTransform(final Matrix4f transform){
		this.transform = transform;
		inv_transform = new Matrix4f();
		Matrix4f.invert(transform, inv_transform);
		return this;
	}

	public void render(){
		for(final Control c : controls) {
			final float width = c.getSize()[0];
			final float length = c.getSize()[1];
			GlStateManager.pushMatrix();
				GlStateManager.translate(((width>1?Math.abs(1-width)/2:(width-1)/2)), 0, ((length>1)? Math.abs(1-length)/2 : (length-1)/2));
				c.render();
			GlStateManager.popMatrix();
		}
	}

	public void update(){
		if(!parent.getControlWorld().isRemote){
			changedVars.clear();
			crossCheckVars(-1, globalVars, globalVarsPrev);
			for(int i = 0; i < controls.size(); i ++){
				final Control c = controls.get(i);
				crossCheckVars(i, c.vars, c.varsPrev);
			}
		}
	}
	
	public void crossCheckVars(final int idx, final Map<String, DataValue> vars, final Map<String, DataValue> varsPrev){
		for(final String s : vars.keySet()){
			if(!varsPrev.containsKey(s)){
				changedVars.add(new VarUpdate(idx, s, vars.get(s)));
			}
		}
		for(final String s : varsPrev.keySet()) {
			if(!vars.containsKey(s)){
				changedVars.add(new VarUpdate(idx, s, null));
			} else if(!varsPrev.get(s).equals(vars.get(s))){
				changedVars.add(new VarUpdate(idx, s, vars.get(s)));
			}
		}
		varsPrev.clear();
		varsPrev.putAll(vars);
	}

	public NBTTagCompound writeToNBT(final NBTTagCompound nbt){
		final NBTTagCompound globalVars = new NBTTagCompound();
		for(final Entry<String, DataValue> e : this.globalVars.entrySet()) {
			globalVars.setTag(e.getKey(), e.getValue().writeToNBT());
		}

		final NBTTagCompound controls = new NBTTagCompound();
		for(int i = 0; i < this.controls.size(); i ++){
			controls.setTag("control" + i, this.controls.get(i).writeToNBT(new NBTTagCompound()));
		}
		
		nbt.setTag("globalvars", globalVars);
		nbt.setTag("controls", controls);
		nbt.setFloat("height", height);
		nbt.setFloat("angle", angle);
		nbt.setFloat("a_offset", a_off);
		nbt.setFloat("b_offset", b_off);
		nbt.setFloat("c_offset", c_off);
		nbt.setFloat("d_offset", d_off);
		return nbt;
	}

	public void readFromNBT(final NBTTagCompound tag){
		this.globalVars.clear();
		this.controls.clear();
		final NBTTagCompound globalVars = tag.getCompoundTag("globalvars");
		for(final String k : globalVars.getKeySet()) {
			final NBTBase base = globalVars.getTag(k);
			final DataValue val = DataValue.newFromNBT(base);
			if(val != null) {
				this.globalVars.put(k, val);
			}
		}

		final NBTTagCompound controls = tag.getCompoundTag("controls");
		for(int i = 0; i < controls.getKeySet().size(); i ++){
			final NBTTagCompound ct = controls.getCompoundTag("control" + i);
			final Control c = ControlRegistry.getNew(ct.getString("name"), this);

			final Map<String, DataValue> configs = new HashMap<>();
			final NBTTagCompound config_tag = ct.getCompoundTag("configs");
			for (final String s : config_tag.getKeySet()) {
				configs.put(s, DataValue.newFromNBT(config_tag.getTag(s)));
			}
			c.applyConfigs(configs);

			this.controls.add(c);
			c.readFromNBT(ct);
		}

		height = tag.getFloat("height");
		angle = tag.getFloat("angle");
		a_off = tag.getFloat("a_offset");
		b_off = tag.getFloat("b_offset");
		c_off = tag.getFloat("c_offset");
		d_off = tag.getFloat("d_offset");
	}

	public void receiveEvent(final BlockPos from, final ControlEvent evt){
		for(final Control c : controls) {
			final int idx = c.connectedSet.indexOf(from);
			if(idx != -1 || parent.getControlPos().equals(from)) {
				evt.setVar("from index", idx);
				c.receiveEvent(evt);
			}
		}
	}

	public DataValue getVar(final String name){
		return globalVars.getOrDefault(name, new DataValueFloat(0));
	}

	@SideOnly(Side.CLIENT)
	public Control getSelectedControl(final Vec3d pos, final Vec3d direction){
		final BlockPos blockPos = parent.getControlPos();
		final Vector4f localPos = new Vector4f((float)pos.x - blockPos.getX(), (float)pos.y - blockPos.getY(), (float)pos.z - blockPos.getZ(), 1);
		final Vector4f localDir = new Vector4f((float)direction.x, (float)direction.y, (float)direction.z, 0);

		Matrix4f.transform(inv_transform, localPos, localPos);
		Matrix4f.transform(inv_transform, localDir, localDir);
		final Vec3d start = new Vec3d(localPos.x, localPos.y, localPos.z);
		localDir.scale(3);
		final Vec3d end = new Vec3d(localPos.x + localDir.x, localPos.y + localDir.y, localPos.z + localDir.z);
		double dist = Double.MAX_VALUE;
		Control ctrl = null;
		for(final Control c : controls) {
			if (c.getBoundingBox() != null) {
				final RayTraceResult r = c.getBoundingBox().calculateIntercept(start, end);

				if (r != null && r.typeOfHit != Type.MISS) {
					final double newDist = r.hitVec.squareDistanceTo(start);
					if (newDist < dist) {
						dist = newDist;
						ctrl = c;
					}
				}
			}
		}
		return ctrl;
	}

	public static float getSlopeHeightFromZ(final float z, final float height, final float angle) {
		final double halfH = 0.5 * Math.tan(angle);

		return (float) ((height - halfH) + Math.tan(angle) * z);
	}

}
