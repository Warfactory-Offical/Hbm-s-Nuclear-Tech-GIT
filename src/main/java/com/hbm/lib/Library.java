package com.hbm.lib;

import api.hbm.energy.IBatteryItem;
import api.hbm.energy.IEnergyConnector;
import api.hbm.energy.IEnergyConnectorBlock;
import com.google.common.base.Predicates;
import com.google.common.collect.Sets;
import com.hbm.blocks.ModBlocks;
import com.hbm.capability.HbmLivingCapability.EntityHbmPropsProvider;
import com.hbm.capability.HbmLivingCapability.IEntityHbmProps;
import com.hbm.entity.mob.EntityHunterChopper;
import com.hbm.entity.projectile.EntityChopperMine;
import com.hbm.handler.WeightedRandomChestContentFrom1710;
import com.hbm.interfaces.Spaghetti;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.util.BobMathUtil;
import com.hbm.util.ItemStackUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.*;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

@Spaghetti("this whole class")
public class Library {

	static Random rand = new Random();
	
	//this is a list of UUIDs used for various things, primarily for accessories.
	//for a comprehensive list, check RenderAccessoryUtility.java
	public static String HbMinecraft = "192af5d7-ed0f-48d8-bd89-9d41af8524f8";
	public static String TacoRedneck = "5aee1e3d-3767-4987-a222-e7ce1fbdf88e";
	// Earl0fPudding
	public static String LPkukin = "937c9804-e11f-4ad2-a5b1-42e62ac73077";
	public static String Dafnik = "3af1c262-61c0-4b12-a4cb-424cc3a9c8c0";
	// anna20
	public static String a20 = "4729b498-a81c-42fd-8acd-20d6d9f759e0";
	public static String rodolphito = "c3f5e449-6d8c-4fe3-acc9-47ef50e7e7ae";
	public static String LordVertice = "a41df45e-13d8-4677-9398-090d3882b74f";
	// twillycorn
	public static String CodeRed_ = "912ec334-e920-4dd7-8338-4d9b2d42e0a1";
	public static String dxmaster769 = "62c168b2-d11d-4dbf-9168-c6cea3dcb20e";
	public static String Dr_Nostalgia = "e82684a7-30f1-44d2-ab37-41b342be1bbd";
	public static String Samino2 = "87c3960a-4332-46a0-a929-ef2a488d1cda";
	public static String Hoboy03new = "d7f29d9c-5103-4f6f-88e1-2632ff95973f";
	public static String Dragon59MC = "dc23a304-0f84-4e2d-b47d-84c8d3bfbcdb";
	public static String SteelCourage = "ac49720b-4a9a-4459-a26f-bee92160287a";
	public static String Ducxkskiziko = "122fe98f-be19-49ca-a96b-d4dee4f0b22e";
	
	public static String SweatySwiggs = "5544aa30-b305-4362-b2c1-67349bb499d5";
	public static String Drillgon = "41ebd03f-7a12-42f3-b037-0caa4d6f235b";
	public static String Alcater = "0b399a4a-8545-45a1-be3d-ece70d7d48e9";
	public static String ege444 = "42ee978c-442a-4cd8-95b6-29e469b6df10";
	public static String Doctor17 = "e4ab1199-1c22-4f82-a516-c3238bc2d0d1";
	public static String Doctor17PH = "4d0477d7-58da-41a9-a945-e93df8601c5a";
	public static String ShimmeringBlaze = "061bc566-ec74-4307-9614-ac3a70d2ef38";
	public static String FifeMiner = "37e5eb63-b9a2-4735-9007-1c77d703daa3";
	public static String lag_add = "259785a0-20e9-4c63-9286-ac2f93ff528f";
	public static String Pu_238 = "c95fdfd3-bea7-4255-a44b-d21bc3df95e3";

	public static String Golem = "058b52a6-05b7-4d11-8cfa-2db665d9a521";
	public static Set<String> contributors = Sets.newHashSet("06ab7c03-55ce-43f8-9d3c-2850e3c652de", //mustang_rudolf
            "5bf069bc-5b46-4179-aafe-35c0a07dee8b" //JMF781
    );


	public static final ForgeDirection POS_X = ForgeDirection.EAST;
	public static final ForgeDirection NEG_X = ForgeDirection.WEST;
	public static final ForgeDirection POS_Y = ForgeDirection.UP;
	public static final ForgeDirection NEG_Y = ForgeDirection.DOWN;
	public static final ForgeDirection POS_Z = ForgeDirection.SOUTH;
	public static final ForgeDirection NEG_Z = ForgeDirection.NORTH;

	public static final int[] powersOfTen = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

	public static DecimalFormat numberformat = new DecimalFormat("0.00");
		
	//the old list that allowed superuser mode for the ZOMG
	//currently unused
	public static List<String> superuser = new ArrayList<String>();

	// Drillgon200: Not like super users are used for anything, but they could
	// in the future I guess.
	public static void initSuperusers() {
		superuser.add(HbMinecraft);
		superuser.add(TacoRedneck);
		superuser.add(LPkukin);
		superuser.add(Dafnik);
		superuser.add(a20);
		superuser.add(rodolphito);
		// Drillgon200: Pretty sure he did install NEI.
		superuser.add(Ducxkskiziko);
		superuser.add(Drillgon);
		superuser.add(Alcater);
	}

	public static boolean checkForHeld(final EntityPlayer player, final Item item) {
		return player.getHeldItemMainhand().getItem() == item || player.getHeldItemOffhand().getItem() == item;
	}

