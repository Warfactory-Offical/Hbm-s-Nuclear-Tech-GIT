package com.hbm.entity.effect;

import java.util.ArrayList;

import com.hbm.interfaces.IConstantRenderer;
import com.hbm.render.amlfrom1710.Vec3;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
/*
 * Toroidial Convection Simulation Explosion Effect
 * Tor                             Ex
 */
public class EntityNukeTorex extends Entity implements IConstantRenderer {

	public static final DataParameter<Float> SCALE = EntityDataManager.createKey(EntityNukeTorex.class, DataSerializers.FLOAT);
	public static final DataParameter<Byte> TYPE = EntityDataManager.createKey(EntityNukeTorex.class, DataSerializers.BYTE);
	
	public static final int firstCondenseHeight = 130;
	public static final int secondCondenseHeight = 170;
	public static final int blastWaveHeadstart = 5;
	public static final int maxCloudlets = 20_000;

	//Nuke colors
	public static final double nr1 = 2.5;
	public static final double ng1 = 1.3;
	public static final double nb1 = 0.4;
	public static final double nr2 = 0.1;
	public static final double ng2 = 0.075;
	public static final double nb2 = 0.05;

	//Balefire colors
	public static final double br1 = 1;
	public static final double bg1 = 2;
	public static final double bb1 = 0.5;
	public static final double br2 = 0.1;
	public static final double bg2 = 0.1;
	public static final double bb2 = 0.1;

	public double coreHeight = 3;
	public double convectionHeight = 3;
	public double torusWidth = 3;
	public double rollerSize = 1;
	public double heat = 1;
	public double lastSpawnY = -1;
	public ArrayList<Cloudlet> cloudlets = new ArrayList();
	public int maxAge = 1000;
	public float humidity = -1;

	public EntityNukeTorex(final World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(20F, 40F);
		this.isImmuneToFire = true;
		this.ignoreFrustumCheck = true;
	}

	@Override
	protected void entityInit() {
		this.dataManager.register(SCALE, 1.0F);
		this.dataManager.register(TYPE, Byte.valueOf((byte) 0));
	}

	@Override
	protected void readEntityFromNBT(final NBTTagCompound nbt) {
		if (nbt.hasKey("scale"))
			setScale(nbt.getFloat("scale"));
		if (nbt.hasKey("type"))
			this.dataManager.set(TYPE, nbt.getByte("type"));
	}

	@Override
	protected void writeEntityToNBT(final NBTTagCompound nbt) {
		nbt.setFloat("scale", this.dataManager.get(SCALE));
		nbt.setByte("type", this.dataManager.get(TYPE));
	}

	@Override
	@SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(final double distance) {
		return true;
	}

