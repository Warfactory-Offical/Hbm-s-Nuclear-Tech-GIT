package com.hbm.blocks.machine;

import api.hbm.block.IToolable;
import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ILookOverlay;
import com.hbm.blocks.ITooltipProvider;
import com.hbm.inventory.FluidCombustionRecipes;
import com.hbm.items.tool.ItemTooling;
import com.hbm.lib.ForgeDirection;
import com.hbm.tileentity.TileEntityProxyCombo;
import com.hbm.tileentity.machine.TileEntityHeaterOilburner;
import com.hbm.util.I18nUtil;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class HeaterOilburner extends BlockDummyable implements ITooltipProvider, ILookOverlay, IToolable {
    public HeaterOilburner(final Material mat, final String s) {
        super(mat, s);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(final World worldIn, final int meta) {
        if (meta >= 12)
            return new TileEntityHeaterOilburner();

        if (hasExtra(meta) && meta - extra > 1)
            return new TileEntityProxyCombo(false, false, true);

        if (hasExtra(meta))
            return new TileEntityProxyCombo(false, false, false, true);

        return null;
    }

    @Override
    public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state, final EntityPlayer playerIn, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
        if(playerIn.getHeldItem(hand).getItem() instanceof ItemTooling tool){
            if(tool.getType() == ToolType.SCREWDRIVER || tool.getType() == ToolType.HAND_DRILL)
                return false;
        }
        return this.standardOpenBehavior(worldIn, pos.getX(), pos.getY(), pos.getZ(), playerIn, 0);
    }

    @Override
    public int[] getDimensions() {
        return new int[]{1, 0, 1, 1, 1, 1};
    }

    @Override
    public int getOffset() {
        return 1;
    }

    @Override
    protected void fillSpace(final World world, int x, final int y, int z, final ForgeDirection dir, final int o) {
        super.fillSpace(world, x, y, z, dir, o);

        x = x + dir.offsetX * o;
        z = z + dir.offsetZ * o;

        this.makeExtra(world, x + 1, y, z);
        this.makeExtra(world, x - 1, y, z);
        this.makeExtra(world, x, y, z + 1);
        this.makeExtra(world, x, y, z - 1);
        this.makeExtra(world, x, y + 1, z);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack stack, @Nullable final World player, final List<String> tooltip, final ITooltipFlag advanced) {
        this.addStandardInfo(tooltip);
    }

    @Override
    public void printHook(final Pre event, final World world, final int x, final int y, final int z) {
        final int[] pos = this.findCore(world, x, y, z);

        if (pos == null)
            return;

        final TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));

        if (!(te instanceof TileEntityHeaterOilburner heater))
            return;

        final List<String> text = new ArrayList();
        text.add(String.format("%,d", heater.heatEnergy) + " TU");
        text.add("§a-> §r" + heater.setting + " mB/t");
        final Fluid type = heater.fluidType;
        final int energy = FluidCombustionRecipes.getFlameEnergy(type);
        if (energy != 0) {
            final int heat = energy * heater.setting;
            text.add("§c<- §r" + String.format("%,d", heat) + " TU/t");
        }

        ILookOverlay.printGeneric(event, I18nUtil.resolveKey(getTranslationKey() + ".name"), 0xffff00, 0x404000, text);
    }

    @Override
    public boolean onScrew(final World world, final EntityPlayer player, final int x, final int y, final int z, final EnumFacing side, final float fX, final float fY, final float fZ, final EnumHand hand, final ToolType tool) {
        if (tool != ToolType.SCREWDRIVER && tool != ToolType.HAND_DRILL)
            return false;

        if (world.isRemote) return true;

        final int[] pos = this.findCore(world, x, y, z);

        if (pos == null) return false;

        final TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));

        if (!(te instanceof TileEntityHeaterOilburner tile)) return false;

        if(tool == ToolType.SCREWDRIVER)
            tile.toggleSettingUp();
        else
            tile.toggleSettingDown();
        tile.markDirty();

        return true;
    }
}