	public static boolean isObstructed(final World world, final double x, final double y, final double z, final double a, final double b, final double c) {
		final RayTraceResult pos = world.rayTraceBlocks(new Vec3d(x, y, z), new Vec3d(a, b, c), false, true, true);
		return pos != null && pos.typeOfHit != Type.MISS;
	}

	public static int getColorProgress(final double fraction){
		final int r = (int)(255*Math.min(1, fraction*-2+2));
		final int g = (int)(255*Math.min(1, fraction*2));
		return 65536 * r + 256 * g;
	}

	public static String getPercentage(final double fraction){
		return numberformat.format(roundFloat(fraction*100D, 2));
	}

	public static String getShortNumber(final long l) {
		return getShortNumber(new BigDecimal(l));
	}

	public static Map<Integer, String> numbersMap = null;
	

	public static void initNumbers(){
		numbersMap = new TreeMap<>();
		numbersMap.put(3, "k");
		numbersMap.put(6, "M");
		numbersMap.put(9, "G");
		numbersMap.put(12, "T");
		numbersMap.put(15, "P");
		numbersMap.put(18, "E");
		numbersMap.put(21, "Z");
		numbersMap.put(24, "Y");
		numbersMap.put(27, "R");
		numbersMap.put(30, "Q");
	}
	
	public static String getShortNumber(BigDecimal l) {
		if(numbersMap == null) initNumbers();

		final boolean negative = l.signum() < 0;
		if(negative){
			l = l.negate();
		}

		String result = l.toPlainString();
		BigDecimal c = null;
		for(final Map.Entry<Integer, String> num : numbersMap.entrySet()){
			c = new BigDecimal("1E"+num.getKey());
			if(l.compareTo(c) >= 0){
				final double res = l.divide(c).doubleValue();
				result = numberformat.format(roundFloat(res, 2)) + num.getValue();
			} else {
				break;
			}
		}

		if (negative){
			result = "-"+result;
		}

		return result;
	}

	public static float roundFloat(final float number, final int decimal){
		return Math.round(number * powersOfTen[decimal]) / (float)powersOfTen[decimal];
	}

	public static float roundFloat(final double number, final int decimal){
		return Math.round(number * powersOfTen[decimal]) / (float)powersOfTen[decimal];
	}

	public static int getColorFromItemStack(final ItemStack stack){
		ResourceLocation path = null;
		ResourceLocation actualPath = null;
		final TextureAtlasSprite sprite = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(stack.getItem(), stack.getMetadata());
		if (sprite != null) {
			path = new ResourceLocation(sprite.getIconName() + ".png");
			actualPath = new ResourceLocation(path.getNamespace(), "textures/" + path.getPath());
		} else {
			path = new ResourceLocation(stack.getItem().getRegistryName() + ".png");
			actualPath = new ResourceLocation(path.getNamespace(), "textures/items/" + path.getPath());
		}
		return getColorFromResourceLocation(actualPath);
	}

