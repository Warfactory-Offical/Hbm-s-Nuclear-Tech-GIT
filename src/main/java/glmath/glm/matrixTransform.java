/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm;

import glmath.glm.mat._4.Mat4;
import glmath.glm.mat._4.d.Mat4d;
import glmath.glm.vec._3.Vec3;
import glmath.glm.vec._3.d.Vec3d;

/**
 *
 * @author GBarbieri
 */
abstract class matrixTransform extends funcMatrix {

    public static boolean GLM_LEFT_HANDED = false;
    public static boolean GLM_DEPTH_ZERO_TO_ONE = false;

    public static Mat4 lookAt_(final Vec3 eye, final Vec3 center, final Vec3 up) {
        return GLM_LEFT_HANDED ? lookAtLH(eye, center, up, new Mat4()) : lookAtRH(eye, center, up, new Mat4());
    }

    public static Mat4 lookAt(final Vec3 eye, final Vec3 center, final Vec3 up, final Mat4 res) {
        return GLM_LEFT_HANDED ? lookAtLH(eye, center, up, res) : lookAtRH(eye, center, up, res);
    }

    private static Mat4 lookAtLH(final Vec3 eye, final Vec3 center, final Vec3 up, final Mat4 res) {
        // f(normalize(center - eye))
        float fX = center.x - eye.x;
        float fY = center.y - eye.y;
        float fZ = center.z - eye.z;
        float inverseSqrt = 1f / (float) Math.sqrt((fX - eye.x) * (fX - eye.x) + (fY - eye.y) * (fY - eye.y)
                + (fZ - eye.z) * (fZ - eye.z));
        fX *= inverseSqrt;
        fY *= inverseSqrt;
        fZ *= inverseSqrt;
        // s(normalize(cross(up, f)))
        float sX = up.y * fZ - up.z * fY;
        float sY = up.z * fX - up.x * fZ;
        float sZ = up.x * fY - up.y * fX;
        inverseSqrt = 1.0f / (float) Math.sqrt(sX * sX + sY * sY + sZ * sZ);
        sX *= inverseSqrt;
        sY *= inverseSqrt;
        sZ *= inverseSqrt;
        // u(cross(f, s))
        final float uX = fY * sZ - fZ * sY;
        final float uY = fZ * sX - fX * sZ;
        final float uZ = fX * sY - fY * sX;
        res.m00 = sX;
        res.m01 = uX;
        res.m02 = fX;
        res.m03 = 0f;
        res.m10 = sY;
        res.m11 = uY;
        res.m12 = fY;
        res.m13 = 0f;
        res.m20 = sZ;
        res.m21 = uZ;
        res.m22 = fZ;
        res.m23 = 0f;
        res.m30 = -sX * eye.x - sY * eye.y - sZ * eye.z;
        res.m31 = -uX * eye.x - uY * eye.y - uZ * eye.z;
        res.m32 = -fX * eye.x - fY * eye.y - fZ * eye.z;
        res.m33 = 1f;
        return res;
    }

    private static Mat4 lookAtRH(final Vec3 eye, final Vec3 center, final Vec3 up, final Mat4 res) {
        // f(normalize(center - eye))
        float fX = center.x - eye.x;
        float fY = center.y - eye.y;
        float fZ = center.z - eye.z;
        float inverseSqrt = 1f / (float) Math.sqrt(fX * fX + fY * fY + fZ * fZ);
        fX *= inverseSqrt;
        fY *= inverseSqrt;
        fZ *= inverseSqrt;
        // s(normalize(cross(f, up)))
        float sX = fY * up.z - fZ * up.y;
        float sY = fZ * up.x - fX * up.z;
        float sZ = fX * up.y - fY * up.x;
        inverseSqrt = 1.0f / (float) Math.sqrt(sX * sX + sY * sY + sZ * sZ);
        sX *= inverseSqrt;
        sY *= inverseSqrt;
        sZ *= inverseSqrt;
        // u(cross(s, f))
        final float uX = sY * fZ - sZ * fY;
        final float uY = sZ * fX - sX * fZ;
        final float uZ = sX * fY - sY * fX;
        res.m00 = sX;
        res.m01 = uX;
        res.m02 = -fX;
        res.m03 = 0f;
        res.m10 = sY;
        res.m11 = uY;
        res.m12 = -fY;
        res.m13 = 0f;
        res.m20 = sZ;
        res.m21 = uZ;
        res.m22 = -fZ;
        res.m23 = 0f;
        res.m30 = -sX * eye.x - sY * eye.y - sZ * eye.z;
        res.m31 = -uX * eye.x - uY * eye.y - uZ * eye.z;
        res.m32 = +fX * eye.x + fY * eye.y + fZ * eye.z;
        res.m33 = 1f;
        return res;
    }