	@Override
	public void onUpdate() {
		if(world.isRemote) {
			
			final double s = this.getScale();
			final double cs = 1.5;
			if(this.ticksExisted == 1) this.setScale((float) s);
			
			if(humidity == -1) humidity = world.getBiome(this.getPosition()).getRainfall();
			
			if(lastSpawnY == -1) {
				lastSpawnY = posY - 3;
			}
			
			final int spawnTarget = Math.max(world.getHeight((int) Math.floor(posX), (int) Math.floor(posZ)) - 3, 1);
			final double moveSpeed = 0.5D;
			
			if(Math.abs(spawnTarget - lastSpawnY) < moveSpeed) {
				lastSpawnY = spawnTarget;
			} else {
				lastSpawnY += moveSpeed * Math.signum(spawnTarget - lastSpawnY);
			}
			
			// spawn mush clouds
			final double range = (torusWidth - rollerSize) * 0.5;
			final double simSpeed = getSimulationSpeed();
			int lifetime = Math.min((this.ticksExisted * this.ticksExisted) + 200, maxAge - this.ticksExisted + 200);
			final int toSpawn = (int) (0.6 * Math.min(Math.max(0, maxCloudlets-cloudlets.size()), Math.ceil(10 * simSpeed * simSpeed * Math.min(1, 1200/(double)lifetime))));
			

			for(int i = 0; i < toSpawn; i++) {
				final double x = posX + rand.nextGaussian() * range;
				final double z = posZ + rand.nextGaussian() * range;
				final Cloudlet cloud = new Cloudlet(x, lastSpawnY, z, (float)(rand.nextDouble() * 2D * Math.PI), 0, lifetime);
				cloud.setScale((float) (Math.sqrt(s) * 3 + this.ticksExisted * 0.0025 * s), (float) (Math.sqrt(s) * 3 + this.ticksExisted * 0.0025 * 6 * cs * s));
				cloudlets.add(cloud);
			}

			if(this.ticksExisted < 120 * s){
				world.setLastLightningBolt(2);
			}
			
			// spawn shock clouds
			if(this.ticksExisted < 150) {
				
				final int cloudCount = Math.min(this.ticksExisted * 2, 100);
				final int shockLife = Math.max(400 - this.ticksExisted * 20, 50);
				
				for(int i = 0; i < cloudCount; i++) {
					final Vec3 vec = Vec3.createVectorHelper((this.ticksExisted + rand.nextDouble() * 2) * 1.5, 0, 0);
					final float rot = (float) (Math.PI * 2 * rand.nextDouble());
					vec.rotateAroundY(rot);
					this.cloudlets.add(new Cloudlet(vec.xCoord + posX, world.getHeight((int) (vec.xCoord + posX) + 1, (int) (vec.zCoord + posZ)), vec.zCoord + posZ, rot, 0, shockLife, TorexType.SHOCK)
							.setScale((float)s * 5F, (float)s * 2F).setMotion(MathHelper.clamp(0.25 * this.ticksExisted - 5, 0, 1)));
				}
			}
			
			// spawn ring clouds
			if(this.ticksExisted < 200) {
				lifetime *= s;
				for(int i = 0; i < 2; i++) {
					final Cloudlet cloud = new Cloudlet(posX, posY + coreHeight, posZ, (float)(rand.nextDouble() * 2D * Math.PI), 0, lifetime, TorexType.RING);
					cloud.setScale((float) (Math.sqrt(s) * cs + this.ticksExisted * 0.0015 * s), (float) (Math.sqrt(s) * cs + this.ticksExisted * 0.0015 * 6 * cs * s));
					cloudlets.add(cloud);
				}
			}

			if(this.humidity > 0 && this.ticksExisted < 220){
				// spawn lower condensation clouds
				spawnCondensationClouds(this.ticksExisted, this.humidity, firstCondenseHeight, 80, 4, s, cs);

				// spawn upper condensation clouds
				spawnCondensationClouds(this.ticksExisted, this.humidity, secondCondenseHeight, 80, 2, s, cs);
			}

			cloudlets.removeIf(x -> x.isDead);
			for(final Cloudlet cloud : cloudlets) {
				cloud.update();
			}
			
			coreHeight += 0.15/* * s*/;
			torusWidth += 0.05/* * s*/;
			rollerSize = torusWidth * 0.35;
			convectionHeight = coreHeight + rollerSize;
			
			final int maxHeat = (int) (50 * s * s);
			heat = maxHeat - Math.pow((maxHeat * this.ticksExisted) / maxAge, 0.6);
		}
		
		if(!world.isRemote && this.ticksExisted > maxAge) {
			this.setDead();
		}
	}

