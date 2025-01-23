/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.ul;

import glmath.joou.ULong;

/**
 *
 * @author GBarbieri
 */
public class Vec3ul extends FuncRelational {

    public Vec3ul() {
        x.value = 0;
        y.value = 0;
        z.value = 0;
    }

    public Vec3ul(final int x, final int y, final int z) {
        this((long) x, y, z);
    }

    public Vec3ul(final long x, final long y, final long z) {
        this.x.value = x;
        this.y.value = y;
        this.z.value = z;
    }

    public Vec3ul set(final ULong x, final ULong y, final ULong z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }
}
