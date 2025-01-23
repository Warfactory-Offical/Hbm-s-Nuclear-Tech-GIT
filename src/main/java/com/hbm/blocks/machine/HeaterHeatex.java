package com.hbm.blocks.machine;

import java.util.ArrayList;
import java.util.List;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ILookOverlay;
import com.hbm.blocks.ITooltipProvider;
import com.hbm.lib.ForgeDirection;
import com.hbm.tileentity.TileEntityProxyCombo;
import com.hbm.tileentity.machine.TileEntityHeaterHeatex;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HeaterHeatex extends BlockDummyable implements ITooltipProvider, ILookOverlay {

    public HeaterHeatex(final Material mat, final String s) {
        super(Material.IRON, s);
    }

    @Override
    public TileEntity createNewTileEntity(final World world, final int meta) {

        if (meta >= 12) return new TileEntityHeaterHeatex();
        if (hasExtra(meta)) {
            return new TileEntityProxyCombo(false, false, true);
        }

        return null;
    }

    @Override
    public int[] getDimensions() {
        return new int[]{0, 0, 1, 1, 1, 1};
    }

    @Override
    public int getOffset() {
        return 1;
    }

    @Override
    public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
        return standardOpenBehavior(world, pos.getX(), pos.getY(), pos.getZ(), player, 0);
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
        this.addStandardInfo(list);
        super.addInformation(stack, worldIn, list, flagIn);
    }

    @Override
    protected void fillSpace(final World world, int x, final int y, int z, final ForgeDirection dir, final int o) {
        super.fillSpace(world, x, y, z, dir, o);

        x += dir.offsetX * o;
        z += dir.offsetZ * o;

        this.makeExtra(world, x + 1, y, z + 1);
        this.makeExtra(world, x + 1, y, z - 1);
        this.makeExtra(world, x - 1, y, z + 1);
        this.makeExtra(world, x - 1, y, z - 1);
    }

    @Override
    public void printHook(final Pre event, final World world, final int x, final int y, final int z) {
        final int[] pos = this.findCore(world, x, y, z);

        if (pos == null)
            return;

        final TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));

        if (!(te instanceof TileEntityHeaterHeatex heater))
            return;

        final List<String> text = new ArrayList<>();
        text.add(String.format("%,d", heater.heatEnergy) + " TU");
        text.add("§c<- §r"+String.format("%,d", heater.heatGen) + " TU/t");
        ILookOverlay.printGeneric(event, I18nUtil.resolveKey(getTranslationKey() + ".name"), 0xffff00, 0x404000, text);
    }
}
