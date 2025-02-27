/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.jglm;

/**
 * @deprecated 
 * @author gbarbieri
 */
public class Jglm {

    public static float mix(final float start, final float end, final float lerp) {
        return (start + lerp * (end - start));
    }

//    public static Vec normalize(Vec vec) {
//
//        float length = 0;
//
//        for (int i = 0; i < vec.order; i++) {
//            length += vec.toFloatArray()[i] * vec.toFloatArray()[i];
//        }
//
//        length = (float) Math.sqrt(length);
//
//        float[] result = new float[vec.order];
//
//        for (int i = 0; i < vec.order; i++) {
//            result[i] = vec.toFloatArray()[i] / length;
//        }
//
//        return new Vec3(result);
//
////        float length = ((float) Math.sqrt((vec3[0] * vec3[0]) + (vec3[1] * vec3[1]) + (vec3[2] * vec3[2])));
////
////        return new float[]{vec3[0] / length, vec3[1] / length, vec3[2] / length};
//    }
    public static float clamp(final float value, final float min, final float max) {

        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }

        return value;
    }

    public static int clamp(final int value, final int min, final int max) {

        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }

        return value;
    }

    public static Mat4 translate(final Mat4 mat, final Vec3 vec3) {

        final Mat4 result = mat;

        result.c3 = mat.mult(new Vec4(vec3, 1.0f));

        return result;
    }

    public static Quat angleAxis(final float angle, Vec3 axis) {

        final Quat result = new Quat();

        final float a = (float) Math.toRadians(angle);

        final float s = (float) Math.sin(a * 0.5f);

        axis = axis.normalize();

        result.x = axis.x * s;
        result.y = axis.y * s;
        result.z = axis.z * s;
        result.w = (float) Math.cos(a * 0.5f);

        return result;
    }

    public static Mat4 perspective(final float fovDeg, final float aspect, final float zNear, final float zFar) {

        final float frustumScale = calculateFrustumScale(fovDeg);

        final Mat4 perspectiveMatrix = new Mat4();

        perspectiveMatrix.c0.x = frustumScale / aspect;
        perspectiveMatrix.c1.y = frustumScale;
        perspectiveMatrix.c2.z = (zFar + zNear) / (zNear - zFar);
        perspectiveMatrix.c2.w = -1.0f;
        perspectiveMatrix.c3.z = (2 * zFar * zNear) / (zNear - zFar);

//        matrices.set(matrices.size() - 1, perspectiveMatrix);
//        setTop(top().mult(perspectiveMatrix));
        return perspectiveMatrix;
    }

    public static Mat4 perspective(final float fovDeg, final float zNear, final float zFar) {

        final float frustumScale = calculateFrustumScale(fovDeg);

        final Mat4 perspectiveMatrix = new Mat4();

        perspectiveMatrix.c0.x = frustumScale;
        perspectiveMatrix.c1.y = frustumScale;
        perspectiveMatrix.c2.z = (zFar + zNear) / (zNear - zFar);
        perspectiveMatrix.c2.w = -1.0f;
        perspectiveMatrix.c3.z = (2 * zFar * zNear) / (zNear - zFar);

//        matrices.set(matrices.size() - 1, perspectiveMatrix);
//        setTop(top().mult(perspectiveMatrix));
        return perspectiveMatrix;
    }

    public static Mat4 orthographic(final float left, final float right, final float bottom, final float top, final float nearVal, final float farVal) {

        final Mat4 orthographicMatric = new Mat4(1.0f);

        orthographicMatric.c0.x = 2 / (right - left);

        orthographicMatric.c1.y = 2 / (top - bottom);

        orthographicMatric.c2.z = -2 / (farVal - nearVal);

        orthographicMatric.c3.x = -(right + left) / (right - left);

        orthographicMatric.c3.y = -(top + bottom) / (top - bottom);

        orthographicMatric.c3.z = -(farVal + nearVal) / (farVal - nearVal);

        return orthographicMatric;
    }

    public static Mat4 orthographic2D(final float left, final float right, final float bottom, final float top) {

        return orthographic(left, right, bottom, top, -1.0f, 1.0f);
    }

    public static Vec3 unProject(final Vec3 window, final Mat4 modelview, final Mat4 projection, final Vec4 viewport) {

        final Mat4 pm = projection.mult(modelview);

        final Mat4 inverse = pm.inverse();

        Vec4 tmp = new Vec4(window, 1.0f);
        tmp.x = (tmp.x - viewport.x) / viewport.z;
        tmp.y = (tmp.y - viewport.y) / viewport.w;
        tmp = tmp.mult(2);
        tmp = tmp.minus(1);

        Vec4 obj = inverse.mult(tmp);

        obj = obj.divide(obj.w);

        return new Vec3(obj);
    }

    public static float dot(final Vec4 v0, final Vec4 v1) {

        return (v0.x * v1.x + v0.y * v1.y + v0.z * v1.z + v0.w * v1.w);
    }

    public static float calculateFrustumScale(final float fFovDeg) {

//        float degToRad = (float) (Math.PI * 2.0f / 360.0f);
        final float fFovRad = (float) Math.toRadians(fFovDeg);
        return (float) (1.0f / Math.tan(fFovRad / 2.0f));
    }

//    public static Mat4 leftPerspectiveRH(float yFov, float aspect, float zNear, float zFar, HMDInfo hmd) {
//
//        Mat4 projectionCenter = perspectiveRH(yFov, aspect, zNear, zFar);
//
//        Mat4 H = new Mat4(1f);
//
//        H.c3.x = projectionCenterOffset(hmd);
//
//        return H.mult(projectionCenter);
//    }
//
//    public static Mat4 rightPerspectiveRH(float yFov, float aspect, float zNear, float zFar, HMDInfo hmd) {
//
//        Mat4 projectionCenter = perspectiveRH(yFov, aspect, zNear, zFar);
//
//        Mat4 H = new Mat4(1f);
//
//        H.c3.x = -projectionCenterOffset(hmd);
//
//        return H.mult(projectionCenter);
//    }
//
//    public static Mat4 leftView(Mat4 viewMatrix, HMDInfo hmd) {
//
//        Mat4 shift = new Mat4(1f);
//
//        shift.c3.x = hmd.InterpupillaryDistance / 2;
//
//        return shift.mult(viewMatrix);
//    }
//
//    public static Mat4 rightView(Mat4 viewMatrix, HMDInfo hmd) {
//
//        Mat4 shift = new Mat4(1f);
//
//        shift.c3.x = -hmd.InterpupillaryDistance / 2;
//
//        return shift.mult(viewMatrix);
//    }
//
//    private static float projectionCenterOffset(HMDInfo hmdInfo) {
//
//        float viewCenter = hmdInfo.HScreenSize * 0.25f;
//
//        float eyeProjectionShift = viewCenter - hmdInfo.LensSeparationDistance * 0.5f;
//
//        return 1 * (4f * eyeProjectionShift / hmdInfo.HScreenSize);
//    }

    private static Mat4 perspectiveRH(final float yFov, final float aspect, final float zNear, final float zFar) {

        final float frustumScale = calculateFrustumScale(yFov);

        final Mat4 perspectiveRH = new Mat4(0);

        perspectiveRH.c0.x = 1 / (aspect * frustumScale);
        perspectiveRH.c1.y = 1 / frustumScale;
        perspectiveRH.c2.z = zFar / (zNear - zFar);
        perspectiveRH.c2.w = -1;
        perspectiveRH.c3.z = zFar * zNear / (zNear - zFar);

        return perspectiveRH;
    }
}
