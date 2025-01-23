package com.hbm.saveddata.satellites;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hbm.items.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class Satellite {
	
	public static List<Class<? extends Satellite>> satellites = new ArrayList<Class<? extends Satellite>>();
	public static HashMap<Item, Class<? extends Satellite>> itemToClass = new HashMap<Item, Class<? extends Satellite>>();
	
	public static enum InterfaceActions {
		HAS_MAP,		//lets the interface display loaded chunks
		CAN_CLICK,		//enables onClick events
		SHOW_COORDS,	//enables coordinates as a mouse tooltip
		HAS_RADAR,		//lets the interface display loaded entities
		HAS_ORES		//like HAS_MAP but only shows ores
	}
	
	public static enum CoordActions {
		HAS_Y		//enables the Y-coord field which is disabled by default
	}
	
	public static enum Interfaces {
		NONE,		//does not interact with any sat interface (i.e. asteroid miners)
		SAT_PANEL,	//allows to interact with the sat interface panel (for graphical applications)
		SAT_COORD	//allows to interact with the sat coord remote (for teleportation or other coord related actions)
	}

	public List<InterfaceActions> ifaceAcs = new ArrayList<InterfaceActions>();
	public List<CoordActions> coordAcs = new ArrayList<CoordActions>();
	public Interfaces satIface = Interfaces.NONE;
	
	public static void register() {

		registerSatellite(SatelliteMapper.class, ModItems.sat_mapper);
		registerSatellite(SatelliteScanner.class, ModItems.sat_scanner);
		registerSatellite(SatelliteRadar.class, ModItems.sat_radar);
		registerSatellite(SatelliteLaser.class, ModItems.sat_laser);
		registerSatellite(SatelliteResonator.class, ModItems.sat_resonator);
		registerSatellite(SatelliteRelay.class, ModItems.sat_foeq);
		registerSatellite(SatelliteMiner.class, ModItems.sat_miner);
		registerSatellite(SatelliteHorizons.class, ModItems.sat_gerald);
	}
	
	private static void registerSatellite(final Class<? extends Satellite> sat, final Item item) {

		satellites.add(sat);
		itemToClass.put(item, sat);
	}
	
	public static void orbit(final World world, final int id, final int freq, final double x, final double y, final double z) {
		
		final Satellite sat = create(id);
		if(sat != null && !world.isRemote) {
			final SatelliteSavedData data = SatelliteSavedData.getData(world);
			data.sats.put(freq, sat);
			sat.onOrbit(world, x, y, z);
			data.markDirty();
		}
	}
	
	public static Satellite create(final int id) {
		
		Satellite sat = null;
		
		try {
			final Class<? extends Satellite> c = satellites.get(id);
			sat = c.newInstance();
		} catch(final Exception ex) {
		}
		
		return sat;
	}
	
	public static int getIDFromItem(final Item item) {
		
		final Class<? extends Satellite> sat = itemToClass.get(item);
		final int i = satellites.indexOf(sat);
		
		return i;
	}
	
	public int getID() {
		return satellites.indexOf(this.getClass());
	}
	
	public void writeToNBT(final NBTTagCompound nbt) { }
	
	public void readFromNBT(final NBTTagCompound nbt) { }
	
	/**
	 * Called when the satellite reaches space, used to trigger achievements and other funny stuff.
	 * @param x posX of the rocket
	 * @param y ditto
	 * @param z ditto
	 */
	public void onOrbit(final World world, final double x, final double y, final double z) { }
	
	/**
	 * Called by the sat interface when clicking on the screen
	 * @param x the x-coordinate translated from the on-screen coords to actual world coordinates
	 * @param z ditto
	 */
	public void onClick(final World world, final int x, final int z) { }
	
	/**
	 * Called by the coord sat interface
	 * @param x the specified x-coordinate
	 * @param y ditto
	 * @param z ditto
	 */
	public void onCoordAction(final World world, final EntityPlayer player, final int x, final int y, final int z) { }
}