	public void spawnCondensationClouds(final int age, final float humidity, final int height, final int count, final int spreadAngle, final double s, final double cs){
		if((posY + age) > height) {
			
			for(int i = 0; i < (int)(5 * humidity * count/(double)spreadAngle); i++) {
				for(int j = 1; j < spreadAngle; j++) {
					final float angle = (float) (Math.PI * 2 * rand.nextDouble());
					final Vec3 vec = Vec3.createVectorHelper(0, age, 0);
					vec.rotateAroundZ((float)Math.acos((height-posY)/(age))+(float)Math.toRadians(humidity*humidity*90*j*(0.1*rand.nextDouble()-0.05)));
					vec.rotateAroundY(angle);
					final Cloudlet cloud = new Cloudlet(posX + vec.xCoord, posY + vec.yCoord, posZ + vec.zCoord, angle, 0, (int) ((20 + age / 10) * (1 + rand.nextDouble() * 0.1)), TorexType.CONDENSATION);
					cloud.setScale(3F * (float) (cs * s), 4F * (float) (cs * s));
					cloudlets.add(cloud);
				}
			}
		}
	}
	
	public EntityNukeTorex setScale(final float scale) {
		if(!world.isRemote)
			this.dataManager.set(SCALE, scale);
		this.coreHeight = this.coreHeight * scale;
		this.convectionHeight = this.convectionHeight * scale;
		this.torusWidth = this.torusWidth * scale;
		this.rollerSize = this.rollerSize * scale;
		this.maxAge = (int) (45 * 20 * scale);
		return this;
	}
	
	public EntityNukeTorex setType(final int type) {
		this.dataManager.set(TYPE, (byte) type);
		return this;
	}

	public double getScale() {
		return this.dataManager.get(SCALE);
	}

	public byte getType() {
		return this.dataManager.get(TYPE);
	}
	
	public double getSimulationSpeed() {
		
		final int simSlow = maxAge / 4;
		final int life = this.ticksExisted;
		
		if(life > maxAge) {
			return 0D;
		}
		
		if(life > simSlow) {
			return 1D - ((double)(life - simSlow) / (double)(maxAge - simSlow));
		}
		
		return 1.0D;
	}
	
	public float getAlpha() {
		
		final int fadeOut = maxAge * 3 / 4;
		final int life = this.ticksExisted;
		
		if(life > fadeOut) {
			final float fac = (float)(life - fadeOut) / (float)(maxAge - fadeOut);
			return 1F - fac;
		}
		
		return 1.0F;
	}

	public Vec3 getInterpColor(final double interp, final byte type) {
		if(type == 0){
			return Vec3.createVectorHelper(
				(nr2 + (nr1 - nr2) * interp),
				(ng2 + (ng1 - ng2) * interp),
				(nb2 + (nb1 - nb2) * interp));
		}
		return Vec3.createVectorHelper(
			(br2 + (br1 - br2) * interp),
			(bg2 + (bg1 - bg2) * interp),
			(bb2 + (bb1 - bb2) * interp));
	}

	public class Cloudlet {

		public double posX;
		public double posY;
		public double posZ;
		public double prevPosX;
		public double prevPosY;
		public double prevPosZ;
		public double motionX;
		public double motionY;
		public double motionZ;
		public int age;
		public int cloudletLife;
		public float angle;
		public boolean isDead = false;
		float rangeMod = 1.0F;
		public float colorMod = 1.0F;
		public Vec3 color;
		public Vec3 prevColor;
		public TorexType type;
		private float startingScale = 3F;
		private float growingScale = 5F;
		
		public Cloudlet(final double posX, final double posY, final double posZ, final float angle, final int age, final int maxAge) {
			this(posX, posY, posZ, angle, age, maxAge, TorexType.STANDARD);
		}

		public Cloudlet(final double posX, final double posY, final double posZ, final float angle, final int age, final int maxAge, final TorexType type) {
			this.posX = posX;
			this.posY = posY;
			this.posZ = posZ;
			this.age = age;
			this.cloudletLife = maxAge;
			this.angle = angle;
			this.rangeMod = 0.3F + rand.nextFloat() * 0.7F;
			this.colorMod = 0.8F + rand.nextFloat() * 0.2F;
			this.type = type;
			
			this.updateColor();
		}

