/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2.b;

import glmath.glm.Glm;

/**
 *
 * @author GBarbieri
 */
abstract class BasicOperators {

    public static final int SIZE = 2 * Byte.BYTES;

    public byte x, y;

    public Vec2b add_(final byte b) {
        return Glm.add(new Vec2b(), (Vec2b) this, (int) b, b);
    }

    public Vec2b add_(final int b) {
        return Glm.add(new Vec2b(), (Vec2b) this, b, b);
    }

    public Vec2b add_(final byte bX, final byte bY) {
        return Glm.add(new Vec2b(), (Vec2b) this, (int) bX, bY);
    }

    public Vec2b add_(final int bX, final int bY) {
        return Glm.add(new Vec2b(), (Vec2b) this, bX, bY);
    }

    public Vec2b add_(final Vec2b b) {
        return Glm.add(new Vec2b(), (Vec2b) this, (int) b.x, b.y);
    }

    public Vec2b add(final byte b) {
        return Glm.add((Vec2b) this, (Vec2b) this, (int) b, b);
    }

    public Vec2b add(final int b) {
        return Glm.add((Vec2b) this, (Vec2b) this, b, b);
    }

    public Vec2b add(final byte bX, final byte bY) {
        return Glm.add((Vec2b) this, (Vec2b) this, (int) bX, bY);
    }

    public Vec2b add(final int bX, final int bY) {
        return Glm.add((Vec2b) this, (Vec2b) this, bX, bY);
    }

    public Vec2b add(final Vec2b b) {
        return Glm.add((Vec2b) this, (Vec2b) this, (int) b.x, b.y);
    }

    public Vec2b add(final byte b, final Vec2b res) {
        return Glm.add(res, (Vec2b) this, (int) b, b);
    }

    public Vec2b add(final int b, final Vec2b res) {
        return Glm.add(res, (Vec2b) this, b, b);
    }

    public Vec2b add(final byte bX, final byte bY, final Vec2b res) {
        return Glm.add(res, (Vec2b) this, (int) bX, bY);
    }

    public Vec2b add(final int bX, final int bY, final Vec2b res) {
        return Glm.add(res, (Vec2b) this, bX, bY);
    }

    public Vec2b add(final Vec2b b, final Vec2b res) {
        return Glm.add(res, (Vec2b) this, (int) b.x, b.y);
    }

    public Vec2b sub_(final byte b) {
        return Glm.sub(new Vec2b(), (Vec2b) this, (int) b, b);
    }

    public Vec2b sub_(final int b) {
        return Glm.sub(new Vec2b(), (Vec2b) this, b, b);
    }

    public Vec2b sub_(final byte bX, final byte bY) {
        return Glm.sub(new Vec2b(), (Vec2b) this, (int) bX, bY);
    }

    public Vec2b sub_(final int bX, final int bY) {
        return Glm.sub(new Vec2b(), (Vec2b) this, bX, bY);
    }

    public Vec2b sub_(final Vec2b b) {
        return Glm.sub(new Vec2b(), (Vec2b) this, (int) b.x, b.y);
    }

    public Vec2b sub(final byte b) {
        return Glm.sub((Vec2b) this, (Vec2b) this, (int) b, b);
    }

    public Vec2b sub(final int b) {
        return Glm.sub((Vec2b) this, (Vec2b) this, b, b);
    }

    public Vec2b sub(final byte bX, final byte bY) {
        return Glm.sub((Vec2b) this, (Vec2b) this, (int) bX, bY);
    }

    public Vec2b sub(final int bX, final int bY) {
        return Glm.sub((Vec2b) this, (Vec2b) this, bX, bY);
    }

    public Vec2b sub(final Vec2b b) {
        return Glm.sub((Vec2b) this, (Vec2b) this, (int) b.x, b.y);
    }

    public Vec2b sub(final byte b, final Vec2b res) {
        return Glm.sub(res, (Vec2b) this, (int) b, b);
    }

    public Vec2b sub(final int b, final Vec2b res) {
        return Glm.sub(res, (Vec2b) this, b, b);
    }

    public Vec2b sub(final byte bX, final byte bY, final Vec2b res) {
        return Glm.sub(res, (Vec2b) this, (int) bX, bY);
    }

    public Vec2b sub(final int bX, final int bY, final Vec2b res) {
        return Glm.sub(res, (Vec2b) this, bX, bY);
    }

    public Vec2b sub(final Vec2b b, final Vec2b res) {
        return Glm.sub(res, (Vec2b) this, (int) b.x, b.y);
    }
    
    public Vec2b mul_(final byte b) {
        return Glm.mul(new Vec2b(), (Vec2b) this, (int) b, b);
    }

