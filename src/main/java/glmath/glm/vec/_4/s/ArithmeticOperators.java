/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.s;

import glmath.glm.Glm;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 4 * Short.BYTES;

    public short x, y, z, w;

    public Vec4s add_(final short b) {
        return Glm.add(new Vec4s(), (Vec4s) this, (int) b, b, b, b);
    }

    public Vec4s add_(final int b) {
        return Glm.add(new Vec4s(), (Vec4s) this, b, b, b, b);
    }

    public Vec4s add_(final short bX, final short bY, final short bZ, final short bW) {
        return Glm.add(new Vec4s(), (Vec4s) this, (int) bX, bY, bZ, bW);
    }

    public Vec4s add_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.add(new Vec4s(), (Vec4s) this, bX, bY, bZ, bW);
    }

    public Vec4s add_(final Vec4s b) {
        return Glm.add(new Vec4s(), (Vec4s) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4s add(final short b) {
        return Glm.add((Vec4s) this, (Vec4s) this, (int) b, b, b, b);
    }

    public Vec4s add(final int b) {
        return Glm.add((Vec4s) this, (Vec4s) this, b, b, b, b);
    }

    public Vec4s add(final short bX, final short bY, final short bZ, final short bW) {
        return Glm.add((Vec4s) this, (Vec4s) this, (int) bX, bY, bZ, bW);
    }

    public Vec4s add(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.add((Vec4s) this, (Vec4s) this, bX, bY, bZ, bW);
    }

    public Vec4s add(final Vec4s b) {
        return Glm.add((Vec4s) this, (Vec4s) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4s add(final short b, final Vec4s res) {
        return Glm.add(res, (Vec4s) this, (int) b, b, b, b);
    }

    public Vec4s add(final int b, final Vec4s res) {
        return Glm.add(res, (Vec4s) this, b, b, b, b);
    }

    public Vec4s add(final short bX, final short bY, final short bZ, final short bW, final Vec4s res) {
        return Glm.add(res, (Vec4s) this, (int) bX, bY, bZ, bW);
    }

    public Vec4s add(final int bX, final int bY, final int bZ, final int bW, final Vec4s res) {
        return Glm.add(res, (Vec4s) this, bX, bY, bZ, bW);
    }

    public Vec4s add(final Vec4s b, final Vec4s res) {
        return Glm.add(res, (Vec4s) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4s sub_(final short b) {
        return Glm.sub(new Vec4s(), (Vec4s) this, (int) b, b, b, b);
    }

    public Vec4s sub_(final int b) {
        return Glm.sub(new Vec4s(), (Vec4s) this, b, b, b, b);
    }

    public Vec4s sub_(final short bX, final short bY, final short bZ, final short bW) {
        return Glm.sub(new Vec4s(), (Vec4s) this, (int) bX, bY, bZ, bW);
    }

    public Vec4s sub_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.sub(new Vec4s(), (Vec4s) this, bX, bY, bZ, bW);
    }

    public Vec4s sub_(final Vec4s b) {
        return Glm.sub(new Vec4s(), (Vec4s) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4s sub(final short b) {
        return Glm.sub((Vec4s) this, (Vec4s) this, (int) b, b, b, b);
    }

    public Vec4s sub(final int b) {
        return Glm.sub((Vec4s) this, (Vec4s) this, b, b, b, b);
    }

    public Vec4s sub(final short bX, final short bY, final short bZ, final short bW) {
        return Glm.sub((Vec4s) this, (Vec4s) this, (int) bX, bY, bZ, bW);
    }

    public Vec4s sub(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.sub((Vec4s) this, (Vec4s) this, bX, bY, bZ, bW);
    }

    public Vec4s sub(final Vec4s b) {
        return Glm.sub((Vec4s) this, (Vec4s) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4s sub(final short b, final Vec4s res) {
        return Glm.sub(res, (Vec4s) this, (int) b, b, b, b);
    }

    public Vec4s sub(final int b, final Vec4s res) {
        return Glm.sub(res, (Vec4s) this, b, b, b, b);
    }

    public Vec4s sub(final short bX, final short bY, final short bZ, final short bW, final Vec4s res) {
        return Glm.sub(res, (Vec4s) this, (int) bX, bY, bZ, bW);
    }

    public Vec4s sub(final int bX, final int bY, final int bZ, final int bW, final Vec4s res) {
        return Glm.sub(res, (Vec4s) this, bX, bY, bZ, bW);
    }

    public Vec4s sub(final Vec4s b, final Vec4s res) {
        return Glm.sub(res, (Vec4s) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4s mul_(final short b) {
        return Glm.mul(new Vec4s(), (Vec4s) this, (int) b, b, b, b);
    }

    public Vec4s mul_(final int b) {
        return Glm.mul(new Vec4s(), (Vec4s) this, b, b, b, b);
    }

    public Vec4s mul_(final short bX, final short bY, final short bZ, final short bW) {
        return Glm.mul(new Vec4s(), (Vec4s) this, (int) bX, bY, bZ, bW);
    }

    public Vec4s mul_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.mul(new Vec4s(), (Vec4s) this, bX, bY, bZ, bW);
    }

    public Vec4s mul_(final Vec4s b) {
        return Glm.mul(new Vec4s(), (Vec4s) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4s mul(final short b) {
        return Glm.mul((Vec4s) this, (Vec4s) this, (int) b, b, b, b);
    }

    public Vec4s mul(final int b) {
        return Glm.mul((Vec4s) this, (Vec4s) this, b, b, b, b);
    }

    public Vec4s mul(final short bX, final short bY, final short bZ, final short bW) {
        return Glm.mul((Vec4s) this, (Vec4s) this, (int) bX, bY, bZ, bW);
    }

    public Vec4s mul(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.mul((Vec4s) this, (Vec4s) this, bX, bY, bZ, bW);
    }

    public Vec4s mul(final Vec4s b) {
        return Glm.mul((Vec4s) this, (Vec4s) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4s mul(final short b, final Vec4s res) {
        return Glm.mul(res, (Vec4s) this, (int) b, b, b, b);
    }

    public Vec4s mul(final int b, final Vec4s res) {
        return Glm.mul(res, (Vec4s) this, b, b, b, b);
    }

    public Vec4s mul(final short bX, final short bY, final short bZ, final short bW, final Vec4s res) {
        return Glm.mul(res, (Vec4s) this, (int) bX, bY, bZ, bW);
    }

    public Vec4s mul(final int bX, final int bY, final int bZ, final int bW, final Vec4s res) {
        return Glm.mul(res, (Vec4s) this, bX, bY, bZ, bW);
    }

    public Vec4s mul(final Vec4s b, final Vec4s res) {
        return Glm.mul(res, (Vec4s) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4s div_(final short b) {
        return Glm.div(new Vec4s(), (Vec4s) this, (int) b, b, b, b);
    }

    public Vec4s div_(final int b) {
        return Glm.div(new Vec4s(), (Vec4s) this, b, b, b, b);
    }

    public Vec4s div_(final short bX, final short bY, final short bZ, final short bW) {
        return Glm.div(new Vec4s(), (Vec4s) this, (int) bX, bY, bZ, bW);
    }

    public Vec4s div_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.div(new Vec4s(), (Vec4s) this, bX, bY, bZ, bW);
    }

    public Vec4s div_(final Vec4s b) {
        return Glm.div(new Vec4s(), (Vec4s) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4s div(final short b) {
        return Glm.div((Vec4s) this, (Vec4s) this, (int) b, b, b, b);
    }

    public Vec4s div(final int b) {
        return Glm.div((Vec4s) this, (Vec4s) this, b, b, b, b);
    }

    public Vec4s div(final short bX, final short bY, final short bZ, final short bW) {
        return Glm.div((Vec4s) this, (Vec4s) this, (int) bX, bY, bZ, bW);
    }

    public Vec4s div(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.div((Vec4s) this, (Vec4s) this, bX, bY, bZ, bW);
    }

    public Vec4s div(final Vec4s b) {
        return Glm.div((Vec4s) this, (Vec4s) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4s div(final short b, final Vec4s res) {
        return Glm.div(res, (Vec4s) this, (int) b, b, b, b);
    }

    public Vec4s div(final int b, final Vec4s res) {
        return Glm.div(res, (Vec4s) this, b, b, b, b);
    }

    public Vec4s div(final short bX, final short bY, final short bZ, final short bW, final Vec4s res) {
        return Glm.div(res, (Vec4s) this, (int) bX, bY, bZ, bW);
    }

    public Vec4s div(final int bX, final int bY, final int bZ, final int bW, final Vec4s res) {
        return Glm.div(res, (Vec4s) this, bX, bY, bZ, bW);
    }

    public Vec4s div(final Vec4s b, final Vec4s res) {
        return Glm.div(res, (Vec4s) this, (int) b.x, b.y, b.z, b.w);
    }

    public Vec4s incr_() {
        return Glm.incr_((Vec4s) this);
    }

    public Vec4s incr() {
        return Glm.incr((Vec4s) this);
    }

    public Vec4s incr(final Vec4s res) {
        return Glm.incr(res, (Vec4s) this);
    }

    public Vec4s decr_() {
        return Glm.decr_((Vec4s) this);
    }

    public Vec4s decr() {
        return Glm.decr((Vec4s) this);
    }

    public Vec4s decr(final Vec4s res) {
        return Glm.decr(res, (Vec4s) this);
    }
}