		private double motionMult = 1F;
		private final double motionConvectionMult = 0.5F;
		private final double motionLiftMult = 0.625F;
		private final double motionRingMult = 0.5F;
		private final double motionCondensationMult = 1F;
		private final double motionShockwaveMult = 1F;
		
		
		private void update() {
			age++;
			
			if(age > cloudletLife) {
				this.isDead = true;
			}

			this.prevPosX = this.posX;
			this.prevPosY = this.posY;
			this.prevPosZ = this.posZ;
			
			final Vec3 simPos = Vec3.createVectorHelper(EntityNukeTorex.this.posX - this.posX, 0, EntityNukeTorex.this.posZ - this.posZ);
			final double simPosX = EntityNukeTorex.this.posX + simPos.length();
			final double simPosZ = EntityNukeTorex.this.posZ + 0D;
			
			if(this.type == TorexType.STANDARD) {
				final Vec3 convection = getConvectionMotion(simPosX, simPosZ);
				final Vec3 lift = getLiftMotion(simPosX, simPosZ);
				
				final double factor = MathHelper.clamp((this.posY - EntityNukeTorex.this.posY) / EntityNukeTorex.this.coreHeight, 0, 1);
				this.motionX = convection.xCoord * factor + lift.xCoord * (1D - factor);
				this.motionY = convection.yCoord * factor + lift.yCoord * (1D - factor);
				this.motionZ = convection.zCoord * factor + lift.zCoord * (1D - factor);
			} else if(this.type == TorexType.RING) {
				final Vec3 motion = getRingMotion(simPosX, simPosZ);
				this.motionX = motion.xCoord;
				this.motionY = motion.yCoord;
				this.motionZ = motion.zCoord;
			} else if(this.type == TorexType.CONDENSATION) {
				final Vec3 motion = getCondensationMotion();
				this.motionX = motion.xCoord;
				this.motionY = motion.yCoord;
				this.motionZ = motion.zCoord;
			} else if(this.type == TorexType.SHOCK) {
				final Vec3 motion = getShockwaveMotion();
				this.motionX = motion.xCoord;
				this.motionY = motion.yCoord;
				this.motionZ = motion.zCoord;
			}
			
			final double mult = this.motionMult * getSimulationSpeed();
			
			this.posX += this.motionX * mult;
			this.posY += this.motionY * mult;
			this.posZ += this.motionZ * mult;
			
			this.updateColor();
		}
		
		private Vec3 getCondensationMotion() {
			final Vec3 delta = Vec3.createVectorHelper(posX - EntityNukeTorex.this.posX, 0, posZ - EntityNukeTorex.this.posZ).normalize();
			final double speed = motionCondensationMult * EntityNukeTorex.this.getScale() * 0.125D;
			delta.xCoord *= speed;
			delta.yCoord = 0;
			delta.zCoord *= speed;
			return delta;
		}

		private Vec3 getShockwaveMotion() {
			final Vec3 delta = Vec3.createVectorHelper(posX - EntityNukeTorex.this.posX, 0, posZ - EntityNukeTorex.this.posZ).normalize();
			final double speed = motionShockwaveMult * EntityNukeTorex.this.getScale() * 0.25D;
			delta.xCoord *= speed;
			delta.yCoord = 0;
			delta.zCoord *= speed;
			return delta;
		}
		
