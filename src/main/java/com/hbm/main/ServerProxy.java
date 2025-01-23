package com.hbm.main;

import com.hbm.handler.HbmKeybinds.EnumKeybind;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.sound.AudioWrapper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class ServerProxy
{
	public void registerRenderInfo() { }
	public void registerTileEntitySpecialRenderer() { }
	public void registerItemRenderer() { }
	public void registerEntityRenderer() { }
	public void registerBlockRenderer() { }
	
	public void particleControl(final double x, final double y, final double z, final int type) { }

	public void spawnParticle(final double x, final double y, final double z, final String type, final float[] args) { }
	
	public void spawnSFX(final World world, final double posX, final double posY, final double posZ, final int type, final Vec3 payload) { }

	public void effectNT(final NBTTagCompound data) { }
	
	public void registerMissileItems(final IRegistry<ModelResourceLocation, IBakedModel> reg) { }

	public AudioWrapper getLoopedSound(final SoundEvent sound, final SoundCategory cat, final float x, final float y, final float z, final float volume, final float pitch) { return null; }
	
	public AudioWrapper getLoopedSoundStartStop(final World world, final SoundEvent sound, final SoundEvent start, final SoundEvent stop, final SoundCategory cat, final float x, final float y, final float z, final float volume, final float pitch){return null;}
	
	public void preInit(final FMLPreInitializationEvent evt) {}
	
	public void checkGLCaps(){}

    public File getDataDir(){
		return FMLCommonHandler.instance().getMinecraftServerInstance().getDataDirectory();
	}
	
	public void postInit(final FMLPostInitializationEvent e){
	}
	
	public boolean opengl33(){
		return true;//Doesn't matter for servers, and this won't print an error message.
	}
	
	public boolean getIsKeyPressed(final EnumKeybind key) {
		return false;
	}
	public EntityPlayer me() {
		return null;
	}
	
	public float partialTicks(){
		return 1;
	}

    public void playSound(final String sound, final Object data) { }
	
	public void displayTooltip(final String msg) { }
	
	public void setRecoil(final float rec){}

    public boolean isVanished(final Entity e) {
		return false;
	}
}