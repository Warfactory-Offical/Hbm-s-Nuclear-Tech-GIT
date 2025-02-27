/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3;

/**
 *
 * @author elect
 */
abstract class funcGeometric extends funcCommon {

    public Vec3 cross(final Vec3 y) {
        return cross(y, (Vec3) this);
    }

    public Vec3 cross_(final Vec3 y) {
        return cross(y, new Vec3());
    }

    public Vec3 cross(final Vec3 y, final Vec3 res) {
        return cross((Vec3) this, y, res);
    }

    public static Vec3 cross(final Vec3 x, final Vec3 y, final Vec3 res) {
        return res.set(
                x.y * y.z - y.y * x.z,
                x.z * y.x - y.z * x.x,
                x.x * y.y - y.x * x.y);
    }

    public float dot(final Vec3 y) {
        return dot((Vec3) this, y);
    }

    public static float dot(final Vec3 x, final Vec3 y) {
        final float tX = x.x * y.x;
        final float tY = x.y * y.y;
        final float tZ = x.z * y.z;
        return tX + tY + tZ;
    }

    /**
     * Normalize this vector.
     *
     * @return this
     */
    public Vec3 normalize() {
        return normalize((Vec3) this);
    }

    public Vec3 normalize_() {
        return normalize(new Vec3());
    }

    /**
     * Normalize this vector and store the result in <code>res</code>.
     *
     * @param res will hold the result
     * @return
     */
    public Vec3 normalize(final Vec3 res) {
        final float invLength = (float) (1.0 / Math.sqrt(x * x + y * y + z * z));
        res.x = x * invLength;
        res.y = y * invLength;
        res.z = z * invLength;
        return res;
    }
}
