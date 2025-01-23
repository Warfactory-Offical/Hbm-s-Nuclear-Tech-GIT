/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.ub;

import glmath.glm.Glm;
import glmath.joou.UByte;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 4 * Byte.BYTES;

    public UByte x = new UByte(), y = new UByte(), z = new UByte(), w = new UByte();

    public Vec4ub add_(final UByte b) {
        return Glm.add(new Vec4ub(), (Vec4ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec4ub add_(final byte b) {
        return Glm.add(new Vec4ub(), (Vec4ub) this, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec4ub add_(final int b) {
        return Glm.add(new Vec4ub(), (Vec4ub) this, b, b, b, b);
    }

    public Vec4ub add_(final UByte bX, final UByte bY, final UByte bZ, final UByte bW) {
        return Glm.add(new Vec4ub(), (Vec4ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bW.value & 0xff);
    }

    public Vec4ub add_(final byte bX, final byte bY, final byte bZ, final byte bW) {
        return Glm.add(new Vec4ub(), (Vec4ub) this, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public Vec4ub add_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.add(new Vec4ub(), (Vec4ub) this, bX, bY, bZ, bW);
    }

    public Vec4ub add_(final Vec4ub b) {
        return Glm.add(new Vec4ub(), (Vec4ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public Vec4ub add(final UByte b) {
        return Glm.add((Vec4ub) this, (Vec4ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec4ub add(final byte b) {
        return Glm.add((Vec4ub) this, (Vec4ub) this, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec4ub add(final int b) {
        return Glm.add((Vec4ub) this, (Vec4ub) this, b, b, b, b);
    }

    public Vec4ub add(final UByte bX, final UByte bY, final UByte bZ, final UByte bW) {
        return Glm.add((Vec4ub) this, (Vec4ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bW.value & 0xff);
    }

    public Vec4ub add(final byte bX, final byte bY, final byte bZ, final byte bW) {
        return Glm.add((Vec4ub) this, (Vec4ub) this, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public Vec4ub add(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.add((Vec4ub) this, (Vec4ub) this, bX, bY, bZ, bW);
    }

    public Vec4ub add(final Vec4ub b) {
        return Glm.add((Vec4ub) this, (Vec4ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public Vec4ub add(final UByte b, final Vec4ub res) {
        return Glm.add(res, (Vec4ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec4ub add(final byte b, final Vec4ub res) {
        return Glm.add(res, (Vec4ub) this, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec4ub add(final int b, final Vec4ub res) {
        return Glm.add(res, (Vec4ub) this, b, b, b, b);
    }

    public Vec4ub add(final UByte bX, final UByte bY, final UByte bZ, final UByte bW, final Vec4ub res) {
        return Glm.add(res, (Vec4ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bW.value & 0xff);
    }

    public Vec4ub add(final byte bX, final byte bY, final byte bZ, final byte bW, final Vec4ub res) {
        return Glm.add(res, (Vec4ub) this, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public Vec4ub add(final int bX, final int bY, final int bZ, final int bW, final Vec4ub res) {
        return Glm.add(res, (Vec4ub) this, bX, bY, bZ, bW);
    }

    public Vec4ub add(final Vec4ub b, final Vec4ub res) {
        return Glm.add(res, (Vec4ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public Vec4ub sub_(final UByte b) {
        return Glm.sub(new Vec4ub(), (Vec4ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec4ub sub_(final byte b) {
        return Glm.sub(new Vec4ub(), (Vec4ub) this, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec4ub sub_(final int b) {
        return Glm.sub(new Vec4ub(), (Vec4ub) this, b, b, b, b);
    }

    public Vec4ub sub_(final UByte bX, final UByte bY, final UByte bZ, final UByte bW) {
        return Glm.sub(new Vec4ub(), (Vec4ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bW.value & 0xff);
    }

    public Vec4ub sub_(final byte bX, final byte bY, final byte bZ, final byte bW) {
        return Glm.sub(new Vec4ub(), (Vec4ub) this, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public Vec4ub sub_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.sub(new Vec4ub(), (Vec4ub) this, bX, bY, bZ, bW);
    }

    public Vec4ub sub_(final Vec4ub b) {
        return Glm.sub(new Vec4ub(), (Vec4ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public Vec4ub sub(final UByte b) {
        return Glm.sub((Vec4ub) this, (Vec4ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec4ub sub(final byte b) {
        return Glm.sub((Vec4ub) this, (Vec4ub) this, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec4ub sub(final int b) {
        return Glm.sub((Vec4ub) this, (Vec4ub) this, b, b, b, b);
    }

    public Vec4ub sub(final UByte bX, final UByte bY, final UByte bZ, final UByte bW) {
        return Glm.sub((Vec4ub) this, (Vec4ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bW.value & 0xff);
    }

    public Vec4ub sub(final byte bX, final byte bY, final byte bZ, final byte bW) {
        return Glm.sub((Vec4ub) this, (Vec4ub) this, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public Vec4ub sub(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.sub((Vec4ub) this, (Vec4ub) this, bX, bY, bZ, bW);
    }

    public Vec4ub sub(final Vec4ub b) {
        return Glm.sub((Vec4ub) this, (Vec4ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public Vec4ub sub(final UByte b, final Vec4ub res) {
        return Glm.sub(res, (Vec4ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec4ub sub(final byte b, final Vec4ub res) {
        return Glm.sub(res, (Vec4ub) this, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec4ub sub(final int b, final Vec4ub res) {
        return Glm.sub(res, (Vec4ub) this, b, b, b, b);
    }

    public Vec4ub sub(final UByte bX, final UByte bY, final UByte bZ, final UByte bW, final Vec4ub res) {
        return Glm.sub(res, (Vec4ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bW.value & 0xff);
    }

    public Vec4ub sub(final byte bX, final byte bY, final byte bZ, final byte bW, final Vec4ub res) {
        return Glm.sub(res, (Vec4ub) this, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public Vec4ub sub(final int bX, final int bY, final int bZ, final int bW, final Vec4ub res) {
        return Glm.sub(res, (Vec4ub) this, bX, bY, bZ, bW);
    }

    public Vec4ub sub(final Vec4ub b, final Vec4ub res) {
        return Glm.sub(res, (Vec4ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public Vec4ub mul_(final UByte b) {
        return Glm.mul(new Vec4ub(), (Vec4ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec4ub mul_(final byte b) {
        return Glm.mul(new Vec4ub(), (Vec4ub) this, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec4ub mul_(final int b) {
        return Glm.mul(new Vec4ub(), (Vec4ub) this, b, b, b, b);
    }

    public Vec4ub mul_(final UByte bX, final UByte bY, final UByte bZ, final UByte bW) {
        return Glm.mul(new Vec4ub(), (Vec4ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bW.value & 0xff);
    }

    public Vec4ub mul_(final byte bX, final byte bY, final byte bZ, final byte bW) {
        return Glm.mul(new Vec4ub(), (Vec4ub) this, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public Vec4ub mul_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.mul(new Vec4ub(), (Vec4ub) this, bX, bY, bZ, bW);
    }

    public Vec4ub mul_(final Vec4ub b) {
        return Glm.mul(new Vec4ub(), (Vec4ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public Vec4ub mul(final UByte b) {
        return Glm.mul((Vec4ub) this, (Vec4ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec4ub mul(final byte b) {
        return Glm.mul((Vec4ub) this, (Vec4ub) this, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec4ub mul(final int b) {
        return Glm.mul((Vec4ub) this, (Vec4ub) this, b, b, b, b);
    }

    public Vec4ub mul(final UByte bX, final UByte bY, final UByte bZ, final UByte bW) {
        return Glm.mul((Vec4ub) this, (Vec4ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bW.value & 0xff);
    }

    public Vec4ub mul(final byte bX, final byte bY, final byte bZ, final byte bW) {
        return Glm.mul((Vec4ub) this, (Vec4ub) this, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public Vec4ub mul(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.mul((Vec4ub) this, (Vec4ub) this, bX, bY, bZ, bW);
    }

    public Vec4ub mul(final Vec4ub b) {
        return Glm.mul((Vec4ub) this, (Vec4ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public Vec4ub mul(final UByte b, final Vec4ub res) {
        return Glm.mul(res, (Vec4ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec4ub mul(final byte b, final Vec4ub res) {
        return Glm.mul(res, (Vec4ub) this, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec4ub mul(final int b, final Vec4ub res) {
        return Glm.mul(res, (Vec4ub) this, b, b, b, b);
    }

    public Vec4ub mul(final UByte bX, final UByte bY, final UByte bZ, final UByte bW, final Vec4ub res) {
        return Glm.mul(res, (Vec4ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bW.value & 0xff);
    }

    public Vec4ub mul(final byte bX, final byte bY, final byte bZ, final byte bW, final Vec4ub res) {
        return Glm.mul(res, (Vec4ub) this, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public Vec4ub mul(final int bX, final int bY, final int bZ, final int bW, final Vec4ub res) {
        return Glm.mul(res, (Vec4ub) this, bX, bY, bZ, bW);
    }

    public Vec4ub mul(final Vec4ub b, final Vec4ub res) {
        return Glm.mul(res, (Vec4ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public Vec4ub div_(final UByte b) {
        return Glm.div(new Vec4ub(), (Vec4ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec4ub div_(final byte b) {
        return Glm.div(new Vec4ub(), (Vec4ub) this, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec4ub div_(final int b) {
        return Glm.div(new Vec4ub(), (Vec4ub) this, b, b, b, b);
    }

    public Vec4ub div_(final UByte bX, final UByte bY, final UByte bZ, final UByte bW) {
        return Glm.div(new Vec4ub(), (Vec4ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bW.value & 0xff);
    }

    public Vec4ub div_(final byte bX, final byte bY, final byte bZ, final byte bW) {
        return Glm.div(new Vec4ub(), (Vec4ub) this, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public Vec4ub div_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.div(new Vec4ub(), (Vec4ub) this, bX, bY, bZ, bW);
    }

    public Vec4ub div_(final Vec4ub b) {
        return Glm.div(new Vec4ub(), (Vec4ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public Vec4ub div(final UByte b) {
        return Glm.div((Vec4ub) this, (Vec4ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec4ub div(final byte b) {
        return Glm.div((Vec4ub) this, (Vec4ub) this, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec4ub div(final int b) {
        return Glm.div((Vec4ub) this, (Vec4ub) this, b, b, b, b);
    }

    public Vec4ub div(final UByte bX, final UByte bY, final UByte bZ, final UByte bW) {
        return Glm.div((Vec4ub) this, (Vec4ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bW.value & 0xff);
    }

    public Vec4ub div(final byte bX, final byte bY, final byte bZ, final byte bW) {
        return Glm.div((Vec4ub) this, (Vec4ub) this, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public Vec4ub div(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.div((Vec4ub) this, (Vec4ub) this, bX, bY, bZ, bW);
    }

    public Vec4ub div(final Vec4ub b) {
        return Glm.div((Vec4ub) this, (Vec4ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public Vec4ub div(final UByte b, final Vec4ub res) {
        return Glm.div(res, (Vec4ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec4ub div(final byte b, final Vec4ub res) {
        return Glm.div(res, (Vec4ub) this, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec4ub div(final int b, final Vec4ub res) {
        return Glm.div(res, (Vec4ub) this, b, b, b, b);
    }

    public Vec4ub div(final UByte bX, final UByte bY, final UByte bZ, final UByte bW, final Vec4ub res) {
        return Glm.div(res, (Vec4ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bW.value & 0xff);
    }

    public Vec4ub div(final byte bX, final byte bY, final byte bZ, final byte bW, final Vec4ub res) {
        return Glm.div(res, (Vec4ub) this, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public Vec4ub div(final int bX, final int bY, final int bZ, final int bW, final Vec4ub res) {
        return Glm.div(res, (Vec4ub) this, bX, bY, bZ, bW);
    }

    public Vec4ub div(final Vec4ub b, final Vec4ub res) {
        return Glm.div(res, (Vec4ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public Vec4ub incr_() {
        return Glm.incr_((Vec4ub) this);
    }

    public Vec4ub incr() {
        return Glm.incr((Vec4ub) this);
    }

    public Vec4ub incr(final Vec4ub res) {
        return Glm.incr(res, (Vec4ub) this);
    }

    public Vec4ub decr_() {
        return Glm.decr_((Vec4ub) this);
    }

    public Vec4ub decr() {
        return Glm.decr((Vec4ub) this);
    }

    public Vec4ub decr(final Vec4ub res) {
        return Glm.decr(res, (Vec4ub) this);
    }
}
