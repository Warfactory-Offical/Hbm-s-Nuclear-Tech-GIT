/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.d;

import glmath.glm.Glm;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public final static int SIZE = 3 * Double.BYTES;

    public double x, y, z;

    public Vec3d add_(final double b) {
        return Glm.add(new Vec3d(), (Vec3d) this, b, b, b);
    }

    public Vec3d add_(final double bX, final double bY, final double bZ) {
        return Glm.add(new Vec3d(), (Vec3d) this, bX, bY, bZ);
    }

    public Vec3d add_(final Vec3d b) {
        return Glm.add(new Vec3d(), (Vec3d) this, b.x, b.y, b.z);
    }

    public Vec3d add(final double b) {
        return Glm.add((Vec3d) this, (Vec3d) this, b, b, b);
    }

    public Vec3d add(final double bX, final double bY, final double bZ) {
        return Glm.add((Vec3d) this, (Vec3d) this, bX, bY, bZ);
    }

    public Vec3d add(final Vec3d b) {
        return Glm.add((Vec3d) this, (Vec3d) this, b.x, b.y, b.z);
    }

    public Vec3d add(final double b, final Vec3d res) {
        return Glm.add(res, (Vec3d) this, b, b, b);
    }

    public Vec3d add(final double bX, final double bY, final double bZ, final Vec3d res) {
        return Glm.add(res, (Vec3d) this, bX, bY, bZ);
    }

    public Vec3d add(final Vec3d b, final Vec3d res) {
        return Glm.add(res, (Vec3d) this, b.x, b.y, b.z);
    }

    public Vec3d sub_(final double b) {
        return Glm.sub(new Vec3d(), (Vec3d) this, b, b, b);
    }

    public Vec3d sub_(final double bX, final double bY, final double bZ) {
        return Glm.sub(new Vec3d(), (Vec3d) this, bX, bY, bZ);
    }

    public Vec3d sub_(final Vec3d b) {
        return Glm.sub(new Vec3d(), (Vec3d) this, b.x, b.y, b.z);
    }

    public Vec3d sub(final double b) {
        return Glm.sub((Vec3d) this, (Vec3d) this, b, b, b);
    }

    public Vec3d sub(final double bX, final double bY, final double bZ) {
        return Glm.sub((Vec3d) this, (Vec3d) this, bX, bY, bZ);
    }

    public Vec3d sub(final Vec3d b) {
        return Glm.sub((Vec3d) this, (Vec3d) this, b.x, b.y, b.z);
    }

    public Vec3d sub(final double b, final Vec3d res) {
        return Glm.sub(res, (Vec3d) this, b, b, b);
    }

    public Vec3d sub(final double bX, final double bY, final double bZ, final Vec3d res) {
        return Glm.sub(res, (Vec3d) this, bX, bY, bZ);
    }

    public Vec3d sub(final Vec3d b, final Vec3d res) {
        return Glm.sub(res, (Vec3d) this, b.x, b.y, b.z);
    }

    public Vec3d mul_(final double b) {
        return Glm.mul(new Vec3d(), (Vec3d) this, b, b, b);
    }

    public Vec3d mul_(final double bX, final double bY, final double bZ) {
        return Glm.mul(new Vec3d(), (Vec3d) this, bX, bY, bZ);
    }

    public Vec3d mul_(final Vec3d b) {
        return Glm.mul(new Vec3d(), (Vec3d) this, b.x, b.y, b.z);
    }

    public Vec3d mul(final double b) {
        return Glm.mul((Vec3d) this, (Vec3d) this, b, b, b);
    }

    public Vec3d mul(final double bX, final double bY, final double bZ) {
        return Glm.mul((Vec3d) this, (Vec3d) this, bX, bY, bZ);
    }

    public Vec3d mul(final Vec3d b) {
        return Glm.mul((Vec3d) this, (Vec3d) this, b.x, b.y, b.z);
    }

    public Vec3d mul(final double b, final Vec3d res) {
        return Glm.mul(res, (Vec3d) this, b, b, b);
    }

    public Vec3d mul(final double bX, final double bY, final double bZ, final Vec3d res) {
        return Glm.mul(res, (Vec3d) this, bX, bY, bZ);
    }

    public Vec3d mul(final Vec3d b, final Vec3d res) {
        return Glm.mul(res, (Vec3d) this, b.x, b.y, b.z);
    }

    public Vec3d div_(final double b) {
        return Glm.div(new Vec3d(), (Vec3d) this, b, b, b);
    }

    public Vec3d div_(final double bX, final double bY, final double bZ) {
        return Glm.div(new Vec3d(), (Vec3d) this, bX, bY, bZ);
    }

    public Vec3d div_(final Vec3d b) {
        return Glm.div(new Vec3d(), (Vec3d) this, b.x, b.y, b.z);
    }

    public Vec3d div(final double b) {
        return Glm.div((Vec3d) this, (Vec3d) this, b, b, b);
    }

    public Vec3d div(final double bX, final double bY, final double bZ) {
        return Glm.div((Vec3d) this, (Vec3d) this, bX, bY, bZ);
    }

    public Vec3d div(final Vec3d b) {
        return Glm.div((Vec3d) this, (Vec3d) this, b.x, b.y, b.z);
    }

    public Vec3d div(final double b, final Vec3d res) {
        return Glm.div(res, (Vec3d) this, b, b, b);
    }

    public Vec3d div(final double bX, final double bY, final double bZ, final Vec3d res) {
        return Glm.div(res, (Vec3d) this, bX, bY, bZ);
    }

    public Vec3d div(final Vec3d b, final Vec3d res) {
        return Glm.div(res, (Vec3d) this, b.x, b.y, b.z);
    }

    public Vec3d incr_() {
        return Glm.incr_((Vec3d) this);
    }

    public Vec3d incr() {
        return Glm.incr((Vec3d) this);
    }

    public Vec3d incr(final Vec3d res) {
        return Glm.incr(res, (Vec3d) this);
    }

    public Vec3d decr_() {
        return Glm.decr_((Vec3d) this);
    }

    public Vec3d decr() {
        return Glm.decr((Vec3d) this);
    }

    public Vec3d decr(final Vec3d res) {
        return Glm.decr(res, (Vec3d) this);
    }
}