	public static int getColorFromResourceLocation(final ResourceLocation r){
		if(r == null) {
			return 0;
		}
		try{
			final BufferedImage image = ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(r).getInputStream());
			return getRGBfromARGB(image.getRGB(image.getWidth()>>1, image.getHeight()>>1));
		} catch(final Exception e) {
			MainRegistry.logger.log(Level.INFO, "[NTM] Fluid Texture not found for "+e.getMessage());
			return 0xFFFFFF;
		}
	}

	public static int getRGBfromARGB(final int pixel){
		return pixel & 0x00ffffff;
	}

	// Drillgon200: Just realized I copied the wrong method. God dang it.
	// It works though. Not sure why, but it works.
	public static long chargeTEFromItems(final IItemHandlerModifiable inventory, final int index, long power, final long maxPower) {
		if(inventory.getStackInSlot(index).getItem() == ModItems.battery_creative)
		{
			return maxPower;
		}
		
		if(inventory.getStackInSlot(index).getItem() == ModItems.fusion_core_infinite)
		{
			return maxPower;
		}
		
		if(inventory.getStackInSlot(index).getItem() instanceof IBatteryItem battery) {

            final long batCharge = battery.getCharge(inventory.getStackInSlot(index));
			final long batRate = battery.getDischargeRate();
			
			//in hHe
			final long toDischarge = Math.min(Math.min((maxPower - power), batRate), batCharge);
			
			battery.dischargeBattery(inventory.getStackInSlot(index), toDischarge);
			power += toDischarge;
		}
		
		return power;
	}

	//not great either but certainly better
	public static long chargeItemsFromTE(final IItemHandlerModifiable inventory, final int index, long power, final long maxPower) {
		if(inventory.getStackInSlot(index).getItem() instanceof IBatteryItem battery) {
            final ItemStack stack = inventory.getStackInSlot(index);
			
			final long batMax = battery.getMaxCharge();
			final long batCharge = battery.getCharge(stack);
			final long batRate = battery.getChargeRate();
			
			//in hHE
			final long toCharge = Math.min(Math.min(power, batRate), batMax - batCharge);
			
			power -= toCharge;
			
			battery.chargeBattery(stack, toCharge);
		}
		
		return power;
	}

	public static boolean isArrayEmpty(final Object[] array) {
		if(array == null)
			return true;
		if(array.length == 0)
			return true;

		boolean flag = true;

		for(int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                flag = false;
                break;
            }
		}

		return flag;
	}

	// Drillgon200: useless method but whatever
	public static ItemStack carefulCopy(final ItemStack stack) {
		if(stack == null)
			return null;
		else
			return stack.copy();
	}
	
	public static EntityPlayer getClosestPlayerForSound(final World world, final double x, final double y, final double z, final double radius) {
		double d4 = -1.0D;
		EntityPlayer entity = null;
		if(world == null) return null;
		for (int i = 0; i < world.loadedEntityList.size(); ++i) {
				final Entity entityplayer1 = world.loadedEntityList.get(i);

				if (entityplayer1.isEntityAlive() && entityplayer1 instanceof EntityPlayer) {
					final double d5 = entityplayer1.getDistanceSq(x, y, z);

					if ((radius < 0.0D || d5 < radius * radius) && (d4 == -1.0D || d5 < d4)) {
						d4 = d5;
						entity = (EntityPlayer)entityplayer1;
					}
			}
		}

		return entity;
	}

	public static EntityHunterChopper getClosestChopperForSound(final World world, final double x, final double y, final double z, final double radius) {
		double d4 = -1.0D;
		EntityHunterChopper entity = null;

		for (int i = 0; i < world.loadedEntityList.size(); ++i) {
				final Entity entityplayer1 = world.loadedEntityList.get(i);

				if (entityplayer1.isEntityAlive() && entityplayer1 instanceof EntityHunterChopper) {
					final double d5 = entityplayer1.getDistanceSq(x, y, z);
					final double d6 = radius;

					if ((radius < 0.0D || d5 < d6 * d6) && (d4 == -1.0D || d5 < d4)) {
						d4 = d5;
						entity = (EntityHunterChopper)entityplayer1;
					}
			}
		}

		return entity;
	}

	public static EntityChopperMine getClosestMineForSound(final World world, final double x, final double y, final double z, final double radius) {
		double d4 = -1.0D;
		EntityChopperMine entity = null;

		for (int i = 0; i < world.loadedEntityList.size(); ++i) {
				final Entity entityplayer1 = world.loadedEntityList.get(i);

				if (entityplayer1.isEntityAlive() && entityplayer1 instanceof EntityChopperMine) {
					final double d5 = entityplayer1.getDistanceSq(x, y, z);
					final double d6 = radius;

					if ((radius < 0.0D || d5 < d6 * d6) && (d4 == -1.0D || d5 < d4)) {
						d4 = d5;
						entity = (EntityChopperMine)entityplayer1;
					}
			}
		}

		return entity;
	}

	public static RayTraceResult rayTrace(final EntityPlayer player, final double length, final float interpolation) {
		Vec3d vec3 = getPosition(interpolation, player);
		vec3 = vec3.add(0D, player.eyeHeight, 0D);
		final Vec3d vec31 = player.getLook(interpolation);
		final Vec3d vec32 = vec3.add(vec31.x * length, vec31.y * length, vec31.z * length);
		return player.world.rayTraceBlocks(vec3, vec32, false, false, true);
	}
	
	public static RayTraceResult rayTrace(final EntityPlayer player, final double length, final float interpolation, final boolean b1, final boolean b2, final boolean b3) {
		Vec3d vec3 = getPosition(interpolation, player);
		vec3 = vec3.add(0D, player.eyeHeight, 0D);
		final Vec3d vec31 = player.getLook(interpolation);
		final Vec3d vec32 = vec3.add(vec31.x * length, vec31.y * length, vec31.z * length);
		return player.world.rayTraceBlocks(vec3, vec32, b1, b2, b3);
	}
	
	public static AxisAlignedBB rotateAABB(final AxisAlignedBB box, final EnumFacing facing){
		switch(facing){
		case NORTH:
			return new AxisAlignedBB(box.minX, box.minY, 1-box.minZ, box.maxX, box.maxY, 1-box.maxZ);
		case SOUTH:
			return box;
		case EAST:
			return new AxisAlignedBB(box.minZ, box.minY, box.minX, box.maxZ, box.maxY, box.maxX);
		case WEST:
			return new AxisAlignedBB(1-box.minZ, box.minY, box.minX, 1-box.maxZ, box.maxY, box.maxX);
		default:
			return box;
		}
	}
	
	public static RayTraceResult rayTraceIncludeEntities(final EntityPlayer player, final double d, final float f) {
		Vec3d vec3 = getPosition(f, player);
		vec3 = vec3.add(0D, player.eyeHeight, 0D);
		final Vec3d vec31 = player.getLook(f);
		final Vec3d vec32 = vec3.add(vec31.x * d, vec31.y * d, vec31.z * d);
		return rayTraceIncludeEntities(player.world, vec3, vec32, player);
	}
	
	public static RayTraceResult rayTraceIncludeEntitiesCustomDirection(final EntityPlayer player, final Vec3d look, final double d, final float f) {
		Vec3d vec3 = getPosition(f, player);
		vec3 = vec3.add(0D, player.eyeHeight, 0D);
		final Vec3d vec32 = vec3.add(look.x * d, look.y * d, look.z * d);
		return rayTraceIncludeEntities(player.world, vec3, vec32, player);
	}
	
	public static Vec3d changeByAngle(final Vec3d oldDir, final float yaw, final float pitch){
		Vec3d dir = new Vec3d(0, 0, 1);
		dir = dir.rotatePitch((float) Math.toRadians(pitch)).rotateYaw((float) Math.toRadians(yaw));
		final Vec3d angles = BobMathUtil.getEulerAngles(oldDir);
		return dir.rotatePitch((float) Math.toRadians(angles.y+90)).rotateYaw((float)Math.toRadians(angles.x));
	}
	
	public static RayTraceResult rayTraceIncludeEntities(final World w, final Vec3d vec3, Vec3d vec32, @Nullable final Entity excluded) {
		RayTraceResult result = w.rayTraceBlocks(vec3, vec32, false, true, true);
		if(result != null)
			vec32 = result.hitVec;
		
		final AxisAlignedBB box = new AxisAlignedBB(vec3.x, vec3.y, vec3.z, vec32.x, vec32.y, vec32.z).grow(1D);
		final List<Entity> ents = w.getEntitiesInAABBexcluding(excluded, box, Predicates.and(EntitySelectors.IS_ALIVE, entity -> entity instanceof EntityLivingBase));
		for(final Entity ent : ents){
			final RayTraceResult test = ent.getEntityBoundingBox().grow(0.3D).calculateIntercept(vec3, vec32);
			if(test != null){
				if(result == null || vec3.squareDistanceTo(result.hitVec) > vec3.squareDistanceTo(test.hitVec)){
					test.typeOfHit = RayTraceResult.Type.ENTITY;
					test.entityHit = ent;
					result = test;
				}
			}
		}
		
		return result;
	}
	
	public static Pair<RayTraceResult, List<Entity>> rayTraceEntitiesOnLine(final EntityPlayer player, final double d, final float f){
		Vec3d vec3 = getPosition(f, player);
		vec3 = vec3.add(0D, player.eyeHeight, 0D);
		final Vec3d vec31 = player.getLook(f);
		Vec3d vec32 = vec3.add(vec31.x * d, vec31.y * d, vec31.z * d);
		final RayTraceResult result = player.world.rayTraceBlocks(vec3, vec32, false, true, true);
		if(result != null)
			vec32 = result.hitVec;
		final AxisAlignedBB box = new AxisAlignedBB(vec3.x, vec3.y, vec3.z, vec32.x, vec32.y, vec32.z).grow(1D);
		final List<Entity> ents = player.world.getEntitiesInAABBexcluding(player, box, Predicates.and(EntitySelectors.IS_ALIVE, entity -> entity instanceof EntityLiving));
		final Iterator<Entity> itr = ents.iterator();
		while(itr.hasNext()){
			final Entity ent = itr.next();
			final AxisAlignedBB entityBox = ent.getEntityBoundingBox().grow(0.1);
			final RayTraceResult entTrace = entityBox.calculateIntercept(vec3, vec32);
			if(entTrace == null || entTrace.typeOfHit == Type.MISS){
				itr.remove();
			}
		}
		return Pair.of(rayTraceIncludeEntities(player, d, f), ents);
	}
	
	public static RayTraceResult rayTraceEntitiesInCone(final EntityPlayer player, final double d, final float f, final float degrees) {
		final double cosDegrees = Math.cos(Math.toRadians(degrees));
		Vec3d vec3 = getPosition(f, player);
		vec3 = vec3.add(0D, player.eyeHeight, 0D);
		final Vec3d vec31 = player.getLook(f);
		final Vec3d vec32 = vec3.add(vec31.x * d, vec31.y * d, vec31.z * d);
		
		RayTraceResult result = player.world.rayTraceBlocks(vec3, vec32, false, true, true);
		double runningDot = Double.MIN_VALUE;
		
		final AxisAlignedBB box = new AxisAlignedBB(vec3.x, vec3.y, vec3.z, vec3.x, vec3.y, vec3.z).grow(1D+d);
		final List<Entity> ents = player.world.getEntitiesInAABBexcluding(player, box, Predicates.and(EntitySelectors.IS_ALIVE, entity -> entity instanceof EntityLiving));
		for(final Entity ent : ents){
			final Vec3d entPos = closestPointOnBB(ent.getEntityBoundingBox(), vec3, vec32);
			final Vec3d relativeEntPos = entPos.subtract(vec3).normalize();
			final double dot = relativeEntPos.dotProduct(vec31);
			
			if(dot > cosDegrees && dot > runningDot && !isObstructed(player.world, vec3.x, vec3.y, vec3.z, ent.posX, ent.posY + ent.getEyeHeight()*0.75, ent.posZ)){
				runningDot = dot;
				result = new RayTraceResult(ent);
				result.hitVec = new Vec3d(ent.posX, ent.posY + ent.getEyeHeight()/2, ent.posZ);
			}
			
		}
		
		return result;
	}
	
	//Drillgon200: Turns out the closest point on a bounding box to a line is a pretty good method for determine if a cone and an AABB intersect.
	//Actually that was a pretty garbage method. Changing it out for a slightly less efficient sphere culling algorithm that only gives false positives.
	//https://bartwronski.com/2017/04/13/cull-that-cone/
	//Idea is that we find the closest point on the cone to the center of the sphere and check if it's inside the sphere.
	public static boolean isBoxCollidingCone(final AxisAlignedBB box, final Vec3d coneStart, final Vec3d coneEnd, final float degrees){
		final Vec3d center = box.getCenter();
		final double radius = center.distanceTo(new Vec3d(box.maxX, box.maxY, box.maxZ));
		final Vec3d V = center.subtract(coneStart);
		final double VlenSq = V.lengthSquared();
		final Vec3d direction = coneEnd.subtract(coneStart);
		final double size = direction.length();
		final double V1len  = V.dotProduct(direction.normalize());
		final double angRad = Math.toRadians(degrees);
		final double distanceClosestPoint = Math.cos(angRad) * Math.sqrt(VlenSq - V1len*V1len) - V1len * Math.sin(angRad);
		 
		final boolean angleCull = distanceClosestPoint > radius;
		final boolean frontCull = V1len >  radius + size;
		final boolean backCull  = V1len < -radius;
		return !(angleCull || frontCull || backCull);
	}
	
	//Drillgon200: Basically the AxisAlignedBB calculateIntercept method except it clamps to edge instead of returning null
	public static Vec3d closestPointOnBB(final AxisAlignedBB box, final Vec3d vecA, final Vec3d vecB){
		
		Vec3d vec3d = collideWithXPlane(box, box.minX, vecA, vecB);
        Vec3d vec3d1 = collideWithXPlane(box, box.maxX, vecA, vecB);

        if (vec3d1 != null && isClosest(vecA, vecB, vec3d, vec3d1))
        {
            vec3d = vec3d1;
        }

        vec3d1 = collideWithYPlane(box, box.minY, vecA, vecB);

        if (vec3d1 != null && isClosest(vecA, vecB, vec3d, vec3d1))
        {
            vec3d = vec3d1;
        }

        vec3d1 = collideWithYPlane(box, box.maxY, vecA, vecB);

        if (vec3d1 != null && isClosest(vecA, vecB, vec3d, vec3d1))
        {
            vec3d = vec3d1;
        }

        vec3d1 = collideWithZPlane(box, box.minZ, vecA, vecB);

        if (vec3d1 != null && isClosest(vecA, vecB, vec3d, vec3d1))
        {
            vec3d = vec3d1;
        }

        vec3d1 = collideWithZPlane(box, box.maxZ, vecA, vecB);

        if (vec3d1 != null && isClosest(vecA, vecB, vec3d, vec3d1))
        {
            vec3d = vec3d1;
        }
		
		return vec3d;
	}
	
	protected static Vec3d collideWithXPlane(final AxisAlignedBB box, final double p_186671_1_, final Vec3d p_186671_3_, final Vec3d p_186671_4_)
    {
        final Vec3d vec3d = getIntermediateWithXValue(p_186671_3_, p_186671_4_, p_186671_1_);
        return clampToBox(box, vec3d);
        //return vec3d != null && box.intersectsWithYZ(vec3d) ? vec3d : null;
    }

	protected static Vec3d collideWithYPlane(final AxisAlignedBB box, final double p_186663_1_, final Vec3d p_186663_3_, final Vec3d p_186663_4_)
    {
        final Vec3d vec3d = getIntermediateWithYValue(p_186663_3_, p_186663_4_, p_186663_1_);
        return clampToBox(box, vec3d);
        //return vec3d != null && box.intersectsWithXZ(vec3d) ? vec3d : null;
    }

	protected static Vec3d collideWithZPlane(final AxisAlignedBB box, final double p_186665_1_, final Vec3d p_186665_3_, final Vec3d p_186665_4_)
    {
        final Vec3d vec3d = getIntermediateWithZValue(p_186665_3_, p_186665_4_, p_186665_1_);
        return clampToBox(box, vec3d);
        //return vec3d != null && box.intersectsWithXY(vec3d) ? vec3d : null;
    }
	
	protected static Vec3d clampToBox(final AxisAlignedBB box, final Vec3d vec)
    {
		return new Vec3d(MathHelper.clamp(vec.x, box.minX, box.maxX), MathHelper.clamp(vec.y, box.minY, box.maxY), MathHelper.clamp(vec.z, box.minZ, box.maxZ));
    }
	
	protected static boolean isClosest(final Vec3d line1, final Vec3d line2, @Nullable final Vec3d p_186661_2_, final Vec3d p_186661_3_)
    {
		if(p_186661_2_ == null)
			return true;
		final double d1 = dist_to_segment_squared(p_186661_3_, line1, line2);
		final double d2 = dist_to_segment_squared(p_186661_2_, line1, line2);
		if(Math.abs(d1-d2) < 0.01)
			return line1.squareDistanceTo(p_186661_3_) < line1.squareDistanceTo(p_186661_2_);
        return d1 < d2;
    }
	
	//Drillgon200: https://stackoverflow.com/questions/849211/shortest-distance-between-a-point-and-a-line-segment
	//Drillgon200: I'm not figuring this out myself.
	protected static double dist_to_segment_squared(final Vec3d point, final Vec3d linePoint1, final Vec3d linePoint2) {
		  final double line_dist = linePoint1.squareDistanceTo(linePoint2);
		  if (line_dist == 0) return point.squareDistanceTo(linePoint1);
		  double t = ((point.x - linePoint1.x) * (linePoint2.x - linePoint1.x) + (point.y - linePoint1.y) * (linePoint2.y - linePoint1.y) + (point.z - linePoint1.z) * (linePoint2.z - linePoint1.z)) / line_dist;
		  t = MathHelper.clamp(t, 0, 1);
		  final Vec3d pointOnLine = new Vec3d(linePoint1.x + t * (linePoint2.x - linePoint1.x), linePoint1.y + t * (linePoint2.y - linePoint1.y), linePoint1.z + t * (linePoint2.z - linePoint1.z));
		  return point.squareDistanceTo(pointOnLine);
	}
	
	/**
     * Returns a new vector with x value equal to the second parameter, along the line between this vector and the
     * passed in vector, or null if not possible.
     */
    @Nullable
    public static Vec3d getIntermediateWithXValue(final Vec3d vec1, final Vec3d vec, final double x)
    {
        final double d0 = vec.x - vec1.x;
        final double d1 = vec.y - vec1.y;
        final double d2 = vec.z - vec1.z;

        if (d0 * d0 < 1.0000000116860974E-7D)
        {
            return vec;
        }
        else
        {
            final double d3 = (x - vec1.x) / d0;
            if(d3 < 0){
            	return new Vec3d(x, vec.y, vec.z);
            } else if(d3 > 1){
            	return new Vec3d(x, vec1.y, vec1.z);
            } else {
            	return new Vec3d(vec1.x + d0 * d3, vec1.y + d1 * d3, vec1.z + d2 * d3);
            }
            //return d3 >= 0.0D && d3 <= 1.0D ? new Vec3d(vec1.x + d0 * d3, vec1.y + d1 * d3, vec1.z + d2 * d3) : null;
        }
    }

    /**
     * Returns a new vector with y value equal to the second parameter, along the line between this vector and the
     * passed in vector, or null if not possible.
     */
    @Nullable
    public static Vec3d getIntermediateWithYValue(final Vec3d vec1, final Vec3d vec, final double y)
    {
        final double d0 = vec.x - vec1.x;
        final double d1 = vec.y - vec1.y;
        final double d2 = vec.z - vec1.z;

        if (d1 * d1 < 1.0000000116860974E-7D)
        {
            return vec;
        }
        else
        {
            final double d3 = (y - vec1.y) / d1;
            if(d3 < 0){
            	return new Vec3d(vec.x, y, vec.z);
            } else if(d3 > 1){
            	return new Vec3d(vec1.x, y, vec1.z);
            } else {
            	return new Vec3d(vec1.x + d0 * d3, vec1.y + d1 * d3, vec1.z + d2 * d3);
            }
            //return d3 >= 0.0D && d3 <= 1.0D ? new Vec3d(vec1.x + d0 * d3, vec1.y + d1 * d3, vec1.z + d2 * d3) : null;
        }
    }

    /**
     * Returns a new vector with z value equal to the second parameter, along the line between this vector and the
     * passed in vector, or null if not possible.
     */
    @Nullable
    public static Vec3d getIntermediateWithZValue(final Vec3d vec1, final Vec3d vec, final double z)
    {
        final double d0 = vec.x - vec1.x;
        final double d1 = vec.y - vec1.y;
        final double d2 = vec.z - vec1.z;

        if (d2 * d2 < 1.0000000116860974E-7D)
        {
            return vec;
        }
        else
        {
            final double d3 = (z - vec1.z) / d2;
            if(d3 < 0){
            	return new Vec3d(vec.x, vec.y, z);
            } else if(d3 > 1){
            	return new Vec3d(vec1.x, vec1.y, z);
            } else {
            	return new Vec3d(vec1.x + d0 * d3, vec1.y + d1 * d3, vec1.z + d2 * d3);
            }
            //return d3 >= 0.0D && d3 <= 1.0D ? new Vec3d(vec1.x + d0 * d3, vec1.y + d1 * d3, vec1.z + d2 * d3) : null;
        }
    }
    
    public static Vec3d getEuler(final Vec3d vec){
    	final double yaw = Math.toDegrees(Math.atan2(vec.x, vec.z));
		final double sqrt = MathHelper.sqrt(vec.x * vec.x + vec.z * vec.z);
		final double pitch = Math.toDegrees(Math.atan2(vec.y, sqrt));
		return new Vec3d(yaw, pitch, 0);
    }
    
    //Drillgon200: https://thebookofshaders.com/glossary/?search=smoothstep
    public static double smoothstep(double t, final double edge0, final double edge1){
    	t = MathHelper.clamp((t - edge0) / (edge1 - edge0), 0.0, 1.0);
        return t * t * (3.0 - 2.0 * t);	
    }
    public static float smoothstep(float t, final float edge0, final float edge1){
    	t = MathHelper.clamp((t - edge0) / (edge1 - edge0), 0.0F, 1.0F);
        return t * t * (3.0F - 2.0F * t);	
    }
	
	public static Vec3d getPosition(final float interpolation, final EntityPlayer player) {
		if(interpolation == 1.0F) {
			return new Vec3d(player.posX, player.posY + (player.getEyeHeight() - player.getDefaultEyeHeight()), player.posZ);
		} else {
			final double d0 = player.prevPosX + (player.posX - player.prevPosX) * interpolation;
			final double d1 = player.prevPosY + (player.posY - player.prevPosY) * interpolation + (player.getEyeHeight() - player.getDefaultEyeHeight());
			final double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * interpolation;
			return new Vec3d(d0, d1, d2);
		}
	}

