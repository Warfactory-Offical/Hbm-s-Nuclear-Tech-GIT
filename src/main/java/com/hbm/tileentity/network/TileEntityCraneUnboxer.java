package com.hbm.tileentity.network;

import api.hbm.block.IConveyorBelt;
import com.hbm.entity.item.EntityMovingItem;
import com.hbm.inventory.container.ContainerCraneUnboxer;
import com.hbm.inventory.gui.GUICraneUnboxer;
import com.hbm.items.ModItems;
import com.hbm.lib.Library;
import com.hbm.tileentity.IGUIProvider;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileEntityCraneUnboxer extends TileEntityCraneBase implements IGUIProvider {
    private int tickCounter = 0;
    public static int[] allowed_slots = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    public TileEntityCraneUnboxer() {
        super(23);
    }

    @Override
    public String getName() {
        return "container.craneUnboxer";
    }

    @Override
    public int[] getAccessibleSlotsFromSide(final EnumFacing e) {
        return allowed_slots;
    }

    @Override
    public void update() {
        super.update();
        if(!world.isRemote) {
            tickCounter++;

            final int xCoord = pos.getX();
            final int yCoord = pos.getY();
            final int zCoord = pos.getZ();
            int delay = 20;
            if (inventory.getStackInSlot(22) != null && inventory.getStackInSlot(22) != ItemStack.EMPTY) {
                if (inventory.getStackInSlot(22).getItem() == ModItems.upgrade_ejector_1) {
                    delay = 10;
                } else if (inventory.getStackInSlot(22).getItem() == ModItems.upgrade_ejector_2) {
                    delay = 5;
                } else if (inventory.getStackInSlot(22).getItem() == ModItems.upgrade_ejector_3) {
                    delay = 2;
                }
            }

            if (tickCounter >= delay && !this.world.isBlockPowered(pos)) {
                tickCounter = 0;
                int amount = 1;

                if (inventory.getStackInSlot(21) != null && inventory.getStackInSlot(21) != ItemStack.EMPTY) {
                    if (inventory.getStackInSlot(21).getItem() == ModItems.upgrade_stack_1) {
                        amount = 4;
                    } else if (inventory.getStackInSlot(21).getItem() == ModItems.upgrade_stack_2) {
                        amount = 16;
                    } else if (inventory.getStackInSlot(21).getItem() == ModItems.upgrade_stack_3) {
                        amount = 64;
                    }
                }

                final EnumFacing inputSide = getOutputSide(); // note the switcheroo!
                final Block b = world.getBlockState(pos.offset(inputSide)).getBlock();

                if (b instanceof IConveyorBelt belt) {

                    for (final int index : allowed_slots) {
                        final ItemStack stack = inventory.getStackInSlot(index);

                        if (stack != ItemStack.EMPTY) {

                            final int toSend = Math.min(amount, stack.getCount());
                            final ItemStack cStack = stack.copy();
                            stack.shrink(toSend);
                            if (stack.getCount() == 0)
                                inventory.setStackInSlot(index, ItemStack.EMPTY);
                            cStack.setCount(toSend);

                            final EntityMovingItem moving = new EntityMovingItem(world);
                            final Vec3d pos = new Vec3d(xCoord + 0.5 + inputSide.getDirectionVec().getX() * 0.55, yCoord + 0.5 + inputSide.getDirectionVec().getY() * 0.55, zCoord + 0.5 + inputSide.getDirectionVec().getZ() * 0.55);
                            final Vec3d snap = belt.getClosestSnappingPosition(world, new BlockPos(xCoord + inputSide.getDirectionVec().getX(), yCoord + inputSide.getDirectionVec().getY(), zCoord + inputSide.getDirectionVec().getZ()), pos);
                            moving.setPosition(snap.x, snap.y, snap.z);
                            moving.setItemStack(cStack);
                            world.spawnEntity(moving);
                            break;
                        }
                    }
                }
            }
        }
    }

    public boolean tryFillTeDirect(final ItemStack stack){
        return tryInsertItemCap(inventory, stack);
    }

    public boolean tryInsertItemCap(final IItemHandler chest, final ItemStack stack) {
        //Check if we have something to output
        if(stack.isEmpty())
            return false;

        for(final int i: allowed_slots) {

            final ItemStack outputStack = stack.copy();
            if(outputStack.isEmpty() || outputStack.getCount() == 0)
                return true;

            final ItemStack chestItem = chest.getStackInSlot(i).copy();
            if(chestItem.isEmpty() || (Library.areItemStacksCompatible(outputStack, chestItem, false) && chestItem.getCount() < chestItem.getMaxStackSize())) {
                final int fillAmount = Math.min(chestItem.getMaxStackSize()-chestItem.getCount(), outputStack.getCount());

                outputStack.setCount(fillAmount);

                final ItemStack rest = chest.insertItem(i, outputStack, true);
                if(rest.getItem() == Item.getItemFromBlock(Blocks.AIR)){
                    stack.shrink(outputStack.getCount());
                    chest.insertItem(i, outputStack, false);
                }
            }
        }

        return false;
    }

    @Override
    public boolean isItemValidForSlot(final int i, final ItemStack itemStack) {
        return true;
    }

    @Override
    public boolean canExtractItem(final int i, final ItemStack itemStack, final int j) {
        return true;
    }

    @Override
    public Container provideContainer(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {

        return new ContainerCraneUnboxer(player.inventory, this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen provideGUI(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        return new GUICraneUnboxer(player.inventory, this);
    }
}
