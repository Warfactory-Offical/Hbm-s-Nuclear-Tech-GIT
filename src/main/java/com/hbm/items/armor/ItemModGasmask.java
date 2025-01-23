package com.hbm.items.armor;

import api.hbm.item.IGasMask;
import com.hbm.handler.ArmorModHandler;
import com.hbm.handler.ArmorUtil;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import com.hbm.render.model.ModelM65;
import com.hbm.util.ArmorRegistry.HazardClass;
import com.hbm.util.I18nUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent.Pre;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemModGasmask extends ItemArmorMod implements IGasMask {

	@SideOnly(Side.CLIENT)
	private ModelM65 modelM65;
	
	private final ResourceLocation tex = new ResourceLocation("hbm:textures/armor/ModelM65.png");
	private final ResourceLocation tex_mono = new ResourceLocation("hbm:textures/armor/ModelM65Mono.png");
	
	public ItemModGasmask(final String s) {
		super(ArmorModHandler.helmet_only, true, false, false, false, s);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn){
		if(this == ModItems.attachment_mask)
			list.add(TextFormatting.GREEN + "Gas protection");
		
		list.add("");
		super.addInformation(stack, worldIn, list, flagIn);

		ArmorUtil.addGasMaskTooltip(stack, worldIn, list, flagIn);

		final List<HazardClass> haz = getBlacklist(stack);
		
		if(!haz.isEmpty()) {
			list.add("§c"+I18nUtil.resolveKey("hazard.neverProtects"));
			
			for(final HazardClass clazz : haz) {
				list.add("§4 -" + I18nUtil.resolveKey(clazz.lang));
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addDesc(final List<String> list, final ItemStack stack, final ItemStack armor){
		list.add("§a  " + stack.getDisplayName() + " (gas protection)");
		ArmorUtil.addGasMaskTooltip(stack, null, list, ITooltipFlag.TooltipFlags.NORMAL);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void modRender(final Pre event, final ItemStack armor){
		if(this.modelM65 == null) {
			this.modelM65 = new ModelM65();
		}
		final RenderPlayer renderer = event.getRenderer();
		final ModelBiped model = renderer.getMainModel();
		final EntityPlayer player = event.getEntityPlayer();

		copyRot(modelM65, model);

		final float interp = event.getPartialRenderTick();
		final float yawWrapped = MathHelper.wrapDegrees(player.prevRotationYawHead + (player.rotationYawHead - player.prevRotationYawHead) * interp + 180);
		final float pitch = player.rotationPitch;

		if(this == ModItems.attachment_mask)
			Minecraft.getMinecraft().renderEngine.bindTexture(tex);
		if(this == ModItems.attachment_mask_mono)
			Minecraft.getMinecraft().renderEngine.bindTexture(tex_mono);
		
		final EntityPlayer me = MainRegistry.proxy.me();
		final boolean isMe = player == me;
		if(!isMe){
			GL11.glPushMatrix();
			offset(player, me, interp);
		}
		if(model.isSneak) GL11.glTranslatef(0, -0.1875F, 0);
		modelM65.render(player, 0F, 0F, 0, yawWrapped, pitch, 0.0625F);
		if(!isMe){
			GL11.glPopMatrix();
		}
	}

	@Override
	public ArrayList<HazardClass> getBlacklist(final ItemStack stack) {
		
		if(this == ModItems.attachment_mask_mono) {
			return new ArrayList<HazardClass>(Arrays.asList(HazardClass.GAS_CHLORINE, HazardClass.GAS_CORROSIVE, HazardClass.NERVE_AGENT, HazardClass.BACTERIA));
		} else {
			return new ArrayList<HazardClass>(Arrays.asList(HazardClass.GAS_CORROSIVE, HazardClass.NERVE_AGENT));
		}
	}

	@Override
	public ItemStack getFilter(final ItemStack stack) {
		return ArmorUtil.getGasMaskFilter(stack);
	}

	@Override
	public void installFilter(final ItemStack stack, final ItemStack filter) {
		ArmorUtil.installGasMaskFilter(stack, filter);
	}

	@Override
	public void damageFilter(final ItemStack stack, final int damage) {
		ArmorUtil.damageGasMaskFilter(stack, damage);
	}

	@Override
	public boolean isFilterApplicable(final ItemStack stack, final ItemStack filter) {
		return true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(final World world, final EntityPlayer player, final EnumHand hand) {
		
		if(player.isSneaking()) {
			final ItemStack stack = player.getHeldItem(hand);
			final ItemStack filter = this.getFilter(stack);
			
			if(filter != null) {
				ArmorUtil.removeFilter(stack);
				
				if(!player.inventory.addItemStackToInventory(filter)) {
					player.dropItem(filter, true, false);
				}
			}
		}
		
		return super.onItemRightClick(world, player, hand);
	}
}
