/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.l;

import glmath.glm.Glm;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 4 * Long.BYTES;

    public long x, y, z, w;

    public Vec4l add_(final long b) {
        return Glm.add(new Vec4l(), (Vec4l) this, b, b, b, b);
    }

    public Vec4l add_(final long bX, final long bY, final long bZ, final long bW) {
        return Glm.add(new Vec4l(), (Vec4l) this, bX, bY, bZ, bW);
    }

    public Vec4l add_(final Vec4l b) {
        return Glm.add(new Vec4l(), (Vec4l) this, b.x, b.y, b.z, b.w);
    }

    public Vec4l add(final long b) {
        return Glm.add((Vec4l) this, (Vec4l) this, b, b, b, b);
    }

    public Vec4l add(final long bX, final long bY, final long bZ, final long bW) {
        return Glm.add((Vec4l) this, (Vec4l) this, bX, bY, bZ, bW);
    }

    public Vec4l add(final Vec4l b) {
        return Glm.add((Vec4l) this, (Vec4l) this, b.x, b.y, b.z, b.w);
    }

    public Vec4l add(final long b, final Vec4l res) {
        return Glm.add(res, (Vec4l) this, b, b, b, b);
    }

    public Vec4l add(final long bX, final long bY, final long bZ, final long bW, final Vec4l res) {
        return Glm.add(res, (Vec4l) this, bX, bY, bZ, bW);
    }

    public Vec4l add(final Vec4l b, final Vec4l res) {
        return Glm.add(res, (Vec4l) this, b.x, b.y, b.z, b.w);
    }

    public Vec4l sub_(final long b) {
        return Glm.sub(new Vec4l(), (Vec4l) this, b, b, b, b);
    }

    public Vec4l sub_(final long bX, final long bY, final long bZ, final long bW) {
        return Glm.sub(new Vec4l(), (Vec4l) this, bX, bY, bZ, bW);
    }

    public Vec4l sub_(final Vec4l b) {
        return Glm.sub(new Vec4l(), (Vec4l) this, b.x, b.y, b.z, b.w);
    }

    public Vec4l sub(final long b) {
        return Glm.sub((Vec4l) this, (Vec4l) this, b, b, b, b);
    }

    public Vec4l sub(final long bX, final long bY, final long bZ, final long bW) {
        return Glm.sub((Vec4l) this, (Vec4l) this, bX, bY, bZ, bW);
    }

    public Vec4l sub(final Vec4l b) {
        return Glm.sub((Vec4l) this, (Vec4l) this, b.x, b.y, b.z, b.w);
    }

    public Vec4l sub(final long b, final Vec4l res) {
        return Glm.sub(res, (Vec4l) this, b, b, b, b);
    }

    public Vec4l sub(final long bX, final long bY, final long bZ, final long bW, final Vec4l res) {
        return Glm.sub(res, (Vec4l) this, bX, bY, bZ, bW);
    }

    public Vec4l sub(final Vec4l b, final Vec4l res) {
        return Glm.sub(res, (Vec4l) this, b.x, b.y, b.z, b.w);
    }

    public Vec4l mul_(final long b) {
        return Glm.mul(new Vec4l(), (Vec4l) this, b, b, b, b);
    }

    public Vec4l mul_(final long bX, final long bY, final long bZ, final long bW) {
        return Glm.mul(new Vec4l(), (Vec4l) this, bX, bY, bZ, bW);
    }

    public Vec4l mul_(final Vec4l b) {
        return Glm.mul(new Vec4l(), (Vec4l) this, b.x, b.y, b.z, b.w);
    }

    public Vec4l mul(final long b) {
        return Glm.mul((Vec4l) this, (Vec4l) this, b, b, b, b);
    }

    public Vec4l mul(final long bX, final long bY, final long bZ, final long bW) {
        return Glm.mul((Vec4l) this, (Vec4l) this, bX, bY, bZ, bW);
    }

    public Vec4l mul(final Vec4l b) {
        return Glm.mul((Vec4l) this, (Vec4l) this, b.x, b.y, b.z, b.w);
    }

    public Vec4l mul(final long b, final Vec4l res) {
        return Glm.mul(res, (Vec4l) this, b, b, b, b);
    }

    public Vec4l mul(final long bX, final long bY, final long bZ, final long bW, final Vec4l res) {
        return Glm.mul(res, (Vec4l) this, bX, bY, bZ, bW);
    }

    public Vec4l mul(final Vec4l b, final Vec4l res) {
        return Glm.mul(res, (Vec4l) this, b.x, b.y, b.z, b.w);
    }

    public Vec4l div_(final long b) {
        return Glm.div(new Vec4l(), (Vec4l) this, b, b, b, b);
    }

    public Vec4l div_(final long bX, final long bY, final long bZ, final long bW) {
        return Glm.div(new Vec4l(), (Vec4l) this, bX, bY, bZ, bW);
    }

    public Vec4l div_(final Vec4l b) {
        return Glm.div(new Vec4l(), (Vec4l) this, b.x, b.y, b.z, b.w);
    }

    public Vec4l div(final long b) {
        return Glm.div((Vec4l) this, (Vec4l) this, b, b, b, b);
    }

    public Vec4l div(final long bX, final long bY, final long bZ, final long bW) {
        return Glm.div((Vec4l) this, (Vec4l) this, bX, bY, bZ, bW);
    }

    public Vec4l div(final Vec4l b) {
        return Glm.div((Vec4l) this, (Vec4l) this, b.x, b.y, b.z, b.w);
    }

    public Vec4l div(final long b, final Vec4l res) {
        return Glm.div(res, (Vec4l) this, b, b, b, b);
    }

    public Vec4l div(final long bX, final long bY, final long bZ, final long bW, final Vec4l res) {
        return Glm.div(res, (Vec4l) this, bX, bY, bZ, bW);
    }

    public Vec4l div(final Vec4l b, final Vec4l res) {
        return Glm.div(res, (Vec4l) this, b.x, b.y, b.z, b.w);
    }

    public Vec4l incr_() {
        return Glm.incr_((Vec4l) this);
    }

    public Vec4l incr() {
        return Glm.incr((Vec4l) this);
    }

    public Vec4l incr(final Vec4l res) {
        return Glm.incr(res, (Vec4l) this);
    }

    public Vec4l decr_() {
        return Glm.decr_((Vec4l) this);
    }

    public Vec4l decr() {
        return Glm.decr((Vec4l) this);
    }

    public Vec4l decr(final Vec4l res) {
        return Glm.decr(res, (Vec4l) this);
    }
}
