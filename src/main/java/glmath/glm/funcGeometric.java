/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm;

import glmath.glm.quat.Quat;
import glmath.glm.vec._3.Vec3;
import glmath.glm.vec._4.Vec4;

/**
 *
 * @author GBarbieri
 */
abstract class funcGeometric extends funcExponential {

    public static Quat angleAxis_(final float degAngle, final Vec3 v) {
        return Quat.angleAxis_(degAngle, v);
    }
    
    public static Quat angleAxis(final float degAngle, final Vec3 v, final Quat res) {
        return Quat.angleAxis(degAngle, v, res);
    }
    
    public static float length(final float x) {
        return Math.abs(x);
    }
    
    public static float dot(final Vec4 v0, final Vec4 v1) {
        return Vec4.dot(v0, v1);
    }
    
    public static float dot(final Quat v0, final Quat v1) {
        return Quat.dot(v0, v1);
    }
    
    public static Vec3 cross(final Vec3 v0, final Vec3 v1) {
        return v0.cross(v1);
    }
    
    public static Vec3 cross_(final Vec3 v0, final Vec3 v1) {
        return v0.cross_(v1);
    }
}