    public static Mat4 ortho_(final float left, final float right, final float bottom, final float top) {
        return ortho(new Mat4(), left, right, bottom, top);
    }
    public static Mat4 ortho(final Mat4 res, final float left, final float right, final float bottom, final float top) {
        res.identity();
        res.m00 = 2 / (right - left);
        res.m11 = 2 / (top - bottom);
        res.m22 = -1;
        res.m30 = -(right + left) / (right - left);
        res.m31 = -(top + bottom) / (top - bottom);
        return res;
    }

    public static Mat4 ortho_(final float left, final float right, final float bottom, final float top, final float zNear, final float zFar) {
        return GLM_LEFT_HANDED ? orthoLH(new Mat4(), left, right, bottom, top, zNear, zFar)
                : orthoRH(new Mat4(), left, right, bottom, top, zNear, zFar);
    }

    public static Mat4 ortho(final Mat4 res, final float left, final float right, final float bottom, final float top, final float zNear, final float zFar) {
        return GLM_LEFT_HANDED ? orthoLH(res, left, right, bottom, top, zNear, zFar)
                : orthoRH(res, left, right, bottom, top, zNear, zFar);
    }

    private static Mat4 orthoLH(final Mat4 res, final float left, final float right, final float bottom, final float top, final float zNear, final float zFar) {
        res.m00 = 2.0f / (right - left);
        res.m01 = 0.0f;
        res.m02 = 0.0f;
        res.m03 = 0.0f;
        res.m10 = 0.0f;
        res.m11 = 2.0f / (top - bottom);
        res.m12 = 0.0f;
        res.m13 = 0.0f;
        res.m20 = 0.0f;
        res.m21 = 0.0f;
        res.m22 = (GLM_DEPTH_ZERO_TO_ONE ? 1f : 2.0f) / (zFar - zNear);
        res.m23 = 0.0f;
        res.m30 = -(right + left) / (right - left);
        res.m31 = -(top + bottom) / (top - bottom);
        res.m32 = -(GLM_DEPTH_ZERO_TO_ONE ? zNear : (zFar + zNear)) / (zFar - zNear);
        res.m33 = 1.0f;
        return res;
    }

    private static Mat4 orthoRH(final Mat4 res, final float left, final float right, final float bottom, final float top, final float zNear, final float zFar) {
        res.m00 = 2.0f / (right - left);
        res.m01 = 0.0f;
        res.m02 = 0.0f;
        res.m03 = 0.0f;
        res.m10 = 0.0f;
        res.m11 = 2.0f / (top - bottom);
        res.m12 = 0.0f;
        res.m13 = 0.0f;
        res.m20 = 0.0f;
        res.m21 = 0.0f;
        res.m22 = -(GLM_DEPTH_ZERO_TO_ONE ? 1f : 2.0f) / (zFar - zNear);
        res.m23 = 0.0f;
        res.m30 = -(right + left) / (right - left);
        res.m31 = -(top + bottom) / (top - bottom);
        res.m32 = -(GLM_DEPTH_ZERO_TO_ONE ? zNear : (zFar + zNear)) / (zFar - zNear);
        res.m33 = 1.0f;
        return res;
    }

    public static Mat4 perspective_(final float fovy, final float aspect, final float zNear, final float zFar) {
        return GLM_LEFT_HANDED ? perspectiveLH(fovy, aspect, zNear, zFar, new Mat4())
                : perspectiveRH(fovy, aspect, zNear, zFar, new Mat4());
    }

    public static Mat4 perspective(final float fovy, final float aspect, final float zNear, final float zFar, final Mat4 mat) {
        return GLM_LEFT_HANDED ? perspectiveLH(fovy, aspect, zNear, zFar, mat)
                : perspectiveRH(fovy, aspect, zNear, zFar, mat);
    }

    private static Mat4 perspectiveRH(final float fovy, final float aspect, final float zNear, final float zFar, final Mat4 res) {
        final float tanHalfFovy = (float) Math.tan(fovy * 0.5f);
        res.m00 = 1.0f / (aspect * tanHalfFovy);
        res.m01 = 0.0f;
        res.m02 = 0.0f;
        res.m03 = 0.0f;
        res.m10 = 0.0f;
        res.m11 = 1.0f / tanHalfFovy;
        res.m12 = 0.0f;
        res.m13 = 0.0f;
        res.m20 = 0.0f;
        res.m21 = 0.0f;
        res.m22 = -(zFar + zNear) / (zFar - zNear);
        res.m23 = -1.0f;
        res.m30 = 0.0f;
        res.m31 = 0.0f;
        res.m32 = -2.0f * zFar * zNear / (zFar - zNear);
        res.m33 = 0.0f;
        return res;
    }

