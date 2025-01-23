/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.us;

import glmath.glm.Glm;
import glmath.joou.UShort;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 3 * Short.BYTES;

    public UShort x = new UShort(), y = new UShort(), z = new UShort();

    public Vec3us add_(final UShort b) {
        return Glm.add(new Vec3us(), (Vec3us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec3us add_(final short b) {
        return Glm.add(new Vec3us(), (Vec3us) this, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec3us add_(final int b) {
        return Glm.add(new Vec3us(), (Vec3us) this, b, b, b);
    }

    public Vec3us add_(final UShort bX, final UShort bY, final UShort bZ) {
        return Glm.add(new Vec3us(), (Vec3us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public Vec3us add_(final short bX, final short bY, final short bZ) {
        return Glm.add(new Vec3us(), (Vec3us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public Vec3us add_(final int bX, final int bY, final int bZ) {
        return Glm.add(new Vec3us(), (Vec3us) this, bX, bY, bZ);
    }

    public Vec3us add_(final Vec3us b) {
        return Glm.add(new Vec3us(), (Vec3us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public Vec3us add(final UShort b) {
        return Glm.add((Vec3us) this, (Vec3us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec3us add(final short b) {
        return Glm.add((Vec3us) this, (Vec3us) this, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec3us add(final int b) {
        return Glm.add((Vec3us) this, (Vec3us) this, b, b, b);
    }

    public Vec3us add(final UShort bX, final UShort bY, final UShort bZ) {
        return Glm.add((Vec3us) this, (Vec3us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public Vec3us add(final short bX, final short bY, final short bZ) {
        return Glm.add((Vec3us) this, (Vec3us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public Vec3us add(final int bX, final int bY, final int bZ) {
        return Glm.add((Vec3us) this, (Vec3us) this, bX, bY, bZ);
    }

    public Vec3us add(final Vec3us b) {
        return Glm.add((Vec3us) this, (Vec3us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public Vec3us add(final UShort b, final Vec3us res) {
        return Glm.add(res, (Vec3us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec3us add(final short b, final Vec3us res) {
        return Glm.add(res, (Vec3us) this, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec3us add(final int b, final Vec3us res) {
        return Glm.add(res, (Vec3us) this, b, b, b);
    }

    public Vec3us add(final UShort bX, final UShort bY, final UShort bZ, final Vec3us res) {
        return Glm.add(res, (Vec3us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public Vec3us add(final short bX, final short bY, final short bZ, final Vec3us res) {
        return Glm.add(res, (Vec3us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public Vec3us add(final int bX, final int bY, final int bZ, final Vec3us res) {
        return Glm.add(res, (Vec3us) this, bX, bY, bZ);
    }

    public Vec3us add(final Vec3us b, final Vec3us res) {
        return Glm.add(res, (Vec3us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public Vec3us sub_(final UShort b) {
        return Glm.sub(new Vec3us(), (Vec3us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec3us sub_(final short b) {
        return Glm.sub(new Vec3us(), (Vec3us) this, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec3us sub_(final int b) {
        return Glm.sub(new Vec3us(), (Vec3us) this, b, b, b);
    }

    public Vec3us sub_(final UShort bX, final UShort bY, final UShort bZ) {
        return Glm.sub(new Vec3us(), (Vec3us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public Vec3us sub_(final short bX, final short bY, final short bZ) {
        return Glm.sub(new Vec3us(), (Vec3us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public Vec3us sub_(final int bX, final int bY, final int bZ) {
        return Glm.sub(new Vec3us(), (Vec3us) this, bX, bY, bZ);
    }

    public Vec3us sub_(final Vec3us b) {
        return Glm.sub(new Vec3us(), (Vec3us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public Vec3us sub(final UShort b) {
        return Glm.sub((Vec3us) this, (Vec3us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec3us sub(final short b) {
        return Glm.sub((Vec3us) this, (Vec3us) this, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec3us sub(final int b) {
        return Glm.sub((Vec3us) this, (Vec3us) this, b, b, b);
    }

    public Vec3us sub(final UShort bX, final UShort bY, final UShort bZ) {
        return Glm.sub((Vec3us) this, (Vec3us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public Vec3us sub(final short bX, final short bY, final short bZ) {
        return Glm.sub((Vec3us) this, (Vec3us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public Vec3us sub(final int bX, final int bY, final int bZ) {
        return Glm.sub((Vec3us) this, (Vec3us) this, bX, bY, bZ);
    }

    public Vec3us sub(final Vec3us b) {
        return Glm.sub((Vec3us) this, (Vec3us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public Vec3us sub(final UShort b, final Vec3us res) {
        return Glm.sub(res, (Vec3us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec3us sub(final short b, final Vec3us res) {
        return Glm.sub(res, (Vec3us) this, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec3us sub(final int b, final Vec3us res) {
        return Glm.sub(res, (Vec3us) this, b, b, b);
    }

    public Vec3us sub(final UShort bX, final UShort bY, final UShort bZ, final Vec3us res) {
        return Glm.sub(res, (Vec3us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public Vec3us sub(final short bX, final short bY, final short bZ, final Vec3us res) {
        return Glm.sub(res, (Vec3us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public Vec3us sub(final int bX, final int bY, final int bZ, final Vec3us res) {
        return Glm.sub(res, (Vec3us) this, bX, bY, bZ);
    }

    public Vec3us sub(final Vec3us b, final Vec3us res) {
        return Glm.sub(res, (Vec3us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public Vec3us mul_(final UShort b) {
        return Glm.mul(new Vec3us(), (Vec3us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec3us mul_(final short b) {
        return Glm.mul(new Vec3us(), (Vec3us) this, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec3us mul_(final int b) {
        return Glm.mul(new Vec3us(), (Vec3us) this, b, b, b);
    }

    public Vec3us mul_(final UShort bX, final UShort bY, final UShort bZ) {
        return Glm.mul(new Vec3us(), (Vec3us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public Vec3us mul_(final short bX, final short bY, final short bZ) {
        return Glm.mul(new Vec3us(), (Vec3us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public Vec3us mul_(final int bX, final int bY, final int bZ) {
        return Glm.mul(new Vec3us(), (Vec3us) this, bX, bY, bZ);
    }

    public Vec3us mul_(final Vec3us b) {
        return Glm.mul(new Vec3us(), (Vec3us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public Vec3us mul(final UShort b) {
        return Glm.mul((Vec3us) this, (Vec3us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec3us mul(final short b) {
        return Glm.mul((Vec3us) this, (Vec3us) this, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec3us mul(final int b) {
        return Glm.mul((Vec3us) this, (Vec3us) this, b, b, b);
    }

    public Vec3us mul(final UShort bX, final UShort bY, final UShort bZ) {
        return Glm.mul((Vec3us) this, (Vec3us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public Vec3us mul(final short bX, final short bY, final short bZ) {
        return Glm.mul((Vec3us) this, (Vec3us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public Vec3us mul(final int bX, final int bY, final int bZ) {
        return Glm.mul((Vec3us) this, (Vec3us) this, bX, bY, bZ);
    }

    public Vec3us mul(final Vec3us b) {
        return Glm.mul((Vec3us) this, (Vec3us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public Vec3us mul(final UShort b, final Vec3us res) {
        return Glm.mul(res, (Vec3us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec3us mul(final short b, final Vec3us res) {
        return Glm.mul(res, (Vec3us) this, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec3us mul(final int b, final Vec3us res) {
        return Glm.mul(res, (Vec3us) this, b, b, b);
    }

    public Vec3us mul(final UShort bX, final UShort bY, final UShort bZ, final Vec3us res) {
        return Glm.mul(res, (Vec3us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public Vec3us mul(final short bX, final short bY, final short bZ, final Vec3us res) {
        return Glm.mul(res, (Vec3us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public Vec3us mul(final int bX, final int bY, final int bZ, final Vec3us res) {
        return Glm.mul(res, (Vec3us) this, bX, bY, bZ);
    }

    public Vec3us mul(final Vec3us b, final Vec3us res) {
        return Glm.mul(res, (Vec3us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public Vec3us div_(final UShort b) {
        return Glm.div(new Vec3us(), (Vec3us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec3us div_(final short b) {
        return Glm.div(new Vec3us(), (Vec3us) this, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec3us div_(final int b) {
        return Glm.div(new Vec3us(), (Vec3us) this, b, b, b);
    }

    public Vec3us div_(final UShort bX, final UShort bY, final UShort bZ) {
        return Glm.div(new Vec3us(), (Vec3us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public Vec3us div_(final short bX, final short bY, final short bZ) {
        return Glm.div(new Vec3us(), (Vec3us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public Vec3us div_(final int bX, final int bY, final int bZ) {
        return Glm.div(new Vec3us(), (Vec3us) this, bX, bY, bZ);
    }

    public Vec3us div_(final Vec3us b) {
        return Glm.div(new Vec3us(), (Vec3us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public Vec3us div(final UShort b) {
        return Glm.div((Vec3us) this, (Vec3us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec3us div(final short b) {
        return Glm.div((Vec3us) this, (Vec3us) this, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec3us div(final int b) {
        return Glm.div((Vec3us) this, (Vec3us) this, b, b, b);
    }

    public Vec3us div(final UShort bX, final UShort bY, final UShort bZ) {
        return Glm.div((Vec3us) this, (Vec3us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public Vec3us div(final short bX, final short bY, final short bZ) {
        return Glm.div((Vec3us) this, (Vec3us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public Vec3us div(final int bX, final int bY, final int bZ) {
        return Glm.div((Vec3us) this, (Vec3us) this, bX, bY, bZ);
    }

    public Vec3us div(final Vec3us b) {
        return Glm.div((Vec3us) this, (Vec3us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public Vec3us div(final UShort b, final Vec3us res) {
        return Glm.div(res, (Vec3us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec3us div(final short b, final Vec3us res) {
        return Glm.div(res, (Vec3us) this, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec3us div(final int b, final Vec3us res) {
        return Glm.div(res, (Vec3us) this, b, b, b);
    }

    public Vec3us div(final UShort bX, final UShort bY, final UShort bZ, final Vec3us res) {
        return Glm.div(res, (Vec3us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public Vec3us div(final short bX, final short bY, final short bZ, final Vec3us res) {
        return Glm.div(res, (Vec3us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public Vec3us div(final int bX, final int bY, final int bZ, final Vec3us res) {
        return Glm.div(res, (Vec3us) this, bX, bY, bZ);
    }

    public Vec3us div(final Vec3us b, final Vec3us res) {
        return Glm.div(res, (Vec3us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public Vec3us incr_() {
        return Glm.incr_((Vec3us) this);
    }

    public Vec3us incr() {
        return Glm.incr((Vec3us) this);
    }

    public Vec3us incr(final Vec3us res) {
        return Glm.incr(res, (Vec3us) this);
    }

    public Vec3us decr_() {
        return Glm.decr_((Vec3us) this);
    }

    public Vec3us decr() {
        return Glm.decr((Vec3us) this);
    }

    public Vec3us decr(final Vec3us res) {
        return Glm.decr(res, (Vec3us) this);
    }
}
