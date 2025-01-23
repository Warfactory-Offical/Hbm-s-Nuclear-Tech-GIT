package com.hbm.physics;

import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.util.BobMathUtil;

public class ContactManifold {

	public static final float CONTACT_BREAK = 0.02F;
	public static final float CONTACT_BREAK_SQ = CONTACT_BREAK*CONTACT_BREAK;
	
	public Contact[] contacts = new Contact[4];
	public int contactCount = 0;
	
	public ContactManifold() {
	}
	
	public void update(){
		for(int i = 0; i < contactCount; i++){
			final Contact c = contacts[i];
			c.globalA = c.bodyA.localToGlobalPos(c.localA);
			c.globalB = c.bodyB.localToGlobalPos(c.localB);
			
			contacts[i].depth = (float) c.globalA.subtract(c.globalB).dotProduct(c.normal);
		}
		
		for(int i = 0; i < contactCount; i ++){
			final Contact c = contacts[i];
			//This is where contacts go to die
			if(c.depth > CONTACT_BREAK){
				removeContact(i);
				i --;
			} else {
				final Vec3 proj = c.globalA.subtract(c.normal.mult(c.depth));
				final double orthoDistToB = proj.subtract(c.globalB).lengthSquared();
				if(orthoDistToB > CONTACT_BREAK_SQ){
					removeContact(i);
					i--;
				}
			}
		}
	}
	
	public void removeContact(final int idx){
		contacts[idx] = null;
		for(int i = idx; i < 3; i ++){
			contacts[i] = contacts[i+1];
			contacts[i+1] = null;
		}
		contactCount --;
	}
	
	/**
	 * Adds a contact to this manifold
	 * @param c - the contact to try to add
	 * @return true if it added successfully
	 */
	public boolean addContact(final Contact c){
		int idx = getContactIndex(c);
		boolean replace = true;
		if(idx < 0){
			if(contactCount < 4){
				idx = contactCount;
				replace = false;
			} else {
				idx = getLeastRemoteIndex(c);
			}
		}
		if(idx >= 0){
			if(replace){
			} else {
				contactCount ++;
			}
			contacts[idx] = c;
			return true;
		}
		return false;
	}
	
	//Idea from bullet physics engine, the point where the quad made from the other four points has the most area is the point that matters least
	//and we can remove it. We also want to keep the deepest point, so we just use a 0 area for the deepest point.
	public int getLeastRemoteIndex(final Contact c){
		float deepest = -Float.MAX_VALUE;
		int deepIdx = -1;
		for(int i = 0; i < contactCount; i ++){
			if(contacts[i].depth > deepest){
				deepest = contacts[i].depth;
				deepIdx = i;
			}
		}
		double res0 = 0F, res1 = 0F, res2 = 0F, res3 = 0F;
		if(deepIdx != 0){
			final Vec3 a = c.localA.subtract(contacts[1].localA);
			final Vec3 b = contacts[3].localA.subtract(contacts[2].localA);
			res0 = a.crossProduct(b).lengthSquared();
		}
		if(deepIdx != 1){
			final Vec3 a = c.localA.subtract(contacts[0].localA);
			final Vec3 b = contacts[3].localA.subtract(contacts[2].localA);
			res1 = a.crossProduct(b).lengthSquared();
		}
		if(deepIdx != 2){
			final Vec3 a = c.localA.subtract(contacts[0].localA);
			final Vec3 b = contacts[3].localA.subtract(contacts[1].localA);
			res2 = a.crossProduct(b).lengthSquared();
		}
		if(deepIdx != 3){
			final Vec3 a = c.localA.subtract(contacts[0].localA);
			final Vec3 b = contacts[2].localA.subtract(contacts[1].localA);
			res3 = a.crossProduct(b).lengthSquared();
		}
		return BobMathUtil.absMaxIdx(res0, res1, res2, res3);
	}
	
	public int getContactIndex(final Contact c){
		int idx = -1;
		double shortestDist = CONTACT_BREAK_SQ;
		for(int i = 0; i < contactCount; i ++){
			final double dist = contacts[i].localA.subtract(c.localA).lengthSquared();
			if(dist < shortestDist){
				shortestDist = dist;
				idx = i;
			}
		}
		return idx;
	}
	
}