    private static Mat4 perspectiveLH(final float fovy, final float aspect, final float zNear, final float zFar, final Mat4 res) {
        final float tanHalfFovy = (float) Math.tan(fovy * 0.5f);
        res.m00 = 1.0f / (aspect * tanHalfFovy);
        res.m01 = 0.0f;
        res.m02 = 0.0f;
        res.m03 = 0.0f;
        res.m10 = 0.0f;
        res.m11 = 1.0f / tanHalfFovy;
        res.m12 = 0.0f;
        res.m13 = 0.0f;
        res.m20 = 0.0f;
        res.m21 = 0.0f;
        res.m22 = (zFar + zNear) / (zFar - zNear);
        res.m23 = 1.0f;
        res.m30 = 0.0f;
        res.m31 = 0.0f;
        res.m32 = -2.0f * zFar * zNear / (zFar - zNear);
        res.m33 = 0.0f;
        return res;
    }

    public static Mat4 perspectiveFov_(final float fov, final float width, final float height, final float zNear, final float zFar) {
        return GLM_LEFT_HANDED ? perspectiveFovLH(fov, width, height, zNear, zFar, new Mat4())
                : perspectiveFovRH(fov, width, height, zNear, zFar, new Mat4());
    }

    public static Mat4 perspectiveFov(final float fov, final float width, final float height, final float zNear, final float zFar, final Mat4 mat) {
        return GLM_LEFT_HANDED ? perspectiveFovRH(fov, width, height, zNear, zFar, mat)
                : perspectiveFovLH(fov, width, height, zNear, zFar, mat);
    }

    private static Mat4 perspectiveFovRH(final float fov, final float width, final float height, final float zNear, final float zFar, final Mat4 res) {
        final float h = (float) (Math.cos(0.5f * fov) / Math.sin(0.5f * fov));
        final float w = h * height / width;
        res.m00 = w;
        res.m01 = 0.0f;
        res.m02 = 0.0f;
        res.m03 = 0.0f;
        res.m10 = 0.0f;
        res.m11 = h;
        res.m12 = 0.0f;
        res.m13 = 0.0f;
        res.m20 = 0.0f;
        res.m21 = 0.0f;
        res.m22 = -(zFar + zNear) / (zFar - zNear);
        res.m23 = -1.0f;
        res.m30 = 0.0f;
        res.m31 = 0.0f;
        res.m32 = -2.0f * zFar * zNear / (zFar - zNear);
        res.m33 = 0.0f;
        return res;
    }

    private static Mat4 perspectiveFovLH(final float fov, final float width, final float height, final float zNear, final float zFar, final Mat4 res) {
        final float h = (float) (Math.cos(0.5f * fov) / Math.sin(0.5f * fov));
        final float w = h * height / width;
        res.m00 = w;
        res.m01 = 0.0f;
        res.m02 = 0.0f;
        res.m03 = 0.0f;
        res.m10 = 0.0f;
        res.m11 = h;
        res.m12 = 0.0f;
        res.m13 = 0.0f;
        res.m20 = 0.0f;
        res.m21 = 0.0f;
        res.m22 = (zFar + zNear) / (zFar - zNear);
        res.m23 = 1.0f;
        res.m30 = 0.0f;
        res.m31 = 0.0f;
        res.m32 = -2.0f * zFar * zNear / (zFar - zNear);
        res.m33 = 0.0f;
        return res;
    }

    public static Mat4d lookAt_(final Vec3d eye, final Vec3d center, final Vec3d up) {
        return GLM_LEFT_HANDED ? lookAtLH(eye, center, up, new Mat4d()) : lookAtRH(eye, center, up, new Mat4d());
    }

    public static Mat4d lookAt(final Vec3d eye, final Vec3d center, final Vec3d up, final Mat4d res) {
        return GLM_LEFT_HANDED ? lookAtLH(eye, center, up, res) : lookAtRH(eye, center, up, res);
    }

