package com.hbm.world.generator;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.world.World;

public class TimedGenerator {

	private static final HashMap<Integer, ArrayList<ITimedJob>> operations = new HashMap<>();

	public static void automaton(final World world, final int amount) {

		final ArrayList<ITimedJob> list = operations.get(world.provider.getDimension());

		if(list == null)
			return;

		final long start = System.currentTimeMillis();

		while(start + 10 > System.currentTimeMillis()) {

			if(list.isEmpty())
				return;

			final ITimedJob entry = list.get(0);
			list.remove(0);

			entry.work();
		}
	}

	public static void addOp(final World world, final ITimedJob job) {

		ArrayList<ITimedJob> list = operations.get(world.provider.getDimension());

		if(list == null) {
			list = new ArrayList<>();
			operations.put(world.provider.getDimension(), list);
		}

		list.add(job);
	}
	
	//should i be doing this? probably not, but watch me go
	//Drillgon200: I mean, a standard java Runnable probably would have worked exactly the same.
	public interface ITimedJob {

		public void work();

	}
}
