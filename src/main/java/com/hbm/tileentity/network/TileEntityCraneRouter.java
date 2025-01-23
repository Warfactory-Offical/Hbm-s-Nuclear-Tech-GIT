package com.hbm.tileentity.network;

import com.hbm.interfaces.IControlReceiver;
import com.hbm.inventory.container.ContainerCraneRouter;
import com.hbm.inventory.gui.GUICraneRouter;
import com.hbm.modules.ModulePatternMatcher;
import com.hbm.tileentity.IGUIProvider;
import com.hbm.tileentity.TileEntityMachineBase;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityCraneRouter extends TileEntityMachineBase implements IGUIProvider, IControlReceiver, ITickable {
    public ModulePatternMatcher[] patterns = new ModulePatternMatcher[6]; //why did i make six matchers???
    public int[] modes = new int[6];
    public static final int MODE_NONE = 0;
    public static final int MODE_WHITELIST = 1;
    public static final int MODE_BLACKLIST = 2;
    public static final int MODE_WILDCARD = 3;

    public TileEntityCraneRouter() {
        super(5 * 6);

        for(int i = 0; i < patterns.length; i++) {
            patterns[i] = new ModulePatternMatcher(5);
        }
    }

    @Override
    public String getName() {
        return "container.craneRouter";
    }

    @Override
    public void update() {
        if(!world.isRemote) {

            final NBTTagCompound data = new NBTTagCompound();
            for(int i = 0; i < patterns.length; i++) {
                final NBTTagCompound compound = new NBTTagCompound();
                patterns[i].writeToNBT(compound);
                data.setTag("pattern" + i, compound);
            }
            data.setIntArray("modes", this.modes);
            this.networkPack(data, 15);
        }
    }

    @Override
    public void networkUnpack(final NBTTagCompound data) {
        super.networkUnpack(data);

        for(int i = 0; i < patterns.length; i++) {
            final NBTTagCompound compound = data.getCompoundTag("pattern" + i);
            patterns[i].readFromNBT(compound);
        }
        this.modes = data.getIntArray("modes");
    }

    @Override
    public Container provideContainer(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        return new ContainerCraneRouter(player.inventory, this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen provideGUI(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        return new GUICraneRouter(player.inventory, this);
    }

    public void nextMode(final int index) {

        final int matcher = index / 5;
        final int mIndex = index % 5;

        this.patterns[matcher].nextMode(world, inventory.getStackInSlot(index), mIndex);
    }

    public void initPattern(final ItemStack stack, final int index) {

        final int matcher = index / 5;
        final int mIndex = index % 5;

        this.patterns[matcher].initPatternSmart(world, stack, mIndex);
    }

    @Override
    public void readFromNBT(final NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        for(int i = 0; i < patterns.length; i++) {
            final NBTTagCompound compound = nbt.getCompoundTag("pattern" + i);
            patterns[i].readFromNBT(compound);
        }
        this.modes = nbt.getIntArray("modes");
    }

    @Override
    public NBTTagCompound writeToNBT(final NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        for(int i = 0; i < patterns.length; i++) {
            final NBTTagCompound compound = new NBTTagCompound();
            patterns[i].writeToNBT(compound);
            nbt.setTag("pattern" + i, compound);
        }
        nbt.setIntArray("modes", this.modes);
        return nbt;
    }

    @Override
    public boolean hasPermission(final EntityPlayer player) {
        final int xCoord = pos.getX();
        final int yCoord = pos.getY();
        final int zCoord = pos.getZ();
        return new Vec3d(xCoord - player.posX, yCoord - player.posY, zCoord - player.posZ).length() < 20;
    }

    @Override
    public void receiveControl(final NBTTagCompound data) {
        final int i = data.getInteger("toggle");
        modes[i]++;
        if(modes[i] > 3)
            modes [i] = 0;
    }
}
