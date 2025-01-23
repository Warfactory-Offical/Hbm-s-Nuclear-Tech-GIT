/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2;

import glmath.glm.Glm;

/**
 *
 * @author GBarbieri
 */
abstract class BasicOperators {

    public static final int SIZE = 2 * Float.BYTES;

    public float x, y;

    public Vec2 add_(final float b) {
        return Glm.add(new Vec2(), (Vec2) this, b, b);
    }

    public Vec2 add_(final float bX, final float bY) {
        return Glm.add(new Vec2(), (Vec2) this, bX, bY);
    }

    public Vec2 add_(final Vec2 b) {
        return Glm.add(new Vec2(), (Vec2) this, b.x, b.y);
    }

    public Vec2 add(final float b) {
        return Glm.add((Vec2) this, (Vec2) this, b, b);
    }

    public Vec2 add(final float bX, final float bY) {
        return Glm.add((Vec2) this, (Vec2) this, bX, bY);
    }

    public Vec2 add(final Vec2 b) {
        return Glm.add((Vec2) this, (Vec2) this, b.x, b.y);
    }

    public Vec2 add(final float b, final Vec2 res) {
        return Glm.add(res, (Vec2) this, b, b);
    }

    public Vec2 add(final float bX, final float bY, final Vec2 res) {
        return Glm.add(res, (Vec2) this, bX, bY);
    }

    public Vec2 add(final Vec2 b, final Vec2 res) {
        return Glm.add(res, (Vec2) this, b.x, b.y);
    }

    public Vec2 sub_(final float b) {
        return Glm.sub(new Vec2(), (Vec2) this, b, b);
    }

    public Vec2 sub_(final float bX, final float bY) {
        return Glm.sub(new Vec2(), (Vec2) this, bX, bY);
    }

    public Vec2 sub_(final Vec2 b) {
        return Glm.sub(new Vec2(), (Vec2) this, b.x, b.y);
    }

    public Vec2 sub(final float b) {
        return Glm.sub((Vec2) this, (Vec2) this, b, b);
    }

    public Vec2 sub(final float bX, final float bY) {
        return Glm.sub((Vec2) this, (Vec2) this, bX, bY);
    }

    public Vec2 sub(final Vec2 b) {
        return Glm.sub((Vec2) this, (Vec2) this, b.x, b.y);
    }

    public Vec2 sub(final float b, final Vec2 res) {
        return Glm.sub(res, (Vec2) this, b, b);
    }

    public Vec2 sub(final float bX, final float bY, final Vec2 res) {
        return Glm.sub(res, (Vec2) this, bX, bY);
    }

    public Vec2 sub(final Vec2 b, final Vec2 res) {
        return Glm.sub(res, (Vec2) this, b.x, b.y);
    }

    public Vec2 mul_(final float b) {
        return Glm.mul(new Vec2(), (Vec2) this, b, b);
    }

    public Vec2 mul_(final float bX, final float bY) {
        return Glm.mul(new Vec2(), (Vec2) this, bX, bY);
    }

    public Vec2 mul_(final Vec2 b) {
        return Glm.mul(new Vec2(), (Vec2) this, b.x, b.y);
    }

    public Vec2 mul(final float b) {
        return Glm.mul((Vec2) this, (Vec2) this, b, b);
    }

    public Vec2 mul(final float bX, final float bY) {
        return Glm.mul((Vec2) this, (Vec2) this, bX, bY);
    }

    public Vec2 mul(final Vec2 b) {
        return Glm.mul((Vec2) this, (Vec2) this, b.x, b.y);
    }

    public Vec2 mul(final float b, final Vec2 res) {
        return Glm.mul(res, (Vec2) this, b, b);
    }

    public Vec2 mul(final float bX, final float bY, final Vec2 res) {
        return Glm.mul(res, (Vec2) this, bX, bY);
    }

    public Vec2 mul(final Vec2 b, final Vec2 res) {
        return Glm.mul(res, (Vec2) this, b.x, b.y);
    }

    public Vec2 div_(final float b) {
        return Glm.div(new Vec2(), (Vec2) this, b, b);
    }

    public Vec2 div_(final float bX, final float bY) {
        return Glm.div(new Vec2(), (Vec2) this, bX, bY);
    }

    public Vec2 div_(final Vec2 b) {
        return Glm.div(new Vec2(), (Vec2) this, b.x, b.y);
    }

    public Vec2 div(final float b) {
        return Glm.div((Vec2) this, (Vec2) this, b, b);
    }

    public Vec2 div(final float bX, final float bY) {
        return Glm.div((Vec2) this, (Vec2) this, bX, bY);
    }

    public Vec2 div(final Vec2 b) {
        return Glm.div((Vec2) this, (Vec2) this, b.x, b.y);
    }

    public Vec2 div(final float b, final Vec2 res) {
        return Glm.div(res, (Vec2) this, b, b);
    }

    public Vec2 div(final float bX, final float bY, final Vec2 res) {
        return Glm.div(res, (Vec2) this, bX, bY);
    }

    public Vec2 div(final Vec2 b, final Vec2 res) {
        return Glm.div(res, (Vec2) this, b.x, b.y);
    }

    public Vec2 incr_() {
        return Glm.incr_((Vec2) this);
    }

    public Vec2 incr() {
        return Glm.incr((Vec2) this);
    }

    public Vec2 incr(final Vec2 res) {
        return Glm.incr(res, (Vec2) this);
    }

    public Vec2 decr_() {
        return Glm.decr_((Vec2) this);
    }

    public Vec2 decr() {
        return Glm.decr((Vec2) this);
    }

    public Vec2 decr(final Vec2 res) {
        return Glm.decr(res, (Vec2) this);
    }
}
