package com.hbm.sound;

import java.util.ArrayList;
import java.util.List;

import com.hbm.tileentity.machine.TileEntityFEL;
import com.hbm.tileentity.machine.TileEntityMachineMiningLaser;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundEvent;

public class SoundLoopFel extends SoundLoopMachine {
	
	public static List<SoundLoopFel> list = new ArrayList<SoundLoopFel>();

	public SoundLoopFel(final SoundEvent path, final TileEntity te) {
		super(path, te);
		list.add(this);
	}

	@Override
	public void update() {
		super.update();
		
		if(te instanceof TileEntityFEL plant) {

            if(this.volume != 3)
				volume = 3;
			
			if(!plant.isOn)
				this.donePlaying = true;
		}

		if(te instanceof TileEntityMachineMiningLaser plant) {

            if(this.volume != 3)
				volume = 3;
			
			if(!plant.isOn)
				this.donePlaying = true;
		}
	}
	
	public TileEntity getTE() {
		return te;
	}

}