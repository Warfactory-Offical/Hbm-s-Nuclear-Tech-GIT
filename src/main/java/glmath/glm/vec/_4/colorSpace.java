/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4;

/**
 *
 * @author GBarbieri
 */
public abstract class colorSpace extends noise {

    public Vec4 convertLinearToSRGB() {
        return compute_rgbToSrgb((Vec4) this, 0.41666f, (Vec4) this);
    }

    public Vec4 convertLinearToSRGB_() {
        return compute_rgbToSrgb((Vec4) this, 0.41666f, new Vec4());
    }

    public Vec4 convertLinearToSRGB(final Vec4 colorSRGB) {
        return compute_rgbToSrgb((Vec4) this, 0.41666f, colorSRGB);
    }

    public static Vec4 compute_rgbToSrgb(final Vec4 colorRGB, final float gammaCorrection, final Vec4 colorSRGB) {
        // vecType<T, P> const ClampedColor(clamp(ColorRGB, static_cast<T>(0), static_cast<T>(1)));
        final float clampedColorX = Math.min(Math.max(colorRGB.x, 0), 1);
        final float clampedColorY = Math.min(Math.max(colorRGB.y, 0), 1);
        final float clampedColorZ = Math.min(Math.max(colorRGB.z, 0), 1);
        // x = pow(ClampedColor, vecType<T, P>(GammaCorrection)) * static_cast<T>(1.055) - static_cast<T>(0.055)
        final float xX = (float) (Math.pow(clampedColorX, gammaCorrection) * 1.055 - 0.055);
        final float xY = (float) (Math.pow(clampedColorY, gammaCorrection) * 1.055 - 0.055);
        final float xZ = (float) (Math.pow(clampedColorZ, gammaCorrection) * 1.055 - 0.055);
        // y = ClampedColor * static_cast<T>(12.92)
        final float yX = clampedColorX * 12.92f;
        final float yY = clampedColorY * 12.92f;
        final float yZ = clampedColorZ * 12.92f;
        // a = lessThan(ClampedColor, vecType<T, P>(static_cast<T>(0.0031308)))
        final float aX = clampedColorX < 0.0031308f ? 1 : 0;
        final float aY = clampedColorY < 0.0031308f ? 1 : 0;
        final float aZ = clampedColorZ < 0.0031308f ? 1 : 0;
        // mix(x, y, a)
        colorSRGB.x = xX + aX * (yX - xX);
        colorSRGB.y = xY + aY * (yY - xY);
        colorSRGB.z = xZ + aZ * (yZ - xZ);
        colorSRGB.w = colorRGB.w;
        return colorSRGB;
    }
}
