/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2.s;

/**
 *
 * @author GBarbieri
 */
public class Vec2s extends FuncRelational {

    public Vec2s() {
        x = 0;
        y = 0;
    }

    public Vec2s(final int x, final int y) {
        this((short) x, (short) y);
    }

    public Vec2s(final short x, final short y) {
        this.x = x;
        this.y = y;
    }

    public Vec2s set(final int x, final int y) {
        return set((short) x, (short) y);
    }

    public Vec2s set(final short x, final short y) {
        this.x = x;
        this.y = y;
        return this;
    }
}
