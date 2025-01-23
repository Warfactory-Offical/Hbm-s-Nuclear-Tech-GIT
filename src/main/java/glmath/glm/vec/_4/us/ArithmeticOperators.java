/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.us;

import glmath.glm.Glm;
import glmath.joou.UShort;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 4 * Short.BYTES;

    public UShort x = new UShort(), y = new UShort(), z = new UShort(), w = new UShort();

    public Vec4us add_(final UShort b) {
        return Glm.add(new Vec4us(), (Vec4us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec4us add_(final short b) {
        return Glm.add(new Vec4us(), (Vec4us) this, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec4us add_(final int b) {
        return Glm.add(new Vec4us(), (Vec4us) this, b, b, b, b);
    }

    public Vec4us add_(final UShort bX, final UShort bY, final UShort bZ, final UShort bW) {
        return Glm.add(new Vec4us(), (Vec4us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public Vec4us add_(final short bX, final short bY, final short bZ, final short bW) {
        return Glm.add(new Vec4us(), (Vec4us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public Vec4us add_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.add(new Vec4us(), (Vec4us) this, bX, bY, bZ, bW);
    }

    public Vec4us add_(final Vec4us b) {
        return Glm.add(new Vec4us(), (Vec4us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public Vec4us add(final UShort b) {
        return Glm.add((Vec4us) this, (Vec4us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec4us add(final short b) {
        return Glm.add((Vec4us) this, (Vec4us) this, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec4us add(final int b) {
        return Glm.add((Vec4us) this, (Vec4us) this, b, b, b, b);
    }

    public Vec4us add(final UShort bX, final UShort bY, final UShort bZ, final UShort bW) {
        return Glm.add((Vec4us) this, (Vec4us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public Vec4us add(final short bX, final short bY, final short bZ, final short bW) {
        return Glm.add((Vec4us) this, (Vec4us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public Vec4us add(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.add((Vec4us) this, (Vec4us) this, bX, bY, bZ, bW);
    }

    public Vec4us add(final Vec4us b) {
        return Glm.add((Vec4us) this, (Vec4us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public Vec4us add(final UShort b, final Vec4us res) {
        return Glm.add(res, (Vec4us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec4us add(final short b, final Vec4us res) {
        return Glm.add(res, (Vec4us) this, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec4us add(final int b, final Vec4us res) {
        return Glm.add(res, (Vec4us) this, b, b, b, b);
    }

    public Vec4us add(final UShort bX, final UShort bY, final UShort bZ, final UShort bW, final Vec4us res) {
        return Glm.add(res, (Vec4us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public Vec4us add(final short bX, final short bY, final short bZ, final short bW, final Vec4us res) {
        return Glm.add(res, (Vec4us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public Vec4us add(final int bX, final int bY, final int bZ, final int bW, final Vec4us res) {
        return Glm.add(res, (Vec4us) this, bX, bY, bZ, bW);
    }

    public Vec4us add(final Vec4us b, final Vec4us res) {
        return Glm.add(res, (Vec4us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public Vec4us sub_(final UShort b) {
        return Glm.sub(new Vec4us(), (Vec4us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec4us sub_(final short b) {
        return Glm.sub(new Vec4us(), (Vec4us) this, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec4us sub_(final int b) {
        return Glm.sub(new Vec4us(), (Vec4us) this, b, b, b, b);
    }

    public Vec4us sub_(final UShort bX, final UShort bY, final UShort bZ, final UShort bW) {
        return Glm.sub(new Vec4us(), (Vec4us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public Vec4us sub_(final short bX, final short bY, final short bZ, final short bW) {
        return Glm.sub(new Vec4us(), (Vec4us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public Vec4us sub_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.sub(new Vec4us(), (Vec4us) this, bX, bY, bZ, bW);
    }

    public Vec4us sub_(final Vec4us b) {
        return Glm.sub(new Vec4us(), (Vec4us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public Vec4us sub(final UShort b) {
        return Glm.sub((Vec4us) this, (Vec4us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec4us sub(final short b) {
        return Glm.sub((Vec4us) this, (Vec4us) this, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec4us sub(final int b) {
        return Glm.sub((Vec4us) this, (Vec4us) this, b, b, b, b);
    }

    public Vec4us sub(final UShort bX, final UShort bY, final UShort bZ, final UShort bW) {
        return Glm.sub((Vec4us) this, (Vec4us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public Vec4us sub(final short bX, final short bY, final short bZ, final short bW) {
        return Glm.sub((Vec4us) this, (Vec4us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public Vec4us sub(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.sub((Vec4us) this, (Vec4us) this, bX, bY, bZ, bW);
    }

    public Vec4us sub(final Vec4us b) {
        return Glm.sub((Vec4us) this, (Vec4us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public Vec4us sub(final UShort b, final Vec4us res) {
        return Glm.sub(res, (Vec4us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec4us sub(final short b, final Vec4us res) {
        return Glm.sub(res, (Vec4us) this, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec4us sub(final int b, final Vec4us res) {
        return Glm.sub(res, (Vec4us) this, b, b, b, b);
    }

    public Vec4us sub(final UShort bX, final UShort bY, final UShort bZ, final UShort bW, final Vec4us res) {
        return Glm.sub(res, (Vec4us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public Vec4us sub(final short bX, final short bY, final short bZ, final short bW, final Vec4us res) {
        return Glm.sub(res, (Vec4us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public Vec4us sub(final int bX, final int bY, final int bZ, final int bW, final Vec4us res) {
        return Glm.sub(res, (Vec4us) this, bX, bY, bZ, bW);
    }

    public Vec4us sub(final Vec4us b, final Vec4us res) {
        return Glm.sub(res, (Vec4us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public Vec4us mul_(final UShort b) {
        return Glm.mul(new Vec4us(), (Vec4us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec4us mul_(final short b) {
        return Glm.mul(new Vec4us(), (Vec4us) this, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec4us mul_(final int b) {
        return Glm.mul(new Vec4us(), (Vec4us) this, b, b, b, b);
    }

    public Vec4us mul_(final UShort bX, final UShort bY, final UShort bZ, final UShort bW) {
        return Glm.mul(new Vec4us(), (Vec4us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public Vec4us mul_(final short bX, final short bY, final short bZ, final short bW) {
        return Glm.mul(new Vec4us(), (Vec4us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public Vec4us mul_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.mul(new Vec4us(), (Vec4us) this, bX, bY, bZ, bW);
    }

    public Vec4us mul_(final Vec4us b) {
        return Glm.mul(new Vec4us(), (Vec4us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public Vec4us mul(final UShort b) {
        return Glm.mul((Vec4us) this, (Vec4us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec4us mul(final short b) {
        return Glm.mul((Vec4us) this, (Vec4us) this, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec4us mul(final int b) {
        return Glm.mul((Vec4us) this, (Vec4us) this, b, b, b, b);
    }

    public Vec4us mul(final UShort bX, final UShort bY, final UShort bZ, final UShort bW) {
        return Glm.mul((Vec4us) this, (Vec4us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public Vec4us mul(final short bX, final short bY, final short bZ, final short bW) {
        return Glm.mul((Vec4us) this, (Vec4us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public Vec4us mul(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.mul((Vec4us) this, (Vec4us) this, bX, bY, bZ, bW);
    }

    public Vec4us mul(final Vec4us b) {
        return Glm.mul((Vec4us) this, (Vec4us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public Vec4us mul(final UShort b, final Vec4us res) {
        return Glm.mul(res, (Vec4us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec4us mul(final short b, final Vec4us res) {
        return Glm.mul(res, (Vec4us) this, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec4us mul(final int b, final Vec4us res) {
        return Glm.mul(res, (Vec4us) this, b, b, b, b);
    }

    public Vec4us mul(final UShort bX, final UShort bY, final UShort bZ, final UShort bW, final Vec4us res) {
        return Glm.mul(res, (Vec4us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public Vec4us mul(final short bX, final short bY, final short bZ, final short bW, final Vec4us res) {
        return Glm.mul(res, (Vec4us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public Vec4us mul(final int bX, final int bY, final int bZ, final int bW, final Vec4us res) {
        return Glm.mul(res, (Vec4us) this, bX, bY, bZ, bW);
    }

    public Vec4us mul(final Vec4us b, final Vec4us res) {
        return Glm.mul(res, (Vec4us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public Vec4us div_(final UShort b) {
        return Glm.div(new Vec4us(), (Vec4us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec4us div_(final short b) {
        return Glm.div(new Vec4us(), (Vec4us) this, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec4us div_(final int b) {
        return Glm.div(new Vec4us(), (Vec4us) this, b, b, b, b);
    }

    public Vec4us div_(final UShort bX, final UShort bY, final UShort bZ, final UShort bW) {
        return Glm.div(new Vec4us(), (Vec4us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public Vec4us div_(final short bX, final short bY, final short bZ, final short bW) {
        return Glm.div(new Vec4us(), (Vec4us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public Vec4us div_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.div(new Vec4us(), (Vec4us) this, bX, bY, bZ, bW);
    }

    public Vec4us div_(final Vec4us b) {
        return Glm.div(new Vec4us(), (Vec4us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public Vec4us div(final UShort b) {
        return Glm.div((Vec4us) this, (Vec4us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec4us div(final short b) {
        return Glm.div((Vec4us) this, (Vec4us) this, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec4us div(final int b) {
        return Glm.div((Vec4us) this, (Vec4us) this, b, b, b, b);
    }

    public Vec4us div(final UShort bX, final UShort bY, final UShort bZ, final UShort bW) {
        return Glm.div((Vec4us) this, (Vec4us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public Vec4us div(final short bX, final short bY, final short bZ, final short bW) {
        return Glm.div((Vec4us) this, (Vec4us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public Vec4us div(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.div((Vec4us) this, (Vec4us) this, bX, bY, bZ, bW);
    }

    public Vec4us div(final Vec4us b) {
        return Glm.div((Vec4us) this, (Vec4us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public Vec4us div(final UShort b, final Vec4us res) {
        return Glm.div(res, (Vec4us) this, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec4us div(final short b, final Vec4us res) {
        return Glm.div(res, (Vec4us) this, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public Vec4us div(final int b, final Vec4us res) {
        return Glm.div(res, (Vec4us) this, b, b, b, b);
    }

    public Vec4us div(final UShort bX, final UShort bY, final UShort bZ, final UShort bW, final Vec4us res) {
        return Glm.div(res, (Vec4us) this, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public Vec4us div(final short bX, final short bY, final short bZ, final short bW, final Vec4us res) {
        return Glm.div(res, (Vec4us) this, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public Vec4us div(final int bX, final int bY, final int bZ, final int bW, final Vec4us res) {
        return Glm.div(res, (Vec4us) this, bX, bY, bZ, bW);
    }

    public Vec4us div(final Vec4us b, final Vec4us res) {
        return Glm.div(res, (Vec4us) this, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public Vec4us incr_() {
        return Glm.incr_((Vec4us) this);
    }

    public Vec4us incr() {
        return Glm.incr((Vec4us) this);
    }

    public Vec4us incr(final Vec4us res) {
        return Glm.incr(res, (Vec4us) this);
    }

    public Vec4us decr_() {
        return Glm.decr_((Vec4us) this);
    }

    public Vec4us decr() {
        return Glm.decr((Vec4us) this);
    }

    public Vec4us decr(final Vec4us res) {
        return Glm.decr(res, (Vec4us) this);
    }
}
