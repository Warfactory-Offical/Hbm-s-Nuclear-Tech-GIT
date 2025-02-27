package com.hbm.main;

import com.google.common.collect.Multimap;
import com.hbm.blocks.ModBlocks;
import com.hbm.capability.HbmCapability;
import com.hbm.capability.HbmCapability.IHBMData;
import com.hbm.capability.HbmLivingCapability;
import com.hbm.capability.HbmLivingProps;
import com.hbm.config.CompatibilityConfig;
import com.hbm.config.GeneralConfig;
import com.hbm.config.RadiationConfig;
import com.hbm.entity.logic.IChunkLoader;
import com.hbm.entity.mob.EntityCyberCrab;
import com.hbm.entity.mob.EntityTaintedCreeper;
import com.hbm.entity.projectile.EntityBurningFOEQ;
import com.hbm.forgefluid.FFPipeNetwork;
import com.hbm.handler.*;
import com.hbm.handler.HbmKeybinds.EnumKeybind;
import com.hbm.hazard_old.HazardSystem;
import com.hbm.interfaces.IBomb;
import com.hbm.inventory.AssemblerRecipes;
import com.hbm.items.IEquipReceiver;
import com.hbm.items.ModItems;
import com.hbm.items.armor.ItemArmorMod;
import com.hbm.items.armor.ItemModRevive;
import com.hbm.items.armor.ItemModShackles;
import com.hbm.items.gear.ArmorFSB;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.items.special.ItemHot;
import com.hbm.items.tool.ItemDigammaDiagnostic;
import com.hbm.items.weapon.ItemGunBase;
import com.hbm.lib.*;
import com.hbm.packet.*;
import com.hbm.particle.bullet_hit.EntityHitDataHandler;
import com.hbm.potion.HbmDetox;
import com.hbm.tileentity.machine.rbmk.RBMKDials;
import com.hbm.tileentity.network.RTTYSystem;
import com.hbm.util.ArmorRegistry;
import com.hbm.util.ArmorRegistry.HazardClass;
import com.hbm.util.EnchantmentUtil;
import com.hbm.util.EntityDamageUtil;
import com.hbm.util.ItemStackUtil;
import com.hbm.world.generator.TimedGenerator;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChanceWithLooting;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.*;
import net.minecraftforge.event.entity.EntityEvent.EnteringChunk;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.registries.DataSerializerEntry;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.Map.Entry;


public class ModEventHandler {

	public static final int HAZARD_POLL_RATE = RadiationConfig.hazardRate;
	public static final ResourceLocation ENT_HBM_PROP_ID = new ResourceLocation(RefStrings.MODID, "HBMLIVINGPROPS");
	public static final ResourceLocation DATA_LOC = new ResourceLocation(RefStrings.MODID, "HBMDATA");

	public static boolean showMessage = true;
	public static Random rand = new Random();


	@SubscribeEvent
	public void soundRegistering(final RegistryEvent.Register<SoundEvent> evt) {

		for(final SoundEvent e : HBMSoundHandler.ALL_SOUNDS) {
			evt.getRegistry().register(e);
		}
	}

	@SubscribeEvent
	public void attachRadCap(final AttachCapabilitiesEvent<Entity> e) {
		if(e.getObject() instanceof EntityLivingBase)
			e.addCapability(ENT_HBM_PROP_ID, new HbmLivingCapability.EntityHbmPropsProvider());
		if(e.getObject() instanceof EntityPlayer){
			e.addCapability(DATA_LOC, new HbmCapability.HBMDataProvider());
		}
	}

	@SubscribeEvent
	public void worldUnload(final WorldEvent.Unload e) {
		final Iterator<FFPipeNetwork> itr = MainRegistry.allPipeNetworks.iterator();
		while(itr.hasNext()) {
			final FFPipeNetwork net = itr.next();
			if(net.getNetworkWorld() == e.getWorld()) {
				net.destroySoft();
				itr.remove();
			}
		}
	}

	@SubscribeEvent
	public void potionCheck(final PotionApplicableEvent e) {
		if(HbmDetox.isBlacklisted(e.getPotionEffect().getPotion()) && ArmorUtil.checkForHazmat(e.getEntityLiving()) && ArmorRegistry.hasProtection(e.getEntityLiving(), EntityEquipmentSlot.HEAD, HazardClass.BACTERIA)){
			e.setResult(Result.DENY);
			ArmorUtil.damageGasMaskFilter(e.getEntityLiving(), 10);
		}
	}

	@SubscribeEvent
	public void enteringChunk(final EnteringChunk evt) {
		if(evt.getEntity() instanceof IChunkLoader) {
			((IChunkLoader) evt.getEntity()).loadNeighboringChunks(evt.getNewChunkX(), evt.getNewChunkZ());
		}
	}

	@SubscribeEvent
	public void onItemToss(final ItemTossEvent event){
		final ItemStack yeet = event.getEntityItem().getItem();

		if(yeet.getItem() instanceof ItemArmor && ArmorModHandler.hasMods(yeet)) {

			final ItemStack[] mods = ArmorModHandler.pryMods(yeet);
			final ItemStack cladding = mods[ArmorModHandler.cladding];

			if(cladding != null && cladding.getItem() == ModItems.cladding_obsidian) {
				event.getEntity().setEntityInvulnerable(true);
			}
		}

		if(yeet.getItem() == ModItems.bismuth_tool) {
			event.getEntity().setEntityInvulnerable(true);
		}
	}

