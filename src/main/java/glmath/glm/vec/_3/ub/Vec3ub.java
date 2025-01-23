/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.ub;

import glmath.joou.UByte;

/**
 *
 * @author elect
 */
public class Vec3ub extends FuncRelational {

    public Vec3ub() {
        x.value = 0;
        y.value = 0;
        z.value = 0;
    }

    public Vec3ub(final int x, final int y, final int z) {
        this((byte) x, (byte) y, (byte) z);
    }

    public Vec3ub(final byte x, final byte y, final byte z) {
        this(new UByte(x), new UByte(y), new UByte(z));
    }

    public Vec3ub(final UByte x, final UByte y, final UByte z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3ub set(final UByte x, final UByte y, final UByte z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }
}
