/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.s;

import glmath.glm.Glm;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 3 * Short.BYTES;

    public short x, y, z;

    public Vec3s add_(final short b) {
        return Glm.add(new Vec3s(), (Vec3s) this, (int) b, b, b);
    }

    public Vec3s add_(final int b) {
        return Glm.add(new Vec3s(), (Vec3s) this, b, b, b);
    }

    public Vec3s add_(final short bX, final short bY, final short bZ) {
        return Glm.add(new Vec3s(), (Vec3s) this, (int) bX, bY, bZ);
    }

    public Vec3s add_(final int bX, final int bY, final int bZ) {
        return Glm.add(new Vec3s(), (Vec3s) this, bX, bY, bZ);
    }

    public Vec3s add_(final Vec3s b) {
        return Glm.add(new Vec3s(), (Vec3s) this, (int) b.x, b.y, b.z);
    }

    public Vec3s add(final short b) {
        return Glm.add((Vec3s) this, (Vec3s) this, (int) b, b, b);
    }

    public Vec3s add(final int b) {
        return Glm.add((Vec3s) this, (Vec3s) this, b, b, b);
    }

    public Vec3s add(final short bX, final short bY, final short bZ) {
        return Glm.add((Vec3s) this, (Vec3s) this, (int) bX, bY, bZ);
    }

    public Vec3s add(final int bX, final int bY, final int bZ) {
        return Glm.add((Vec3s) this, (Vec3s) this, bX, bY, bZ);
    }

    public Vec3s add(final Vec3s b) {
        return Glm.add((Vec3s) this, (Vec3s) this, (int) b.x, b.y, b.z);
    }

    public Vec3s add(final short b, final Vec3s res) {
        return Glm.add(res, (Vec3s) this, (int) b, b, b);
    }

    public Vec3s add(final int b, final Vec3s res) {
        return Glm.add(res, (Vec3s) this, b, b, b);
    }

    public Vec3s add(final short bX, final short bY, final short bZ, final Vec3s res) {
        return Glm.add(res, (Vec3s) this, (int) bX, bY, bZ);
    }

    public Vec3s add(final int bX, final int bY, final int bZ, final Vec3s res) {
        return Glm.add(res, (Vec3s) this, bX, bY, bZ);
    }

    public Vec3s add(final Vec3s b, final Vec3s res) {
        return Glm.add(res, (Vec3s) this, (int) b.x, b.y, b.z);
    }

    public Vec3s sub_(final short b) {
        return Glm.sub(new Vec3s(), (Vec3s) this, (int) b, b, b);
    }

    public Vec3s sub_(final int b) {
        return Glm.sub(new Vec3s(), (Vec3s) this, b, b, b);
    }

    public Vec3s sub_(final short bX, final short bY, final short bZ) {
        return Glm.sub(new Vec3s(), (Vec3s) this, (int) bX, bY, bZ);
    }

    public Vec3s sub_(final int bX, final int bY, final int bZ) {
        return Glm.sub(new Vec3s(), (Vec3s) this, bX, bY, bZ);
    }

    public Vec3s sub_(final Vec3s b) {
        return Glm.sub(new Vec3s(), (Vec3s) this, (int) b.x, b.y, b.z);
    }

    public Vec3s sub(final short b) {
        return Glm.sub((Vec3s) this, (Vec3s) this, (int) b, b, b);
    }

    public Vec3s sub(final int b) {
        return Glm.sub((Vec3s) this, (Vec3s) this, b, b, b);
    }

    public Vec3s sub(final short bX, final short bY, final short bZ) {
        return Glm.sub((Vec3s) this, (Vec3s) this, (int) bX, bY, bZ);
    }

    public Vec3s sub(final int bX, final int bY, final int bZ) {
        return Glm.sub((Vec3s) this, (Vec3s) this, bX, bY, bZ);
    }

    public Vec3s sub(final Vec3s b) {
        return Glm.sub((Vec3s) this, (Vec3s) this, (int) b.x, b.y, b.z);
    }

    public Vec3s sub(final short b, final Vec3s res) {
        return Glm.sub(res, (Vec3s) this, (int) b, b, b);
    }

    public Vec3s sub(final int b, final Vec3s res) {
        return Glm.sub(res, (Vec3s) this, b, b, b);
    }

    public Vec3s sub(final short bX, final short bY, final short bZ, final Vec3s res) {
        return Glm.sub(res, (Vec3s) this, (int) bX, bY, bZ);
    }

    public Vec3s sub(final int bX, final int bY, final int bZ, final Vec3s res) {
        return Glm.sub(res, (Vec3s) this, bX, bY, bZ);
    }

    public Vec3s sub(final Vec3s b, final Vec3s res) {
        return Glm.sub(res, (Vec3s) this, (int) b.x, b.y, b.z);
    }

    public Vec3s mul_(final short b) {
        return Glm.mul(new Vec3s(), (Vec3s) this, (int) b, b, b);
    }

    public Vec3s mul_(final int b) {
        return Glm.mul(new Vec3s(), (Vec3s) this, b, b, b);
    }

    public Vec3s mul_(final short bX, final short bY, final short bZ) {
        return Glm.mul(new Vec3s(), (Vec3s) this, (int) bX, bY, bZ);
    }

    public Vec3s mul_(final int bX, final int bY, final int bZ) {
        return Glm.mul(new Vec3s(), (Vec3s) this, bX, bY, bZ);
    }

    public Vec3s mul_(final Vec3s b) {
        return Glm.mul(new Vec3s(), (Vec3s) this, (int) b.x, b.y, b.z);
    }

    public Vec3s mul(final short b) {
        return Glm.mul((Vec3s) this, (Vec3s) this, (int) b, b, b);
    }

    public Vec3s mul(final int b) {
        return Glm.mul((Vec3s) this, (Vec3s) this, b, b, b);
    }

    public Vec3s mul(final short bX, final short bY, final short bZ) {
        return Glm.mul((Vec3s) this, (Vec3s) this, (int) bX, bY, bZ);
    }

    public Vec3s mul(final int bX, final int bY, final int bZ) {
        return Glm.mul((Vec3s) this, (Vec3s) this, bX, bY, bZ);
    }

    public Vec3s mul(final Vec3s b) {
        return Glm.mul((Vec3s) this, (Vec3s) this, (int) b.x, b.y, b.z);
    }

    public Vec3s mul(final short b, final Vec3s res) {
        return Glm.mul(res, (Vec3s) this, (int) b, b, b);
    }

    public Vec3s mul(final int b, final Vec3s res) {
        return Glm.mul(res, (Vec3s) this, b, b, b);
    }

    public Vec3s mul(final short bX, final short bY, final short bZ, final Vec3s res) {
        return Glm.mul(res, (Vec3s) this, (int) bX, bY, bZ);
    }

    public Vec3s mul(final int bX, final int bY, final int bZ, final Vec3s res) {
        return Glm.mul(res, (Vec3s) this, bX, bY, bZ);
    }

    public Vec3s mul(final Vec3s b, final Vec3s res) {
        return Glm.mul(res, (Vec3s) this, (int) b.x, b.y, b.z);
    }

    public Vec3s div_(final short b) {
        return Glm.div(new Vec3s(), (Vec3s) this, (int) b, b, b);
    }

    public Vec3s div_(final int b) {
        return Glm.div(new Vec3s(), (Vec3s) this, b, b, b);
    }

    public Vec3s div_(final short bX, final short bY, final short bZ) {
        return Glm.div(new Vec3s(), (Vec3s) this, (int) bX, bY, bZ);
    }

    public Vec3s div_(final int bX, final int bY, final int bZ) {
        return Glm.div(new Vec3s(), (Vec3s) this, bX, bY, bZ);
    }

    public Vec3s div_(final Vec3s b) {
        return Glm.div(new Vec3s(), (Vec3s) this, (int) b.x, b.y, b.z);
    }

    public Vec3s div(final short b) {
        return Glm.div((Vec3s) this, (Vec3s) this, (int) b, b, b);
    }

    public Vec3s div(final int b) {
        return Glm.div((Vec3s) this, (Vec3s) this, b, b, b);
    }

    public Vec3s div(final short bX, final short bY, final short bZ) {
        return Glm.div((Vec3s) this, (Vec3s) this, (int) bX, bY, bZ);
    }

    public Vec3s div(final int bX, final int bY, final int bZ) {
        return Glm.div((Vec3s) this, (Vec3s) this, bX, bY, bZ);
    }

    public Vec3s div(final Vec3s b) {
        return Glm.div((Vec3s) this, (Vec3s) this, (int) b.x, b.y, b.z);
    }

    public Vec3s div(final short b, final Vec3s res) {
        return Glm.div(res, (Vec3s) this, (int) b, b, b);
    }

    public Vec3s div(final int b, final Vec3s res) {
        return Glm.div(res, (Vec3s) this, b, b, b);
    }

    public Vec3s div(final short bX, final short bY, final short bZ, final Vec3s res) {
        return Glm.div(res, (Vec3s) this, (int) bX, bY, bZ);
    }

    public Vec3s div(final int bX, final int bY, final int bZ, final Vec3s res) {
        return Glm.div(res, (Vec3s) this, bX, bY, bZ);
    }

    public Vec3s div(final Vec3s b, final Vec3s res) {
        return Glm.div(res, (Vec3s) this, (int) b.x, b.y, b.z);
    }

    public Vec3s incr_() {
        return Glm.incr_((Vec3s) this);
    }

    public Vec3s incr() {
        return Glm.incr((Vec3s) this);
    }

    public Vec3s incr(final Vec3s res) {
        return Glm.incr(res, (Vec3s) this);
    }

    public Vec3s decr_() {
        return Glm.decr_((Vec3s) this);
    }

    public Vec3s decr() {
        return Glm.decr((Vec3s) this);
    }

    public Vec3s decr(final Vec3s res) {
        return Glm.decr(res, (Vec3s) this);
    }
}
