package net.minecraft.world;

public class HbmWorldUtility {

	public static void setImmediateScheduledUpdates(final World world, final boolean update){
		world.scheduledUpdatesAreImmediate = update;
	}

	public static World getProviderWorld(final WorldProvider provider){
		return provider.world;
	}
}
