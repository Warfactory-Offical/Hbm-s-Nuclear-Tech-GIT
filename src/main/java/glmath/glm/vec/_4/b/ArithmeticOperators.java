/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.b;

import glmath.glm.Glm;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 4 * Byte.BYTES;

    public byte x, y, z, w;

    public Vec4b add_(final byte b) {
        return Glm.add(new Vec4b(), (Vec4b) this, (int) b, b, b, b);
    }

    public Vec4b add_(final int b) {
        return Glm.add(new Vec4b(), (Vec4b) this, b, b, b, b);
    }

    public Vec4b add_(final byte bX, final byte bY, final byte bZ, final byte bW) {
        return Glm.add(new Vec4b(), (Vec4b) this, (int) bX, bY, bZ, bW);
    }

    public Vec4b add_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.add(new Vec4b(), (Vec4b) this, bX, bY, bZ, bW);
    }

    public Vec4b add_(final Vec4b b) {
        return Glm.add(new Vec4b(), (Vec4b) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4b add(final byte b) {
        return Glm.add((Vec4b) this, (Vec4b) this, (int) b, b, b, b);
    }

    public Vec4b add(final int b) {
        return Glm.add((Vec4b) this, (Vec4b) this, b, b, b, b);
    }

    public Vec4b add(final byte bX, final byte bY, final byte bZ, final byte bW) {
        return Glm.add((Vec4b) this, (Vec4b) this, (int) bX, bY, bZ, bW);
    }

    public Vec4b add(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.add((Vec4b) this, (Vec4b) this, bX, bY, bZ, bW);
    }

    public Vec4b add(final Vec4b b) {
        return Glm.add((Vec4b) this, (Vec4b) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4b add(final byte b, final Vec4b res) {
        return Glm.add(res, (Vec4b) this, (int) b, b, b, b);
    }

    public Vec4b add(final int b, final Vec4b res) {
        return Glm.add(res, (Vec4b) this, b, b, b, b);
    }

    public Vec4b add(final byte bX, final byte bY, final byte bZ, final byte bW, final Vec4b res) {
        return Glm.add(res, (Vec4b) this, (int) bX, bY, bZ, bW);
    }

    public Vec4b add(final int bX, final int bY, final int bZ, final int bW, final Vec4b res) {
        return Glm.add(res, (Vec4b) this, bX, bY, bZ, bW);
    }

    public Vec4b add(final Vec4b b, final Vec4b res) {
        return Glm.add(res, (Vec4b) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4b sub_(final byte b) {
        return Glm.sub(new Vec4b(), (Vec4b) this, (int) b, b, b, b);
    }

    public Vec4b sub_(final int b) {
        return Glm.sub(new Vec4b(), (Vec4b) this, b, b, b, b);
    }

    public Vec4b sub_(final byte bX, final byte bY, final byte bZ, final byte bW) {
        return Glm.sub(new Vec4b(), (Vec4b) this, (int) bX, bY, bZ, bW);
    }

    public Vec4b sub_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.sub(new Vec4b(), (Vec4b) this, bX, bY, bZ, bW);
    }

    public Vec4b sub_(final Vec4b b) {
        return Glm.sub(new Vec4b(), (Vec4b) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4b sub(final byte b) {
        return Glm.sub((Vec4b) this, (Vec4b) this, (int) b, b, b, b);
    }

    public Vec4b sub(final int b) {
        return Glm.sub((Vec4b) this, (Vec4b) this, b, b, b, b);
    }

    public Vec4b sub(final byte bX, final byte bY, final byte bZ, final byte bW) {
        return Glm.sub((Vec4b) this, (Vec4b) this, (int) bX, bY, bZ, bW);
    }

    public Vec4b sub(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.sub((Vec4b) this, (Vec4b) this, bX, bY, bZ, bW);
    }

    public Vec4b sub(final Vec4b b) {
        return Glm.sub((Vec4b) this, (Vec4b) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4b sub(final byte b, final Vec4b res) {
        return Glm.sub(res, (Vec4b) this, (int) b, b, b, b);
    }

    public Vec4b sub(final int b, final Vec4b res) {
        return Glm.sub(res, (Vec4b) this, b, b, b, b);
    }

    public Vec4b sub(final byte bX, final byte bY, final byte bZ, final byte bW, final Vec4b res) {
        return Glm.sub(res, (Vec4b) this, (int) bX, bY, bZ, bW);
    }

    public Vec4b sub(final int bX, final int bY, final int bZ, final int bW, final Vec4b res) {
        return Glm.sub(res, (Vec4b) this, bX, bY, bZ, bW);
    }

    public Vec4b sub(final Vec4b b, final Vec4b res) {
        return Glm.sub(res, (Vec4b) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4b mul_(final byte b) {
        return Glm.mul(new Vec4b(), (Vec4b) this, (int) b, b, b, b);
    }

    public Vec4b mul_(final int b) {
        return Glm.mul(new Vec4b(), (Vec4b) this, b, b, b, b);
    }

    public Vec4b mul_(final byte bX, final byte bY, final byte bZ, final byte bW) {
        return Glm.mul(new Vec4b(), (Vec4b) this, (int) bX, bY, bZ, bW);
    }

    public Vec4b mul_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.mul(new Vec4b(), (Vec4b) this, bX, bY, bZ, bW);
    }

    public Vec4b mul_(final Vec4b b) {
        return Glm.mul(new Vec4b(), (Vec4b) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4b mul(final byte b) {
        return Glm.mul((Vec4b) this, (Vec4b) this, (int) b, b, b, b);
    }

    public Vec4b mul(final int b) {
        return Glm.mul((Vec4b) this, (Vec4b) this, b, b, b, b);
    }

    public Vec4b mul(final byte bX, final byte bY, final byte bZ, final byte bW) {
        return Glm.mul((Vec4b) this, (Vec4b) this, (int) bX, bY, bZ, bW);
    }

    public Vec4b mul(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.mul((Vec4b) this, (Vec4b) this, bX, bY, bZ, bW);
    }

    public Vec4b mul(final Vec4b b) {
        return Glm.mul((Vec4b) this, (Vec4b) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4b mul(final byte b, final Vec4b res) {
        return Glm.mul(res, (Vec4b) this, (int) b, b, b, b);
    }

    public Vec4b mul(final int b, final Vec4b res) {
        return Glm.mul(res, (Vec4b) this, b, b, b, b);
    }

    public Vec4b mul(final byte bX, final byte bY, final byte bZ, final byte bW, final Vec4b res) {
        return Glm.mul(res, (Vec4b) this, (int) bX, bY, bZ, bW);
    }

    public Vec4b mul(final int bX, final int bY, final int bZ, final int bW, final Vec4b res) {
        return Glm.mul(res, (Vec4b) this, bX, bY, bZ, bW);
    }

    public Vec4b mul(final Vec4b b, final Vec4b res) {
        return Glm.mul(res, (Vec4b) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4b div_(final byte b) {
        return Glm.div(new Vec4b(), (Vec4b) this, (int) b, b, b, b);
    }

    public Vec4b div_(final int b) {
        return Glm.div(new Vec4b(), (Vec4b) this, b, b, b, b);
    }

    public Vec4b div_(final byte bX, final byte bY, final byte bZ, final byte bW) {
        return Glm.div(new Vec4b(), (Vec4b) this, (int) bX, bY, bZ, bW);
    }

    public Vec4b div_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.div(new Vec4b(), (Vec4b) this, bX, bY, bZ, bW);
    }

    public Vec4b div_(final Vec4b b) {
        return Glm.div(new Vec4b(), (Vec4b) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4b div(final byte b) {
        return Glm.div((Vec4b) this, (Vec4b) this, (int) b, b, b, b);
    }

    public Vec4b div(final int b) {
        return Glm.div((Vec4b) this, (Vec4b) this, b, b, b, b);
    }

    public Vec4b div(final byte bX, final byte bY, final byte bZ, final byte bW) {
        return Glm.div((Vec4b) this, (Vec4b) this, (int) bX, bY, bZ, bW);
    }

    public Vec4b div(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.div((Vec4b) this, (Vec4b) this, bX, bY, bZ, bW);
    }

    public Vec4b div(final Vec4b b) {
        return Glm.div((Vec4b) this, (Vec4b) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4b div(final byte b, final Vec4b res) {
        return Glm.div(res, (Vec4b) this, (int) b, b, b, b);
    }

    public Vec4b div(final int b, final Vec4b res) {
        return Glm.div(res, (Vec4b) this, b, b, b, b);
    }

    public Vec4b div(final byte bX, final byte bY, final byte bZ, final byte bW, final Vec4b res) {
        return Glm.div(res, (Vec4b) this, (int) bX, bY, bZ, bW);
    }

    public Vec4b div(final int bX, final int bY, final int bZ, final int bW, final Vec4b res) {
        return Glm.div(res, (Vec4b) this, bX, bY, bZ, bW);
    }

    public Vec4b div(final Vec4b b, final Vec4b res) {
        return Glm.div(res, (Vec4b) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4b incr_() {
        return Glm.incr_((Vec4b) this);
    }

    public Vec4b incr() {
        return Glm.incr((Vec4b) this);
    }

    public Vec4b incr(final Vec4b res) {
        return Glm.incr(res, (Vec4b) this);
    }

    public Vec4b decr_() {
        return Glm.decr_((Vec4b) this);
    }

    public Vec4b decr() {
        return Glm.decr((Vec4b) this);
    }

    public Vec4b decr(final Vec4b res) {
        return Glm.decr(res, (Vec4b) this);
    }
}
