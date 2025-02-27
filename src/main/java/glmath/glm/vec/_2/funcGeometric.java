/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2;

/**
 *
 * @author elect
 */
abstract class funcGeometric extends funcExponential {

    public Vec2 cross(final Vec2 y) {
        return cross(y, (Vec2) this);
    }

    public Vec2 cross_(final Vec2 y) {
        return cross(y, new Vec2());
    }

    public Vec2 cross(final Vec2 y, final Vec2 result) {
        return cross((Vec2) this, y, result);
    }

    public static Vec2 cross(final Vec2 x, final Vec2 y, final Vec2 result) {
        result.y = x.x * y.y - y.x * x.y;
        result.x = 0f;
        return result;
    }

    public float distance(final Vec2 p0, final Vec2 p1) {
        final float t0x = p0.x - p1.x;
        final float t0y = p0.y - p1.y;
        final float dot = t0x * t0y + t0x * t0y;
        return (float) Math.sqrt(dot);
    }

    public float dot(final Vec2 y) {
        return dot((Vec2) this, y);
    }

    public static float dot(final Vec2 x, final Vec2 y) {
        final float tX = x.x * y.x;
        final float tY = x.y * y.y;
        return tX + tY;
    }

    public static float length(final Vec2 x, final Vec2 result) {
        return (float) Math.sqrt(dot(x, x));
    }
}
