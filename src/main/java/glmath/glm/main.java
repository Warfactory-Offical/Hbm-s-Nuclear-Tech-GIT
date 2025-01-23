/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm;

import glmath.glm.mat._4.Mat4;
import glmath.glm.vec._2.Vec2;
import glmath.glm.vec._2.i.Vec2i;
import glmath.glm.vec._3.Vec3;
import glmath.glm.vec._3.i.Vec3i;
import glmath.glm.vec._4.Vec4;

/**
 *
 * @author fschaefers
 */
public class main {

    public static void main(final String[] args) {
        final Vec2 vec2F_1 = new Vec2(5, 2);
        final Vec2 vec2F_2 = new Vec2(5, 8);
        final Vec2i vec2I_1 = new Vec2i(5, 8);
        final Vec2i vec2I_12 = new Vec2i(6, 8);
        final Vec4 vec4_1 = new Vec4(0, 0, 0, 0);
        final Vec3 vec3f_1 = new Vec3(3, 5, 7);
        final Vec3 vec3f_2 = new Vec3(10, 10, 10);
        final Vec3i vec3i_1 = new Vec3i(3, 5, 7);
        final Vec3i vec3i_2 = new Vec3i(10, 10, 10);
        final Vec2i x = vec2I_1.lessThan(vec2I_12);
        final Vec3 modVec = vec3f_2.mod_(vec3f_1);
        final Vec3i modVeci = vec3i_2.mod_(vec3i_1);
        modVec.print("modVec");
        modVeci.print("modVec");
        
        final Vec4 v = new Vec4(0,1,3,4);
        v.mul(new Mat4());
//          vec2F_2.
    }
}
