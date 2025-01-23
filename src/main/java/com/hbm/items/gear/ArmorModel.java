package com.hbm.items.gear;

import com.hbm.items.ModItems;
import com.hbm.lib.RefStrings;
import com.hbm.render.RenderHelper;
import com.hbm.render.model.ModelCloak;
import com.hbm.render.model.ModelGoggles;
import com.hbm.render.model.ModelHat;
import com.hbm.util.ItemStackUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ArmorModel extends ItemArmor {

	@SideOnly(Side.CLIENT)
	private ModelGoggles modelGoggles;
	@SideOnly(Side.CLIENT)
	private ModelCloak modelCloak;
	/*@SideOnly(Side.CLIENT)
	private ModelOxygenMask modelOxy;*/
	@SideOnly(Side.CLIENT)
	private ModelHat modelHat;
	
	private final ResourceLocation goggleBlur0 = new ResourceLocation(RefStrings.MODID + ":textures/misc/overlay_goggles_0.png");
	private final ResourceLocation goggleBlur1 = new ResourceLocation(RefStrings.MODID + ":textures/misc/overlay_goggles_1.png");
	private final ResourceLocation goggleBlur2 = new ResourceLocation(RefStrings.MODID + ":textures/misc/overlay_goggles_2.png");
	private final ResourceLocation goggleBlur3 = new ResourceLocation(RefStrings.MODID + ":textures/misc/overlay_goggles_3.png");
	private final ResourceLocation goggleBlur4 = new ResourceLocation(RefStrings.MODID + ":textures/misc/overlay_goggles_4.png");
	private final ResourceLocation goggleBlur5 = new ResourceLocation(RefStrings.MODID + ":textures/misc/overlay_goggles_5.png");

	public ArmorModel(final ArmorMaterial materialIn, final int renderIndexIn, final EntityEquipmentSlot equipmentSlotIn, final String s) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(CreativeTabs.COMBAT);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public boolean isValidArmor(final ItemStack stack, final EntityEquipmentSlot armorType, final Entity entity) {
		if (this == ModItems.goggles) {
			return armorType == EntityEquipmentSlot.HEAD;
		}
		if (this == ModItems.mask_damp) {
			return armorType == EntityEquipmentSlot.HEAD;
		}
		if (this == ModItems.mask_piss) {
			return armorType == EntityEquipmentSlot.HEAD;
		}
		// if (this == ModItems.cape_test) {
		// 	return armorType == EntityEquipmentSlot.CHEST;
		// }
		if (this == ModItems.cape_radiation) {
			return armorType == EntityEquipmentSlot.CHEST;
		}
		if (this == ModItems.cape_gasmask) {
			return armorType == EntityEquipmentSlot.CHEST;
		}
		if (this == ModItems.cape_schrabidium) {
			return armorType == EntityEquipmentSlot.CHEST;
		}
		if (this == ModItems.hat) {
			return armorType == EntityEquipmentSlot.HEAD;
		}
		return armorType == EntityEquipmentSlot.HEAD;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ModelBiped getArmorModel(final EntityLivingBase entityLiving, final ItemStack itemStack, final EntityEquipmentSlot armorSlot, final ModelBiped _default) {
		if (this == ModItems.goggles) {
			if (armorSlot == EntityEquipmentSlot.HEAD) {
				if (this.modelGoggles == null) {
					this.modelGoggles = new ModelGoggles();
				}
				return this.modelGoggles;
			}
		}
		if (/*this == ModItems.cape_test ||*/ this == ModItems.cape_radiation || this == ModItems.cape_gasmask || this == ModItems.cape_schrabidium) {
			if (armorSlot == EntityEquipmentSlot.CHEST) {
				if (this.modelCloak == null) {
					this.modelCloak = new ModelCloak();
				}
				return this.modelCloak;
			}
		}
		if (this == ModItems.hat) {
			if (armorSlot == EntityEquipmentSlot.HEAD) {
				if (this.modelHat == null) {
					this.modelHat = new ModelHat(0);
				}
				return this.modelHat;
			}
		}
		return null;
	}
	
	@Override
	public String getArmorTexture(final ItemStack stack, final Entity entity, final EntityEquipmentSlot slot, final String type) {
		if (stack.getItem() == ModItems.goggles) {
			return "hbm:textures/armor/Goggles.png";
		}
		if (stack.getItem() == ModItems.mask_damp) {
			return "hbm:textures/armor/rag_damp.png";
		}
		if (stack.getItem() == ModItems.mask_piss) {
			return "hbm:textures/armor/rag_piss.png";
		}
		if (stack.getItem() == ModItems.cape_radiation) {
			return "hbm:textures/models/capes/CapeRadiation.png";
		}
		if (stack.getItem() == ModItems.cape_gasmask) {
			return "hbm:textures/models/capes/CapeGasMask.png";
		}
		if (stack.getItem() == ModItems.cape_schrabidium) {
			return "hbm:textures/models/capes/CapeSchrabidium.png";
		}
		return "hbm:textures/models/capes/CapeUnknown.png";
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void renderHelmetOverlay(final ItemStack stack, final EntityPlayer player, final ScaledResolution resolution, final float partialTicks) {
		if(this != ModItems.goggles)
    		return;
    	

        GlStateManager.disableDepth();
        GlStateManager.depthMask(false);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableAlpha();
        
        if(this == ModItems.goggles) {
        	switch((int)((double)stack.getItemDamage() / (double)stack.getMaxDamage() * 6D)) {
        	case 0:
            	Minecraft.getMinecraft().getTextureManager().bindTexture(goggleBlur0); break;
        	case 1:
            	Minecraft.getMinecraft().getTextureManager().bindTexture(goggleBlur1); break;
        	case 2:
            	Minecraft.getMinecraft().getTextureManager().bindTexture(goggleBlur2); break;
        	case 3:
            	Minecraft.getMinecraft().getTextureManager().bindTexture(goggleBlur3); break;
        	case 4:
            	Minecraft.getMinecraft().getTextureManager().bindTexture(goggleBlur4); break;
        	case 5:
            	Minecraft.getMinecraft().getTextureManager().bindTexture(goggleBlur5); break;
        	default:
            	Minecraft.getMinecraft().getTextureManager().bindTexture(goggleBlur5); break;
        	}
        }
        
        RenderHelper.startDrawingTexturedQuads();
        RenderHelper.addVertexWithUV(0.0D, resolution.getScaledHeight(), -90.0D, 0.0D, 1.0D);
        RenderHelper.addVertexWithUV(resolution.getScaledWidth(), resolution.getScaledHeight(), -90.0D, 1.0D, 1.0D);
        RenderHelper.addVertexWithUV(resolution.getScaledWidth(), 0.0D, -90.0D, 1.0D, 0.0D);
        RenderHelper.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        RenderHelper.draw();
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		if (stack.getItem() == ModItems.cape_radiation) {
			list.add("Avalible for everyone");
		}
		if (stack.getItem() == ModItems.cape_gasmask) {
			list.add("Avalible for everyone");
		}
		if (stack.getItem() == ModItems.cape_schrabidium) {
			list.add("Avalible for everyone");
		}
		if (stack.getItem() == ModItems.goggles) {
			list.add("§3[Blinding] §7protection");
		}
	}

	@Override
	public void onUpdate(final ItemStack stack, final World world, final Entity e, final int itemSlot, final boolean isSelected) {
		if(!(e instanceof EntityPlayer))
			return;
		if(!world.isRemote){
			if(stack.getItem() == ModItems.mask_damp || stack.getItem() == ModItems.mask_piss){
				if(world.rand.nextInt(8192) == 0){
					stack.shrink(1);
					((EntityPlayer)e).dropItem(ItemStackUtil.itemStackFrom(ModItems.mask_rag), true);
				}
			}
		}
		super.onUpdate(stack, world, e, itemSlot, isSelected);
	}
}
