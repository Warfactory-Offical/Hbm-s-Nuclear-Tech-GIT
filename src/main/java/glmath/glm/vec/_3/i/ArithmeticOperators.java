/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.i;

import glmath.glm.Glm;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 3 * Integer.BYTES;

    public int x, y, z;

    public Vec3i add_(final int b) {
        return Glm.add(new Vec3i(), (Vec3i) this, b, b, b);
    }

    public Vec3i add_(final int bX, final int bY, final int bZ) {
        return Glm.add(new Vec3i(), (Vec3i) this, bX, bY, bZ);
    }

    public Vec3i add_(final Vec3i b) {
        return Glm.add(new Vec3i(), (Vec3i) this, b.x, b.y, b.z);
    }

    public Vec3i add(final int b) {
        return Glm.add((Vec3i) this, (Vec3i) this, b, b, b);
    }

    public Vec3i add(final int bX, final int bY, final int bZ) {
        return Glm.add((Vec3i) this, (Vec3i) this, bX, bY, bZ);
    }

    public Vec3i add(final Vec3i b) {
        return Glm.add((Vec3i) this, (Vec3i) this, b.x, b.y, b.z);
    }

    public Vec3i add(final int b, final Vec3i res) {
        return Glm.add(res, (Vec3i) this, b, b, b);
    }

    public Vec3i add(final int bX, final int bY, final int bZ, final Vec3i res) {
        return Glm.add(res, (Vec3i) this, bX, bY, bZ);
    }

    public Vec3i add(final Vec3i b, final Vec3i res) {
        return Glm.add(res, (Vec3i) this, b.x, b.y, b.z);
    }

    public Vec3i sub_(final int b) {
        return Glm.sub(new Vec3i(), (Vec3i) this, b, b, b);
    }

    public Vec3i sub_(final int bX, final int bY, final int bZ) {
        return Glm.sub(new Vec3i(), (Vec3i) this, bX, bY, bZ);
    }

    public Vec3i sub_(final Vec3i b) {
        return Glm.sub(new Vec3i(), (Vec3i) this, b.x, b.y, b.z);
    }

    public Vec3i sub(final int b) {
        return Glm.sub((Vec3i) this, (Vec3i) this, b, b, b);
    }

    public Vec3i sub(final int bX, final int bY, final int bZ) {
        return Glm.sub((Vec3i) this, (Vec3i) this, bX, bY, bZ);
    }

    public Vec3i sub(final Vec3i b) {
        return Glm.sub((Vec3i) this, (Vec3i) this, b.x, b.y, b.z);
    }

    public Vec3i sub(final int b, final Vec3i res) {
        return Glm.sub(res, (Vec3i) this, b, b, b);
    }

    public Vec3i sub(final int bX, final int bY, final int bZ, final Vec3i res) {
        return Glm.sub(res, (Vec3i) this, bX, bY, bZ);
    }

    public Vec3i sub(final Vec3i b, final Vec3i res) {
        return Glm.sub(res, (Vec3i) this, b.x, b.y, b.z);
    }

    public Vec3i mul_(final int b) {
        return Glm.mul(new Vec3i(), (Vec3i) this, b, b, b);
    }

    public Vec3i mul_(final int bX, final int bY, final int bZ) {
        return Glm.mul(new Vec3i(), (Vec3i) this, bX, bY, bZ);
    }

    public Vec3i mul_(final Vec3i b) {
        return Glm.mul(new Vec3i(), (Vec3i) this, b.x, b.y, b.z);
    }

    public Vec3i mul(final int b) {
        return Glm.mul((Vec3i) this, (Vec3i) this, b, b, b);
    }

    public Vec3i mul(final int bX, final int bY, final int bZ) {
        return Glm.mul((Vec3i) this, (Vec3i) this, bX, bY, bZ);
    }

    public Vec3i mul(final Vec3i b) {
        return Glm.mul((Vec3i) this, (Vec3i) this, b.x, b.y, b.z);
    }

    public Vec3i mul(final int b, final Vec3i res) {
        return Glm.mul(res, (Vec3i) this, b, b, b);
    }

    public Vec3i mul(final int bX, final int bY, final int bZ, final Vec3i res) {
        return Glm.mul(res, (Vec3i) this, bX, bY, bZ);
    }

    public Vec3i mul(final Vec3i b, final Vec3i res) {
        return Glm.mul(res, (Vec3i) this, b.x, b.y, b.z);
    }

    public Vec3i div_(final int b) {
        return Glm.div(new Vec3i(), (Vec3i) this, b, b, b);
    }

    public Vec3i div_(final int bX, final int bY, final int bZ) {
        return Glm.div(new Vec3i(), (Vec3i) this, bX, bY, bZ);
    }

    public Vec3i div_(final Vec3i b) {
        return Glm.div(new Vec3i(), (Vec3i) this, b.x, b.y, b.z);
    }

    public Vec3i div(final int b) {
        return Glm.div((Vec3i) this, (Vec3i) this, b, b, b);
    }

    public Vec3i div(final int bX, final int bY, final int bZ) {
        return Glm.div((Vec3i) this, (Vec3i) this, bX, bY, bZ);
    }

    public Vec3i div(final Vec3i b) {
        return Glm.div((Vec3i) this, (Vec3i) this, b.x, b.y, b.z);
    }

    public Vec3i div(final int b, final Vec3i res) {
        return Glm.div(res, (Vec3i) this, b, b, b);
    }

    public Vec3i div(final int bX, final int bY, final int bZ, final Vec3i res) {
        return Glm.div(res, (Vec3i) this, bX, bY, bZ);
    }

    public Vec3i div(final Vec3i b, final Vec3i res) {
        return Glm.div(res, (Vec3i) this, b.x, b.y, b.z);
    }

    public Vec3i incr_() {
        return Glm.incr_((Vec3i) this);
    }

    public Vec3i incr() {
        return Glm.incr((Vec3i) this);
    }

    public Vec3i incr(final Vec3i res) {
        return Glm.incr(res, (Vec3i) this);
    }

    public Vec3i decr_() {
        return Glm.decr_((Vec3i) this);
    }

    public Vec3i decr() {
        return Glm.decr((Vec3i) this);
    }

    public Vec3i decr(final Vec3i res) {
        return Glm.decr(res, (Vec3i) this);
    }
}
