package com.hbm.sound;

import java.util.ArrayList;
import java.util.List;

import com.hbm.tileentity.machine.TileEntityMachineAssembler;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundEvent;

public class SoundLoopAssembler extends SoundLoopMachine {
	
	public static List<SoundLoopAssembler> list = new ArrayList<SoundLoopAssembler>();

	public SoundLoopAssembler(final SoundEvent path, final TileEntity te) {
		super(path, te);
		list.add(this);
		
	}

	@Override
	public void update() {
		super.update();
		
		if(te instanceof TileEntityMachineAssembler drill) {

            if(this.volume != 3)
				volume = 3;
			
			if(!drill.isProgressing)
				this.donePlaying = true;
		}
	}
	
	public TileEntity getTE() {
		return te;
	}

}