		private Vec3 getRingMotion(final double simPosX, final double simPosZ) {
			
			if(simPosX > EntityNukeTorex.this.posX + torusWidth * 2)
				return Vec3.createVectorHelper(0, 0, 0);
			
			/* the position of the torus' outer ring center */
			final Vec3 torusPos = Vec3.createVectorHelper(
					(EntityNukeTorex.this.posX + torusWidth),
					(EntityNukeTorex.this.posY + coreHeight * 0.5),
					EntityNukeTorex.this.posZ);
			
			/* the difference between the cloudlet and the torus' ring center */
			final Vec3 delta = Vec3.createVectorHelper(torusPos.xCoord - simPosX, torusPos.yCoord - this.posY, torusPos.zCoord - simPosZ);
			
			/* the distance this cloudlet wants to achieve to the torus' ring center */
			final double roller = EntityNukeTorex.this.rollerSize * this.rangeMod * 0.25;
			/* the distance between this cloudlet and the torus' outer ring perimeter */
			final double dist = delta.length() / roller - 1D;
			
			/* euler function based on how far the cloudlet is away from the perimeter */
			final double func = 1D - Math.pow(Math.E, -dist); // [0;1]
			/* just an approximation, but it's good enough */
			final float angle = (float) (func * Math.PI * 0.5D); // [0;90°]
			
			/* vector going from the ring center in the direction of the cloudlet, stopping at the perimeter */
			final Vec3 rot = Vec3.createVectorHelper(-delta.xCoord / dist, -delta.yCoord / dist, -delta.zCoord / dist);
			/* rotate by the approximate angle */
			rot.rotateAroundZ(angle);
			
			/* the direction from the cloudlet to the target position on the perimeter */
			Vec3 motion = Vec3.createVectorHelper(
					torusPos.xCoord + rot.xCoord - simPosX,
					torusPos.yCoord + rot.yCoord - this.posY,
					torusPos.zCoord + rot.zCoord - simPosZ);
			
			motion = motion.normalize();
			motion.rotateAroundY(this.angle);
			final double speed = motionRingMult * 0.5D;
			motion.xCoord *= speed;
			motion.yCoord *= speed;
			motion.zCoord *= speed;
			
			return motion;
		}
		
		/* simulated on a 2D-plane along the X/Y axis */
		private Vec3 getConvectionMotion(final double simPosX, final double simPosZ) {
			
			if(simPosX > EntityNukeTorex.this.posX + torusWidth * 2)
				return Vec3.createVectorHelper(0, 0, 0);
			
			/* the position of the torus' outer ring center */
			final Vec3 torusPos = Vec3.createVectorHelper(
					(EntityNukeTorex.this.posX + torusWidth),
					(EntityNukeTorex.this.posY + coreHeight),
					EntityNukeTorex.this.posZ);
			
			/* the difference between the cloudlet and the torus' ring center */
			final Vec3 delta = Vec3.createVectorHelper(torusPos.xCoord - simPosX, torusPos.yCoord - this.posY, torusPos.zCoord - simPosZ);
			
			/* the distance this cloudlet wants to achieve to the torus' ring center */
			final double roller = EntityNukeTorex.this.rollerSize * this.rangeMod;
			/* the distance between this cloudlet and the torus' outer ring perimeter */
			final double dist = delta.length() / roller - 1D;
			
			/* euler function based on how far the cloudlet is away from the perimeter */
			final double func = 1D - Math.pow(Math.E, -dist); // [0;1]
			/* just an approximation, but it's good enough */
			final float angle = (float) (func * Math.PI * 0.5D); // [0;90°]
			
			/* vector going from the ring center in the direction of the cloudlet, stopping at the perimeter */
			final Vec3 rot = Vec3.createVectorHelper(-delta.xCoord / dist, -delta.yCoord / dist, -delta.zCoord / dist);
			/* rotate by the approximate angle */
			rot.rotateAroundZ(angle);
			
			/* the direction from the cloudlet to the target position on the perimeter */
			Vec3 motion = Vec3.createVectorHelper(
					torusPos.xCoord + rot.xCoord - simPosX,
					torusPos.yCoord + rot.yCoord - this.posY,
					torusPos.zCoord + rot.zCoord - simPosZ);
			
			motion = motion.normalize();
			motion.rotateAroundY(this.angle);

			motion.xCoord *= motionConvectionMult;
			motion.yCoord *= motionConvectionMult;
			motion.zCoord *= motionConvectionMult;
			
			return motion;
		}
		