    private static Mat4d lookAtLH(final Vec3d eye, final Vec3d center, final Vec3d up, final Mat4d res) {
        // f(normalize(center - eye))
        double fX = center.x - eye.x;
        double fY = center.y - eye.y;
        double fZ = center.z - eye.z;
        double inverseSqrt = 1 / Math.sqrt((fX - eye.x) * (fX - eye.x) + (fY - eye.y) * (fY - eye.y)
                + (fZ - eye.z) * (fZ - eye.z));
        fX *= inverseSqrt;
        fY *= inverseSqrt;
        fZ *= inverseSqrt;
        // s(normalize(cross(up, f)))
        double sX = up.y * fZ - up.z * fY;
        double sY = up.z * fX - up.x * fZ;
        double sZ = up.x * fY - up.y * fX;
        inverseSqrt = 1.0 / Math.sqrt(sX * sX + sY * sY + sZ * sZ);
        sX *= inverseSqrt;
        sY *= inverseSqrt;
        sZ *= inverseSqrt;
        // u(cross(f, s))
        final double uX = fY * sZ - fZ * sY;
        final double uY = fZ * sX - fX * sZ;
        final double uZ = fX * sY - fY * sX;
        res.m00 = sX;
        res.m01 = uX;
        res.m02 = fX;
        res.m03 = 0;
        res.m10 = sY;
        res.m11 = uY;
        res.m12 = fY;
        res.m13 = 0;
        res.m20 = sZ;
        res.m21 = uZ;
        res.m22 = fZ;
        res.m23 = 0;
        res.m30 = -sX * eye.x - sY * eye.y - sZ * eye.z;
        res.m31 = -uX * eye.x - uY * eye.y - uZ * eye.z;
        res.m32 = -fX * eye.x - fY * eye.y - fZ * eye.z;
        res.m33 = 1;
        return res;
    }

    private static Mat4d lookAtRH(final Vec3d eye, final Vec3d center, final Vec3d up, final Mat4d res) {
        // f(normalize(center - eye))
        double fX = center.x - eye.x;
        double fY = center.y - eye.y;
        double fZ = center.z - eye.z;
        double inverseSqrt = 1 / Math.sqrt(fX * fX + fY * fY + fZ * fZ);
        fX *= inverseSqrt;
        fY *= inverseSqrt;
        fZ *= inverseSqrt;
        // s(normalize(cross(f, up)))
        double sX = fY * up.z - fZ * up.y;
        double sY = fZ * up.x - fX * up.z;
        double sZ = fX * up.y - fY * up.x;
        inverseSqrt = 1.0 / Math.sqrt(sX * sX + sY * sY + sZ * sZ);
        sX *= inverseSqrt;
        sY *= inverseSqrt;
        sZ *= inverseSqrt;
        // u(cross(s, f))
        final double uX = sY * fZ - sZ * fY;
        final double uY = sZ * fX - sX * fZ;
        final double uZ = sX * fY - sY * fX;
        res.m00 = sX;
        res.m01 = uX;
        res.m02 = -fX;
        res.m03 = 0;
        res.m10 = sY;
        res.m11 = uY;
        res.m12 = -fY;
        res.m13 = 0;
        res.m20 = sZ;
        res.m21 = uZ;
        res.m22 = -fZ;
        res.m23 = 0;
        res.m30 = -sX * eye.x - sY * eye.y - sZ * eye.z;
        res.m31 = -uX * eye.x - uY * eye.y - uZ * eye.z;
        res.m32 = +fX * eye.x + fY * eye.y + fZ * eye.z;
        res.m33 = 1;
        return res;
    }

    public static Mat4d ortho_(final double left, final double right, final double bottom, final double top, final double zNear, final double zFar) {
        return ortho(new Mat4d(), left, right, bottom, top, zNear, zFar);
    }

    public static Mat4d ortho(final Mat4d res, final double left, final double right, final double bottom, final double top, final double zNear, final double zFar) {
        res.m00 = 2.0 / (right - left);
        res.m01 = 0.0;
        res.m02 = 0.0;
        res.m03 = 0.0;
        res.m10 = 0.0;
        res.m11 = 2.0 / (top - bottom);
        res.m12 = 0.0;
        res.m13 = 0.0;
        res.m20 = 0.0;
        res.m21 = 0.0;
        res.m22 = -2.0 / (zFar - zNear);
        res.m23 = 0.0;
        res.m30 = -(right + left) / (right - left);
        res.m31 = -(top + bottom) / (top - bottom);
        res.m32 = -(zFar + zNear) / (zFar - zNear);
        res.m33 = 1.0;
        return res;
    }

    public static Mat4d perspective_(final double fovy, final double aspect, final double zNear, final double zFar) {
        return GLM_LEFT_HANDED ? perspectiveLH(fovy, aspect, zNear, zFar, new Mat4d())
                : perspectiveRH(fovy, aspect, zNear, zFar, new Mat4d());
    }

