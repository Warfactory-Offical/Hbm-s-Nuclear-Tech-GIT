/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.ul;

import glmath.joou.ULong;

/**
 *
 * @author GBarbieri
 */
public class Vec4ul extends FuncRelational {

    public Vec4ul() {
        x.value = 0;
        y.value = 0;
        z.value = 0;
        w.value = 0;
    }

    public Vec4ul(final int x, final int y, final int z, final int w) {
        this((long) x, y, z, w);
    }

    public Vec4ul(final long x, final long y, final long z, final long w) {
        this.x.value = x;
        this.y.value = y;
        this.z.value = z;
        this.w.value = w;
    }

    public Vec4ul set(final ULong x, final ULong y, final ULong z, final ULong w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }
}
