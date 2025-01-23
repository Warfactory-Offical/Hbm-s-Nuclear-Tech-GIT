/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2.l;

import glmath.glm.Glm;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 2 * Long.BYTES;

    public long x, y;

    public Vec2l add_(final long b) {
        return Glm.add(new Vec2l(), (Vec2l) this, b, b);
    }

    public Vec2l add_(final long bX, final long bY) {
        return Glm.add(new Vec2l(), (Vec2l) this, bX, bY);
    }

    public Vec2l add_(final Vec2l b) {
        return Glm.add(new Vec2l(), (Vec2l) this, b.x, b.y);
    }

    public Vec2l add(final long b) {
        return Glm.add((Vec2l) this, (Vec2l) this, b, b);
    }

    public Vec2l add(final long bX, final long bY) {
        return Glm.add((Vec2l) this, (Vec2l) this, bX, bY);
    }

    public Vec2l add(final Vec2l b) {
        return Glm.add((Vec2l) this, (Vec2l) this, b.x, b.y);
    }

    public Vec2l add(final long b, final Vec2l res) {
        return Glm.add(res, (Vec2l) this, b, b);
    }

    public Vec2l add(final long bX, final long bY, final Vec2l res) {
        return Glm.add(res, (Vec2l) this, bX, bY);
    }

    public Vec2l add(final Vec2l b, final Vec2l res) {
        return Glm.add(res, (Vec2l) this, b.x, b.y);
    }

    public Vec2l sub_(final long b) {
        return Glm.sub(new Vec2l(), (Vec2l) this, b, b);
    }

    public Vec2l sub_(final long bX, final long bY) {
        return Glm.sub(new Vec2l(), (Vec2l) this, bX, bY);
    }

    public Vec2l sub_(final Vec2l b) {
        return Glm.sub(new Vec2l(), (Vec2l) this, b.x, b.y);
    }

    public Vec2l sub(final long b) {
        return Glm.sub((Vec2l) this, (Vec2l) this, b, b);
    }

    public Vec2l sub(final long bX, final long bY) {
        return Glm.sub((Vec2l) this, (Vec2l) this, bX, bY);
    }

    public Vec2l sub(final Vec2l b) {
        return Glm.sub((Vec2l) this, (Vec2l) this, b.x, b.y);
    }

    public Vec2l sub(final long b, final Vec2l res) {
        return Glm.sub(res, (Vec2l) this, b, b);
    }

    public Vec2l sub(final long bX, final long bY, final Vec2l res) {
        return Glm.sub(res, (Vec2l) this, bX, bY);
    }

    public Vec2l sub(final Vec2l b, final Vec2l res) {
        return Glm.sub(res, (Vec2l) this, b.x, b.y);
    }

    public Vec2l mul_(final long b) {
        return Glm.mul(new Vec2l(), (Vec2l) this, b, b);
    }

    public Vec2l mul_(final long bX, final long bY) {
        return Glm.mul(new Vec2l(), (Vec2l) this, bX, bY);
    }

    public Vec2l mul_(final Vec2l b) {
        return Glm.mul(new Vec2l(), (Vec2l) this, b.x, b.y);
    }

    public Vec2l mul(final long b) {
        return Glm.mul((Vec2l) this, (Vec2l) this, b, b);
    }

    public Vec2l mul(final long bX, final long bY) {
        return Glm.mul((Vec2l) this, (Vec2l) this, bX, bY);
    }

    public Vec2l mul(final Vec2l b) {
        return Glm.mul((Vec2l) this, (Vec2l) this, b.x, b.y);
    }

    public Vec2l mul(final long b, final Vec2l res) {
        return Glm.mul(res, (Vec2l) this, b, b);
    }

    public Vec2l mul(final long bX, final long bY, final Vec2l res) {
        return Glm.mul(res, (Vec2l) this, bX, bY);
    }

    public Vec2l mul(final Vec2l b, final Vec2l res) {
        return Glm.mul(res, (Vec2l) this, b.x, b.y);
    }

    public Vec2l div_(final long b) {
        return Glm.div(new Vec2l(), (Vec2l) this, b, b);
    }

    public Vec2l div_(final long bX, final long bY) {
        return Glm.div(new Vec2l(), (Vec2l) this, bX, bY);
    }

    public Vec2l div_(final Vec2l b) {
        return Glm.div(new Vec2l(), (Vec2l) this, b.x, b.y);
    }

    public Vec2l div(final long b) {
        return Glm.div((Vec2l) this, (Vec2l) this, b, b);
    }

    public Vec2l div(final long bX, final long bY) {
        return Glm.div((Vec2l) this, (Vec2l) this, bX, bY);
    }

    public Vec2l div(final Vec2l b) {
        return Glm.div((Vec2l) this, (Vec2l) this, b.x, b.y);
    }

    public Vec2l div(final long b, final Vec2l res) {
        return Glm.div(res, (Vec2l) this, b, b);
    }

    public Vec2l div(final long bX, final long bY, final Vec2l res) {
        return Glm.div(res, (Vec2l) this, bX, bY);
    }

    public Vec2l div(final Vec2l b, final Vec2l res) {
        return Glm.div(res, (Vec2l) this, b.x, b.y);
    }

    public Vec2l incr_() {
        return Glm.incr_((Vec2l) this);
    }

    public Vec2l incr() {
        return Glm.incr((Vec2l) this);
    }

    public Vec2l incr(final Vec2l res) {
        return Glm.incr(res, (Vec2l) this);
    }

    public Vec2l decr_() {
        return Glm.decr_((Vec2l) this);
    }

    public Vec2l decr() {
        return Glm.decr((Vec2l) this);
    }

    public Vec2l decr(final Vec2l res) {
        return Glm.decr(res, (Vec2l) this);
    }
}