	@SubscribeEvent
	public void lootTableLoad(final LootTableLoadEvent e){
		//Drillgon200: Yeah we're doing this in code. Screw minecraft json.
		if(CompatibilityConfig.modLoot){
			addWeightedRandomToLootTable(e, LootTableList.CHESTS_VILLAGE_BLACKSMITH, new WeightedRandomChestContentFrom1710(ItemStackUtil.itemStackFrom(ModItems.armor_polish), 1, 1, 3));
			addWeightedRandomToLootTable(e, LootTableList.CHESTS_VILLAGE_BLACKSMITH, new WeightedRandomChestContentFrom1710(ItemStackUtil.itemStackFrom(ModItems.bathwater), 1, 1, 1));
			addWeightedRandomToLootTable(e, LootTableList.CHESTS_ABANDONED_MINESHAFT, new WeightedRandomChestContentFrom1710(ItemStackUtil.itemStackFrom(ModItems.bathwater), 1, 1, 1));
			addWeightedRandomToLootTable(e, LootTableList.CHESTS_ABANDONED_MINESHAFT, new WeightedRandomChestContentFrom1710(ItemStackUtil.itemStackFrom(ModItems.serum), 1, 1, 5));
			addWeightedRandomToLootTable(e, LootTableList.CHESTS_SIMPLE_DUNGEON, new WeightedRandomChestContentFrom1710(ItemStackUtil.itemStackFrom(ModItems.heart_piece), 1, 1, 1));
			addWeightedRandomToLootTable(e, LootTableList.CHESTS_DESERT_PYRAMID, new WeightedRandomChestContentFrom1710(ItemStackUtil.itemStackFrom(ModItems.heart_piece), 1, 1, 1));
			addWeightedRandomToLootTable(e, LootTableList.CHESTS_JUNGLE_TEMPLE, new WeightedRandomChestContentFrom1710(ItemStackUtil.itemStackFrom(ModItems.heart_piece), 1, 1, 1));
			addWeightedRandomToLootTable(e, LootTableList.CHESTS_SIMPLE_DUNGEON, new WeightedRandomChestContentFrom1710(ItemStackUtil.itemStackFrom(ModItems.scrumpy), 1, 1, 1));
			addWeightedRandomToLootTable(e, LootTableList.CHESTS_DESERT_PYRAMID, new WeightedRandomChestContentFrom1710(ItemStackUtil.itemStackFrom(ModItems.scrumpy), 1, 1, 1));
		}
	}

	private void addWeightedRandomToLootTable(final LootTableLoadEvent e, final ResourceLocation loc, final WeightedRandomChestContentFrom1710 content){
		if(e.getName().equals(loc)){
			final LootCondition[] conds = new LootCondition[0];
			final LootFunction[] funcs = new LootFunction[1];
			funcs[0] = new LootFunction(conds){
				@Override
				public ItemStack apply(final ItemStack stack, final Random rand, final LootContext context){
					final ItemStack sta = content.theItemId.copy();
					sta.setCount(content.theMinimumChanceToGenerateItem + rand.nextInt(content.theMaximumChanceToGenerateItem - content.theMinimumChanceToGenerateItem + 1));
					return sta;
				}
			};
			final LootEntry entry = new LootEntryItem(content.theItemId.getItem(), content.itemWeight, 1, funcs, conds, content.theItemId.getTranslationKey() + "_loot");
			final LootPool pool = new LootPool(new LootEntry[]{entry}, new LootCondition[]{new RandomChanceWithLooting(0.25F, 0.1F)}, new RandomValueRange(1), new RandomValueRange(0), content.theItemId.getTranslationKey() + "_loot");
			e.getTable().addPool(pool);
		}
	}

	/// SMELTING ///
	@SubscribeEvent
	public void itemSmelted(final PlayerEvent.ItemSmeltedEvent event) {
		if (event.player.world.isRemote) return;

		final ItemStack smeltedItem = event.smelting;
		final int randomChance = 64;

		// Check for specific smelting results and handle rewards
		if (ItemStackUtil.isSameMetaItem(smeltedItem, Items.IRON_INGOT) || ItemStackUtil.isSameMetaItem(smeltedItem, ModItems.ingot.getItemStack(MaterialMineral.URANIUM))) {
			final Item rewardItem = ItemStackUtil.isSameMetaItem(smeltedItem, Items.IRON_INGOT) ? ModItems.lodestone : ModItems.quartz_plutonium;

			if (event.player.getRNG().nextInt(randomChance) == 0) {
				giveOrDropItem(event.player, ItemStackUtil.itemStackFrom(rewardItem));
			}
		}
	}

	// Utility method to handle giving or dropping the reward item
	private void giveOrDropItem(final EntityPlayer player, final ItemStack reward) {
		if (!player.inventory.addItemStackToInventory(reward)) {
			player.dropItem(reward, false);
		} else {
			player.inventoryContainer.detectAndSendChanges();
		}
	}
	///MOB SPAWNING
	public boolean canWear(final Entity entity){
		return entity instanceof EntityZombie || entity instanceof EntitySkeleton || entity instanceof EntityVillager || entity instanceof EntityIronGolem;
	}

