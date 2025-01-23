/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.ub;

import glmath.glm.Glm;
import glmath.joou.UByte;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 3 * Byte.BYTES;

    public UByte x = new UByte(), y = new UByte(), z = new UByte();

    public Vec3ub add_(final UByte b) {
        return Glm.add(new Vec3ub(), (Vec3ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec3ub add_(final byte b) {
        return Glm.add(new Vec3ub(), (Vec3ub) this, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec3ub add_(final int b) {
        return Glm.add(new Vec3ub(), (Vec3ub) this, b, b, b);
    }

    public Vec3ub add_(final UByte bX, final UByte bY, final UByte bZ) {
        return Glm.add(new Vec3ub(), (Vec3ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public Vec3ub add_(final byte bX, final byte bY, final byte bZ) {
        return Glm.add(new Vec3ub(), (Vec3ub) this, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public Vec3ub add_(final int bX, final int bY, final int bZ) {
        return Glm.add(new Vec3ub(), (Vec3ub) this, bX, bY, bZ);
    }

    public Vec3ub add_(final Vec3ub b) {
        return Glm.add(new Vec3ub(), (Vec3ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public Vec3ub add(final UByte b) {
        return Glm.add((Vec3ub) this, (Vec3ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec3ub add(final byte b) {
        return Glm.add((Vec3ub) this, (Vec3ub) this, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec3ub add(final int b) {
        return Glm.add((Vec3ub) this, (Vec3ub) this, b, b, b);
    }

    public Vec3ub add(final UByte bX, final UByte bY, final UByte bZ) {
        return Glm.add((Vec3ub) this, (Vec3ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public Vec3ub add(final byte bX, final byte bY, final byte bZ) {
        return Glm.add((Vec3ub) this, (Vec3ub) this, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public Vec3ub add(final int bX, final int bY, final int bZ) {
        return Glm.add((Vec3ub) this, (Vec3ub) this, bX, bY, bZ);
    }

    public Vec3ub add(final Vec3ub b) {
        return Glm.add((Vec3ub) this, (Vec3ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public Vec3ub add(final UByte b, final Vec3ub res) {
        return Glm.add(res, (Vec3ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec3ub add(final byte b, final Vec3ub res) {
        return Glm.add(res, (Vec3ub) this, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec3ub add(final int b, final Vec3ub res) {
        return Glm.add(res, (Vec3ub) this, b, b, b);
    }

    public Vec3ub add(final UByte bX, final UByte bY, final UByte bZ, final Vec3ub res) {
        return Glm.add(res, (Vec3ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public Vec3ub add(final byte bX, final byte bY, final byte bZ, final Vec3ub res) {
        return Glm.add(res, (Vec3ub) this, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public Vec3ub add(final int bX, final int bY, final int bZ, final Vec3ub res) {
        return Glm.add(res, (Vec3ub) this, bX, bY, bZ);
    }

    public Vec3ub add(final Vec3ub b, final Vec3ub res) {
        return Glm.add(res, (Vec3ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public Vec3ub sub_(final UByte b) {
        return Glm.sub(new Vec3ub(), (Vec3ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec3ub sub_(final byte b) {
        return Glm.sub(new Vec3ub(), (Vec3ub) this, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec3ub sub_(final int b) {
        return Glm.sub(new Vec3ub(), (Vec3ub) this, b, b, b);
    }

    public Vec3ub sub_(final UByte bX, final UByte bY, final UByte bZ) {
        return Glm.sub(new Vec3ub(), (Vec3ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public Vec3ub sub_(final byte bX, final byte bY, final byte bZ) {
        return Glm.sub(new Vec3ub(), (Vec3ub) this, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public Vec3ub sub_(final int bX, final int bY, final int bZ) {
        return Glm.sub(new Vec3ub(), (Vec3ub) this, bX, bY, bZ);
    }

    public Vec3ub sub_(final Vec3ub b) {
        return Glm.sub(new Vec3ub(), (Vec3ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public Vec3ub sub(final UByte b) {
        return Glm.sub((Vec3ub) this, (Vec3ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec3ub sub(final byte b) {
        return Glm.sub((Vec3ub) this, (Vec3ub) this, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec3ub sub(final int b) {
        return Glm.sub((Vec3ub) this, (Vec3ub) this, b, b, b);
    }

    public Vec3ub sub(final UByte bX, final UByte bY, final UByte bZ) {
        return Glm.sub((Vec3ub) this, (Vec3ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public Vec3ub sub(final byte bX, final byte bY, final byte bZ) {
        return Glm.sub((Vec3ub) this, (Vec3ub) this, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public Vec3ub sub(final int bX, final int bY, final int bZ) {
        return Glm.sub((Vec3ub) this, (Vec3ub) this, bX, bY, bZ);
    }

    public Vec3ub sub(final Vec3ub b) {
        return Glm.sub((Vec3ub) this, (Vec3ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public Vec3ub sub(final UByte b, final Vec3ub res) {
        return Glm.sub(res, (Vec3ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec3ub sub(final byte b, final Vec3ub res) {
        return Glm.sub(res, (Vec3ub) this, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec3ub sub(final int b, final Vec3ub res) {
        return Glm.sub(res, (Vec3ub) this, b, b, b);
    }

    public Vec3ub sub(final UByte bX, final UByte bY, final UByte bZ, final Vec3ub res) {
        return Glm.sub(res, (Vec3ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public Vec3ub sub(final byte bX, final byte bY, final byte bZ, final Vec3ub res) {
        return Glm.sub(res, (Vec3ub) this, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public Vec3ub sub(final int bX, final int bY, final int bZ, final Vec3ub res) {
        return Glm.sub(res, (Vec3ub) this, bX, bY, bZ);
    }

    public Vec3ub sub(final Vec3ub b, final Vec3ub res) {
        return Glm.sub(res, (Vec3ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public Vec3ub mul_(final UByte b) {
        return Glm.mul(new Vec3ub(), (Vec3ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec3ub mul_(final byte b) {
        return Glm.mul(new Vec3ub(), (Vec3ub) this, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec3ub mul_(final int b) {
        return Glm.mul(new Vec3ub(), (Vec3ub) this, b, b, b);
    }

    public Vec3ub mul_(final UByte bX, final UByte bY, final UByte bZ) {
        return Glm.mul(new Vec3ub(), (Vec3ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public Vec3ub mul_(final byte bX, final byte bY, final byte bZ) {
        return Glm.mul(new Vec3ub(), (Vec3ub) this, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public Vec3ub mul_(final int bX, final int bY, final int bZ) {
        return Glm.mul(new Vec3ub(), (Vec3ub) this, bX, bY, bZ);
    }

    public Vec3ub mul_(final Vec3ub b) {
        return Glm.mul(new Vec3ub(), (Vec3ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public Vec3ub mul(final UByte b) {
        return Glm.mul((Vec3ub) this, (Vec3ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec3ub mul(final byte b) {
        return Glm.mul((Vec3ub) this, (Vec3ub) this, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec3ub mul(final int b) {
        return Glm.mul((Vec3ub) this, (Vec3ub) this, b, b, b);
    }

    public Vec3ub mul(final UByte bX, final UByte bY, final UByte bZ) {
        return Glm.mul((Vec3ub) this, (Vec3ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public Vec3ub mul(final byte bX, final byte bY, final byte bZ) {
        return Glm.mul((Vec3ub) this, (Vec3ub) this, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public Vec3ub mul(final int bX, final int bY, final int bZ) {
        return Glm.mul((Vec3ub) this, (Vec3ub) this, bX, bY, bZ);
    }

    public Vec3ub mul(final Vec3ub b) {
        return Glm.mul((Vec3ub) this, (Vec3ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public Vec3ub mul(final UByte b, final Vec3ub res) {
        return Glm.mul(res, (Vec3ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec3ub mul(final byte b, final Vec3ub res) {
        return Glm.mul(res, (Vec3ub) this, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec3ub mul(final int b, final Vec3ub res) {
        return Glm.mul(res, (Vec3ub) this, b, b, b);
    }

    public Vec3ub mul(final UByte bX, final UByte bY, final UByte bZ, final Vec3ub res) {
        return Glm.mul(res, (Vec3ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public Vec3ub mul(final byte bX, final byte bY, final byte bZ, final Vec3ub res) {
        return Glm.mul(res, (Vec3ub) this, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public Vec3ub mul(final int bX, final int bY, final int bZ, final Vec3ub res) {
        return Glm.mul(res, (Vec3ub) this, bX, bY, bZ);
    }

    public Vec3ub mul(final Vec3ub b, final Vec3ub res) {
        return Glm.mul(res, (Vec3ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public Vec3ub div_(final UByte b) {
        return Glm.div(new Vec3ub(), (Vec3ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec3ub div_(final byte b) {
        return Glm.div(new Vec3ub(), (Vec3ub) this, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec3ub div_(final int b) {
        return Glm.div(new Vec3ub(), (Vec3ub) this, b, b, b);
    }

    public Vec3ub div_(final UByte bX, final UByte bY, final UByte bZ) {
        return Glm.div(new Vec3ub(), (Vec3ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public Vec3ub div_(final byte bX, final byte bY, final byte bZ) {
        return Glm.div(new Vec3ub(), (Vec3ub) this, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public Vec3ub div_(final int bX, final int bY, final int bZ) {
        return Glm.div(new Vec3ub(), (Vec3ub) this, bX, bY, bZ);
    }

    public Vec3ub div_(final Vec3ub b) {
        return Glm.div(new Vec3ub(), (Vec3ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public Vec3ub div(final UByte b) {
        return Glm.div((Vec3ub) this, (Vec3ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec3ub div(final byte b) {
        return Glm.div((Vec3ub) this, (Vec3ub) this, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec3ub div(final int b) {
        return Glm.div((Vec3ub) this, (Vec3ub) this, b, b, b);
    }

    public Vec3ub div(final UByte bX, final UByte bY, final UByte bZ) {
        return Glm.div((Vec3ub) this, (Vec3ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public Vec3ub div(final byte bX, final byte bY, final byte bZ) {
        return Glm.div((Vec3ub) this, (Vec3ub) this, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public Vec3ub div(final int bX, final int bY, final int bZ) {
        return Glm.div((Vec3ub) this, (Vec3ub) this, bX, bY, bZ);
    }

    public Vec3ub div(final Vec3ub b) {
        return Glm.div((Vec3ub) this, (Vec3ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public Vec3ub div(final UByte b, final Vec3ub res) {
        return Glm.div(res, (Vec3ub) this, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public Vec3ub div(final byte b, final Vec3ub res) {
        return Glm.div(res, (Vec3ub) this, b & 0xff, b & 0xff, b & 0xff);
    }

    public Vec3ub div(final int b, final Vec3ub res) {
        return Glm.div(res, (Vec3ub) this, b, b, b);
    }

    public Vec3ub div(final UByte bX, final UByte bY, final UByte bZ, final Vec3ub res) {
        return Glm.div(res, (Vec3ub) this, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public Vec3ub div(final byte bX, final byte bY, final byte bZ, final Vec3ub res) {
        return Glm.div(res, (Vec3ub) this, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public Vec3ub div(final int bX, final int bY, final int bZ, final Vec3ub res) {
        return Glm.div(res, (Vec3ub) this, bX, bY, bZ);
    }

    public Vec3ub div(final Vec3ub b, final Vec3ub res) {
        return Glm.div(res, (Vec3ub) this, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public Vec3ub incr_() {
        return Glm.incr_((Vec3ub) this);
    }

    public Vec3ub incr() {
        return Glm.incr((Vec3ub) this);
    }

    public Vec3ub incr(final Vec3ub res) {
        return Glm.incr(res, (Vec3ub) this);
    }

    public Vec3ub decr_() {
        return Glm.decr_((Vec3ub) this);
    }

    public Vec3ub decr() {
        return Glm.decr((Vec3ub) this);
    }

    public Vec3ub decr(final Vec3ub res) {
        return Glm.decr(res, (Vec3ub) this);
    }
}
