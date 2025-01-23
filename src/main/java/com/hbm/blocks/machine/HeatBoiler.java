package com.hbm.blocks.machine;
import com.hbm.util.ItemStackUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ILookOverlay;
import com.hbm.blocks.ITooltipProvider;
import com.hbm.inventory.HeatRecipes;
import com.hbm.lib.ForgeDirection;
import com.hbm.items.machine.ItemForgeFluidIdentifier;
import com.hbm.tileentity.TileEntityProxyCombo;
import com.hbm.tileentity.machine.TileEntityHeatBoiler;
import com.hbm.util.I18nUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

public class HeatBoiler extends BlockDummyable implements ILookOverlay, ITooltipProvider {

    public HeatBoiler(final Material materialIn, final String s) {
        super(materialIn, s);
    }

    @Override
    public TileEntity createNewTileEntity(final World world, final int meta) {

        if(meta >= 12) return new TileEntityHeatBoiler();
        if(meta >= 6) return new TileEntityProxyCombo(false, false, true);

        return null;
    }

    @Override
    public int[] getDimensions() {
        return new int[] {3, 0, 1, 1, 1, 1};
    }

    @Override
    public int getOffset() {
        return 1;
    }

    @Override
    public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
        return Item.getItemFromBlock(ModBlocks.heat_boiler);
    }

    @Override
    public ItemStack getPickBlock(final IBlockState state, final RayTraceResult target, final World world, final BlockPos pos, final EntityPlayer player) {
        return ItemStackUtil.itemStackFrom(ModBlocks.heat_boiler);
    }

    @Override
    public boolean onBlockActivated(final World world, final BlockPos pos1, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {

        if(!world.isRemote && !player.isSneaking()) {

            if(!player.getHeldItem(hand).isEmpty() && player.getHeldItem(hand).getItem() instanceof ItemForgeFluidIdentifier) {
                final int[] pos = this.findCore(world, pos1.getX(), pos1.getY(), pos1.getZ());
                if(pos == null)
                    return false;

                final TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));

                if(!(te instanceof TileEntityHeatBoiler boiler))
                    return false;

                final Fluid type = ItemForgeFluidIdentifier.getType(player.getHeldItem(hand));
                if(!HeatRecipes.hasBoilRecipe(type)){
                    player.sendMessage(new TextComponentString("§cNo recipe found for §e"+type.getLocalizedName(new FluidStack(type, 1))));
                    return false;
                }
                boiler.setTankType(0, type);
                boiler.markDirty();
                player.sendMessage(new TextComponentString("§eRecipe changed to §a"+type.getLocalizedName(new FluidStack(type, 1))));

                return true;
            }
            return false;

        } else {
            return true;
        }
    }



    @Override
    protected void fillSpace(final World world, int x, final int y, int z, final ForgeDirection dir, final int o) {
        super.fillSpace(world, x, y, z, dir, o);

        x = x + dir.offsetX * o;
        z = z + dir.offsetZ * o;

        final ForgeDirection rot = dir.getRotation(ForgeDirection.UP);

        this.makeExtra(world, x + rot.offsetX, y, z + rot.offsetZ); //these add the side ports
        this.makeExtra(world, x - rot.offsetX, y, z - rot.offsetZ);
        this.makeExtra(world, x, y + 3, z); 
    }

    @Override
    public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
        this.addStandardInfo(list);
        super.addInformation(stack, worldIn, list, flagIn);
    }

    @Override
    public void printHook(final Pre event, final World world, final int x, final int y, final int z) {
        final int[] pos = this.findCore(world, x, y, z);

        if(pos == null)
            return;

        final TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));

        if(!(te instanceof TileEntityHeatBoiler boiler))
            return;

        final List<String> text = new ArrayList();

        for(int i = 0; i < boiler.types.length; i++)
            if(boiler.types[i] != null)
                text.add((i < 1 ? "§a-> " : "§c<- ") + "§r" + boiler.types[i].getLocalizedName(new FluidStack(boiler.types[i], 1)) + ": " + boiler.tanks[i].getFluidAmount() + "/" + boiler.tanks[i].getCapacity() + "mB");

        ILookOverlay.printGeneric(event, I18nUtil.resolveKey(getTranslationKey() + ".name"), 0xffff00, 0x404000, text);
    }
}
