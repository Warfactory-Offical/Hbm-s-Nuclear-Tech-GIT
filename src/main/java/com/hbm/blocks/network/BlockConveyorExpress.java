package com.hbm.blocks.network;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BlockConveyorExpress extends BlockConveyor {

    public BlockConveyorExpress(final Material materialIn, final String s) {
        super(materialIn, s);
    }

    @Override
    public Vec3d getTravelLocation(final World world, final int x, final int y, final int z, final Vec3d itemPos, final double speed) {
        return super.getTravelLocation(world, x, y, z, itemPos, speed * 3);
    }
}
