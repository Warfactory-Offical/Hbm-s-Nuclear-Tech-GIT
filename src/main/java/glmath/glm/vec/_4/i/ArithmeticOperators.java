/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.i;

import glmath.glm.Glm;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 4 * Float.BYTES;

    public int x, y, z, w;

    public Vec4i add_(final int b) {
        return Glm.add(new Vec4i(), (Vec4i) this, b, b, b, b);
    }

    public Vec4i add_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.add(new Vec4i(), (Vec4i) this, bX, bY, bZ, bW);
    }

    public Vec4i add_(final Vec4i b) {
        return Glm.add(new Vec4i(), (Vec4i) this, b.x, b.y, b.z, b.w);
    }

    public Vec4i add(final int b) {
        return Glm.add((Vec4i) this, (Vec4i) this, b, b, b, b);
    }

    public Vec4i add(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.add((Vec4i) this, (Vec4i) this, bX, bY, bZ, bW);
    }

    public Vec4i add(final Vec4i b) {
        return Glm.add((Vec4i) this, (Vec4i) this, b.x, b.y, b.z, b.w);
    }

    public Vec4i add(final int b, final Vec4i res) {
        return Glm.add(res, (Vec4i) this, b, b, b, b);
    }

    public Vec4i add(final int bX, final int bY, final int bZ, final int bW, final Vec4i res) {
        return Glm.add(res, (Vec4i) this, bX, bY, bZ, bW);
    }

    public Vec4i add(final Vec4i b, final Vec4i res) {
        return Glm.add(res, (Vec4i) this, b.x, b.y, b.z, b.w);
    }

    public Vec4i sub_(final int b) {
        return Glm.sub(new Vec4i(), (Vec4i) this, b, b, b, b);
    }

    public Vec4i sub_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.sub(new Vec4i(), (Vec4i) this, bX, bY, bZ, bW);
    }

    public Vec4i sub_(final Vec4i b) {
        return Glm.sub(new Vec4i(), (Vec4i) this, b.x, b.y, b.z, b.w);
    }

    public Vec4i sub(final int b) {
        return Glm.sub((Vec4i) this, (Vec4i) this, b, b, b, b);
    }

    public Vec4i sub(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.sub((Vec4i) this, (Vec4i) this, bX, bY, bZ, bW);
    }

    public Vec4i sub(final Vec4i b) {
        return Glm.sub((Vec4i) this, (Vec4i) this, b.x, b.y, b.z, b.w);
    }

    public Vec4i sub(final int b, final Vec4i res) {
        return Glm.sub(res, (Vec4i) this, b, b, b, b);
    }

    public Vec4i sub(final int bX, final int bY, final int bZ, final int bW, final Vec4i res) {
        return Glm.sub(res, (Vec4i) this, bX, bY, bZ, bW);
    }

    public Vec4i sub(final Vec4i b, final Vec4i res) {
        return Glm.sub(res, (Vec4i) this, b.x, b.y, b.z, b.w);
    }

    public Vec4i mul_(final int b) {
        return Glm.mul(new Vec4i(), (Vec4i) this, b, b, b, b);
    }

    public Vec4i mul_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.mul(new Vec4i(), (Vec4i) this, bX, bY, bZ, bW);
    }

    public Vec4i mul_(final Vec4i b) {
        return Glm.mul(new Vec4i(), (Vec4i) this, b.x, b.y, b.z, b.w);
    }

    public Vec4i mul(final int b) {
        return Glm.mul((Vec4i) this, (Vec4i) this, b, b, b, b);
    }

    public Vec4i mul(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.mul((Vec4i) this, (Vec4i) this, bX, bY, bZ, bW);
    }

    public Vec4i mul(final Vec4i b) {
        return Glm.mul((Vec4i) this, (Vec4i) this, b.x, b.y, b.z, b.w);
    }

    public Vec4i mul(final int b, final Vec4i res) {
        return Glm.mul(res, (Vec4i) this, b, b, b, b);
    }

    public Vec4i mul(final int bX, final int bY, final int bZ, final int bW, final Vec4i res) {
        return Glm.mul(res, (Vec4i) this, bX, bY, bZ, bW);
    }

    public Vec4i mul(final Vec4i b, final Vec4i res) {
        return Glm.mul(res, (Vec4i) this, b.x, b.y, b.z, b.w);
    }

    public Vec4i div_(final int b) {
        return Glm.div(new Vec4i(), (Vec4i) this, b, b, b, b);
    }

    public Vec4i div_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.div(new Vec4i(), (Vec4i) this, bX, bY, bZ, bW);
    }

    public Vec4i div_(final Vec4i b) {
        return Glm.div(new Vec4i(), (Vec4i) this, b.x, b.y, b.z, b.w);
    }

    public Vec4i div(final int b) {
        return Glm.div((Vec4i) this, (Vec4i) this, b, b, b, b);
    }

    public Vec4i div(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.div((Vec4i) this, (Vec4i) this, bX, bY, bZ, bW);
    }

    public Vec4i div(final Vec4i b) {
        return Glm.div((Vec4i) this, (Vec4i) this, b.x, b.y, b.z, b.w);
    }

    public Vec4i div(final int b, final Vec4i res) {
        return Glm.div(res, (Vec4i) this, b, b, b, b);
    }

    public Vec4i div(final int bX, final int bY, final int bZ, final int bW, final Vec4i res) {
        return Glm.div(res, (Vec4i) this, bX, bY, bZ, bW);
    }

    public Vec4i div(final Vec4i b, final Vec4i res) {
        return Glm.div(res, (Vec4i) this, b.x, b.y, b.z, b.w);
    }

    public Vec4i incr_() {
        return Glm.incr_((Vec4i) this);
    }

    public Vec4i incr() {
        return Glm.incr((Vec4i) this);
    }

    public Vec4i incr(final Vec4i res) {
        return Glm.incr(res, (Vec4i) this);
    }

    public Vec4i decr_() {
        return Glm.decr_((Vec4i) this);
    }

    public Vec4i decr() {
        return Glm.decr((Vec4i) this);
    }

    public Vec4i decr(final Vec4i res) {
        return Glm.decr(res, (Vec4i) this);
    }
}
