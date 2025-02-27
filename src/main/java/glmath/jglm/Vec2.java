/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.jglm;

/**
 * @deprecated 
 * @author gbarbieri
 */
public class Vec2 extends Vec {

    public float x;
    public float y;

    public Vec2() {

        x = 0;
        y = 0;
    }

    public Vec2(final float[] floatArray) {
        vector = floatArray;
        x = vector[0];
        y = vector[1];
    }

    public Vec2(final float x, final float y) {

        this.x = x;
        this.y = y;
        vector = new float[]{x, y};
    }

    public Vec2(final float[] floatArray, final int i) {

        vector = new float[]{floatArray[i], floatArray[i + 1]};
        x = vector[0];
        y = vector[1];
    }

    public Vec2 normalize() {

        final float length = ((float) Math.sqrt(x * x + y * y));

        return new Vec2(x / length, y / length);
    }

    public Vec2 times(final float scalar) {

        return new Vec2(x * scalar, y * scalar);
    }

    public Vec2 plus(final Vec2 vec2) {

        return new Vec2(x + vec2.x, y + vec2.y);
    }

    public Vec2 minus(final Vec2 vec2) {

        return new Vec2(x - vec2.x, y - vec2.y);
    }
    
    public Vec2 minus(final Vec2i vec2) {

        return new Vec2(x - vec2.x, y - vec2.y);
    }

    public Vec2 negated() {

        return new Vec2(-x, -y);
    }

    public float length() {

        return (float) Math.sqrt(x * x + y * y);
    }

    public void print() {
        System.out.println("(" + x + ", " + y + ")");
    }

    public void print(final String title) {
        System.out.println(title + " (" + x + ", " + y + ")");
    }
    
    public float[] toFloatArray(){
        
        return new float[]{x, y};
    }
}
