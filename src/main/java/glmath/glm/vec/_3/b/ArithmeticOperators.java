/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.b;

import glmath.glm.Glm;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 3 * Byte.BYTES;

    public byte x, y, z;

    public Vec3b add_(final byte b) {
        return Glm.add(new Vec3b(), (Vec3b) this, (int) b, b, b);
    }

    public Vec3b add_(final int b) {
        return Glm.add(new Vec3b(), (Vec3b) this, b, b, b);
    }

    public Vec3b add_(final byte bX, final byte bY, final byte bZ) {
        return Glm.add(new Vec3b(), (Vec3b) this, (int) bX, bY, bZ);
    }

    public Vec3b add_(final int bX, final int bY, final int bZ) {
        return Glm.add(new Vec3b(), (Vec3b) this, bX, bY, bZ);
    }

    public Vec3b add_(final Vec3b b) {
        return Glm.add(new Vec3b(), (Vec3b) this, (int) b.x, b.y, b.z);
    }

    public Vec3b add(final byte b) {
        return Glm.add((Vec3b) this, (Vec3b) this, (int) b, b, b);
    }

    public Vec3b add(final int b) {
        return Glm.add((Vec3b) this, (Vec3b) this, b, b, b);
    }

    public Vec3b add(final byte bX, final byte bY, final byte bZ) {
        return Glm.add((Vec3b) this, (Vec3b) this, (int) bX, bY, bZ);
    }

    public Vec3b add(final int bX, final int bY, final int bZ) {
        return Glm.add((Vec3b) this, (Vec3b) this, bX, bY, bZ);
    }

    public Vec3b add(final Vec3b b) {
        return Glm.add((Vec3b) this, (Vec3b) this, (int) b.x, b.y, b.z);
    }

    public Vec3b add(final byte b, final Vec3b res) {
        return Glm.add(res, (Vec3b) this, (int) b, b, b);
    }

    public Vec3b add(final int b, final Vec3b res) {
        return Glm.add(res, (Vec3b) this, b, b, b);
    }

    public Vec3b add(final byte bX, final byte bY, final byte bZ, final Vec3b res) {
        return Glm.add(res, (Vec3b) this, (int) bX, bY, bZ);
    }

    public Vec3b add(final int bX, final int bY, final int bZ, final Vec3b res) {
        return Glm.add(res, (Vec3b) this, bX, bY, bZ);
    }

    public Vec3b add(final Vec3b b, final Vec3b res) {
        return Glm.add(res, (Vec3b) this, (int) b.x, b.y, b.z);
    }

    public Vec3b sub_(final byte b) {
        return Glm.sub(new Vec3b(), (Vec3b) this, (int) b, b, b);
    }

    public Vec3b sub_(final int b) {
        return Glm.sub(new Vec3b(), (Vec3b) this, b, b, b);
    }

    public Vec3b sub_(final byte bX, final byte bY, final byte bZ) {
        return Glm.sub(new Vec3b(), (Vec3b) this, (int) bX, bY, bZ);
    }

    public Vec3b sub_(final int bX, final int bY, final int bZ) {
        return Glm.sub(new Vec3b(), (Vec3b) this, bX, bY, bZ);
    }

    public Vec3b sub_(final Vec3b b) {
        return Glm.sub(new Vec3b(), (Vec3b) this, (int) b.x, b.y, b.z);
    }

    public Vec3b sub(final byte b) {
        return Glm.sub((Vec3b) this, (Vec3b) this, (int) b, b, b);
    }

    public Vec3b sub(final int b) {
        return Glm.sub((Vec3b) this, (Vec3b) this, b, b, b);
    }

    public Vec3b sub(final byte bX, final byte bY, final byte bZ) {
        return Glm.sub((Vec3b) this, (Vec3b) this, (int) bX, bY, bZ);
    }

    public Vec3b sub(final int bX, final int bY, final int bZ) {
        return Glm.sub((Vec3b) this, (Vec3b) this, bX, bY, bZ);
    }

    public Vec3b sub(final Vec3b b) {
        return Glm.sub((Vec3b) this, (Vec3b) this, (int) b.x, b.y, b.z);
    }

    public Vec3b sub(final byte b, final Vec3b res) {
        return Glm.sub(res, (Vec3b) this, (int) b, b, b);
    }

    public Vec3b sub(final int b, final Vec3b res) {
        return Glm.sub(res, (Vec3b) this, b, b, b);
    }

    public Vec3b sub(final byte bX, final byte bY, final byte bZ, final Vec3b res) {
        return Glm.sub(res, (Vec3b) this, (int) bX, bY, bZ);
    }

    public Vec3b sub(final int bX, final int bY, final int bZ, final Vec3b res) {
        return Glm.sub(res, (Vec3b) this, bX, bY, bZ);
    }

    public Vec3b sub(final Vec3b b, final Vec3b res) {
        return Glm.sub(res, (Vec3b) this, (int) b.x, b.y, b.z);
    }

    public Vec3b mul_(final byte b) {
        return Glm.mul(new Vec3b(), (Vec3b) this, (int) b, b, b);
    }

    public Vec3b mul_(final int b) {
        return Glm.mul(new Vec3b(), (Vec3b) this, b, b, b);
    }

    public Vec3b mul_(final byte bX, final byte bY, final byte bZ) {
        return Glm.mul(new Vec3b(), (Vec3b) this, (int) bX, bY, bZ);
    }

    public Vec3b mul_(final int bX, final int bY, final int bZ) {
        return Glm.mul(new Vec3b(), (Vec3b) this, bX, bY, bZ);
    }

    public Vec3b mul_(final Vec3b b) {
        return Glm.mul(new Vec3b(), (Vec3b) this, (int) b.x, b.y, b.z);
    }

    public Vec3b mul(final byte b) {
        return Glm.mul((Vec3b) this, (Vec3b) this, (int) b, b, b);
    }

    public Vec3b mul(final int b) {
        return Glm.mul((Vec3b) this, (Vec3b) this, b, b, b);
    }

    public Vec3b mul(final byte bX, final byte bY, final byte bZ) {
        return Glm.mul((Vec3b) this, (Vec3b) this, (int) bX, bY, bZ);
    }

    public Vec3b mul(final int bX, final int bY, final int bZ) {
        return Glm.mul((Vec3b) this, (Vec3b) this, bX, bY, bZ);
    }

    public Vec3b mul(final Vec3b b) {
        return Glm.mul((Vec3b) this, (Vec3b) this, (int) b.x, b.y, b.z);
    }

    public Vec3b mul(final byte b, final Vec3b res) {
        return Glm.mul(res, (Vec3b) this, (int) b, b, b);
    }

    public Vec3b mul(final int b, final Vec3b res) {
        return Glm.mul(res, (Vec3b) this, b, b, b);
    }

    public Vec3b mul(final byte bX, final byte bY, final byte bZ, final Vec3b res) {
        return Glm.mul(res, (Vec3b) this, (int) bX, bY, bZ);
    }

    public Vec3b mul(final int bX, final int bY, final int bZ, final Vec3b res) {
        return Glm.mul(res, (Vec3b) this, bX, bY, bZ);
    }

    public Vec3b mul(final Vec3b b, final Vec3b res) {
        return Glm.mul(res, (Vec3b) this, (int) b.x, b.y, b.z);
    }

    public Vec3b div_(final byte b) {
        return Glm.div(new Vec3b(), (Vec3b) this, (int) b, b, b);
    }

    public Vec3b div_(final int b) {
        return Glm.div(new Vec3b(), (Vec3b) this, b, b, b);
    }

    public Vec3b div_(final byte bX, final byte bY, final byte bZ) {
        return Glm.div(new Vec3b(), (Vec3b) this, (int) bX, bY, bZ);
    }

    public Vec3b div_(final int bX, final int bY, final int bZ) {
        return Glm.div(new Vec3b(), (Vec3b) this, bX, bY, bZ);
    }

    public Vec3b div_(final Vec3b b) {
        return Glm.div(new Vec3b(), (Vec3b) this, (int) b.x, b.y, b.z);
    }

    public Vec3b div(final byte b) {
        return Glm.div((Vec3b) this, (Vec3b) this, (int) b, b, b);
    }

    public Vec3b div(final int b) {
        return Glm.div((Vec3b) this, (Vec3b) this, b, b, b);
    }

    public Vec3b div(final byte bX, final byte bY, final byte bZ) {
        return Glm.div((Vec3b) this, (Vec3b) this, (int) bX, bY, bZ);
    }

    public Vec3b div(final int bX, final int bY, final int bZ) {
        return Glm.div((Vec3b) this, (Vec3b) this, bX, bY, bZ);
    }

    public Vec3b div(final Vec3b b) {
        return Glm.div((Vec3b) this, (Vec3b) this, (int) b.x, b.y, b.z);
    }

    public Vec3b div(final byte b, final Vec3b res) {
        return Glm.div(res, (Vec3b) this, (int) b, b, b);
    }

    public Vec3b div(final int b, final Vec3b res) {
        return Glm.div(res, (Vec3b) this, b, b, b);
    }

    public Vec3b div(final byte bX, final byte bY, final byte bZ, final Vec3b res) {
        return Glm.div(res, (Vec3b) this, (int) bX, bY, bZ);
    }

    public Vec3b div(final int bX, final int bY, final int bZ, final Vec3b res) {
        return Glm.div(res, (Vec3b) this, bX, bY, bZ);
    }

    public Vec3b div(final Vec3b b, final Vec3b res) {
        return Glm.div(res, (Vec3b) this, (int) b.x, b.y, b.z);
    }

    public Vec3b incr_() {
        return Glm.incr_((Vec3b) this);
    }

    public Vec3b incr() {
        return Glm.incr((Vec3b) this);
    }

    public Vec3b incr(final Vec3b res) {
        return Glm.incr(res, (Vec3b) this);
    }

    public Vec3b decr_() {
        return Glm.decr_((Vec3b) this);
    }

    public Vec3b decr() {
        return Glm.decr((Vec3b) this);
    }

    public Vec3b decr(final Vec3b res) {
        return Glm.decr(res, (Vec3b) this);
    }
}
