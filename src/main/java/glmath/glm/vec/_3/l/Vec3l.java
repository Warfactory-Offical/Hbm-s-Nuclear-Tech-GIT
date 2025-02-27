/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.l;

/**
 *
 * @author GBarbieri
 */
public class Vec3l extends FuncRelational {

    public Vec3l() {
        x = 0;
        y = 0;
    }

    public Vec3l(final int x, final int y, final int z) {
        this((long) x, y, z);
    }

    public Vec3l(final long x, final long y, final long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3l set(final long x, final long y, final long z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }
}
