package com.hbm.blocks.network;

import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BlockConveyorTriple extends BlockConveyor {

    public BlockConveyorTriple(final Material materialIn, final String s) {
        super(materialIn, s);
    }

    @Override
    public Vec3d getClosestSnappingPosition(final World world, final BlockPos pos, final Vec3d itemPos) {

        final EnumFacing dir = this.getTravelDirection(world, pos, itemPos);

        final double posX = MathHelper.clamp(itemPos.x, pos.getX(), pos.getX() + 1);
        final double posZ = MathHelper.clamp(itemPos.z, pos.getZ(), pos.getZ() + 1);

        double xCenter = pos.getX() + 0.5;
        double zCenter = pos.getZ() + 0.5;

        if(dir.getAxis() == EnumFacing.Axis.X) {
            xCenter = posX;
            zCenter += (posZ > zCenter + 0.15 ? 0.3125 : posZ < zCenter - 0.15 ? -0.3125 : 0);
        }
        if(dir.getAxis() == EnumFacing.Axis.Z) {
            zCenter = posZ;
            xCenter += (posX > xCenter + 0.15 ? 0.3125 : posX < xCenter - 0.15 ? -0.3125 : 0);
        }

        return new Vec3d(xCenter, pos.getY() + 0.25, zCenter);
    }
}