		private Vec3 getLiftMotion(final double simPosX, final double simPosZ) {
			final double scale = MathHelper.clamp(1D - (simPosX - (EntityNukeTorex.this.posX + torusWidth)), 0, 1) * motionLiftMult;
			
			Vec3 motion = Vec3.createVectorHelper(EntityNukeTorex.this.posX - this.posX, (EntityNukeTorex.this.posY + convectionHeight) - this.posY, EntityNukeTorex.this.posZ - this.posZ);
			
			motion = motion.normalize();
			motion.xCoord *= scale;
			motion.yCoord *= scale;
			motion.zCoord *= scale;
			
			return motion;
		}
		
		private void updateColor() {
			this.prevColor = this.color;

			final double exX = EntityNukeTorex.this.posX;
			final double exY = EntityNukeTorex.this.posY + EntityNukeTorex.this.coreHeight;
			final double exZ = EntityNukeTorex.this.posZ;

			final double distX = exX - posX;
			final double distY = exY - posY;
			final double distZ = exZ - posZ;
			
			double distSq = distX * distX + distY * distY + distZ * distZ;
			distSq /= this.type == TorexType.SHOCK ? EntityNukeTorex.this.heat * 3 : EntityNukeTorex.this.heat;
			
			final double col = 2D / Math.max(distSq, 1); //col goes from 2-0

			final byte type = EntityNukeTorex.this.getType();
			
			this.color = EntityNukeTorex.this.getInterpColor(col, type);
		}
		
		public Vec3 getInterpPos(final float interp) {
			return Vec3.createVectorHelper(
					prevPosX + (posX - prevPosX) * interp,
					prevPosY + (posY - prevPosY) * interp,
					prevPosZ + (posZ - prevPosZ) * interp);
		}
		
		public Vec3 getInterpColor(final float interp) {
			
			if(this.type == TorexType.CONDENSATION) {
				return Vec3.createVectorHelper(1F, 1F, 1F);
			}
			
			double greying = 0;
			
			if(this.type == TorexType.RING) {
				greying += 0.05;
			}
			
			return Vec3.createVectorHelper(
					(prevColor.xCoord + (color.xCoord - prevColor.xCoord) * interp) + greying,
					(prevColor.yCoord + (color.yCoord - prevColor.yCoord) * interp) + greying,
					(prevColor.zCoord + (color.zCoord - prevColor.zCoord) * interp) + greying);
		}
		
		public float getAlpha() {
			float alpha = (1F - ((float)age / (float)cloudletLife)) * EntityNukeTorex.this.getAlpha();
			if(this.type == TorexType.CONDENSATION) alpha *= 0.25;
			return MathHelper.clamp(alpha, 0.0001F, 1F);
		}
		
		
		public float getScale() {
			return startingScale + ((float)age / (float)cloudletLife) * growingScale;
		}
		
		public Cloudlet setScale(final float start, final float grow) {
			this.startingScale = start;
			this.growingScale = grow;
			return this;
		}
		
		public Cloudlet setMotion(final double mult) {
			this.motionMult = mult;
			return this;
		}
	}
	
	public static enum TorexType {
		STANDARD,
		RING,
		CONDENSATION,
		SHOCK
	}
	
	public static void statFac(final World world, final double x, final double y, final double z, final float scale) {
		final EntityNukeTorex torex = new EntityNukeTorex(world).setScale(MathHelper.clamp(scale * 0.01F, 0.25F, 5F));
		torex.setPosition(x, y, z);
		world.spawnEntity(torex);
	}
	
	public static void statFacBale(final World world, final double x, final double y, final double z, final float scale) {
		final EntityNukeTorex torex = new EntityNukeTorex(world).setScale(MathHelper.clamp(scale * 0.01F, 0.25F, 5F)).setType(1);
		torex.setPosition(x, y, z);
		world.spawnEntity(torex);
	}
}
