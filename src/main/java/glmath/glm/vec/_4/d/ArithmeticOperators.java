/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.d;

import glmath.glm.Glm;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 4 * Double.BYTES;

    public double x, y, z, w;

    public Vec4d add_(final double b) {
        return Glm.add(new Vec4d(), (Vec4d) this, b, b, b, b);
    }

    public Vec4d add_(final double bX, final double bY, final double bZ, final double bW) {
        return Glm.add(new Vec4d(), (Vec4d) this, bX, bY, bZ, bW);
    }

    public Vec4d add_(final Vec4d b) {
        return Glm.add(new Vec4d(), (Vec4d) this, b.x, b.y, b.z, b.w);
    }

    public Vec4d add(final double b) {
        return Glm.add((Vec4d) this, (Vec4d) this, b, b, b, b);
    }

    public Vec4d add(final double bX, final double bY, final double bZ, final double bW) {
        return Glm.add((Vec4d) this, (Vec4d) this, bX, bY, bZ, bW);
    }

    public Vec4d add(final Vec4d b) {
        return Glm.add((Vec4d) this, (Vec4d) this, b.x, b.y, b.z, b.w);
    }

    public Vec4d add(final double b, final Vec4d res) {
        return Glm.add(res, (Vec4d) this, b, b, b, b);
    }

    public Vec4d add(final double bX, final double bY, final double bZ, final double bW, final Vec4d res) {
        return Glm.add(res, (Vec4d) this, bX, bY, bZ, bW);
    }

    public Vec4d add(final Vec4d b, final Vec4d res) {
        return Glm.add(res, (Vec4d) this, b.x, b.y, b.z, b.w);
    }

    public Vec4d sub_(final double b) {
        return Glm.sub(new Vec4d(), (Vec4d) this, b, b, b, b);
    }

    public Vec4d sub_(final double bX, final double bY, final double bZ, final double bW) {
        return Glm.sub(new Vec4d(), (Vec4d) this, bX, bY, bZ, bW);
    }

    public Vec4d sub_(final Vec4d b) {
        return Glm.sub(new Vec4d(), (Vec4d) this, b.x, b.y, b.z, b.w);
    }

    public Vec4d sub(final double b) {
        return Glm.sub((Vec4d) this, (Vec4d) this, b, b, b, b);
    }

    public Vec4d sub(final double bX, final double bY, final double bZ, final double bW) {
        return Glm.sub((Vec4d) this, (Vec4d) this, bX, bY, bZ, bW);
    }

    public Vec4d sub(final Vec4d b) {
        return Glm.sub((Vec4d) this, (Vec4d) this, b.x, b.y, b.z, b.w);
    }

    public Vec4d sub(final double b, final Vec4d res) {
        return Glm.sub(res, (Vec4d) this, b, b, b, b);
    }

    public Vec4d sub(final double bX, final double bY, final double bZ, final double bW, final Vec4d res) {
        return Glm.sub(res, (Vec4d) this, bX, bY, bZ, bW);
    }

    public Vec4d sub(final Vec4d b, final Vec4d res) {
        return Glm.sub(res, (Vec4d) this, b.x, b.y, b.z, b.w);
    }

    public Vec4d mul_(final double b) {
        return Glm.mul(new Vec4d(), (Vec4d) this, b, b, b, b);
    }

    public Vec4d mul_(final double bX, final double bY, final double bZ, final double bW) {
        return Glm.mul(new Vec4d(), (Vec4d) this, bX, bY, bZ, bW);
    }

    public Vec4d mul_(final Vec4d b) {
        return Glm.mul(new Vec4d(), (Vec4d) this, b.x, b.y, b.z, b.w);
    }

    public Vec4d mul(final double b) {
        return Glm.mul((Vec4d) this, (Vec4d) this, b, b, b, b);
    }

    public Vec4d mul(final double bX, final double bY, final double bZ, final double bW) {
        return Glm.mul((Vec4d) this, (Vec4d) this, bX, bY, bZ, bW);
    }

    public Vec4d mul(final Vec4d b) {
        return Glm.mul((Vec4d) this, (Vec4d) this, b.x, b.y, b.z, b.w);
    }

    public Vec4d mul(final double b, final Vec4d res) {
        return Glm.mul(res, (Vec4d) this, b, b, b, b);
    }

    public Vec4d mul(final double bX, final double bY, final double bZ, final double bW, final Vec4d res) {
        return Glm.mul(res, (Vec4d) this, bX, bY, bZ, bW);
    }

    public Vec4d mul(final Vec4d b, final Vec4d res) {
        return Glm.mul(res, (Vec4d) this, b.x, b.y, b.z, b.w);
    }

    public Vec4d div_(final double b) {
        return Glm.div(new Vec4d(), (Vec4d) this, b, b, b, b);
    }

    public Vec4d div_(final double bX, final double bY, final double bZ, final double bW) {
        return Glm.div(new Vec4d(), (Vec4d) this, bX, bY, bZ, bW);
    }

    public Vec4d div_(final Vec4d b) {
        return Glm.div(new Vec4d(), (Vec4d) this, b.x, b.y, b.z, b.w);
    }

    public Vec4d div(final double b) {
        return Glm.div((Vec4d) this, (Vec4d) this, b, b, b, b);
    }

    public Vec4d div(final double bX, final double bY, final double bZ, final double bW) {
        return Glm.div((Vec4d) this, (Vec4d) this, bX, bY, bZ, bW);
    }

    public Vec4d div(final Vec4d b) {
        return Glm.div((Vec4d) this, (Vec4d) this, b.x, b.y, b.z, b.w);
    }

    public Vec4d div(final double b, final Vec4d res) {
        return Glm.div(res, (Vec4d) this, b, b, b, b);
    }

    public Vec4d div(final double bX, final double bY, final double bZ, final double bW, final Vec4d res) {
        return Glm.div(res, (Vec4d) this, bX, bY, bZ, bW);
    }

    public Vec4d div(final Vec4d b, final Vec4d res) {
        return Glm.div(res, (Vec4d) this, b.x, b.y, b.z, b.w);
    }

    public Vec4d incr_() {
        return Glm.incr_((Vec4d) this);
    }

    public Vec4d incr() {
        return Glm.incr((Vec4d) this);
    }

    public Vec4d incr(final Vec4d res) {
        return Glm.incr(res, (Vec4d) this);
    }

    public Vec4d decr_() {
        return Glm.decr_((Vec4d) this);
    }

    public Vec4d decr() {
        return Glm.decr((Vec4d) this);
    }

    public Vec4d decr(final Vec4d res) {
        return Glm.decr(res, (Vec4d) this);
    }
}
