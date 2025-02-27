/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glf;

import java.nio.ByteBuffer;

import glmath.dev.Vec4u8;
import glmath.glm.vec._3.Vec3;

/**
 *
 * @author GBarbieri
 */
public class Vertex_v3fv4u8 {

    public static final int SIZE = Vec3.SIZE + Vec4u8.SIZE;

    public Vec3 position;
    public Vec4u8 color;

    public Vertex_v3fv4u8(final Vec3 position, final Vec4u8 color) {
        this.position = position;
        this.color = color;
    }

    public void toBb(final ByteBuffer bb, final int index) {
        bb
                .putFloat(index * SIZE, position.x)
                .putFloat(index * SIZE + Float.BYTES, position.y)
                .putFloat(index * SIZE + 2 * Float.BYTES, position.z)
                .put(index * SIZE + 3 * Float.BYTES, color.x)
                .put(index * SIZE + 3 * Float.BYTES + Byte.BYTES, color.y)
                .put(index * SIZE + 3 * Float.BYTES + 2 * Byte.BYTES, color.z)
                .put(index * SIZE + 3 * Float.BYTES + 3 * Byte.BYTES, color.w);
    }
}
