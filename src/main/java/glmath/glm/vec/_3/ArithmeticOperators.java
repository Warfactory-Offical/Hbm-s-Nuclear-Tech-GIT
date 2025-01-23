/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3;

import glmath.glm.Glm;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 3 * Float.BYTES;

    public float x, y, z;

    public Vec3 add_(final float b) {
        return Glm.add(new Vec3(), (Vec3) this, b, b, b);
    }

    public Vec3 add_(final float bX, final float bY, final float bZ) {
        return Glm.add(new Vec3(), (Vec3) this, bX, bY, bZ);
    }

    public Vec3 add_(final Vec3 b) {
        return Glm.add(new Vec3(), (Vec3) this, b.x, b.y, b.z);
    }

    public Vec3 add(final float b) {
        return Glm.add((Vec3) this, (Vec3) this, b, b, b);
    }

    public Vec3 add(final float bX, final float bY, final float bZ) {
        return Glm.add((Vec3) this, (Vec3) this, bX, bY, bZ);
    }

    public Vec3 add(final Vec3 b) {
        return Glm.add((Vec3) this, (Vec3) this, b.x, b.y, b.z);
    }

    public Vec3 add(final float b, final Vec3 res) {
        return Glm.add(res, (Vec3) this, b, b, b);
    }

    public Vec3 add(final float bX, final float bY, final float bZ, final Vec3 res) {
        return Glm.add(res, (Vec3) this, bX, bY, bZ);
    }

    public Vec3 add(final Vec3 b, final Vec3 res) {
        return Glm.add(res, (Vec3) this, b.x, b.y, b.z);
    }

    public Vec3 sub_(final float b) {
        return Glm.sub(new Vec3(), (Vec3) this, b, b, b);
    }

    public Vec3 sub_(final float bX, final float bY, final float bZ) {
        return Glm.sub(new Vec3(), (Vec3) this, bX, bY, bZ);
    }

    public Vec3 sub_(final Vec3 b) {
        return Glm.sub(new Vec3(), (Vec3) this, b.x, b.y, b.z);
    }

    public Vec3 sub(final float b) {
        return Glm.sub((Vec3) this, (Vec3) this, b, b, b);
    }

    public Vec3 sub(final float bX, final float bY, final float bZ) {
        return Glm.sub((Vec3) this, (Vec3) this, bX, bY, bZ);
    }

    public Vec3 sub(final Vec3 b) {
        return Glm.sub((Vec3) this, (Vec3) this, b.x, b.y, b.z);
    }

    public Vec3 sub(final float b, final Vec3 res) {
        return Glm.sub(res, (Vec3) this, b, b, b);
    }

    public Vec3 sub(final float bX, final float bY, final float bZ, final Vec3 res) {
        return Glm.sub(res, (Vec3) this, bX, bY, bZ);
    }

    public Vec3 sub(final Vec3 b, final Vec3 res) {
        return Glm.sub(res, (Vec3) this, b.x, b.y, b.z);
    }

    public Vec3 mul_(final float b) {
        return Glm.mul(new Vec3(), (Vec3) this, b, b, b);
    }

    public Vec3 mul_(final float bX, final float bY, final float bZ) {
        return Glm.mul(new Vec3(), (Vec3) this, bX, bY, bZ);
    }

    public Vec3 mul_(final Vec3 b) {
        return Glm.mul(new Vec3(), (Vec3) this, b.x, b.y, b.z);
    }

    public Vec3 mul(final float b) {
        return Glm.mul((Vec3) this, (Vec3) this, b, b, b);
    }

    public Vec3 mul(final float bX, final float bY, final float bZ) {
        return Glm.mul((Vec3) this, (Vec3) this, bX, bY, bZ);
    }

    public Vec3 mul(final Vec3 b) {
        return Glm.mul((Vec3) this, (Vec3) this, b.x, b.y, b.z);
    }

    public Vec3 mul(final float b, final Vec3 res) {
        return Glm.mul(res, (Vec3) this, b, b, b);
    }

    public Vec3 mul(final float bX, final float bY, final float bZ, final Vec3 res) {
        return Glm.mul(res, (Vec3) this, bX, bY, bZ);
    }

    public Vec3 mul(final Vec3 b, final Vec3 res) {
        return Glm.mul(res, (Vec3) this, b.x, b.y, b.z);
    }

    public Vec3 div_(final float b) {
        return Glm.div(new Vec3(), (Vec3) this, b, b, b);
    }

    public Vec3 div_(final float bX, final float bY, final float bZ) {
        return Glm.div(new Vec3(), (Vec3) this, bX, bY, bZ);
    }

    public Vec3 div_(final Vec3 b) {
        return Glm.div(new Vec3(), (Vec3) this, b.x, b.y, b.z);
    }

    public Vec3 div(final float b) {
        return Glm.div((Vec3) this, (Vec3) this, b, b, b);
    }

    public Vec3 div(final float bX, final float bY, final float bZ) {
        return Glm.div((Vec3) this, (Vec3) this, bX, bY, bZ);
    }

    public Vec3 div(final Vec3 b) {
        return Glm.div((Vec3) this, (Vec3) this, b.x, b.y, b.z);
    }

    public Vec3 div(final float b, final Vec3 res) {
        return Glm.div(res, (Vec3) this, b, b, b);
    }

    public Vec3 div(final float bX, final float bY, final float bZ, final Vec3 res) {
        return Glm.div(res, (Vec3) this, bX, bY, bZ);
    }

    public Vec3 div(final Vec3 b, final Vec3 res) {
        return Glm.div(res, (Vec3) this, b.x, b.y, b.z);
    }

    public Vec3 incr_() {
        return Glm.incr_((Vec3) this);
    }

    public Vec3 incr() {
        return Glm.incr((Vec3) this);
    }

    public Vec3 incr(final Vec3 res) {
        return Glm.incr(res, (Vec3) this);
    }

    public Vec3 decr_() {
        return Glm.decr_((Vec3) this);
    }

    public Vec3 decr() {
        return Glm.decr((Vec3) this);
    }

    public Vec3 decr(final Vec3 res) {
        return Glm.decr(res, (Vec3) this);
    }
}
