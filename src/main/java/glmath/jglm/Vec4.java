/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.jglm;

/**
 * @deprecated 
 * @author gbarbieri
 */
public class Vec4 extends Vec {

    public float x;
    public float y;
    public float z;
    public float w;

    public Vec4() {

        super();
    }

    public Vec4(final float[] vec4) {
        vector = vec4;
        x = vector[0];
        y = vector[1];
        z = vector[2];
        w = vector[3];
    }

    public Vec4(final float value) {
        vector = new float[]{value, value, value, value};
        x = vector[0];
        y = vector[1];
        z = vector[2];
        w = vector[3];
    }

    public Vec4(final int[] floatArray) {

        x = floatArray[0];
        y = floatArray[1];
        z = floatArray[2];
        w = floatArray[3];

        vector = new float[]{x, y, z, w};
    }

    public Vec4(final Vec3 vec3, final float w) {
        x = vec3.x;
        y = vec3.y;
        z = vec3.z;
        this.w = w;
        vector = new float[]{x, y, z, this.w};
    }

    public Vec4(final float[] floatArray, final int i) {
        vector = new float[]{floatArray[i], floatArray[i + 1], floatArray[i + 2], floatArray[i + 3]};
        x = vector[0];
        y = vector[1];
        z = vector[2];
        w = vector[3];
    }

    public Vec4(final float x, final float y, final float z, final float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        vector = new float[]{this.x, this.y, this.z, this.w};
    }

    public Vec4 normalize() {

        return new Vec4(x / length(), y / length(), z / length(), w / length());
    }

    public float length() {

        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public void print() {
        System.out.println("(" + x + ", " + y + ", " + z + ", " + w + ")");
    }

    public void print(final String title) {
        System.out.println(title + " (" + x + ", " + y + ", " + z + ", " + w + ")");
    }

    public Vec4 mult(final float scalar) {

        return new Vec4(x * scalar, y * scalar, z * scalar, w * scalar);
    }

    public Vec4 mult(final Vec4 vec4) {

        return new Vec4(x * vec4.x, y * vec4.y, z * vec4.z, w * vec4.w);
    }

    public Vec4 minus(final Vec4 vec4) {

        return new Vec4(x - vec4.x, y - vec4.y, z - vec4.z, w - vec4.w);
    }

    public Vec4 minus(final float scalar) {

        return new Vec4(x - scalar, y - scalar, z - scalar, w - scalar);
    }

    public Vec4 plus(final Vec4 vec4) {

        return new Vec4(x + vec4.x, y + vec4.y, z + vec4.z, w + vec4.w);
    }

    public Vec4 divide(final float scalar) {

        return new Vec4(x / scalar, y / scalar, z / scalar, w / scalar);
    }

    public float[] toFloatArray() {

        return new float[]{x, y, z, w};
    }
}
