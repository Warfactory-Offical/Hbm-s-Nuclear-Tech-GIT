/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2.b;

/**
 *
 * @author elect
 */
public class Vec2b extends FuncRelational {

    public Vec2b() {
        x = 0;
        y = 0;
    }

    public Vec2b(final int s) {
        this((byte) s, (byte) s);
    }

    public Vec2b(final int x, final int y) {
        this((byte) x, (byte) y);
    }

    public Vec2b(final byte x, final byte y) {
        this.x = x;
        this.y = y;
    }

    public Vec2b set(final int x, final int y) {
        return set((byte) x, (byte) y);
    }

    public Vec2b set(final byte x, final byte y) {
        this.x = x;
        this.y = y;
        return this;
    }
}
