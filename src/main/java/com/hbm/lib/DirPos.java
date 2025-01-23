package com.hbm.lib;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class DirPos {
    
    protected ForgeDirection dir;
    protected BlockPos pos;
    

    public DirPos(final int x, final int y, final int z, final ForgeDirection dir) {
        this.pos = new BlockPos(x, y, z);
        this.dir = dir;
    }

    public DirPos(final BlockPos pos, final ForgeDirection dir) {
        this.pos = pos;
        this.dir = dir;
    }

    public DirPos(final TileEntity te, final ForgeDirection dir) {
        this.pos = te.getPos();
        this.dir = dir;
    }

    public DirPos(final double x, final double y, final double z, final ForgeDirection dir) {
        this.pos = new BlockPos(x, y, z);
        this.dir = dir;
    }
    
    public ForgeDirection getDir() {
        return this.dir;
    }

    public BlockPos getPos() {
        return this.pos;
    }
}
