/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.us;

import glmath.joou.UShort;

/**
 *
 * @author GBarbieri
 */
public class Vec4us extends FuncRelational {

    public Vec4us() {
        x.value = 0;
        y.value = 0;
        z.value = 0;
        w.value = 0;
    }

    public Vec4us(final int x, final int y, final int z, final int w) {
        this((short) x, (short) y, (short) z, (short) w);
    }

    public Vec4us(final short x, final short y, final short z, final short w) {
        this.x.value = x;
        this.y.value = y;
        this.z.value = z;
        this.w.value = w;
    }

    public Vec4us set(final UShort x, final UShort y, final UShort z, final UShort w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }
}
