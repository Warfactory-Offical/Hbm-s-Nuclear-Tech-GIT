/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2.i;

import glmath.glm.Glm;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 2 * Integer.BYTES;

    public int x, y;

    public Vec2i add_(final int b) {
        return Glm.add(new Vec2i(), (Vec2i) this, b, b);
    }

    public Vec2i add_(final int bX, final int bY) {
        return Glm.add(new Vec2i(), (Vec2i) this, bX, bY);
    }

    public Vec2i add_(final Vec2i b) {
        return Glm.add(new Vec2i(), (Vec2i) this, b.x, b.y);
    }

    public Vec2i add(final int b) {
        return Glm.add((Vec2i) this, (Vec2i) this, b, b);
    }

    public Vec2i add(final int bX, final int bY) {
        return Glm.add((Vec2i) this, (Vec2i) this, bX, bY);
    }

    public Vec2i add(final Vec2i b) {
        return Glm.add((Vec2i) this, (Vec2i) this, b.x, b.y);
    }

    public Vec2i add(final int b, final Vec2i res) {
        return Glm.add(res, (Vec2i) this, b, b);
    }

    public Vec2i add(final int bX, final int bY, final Vec2i res) {
        return Glm.add(res, (Vec2i) this, bX, bY);
    }

    public Vec2i add(final Vec2i b, final Vec2i res) {
        return Glm.add(res, (Vec2i) this, b.x, b.y);
    }

    public Vec2i sub_(final int b) {
        return Glm.sub(new Vec2i(), (Vec2i) this, b, b);
    }

    public Vec2i sub_(final int bX, final int bY) {
        return Glm.sub(new Vec2i(), (Vec2i) this, bX, bY);
    }

    public Vec2i sub_(final Vec2i b) {
        return Glm.sub(new Vec2i(), (Vec2i) this, b.x, b.y);
    }

    public Vec2i sub(final int b) {
        return Glm.sub((Vec2i) this, (Vec2i) this, b, b);
    }

    public Vec2i sub(final int bX, final int bY) {
        return Glm.sub((Vec2i) this, (Vec2i) this, bX, bY);
    }

    public Vec2i sub(final Vec2i b) {
        return Glm.sub((Vec2i) this, (Vec2i) this, b.x, b.y);
    }

    public Vec2i sub(final int b, final Vec2i res) {
        return Glm.sub(res, (Vec2i) this, b, b);
    }

    public Vec2i sub(final int bX, final int bY, final Vec2i res) {
        return Glm.sub(res, (Vec2i) this, bX, bY);
    }

    public Vec2i sub(final Vec2i b, final Vec2i res) {
        return Glm.sub(res, (Vec2i) this, b.x, b.y);
    }

    public Vec2i mul_(final int b) {
        return Glm.mul(new Vec2i(), (Vec2i) this, b, b);
    }

    public Vec2i mul_(final int bX, final int bY) {
        return Glm.mul(new Vec2i(), (Vec2i) this, bX, bY);
    }

    public Vec2i mul_(final Vec2i b) {
        return Glm.mul(new Vec2i(), (Vec2i) this, b.x, b.y);
    }

    public Vec2i mul(final int b) {
        return Glm.mul((Vec2i) this, (Vec2i) this, b, b);
    }

    public Vec2i mul(final int bX, final int bY) {
        return Glm.mul((Vec2i) this, (Vec2i) this, bX, bY);
    }

    public Vec2i mul(final Vec2i b) {
        return Glm.mul((Vec2i) this, (Vec2i) this, b.x, b.y);
    }

    public Vec2i mul(final int b, final Vec2i res) {
        return Glm.mul(res, (Vec2i) this, b, b);
    }

    public Vec2i mul(final int bX, final int bY, final Vec2i res) {
        return Glm.mul(res, (Vec2i) this, bX, bY);
    }

    public Vec2i mul(final Vec2i b, final Vec2i res) {
        return Glm.mul(res, (Vec2i) this, b.x, b.y);
    }

    public Vec2i div_(final int b) {
        return Glm.div(new Vec2i(), (Vec2i) this, b, b);
    }

    public Vec2i div_(final int bX, final int bY) {
        return Glm.div(new Vec2i(), (Vec2i) this, bX, bY);
    }

    public Vec2i div_(final Vec2i b) {
        return Glm.div(new Vec2i(), (Vec2i) this, b.x, b.y);
    }

    public Vec2i div(final int b) {
        return Glm.div((Vec2i) this, (Vec2i) this, b, b);
    }

    public Vec2i div(final int bX, final int bY) {
        return Glm.div((Vec2i) this, (Vec2i) this, bX, bY);
    }

    public Vec2i div(final Vec2i b) {
        return Glm.div((Vec2i) this, (Vec2i) this, b.x, b.y);
    }

    public Vec2i div(final int b, final Vec2i res) {
        return Glm.div(res, (Vec2i) this, b, b);
    }

    public Vec2i div(final int bX, final int bY, final Vec2i res) {
        return Glm.div(res, (Vec2i) this, bX, bY);
    }

    public Vec2i div(final Vec2i b, final Vec2i res) {
        return Glm.div(res, (Vec2i) this, b.x, b.y);
    }

    public Vec2i incr_() {
        return Glm.incr_((Vec2i) this);
    }

    public Vec2i incr() {
        return Glm.incr((Vec2i) this);
    }

    public Vec2i incr(final Vec2i res) {
        return Glm.incr(res, (Vec2i) this);
    }

    public Vec2i decr_() {
        return Glm.decr_((Vec2i) this);
    }

    public Vec2i decr() {
        return Glm.decr((Vec2i) this);
    }

    public Vec2i decr(final Vec2i res) {
        return Glm.decr(res, (Vec2i) this);
    }
}