	@SubscribeEvent
	public void mobSpawn(final LivingSpawnEvent.SpecialSpawn event) {
		if(CompatibilityConfig.mobGear){
			final EntityLivingBase entity = event.getEntityLiving();
			final World world = event.getWorld();

			if(entity instanceof EntityLiving mob && canWear(entity)) {
				final int randomArmorNumber = rand.nextInt(2<<16);
				final int randomHandNumber = rand.nextInt(256);
                final boolean hasMainHand = !mob.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).isEmpty();
				final boolean hasOffHand = !mob.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).isEmpty();
				final boolean hasHat = !mob.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty();
				final boolean hasChest = !mob.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty();
				final boolean hasLegs = !mob.getItemStackFromSlot(EntityEquipmentSlot.LEGS).isEmpty();
				final boolean hasFeet = !mob.getItemStackFromSlot(EntityEquipmentSlot.FEET).isEmpty();

				if(!hasHat){
					if(rand.nextInt(64) == 0)
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStackUtil.itemStackFrom(ModItems.gas_mask_m65, 1, world.rand.nextInt(100)));
					if(rand.nextInt(128) == 0)
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStackUtil.itemStackFrom(ModItems.gas_mask, 1, world.rand.nextInt(100)));
					if(rand.nextInt(256) == 0)
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStackUtil.itemStackFrom(ModItems.mask_of_infamy, 1, world.rand.nextInt(100)));
				}
				if(!(hasHat || hasChest || hasLegs || hasFeet)){
					if(randomArmorNumber < 2){ //1:32768
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStackUtil.itemStackFrom(ModItems.dns_helmet, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.CHEST, ItemStackUtil.itemStackFrom(ModItems.dns_plate, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.LEGS, ItemStackUtil.itemStackFrom(ModItems.dns_legs, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.FEET, ItemStackUtil.itemStackFrom(ModItems.dns_boots, 1));
					}
					else if(randomArmorNumber < 2<<6){ //1:1024
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStackUtil.itemStackFrom(ModItems.rpa_helmet, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.CHEST, ItemStackUtil.itemStackFrom(ModItems.rpa_plate, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.LEGS, ItemStackUtil.itemStackFrom(ModItems.rpa_legs, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.FEET, ItemStackUtil.itemStackFrom(ModItems.rpa_boots, 1));
					}

					else if(randomArmorNumber < 2<<8){ //1:256
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStackUtil.itemStackFrom(ModItems.ajr_helmet, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.CHEST, ItemStackUtil.itemStackFrom(ModItems.ajr_plate, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.LEGS, ItemStackUtil.itemStackFrom(ModItems.ajr_legs, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.FEET, ItemStackUtil.itemStackFrom(ModItems.ajr_boots, 1));
					}

					else if(randomArmorNumber < 2<<10){ //1:64
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStackUtil.itemStackFrom(ModItems.t45_helmet, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.CHEST, ItemStackUtil.itemStackFrom(ModItems.t45_plate, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.LEGS, ItemStackUtil.itemStackFrom(ModItems.t45_legs, 1));
						entity.setItemStackToSlot(EntityEquipmentSlot.FEET, ItemStackUtil.itemStackFrom(ModItems.t45_boots, 1));
					}

					else if(randomArmorNumber < 2<<11){ //1:32
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStackUtil.itemStackFrom(ModItems.hazmat_helmet, 1, world.rand.nextInt(ModItems.hazmat_helmet.getMaxDamage(ItemStack.EMPTY))));
						entity.setItemStackToSlot(EntityEquipmentSlot.CHEST, ItemStackUtil.itemStackFrom(ModItems.hazmat_plate, 1, world.rand.nextInt(ModItems.hazmat_helmet.getMaxDamage(ItemStack.EMPTY))));
						entity.setItemStackToSlot(EntityEquipmentSlot.LEGS, ItemStackUtil.itemStackFrom(ModItems.hazmat_legs, 1, world.rand.nextInt(ModItems.hazmat_helmet.getMaxDamage(ItemStack.EMPTY))));
						entity.setItemStackToSlot(EntityEquipmentSlot.FEET, ItemStackUtil.itemStackFrom(ModItems.hazmat_boots, 1, world.rand.nextInt(ModItems.hazmat_helmet.getMaxDamage(ItemStack.EMPTY))));
					}

					else if(randomArmorNumber < 2<<12){ //1:16
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStackUtil.itemStackFrom(ModItems.titanium_helmet, 1, world.rand.nextInt(ModItems.titanium_helmet.getMaxDamage(ItemStack.EMPTY))));
						entity.setItemStackToSlot(EntityEquipmentSlot.CHEST, ItemStackUtil.itemStackFrom(ModItems.titanium_plate, 1, world.rand.nextInt(ModItems.titanium_plate.getMaxDamage(ItemStack.EMPTY))));
						entity.setItemStackToSlot(EntityEquipmentSlot.LEGS, ItemStackUtil.itemStackFrom(ModItems.titanium_legs, 1, world.rand.nextInt(ModItems.titanium_legs.getMaxDamage(ItemStack.EMPTY))));
						entity.setItemStackToSlot(EntityEquipmentSlot.FEET, ItemStackUtil.itemStackFrom(ModItems.titanium_boots, 1, world.rand.nextInt(ModItems.titanium_boots.getMaxDamage(ItemStack.EMPTY))));
					}

					else if(randomArmorNumber < 2<<13){ //1:8
						entity.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStackUtil.itemStackFrom(ModItems.steel_helmet, 1, world.rand.nextInt(ModItems.steel_helmet.getMaxDamage(ItemStack.EMPTY))));
						entity.setItemStackToSlot(EntityEquipmentSlot.CHEST, ItemStackUtil.itemStackFrom(ModItems.steel_plate, 1, world.rand.nextInt(ModItems.steel_plate.getMaxDamage(ItemStack.EMPTY))));
						entity.setItemStackToSlot(EntityEquipmentSlot.LEGS, ItemStackUtil.itemStackFrom(ModItems.steel_legs, 1, world.rand.nextInt(ModItems.steel_legs.getMaxDamage(ItemStack.EMPTY))));
						entity.setItemStackToSlot(EntityEquipmentSlot.FEET, ItemStackUtil.itemStackFrom(ModItems.steel_boots, 1, world.rand.nextInt(ModItems.steel_boots.getMaxDamage(ItemStack.EMPTY))));
					}
				}

				if(!hasMainHand){
					if(randomHandNumber == 0)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStackUtil.itemStackFrom(ModItems.pipe_lead, 1, world.rand.nextInt(100)));
					else if(randomHandNumber == 1)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStackUtil.itemStackFrom(ModItems.reer_graar, 1, world.rand.nextInt(100)));
					else if(randomHandNumber == 2)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStackUtil.itemStackFrom(ModItems.pipe_rusty, 1, world.rand.nextInt(100)));
					else if(randomHandNumber == 3)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStackUtil.itemStackFrom(ModItems.crowbar, 1, world.rand.nextInt(100)));
					else if(randomHandNumber == 4)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStackUtil.itemStackFrom(ModItems.steel_pickaxe, 1, world.rand.nextInt(300)));
					else if(randomHandNumber == 5)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStackUtil.itemStackFrom(ModItems.bat, 1, world.rand.nextInt(300)));
					else if(randomHandNumber == 6)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStackUtil.itemStackFrom(ModItems.bat_nail, 1, world.rand.nextInt(300)));
					else if(randomHandNumber == 7)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStackUtil.itemStackFrom(ModItems.golf_club, 1, world.rand.nextInt(300)));
					else if(randomHandNumber == 8)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStackUtil.itemStackFrom(ModItems.titanium_sword, 1, world.rand.nextInt(300)));
					else if(randomHandNumber == 9)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStackUtil.itemStackFrom(ModItems.steel_sword, 1, world.rand.nextInt(300)));
					else if(randomHandNumber == 10)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStackUtil.itemStackFrom(ModItems.stopsign));
					else if(randomHandNumber == 11)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStackUtil.itemStackFrom(ModItems.sopsign));
					else if(randomHandNumber == 12)
						entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStackUtil.itemStackFrom(ModItems.chernobylsign));
				}

				if(!hasOffHand){
					if(rand.nextInt(128) == 0)
						entity.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, ItemStackUtil.itemStackFrom(ModItems.geiger_counter, 1));
				}
			}
		}
	}

	private static final Set<String> hashes = new HashSet();

	static {
		hashes.add("41de5c372b0589bbdb80571e87efa95ea9e34b0d74c6005b8eab495b7afd9994");
		hashes.add("31da6223a100ed348ceb3254ceab67c9cc102cb2a04ac24de0df3ef3479b1036");
	}

	@SubscribeEvent
	public void onClickSign(final PlayerInteractEvent event) {

		final BlockPos pos = event.getPos();
		final World world = event.getWorld();

		if(!world.isRemote && world.getBlockState(pos).getBlock() == Blocks.STANDING_SIGN) {

			final TileEntitySign sign = (TileEntitySign) world.getTileEntity(pos);

			final String result = smoosh(sign.signText[0].getUnformattedText(), sign.signText[1].getUnformattedText(), sign.signText[2].getUnformattedText(), sign.signText[3].getUnformattedText());
			//System.out.println(result);

			if(hashes.contains(result)){
				world.destroyBlock(pos, false);
				final EntityItem entityitem = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), ItemStackUtil.itemStackFrom(ModItems.bobmazon_hidden));
				entityitem.setPickupDelay(10);
				world.spawnEntity(entityitem);
			}
		}

	}

	private String smoosh(final String s1, final String s2, final String s3, final String s4) {

		final Random rand = new Random();
		String s = "";

		final byte[] b1 = s1.getBytes();
		final byte[] b2 = s2.getBytes();
		final byte[] b3 = s3.getBytes();
		final byte[] b4 = s4.getBytes();

		if(b1.length == 0 || b2.length == 0 || b3.length == 0 || b4.length == 0)
			return "";

		s += s1;
		rand.setSeed(b1[0]);
		s += rand.nextInt(0xffffff);

		s += s2;
		rand.setSeed(rand.nextInt(0xffffff) + b2[0]);
		rand.setSeed(b2[0]);
		s += rand.nextInt(0xffffff);

		s += s3;
		rand.setSeed(rand.nextInt(0xffffff) + b3[0]);
		rand.setSeed(b3[0]);
		s += rand.nextInt(0xffffff);

		s += s4;
		rand.setSeed(rand.nextInt(0xffffff) + b4[0]);
		rand.setSeed(b4[0]);
		s += rand.nextInt(0xffffff);

		//System.out.println(s);

		return getHash(s);
	}

	private String getHash(final String inp) {

		try {
			final MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			final byte[] bytes = sha256.digest(inp.getBytes());
			String str = "";

			for(final int b : bytes)
				str = str + Integer.toString((b & 0xFF) + 256, 16).substring(1);

			return str;

		} catch(final NoSuchAlgorithmException e) {
		}

		return "";
	}

	@SubscribeEvent
	public void chatEvent(final ServerChatEvent event) {

		final EntityPlayerMP player = event.getPlayer();
		final String message = event.getMessage();
		//boolean conditions for the illiterate, edition 1
		//bellow you can see the header of an if-block. inside the brackets, there is a boolean statement.
		//that means nothing other than its value totaling either 'true' or 'false'
		//examples: 'true' would just mean true
		//'1 > 3' would equal false
		//'i < 10' would equal true if 'i' is smaller than 10, if equal or greater, it will result in false

		//let's start from the back:

		//this part means that the message's first character has to equal a '!': -------------------------+
		//                                                                                                |
		//this is a logical AND operator: -------------------------------------------------------------+  |
		//                                                                                             |  |
		//this is a reference to a field in                                                            |  |
		//Library.java containing a reference UUID: --------------------------------------+            |  |
		//                                                                                |            |  |
		//this will compare said UUID with                                                |            |  |
		//the string representation of the                                                |            |  |
		//current player's UUID: ----------+                                              |            |  |
		//                                 |                                              |            |  |
		//another AND operator: --------+  |                                              |            |  |
		//                              |  |                                              |            |  |
		//this is a reference to a      |  |                                              |            |  |
		//boolean called                |  |                                              |            |  |
		//'enableDebugMode' which is    |  |                                              |            |  |
		//only set once by the mod's    |  |                                              |            |  |
		//config and is disabled by     |  |                                              |            |  |
		//default. "debug" is not a     |  |                                              |            |  |
		//substring of the message, nor |  |                                              |            |  |
		//something that can be toggled |  |                                              |            |  |
		//in any other way except for   |  |                                              |            |  |
		//the config file: |            |  |                                              |            |  |
		//                 V            V  V                                              V            V  V
		if(GeneralConfig.enableDebugMode && player.getUniqueID().toString().equals(Library.HbMinecraft) && message.startsWith("!")) {

			final String[] msg = message.split(" ");

			final String m = msg[0].substring(1).toLowerCase();

			if("gv".equals(m)) {

				int id = 0;
				int size = 1;
				int meta = 0;

				if(msg.length > 1 && NumberUtils.isCreatable(msg[1])) {
					id = (int) (double) NumberUtils.createDouble(msg[1]);
				}

				if(msg.length > 2 && NumberUtils.isCreatable(msg[2])) {
					size = (int) (double) NumberUtils.createDouble(msg[2]);
				}

				if(msg.length > 3 && NumberUtils.isCreatable(msg[3])) {
					meta = (int) (double) NumberUtils.createDouble(msg[3]);
				}

				final Item item = Item.getItemById(id);

				if(item != null && size > 0 && meta >= 0) {
					player.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(item, size, meta));
				}
			}

			player.inventoryContainer.detectAndSendChanges();
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void worldTick(final WorldTickEvent event) {
		if (event.world == null || event.world.isRemote) return; // Dont bother updating shit
		List<Object> entityList = new ArrayList<>(event.world.loadedEntityList);

		// Handle pipe network updates
		if (!MainRegistry.allPipeNetworks.isEmpty()) {
			final Iterator<FFPipeNetwork> itr = MainRegistry.allPipeNetworks.iterator();
			while (itr.hasNext()) {
				final FFPipeNetwork net = itr.next();

				if (net == null || net.getNetworkWorld() != event.world) continue; // Skip for invalid

				net.updateTick();

				if (net.getPipes().isEmpty()) {
					net.destroySoft();
					itr.remove();
				}
			}
		}

		final long worldTime = event.world.getTotalWorldTime();

		// Perform periodic operations
		if (worldTime % 100 == 97) {
			PacketDispatcher.wrapper.sendToAll(new SurveyPacket(RBMKDials.getColumnHeight(event.world)));
		}

		// Hazard system update for dropped items
		for (final Object e : entityList) {
			if (e instanceof EntityItem) {
				HazardSystem.updateDroppedItem((EntityItem) e);
			}
		}

		// Handle additional logic at the start of the tick
		if (event.phase == Phase.START) {
			BossSpawnHandler.rollTheDice(event.world);
			TimedGenerator.automaton(event.world, 100);
		}
	}

	@SubscribeEvent
	public void serverTick(final ServerTickEvent e){
		if(e.phase == Phase.START){
			JetpackHandler.serverTick();
			RTTYSystem.updateBroadcastQueue();
		} else {
			EntityHitDataHandler.updateSystem();
		}
	}

	// Drillgon200: So 1.12.2's going to ignore ISpecialArmor if the damage is
	// unblockable, huh?
	@SubscribeEvent
	public void onEntityHurt(final LivingHurtEvent e) {
		final EntityLivingBase ent = e.getEntityLiving();
		if(e.getEntityLiving() instanceof EntityPlayer) {
			if(ArmorUtil.checkArmor(e.getEntityLiving(), ModItems.euphemium_helmet, ModItems.euphemium_plate, ModItems.euphemium_legs, ModItems.euphemium_boots)) {
				e.setCanceled(true);
			}
		}
		ArmorFSB.handleHurt(e);

		/// V1 ///
		if(EntityDamageUtil.wasAttackedByV1(e.getSource())) {
			final EntityPlayer attacker = (EntityPlayer) e.getSource().getImmediateSource();

			final NBTTagCompound data = new NBTTagCompound();
			data.setString("type", "vanillaburst");
			data.setInteger("count", (int)Math.min(ent.getMaxHealth() / 2F, 250));
			data.setDouble("motion", 0.1D);
			data.setString("mode", "blockdust");
			data.setInteger("block", Block.getIdFromBlock(Blocks.REDSTONE_BLOCK));
			PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, ent.posX, ent.posY + ent.height * 0.5, ent.posZ), new TargetPoint(ent.dimension, ent.posX, ent.posY, ent.posZ, 50));

			if(attacker.getDistanceSq(ent) < 25) {
				attacker.heal(e.getAmount() * 0.5F);
			}
		}

		for(int i = 2; i < 6; i++) {

			final ItemStack armor = ent.getItemStackFromSlot(EntityEquipmentSlot.values()[i]);

			if(armor != null && ArmorModHandler.hasMods(armor)) {

				for(final ItemStack mod : ArmorModHandler.pryMods(armor)) {

					if(mod != null && mod.getItem() instanceof ItemArmorMod) {
						((ItemArmorMod)mod.getItem()).modDamage(e, armor);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityAttacked(final LivingAttackEvent event) {
		final EntityLivingBase e = event.getEntityLiving();

		if(e instanceof EntityPlayer && ArmorUtil.checkArmor(e, ModItems.euphemium_helmet, ModItems.euphemium_plate, ModItems.euphemium_legs, ModItems.euphemium_boots)) {
			if(event.getSource() != ModDamageSource.digamma){
				e.world.playSound(null, e.posX, e.posY, e.posZ, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 5F, 1.0F + e.getRNG().nextFloat() * 0.5F);
				event.setCanceled(true);
			}
		}

		ArmorFSB.handleAttack(event);
	}

	@SubscribeEvent
	public void onPlayerFall(final PlayerFlyableFallEvent event) {
		ArmorFSB.handleFall(event.getEntityPlayer());
	}

	@SubscribeEvent
	public void onEntityFall(final LivingFallEvent event) {
		ArmorFSB.handleFall(event.getEntityLiving());
	}

	@SubscribeEvent
	public void onPlayerTick(final TickEvent.PlayerTickEvent event) {
		final EntityPlayer player = event.player;

		//Client-side
		if (player.world.isRemote) {
			if (event.phase == TickEvent.Phase.START && !player.isInvisible() && !player.isSneaking()) {
				if (player.getUniqueID().toString().equals(Library.HbMinecraft)) {
					handleClientSideParticleEffect(player);
				}
			}
			return;
		}

		// Non-client-side
		if (event.phase == TickEvent.Phase.START) {
			handleGhostFix(player);
			handleBetaHealth(player);
			ItemDigammaDiagnostic.playVoices(player.world, player);
		} else if (event.phase == TickEvent.Phase.END) {
			JetpackHandler.postPlayerTick(player);
		}

		// Update inventory hazard system periodically
		if (player.ticksExisted % HAZARD_POLL_RATE == 0) {
			HazardSystem.updatePlayerInventory(player);
		}
	}

	private void handleGhostFix(final EntityPlayer player) {
		if (!Float.isFinite(player.getHealth()) || !Float.isFinite(player.getAbsorptionAmount())) {
			player.sendMessage(new TextComponentString("Your health has been restored!"));
			player.world.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.syringeUse, SoundCategory.PLAYERS, 1.0F, 1.0F);
			player.setHealth(player.getMaxHealth());
			player.setAbsorptionAmount(0);
		}
	}

	private void handleBetaHealth(final EntityPlayer player) {
		if (Library.hasInventoryItem(player.inventory, ModItems.beta)) {
			final int foodLevel = player.getFoodStats().getFoodLevel();
			if (foodLevel < 10) {
				player.getFoodStats().setFoodLevel(10);
			} else if (foodLevel > 10) {
				player.heal(foodLevel - 10);
				player.getFoodStats().setFoodLevel(10);
			}
		}
	}

	private void handleClientSideParticleEffect(final EntityPlayer player) {
		final double angle = Math.toRadians(player.ticksExisted * 3);
		Vec3d vec = new Vec3d(3, 0, 0).rotateYaw((float) angle);

		for (int k = 0; k < 5; k++) {
			vec = vec.rotateYaw((float) Math.toRadians(1));
			player.world.spawnParticle(EnumParticleTypes.TOWN_AURA, player.posX + vec.x, player.posY + 1 + player.world.rand.nextDouble() * 0.05, player.posZ + vec.z, 0.0, 0.0, 0.0);
		}
	}

	@SubscribeEvent
	public void onLivingDeath(final LivingDeathEvent event) {
		HbmLivingProps.setRadiation(event.getEntityLiving(), 0);
		if(event.getEntity().world.isRemote)
			return;

		if(event.getEntityLiving() instanceof EntityPlayer) {
			if(ArmorUtil.checkArmor(event.getEntityLiving(), ModItems.euphemium_helmet, ModItems.euphemium_plate, ModItems.euphemium_legs, ModItems.euphemium_boots)) {
				if(event.getSource() != ModDamageSource.digamma){
					event.setCanceled(true);
					event.getEntityLiving().setHealth(event.getEntityLiving().getMaxHealth());
				}
			}
		}
		if(event.isCancelable() && event.isCanceled())
			return;
		if(GeneralConfig.enableCataclysm) {
			final EntityBurningFOEQ foeq = new EntityBurningFOEQ(event.getEntity().world);
			foeq.setPositionAndRotation(event.getEntity().posX, 500, event.getEntity().posZ, 0.0F, 0.0F);
			event.getEntity().world.spawnEntity(foeq);
		}
		if(event.getEntity().getUniqueID().toString().equals(Library.HbMinecraft)) {
			event.getEntity().dropItem(ModItems.book_of_, 1);
		}

		if(event.getEntity().getUniqueID().toString().equals(Library.Alcater)) {
			event.getEntity().entityDropItem(ItemStackUtil.itemStackFrom(ModItems.bottle_rad).setStackDisplayName("§aAlcater's §2Neo §aNuka§r"), 0.5F);
		}

		if(event.getEntity() instanceof EntityTaintedCreeper && event.getSource() == ModDamageSource.boxcar) {

			for(final EntityPlayer player : event.getEntity().getEntityWorld().getEntitiesWithinAABB(EntityPlayer.class, event.getEntity().getEntityBoundingBox().grow(50, 50, 50))) {
				AdvancementManager.grantAchievement(player, AdvancementManager.bobHidden);
			}
		}

		if(!event.getEntityLiving().world.isRemote) {

			if(event.getSource() instanceof EntityDamageSource && event.getSource().getTrueSource() instanceof EntityPlayer
					 && !(event.getSource().getTrueSource() instanceof FakePlayer)) {

				if(event.getEntityLiving() instanceof EntitySpider && event.getEntityLiving().getRNG().nextInt(500) == 0) {
					event.getEntityLiving().dropItem(ModItems.spider_milk, 1);
				}

				if(event.getEntityLiving() instanceof EntityCaveSpider && event.getEntityLiving().getRNG().nextInt(100) == 0) {
					event.getEntityLiving().dropItem(ModItems.serum, 1);
				}

				if(event.getEntityLiving() instanceof EntityAnimal && event.getEntityLiving().getRNG().nextInt(500) == 0) {
					event.getEntityLiving().dropItem(ModItems.bandaid, 1);
				}

				if(event.getEntityLiving() instanceof IMob && event.getEntityLiving().getRNG().nextInt(1000) == 0) {
					event.getEntityLiving().dropItem(ModItems.heart_piece, 1);
				}

				if(event.getEntityLiving() instanceof EntityCyberCrab && event.getEntityLiving().getRNG().nextInt(500) == 0) {
					event.getEntityLiving().dropItem(ModItems.wd40, 1);
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onEntityDeathFirst(final LivingDeathEvent event){
		for(int i = 2; i < 6; i++) {

			final ItemStack stack = event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.values()[i]);

			if(stack != null && stack.getItem() instanceof ItemArmor && ArmorModHandler.hasMods(stack)) {

				final ItemStack revive = ArmorModHandler.pryMods(stack)[ArmorModHandler.extra];

				if(revive != null) {

					//Classic revive
					if(revive.getItem() instanceof ItemModRevive) {
						revive.setItemDamage(revive.getItemDamage() + 1);

						if(revive.getItemDamage() >= revive.getMaxDamage()) {
							ArmorModHandler.removeMod(stack, ArmorModHandler.extra);
						} else {
							ArmorModHandler.applyMod(stack, revive);
						}

						event.getEntityLiving().setHealth(event.getEntityLiving().getMaxHealth());
						event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 60, 99));
						event.setCanceled(true);
						return;
					}

					//Shackles
					if(revive.getItem() instanceof ItemModShackles && HbmLivingProps.getRadiation(event.getEntityLiving()) < 1000F) {

						revive.setItemDamage(revive.getItemDamage() + 1);

						final int dmg = revive.getItemDamage();
						ArmorModHandler.applyMod(stack, revive);

						event.getEntityLiving().setHealth(event.getEntityLiving().getMaxHealth());
						HbmLivingProps.incrementRadiation(event.getEntityLiving(), dmg * dmg);
						event.setCanceled(true);
						return;
					}
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onEntityDeathLast(final LivingDeathEvent event){
		final EntityLivingBase entity = event.getEntityLiving();

		if(EntityDamageUtil.wasAttackedByV1(event.getSource())) {

			final NBTTagCompound vdat = new NBTTagCompound();
			vdat.setString("type", "giblets");
			vdat.setInteger("ent", entity.getEntityId());
			PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(vdat, entity.posX, entity.posY + entity.height * 0.5, entity.posZ), new TargetPoint(entity.dimension, entity.posX, entity.posY + entity.height * 0.5, entity.posZ, 150));

			entity.world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, SoundCategory.HOSTILE, 2.0F, 0.95F + entity.world.rand.nextFloat() * 0.2F);

			final EntityPlayer attacker = (EntityPlayer) event.getSource().getImmediateSource();

			if(attacker.getDistanceSq(entity) < 100) {
				attacker.heal(entity.getMaxHealth() * 0.25F);
			}
		}

		if(entity instanceof EntityPlayer player) {

            for(int i = 0; i < player.inventory.getSizeInventory(); i++) {

				final ItemStack stack = player.inventory.getStackInSlot(i);

				if(stack != null && stack.getItem() == ModItems.detonator_deadman) {

					if(stack.getTagCompound() != null) {

						final int x = stack.getTagCompound().getInteger("x");
						final int y = stack.getTagCompound().getInteger("y");
						final int z = stack.getTagCompound().getInteger("z");

						if(!player.world.isRemote && player.world.getBlockState(new BlockPos(x, y, z)).getBlock() instanceof IBomb) {

							((IBomb) player.world.getBlockState(new BlockPos(x, y, z)).getBlock()).explode(player.world, new BlockPos(x, y, z));

							if(GeneralConfig.enableExtendedLogging)
								MainRegistry.logger.log(Level.INFO, "[DET] Tried to detonate block at " + x + " / " + y + " / " + z + " by dead man's switch from " + player.getDisplayName() + "!");
						}

						player.inventory.setInventorySlotContents(i, null);
					}
				}
			}
		}
	}

	public static Field r_handInventory = null;
	public static Field r_armorArray = null;

	@SuppressWarnings({ "unchecked", "deprecation" })
	@SubscribeEvent
	public void onLivingUpdate(final LivingUpdateEvent event) {
		if (event.isCancelable() && event.isCanceled()) return;

		final EntityLivingBase entity = event.getEntityLiving();
		ArmorFSB.handleTick(entity);

		// Reflection field initialization
		if (r_handInventory == null || r_armorArray == null) {
			r_handInventory = ReflectionHelper.findField(EntityLivingBase.class, "handInventory", "field_184630_bs");
			r_armorArray = ReflectionHelper.findField(EntityLivingBase.class, "armorArray", "field_184631_bt");
		}

		try {
			final NonNullList<ItemStack> handInventory = (NonNullList<ItemStack>) r_handInventory.get(entity);
			final NonNullList<ItemStack> armorArray = (NonNullList<ItemStack>) r_armorArray.get(entity);

			// Handle equipped items for main hand and off-hand
			if (entity instanceof EntityPlayer player) {
                handleEquipUpdate(player, handInventory.get(0), EnumHand.MAIN_HAND);
				handleEquipUpdate(player, handInventory.get(1), EnumHand.OFF_HAND);
			}

			// Handle armor updates
			for (int i = 2; i < 6; i++) {
				final EntityEquipmentSlot slot = EntityEquipmentSlot.values()[i];
				final ItemStack previousArmor = (armorArray != null) ? armorArray.get(i - 2) : ItemStack.EMPTY;
				final ItemStack currentArmor = entity.getItemStackFromSlot(slot);

				final boolean needsReapply = armorArray != null && !ItemStack.areItemStacksEqual(previousArmor, currentArmor);
				if (needsReapply) {
					removeOldModifiers(previousArmor, slot, entity);
					applyNewModifiers(currentArmor, slot, entity);
				}
			}

		} catch (final Exception e) {
			//TODO:Figure out how to log that
		}

		EntityEffectHandler.onUpdate(entity);

		// New Hazard system
		if (!entity.world.isRemote && !(entity instanceof EntityPlayer)) {
			if (entity.ticksExisted % HAZARD_POLL_RATE == 0) {
				HazardSystem.updateLivingInventory(entity);
			}
		}
	}

	// Handle item equip logic for a given hand
	private void handleEquipUpdate(final EntityPlayer player, final ItemStack previousItem, final EnumHand hand) {
		final ItemStack currentItem = player.getHeldItem(hand);
		if (currentItem.getItem() instanceof IEquipReceiver && !ItemStack.areItemsEqual(previousItem, currentItem)) {
			((IEquipReceiver) currentItem.getItem()).onEquip(player, hand);
		}
	}

	// Remove old attribute modifiers for armor
	private void removeOldModifiers(final ItemStack previousArmor, final EntityEquipmentSlot slot, final EntityLivingBase entity) {
		if (previousArmor != null && ArmorModHandler.hasMods(previousArmor)) {
			for (final ItemStack mod : ArmorModHandler.pryMods(previousArmor)) {
				if (mod != null && mod.getItem() instanceof ItemArmorMod) {
					final Multimap<String, AttributeModifier> modifiers = ((ItemArmorMod) mod.getItem()).getModifiers(slot, previousArmor);
					if (modifiers != null) {
						entity.getAttributeMap().removeAttributeModifiers(modifiers);
					}
				}
			}
		}
	}

	// Apply new attribute modifiers for armor
	private void applyNewModifiers(final ItemStack currentArmor, final EntityEquipmentSlot slot, final EntityLivingBase entity) {
		if (currentArmor != null && ArmorModHandler.hasMods(currentArmor)) {
			for (final ItemStack mod : ArmorModHandler.pryMods(currentArmor)) {
				if (mod != null && mod.getItem() instanceof ItemArmorMod) {
					((ItemArmorMod) mod.getItem()).modUpdate(entity, currentArmor);
					final Multimap<String, AttributeModifier> modifiers = ((ItemArmorMod) mod.getItem()).getModifiers(slot, currentArmor);
					if (modifiers != null) {
						entity.getAttributeMap().applyAttributeModifiers(modifiers);
					}
				}
			}
		}
	}
	@SubscribeEvent
	public void onEntityJump(final LivingJumpEvent event) {
		if(event.isCancelable() && event.isCanceled())
			return;
		ArmorFSB.handleJump(event.getEntityLiving());
	}


	@SubscribeEvent
	public void blockBreak(final BlockEvent.BreakEvent event){
		if(event.isCancelable() && event.isCanceled())
			return;
		if(!(event.getPlayer() instanceof EntityPlayerMP))
			return;

		final Block block = event.getState().getBlock();

		if(block == Blocks.COAL_ORE || block == Blocks.COAL_BLOCK || block == ModBlocks.ore_lignite) {

			for(final ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {

				final int x = event.getPos().getX() + dir.offsetX;
				final int y = event.getPos().getY() + dir.offsetY;
				final int z = event.getPos().getZ() + dir.offsetZ;

				if(event.getWorld().rand.nextInt(2) == 0 && event.getWorld().getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.AIR)
					event.getWorld().setBlockState(new BlockPos(x, y, z), ModBlocks.gas_coal.getDefaultState());
			}
		}
	}

	@SubscribeEvent
	public void clientJoinServer(final PlayerLoggedInEvent e) {
		if(e.player instanceof EntityPlayerMP playerMP){
            PacketDispatcher.sendTo(new AssemblerRecipeSyncPacket(AssemblerRecipes.recipeList, AssemblerRecipes.hidden), playerMP);
			JetpackHandler.playerLoggedIn(e);
			final IHBMData props = HbmCapability.getData(e.player);

			PacketDispatcher.sendTo(new KeybindPacket(EnumKeybind.TOGGLE_JETPACK, props.getEnableBackpack()), playerMP);
			PacketDispatcher.sendTo(new KeybindPacket(EnumKeybind.TOGGLE_HEAD, props.getEnableHUD()), playerMP);

			if (GeneralConfig.enableWelcomeMessage) {
				e.player.sendMessage(new TextComponentTranslation("chat.welcome"));
			}

			if(HTTPHandler.newVersion && GeneralConfig.changelog) {
				e.player.sendMessage(new TextComponentTranslation("chat.newver", HTTPHandler.versionNumber));
				e.player.sendMessage(new TextComponentTranslation("chat.curver", RefStrings.VERSION));

				if(HTTPHandler.changes != ""){
					final String[] lines = HTTPHandler.changes.split("\\$");
					e.player.sendMessage(new TextComponentString("§6[Some of the new Features]§r"));//RefStrings.CHANGELOG
					for(final String w: lines){
						e.player.sendMessage(new TextComponentString(w));//RefStrings.CHANGELOG
					}
				}
			}

			if(HTTPHandler.optifine){
				e.player.sendMessage(new TextComponentString("Optifine detected, may cause compatibility issues. Check log for details."));
			}
			if(GeneralConfig.duckButton){
				if(e.player instanceof EntityPlayerMP && !e.player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).getBoolean("hasDucked")){
	        		PacketDispatcher.sendTo(new PlayerInformPacket("chat.duck"), (EntityPlayerMP)e.player);
				}
	        }
		}
	}

	@SubscribeEvent
	public void worldLoad(final WorldEvent.Load e) {
		JetpackHandler.worldLoad(e);
	}

	@SubscribeEvent
	public void worldSave(final WorldEvent.Save e) {
		JetpackHandler.worldSave(e);
	}

	@SubscribeEvent
	public void onDataSerializerRegister(final RegistryEvent.Register<DataSerializerEntry> evt) {
		evt.getRegistry().register(new DataSerializerEntry(MissileStruct.SERIALIZER).setRegistryName(new ResourceLocation(RefStrings.MODID, "missile_struct")));
	}

	@SubscribeEvent
	public void anvilUpdateEvent(final AnvilUpdateEvent event) {

		if(event.getLeft().getItem() instanceof ItemGunBase && event.getRight().getItem() == Items.ENCHANTED_BOOK) {

			event.setOutput(event.getLeft().copy());

            final Map<Enchantment, Integer> mapright = EnchantmentHelper.getEnchantments(event.getRight());
            final Iterator<Entry<Enchantment, Integer>> itr = mapright.entrySet().iterator();

            while(itr.hasNext()) {
            	final Entry<Enchantment, Integer> entry = itr.next();
            	final Enchantment e = entry.getKey();
            	final int j = entry.getValue();

            	EnchantmentUtil.removeEnchantment(event.getOutput(), e);
            	EnchantmentUtil.addEnchantment(event.getOutput(), e, j);
            }

            event.setCost(10);
		}
		if(ItemStackUtil.isSameMetaItem(event.getLeft(), ModItems.ingot.getItemStack(MaterialMineral.METEORITE)) && ItemStackUtil.isSameMetaItem(event.getRight(), ModItems.ingot.getItemStack(MaterialMineral.METEORITE)) &&
				event.getLeft().getCount() == 1 && event.getRight().getCount() == 1) {

			final double h1 = ItemHot.getHeat(event.getLeft());
			final double h2 = ItemHot.getHeat(event.getRight());

			if(h1 >= 0.5 && h2 >= 0.5) {

				final ItemStack out = ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.METEORITE_FORGED));
				ItemHot.heatUp(out, (h1 + h2) / 2D);
				event.setOutput(out);
	            event.setCost(10);
			}
		}

		if(ItemStackUtil.isSameMetaItem(event.getLeft(), ModItems.ingot.getItemStack(MaterialMineral.METEORITE_FORGED)) && ItemStackUtil.isSameMetaItem(event.getRight(), ModItems.ingot.getItemStack(MaterialMineral.METEORITE_FORGED)) &&
				event.getLeft().getCount() == 1 && event.getRight().getCount() == 1) {

			final double h1 = ItemHot.getHeat(event.getLeft());
			final double h2 = ItemHot.getHeat(event.getRight());

			if(h1 >= 0.5 && h2 >= 0.5) {

				final ItemStack out = ItemStackUtil.itemStackFrom(ModItems.blade_meteorite);
				ItemHot.heatUp(out, (h1 + h2) / 2D);
				event.setOutput(out);
	            event.setCost(30);
			}
		}

		if(ItemStackUtil.isSameMetaItem(event.getLeft(), ModItems.meteorite_sword_seared) && ItemStackUtil.isSameMetaItem(event.getRight(), ModItems.ingot.getItemStack(MaterialMineral.METEORITE_FORGED)) &&
				event.getLeft().getCount() == 1 && event.getRight().getCount() == 1) {

			final double h2 = ItemHot.getHeat(event.getRight());

			if(h2 >= 0.5) {

				final ItemStack out = ItemStackUtil.itemStackFrom(ModItems.meteorite_sword_reforged);
				event.setOutput(out);
	            event.setCost(50);
			}
		}

		if(event.getLeft().getItem() == ModItems.ingot_steel_dusted && event.getRight().getItem() == ModItems.ingot_steel_dusted &&
				event.getLeft().getCount() ==  event.getRight().getCount()) {

			final double h1 = ItemHot.getHeat(event.getLeft());
			final double h2 = ItemHot.getHeat(event.getRight());

			if(h2 >= 0.5) {

				final int i1 = event.getLeft().getItemDamage();
				final int i2 = event.getRight().getItemDamage();

				final int i3 = Math.min(i1, i2) + 1;

				final boolean done = i3 >= 10;

				final ItemStack out;
				if(done){
					out = ItemStackUtil.itemStackFrom(ModItems.ingot.getItemStack(MaterialMineral.CHAINSTEEL), event.getLeft().getCount());
				} else {
					out = ItemStackUtil.itemStackFrom(ModItems.ingot_steel_dusted, event.getLeft().getCount(), i3);
				}

				ItemHot.heatUp(out, done ? 1D : (h1 + h2) / 2D);
				event.setOutput(out);
				event.setCost(event.getLeft().getCount());
			}
		}
	}

	@SubscribeEvent
	public void onFoodEaten(final LivingEntityUseItemEvent.Finish event) {

		final ItemStack stack = event.getItem();

		if(stack != null && stack.getItem() instanceof ItemFood) {

			if(stack.hasTagCompound() && stack.getTagCompound().getBoolean("ntmCyanide")) {
				for(int i = 0; i < 10; i++) {
					event.getEntityLiving().attackEntityFrom(rand.nextBoolean() ? ModDamageSource.euthanizedSelf : ModDamageSource.euthanizedSelf2, 1000);
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerRespawn(final PlayerEvent.PlayerRespawnEvent event) {

		final EntityPlayer player = event.player;

		if(player.getDisplayName().getUnformattedText().equals("Dr_Nostalgia") && !player.world.isRemote) {

			if(!Library.hasInventoryItem(player.inventory, ModItems.hat))
				player.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(ModItems.hat));

			if(!Library.hasInventoryItem(player.inventory, ModItems.beta))
				player.inventory.addItemStackToInventory(ItemStackUtil.itemStackFrom(ModItems.beta));
		}
	}

	
	@SubscribeEvent
	public void craftingRegister(final RegistryEvent.Register<IRecipe> e){
		long mem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println("Memory usage before: " + mem);
		CraftingManager.hack = e;
		CraftingManager.init();
		CraftingManager.hack = null;
		mem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println("Memory usage after: " + mem);
	}

	// TODO should probably use these.

	@SubscribeEvent
	public void onItemRegister(final RegistryEvent.Register<Item> evt) {
	}

	@SubscribeEvent
	public void onBlockRegister(final RegistryEvent.Register<Block> evt) {
	}

	@SubscribeEvent
	public void onRecipeRegister(final RegistryEvent.Register<IRecipe> evt) {
		final IRecipe[] recipes = new IRecipe[12];
		final IRecipe recipe = null;
		doesArrayContain(recipes, recipe);
	}

	public static boolean doesArrayContain(final Object[] array, final Object objectCheck){
		System.out.println("On Recipe Register");
		return false;
	}
	
}
