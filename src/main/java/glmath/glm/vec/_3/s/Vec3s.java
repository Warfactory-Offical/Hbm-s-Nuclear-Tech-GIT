/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.s;

/**
 *
 * @author GBarbieri
 */
public class Vec3s extends FuncRelational {

    public Vec3s() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vec3s(final int x, final int y, final int z) {
        this((short) x, (short) y, (short) z);
    }

    public Vec3s(final short x, final short y, final short z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3s set(final int x, final int y, final int z) {
        return set((short) x, (short) y, (short) z);
    }

    public Vec3s set(final short x, final short y, final short z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }
}
