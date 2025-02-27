/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm;

/**
 *
 * @author GBarbieri
 */
abstract class noise extends matrixTransform {

    public static float taylorInvSqrt_(final float r) {
        return 1.79284291400159f - r * 0.85373472095314f;
    }

    private static float permute(final float x) {
        return mod289((x * 34 + 1) * x);
    }

    private static float mod289(final float x) {
        return x - floor(x * 1 / 289) * 289;
    }
}
