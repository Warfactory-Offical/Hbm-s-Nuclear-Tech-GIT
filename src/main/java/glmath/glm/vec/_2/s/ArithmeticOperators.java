/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2.s;

import glmath.glm.Glm;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {
    
    public static final int SIZE = 2 * Short.BYTES;

    public short x, y;
    
    public Vec2s add_(final short b) {
        return Glm.add(new Vec2s(), (Vec2s) this, (int) b, b);
    }

    public Vec2s add_(final int b) {
        return Glm.add(new Vec2s(), (Vec2s) this, b, b);
    }

    public Vec2s add_(final short bX, final short bY) {
        return Glm.add(new Vec2s(), (Vec2s) this, (int) bX, bY);
    }

    public Vec2s add_(final int bX, final int bY) {
        return Glm.add(new Vec2s(), (Vec2s) this, bX, bY);
    }

    public Vec2s add_(final Vec2s b) {
        return Glm.add(new Vec2s(), (Vec2s) this, (int) b.x, b.y);
    }

    public Vec2s add(final short b) {
        return Glm.add((Vec2s) this, (Vec2s) this, (int) b, b);
    }

    public Vec2s add(final int b) {
        return Glm.add((Vec2s) this, (Vec2s) this, b, b);
    }

    public Vec2s add(final short bX, final short bY) {
        return Glm.add((Vec2s) this, (Vec2s) this, (int) bX, bY);
    }

    public Vec2s add(final int bX, final int bY) {
        return Glm.add((Vec2s) this, (Vec2s) this, bX, bY);
    }

    public Vec2s add(final Vec2s b) {
        return Glm.add((Vec2s) this, (Vec2s) this, (int) b.x, b.y);
    }

    public Vec2s add(final short b, final Vec2s res) {
        return Glm.add(res, (Vec2s) this, (int) b, b);
    }

    public Vec2s add(final int b, final Vec2s res) {
        return Glm.add(res, (Vec2s) this, b, b);
    }

    public Vec2s add(final short bX, final short bY, final Vec2s res) {
        return Glm.add(res, (Vec2s) this, (int) bX, bY);
    }

    public Vec2s add(final int bX, final int bY, final Vec2s res) {
        return Glm.add(res, (Vec2s) this, bX, bY);
    }

    public Vec2s add(final Vec2s b, final Vec2s res) {
        return Glm.add(res, (Vec2s) this, (int) b.x, b.y);
    }

    public Vec2s sub_(final short b) {
        return Glm.sub(new Vec2s(), (Vec2s) this, (int) b, b);
    }

    public Vec2s sub_(final int b) {
        return Glm.sub(new Vec2s(), (Vec2s) this, b, b);
    }

    public Vec2s sub_(final short bX, final short bY) {
        return Glm.sub(new Vec2s(), (Vec2s) this, (int) bX, bY);
    }

    public Vec2s sub_(final int bX, final int bY) {
        return Glm.sub(new Vec2s(), (Vec2s) this, bX, bY);
    }

    public Vec2s sub_(final Vec2s b) {
        return Glm.sub(new Vec2s(), (Vec2s) this, (int) b.x, b.y);
    }

    public Vec2s sub(final short b) {
        return Glm.sub((Vec2s) this, (Vec2s) this, (int) b, b);
    }

    public Vec2s sub(final int b) {
        return Glm.sub((Vec2s) this, (Vec2s) this, b, b);
    }

    public Vec2s sub(final short bX, final short bY) {
        return Glm.sub((Vec2s) this, (Vec2s) this, (int) bX, bY);
    }

    public Vec2s sub(final int bX, final int bY) {
        return Glm.sub((Vec2s) this, (Vec2s) this, bX, bY);
    }

    public Vec2s sub(final Vec2s b) {
        return Glm.sub((Vec2s) this, (Vec2s) this, (int) b.x, b.y);
    }

    public Vec2s sub(final short b, final Vec2s res) {
        return Glm.sub(res, (Vec2s) this, (int) b, b);
    }

    public Vec2s sub(final int b, final Vec2s res) {
        return Glm.sub(res, (Vec2s) this, b, b);
    }

    public Vec2s sub(final short bX, final short bY, final Vec2s res) {
        return Glm.sub(res, (Vec2s) this, (int) bX, bY);
    }

    public Vec2s sub(final int bX, final int bY, final Vec2s res) {
        return Glm.sub(res, (Vec2s) this, bX, bY);
    }

    public Vec2s sub(final Vec2s b, final Vec2s res) {
        return Glm.sub(res, (Vec2s) this, (int) b.x, b.y);
    }
    
    public Vec2s mul_(final short b) {
        return Glm.mul(new Vec2s(), (Vec2s) this, (int) b, b);
    }

    public Vec2s mul_(final int b) {
        return Glm.mul(new Vec2s(), (Vec2s) this, b, b);
    }

    public Vec2s mul_(final short bX, final short bY) {
        return Glm.mul(new Vec2s(), (Vec2s) this, (int) bX, bY);
    }

    public Vec2s mul_(final int bX, final int bY) {
        return Glm.mul(new Vec2s(), (Vec2s) this, bX, bY);
    }

    public Vec2s mul_(final Vec2s b) {
        return Glm.mul(new Vec2s(), (Vec2s) this, (int) b.x, b.y);
    }

    public Vec2s mul(final short b) {
        return Glm.mul((Vec2s) this, (Vec2s) this, (int) b, b);
    }

    public Vec2s mul(final int b) {
        return Glm.mul((Vec2s) this, (Vec2s) this, b, b);
    }

    public Vec2s mul(final short bX, final short bY) {
        return Glm.mul((Vec2s) this, (Vec2s) this, (int) bX, bY);
    }

    public Vec2s mul(final int bX, final int bY) {
        return Glm.mul((Vec2s) this, (Vec2s) this, bX, bY);
    }

    public Vec2s mul(final Vec2s b) {
        return Glm.mul((Vec2s) this, (Vec2s) this, (int) b.x, b.y);
    }

    public Vec2s mul(final short b, final Vec2s res) {
        return Glm.mul(res, (Vec2s) this, (int) b, b);
    }

    public Vec2s mul(final int b, final Vec2s res) {
        return Glm.mul(res, (Vec2s) this, b, b);
    }

    public Vec2s mul(final short bX, final short bY, final Vec2s res) {
        return Glm.mul(res, (Vec2s) this, (int) bX, bY);
    }

    public Vec2s mul(final int bX, final int bY, final Vec2s res) {
        return Glm.mul(res, (Vec2s) this, bX, bY);
    }

    public Vec2s mul(final Vec2s b, final Vec2s res) {
        return Glm.mul(res, (Vec2s) this, (int) b.x, b.y);
    }
    
    public Vec2s div_(final short b) {
        return Glm.div(new Vec2s(), (Vec2s) this, (int) b, b);
    }

    public Vec2s div_(final int b) {
        return Glm.div(new Vec2s(), (Vec2s) this, b, b);
    }

    public Vec2s div_(final short bX, final short bY) {
        return Glm.div(new Vec2s(), (Vec2s) this, (int) bX, bY);
    }

    public Vec2s div_(final int bX, final int bY) {
        return Glm.div(new Vec2s(), (Vec2s) this, bX, bY);
    }

    public Vec2s div_(final Vec2s b) {
        return Glm.div(new Vec2s(), (Vec2s) this, (int) b.x, b.y);
    }

    public Vec2s div(final short b) {
        return Glm.div((Vec2s) this, (Vec2s) this, (int) b, b);
    }

    public Vec2s div(final int b) {
        return Glm.div((Vec2s) this, (Vec2s) this, b, b);
    }

    public Vec2s div(final short bX, final short bY) {
        return Glm.div((Vec2s) this, (Vec2s) this, (int) bX, bY);
    }

    public Vec2s div(final int bX, final int bY) {
        return Glm.div((Vec2s) this, (Vec2s) this, bX, bY);
    }

    public Vec2s div(final Vec2s b) {
        return Glm.div((Vec2s) this, (Vec2s) this, (int) b.x, b.y);
    }

    public Vec2s div(final short b, final Vec2s res) {
        return Glm.div(res, (Vec2s) this, (int) b, b);
    }

    public Vec2s div(final int b, final Vec2s res) {
        return Glm.div(res, (Vec2s) this, b, b);
    }

    public Vec2s div(final short bX, final short bY, final Vec2s res) {
        return Glm.div(res, (Vec2s) this, (int) bX, bY);
    }

    public Vec2s div(final int bX, final int bY, final Vec2s res) {
        return Glm.div(res, (Vec2s) this, bX, bY);
    }

    public Vec2s div(final Vec2s b, final Vec2s res) {
        return Glm.div(res, (Vec2s) this, (int) b.x, b.y);
    }

    public Vec2s incr_() {
        return Glm.incr_((Vec2s) this);
    }

    public Vec2s incr() {
        return Glm.incr((Vec2s) this);
    }

    public Vec2s incr(final Vec2s res) {
        return Glm.incr(res, (Vec2s) this);
    }

    public Vec2s decr_() {
        return Glm.decr_((Vec2s) this);
    }

    public Vec2s decr() {
        return Glm.decr((Vec2s) this);
    }

    public Vec2s decr(final Vec2s res) {
        return Glm.decr(res, (Vec2s) this);
    }
}
