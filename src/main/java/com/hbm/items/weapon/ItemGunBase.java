package com.hbm.items.weapon;

import java.lang.reflect.Field;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.hbm.config.GeneralConfig;
import com.hbm.entity.projectile.EntityBulletBase;
import com.hbm.handler.BulletConfigSyncingUtil;
import com.hbm.handler.BulletConfiguration;
import com.hbm.handler.GunConfiguration;
import com.hbm.handler.HbmKeybinds;
import com.hbm.interfaces.IHoldableWeapon;
import com.hbm.interfaces.IItemHUD;
import com.hbm.items.ModItems;
import com.hbm.lib.Library;
import com.hbm.packet.AuxParticlePacketNT;
import com.hbm.packet.GunAnimationPacket;
import com.hbm.packet.GunButtonPacket;
import com.hbm.packet.GunFXPacket;
import com.hbm.packet.GunFXPacket.FXType;
import com.hbm.packet.PacketDispatcher;
import com.hbm.render.anim.BusAnimation;
import com.hbm.render.anim.HbmAnimations;
import com.hbm.render.anim.HbmAnimations.AnimType;
import com.hbm.render.anim.HbmAnimations.Animation;
import com.hbm.render.misc.RenderScreenOverlay;
import com.hbm.render.misc.RenderScreenOverlay.Crosshair;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGunBase extends Item implements IHoldableWeapon, IItemHUD {

	public GunConfiguration mainConfig;
	public GunConfiguration altConfig;
	public static Field hurtResistantTime;

	@SideOnly(Side.CLIENT)
	public static boolean m1;// = false;
	@SideOnly(Side.CLIENT)
	public static boolean m2;// = false;
	public static boolean oldClickRight;
	public static boolean oldClickLeft;

	public ItemGunBase(final GunConfiguration config, final String s) {
		mainConfig = config;
		this.setMaxStackSize(1);
		this.setTranslationKey(s);
		this.setRegistryName(s);

		ModItems.ALL_ITEMS.add(this);
	}

	public ItemGunBase(final GunConfiguration config, final GunConfiguration alt, final String s) {
		this(config, s);
		altConfig = alt;
	}

	@Override
	public void onUpdate(final ItemStack stack, final World world, final Entity entity, final int itemSlot, final boolean isSelected) {
		EnumHand hand = null;
		if(entity instanceof EntityPlayer) {
			if(((EntityPlayer) entity).getHeldItem(EnumHand.MAIN_HAND) == stack) {
				hand = EnumHand.MAIN_HAND;
			} else if(((EntityPlayer) entity).getHeldItem(EnumHand.OFF_HAND) == stack) {
				hand = EnumHand.OFF_HAND;
			}
			if(hand != null) {
				if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && world.isRemote) {
					updateClient(stack, world, (EntityPlayer) entity, itemSlot, hand);
				} else {
					updateServer(stack, world, (EntityPlayer) entity, itemSlot, hand);
				}
			}
		}
	}

	@Override
	public boolean shouldCauseReequipAnimation(final ItemStack oldStack, final ItemStack newStack, final boolean slotChanged) {
		return oldStack.getItem() != newStack.getItem();
	}

	@SideOnly(Side.CLIENT)
	protected void updateClient(final ItemStack stack, final World world, final EntityPlayer entity, final int slot, final EnumHand hand) {

		final boolean clickLeft = Mouse.isButtonDown(0);
		final boolean clickRight = Mouse.isButtonDown(1);
		final boolean left = m1;
		final boolean right = m2;
		
		if(hand != null) {
			if(left && right) {
				PacketDispatcher.wrapper.sendToServer(new GunButtonPacket(false, (byte) 0, hand));
				PacketDispatcher.wrapper.sendToServer(new GunButtonPacket(false, (byte) 1, hand));
				m1 = false;
				m2 = false;
			}
			
			if((m1 || getIsMouseDown(stack)) && !clickLeft) {
				PacketDispatcher.wrapper.sendToServer(new GunButtonPacket(false, (byte) 0, hand));
				m1 = false;
				endActionClient(stack, world, entity, true, hand);
			}
			
			if((m2 || getIsAltDown(stack)) && !clickRight) {
				PacketDispatcher.wrapper.sendToServer(new GunButtonPacket(false, (byte) 1, hand));
				m2 = false;
				endActionClient(stack, world, entity, false, hand);
			}
			
			if(mainConfig.reloadType != GunConfiguration.RELOAD_NONE || (altConfig != null && altConfig.reloadType != 0)) {
				
				if((HbmKeybinds.reloadKey.isKeyDown() && (getMag(stack) < mainConfig.ammoCap || (mainConfig.allowsInfinity && EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0)))) {
					PacketDispatcher.wrapper.sendToServer(new GunButtonPacket(true, (byte) 2, hand));
					setIsReloading(stack, true);
					resetReloadCycle(stack);
				}
			}
		}
		oldClickLeft = m1;
		oldClickRight = m2;
	}

	protected void updateServer(final ItemStack stack, final World world, final EntityPlayer player, final int slot, final EnumHand hand) {
		if(getDelay(stack) > 0 && hand != null)
			setDelay(stack, getDelay(stack) - 1);
		
		if(getIsMouseDown(stack) && hand == null) {
			setIsMouseDown(stack, false);
		}
		
		if(getIsAltDown(stack) && hand == null) {
			setIsAltDown(stack, false);
		}
		if(GeneralConfig.enableGuns && mainConfig.firingMode == GunConfiguration.FIRE_AUTO && getIsMouseDown(stack) && tryShoot(stack, world, player, hand != null)) {

			fire(stack, world, player, hand);
			setDelay(stack, mainConfig.rateOfFire);
			// setMag(stack, getMag(stack) - 1);
		}

		if(getIsReloading(stack) && hand != null) {
			reload2(stack, world, player, hand);
		}
	}

	// tries to shoot, bullet checks are done here
	protected boolean tryShoot(final ItemStack stack, final World world, final EntityPlayer player, final boolean main) {

		if(main && getDelay(stack) == 0 && !getIsReloading(stack) && getItemWear(stack) < mainConfig.durability) {

			if(mainConfig.reloadType == GunConfiguration.RELOAD_NONE) {
				return getBeltSize(player, getBeltType(player, stack, main)) > 0;

			} else {
				return getMag(stack) > 0;
			}
		}

		if(!main && altConfig != null && getDelay(stack) == 0 && !getIsReloading(stack) && getItemWear(stack) < mainConfig.durability) {

			if(altConfig.reloadType == GunConfiguration.RELOAD_NONE) {
				return getBeltSize(player, getBeltType(player, stack, main)) > 0;
				
			} else {
				return getMag(stack) > 0;
			}
		}

		return false;
	}

	public boolean hasAmmo(final ItemStack stack, final EntityPlayer player, final boolean main) {
		
		if(mainConfig.reloadType == GunConfiguration.RELOAD_NONE || !main) {
			return getBeltSize(player, getBeltType(player, stack, main)) > 0;
			
		} else {
			return getMag(stack) > 0;
		}
	}
	
	// called every time the gun shoots, overridden to change bullet
	// entity/special additions
	private void fire(final ItemStack stack, final World world, final EntityPlayer player, final EnumHand hand) {
		BulletConfiguration config = null;
		
		if(mainConfig.reloadType == GunConfiguration.RELOAD_NONE) {
			config = getBeltCfg(player, stack, true);
		} else {
			config = BulletConfigSyncingUtil.pullConfig(mainConfig.config.get(getMagType(stack)));
		}
		
		final int bullets = getBullets(world, player, hand, config);
		
		for(int k = 0; k < mainConfig.roundsPerCycle; k++) {
			
			if(!hasAmmo(stack, player, true))
				break;
			
			for(int i = 0; i < bullets; i++) {
				spawnProjectile(world, player, stack, BulletConfigSyncingUtil.getKey(config), hand);
			}
			
			useUpAmmo(player, stack, true);
			player.inventoryContainer.detectAndSendChanges();
			
			final int wear = (int) Math.ceil(config.wear / (1F + EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, stack)));
			setItemWear(stack, getItemWear(stack) + wear);
		}
		
		world.playSound(null, player.posX, player.posY, player.posZ, mainConfig.firingSound, SoundCategory.PLAYERS, 1.0F, mainConfig.firingPitch);

		if(player.getDisplayName().equals("Vic4Games")) {
			final NBTTagCompound nbt = new NBTTagCompound();
			nbt.setString("type", "justTilt");
			nbt.setInteger("time", mainConfig.rateOfFire + 1);
			PacketDispatcher.wrapper.sendTo(new AuxParticlePacketNT(nbt, player.posX, player.posY, player.posZ), (EntityPlayerMP) player);
		}
	}
	
	protected int getBullets(final World world, final EntityPlayer player, final EnumHand hand, final BulletConfiguration config){
		int bullets = config.bulletsMin;
		if(config.bulletsMax > config.bulletsMin)
			bullets += world.rand.nextInt(config.bulletsMax - config.bulletsMin);
		return bullets;
	}

	// unlike fire(), being called does not automatically imply success, some
	// things may still have to be handled before spawning the projectile
	protected void altFire(final ItemStack stack, final World world, final EntityPlayer player, final EnumHand hand) {
		if(altConfig == null)
			return;

		final BulletConfiguration config = getBeltCfg(player, stack, false);
		
		int bullets = config.bulletsMin;
		
		for(int k = 0; k < altConfig.roundsPerCycle; k++) {

			if(!hasAmmo(stack, player, false))
				break;

			if(config.bulletsMax > config.bulletsMin)
				bullets += world.rand.nextInt(config.bulletsMax - config.bulletsMin);
			
			for(int i = 0; i < bullets; i++) {
				spawnProjectile(world, player, stack, BulletConfigSyncingUtil.getKey(config), hand);
			}
			
			useUpAmmo(player, stack, false);
			setItemWear(stack, getItemWear(stack) + config.wear);
			player.inventoryContainer.detectAndSendChanges();
		}
		
		world.playSound(null, player.posX, player.posY, player.posZ, altConfig.firingSound, SoundCategory.PLAYERS, 1.0F, altConfig.firingPitch);
	}

	protected EntityBulletBase getBulletEntity(final World world, final EntityPlayer player, final ItemStack stack, final int config, final EnumHand hand){
		final EntityBulletBase bullet = new EntityBulletBase(world, config, player, hand);
		return bullet;
	}
	
	protected void spawnProjectile(final World world, final EntityPlayer player, final ItemStack stack, final int config, final EnumHand hand) {
		world.spawnEntity(getBulletEntity(world, player, stack, config, hand));
		
		if(this.mainConfig.animations.containsKey(AnimType.CYCLE) && player instanceof EntityPlayerMP)
			PacketDispatcher.wrapper.sendTo(new GunAnimationPacket(AnimType.CYCLE.ordinal(), hand), (EntityPlayerMP) player);
		PacketDispatcher.wrapper.sendToAllTracking(new GunFXPacket(player, hand, FXType.FIRE), new TargetPoint(world.provider.getDimension(), player.posX, player.posY, player.posZ, 1));
	}
	
	// called on click (server side, called by mouse packet)
	public void startAction(final ItemStack stack, final World world, final EntityPlayer player, final boolean main, final EnumHand hand) {
		if(mainConfig.firingMode == GunConfiguration.FIRE_MANUAL && getIsMouseDown(stack) && tryShoot(stack, world, player, main)) {
			fire(stack, world, player, hand);
			setDelay(stack, mainConfig.rateOfFire);
			//setMag(stack, getMag(stack) - 1);
			//useUpAmmo(player, stack, main);
		}
		if(!main && altConfig != null && tryShoot(stack, world, player, main)) {
			altFire(stack, world, player, hand);
			setDelay(stack, altConfig.rateOfFire);
			//useUpAmmo(player, stack, main);
		}
	}

	// called on click (client side, called by update cylce)
	public void startActionClient(final ItemStack stack, final World world, final EntityPlayer player, final boolean main, final EnumHand hand) {
	}

	// called on click release (server side, called by mouse packet) for release
	// actions like charged shots
	public void endAction(final ItemStack stack, final World world, final EntityPlayer player, final boolean main, final EnumHand hand) {
	}

	// called on click release (client side, called by update cylce)
	public void endActionClient(final ItemStack stack, final World world, final EntityPlayer player, final boolean main, final EnumHand hand) {
	}

	// martin 2 reload algorithm
	// now with less WET and more DRY
	// compact, readable and most importantly, FUNCTIONAL
	protected void reload2(final ItemStack stack, final World world, final EntityPlayer player, final EnumHand hand) {

		if(getMag(stack) >= mainConfig.ammoCap) {
			setIsReloading(stack, false);
			return;
		}

		if(getReloadCycle(stack) < 0) {

			if(getMag(stack) == 0)
				resetAmmoType(stack, world, player);

			int count = 1;

			if(mainConfig.reloadType == GunConfiguration.RELOAD_FULL) {

				count = mainConfig.ammoCap - getMag(stack);
			}	

			final BulletConfiguration cfg = BulletConfigSyncingUtil.pullConfig(mainConfig.config.get(getMagType(stack)));
			final Item ammo = cfg.ammo;

			int loadCount = 0;
			
			for(int i = 0; i < count; i++) {

				if(Library.hasInventoryItem(player.inventory, ammo) && getMag(stack) < mainConfig.ammoCap) {
					Library.consumeInventoryItem(player.inventory, ammo);
					setMag(stack, Math.min(getMag(stack) + cfg.ammoCount, mainConfig.ammoCap));
					loadCount++;
				} else {
					setIsReloading(stack, false);
					break;
				}
			}

			if(getMag(stack) >= mainConfig.ammoCap) {
				setIsReloading(stack, false);
			} else {
				resetReloadCycle(stack);
			}
			
			if(loadCount > 0){
				onAmmoLoad(world, player, stack, loadCount, hand);
			}

			if(loadCount > 0 && mainConfig.reloadSoundEnd && mainConfig.reloadSound != null)
				world.playSound(null, player.posX, player.posY, player.posZ, mainConfig.reloadSound, SoundCategory.PLAYERS, 1.0F, 1.0F);

		} else {
			setReloadCycle(stack, getReloadCycle(stack) - 1);
		}

		if(stack != player.getHeldItem(hand)) {
			setReloadCycle(stack, 0);
			setIsReloading(stack, false);
		}
	}
	
	public void onAmmoLoad(final World world, final EntityPlayer player, final ItemStack stack, final int loadCount, final EnumHand hand){
	}

	// initiates a reload
	public void startReloadAction(final ItemStack stack, final World world, final EntityPlayer player, final EnumHand hand) {

		if(player.isSneaking() && mainConfig.allowsInfinity && EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0) {
			
			if(getMag(stack) == mainConfig.ammoCap) {
				setMag(stack, 0);
				this.resetAmmoType(stack, world, player);
				player.playSound(SoundEvents.BLOCK_PISTON_EXTEND, 1.0F, 1.0F);
			}
			
			return;
		}
		
		if(getMag(stack) == mainConfig.ammoCap)
			return;
		
		if(getIsReloading(stack))
			return;

		if(!mainConfig.reloadSoundEnd && mainConfig.reloadSound != null)
			world.playSound(null, player.posX, player.posY, player.posZ, mainConfig.reloadSound, SoundCategory.PLAYERS, 1.0F, 1.0F);
		
		PacketDispatcher.wrapper.sendTo(new GunAnimationPacket(AnimType.RELOAD.ordinal(), hand), (EntityPlayerMP) player);

		setIsReloading(stack, true);
		resetReloadCycle(stack);
	}

	public boolean canReload(final ItemStack stack, final World world, final EntityPlayer player) {

		if(getMag(stack) == 0) {

			for(final Integer config : mainConfig.config) {

				final BulletConfiguration cfg = BulletConfigSyncingUtil.pullConfig(config);

				if(Library.hasInventoryItem(player.inventory, cfg.ammo)) {
					return true;
				}
			}

		} else {

			final Item ammo = BulletConfigSyncingUtil.pullConfig(mainConfig.config.get(getMagType(stack))).ammo;
            return Library.hasInventoryItem(player.inventory, ammo);
		}

		return false;
	}

	// searches the player's inv for next fitting ammo type and changes the
	// gun's mag
	protected void resetAmmoType(final ItemStack stack, final World world, final EntityPlayer player) {

		for(final Integer config : mainConfig.config) {

			final BulletConfiguration cfg = BulletConfigSyncingUtil.pullConfig(config);

			if(Library.hasInventoryItem(player.inventory, cfg.ammo)) {
				setMagType(stack, mainConfig.config.indexOf(config));
				break;
			}
		}
	}

	public static String getColor(final int a, final int b){
		final float fraction = 100F * a/b;
		if(fraction > 75)
			return "§a";
		if(fraction > 25)
			return "§e";
		return "§c";
	}

	// item mouseover text
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		final Item ammo = BulletConfigSyncingUtil.pullConfig(mainConfig.config.get(getMagType(stack))).ammo;

		if(mainConfig.ammoCap > 0){
			final int mag = getMag(stack);
			list.add("Ammo: "+ getColor(mag, mainConfig.ammoCap) + mag + " §2/ " + mainConfig.ammoCap);
		}
		else{
			list.add("Ammo: §6Belt");
		}

		list.add("Ammo Type: §e" + I18n.format(ammo.getTranslationKey() + ".name"));

		int dura = mainConfig.durability - getItemWear(stack);

		if(dura < 0)
			dura = 0;

		list.add("Durability: " + getColor(dura, mainConfig.durability) + dura + " §2/ " + mainConfig.durability);

		// if(MainRegistry.enableDebugMode) {
		list.add("");
		list.add("Name: " + mainConfig.name);
		list.add("Manufacturer: " + mainConfig.manufacturer);
		// }

		if(!mainConfig.comment.isEmpty()) {
			list.add("");
			for(final String s : mainConfig.comment)
				list.add("§6"+TextFormatting.ITALIC + s);
		}

		if(GeneralConfig.enableExtendedLogging) {
			list.add("");
			list.add("Type: " + getMagType(stack));
			list.add("Is Reloading: " + getIsReloading(stack));
			list.add("Reload Cycle: " + getReloadCycle(stack));
			list.add("RoF Cooldown: " + getDelay(stack));
		}
	}

	public static Item getBeltType(final EntityPlayer player, final ItemStack stack, final boolean main) {
		if(!(stack.getItem() instanceof ItemGunBase gun))
			return null;
        final GunConfiguration guncfg = main ? gun.mainConfig : (gun.altConfig != null ? gun.altConfig : gun.mainConfig);
		Item ammo = BulletConfigSyncingUtil.pullConfig(guncfg.config.get(0)).ammo;

		for(final Integer config : guncfg.config) {
			
			final BulletConfiguration cfg = BulletConfigSyncingUtil.pullConfig(config);
			
			if(Library.hasInventoryItem(player.inventory, cfg.ammo)) {
				ammo = cfg.ammo;
				break;
			}
		}
		
		return ammo;
	}

	public static BulletConfiguration getBeltCfg(final EntityPlayer player, final ItemStack stack, final boolean main) {
		final ItemGunBase gun = (ItemGunBase)stack.getItem();
		final GunConfiguration guncfg = main ? gun.mainConfig : (gun.altConfig != null ? gun.altConfig : gun.mainConfig);
		getBeltType(player, stack, main);

		for(final Integer config : guncfg.config) {
			
			final BulletConfiguration cfg = BulletConfigSyncingUtil.pullConfig(config);
			
			if(Library.hasInventoryItem(player.inventory, cfg.ammo)) {
				return cfg;
			}
		}

		return BulletConfigSyncingUtil.pullConfig(guncfg.config.get(0));
	}

	// returns ammo capacity of belt-weapons for current ammo
	public static int getBeltSize(final EntityPlayer player, final Item ammo) {

		int amount = 0;

		for(final ItemStack stack : player.inventory.mainInventory) {
			if(stack != null && stack.getItem() == ammo)
				amount += stack.getCount();
		}

		return amount;
	}

	// reduces ammo count for mag and belt-based weapons, should be called AFTER
	// firing
	public void useUpAmmo(final EntityPlayer player, final ItemStack stack, final boolean main) {

		if(!main && altConfig == null)
			return;
		
		GunConfiguration config = mainConfig;
		
		if(!main)
			config = altConfig;
		
		if(config.allowsInfinity && EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0)
			return;

		if(config.reloadType != GunConfiguration.RELOAD_NONE) {
			setMag(stack, getMag(stack) - 1);
		} else {
			Library.consumeInventoryItem(player.inventory, getBeltType(player, stack, main));
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void onFireClient(final ItemStack stack, final EntityPlayer player, final boolean shouldDoThirdPerson){
	}
	
	@SideOnly(Side.CLIENT)
	public void playerWorldRender(final EntityPlayer player, final RenderWorldLastEvent e, final EnumHand hand){
	}

	/// sets reload cycle to config defult ///
	public void resetReloadCycle(final ItemStack stack) {
		writeNBT(stack, "reload", getReloadDuration(stack));
	}

	public int getReloadDuration(final ItemStack stack){
		return ((ItemGunBase) stack.getItem()).mainConfig.reloadDuration;
	}
	
	/// if reloading routine is active ///
	public static void setIsReloading(final ItemStack stack, final boolean b) {
		writeNBT(stack, "isReloading", b ? 1 : 0);
	}

	public static boolean getIsReloading(final ItemStack stack) {
		return readNBT(stack, "isReloading") == 1;
	}

	/// if left mouse button is down ///
	public static void setIsMouseDown(final ItemStack stack, final boolean b) {
		writeNBT(stack, "isMouseDown", b ? 1 : 0);
	}

	public static boolean getIsMouseDown(final ItemStack stack) {
		return readNBT(stack, "isMouseDown") == 1;
	}

	/// if alt mouse button is down ///
	public static void setIsAltDown(final ItemStack stack, final boolean b) {
		writeNBT(stack, "isAltDown", b ? 1 : 0);
	}

	public static boolean getIsAltDown(final ItemStack stack) {
		return readNBT(stack, "isAltDown") == 1;
	}

	/// RoF cooldown ///
	public static void setDelay(final ItemStack stack, final int i) {
		writeNBT(stack, "dlay", i);
	}

	public static int getDelay(final ItemStack stack) {
		return readNBT(stack, "dlay");
	}

	/// Gun wear ///
	public static void setItemWear(final ItemStack stack, final int i) {
		writeNBT(stack, "wear", i);
	}

	public static int getItemWear(final ItemStack stack) {
		return readNBT(stack, "wear");
	}

	/// R/W cycle animation timer ///
	public static void setCycleAnim(final ItemStack stack, final int i) {
		writeNBT(stack, "cycle", i);
	}

	public static int getCycleAnim(final ItemStack stack) {
		return readNBT(stack, "cycle");
	}

	/// R/W reload animation timer ///
	public static void setReloadCycle(final ItemStack stack, final int i) {
		writeNBT(stack, "reload", i);
	}

	public static int getReloadCycle(final ItemStack stack) {
		return readNBT(stack, "reload");
	}

	/// magazine capacity ///
	public static void setMag(final ItemStack stack, final int i) {
		writeNBT(stack, "magazine", i);
	}

	public static int getMag(final ItemStack stack) {
		return readNBT(stack, "magazine");
	}

	/// magazine type (int specified by index in bullet config list) ///
	public static void setMagType(final ItemStack stack, final int i) {
		writeNBT(stack, "magazineType", i);
	}

	public static int getMagType(final ItemStack stack) {
		return readNBT(stack, "magazineType");
	}

	/// NBT utility ///
	protected static void writeNBT(final ItemStack stack, final String key, final int value) {

		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());

		stack.getTagCompound().setInteger(key, value);
	}

	public static int readNBT(final ItemStack stack, final String key) {

		if(!stack.hasTagCompound())
			return 0;

		return stack.getTagCompound().getInteger(key);
	}

	@Override
	public Crosshair getCrosshair() {
		return mainConfig.crosshair;
	}
	
	@SideOnly(Side.CLIENT)
	public void startAnim(final EntityPlayer player, final ItemStack stack, final int slot, final AnimType type){
		final GunConfiguration config = ((ItemGunBase) stack.getItem()).mainConfig;
		final BusAnimation animation = config.animations.get(type);

		if(animation != null) {
			HbmAnimations.hotbar[slot] = new Animation(stack.getItem().getTranslationKey(), System.currentTimeMillis(), animation);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void renderHUD(final Pre event, final ElementType type, final EntityPlayer player, final ItemStack stack, final EnumHand hand) {
		final ItemGunBase gun = ((ItemGunBase) player.getHeldItem(hand).getItem());
		final GunConfiguration gcfg = gun.mainConfig;
		if(event.getType() == ElementType.HOTBAR){
			final BulletConfiguration bcfg = BulletConfigSyncingUtil.pullConfig(gun.mainConfig.config.get(ItemGunBase.getMagType(player.getHeldItem(hand))));
	
			Item ammo = bcfg.ammo;
			int count = ItemGunBase.getMag(player.getHeldItem(hand));
			int max = gcfg.ammoCap;
			final boolean showammo = gcfg.showAmmo;
	
			if(gcfg.reloadType == GunConfiguration.RELOAD_NONE) {
				ammo = ItemGunBase.getBeltType(player, player.getHeldItem(hand), true);
				count = ItemGunBase.getBeltSize(player, ammo);
				max = -1;
			}
	
			final int dura = ItemGunBase.getItemWear(player.getHeldItem(hand)) * 50 / gcfg.durability;
	
			RenderScreenOverlay.renderAmmo(event.getResolution(), Minecraft.getMinecraft().ingameGUI, ammo, count, max, dura, hand, showammo);
	
			if(gun.altConfig != null && gun.altConfig.reloadType == GunConfiguration.RELOAD_NONE) {
				final Item oldAmmo = ammo;
				ammo = ItemGunBase.getBeltType(player, player.getHeldItem(hand), false);
	
				if(ammo != oldAmmo) {
					count = ItemGunBase.getBeltSize(player, ammo);
					RenderScreenOverlay.renderAmmoAlt(event.getResolution(), Minecraft.getMinecraft().ingameGUI, ammo, count, hand);
				}
			}
		}
		if(event.getType() == ElementType.CROSSHAIRS && GeneralConfig.enableCrosshairs && !(hand == EnumHand.OFF_HAND && player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof IHoldableWeapon)){
		
			event.setCanceled(true);
			if(((IHoldableWeapon) player.getHeldItem(hand).getItem()).hasCustomHudElement()){
				((IHoldableWeapon) player.getHeldItem(hand).getItem()).renderHud(event.getResolution(), Minecraft.getMinecraft().ingameGUI, player.getHeldItemMainhand(), event.getPartialTicks());
			} else {
				if(!(gcfg.hasSights && player.isSneaking()))
					RenderScreenOverlay.renderCustomCrosshairs(event.getResolution(), Minecraft.getMinecraft().ingameGUI, ((IHoldableWeapon) player.getHeldItem(hand).getItem()).getCrosshair());
				else
					RenderScreenOverlay.renderCustomCrosshairs(event.getResolution(), Minecraft.getMinecraft().ingameGUI, Crosshair.NONE);
			}
		}
		
	}
}
