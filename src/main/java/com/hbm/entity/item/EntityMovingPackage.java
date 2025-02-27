package com.hbm.entity.item;

import api.hbm.block.IConveyorPackage;
import api.hbm.block.IEnterableBlock;
import com.hbm.util.ItemStackUtil;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityMovingPackage extends EntityMovingConveyorObject implements IConveyorPackage {

    protected ItemStack[] contents = new ItemStack[0];

    public EntityMovingPackage(final World p_i1582_1_) {
        super(p_i1582_1_);
        this.setSize(0.5F, 0.5F);
    }

    @Override
    protected void entityInit() { }
    public void setItemStacks(final ItemStack[] stacks) {
        this.contents = ItemStackUtil.carefulCopyArray(stacks);
    }

    @Override
    public ItemStack[] getItemStacks() {
        return contents;
    }

    @Override
    public boolean attackEntityFrom(final DamageSource source, final float amount) {
        if (!world.isRemote) {
            this.setDead();

            for (final ItemStack stack : contents) {
                world.spawnEntity(new EntityItem(world, posX, posY + 0.125, posZ, stack));
            }
        }
        return true;
    }

    @Override
    public void enterBlock(final IEnterableBlock enterable, final BlockPos pos, final EnumFacing dir) {

        if(enterable.canPackageEnter(world, pos.getX(), pos.getY(), pos.getZ(), dir, this)) {
            enterable.onPackageEnter(world, pos.getX(), pos.getY(), pos.getZ(), dir, this);
            this.setDead();
        }
    }

    @Override
    public boolean onLeaveConveyor() {

        this.setDead();

        for(final ItemStack stack : contents) {
            final EntityItem item = new EntityItem(world, posX + motionX * 2, posY + motionY * 2, posZ + motionZ * 2, stack);
            item.motionX = this.motionX * 2;
            item.motionY = 0.1;
            item.motionZ = this.motionZ * 2;
            item.velocityChanged = true;
            world.spawnEntity(item);
        }

        return true;
    }

    @Override
    protected void writeEntityToNBT(final NBTTagCompound nbt) {
        final NBTTagList nbttaglist = new NBTTagList();

        for(int i = 0; i < this.contents.length; ++i) {
            if(this.contents[i] != null) {
                final NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("slot", (byte) i);
                this.contents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbt.setTag("contents", nbttaglist);
        nbt.setInteger("count", this.contents.length);
    }

    @Override
    protected void readEntityFromNBT(final NBTTagCompound nbt) {
        this.contents = new ItemStack[nbt.getInteger("count")];
        final NBTTagList nbttaglist = nbt.getTagList("contents", 10);

        for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            final NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            final int j = nbttagcompound1.getByte("slot") & 255;

            if(j >= 0 && j < this.contents.length) {
                this.contents[j] = ItemStackUtil.itemStackFrom(nbttagcompound1);
            }
        }
    }
}