    public Vec2b mul_(final int b) {
        return Glm.mul(new Vec2b(), (Vec2b) this, b, b);
    }

    public Vec2b mul_(final byte bX, final byte bY) {
        return Glm.mul(new Vec2b(), (Vec2b) this, (int) bX, bY);
    }

    public Vec2b mul_(final int bX, final int bY) {
        return Glm.mul(new Vec2b(), (Vec2b) this, bX, bY);
    }

    public Vec2b mul_(final Vec2b b) {
        return Glm.mul(new Vec2b(), (Vec2b) this, (int) b.x, b.y);
    }

    public Vec2b mul(final byte b) {
        return Glm.mul((Vec2b) this, (Vec2b) this, (int) b, b);
    }

    public Vec2b mul(final int b) {
        return Glm.mul((Vec2b) this, (Vec2b) this, b, b);
    }

    public Vec2b mul(final byte bX, final byte bY) {
        return Glm.mul((Vec2b) this, (Vec2b) this, (int) bX, bY);
    }

    public Vec2b mul(final int bX, final int bY) {
        return Glm.mul((Vec2b) this, (Vec2b) this, bX, bY);
    }

    public Vec2b mul(final Vec2b b) {
        return Glm.mul((Vec2b) this, (Vec2b) this, (int) b.x, b.y);
    }

    public Vec2b mul(final byte b, final Vec2b res) {
        return Glm.mul(res, (Vec2b) this, (int) b, b);
    }

    public Vec2b mul(final int b, final Vec2b res) {
        return Glm.mul(res, (Vec2b) this, b, b);
    }

    public Vec2b mul(final byte bX, final byte bY, final Vec2b res) {
        return Glm.mul(res, (Vec2b) this, (int) bX, bY);
    }

    public Vec2b mul(final int bX, final int bY, final Vec2b res) {
        return Glm.mul(res, (Vec2b) this, bX, bY);
    }

    public Vec2b mul(final Vec2b b, final Vec2b res) {
        return Glm.mul(res, (Vec2b) this, (int) b.x, b.y);
    }
    
    public Vec2b div_(final byte b) {
        return Glm.div(new Vec2b(), (Vec2b) this, (int) b, b);
    }

    public Vec2b div_(final int b) {
        return Glm.div(new Vec2b(), (Vec2b) this, b, b);
    }

    public Vec2b div_(final byte bX, final byte bY) {
        return Glm.div(new Vec2b(), (Vec2b) this, (int) bX, bY);
    }

    public Vec2b div_(final int bX, final int bY) {
        return Glm.div(new Vec2b(), (Vec2b) this, bX, bY);
    }

    public Vec2b div_(final Vec2b b) {
        return Glm.div(new Vec2b(), (Vec2b) this, (int) b.x, b.y);
    }

    public Vec2b div(final byte b) {
        return Glm.div((Vec2b) this, (Vec2b) this, (int) b, b);
    }

    public Vec2b div(final int b) {
        return Glm.div((Vec2b) this, (Vec2b) this, b, b);
    }

    public Vec2b div(final byte bX, final byte bY) {
        return Glm.div((Vec2b) this, (Vec2b) this, (int) bX, bY);
    }

    public Vec2b div(final int bX, final int bY) {
        return Glm.div((Vec2b) this, (Vec2b) this, bX, bY);
    }

    public Vec2b div(final Vec2b b) {
        return Glm.div((Vec2b) this, (Vec2b) this, (int) b.x, b.y);
    }

    public Vec2b div(final byte b, final Vec2b res) {
        return Glm.div(res, (Vec2b) this, (int) b, b);
    }

    public Vec2b div(final int b, final Vec2b res) {
        return Glm.div(res, (Vec2b) this, b, b);
    }

    public Vec2b div(final byte bX, final byte bY, final Vec2b res) {
        return Glm.div(res, (Vec2b) this, (int) bX, bY);
    }

    public Vec2b div(final int bX, final int bY, final Vec2b res) {
        return Glm.div(res, (Vec2b) this, bX, bY);
    }

    public Vec2b div(final Vec2b b, final Vec2b res) {
        return Glm.div(res, (Vec2b) this, (int) b.x, b.y);
    }

    public Vec2b incr_() {
        return Glm.incr_((Vec2b) this);
    }

    public Vec2b incr() {
        return Glm.incr((Vec2b) this);
    }

    public Vec2b incr(final Vec2b res) {
        return Glm.incr(res, (Vec2b) this);
    }

    public Vec2b decr_() {
        return Glm.decr_((Vec2b) this);
    }

    public Vec2b decr() {
        return Glm.decr((Vec2b) this);
    }

    public Vec2b decr(final Vec2b res) {
        return Glm.decr(res, (Vec2b) this);
    }
}
