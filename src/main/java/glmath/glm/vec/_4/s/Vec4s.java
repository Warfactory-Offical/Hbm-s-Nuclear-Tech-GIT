/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.s;

/**
 *
 * @author GBarbieri
 */
public class Vec4s extends FuncRelational {

    public Vec4s() {
        x = 0;
        y = 0;
        z = 0;
        w = 0;
    }

    public Vec4s(final int x, final int y, final int z, final int w) {
        this((short) x, (short) y, (short) z, (short) w);
    }

    public Vec4s(final short x, final short y, final short z, final short w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vec4s set(final int x, final int y, final int z, final int w) {
        return set((short) x, (short) y, (short) z, (short) w);
    }

    public Vec4s set(final short x, final short y, final short z, final short w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }
}
