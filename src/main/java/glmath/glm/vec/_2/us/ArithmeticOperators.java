/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2.us;

import glmath.glm.Glm;
import glmath.joou.UShort;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 2 * Short.BYTES;

    public UShort x = new UShort(), y = new UShort();

    public Vec2us add_(final UShort b) {
        return Glm.add(new Vec2us(), (Vec2us) this, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec2us add_(final short b) {
        return Glm.add(new Vec2us(), (Vec2us) this, b & 0xffff, b & 0xffff);
    }

    public Vec2us add_(final int b) {
        return Glm.add(new Vec2us(), (Vec2us) this, b, b);
    }

    public Vec2us add_(final UShort bX, final UShort bY) {
        return Glm.add(new Vec2us(), (Vec2us) this, bX.value & 0xffff, bY.value & 0xdd);
    }

    public Vec2us add_(final short bX, final short bY) {
        return Glm.add(new Vec2us(), (Vec2us) this, bX & 0xffff, bY & 0xffff);
    }

    public Vec2us add_(final int bX, final int bY) {
        return Glm.add(new Vec2us(), (Vec2us) this, bX, bY);
    }

    public Vec2us add_(final Vec2us b) {
        return Glm.add(new Vec2us(), (Vec2us) this, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public Vec2us add(final UShort b) {
        return Glm.add((Vec2us) this, (Vec2us) this, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec2us add(final short b) {
        return Glm.add((Vec2us) this, (Vec2us) this, b & 0xffff, b & 0xffff);
    }

    public Vec2us add(final int b) {
        return Glm.add((Vec2us) this, (Vec2us) this, b, b);
    }

    public Vec2us add(final UShort bX, final UShort bY) {
        return Glm.add((Vec2us) this, (Vec2us) this, bX.value & 0xffff, bY.value & 0xdd);
    }

    public Vec2us add(final short bX, final short bY) {
        return Glm.add((Vec2us) this, (Vec2us) this, bX & 0xffff, bY & 0xffff);
    }

    public Vec2us add(final int bX, final int bY) {
        return Glm.add((Vec2us) this, (Vec2us) this, bX, bY);
    }

    public Vec2us add(final Vec2us b) {
        return Glm.add((Vec2us) this, (Vec2us) this, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public Vec2us add(final UShort b, final Vec2us res) {
        return Glm.add(res, (Vec2us) this, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec2us add(final short b, final Vec2us res) {
        return Glm.add(res, (Vec2us) this, b & 0xffff, b & 0xffff);
    }

    public Vec2us add(final int b, final Vec2us res) {
        return Glm.add(res, (Vec2us) this, b, b);
    }

    public Vec2us add(final UShort bX, final UShort bY, final Vec2us res) {
        return Glm.add(res, (Vec2us) this, bX.value & 0xffff, bY.value & 0xdd);
    }

    public Vec2us add(final short bX, final short bY, final Vec2us res) {
        return Glm.add(res, (Vec2us) this, bX & 0xffff, bY & 0xffff);
    }

    public Vec2us add(final int bX, final int bY, final Vec2us res) {
        return Glm.add(res, (Vec2us) this, bX, bY);
    }

    public Vec2us add(final Vec2us b, final Vec2us res) {
        return Glm.add(res, (Vec2us) this, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public Vec2us sub_(final UShort b) {
        return Glm.sub(new Vec2us(), (Vec2us) this, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec2us sub_(final short b) {
        return Glm.sub(new Vec2us(), (Vec2us) this, b & 0xffff, b & 0xffff);
    }

    public Vec2us sub_(final int b) {
        return Glm.sub(new Vec2us(), (Vec2us) this, b, b);
    }

    public Vec2us sub_(final UShort bX, final UShort bY) {
        return Glm.sub(new Vec2us(), (Vec2us) this, bX.value & 0xffff, bY.value & 0xdd);
    }

    public Vec2us sub_(final short bX, final short bY) {
        return Glm.sub(new Vec2us(), (Vec2us) this, bX & 0xffff, bY & 0xffff);
    }

    public Vec2us sub_(final int bX, final int bY) {
        return Glm.sub(new Vec2us(), (Vec2us) this, bX, bY);
    }

    public Vec2us sub_(final Vec2us b) {
        return Glm.sub(new Vec2us(), (Vec2us) this, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public Vec2us sub(final UShort b) {
        return Glm.sub((Vec2us) this, (Vec2us) this, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec2us sub(final short b) {
        return Glm.sub((Vec2us) this, (Vec2us) this, b & 0xffff, b & 0xffff);
    }

    public Vec2us sub(final int b) {
        return Glm.sub((Vec2us) this, (Vec2us) this, b, b);
    }

    public Vec2us sub(final UShort bX, final UShort bY) {
        return Glm.sub((Vec2us) this, (Vec2us) this, bX.value & 0xffff, bY.value & 0xdd);
    }

    public Vec2us sub(final short bX, final short bY) {
        return Glm.sub((Vec2us) this, (Vec2us) this, bX & 0xffff, bY & 0xffff);
    }

    public Vec2us sub(final int bX, final int bY) {
        return Glm.sub((Vec2us) this, (Vec2us) this, bX, bY);
    }

    public Vec2us sub(final Vec2us b) {
        return Glm.sub((Vec2us) this, (Vec2us) this, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public Vec2us sub(final UShort b, final Vec2us res) {
        return Glm.sub(res, (Vec2us) this, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec2us sub(final short b, final Vec2us res) {
        return Glm.sub(res, (Vec2us) this, b & 0xffff, b & 0xffff);
    }

    public Vec2us sub(final int b, final Vec2us res) {
        return Glm.sub(res, (Vec2us) this, b, b);
    }

    public Vec2us sub(final UShort bX, final UShort bY, final Vec2us res) {
        return Glm.sub(res, (Vec2us) this, bX.value & 0xffff, bY.value & 0xdd);
    }

    public Vec2us sub(final short bX, final short bY, final Vec2us res) {
        return Glm.sub(res, (Vec2us) this, bX & 0xffff, bY & 0xffff);
    }

    public Vec2us sub(final int bX, final int bY, final Vec2us res) {
        return Glm.sub(res, (Vec2us) this, bX, bY);
    }

    public Vec2us sub(final Vec2us b, final Vec2us res) {
        return Glm.sub(res, (Vec2us) this, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public Vec2us mul_(final UShort b) {
        return Glm.mul(new Vec2us(), (Vec2us) this, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec2us mul_(final short b) {
        return Glm.mul(new Vec2us(), (Vec2us) this, b & 0xffff, b & 0xffff);
    }

    public Vec2us mul_(final int b) {
        return Glm.mul(new Vec2us(), (Vec2us) this, b, b);
    }

    public Vec2us mul_(final UShort bX, final UShort bY) {
        return Glm.mul(new Vec2us(), (Vec2us) this, bX.value & 0xffff, bY.value & 0xdd);
    }

    public Vec2us mul_(final short bX, final short bY) {
        return Glm.mul(new Vec2us(), (Vec2us) this, bX & 0xffff, bY & 0xffff);
    }

    public Vec2us mul_(final int bX, final int bY) {
        return Glm.mul(new Vec2us(), (Vec2us) this, bX, bY);
    }

    public Vec2us mul_(final Vec2us b) {
        return Glm.mul(new Vec2us(), (Vec2us) this, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public Vec2us mul(final UShort b) {
        return Glm.mul((Vec2us) this, (Vec2us) this, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec2us mul(final short b) {
        return Glm.mul((Vec2us) this, (Vec2us) this, b & 0xffff, b & 0xffff);
    }

    public Vec2us mul(final int b) {
        return Glm.mul((Vec2us) this, (Vec2us) this, b, b);
    }

    public Vec2us mul(final UShort bX, final UShort bY) {
        return Glm.mul((Vec2us) this, (Vec2us) this, bX.value & 0xffff, bY.value & 0xdd);
    }

    public Vec2us mul(final short bX, final short bY) {
        return Glm.mul((Vec2us) this, (Vec2us) this, bX & 0xffff, bY & 0xffff);
    }

    public Vec2us mul(final int bX, final int bY) {
        return Glm.mul((Vec2us) this, (Vec2us) this, bX, bY);
    }

    public Vec2us mul(final Vec2us b) {
        return Glm.mul((Vec2us) this, (Vec2us) this, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public Vec2us mul(final UShort b, final Vec2us res) {
        return Glm.mul(res, (Vec2us) this, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec2us mul(final short b, final Vec2us res) {
        return Glm.mul(res, (Vec2us) this, b & 0xffff, b & 0xffff);
    }

    public Vec2us mul(final int b, final Vec2us res) {
        return Glm.mul(res, (Vec2us) this, b, b);
    }

    public Vec2us mul(final UShort bX, final UShort bY, final Vec2us res) {
        return Glm.mul(res, (Vec2us) this, bX.value & 0xffff, bY.value & 0xdd);
    }

    public Vec2us mul(final short bX, final short bY, final Vec2us res) {
        return Glm.mul(res, (Vec2us) this, bX & 0xffff, bY & 0xffff);
    }

    public Vec2us mul(final int bX, final int bY, final Vec2us res) {
        return Glm.mul(res, (Vec2us) this, bX, bY);
    }

    public Vec2us mul(final Vec2us b, final Vec2us res) {
        return Glm.mul(res, (Vec2us) this, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public Vec2us div_(final UShort b) {
        return Glm.div(new Vec2us(), (Vec2us) this, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec2us div_(final short b) {
        return Glm.div(new Vec2us(), (Vec2us) this, b & 0xffff, b & 0xffff);
    }

    public Vec2us div_(final int b) {
        return Glm.div(new Vec2us(), (Vec2us) this, b, b);
    }

    public Vec2us div_(final UShort bX, final UShort bY) {
        return Glm.div(new Vec2us(), (Vec2us) this, bX.value & 0xffff, bY.value & 0xdd);
    }

    public Vec2us div_(final short bX, final short bY) {
        return Glm.div(new Vec2us(), (Vec2us) this, bX & 0xffff, bY & 0xffff);
    }

    public Vec2us div_(final int bX, final int bY) {
        return Glm.div(new Vec2us(), (Vec2us) this, bX, bY);
    }

    public Vec2us div_(final Vec2us b) {
        return Glm.div(new Vec2us(), (Vec2us) this, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public Vec2us div(final UShort b) {
        return Glm.div((Vec2us) this, (Vec2us) this, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec2us div(final short b) {
        return Glm.div((Vec2us) this, (Vec2us) this, b & 0xffff, b & 0xffff);
    }

    public Vec2us div(final int b) {
        return Glm.div((Vec2us) this, (Vec2us) this, b, b);
    }

    public Vec2us div(final UShort bX, final UShort bY) {
        return Glm.div((Vec2us) this, (Vec2us) this, bX.value & 0xffff, bY.value & 0xdd);
    }

    public Vec2us div(final short bX, final short bY) {
        return Glm.div((Vec2us) this, (Vec2us) this, bX & 0xffff, bY & 0xffff);
    }

    public Vec2us div(final int bX, final int bY) {
        return Glm.div((Vec2us) this, (Vec2us) this, bX, bY);
    }

    public Vec2us div(final Vec2us b) {
        return Glm.div((Vec2us) this, (Vec2us) this, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public Vec2us div(final UShort b, final Vec2us res) {
        return Glm.div(res, (Vec2us) this, b.value & 0xffff, b.value & 0xffff);
    }

    public Vec2us div(final short b, final Vec2us res) {
        return Glm.div(res, (Vec2us) this, b & 0xffff, b & 0xffff);
    }

    public Vec2us div(final int b, final Vec2us res) {
        return Glm.div(res, (Vec2us) this, b, b);
    }

    public Vec2us div(final UShort bX, final UShort bY, final Vec2us res) {
        return Glm.div(res, (Vec2us) this, bX.value & 0xffff, bY.value & 0xdd);
    }

    public Vec2us div(final short bX, final short bY, final Vec2us res) {
        return Glm.div(res, (Vec2us) this, bX & 0xffff, bY & 0xffff);
    }

    public Vec2us div(final int bX, final int bY, final Vec2us res) {
        return Glm.div(res, (Vec2us) this, bX, bY);
    }

    public Vec2us div(final Vec2us b, final Vec2us res) {
        return Glm.div(res, (Vec2us) this, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public Vec2us incr_() {
        return Glm.incr_((Vec2us) this);
    }

    public Vec2us incr() {
        return Glm.incr((Vec2us) this);
    }

    public Vec2us incr(final Vec2us res) {
        return Glm.incr(res, (Vec2us) this);
    }

    public Vec2us decr_() {
        return Glm.decr_((Vec2us) this);
    }

    public Vec2us decr() {
        return Glm.decr((Vec2us) this);
    }

    public Vec2us decr(final Vec2us res) {
        return Glm.decr(res, (Vec2us) this);
    }
}
