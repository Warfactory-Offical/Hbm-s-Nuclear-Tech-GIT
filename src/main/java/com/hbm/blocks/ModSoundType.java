package com.hbm.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.util.SoundEvent;

public class ModSoundType extends SoundType {

	public ModSoundType(final SoundEvent sound, final float volumeIn, final float pitchIn){
		super(volumeIn, pitchIn, sound, sound, sound, sound, sound);
	}
	
	@Override
	public SoundEvent getBreakSound(){
		return super.getBreakSound();
	}
	
	@Override
	public SoundEvent getStepSound(){
		return super.getStepSound();
	}

}