public static boolean canConnect(final IBlockAccess world, final BlockPos pos, final ForgeDirection dir /* cable's connecting side */) {
		
		if(world instanceof World){
			if(((World)world).isOutsideBuildHeight(pos))
				return false;
		} else {
			if(pos.getY() < 0 || pos.getY() > 255)
				return false;
		}
		
		final Block b = world.getBlockState(pos).getBlock();
		
		if(b instanceof IEnergyConnectorBlock con) {

            if(con.canConnect(world, pos, dir.getOpposite() /* machine's connecting side */))
				return true;
		}
		
		final TileEntity te = world.getTileEntity(pos);
		
		if(te instanceof IEnergyConnector con) {

            return con.canConnect(dir.getOpposite() /* machine's connecting side */);
		}
		
		return false;
	}

	//Alcater: Finally this shit is no more

	//TODO: jesus christ
	// Flut-Füll gesteuerter Energieübertragungsalgorithmus
	// Flood fill controlled energy transmission algorithm
	// public static void ffgeua(MutableBlockPos pos, boolean newTact, ISource that, World worldObj) {
		
	// 	/*
	// 	 * This here smoldering crater is all that remains from the old energy system.
	// 	 * In loving memory, 2019-2023.
	// 	 * You won't be missed.
	// 	 */
	// }

	/**
	 * Itemstack equality method except it accounts for possible null stacks and
	 * doesn't check if empty
	 */
	public static boolean areItemsEqual(final ItemStack stackA, final ItemStack stackB) {
		if(stackA == null & stackB == null)
			return true;
		else if((stackA == null && stackB != null) || (stackA != null && stackB == null))
			return false;
		else
			return stackA.getMetadata() == stackB.getMetadata() && stackA.getItem() == stackB.getItem();
	}

	public static boolean hasInventoryItem(final InventoryPlayer inventory, final ItemStack stack) {
		for(int i = 0; i < inventory.getSizeInventory(); i++) {
			if(ItemStackUtil.isSameMetaItem(inventory.getStackInSlot(i), stack)) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasInventoryItem(final InventoryPlayer inventory, final Item item) {
		return hasInventoryItem(inventory, ItemStackUtil.itemStackFrom(item));
	}
	
	public static boolean hasInventoryOreDict(final InventoryPlayer inventory, final String name) {
		final int oreId = OreDictionary.getOreID(name);
		for(int i = 0; i < inventory.getSizeInventory(); i++) {
			final ItemStack stack = inventory.getStackInSlot(i);
			if(stack.isEmpty())
				continue;
			final int[] ids = OreDictionary.getOreIDs(stack);
			for(final int id : ids){
				if(id == oreId)
					return true;
			}
		}
		return false;
	}
	
	public static int countInventoryItem(final InventoryPlayer inventory, final Item ammo) {
		int count = 0;
		for(int i = 0; i < inventory.getSizeInventory(); i++) {
			final ItemStack stack = inventory.getStackInSlot(i);
			if(stack.getItem() == ammo) {
				count += stack.getCount();
			}
		}
		return count;
	}

	public static void consumeInventoryItem(final InventoryPlayer inventory, final Item ammo) {
		for(int i = 0; i < inventory.getSizeInventory(); i++) {
			final ItemStack stack = inventory.getStackInSlot(i);
			if(stack.getItem() == ammo && !stack.isEmpty()) {
				stack.shrink(1);
				inventory.setInventorySlotContents(i, stack.copy());
				return;
			}
		}
	}

	//////  //////  //////  //////  //////  ////        //////  //////  //////
	//      //  //  //        //    //      //  //      //      //      //    
	////    //////  /////     //    ////    ////        ////    //  //  //  //
	//      //  //     //     //    //      //  //      //      //  //  //  //
	//////  //  //  /////     //    //////  //  //      //////  //////  //////
	//Alcater: Huh thats interesing... You can hide from the chopper as long as you are outside 80% of its radius??
	public static EntityLivingBase getClosestEntityForChopper(final World world, final double x, final double y, final double z, final double radius) {
		double d4 = -1.0D;
		EntityLivingBase entityplayer = null;

		for (int i = 0; i < world.loadedEntityList.size(); ++i) {
			if (world.loadedEntityList.get(i) instanceof EntityLivingBase entityplayer1 && !(world.loadedEntityList.get(i) instanceof EntityHunterChopper)) {

                if (entityplayer1.isEntityAlive() && !(entityplayer1 instanceof EntityPlayer && ((EntityPlayer)entityplayer1).capabilities.disableDamage)) {
					final double d5 = entityplayer1.getDistanceSq(x, y, z);
					double d6 = radius;

					if (entityplayer1.isSneaking()) {
						d6 = radius * 0.800000011920929D;
					}

					if ((radius < 0.0D || d5 < d6 * d6) && (d4 == -1.0D || d4 > d5)) {
						d4 = d5;
						entityplayer = entityplayer1;
					}
				}
			}
		}

		return entityplayer;
	}
	
	//Drillgon200: Loot tables? I don't have time for that!
	public static void generateChestContents(final Random p_76293_0_, final WeightedRandomChestContentFrom1710[] p_76293_1_, final ICapabilityProvider p_76293_2_, final int p_76293_3_)
    {
		if(p_76293_2_.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)){
			final IItemHandler test = p_76293_2_.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			if(test instanceof IItemHandlerModifiable inventory){

                for (int j = 0; j < p_76293_3_; ++j)
		        {
					final WeightedRandomChestContentFrom1710 weightedrandomchestcontent = WeightedRandom.getRandomItem(p_76293_0_, Arrays.asList(p_76293_1_));
		            final ItemStack[] stacks = weightedrandomchestcontent.generateChestContent(p_76293_0_, inventory);

		            for (final ItemStack item : stacks)
		            {
		            	inventory.setStackInSlot(p_76293_0_.nextInt(inventory.getSlots()), item);
		            }
		        }
			}
		}
        
    }
	
	public static Block getRandomConcrete() {
		final int i = rand.nextInt(100);

		if(i < 5)
			return ModBlocks.brick_concrete_broken;
		if(i < 20)
			return ModBlocks.brick_concrete_cracked;
		if(i < 50)
			return ModBlocks.brick_concrete_mossy;
		
		return ModBlocks.brick_concrete;
	}
	
	public static void placeDoorWithoutCheck(final World worldIn, final BlockPos pos, final EnumFacing facing, final Block door, final boolean isRightHinge)
    {
        final BlockPos blockpos2 = pos.up();
        final boolean flag2 = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(blockpos2);
        final IBlockState iblockstate = door.getDefaultState().withProperty(BlockDoor.FACING, facing).withProperty(BlockDoor.HINGE, isRightHinge ? BlockDoor.EnumHingePosition.RIGHT : BlockDoor.EnumHingePosition.LEFT).withProperty(BlockDoor.POWERED, Boolean.valueOf(flag2)).withProperty(BlockDoor.OPEN, Boolean.valueOf(flag2));
        worldIn.setBlockState(pos, iblockstate.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER), 2);
        worldIn.setBlockState(blockpos2, iblockstate.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER), 2);
        worldIn.notifyNeighborsOfStateChange(pos, door, false);
        worldIn.notifyNeighborsOfStateChange(blockpos2, door, false);
    }
	
	public static boolean areItemStacksEqualIgnoreCount(final ItemStack a, final ItemStack b){
		if (a.isEmpty() && b.isEmpty())
        {
            return true;
        }
        else
        {
            if(!a.isEmpty() && !b.isEmpty()){

                if (a.getItem() != b.getItem())
                {
                    return false;
                }
                else if (a.getMetadata() != b.getMetadata())
                {
                    return false;
                }
                else
                {
                    return (a.getTagCompound() == null || a.getTagCompound().equals(b.getTagCompound())) && a.areCapsCompatible(b);
                }
            }
        }
		return false;
	}
	
	/**
	 * Same as ItemStack.areItemStacksEqual, except the second one's tag only has to contain all the first one's tag, rather than being exactly equal.
	 */
	public static boolean areItemStacksCompatible(final ItemStack base, final ItemStack toTest, final boolean shouldCompareSize){
		if (base.isEmpty() && toTest.isEmpty())
        {
            return true;
        }
        else
        {
            if(!base.isEmpty() && !toTest.isEmpty()){

            	if(shouldCompareSize && base.getCount() != toTest.getCount()){
            		return false;
            	} 
            	else if (base.getItem() != toTest.getItem())
                {
                    return false;
                }
                else if (base.getMetadata() != toTest.getMetadata() && !(base.getMetadata() == OreDictionary.WILDCARD_VALUE))
                {
                    return false;
                }
                else if (base.getTagCompound() == null && toTest.getTagCompound() != null)
                {
                    return false;
                }
                else
                {
                    return (base.getTagCompound() == null || tagContainsOther(base.getTagCompound(), toTest.getTagCompound())) && base.areCapsCompatible(toTest);
                }
            }
        }
		return false;
	}
	
	public static boolean areItemStacksCompatible(final ItemStack base, final ItemStack toTest){
		return areItemStacksCompatible(base, toTest, true);
	}
	
	/**
	 * Returns true if the second compound contains all the tags and values of the first one, but it can have more. This helps with intermod compatibility
	 */
	public static boolean tagContainsOther(final NBTTagCompound tester, final NBTTagCompound container){
		if(tester == null && container == null){
			return true;
		} else if (tester == null && container != null) {
			return true;
		} else if (tester != null && container == null) {
		} else {
			for(final String s : tester.getKeySet()){
				if(!container.hasKey(s)){
					return false;
				} else {
					final NBTBase nbt1 = tester.getTag(s);
					final NBTBase nbt2 = container.getTag(s);
					if(nbt1 instanceof NBTTagCompound && nbt2 instanceof NBTTagCompound){
						if(!tagContainsOther((NBTTagCompound)nbt1, (NBTTagCompound) nbt2))
							return false;
					} else {
						if(!nbt1.equals(nbt2))
							return false;
					}
				}
			}
		}
		return true;
	}
	
	public static List<int[]> getBlockPosInPath(final BlockPos pos, final int length, final Vec3 vec0) {
		final List<int[]> list = new ArrayList<int[]>();
		
		for(int i = 0; i <= length; i++) {
			list.add(new int[] { (int)(pos.getX() + (vec0.xCoord * i)), pos.getY(), (int)(pos.getZ() + (vec0.zCoord * i)), i });
		}
		
		return list;
	}

	public static List<ItemStack> copyItemStackList(final List<ItemStack> inputs) {
		final List<ItemStack> list = new ArrayList<ItemStack>();
		inputs.forEach(stack -> {list.add(stack.copy());});
		return list;
	}
	
	public static List<List<ItemStack>> copyItemStackListList(final List<List<ItemStack>> inputs) {
		final List<List<ItemStack>> list = new ArrayList<List<ItemStack>>(inputs.size());
		inputs.forEach(list2 -> {
			final List<ItemStack> newList = new ArrayList<>(list2.size());
			list2.forEach(stack -> {newList.add(stack.copy());});
			list.add(newList);
			});
		return list;
	}
	
	public static IEntityHbmProps getEntRadCap(final Entity e){
		if(e.hasCapability(EntityHbmPropsProvider.ENT_HBM_PROPS_CAP, null))
			return e.getCapability(EntityHbmPropsProvider.ENT_HBM_PROPS_CAP, null);
		return EntityHbmPropsProvider.DUMMY;
	}

	public static void addToInventoryOrDrop(final EntityPlayer player, final ItemStack stack) {
		if(!player.inventory.addItemStackToInventory(stack)){
			player.dropItem(stack, false);
		}
	}

	public static Vec3d normalFromRayTrace(final RayTraceResult r) {
		final Vec3i n = r.sideHit.getDirectionVec();
		return new Vec3d(n.getX(), n.getY(), n.getZ());
	}
	
	
	public static Explosion explosionDummy(final World w, final double x, final double y, final double z){
		return new Explosion(w, null, x, y, z, 1000, false, false);
	}
}