    public static Mat4d perspective(final double fovy, final double aspect, final double zNear, final double zFar, final Mat4d mat) {
        return GLM_LEFT_HANDED ? perspectiveLH(fovy, aspect, zNear, zFar, mat)
                : perspectiveRH(fovy, aspect, zNear, zFar, mat);
    }

    private static Mat4d perspectiveRH(final double fovy, final double aspect, final double zNear, final double zFar, final Mat4d res) {
        final double tanHalfFovy = Math.tan(fovy * 0.5);
        res.m00 = 1.0 / (aspect * tanHalfFovy);
        res.m01 = 0.0;
        res.m02 = 0.0;
        res.m03 = 0.0;
        res.m10 = 0.0;
        res.m11 = 1.0 / tanHalfFovy;
        res.m12 = 0.0;
        res.m13 = 0.0;
        res.m20 = 0.0;
        res.m21 = 0.0;
        res.m22 = -(zFar + zNear) / (zFar - zNear);
        res.m23 = -1.0;
        res.m30 = 0.0;
        res.m31 = 0.0;
        res.m32 = -2.0 * zFar * zNear / (zFar - zNear);
        res.m33 = 0.0;
        return res;
    }

    private static Mat4d perspectiveLH(final double fovy, final double aspect, final double zNear, final double zFar, final Mat4d res) {
        final double tanHalfFovy = Math.tan(fovy * 0.5);
        res.m00 = 1.0 / (aspect * tanHalfFovy);
        res.m01 = 0.0;
        res.m02 = 0.0;
        res.m03 = 0.0;
        res.m10 = 0.0;
        res.m11 = 1.0 / tanHalfFovy;
        res.m12 = 0.0;
        res.m13 = 0.0;
        res.m20 = 0.0;
        res.m21 = 0.0;
        res.m22 = (zFar + zNear) / (zFar - zNear);
        res.m23 = 1.0;
        res.m30 = 0.0;
        res.m31 = 0.0;
        res.m32 = -2.0 * zFar * zNear / (zFar - zNear);
        res.m33 = 0.0;
        return res;
    }

    public static Mat4d perspectiveFov_(final double fov, final double width, final double height, final double zNear, final double zFar) {
        return GLM_LEFT_HANDED ? perspectiveFovLH(fov, width, height, zNear, zFar, new Mat4d())
                : perspectiveFovRH(fov, width, height, zNear, zFar, new Mat4d());
    }

    public static Mat4d perspectiveFov(final double fov, final double width, final double height, final double zNear, final double zFar, final Mat4d mat) {
        return GLM_LEFT_HANDED ? perspectiveFovLH(fov, width, height, zNear, zFar, mat)
                : perspectiveFovRH(fov, width, height, zNear, zFar, mat);
    }

    private static Mat4d perspectiveFovRH(final double fov, final double width, final double height, final double zNear, final double zFar, final Mat4d res) {
        final double h = (Math.cos(0.5 * fov) / Math.sin(0.5 * fov));
        final double w = h * height / width;
        res.m00 = w;
        res.m01 = 0.0;
        res.m02 = 0.0;
        res.m03 = 0.0;
        res.m10 = 0.0;
        res.m11 = h;
        res.m12 = 0.0;
        res.m13 = 0.0;
        res.m20 = 0.0;
        res.m21 = 0.0;
        res.m22 = -(zFar + zNear) / (zFar - zNear);
        res.m23 = -1.0;
        res.m30 = 0.0;
        res.m31 = 0.0;
        res.m32 = -2.0 * zFar * zNear / (zFar - zNear);
        res.m33 = 0.0;
        return res;
    }

    private static Mat4d perspectiveFovLH(final double fov, final double width, final double height, final double zNear, final double zFar, final Mat4d res) {
        final double h = (Math.cos(0.5 * fov) / Math.sin(0.5 * fov));
        final double w = h * height / width;
        res.m00 = w;
        res.m01 = 0.0;
        res.m02 = 0.0;
        res.m03 = 0.0;
        res.m10 = 0.0;
        res.m11 = h;
        res.m12 = 0.0;
        res.m13 = 0.0;
        res.m20 = 0.0;
        res.m21 = 0.0;
        res.m22 = (zFar + zNear) / (zFar - zNear);
        res.m23 = 1.0;
        res.m30 = 0.0;
        res.m31 = 0.0;
        res.m32 = -2.0 * zFar * zNear / (zFar - zNear);
        res.m33 = 0.0;
        return res;
    }
}
