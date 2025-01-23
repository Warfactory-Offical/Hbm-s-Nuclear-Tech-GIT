/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.l;

import glmath.glm.Glm;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 3 * Long.BYTES;

    public long x, y, z;

    public Vec3l add_(final long b) {
        return Glm.add(new Vec3l(), (Vec3l) this, b, b, b);
    }

    public Vec3l add_(final long bX, final long bY, final long bZ) {
        return Glm.add(new Vec3l(), (Vec3l) this, bX, bY, bZ);
    }

    public Vec3l add_(final Vec3l b) {
        return Glm.add(new Vec3l(), (Vec3l) this, b.x, b.y, b.z);
    }

    public Vec3l add(final long b) {
        return Glm.add((Vec3l) this, (Vec3l) this, b, b, b);
    }

    public Vec3l add(final long bX, final long bY, final long bZ) {
        return Glm.add((Vec3l) this, (Vec3l) this, bX, bY, bZ);
    }

    public Vec3l add(final Vec3l b) {
        return Glm.add((Vec3l) this, (Vec3l) this, b.x, b.y, b.z);
    }

    public Vec3l add(final long b, final Vec3l res) {
        return Glm.add(res, (Vec3l) this, b, b, b);
    }

    public Vec3l add(final long bX, final long bY, final long bZ, final Vec3l res) {
        return Glm.add(res, (Vec3l) this, bX, bY, bZ);
    }

    public Vec3l add(final Vec3l b, final Vec3l res) {
        return Glm.add(res, (Vec3l) this, b.x, b.y, b.z);
    }

    public Vec3l sub_(final long b) {
        return Glm.sub(new Vec3l(), (Vec3l) this, b, b, b);
    }

    public Vec3l sub_(final long bX, final long bY, final long bZ) {
        return Glm.sub(new Vec3l(), (Vec3l) this, bX, bY, bZ);
    }

    public Vec3l sub_(final Vec3l b) {
        return Glm.sub(new Vec3l(), (Vec3l) this, b.x, b.y, b.z);
    }

    public Vec3l sub(final long b) {
        return Glm.sub((Vec3l) this, (Vec3l) this, b, b, b);
    }

    public Vec3l sub(final long bX, final long bY, final long bZ) {
        return Glm.sub((Vec3l) this, (Vec3l) this, bX, bY, bZ);
    }

    public Vec3l sub(final Vec3l b) {
        return Glm.sub((Vec3l) this, (Vec3l) this, b.x, b.y, b.z);
    }

    public Vec3l sub(final long b, final Vec3l res) {
        return Glm.sub(res, (Vec3l) this, b, b, b);
    }

    public Vec3l sub(final long bX, final long bY, final long bZ, final Vec3l res) {
        return Glm.sub(res, (Vec3l) this, bX, bY, bZ);
    }

    public Vec3l sub(final Vec3l b, final Vec3l res) {
        return Glm.sub(res, (Vec3l) this, b.x, b.y, b.z);
    }

    public Vec3l mul_(final long b) {
        return Glm.mul(new Vec3l(), (Vec3l) this, b, b, b);
    }

    public Vec3l mul_(final long bX, final long bY, final long bZ) {
        return Glm.mul(new Vec3l(), (Vec3l) this, bX, bY, bZ);
    }

    public Vec3l mul_(final Vec3l b) {
        return Glm.mul(new Vec3l(), (Vec3l) this, b.x, b.y, b.z);
    }

    public Vec3l mul(final long b) {
        return Glm.mul((Vec3l) this, (Vec3l) this, b, b, b);
    }

    public Vec3l mul(final long bX, final long bY, final long bZ) {
        return Glm.mul((Vec3l) this, (Vec3l) this, bX, bY, bZ);
    }

    public Vec3l mul(final Vec3l b) {
        return Glm.mul((Vec3l) this, (Vec3l) this, b.x, b.y, b.z);
    }

    public Vec3l mul(final long b, final Vec3l res) {
        return Glm.mul(res, (Vec3l) this, b, b, b);
    }

    public Vec3l mul(final long bX, final long bY, final long bZ, final Vec3l res) {
        return Glm.mul(res, (Vec3l) this, bX, bY, bZ);
    }

    public Vec3l mul(final Vec3l b, final Vec3l res) {
        return Glm.mul(res, (Vec3l) this, b.x, b.y, b.z);
    }

    public Vec3l div_(final long b) {
        return Glm.div(new Vec3l(), (Vec3l) this, b, b, b);
    }

    public Vec3l div_(final long bX, final long bY, final long bZ) {
        return Glm.div(new Vec3l(), (Vec3l) this, bX, bY, bZ);
    }

    public Vec3l div_(final Vec3l b) {
        return Glm.div(new Vec3l(), (Vec3l) this, b.x, b.y, b.z);
    }

    public Vec3l div(final long b) {
        return Glm.div((Vec3l) this, (Vec3l) this, b, b, b);
    }

    public Vec3l div(final long bX, final long bY, final long bZ) {
        return Glm.div((Vec3l) this, (Vec3l) this, bX, bY, bZ);
    }

    public Vec3l div(final Vec3l b) {
        return Glm.div((Vec3l) this, (Vec3l) this, b.x, b.y, b.z);
    }

    public Vec3l div(final long b, final Vec3l res) {
        return Glm.div(res, (Vec3l) this, b, b, b);
    }

    public Vec3l div(final long bX, final long bY, final long bZ, final Vec3l res) {
        return Glm.div(res, (Vec3l) this, bX, bY, bZ);
    }

    public Vec3l div(final Vec3l b, final Vec3l res) {
        return Glm.div(res, (Vec3l) this, b.x, b.y, b.z);
    }

    public Vec3l incr_() {
        return Glm.incr_((Vec3l) this);
    }

    public Vec3l incr() {
        return Glm.incr((Vec3l) this);
    }

    public Vec3l incr(final Vec3l res) {
        return Glm.incr(res, (Vec3l) this);
    }

    public Vec3l decr_() {
        return Glm.decr_((Vec3l) this);
    }

    public Vec3l decr() {
        return Glm.decr((Vec3l) this);
    }

    public Vec3l decr(final Vec3l res) {
        return Glm.decr(res, (Vec3l) this);
    }
}
