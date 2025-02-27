/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.us;

import glmath.joou.UShort;

/**
 *
 * @author GBarbieri
 */
public class Vec3us extends FuncRelational {

    public Vec3us() {
        x.value = 0;
        y.value = 0;
        z.value = 0;
    }

    public Vec3us(final int x, final int y, final int z) {
        this((short) x, (short) y, (short) z);
    }

    public Vec3us(final short x, final short y, final short z) {
        this.x.value = x;
        this.y.value = y;
        this.z.value = z;
    }

    public Vec3us set(final UShort x, final UShort y, final UShort z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }
}
