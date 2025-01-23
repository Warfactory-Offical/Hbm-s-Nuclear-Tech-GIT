package com.hbm.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class AudioDynamic extends MovingSound {

	public float intendedVolume;

	protected AudioDynamic(final SoundEvent loc, final SoundCategory cat) {
		super(loc, cat);
		this.repeat = true;
		this.attenuationType = ISound.AttenuationType.NONE;
		this.intendedVolume = 10;
	}
	
	public void setPosition(final float x, final float y, final float z) {
		this.xPosF = x;
		this.yPosF = y;
		this.zPosF = z;
	}

	public void setAttenuation(final ISound.AttenuationType type){
		this.attenuationType = type;
		volume = intendedVolume;
	}
	
	@Override
	public void update() {
		final EntityPlayerSP player = Minecraft.getMinecraft().player;
		float f = 0;
		if(player != null) {
			if(attenuationType == ISound.AttenuationType.LINEAR){
				/*float f3 = intendedVolume;
                float f2 = 16.0F;

                if (f3 > 1.0F)
                {
                    f2 *= f3;
                }
                f = (float)Math.sqrt(Math.pow(xPosF - player.posX, 2) + Math.pow(yPosF - player.posY, 2) + Math.pow(zPosF - player.posZ, 2));
                volume = 1-f2/f;
                System.out.println(volume);*/
			} else {
				f = (float)Math.sqrt(Math.pow(xPosF - player.posX, 2) + Math.pow(yPosF - player.posY, 2) + Math.pow(zPosF - player.posZ, 2));
				volume = func(f, intendedVolume);
			}
		} else {
			volume = intendedVolume;
		}
	}
	
	public void start() {
		Minecraft.getMinecraft().getSoundHandler().playSound(this);
	}
	
	public void stop() {
		Minecraft.getMinecraft().getSoundHandler().stopSound(this);
	}
	
	public void setVolume(final float volume) {
		this.intendedVolume = volume;
	}
	
	public void setPitch(final float pitch) {
		this.pitch = pitch;
	}
	
	public float func(final float f, final float v) {
		return (f / v) * -2 + 2;
	}
}
