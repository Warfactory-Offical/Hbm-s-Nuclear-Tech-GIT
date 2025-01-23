/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2.ub;

import glmath.glm.Glm;
import glmath.joou.UByte;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 2 * Byte.BYTES;

    public UByte x = new UByte(), y = new UByte();

    public Vec2ub add_(final UByte b) {
        return Glm.add(new Vec2ub(), (Vec2ub) this, b.value & 0xff, b.value& 0xff);
    }

    public Vec2ub add_(final byte b) {
        return Glm.add(new Vec2ub(), (Vec2ub) this, b & 0xff, b & 0xff);
    }

    public Vec2ub add_(final int b) {
        return Glm.add(new Vec2ub(), (Vec2ub) this, b, b);
    }

    public Vec2ub add_(final UByte bX, final UByte bY) {
        return Glm.add(new Vec2ub(), (Vec2ub) this, bX.value & 0xff, bY.value & 0xff);
    }

    public Vec2ub add_(final byte bX, final byte bY) {
        return Glm.add(new Vec2ub(), (Vec2ub) this, bX & 0xff, bY & 0xff);
    }

    public Vec2ub add_(final int bX, final int bY) {
        return Glm.add(new Vec2ub(), (Vec2ub) this, bX, bY);
    }

    public Vec2ub add_(final Vec2ub b) {
        return Glm.add(new Vec2ub(), (Vec2ub) this, b.x.value & 0xff, b.y.value & 0xff);
    }

    public Vec2ub add(final UByte b) {
        return Glm.add((Vec2ub) this, (Vec2ub) this, b.value & 0xff, b.value& 0xff);
    }

    public Vec2ub add(final byte b) {
        return Glm.add((Vec2ub) this, (Vec2ub) this, b & 0xff, b & 0xff);
    }

    public Vec2ub add(final int b) {
        return Glm.add((Vec2ub) this, (Vec2ub) this, b, b);
    }

    public Vec2ub add(final UByte bX, final UByte bY) {
        return Glm.add((Vec2ub) this, (Vec2ub) this, bX.value & 0xff, bY.value & 0xff);
    }

    public Vec2ub add(final byte bX, final byte bY) {
        return Glm.add((Vec2ub) this, (Vec2ub) this, bX & 0xff, bY & 0xff);
    }

    public Vec2ub add(final int bX, final int bY) {
        return Glm.add((Vec2ub) this, (Vec2ub) this, bX, bY);
    }

    public Vec2ub add(final Vec2ub b) {
        return Glm.add((Vec2ub) this, (Vec2ub) this, b.x.value & 0xff, b.y.value & 0xff);
    }

    public Vec2ub add(final UByte b, final Vec2ub res) {
        return Glm.add(res, (Vec2ub) this, b.value & 0xff, b.value& 0xff);
    }

    public Vec2ub add(final byte b, final Vec2ub res) {
        return Glm.add(res, (Vec2ub) this, b & 0xff, b & 0xff);
    }

    public Vec2ub add(final int b, final Vec2ub res) {
        return Glm.add(res, (Vec2ub) this, b, b);
    }

    public Vec2ub add(final UByte bX, final UByte bY, final Vec2ub res) {
        return Glm.add(res, (Vec2ub) this, bX.value & 0xff, bY.value & 0xff);
    }

    public Vec2ub add(final byte bX, final byte bY, final Vec2ub res) {
        return Glm.add(res, (Vec2ub) this, bX & 0xff, bY & 0xff);
    }

    public Vec2ub add(final int bX, final int bY, final Vec2ub res) {
        return Glm.add(res, (Vec2ub) this, bX, bY);
    }

    public Vec2ub add(final Vec2ub b, final Vec2ub res) {
        return Glm.add(res, (Vec2ub) this, b.x.value & 0xff, b.y.value & 0xff);
    }

    public Vec2ub sub_(final UByte b) {
        return Glm.sub(new Vec2ub(), (Vec2ub) this, b.value & 0xff, b.value& 0xff);
    }

    public Vec2ub sub_(final byte b) {
        return Glm.sub(new Vec2ub(), (Vec2ub) this, b & 0xff, b & 0xff);
    }

    public Vec2ub sub_(final int b) {
        return Glm.sub(new Vec2ub(), (Vec2ub) this, b, b);
    }

    public Vec2ub sub_(final UByte bX, final UByte bY) {
        return Glm.sub(new Vec2ub(), (Vec2ub) this, bX.value & 0xff, bY.value & 0xff);
    }

    public Vec2ub sub_(final byte bX, final byte bY) {
        return Glm.sub(new Vec2ub(), (Vec2ub) this, bX & 0xff, bY & 0xff);
    }

    public Vec2ub sub_(final int bX, final int bY) {
        return Glm.sub(new Vec2ub(), (Vec2ub) this, bX, bY);
    }

    public Vec2ub sub_(final Vec2ub b) {
        return Glm.sub(new Vec2ub(), (Vec2ub) this, b.x.value & 0xff, b.y.value & 0xff);
    }

    public Vec2ub sub(final UByte b) {
        return Glm.sub((Vec2ub) this, (Vec2ub) this, b.value & 0xff, b.value& 0xff);
    }

    public Vec2ub sub(final byte b) {
        return Glm.sub((Vec2ub) this, (Vec2ub) this, b & 0xff, b & 0xff);
    }

    public Vec2ub sub(final int b) {
        return Glm.sub((Vec2ub) this, (Vec2ub) this, b, b);
    }

    public Vec2ub sub(final UByte bX, final UByte bY) {
        return Glm.sub((Vec2ub) this, (Vec2ub) this, bX.value & 0xff, bY.value & 0xff);
    }

    public Vec2ub sub(final byte bX, final byte bY) {
        return Glm.sub((Vec2ub) this, (Vec2ub) this, bX & 0xff, bY & 0xff);
    }

    public Vec2ub sub(final int bX, final int bY) {
        return Glm.sub((Vec2ub) this, (Vec2ub) this, bX, bY);
    }

    public Vec2ub sub(final Vec2ub b) {
        return Glm.sub((Vec2ub) this, (Vec2ub) this, b.x.value & 0xff, b.y.value & 0xff);
    }

    public Vec2ub sub(final UByte b, final Vec2ub res) {
        return Glm.sub(res, (Vec2ub) this, b.value & 0xff, b.value& 0xff);
    }

    public Vec2ub sub(final byte b, final Vec2ub res) {
        return Glm.sub(res, (Vec2ub) this, b & 0xff, b & 0xff);
    }

    public Vec2ub sub(final int b, final Vec2ub res) {
        return Glm.sub(res, (Vec2ub) this, b, b);
    }

    public Vec2ub sub(final UByte bX, final UByte bY, final Vec2ub res) {
        return Glm.sub(res, (Vec2ub) this, bX.value & 0xff, bY.value & 0xff);
    }

    public Vec2ub sub(final byte bX, final byte bY, final Vec2ub res) {
        return Glm.sub(res, (Vec2ub) this, bX & 0xff, bY & 0xff);
    }

    public Vec2ub sub(final int bX, final int bY, final Vec2ub res) {
        return Glm.sub(res, (Vec2ub) this, bX, bY);
    }

    public Vec2ub sub(final Vec2ub b, final Vec2ub res) {
        return Glm.sub(res, (Vec2ub) this, b.x.value & 0xff, b.y.value & 0xff);
    }

    public Vec2ub mul_(final UByte b) {
        return Glm.mul(new Vec2ub(), (Vec2ub) this, b.value & 0xff, b.value& 0xff);
    }

    public Vec2ub mul_(final byte b) {
        return Glm.mul(new Vec2ub(), (Vec2ub) this, b & 0xff, b & 0xff);
    }

    public Vec2ub mul_(final int b) {
        return Glm.mul(new Vec2ub(), (Vec2ub) this, b, b);
    }

    public Vec2ub mul_(final UByte bX, final UByte bY) {
        return Glm.mul(new Vec2ub(), (Vec2ub) this, bX.value & 0xff, bY.value & 0xff);
    }

    public Vec2ub mul_(final byte bX, final byte bY) {
        return Glm.mul(new Vec2ub(), (Vec2ub) this, bX & 0xff, bY & 0xff);
    }

    public Vec2ub mul_(final int bX, final int bY) {
        return Glm.mul(new Vec2ub(), (Vec2ub) this, bX, bY);
    }

    public Vec2ub mul_(final Vec2ub b) {
        return Glm.mul(new Vec2ub(), (Vec2ub) this, b.x.value & 0xff, b.y.value & 0xff);
    }

    public Vec2ub mul(final UByte b) {
        return Glm.mul((Vec2ub) this, (Vec2ub) this, b.value & 0xff, b.value& 0xff);
    }

    public Vec2ub mul(final byte b) {
        return Glm.mul((Vec2ub) this, (Vec2ub) this, b & 0xff, b & 0xff);
    }

    public Vec2ub mul(final int b) {
        return Glm.mul((Vec2ub) this, (Vec2ub) this, b, b);
    }

    public Vec2ub mul(final UByte bX, final UByte bY) {
        return Glm.mul((Vec2ub) this, (Vec2ub) this, bX.value & 0xff, bY.value & 0xff);
    }

    public Vec2ub mul(final byte bX, final byte bY) {
        return Glm.mul((Vec2ub) this, (Vec2ub) this, bX & 0xff, bY & 0xff);
    }

    public Vec2ub mul(final int bX, final int bY) {
        return Glm.mul((Vec2ub) this, (Vec2ub) this, bX, bY);
    }

    public Vec2ub mul(final Vec2ub b) {
        return Glm.mul((Vec2ub) this, (Vec2ub) this, b.x.value & 0xff, b.y.value & 0xff);
    }

    public Vec2ub mul(final UByte b, final Vec2ub res) {
        return Glm.mul(res, (Vec2ub) this, b.value & 0xff, b.value& 0xff);
    }

    public Vec2ub mul(final byte b, final Vec2ub res) {
        return Glm.mul(res, (Vec2ub) this, b & 0xff, b & 0xff);
    }

    public Vec2ub mul(final int b, final Vec2ub res) {
        return Glm.mul(res, (Vec2ub) this, b, b);
    }

    public Vec2ub mul(final UByte bX, final UByte bY, final Vec2ub res) {
        return Glm.mul(res, (Vec2ub) this, bX.value & 0xff, bY.value & 0xff);
    }

    public Vec2ub mul(final byte bX, final byte bY, final Vec2ub res) {
        return Glm.mul(res, (Vec2ub) this, bX & 0xff, bY & 0xff);
    }

    public Vec2ub mul(final int bX, final int bY, final Vec2ub res) {
        return Glm.mul(res, (Vec2ub) this, bX, bY);
    }

    public Vec2ub mul(final Vec2ub b, final Vec2ub res) {
        return Glm.mul(res, (Vec2ub) this, b.x.value & 0xff, b.y.value & 0xff);
    }

    public Vec2ub div_(final UByte b) {
        return Glm.div(new Vec2ub(), (Vec2ub) this, b.value & 0xff, b.value& 0xff);
    }

    public Vec2ub div_(final byte b) {
        return Glm.div(new Vec2ub(), (Vec2ub) this, b & 0xff, b & 0xff);
    }

    public Vec2ub div_(final int b) {
        return Glm.div(new Vec2ub(), (Vec2ub) this, b, b);
    }

    public Vec2ub div_(final UByte bX, final UByte bY) {
        return Glm.div(new Vec2ub(), (Vec2ub) this, bX.value & 0xff, bY.value & 0xff);
    }

    public Vec2ub div_(final byte bX, final byte bY) {
        return Glm.div(new Vec2ub(), (Vec2ub) this, bX & 0xff, bY & 0xff);
    }

    public Vec2ub div_(final int bX, final int bY) {
        return Glm.div(new Vec2ub(), (Vec2ub) this, bX, bY);
    }

    public Vec2ub div_(final Vec2ub b) {
        return Glm.div(new Vec2ub(), (Vec2ub) this, b.x.value & 0xff, b.y.value & 0xff);
    }

    public Vec2ub div(final UByte b) {
        return Glm.div((Vec2ub) this, (Vec2ub) this, b.value & 0xff, b.value& 0xff);
    }

    public Vec2ub div(final byte b) {
        return Glm.div((Vec2ub) this, (Vec2ub) this, b & 0xff, b & 0xff);
    }

    public Vec2ub div(final int b) {
        return Glm.div((Vec2ub) this, (Vec2ub) this, b, b);
    }

    public Vec2ub div(final UByte bX, final UByte bY) {
        return Glm.div((Vec2ub) this, (Vec2ub) this, bX.value & 0xff, bY.value & 0xff);
    }

    public Vec2ub div(final byte bX, final byte bY) {
        return Glm.div((Vec2ub) this, (Vec2ub) this, bX & 0xff, bY & 0xff);
    }

    public Vec2ub div(final int bX, final int bY) {
        return Glm.div((Vec2ub) this, (Vec2ub) this, bX, bY);
    }

    public Vec2ub div(final Vec2ub b) {
        return Glm.div((Vec2ub) this, (Vec2ub) this, b.x.value & 0xff, b.y.value & 0xff);
    }

    public Vec2ub div(final UByte b, final Vec2ub res) {
        return Glm.div(res, (Vec2ub) this, b.value & 0xff, b.value& 0xff);
    }

    public Vec2ub div(final byte b, final Vec2ub res) {
        return Glm.div(res, (Vec2ub) this, b & 0xff, b & 0xff);
    }

    public Vec2ub div(final int b, final Vec2ub res) {
        return Glm.div(res, (Vec2ub) this, b, b);
    }

    public Vec2ub div(final UByte bX, final UByte bY, final Vec2ub res) {
        return Glm.div(res, (Vec2ub) this, bX.value & 0xff, bY.value & 0xff);
    }

    public Vec2ub div(final byte bX, final byte bY, final Vec2ub res) {
        return Glm.div(res, (Vec2ub) this, bX & 0xff, bY & 0xff);
    }

    public Vec2ub div(final int bX, final int bY, final Vec2ub res) {
        return Glm.div(res, (Vec2ub) this, bX, bY);
    }

    public Vec2ub div(final Vec2ub b, final Vec2ub res) {
        return Glm.div(res, (Vec2ub) this, b.x.value & 0xff, b.y.value & 0xff);
    }

    public Vec2ub incr_() {
        return Glm.incr_((Vec2ub) this);
    }

    public Vec2ub incr() {
        return Glm.incr((Vec2ub) this);
    }

    public Vec2ub incr(final Vec2ub res) {
        return Glm.incr(res, (Vec2ub) this);
    }

    public Vec2ub decr_() {
        return Glm.decr_((Vec2ub) this);
    }

    public Vec2ub decr() {
        return Glm.decr((Vec2ub) this);
    }

    public Vec2ub decr(final Vec2ub res) {
        return Glm.decr(res, (Vec2ub) this);
    }
}
