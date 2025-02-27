/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.d;

/**
 *
 * @author GBarbieri
 */
public class Vec3d extends FuncRelational {

    public Vec3d() {
        x = 0;
        y = 0;
    }

    public Vec3d(final int x, final int y, final int z) {
        this((double) x, y, z);
    }

    public Vec3d(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3d set(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }
}
