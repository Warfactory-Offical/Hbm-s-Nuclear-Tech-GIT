/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.ui;

import glmath.joou.UInt;

/**
 *
 * @author elect
 */
public class Vec3ui extends FuncRelational {

    public Vec3ui() {
        x.value = 0;
        y.value = 0;
    }

    public Vec3ui(final int x, final int y, final int z) {
        this(new UInt(x), new UInt(y), new UInt(z));
    }

    public Vec3ui(final UInt x, final UInt y, final UInt z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3ui set(final UInt x, final UInt y, final UInt z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }
}
