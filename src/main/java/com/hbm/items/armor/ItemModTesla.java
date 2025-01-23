package com.hbm.items.armor;

import com.hbm.handler.ArmorModHandler;
import com.hbm.main.MainRegistry;
import com.hbm.render.model.ModelBackTesla;
import com.hbm.tileentity.machine.TileEntityTesla;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent.Pre;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class ItemModTesla extends ItemArmorMod {

	private ModelBackTesla modelTesla;
	public List<double[]> targets = new ArrayList<>();
	
	public ItemModTesla(final String s) {
		super(ArmorModHandler.plate_only, false, true, false, false, s);
	}
    
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		list.add(TextFormatting.YELLOW + "Zaps nearby entities (requires full electric set)");
		list.add("");
		super.addInformation(stack, worldIn, list, flagIn);
	}

	@SideOnly(Side.CLIENT)
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor) {
		list.add(TextFormatting.YELLOW + stack.getDisplayName() + " (zaps nearby entities)");
	}
	
	@Override
	public void modUpdate(final EntityLivingBase entity, final ItemStack armor) {
		
		if(!entity.world.isRemote && entity instanceof EntityPlayer && armor.getItem() instanceof ArmorFSBPowered && ArmorFSBPowered.hasFSBArmor(entity)) {
			targets = TileEntityTesla.zap(entity.world, entity.posX, entity.posY + 1.25, entity.posZ, 5, entity);
			
			if(targets != null && !targets.isEmpty() && entity.getRNG().nextInt(5) == 0) {
				armor.damageItem(1, entity);
			}
		}
	}

	@Override
	public void modRender(final Pre event, final ItemStack armor){
		if(this.modelTesla == null) {
			this.modelTesla = new ModelBackTesla();
		}
		
		final EntityPlayer player = event.getEntityPlayer();

		modelTesla.isSneak = player.isSneaking();
		
		final float interp = event.getPartialRenderTick();
		final float yaw = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset)*interp;
		final float yawWrapped = MathHelper.wrapDegrees(yaw+180);
		final float pitch = player.rotationPitch;

		final EntityPlayer me = MainRegistry.proxy.me();
		final boolean isMe = player == me;
		if(!isMe){
			GL11.glPushMatrix();
			offset(player, me, interp);
		}
		modelTesla.render(event.getEntityPlayer(), 0.0F, 0.0F, 0, yawWrapped, pitch, 0.0625F);
		if(!isMe){
			GL11.glPopMatrix();
		}
	}
}