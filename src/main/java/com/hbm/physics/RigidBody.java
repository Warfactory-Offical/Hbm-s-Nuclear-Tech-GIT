package com.hbm.physics;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ClientProxy;
import com.hbm.physics.GJK.GJKInfo;
import com.hbm.physics.GJK.Result;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.util.BobMathUtil;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RigidBody {

	public static final Vec3[] cardinals = new Vec3[]{
			new Vec3(1, 0, 0), new Vec3(0, 1, 0), new Vec3(0, 0, 1),
			new Vec3(-1, 0, 0), new Vec3(0, -1, 0), new Vec3(0, 0, -1)};
	
	public static final RigidBody DUMMY = new RigidBody(null){
		public void solveContacts(final float dt) {}

        public void impulse(final Vec3 force, final Vec3 position) {}

        public void updateOrientation() {}

        public void updateGlobalCentroidFromPosition() {}

        public void updatePositionFromGlobalCentroid() {}

        public void doTimeStep(final float dt) {}

        public void addColliders(final Collider... collide) {}

        public Vec3 globalToLocalPos(final Vec3 pos) {return pos;}

        public Vec3 localToGlobalPos(final Vec3 pos) {return pos;}

        public Vec3 globalToLocalVec(final Vec3 vec) {return vec;}

        public Vec3 localToGlobalVec(final Vec3 vec) {return vec;}

        public void addLinearVelocity(final Vec3 v) {}

        public void addAngularVelocity(final Vec3 v) {}

        public void addContact(final Contact c) {}
    };
	
	static {
		DUMMY.inv_rotation = (Matrix3f) DUMMY.rotation.clone();
		DUMMY.localInertiaTensor = new Matrix3f();
		DUMMY.inv_localInertiaTensor = new Matrix3f();
		DUMMY.inv_globalInertiaTensor = new Matrix3f();
		DUMMY.localCentroid = new Vec3(0, 0, 0);
		DUMMY.globalCentroid = new Vec3(0, 0, 0);
	}
	
	public World world;
	public AxisAlignedBB boundingBox;
	
	public List<Collider> colliders = new ArrayList<>();
	public List<AxisAlignedBB> colliderBoundingBoxes = new ArrayList<>();
	
	public Vec3 position = new Vec3(0, 0, 0);
	public Vec3 globalCentroid;
	public Matrix3f rotation = new Matrix3f();
	public Matrix3f inv_rotation;
	
	public Vec3 prevPosition = new Vec3(0, 0, 0);
	public Quat4f prevRotation = new Quat4f();
	
	public Vec3 linearVelocity = new Vec3(0, 0, 0);
	public Vec3 angularVelocity = new Vec3(0, 0, 0);
	public Vec3 force = new Vec3(0, 0, 0);
	public Vec3 torque = new Vec3(0, 0, 0);
	
	public float mass;
	public float inv_mass;
	public Matrix3f localInertiaTensor;
	public Matrix3f inv_localInertiaTensor;
	public Matrix3f inv_globalInertiaTensor;
	public float friction = 0.5F;
	public float restitution = 0;
	
	public Vec3 localCentroid;
	
	public ContactManifold contacts = new ContactManifold();
	
	public RigidBody(final World w) {
		this.world = w;
		rotation.setIdentity();
	}
	
	public RigidBody(final World w, final double x, final double y, final double z) {
		this(w);
		this.position = new Vec3(x, y, z);
	}
	
	public void minecraftTimestep(){
		this.setPrevData();
		final int timeStepSubDiv = 8;
		final float step = 0.05F/(float)timeStepSubDiv;
		for(int i = 0; i < timeStepSubDiv; i ++){
			doTimeStep(step);
		}
	}
	
	public void doTimeStep(final float dt){
		contacts.update();
		//Do collision detection
		final GJKInfo bestInfo = null;
		final Collider a = null;
		Collider b = null;
		final List<AxisAlignedBB> l = world.getCollisionBoxes(null, boundingBox);
		for(final AxisAlignedBB box : l){
			for(int i = 0; i < colliders.size(); i ++){
				final Collider c = colliders.get(i);
				if(!colliderBoundingBoxes.get(i).intersects(box))
					continue;
				b = new AABBCollider(box);
				final GJKInfo info = GJK.colliding(this, null, c, b);
				if(info.result == Result.COLLIDING /*&& (bestInfo == null || bestInfo.depth < info.depth)*/){
					//No need to find whatever is deepest. We can just add every contact and let the manifold sort itself out correctly.
					//bestInfo = info;
					contacts.addContact(new Contact(this, null, c, b, info));
				}
			}
		}
		
		//if(bestInfo != null){
		//	contacts.addContact(new Contact(this, null, a, b, bestInfo));
		//}
		
		solveContacts(dt);
		integrateVelocityAndPosition(dt);
	}
	
	public void integrateVelocityAndPosition(final float dt){
		//Integrate velocity
		linearVelocity = linearVelocity.add(force.mult(inv_mass*dt));
		angularVelocity = angularVelocity.add(torque.mult(dt).matTransform(inv_globalInertiaTensor));
				
		force.setComponents(0, 0, 0);
		torque.setComponents(0, 0, 0);
				
		//Integrate position
		globalCentroid = globalCentroid.add(linearVelocity.mult(dt));
		if(angularVelocity.lengthSquared() > 0){
			final Vec3 axis = angularVelocity.normalize();
			final double angle = angularVelocity.length()*dt;
			final Matrix3f turn = new Matrix3f();
			turn.set(new AxisAngle4f((float)axis.xCoord, (float)axis.yCoord, (float)axis.zCoord, (float)angle));
			turn.mul(rotation);
			rotation = turn;
			updateOrientation();
		}
		updatePositionFromGlobalCentroid();
		updateAABBs();
		addLinearVelocity(new Vec3(0, -9.81*dt, 0));
	}
	
	public void setPrevData(){
		this.prevPosition.set(position);
		setFromMat(prevRotation, rotation);
	}
	
	public void addContact(final Contact c){
		contacts.addContact(c);
	}
	
	public void solveContacts(final float dt){
		for(int j = 0; j < contacts.contactCount; j ++){
			contacts.contacts[j].init(dt);
		}
		final int velocityIterations = 4;
		for(int i = 0; i < velocityIterations; i ++){
			for(int j = 0; j < contacts.contactCount; j ++){
				contacts.contacts[j].solve(dt);
			}
		}
	}
	
	/*public void solveContacts(float dt){
		for(int i = 0; i < contacts.contactCount; i ++){
			Contact c = contacts.contacts[i];
			RigidBody bodyA = c.bodyA;
			RigidBody bodyB = c.bodyB;
			Vec3 contactNormal = c.normal;
			if(bodyA == RigidBody.DUMMY || bodyA != this){
				bodyA = c.bodyB;
				bodyB = c.bodyA;
				contactNormal = contactNormal.negate();
			}
			Vec3 rA = c.localA.subtract(c.a.localCentroid);
			Vec3 rB = c.localB.subtract(c.b.localCentroid);
			Vec3[] jacobian = new Vec3[]{contactNormal.negate(), rA.crossProduct(contactNormal).negate(), contactNormal, rB.crossProduct(contactNormal)};
			double inv_effectiveMass = 
				  bodyA.inv_mass
				+ jacobian[1].dotProduct(jacobian[1].matTransform(bodyA.inv_globalInertiaTensor))
				+ bodyB.inv_mass
				+ jacobian[3].dotProduct(jacobian[3].matTransform(bodyB.inv_globalInertiaTensor));
			
			double jv = 
				  jacobian[0].dotProduct(bodyA.linearVelocity)
				+ jacobian[1].dotProduct(bodyA.angularVelocity)
				+ jacobian[2].dotProduct(bodyB.linearVelocity)
				+ jacobian[3].dotProduct(bodyB.angularVelocity);
			
			float beta = 0.2F;
			float b = -(beta/dt)*c.depth;
			
			float lambda = (float) ((-(jv+b))/inv_effectiveMass);
			//float oldTotalLambda = c.totalLambda;
			//c.totalLambda = Math.max(0, c.totalLambda + lambda);
			//lambda = c.totalLambda - oldTotalLambda;
			
			bodyA.addLinearVelocity(jacobian[0].mult(bodyA.inv_mass*lambda));
			bodyA.addAngularVelocity(jacobian[1].matTransform(bodyA.inv_globalInertiaTensor).mult(lambda));
			bodyB.addLinearVelocity(jacobian[2].mult(bodyB.inv_mass*lambda));
			bodyB.addAngularVelocity(jacobian[3].matTransform(bodyB.inv_globalInertiaTensor).mult(lambda));
		}
		
	}*/
	
	public Vec3 localToGlobalPos(final Vec3 pos){
		return pos.matTransform(rotation).add(position);
	}
	public Vec3 globalToLocalPos(final Vec3 pos){
		return pos.subtract(position).matTransform(inv_rotation);
	}
	public Vec3 localToGlobalVec(final Vec3 vec){
		return vec.matTransform(rotation);
	}
	public Vec3 globalToLocalVec(final Vec3 vec){
		return vec.matTransform(inv_rotation);
	}
	
	public void addLinearVelocity(final Vec3 v){
		linearVelocity = linearVelocity.add(v);
	}
	public void addAngularVelocity(final Vec3 v){
		angularVelocity = angularVelocity.add(v);
	}
	public void impulse(final Vec3 force, final Vec3 position){
		this.force = this.force.add(force);
		this.torque = this.torque.add(position.subtract(globalCentroid).crossProduct(force));
	}
	
	public void impulseVelocity(final Vec3 force, final Vec3 position){
		linearVelocity = linearVelocity.add(force.mult(inv_mass));
		angularVelocity = angularVelocity.add(position.subtract(globalCentroid).crossProduct(force).matTransform(inv_globalInertiaTensor));
	}
	
	public void impulseVelocityDirect(final Vec3 force, final Vec3 position){
		linearVelocity = linearVelocity.add(force);
		angularVelocity = angularVelocity.add(position.subtract(globalCentroid).crossProduct(force));
	}
	
	public void updateOrientation(){
		final Quat4f quat = new Quat4f();
		final float epsilon = 0.00001F;
		//quat.set(rotation);
		setFromMat(quat, rotation);
		quat.normalize();
		BobMathUtil.matrixFromQuat(rotation, quat);
		//System.out.println("1");
		inv_rotation = (Matrix3f) rotation.clone();
		inv_rotation.transpose();
		
		/*Matrix3f bruh = new Matrix3f(0.44135904F, -0.0038072586F, 0.89732254F,
				-0.004132689F, -0.9999893F, -0.0022101535F,
				0.8973211F, -0.002732877F, -0.44137013F);
		
		System.out.println(bruh.m00 + bruh.m11 + bruh.m22 + 1.0f);
		quat.set(bruh);
		System.out.println(quat);
		quat.normalize();
		System.out.println(quat);
		BobMathUtil.matrixFromQuat(bruh, quat);
		System.out.println(bruh);*/
		
		inv_globalInertiaTensor.set(inv_rotation);
		inv_globalInertiaTensor.mul(inv_localInertiaTensor);
		inv_globalInertiaTensor.mul(rotation);
	}
	public void updatePositionFromGlobalCentroid(){
		position = globalCentroid.add(localCentroid.mult(-1).matTransform(rotation));
	}
	public void updateGlobalCentroidFromPosition(){
		globalCentroid = localCentroid.matTransform(rotation).add(position);
	}
	public void updateAABBs(){
		colliderBoundingBoxes.clear();
		double tMaxX, tMaxY, tMaxZ, tMinX, tMinY, tMinZ;
		tMaxX = tMaxY = tMaxZ = -Double.MAX_VALUE;
		tMinX = tMinY = tMinZ = Double.MAX_VALUE;
		for(final Collider c : colliders){
			final double maxX = GJK.localSupport(this, c, cardinals[0]).xCoord;
			final double maxY = GJK.localSupport(this, c, cardinals[1]).yCoord;
			final double maxZ = GJK.localSupport(this, c, cardinals[2]).zCoord;
			final double minX = GJK.localSupport(this, c, cardinals[3]).xCoord;
			final double minY = GJK.localSupport(this, c, cardinals[4]).yCoord;
			final double minZ = GJK.localSupport(this, c, cardinals[5]).zCoord;
			colliderBoundingBoxes.add(new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ));
			tMaxX = Math.max(tMaxX, maxX);
			tMaxY = Math.max(tMaxY, maxY);
			tMaxZ = Math.max(tMaxZ, maxZ);
			tMinX = Math.min(tMinX, minX);
			tMinY = Math.min(tMinY, minY);
			tMinZ = Math.min(tMinZ, minZ);
		}
		boundingBox = new AxisAlignedBB(tMinX, tMinY, tMinZ, tMaxX, tMaxY, tMaxZ);
	}
	
	public void addColliders(final Collider... collide){
        Collections.addAll(colliders, collide);
		localCentroid = new Vec3(0, 0, 0);
		mass = 0;
		for(final Collider c : colliders){
			mass += c.mass;
			localCentroid = localCentroid.add(c.localCentroid.mult(c.mass));
		}
		inv_mass = 1F/mass;
		localCentroid = localCentroid.mult(inv_mass);
		
		localInertiaTensor = new Matrix3f();
		for(final Collider c : colliders){
			//https://en.wikipedia.org/wiki/Parallel_axis_theorem
			final Vec3 colliderToLocal = localCentroid.subtract(c.localCentroid);
			final double lenSquared = colliderToLocal.dotProduct(colliderToLocal);
			final Matrix3f outerProduct = colliderToLocal.outerProduct(colliderToLocal);
			
			final Matrix3f colliderToLocalMat = new Matrix3f();
			colliderToLocalMat.setIdentity();
			colliderToLocalMat.mul((float) lenSquared);
			colliderToLocalMat.sub(outerProduct);
			colliderToLocalMat.mul(c.mass);
			final Matrix3f cLocalIT = (Matrix3f) c.localInertiaTensor.clone();
			cLocalIT.add(colliderToLocalMat);
			localInertiaTensor.add(cLocalIT);
		}
		inv_localInertiaTensor = new Matrix3f();
		inv_localInertiaTensor.set(localInertiaTensor);
		
		inv_localInertiaTensor.invert();
		inv_globalInertiaTensor = new Matrix3f();
		updateOrientation();
		updateGlobalCentroidFromPosition();
		this.prevPosition = position;
		updateAABBs();
	}
	
	@SideOnly(Side.CLIENT)
	public void doGlTransform(final Vec3 playerPos, final float partialTicks){
		final FloatBuffer buf = ClientProxy.AUX_GL_BUFFER;
		final Quat4f quat = new Quat4f();
		setFromMat(quat, rotation);
		quat.interpolate(prevRotation, 1-partialTicks);
		quat.normalize();
		final Matrix3f rotation = new Matrix3f();
		BobMathUtil.matrixFromQuat(rotation, quat);
		
		buf.put(0, rotation.m00);
		buf.put(1, rotation.m10);
		buf.put(2, rotation.m20);
		buf.put(3, 0);
		buf.put(4, rotation.m01);
		buf.put(5, rotation.m11);
		buf.put(6, rotation.m21);
		buf.put(7, 0);
		buf.put(8, rotation.m02);
		buf.put(9, rotation.m12);
		buf.put(10, rotation.m22);
		buf.put(11, 0);
		
		final Vec3 pos = this.prevPosition.add(this.position.subtract(this.prevPosition).mult(partialTicks)).subtract(playerPos);
		
		buf.put(12, (float)pos.xCoord);
		buf.put(13, (float)pos.yCoord);
		buf.put(14, (float)pos.zCoord);
		buf.put(15, 1);
		
		GL11.glMultMatrix(buf);
		
		buf.rewind();
	}
	
	//vecmath's matrix to quaternion function is garbage and lwjgl's is a chad that actually works correctly when the rotation is aligned along an axis.
	public static void setFromMat(final Quat4f q, final Matrix3f mat){
		setFromMat(q, mat.m00, mat.m01, mat.m02, mat.m10, mat.m11, mat.m12, mat.m20, mat.m21, mat.m22);
	}
	
	public static void setFromMat(final Quat4f q, final float m00, final float m01, final float m02, final float m10,
                                  final float m11, final float m12, final float m20, final float m21, final float m22) {

		float s;
		final float tr = m00 + m11 + m22;
		if (tr >= 0.0) {
			s = (float) Math.sqrt(tr + 1.0);
			q.w = s * 0.5f;
			s = 0.5f / s;
			q.x = (m21 - m12) * s;
			q.y = (m02 - m20) * s;
			q.z = (m10 - m01) * s;
		} else {
			final float max = Math.max(Math.max(m00, m11), m22);
			if (max == m00) {
				s = (float) Math.sqrt(m00 - (m11 + m22) + 1.0);
				q.x = s * 0.5f;
				s = 0.5f / s;
				q.y = (m01 + m10) * s;
				q.z = (m20 + m02) * s;
				q.w = (m21 - m12) * s;
			} else if (max == m11) {
				s = (float) Math.sqrt(m11 - (m22 + m00) + 1.0);
				q.y = s * 0.5f;
				s = 0.5f / s;
				q.z = (m12 + m21) * s;
				q.x = (m01 + m10) * s;
				q.w = (m02 - m20) * s;
			} else {
				s = (float) Math.sqrt(m22 - (m00 + m11) + 1.0);
				q.z = s * 0.5f;
				s = 0.5f / s;
				q.x = (m20 + m02) * s;
				q.y = (m12 + m21) * s;
				q.w = (m10 - m01) * s;
			}
		}
	}
	
	public void renderDebugInfo(final Vec3 offset, final float partialTicks){
		GL11.glPushMatrix();
		final BufferBuilder buf = Tessellator.getInstance().getBuffer();
		GlStateManager.disableDepth();
		for(final Contact c : contacts.contacts){
			if(c != null){
				buf.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
				final Vec3 normal = c.normal.mult(0.5F);
				final Vec3 globalA = c.globalA.subtract(offset);
				final Vec3 globalB = c.globalB.subtract(offset);
				buf.pos(globalA.xCoord, globalA.yCoord, globalA.zCoord).color(1, 0, 0, 1).endVertex();
				buf.pos(globalA.xCoord-normal.xCoord, globalA.yCoord-normal.yCoord, globalA.zCoord-normal.zCoord).color(1, 0, 0, 1).endVertex();
				
				buf.pos(globalB.xCoord, globalB.yCoord, globalB.zCoord).color(1, 0, 0, 1).endVertex();
				buf.pos(globalB.xCoord+normal.xCoord, globalB.yCoord+normal.yCoord, globalB.zCoord+normal.zCoord).color(1, 0, 0, 1).endVertex();
				Tessellator.getInstance().draw();
				
				GL11.glPointSize(16);
				buf.begin(GL11.GL_POINTS, DefaultVertexFormats.POSITION_COLOR);
				buf.pos(globalA.xCoord, globalA.yCoord, globalA.zCoord).color(1, 0, 0, 1).endVertex();
				buf.pos(globalB.xCoord, globalB.yCoord, globalB.zCoord).color(1, 0, 0, 1).endVertex();
				Tessellator.getInstance().draw();
			}
		}
		GlStateManager.enableDepth();
		doGlTransform(offset, partialTicks);
		for(final Collider c : colliders){
			c.debugRender();
		}
		GL11.glPopMatrix();
	}
}
