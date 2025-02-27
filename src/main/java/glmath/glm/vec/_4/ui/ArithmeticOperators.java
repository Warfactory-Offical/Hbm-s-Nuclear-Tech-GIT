/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.ui;

import glmath.glm.Glm;
import glmath.joou.UInt;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 4 * Integer.BYTES;

    public UInt x = new UInt(), y = new UInt(), z = new UInt(), w = new UInt();

    public Vec4ui add_(final UInt b) {
        return Glm.add(new Vec4ui(), (Vec4ui) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ui add_(final int b) {
        return Glm.add(new Vec4ui(), (Vec4ui) this, b, b, b, b);
    }

    public Vec4ui add_(final UInt bX, final UInt bY, final UInt bZ, final UInt bW) {
        return Glm.add(new Vec4ui(), (Vec4ui) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ui add_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.add(new Vec4ui(), (Vec4ui) this, bX, bY, bZ, bW);
    }

    public Vec4ui add_(final Vec4ui b) {
        return Glm.add(new Vec4ui(), (Vec4ui) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ui add(final UInt b) {
        return Glm.add((Vec4ui) this, (Vec4ui) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ui add(final int b) {
        return Glm.add((Vec4ui) this, (Vec4ui) this, b, b, b, b);
    }

    public Vec4ui add(final UInt bX, final UInt bY, final UInt bZ, final UInt bW) {
        return Glm.add((Vec4ui) this, (Vec4ui) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ui add(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.add((Vec4ui) this, (Vec4ui) this, bX, bY, bZ, bW);
    }

    public Vec4ui add(final Vec4ui b) {
        return Glm.add((Vec4ui) this, (Vec4ui) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ui add(final UInt b, final Vec4ui res) {
        return Glm.add(res, (Vec4ui) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ui add(final int b, final Vec4ui res) {
        return Glm.add(res, (Vec4ui) this, b, b, b, b);
    }

    public Vec4ui add(final UInt bX, final UInt bY, final UInt bZ, final UInt bW, final Vec4ui res) {
        return Glm.add(res, (Vec4ui) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ui add(final int bX, final int bY, final int bZ, final int bW, final Vec4ui res) {
        return Glm.add(res, (Vec4ui) this, bX, bY, bZ, bW);
    }

    public Vec4ui add(final Vec4ui b, final Vec4ui res) {
        return Glm.add(res, (Vec4ui) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ui sub_(final UInt b) {
        return Glm.sub(new Vec4ui(), (Vec4ui) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ui sub_(final int b) {
        return Glm.sub(new Vec4ui(), (Vec4ui) this, b, b, b, b);
    }

    public Vec4ui sub_(final UInt bX, final UInt bY, final UInt bZ, final UInt bW) {
        return Glm.sub(new Vec4ui(), (Vec4ui) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ui sub_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.sub(new Vec4ui(), (Vec4ui) this, bX, bY, bZ, bW);
    }

    public Vec4ui sub_(final Vec4ui b) {
        return Glm.sub(new Vec4ui(), (Vec4ui) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ui sub(final UInt b) {
        return Glm.sub((Vec4ui) this, (Vec4ui) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ui sub(final int b) {
        return Glm.sub((Vec4ui) this, (Vec4ui) this, b, b, b, b);
    }

    public Vec4ui sub(final UInt bX, final UInt bY, final UInt bZ, final UInt bW) {
        return Glm.sub((Vec4ui) this, (Vec4ui) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ui sub(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.sub((Vec4ui) this, (Vec4ui) this, bX, bY, bZ, bW);
    }

    public Vec4ui sub(final Vec4ui b) {
        return Glm.sub((Vec4ui) this, (Vec4ui) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ui sub(final UInt b, final Vec4ui res) {
        return Glm.sub(res, (Vec4ui) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ui sub(final int b, final Vec4ui res) {
        return Glm.sub(res, (Vec4ui) this, b, b, b, b);
    }

    public Vec4ui sub(final UInt bX, final UInt bY, final UInt bZ, final UInt bW, final Vec4ui res) {
        return Glm.sub(res, (Vec4ui) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ui sub(final int bX, final int bY, final int bZ, final int bW, final Vec4ui res) {
        return Glm.sub(res, (Vec4ui) this, bX, bY, bZ, bW);
    }

    public Vec4ui sub(final Vec4ui b, final Vec4ui res) {
        return Glm.sub(res, (Vec4ui) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ui mul_(final UInt b) {
        return Glm.mul(new Vec4ui(), (Vec4ui) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ui mul_(final int b) {
        return Glm.mul(new Vec4ui(), (Vec4ui) this, b, b, b, b);
    }

    public Vec4ui mul_(final UInt bX, final UInt bY, final UInt bZ, final UInt bW) {
        return Glm.mul(new Vec4ui(), (Vec4ui) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ui mul_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.mul(new Vec4ui(), (Vec4ui) this, bX, bY, bZ, bW);
    }

    public Vec4ui mul_(final Vec4ui b) {
        return Glm.mul(new Vec4ui(), (Vec4ui) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ui mul(final UInt b) {
        return Glm.mul((Vec4ui) this, (Vec4ui) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ui mul(final int b) {
        return Glm.mul((Vec4ui) this, (Vec4ui) this, b, b, b, b);
    }

    public Vec4ui mul(final UInt bX, final UInt bY, final UInt bZ, final UInt bW) {
        return Glm.mul((Vec4ui) this, (Vec4ui) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ui mul(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.mul((Vec4ui) this, (Vec4ui) this, bX, bY, bZ, bW);
    }

    public Vec4ui mul(final Vec4ui b) {
        return Glm.mul((Vec4ui) this, (Vec4ui) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ui mul(final UInt b, final Vec4ui res) {
        return Glm.mul(res, (Vec4ui) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ui mul(final int b, final Vec4ui res) {
        return Glm.mul(res, (Vec4ui) this, b, b, b, b);
    }

    public Vec4ui mul(final UInt bX, final UInt bY, final UInt bZ, final UInt bW, final Vec4ui res) {
        return Glm.mul(res, (Vec4ui) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ui mul(final int bX, final int bY, final int bZ, final int bW, final Vec4ui res) {
        return Glm.mul(res, (Vec4ui) this, bX, bY, bZ, bW);
    }

    public Vec4ui mul(final Vec4ui b, final Vec4ui res) {
        return Glm.mul(res, (Vec4ui) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ui div_(final UInt b) {
        return Glm.div(new Vec4ui(), (Vec4ui) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ui div_(final int b) {
        return Glm.div(new Vec4ui(), (Vec4ui) this, b, b, b, b);
    }

    public Vec4ui div_(final UInt bX, final UInt bY, final UInt bZ, final UInt bW) {
        return Glm.div(new Vec4ui(), (Vec4ui) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ui div_(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.div(new Vec4ui(), (Vec4ui) this, bX, bY, bZ, bW);
    }

    public Vec4ui div_(final Vec4ui b) {
        return Glm.div(new Vec4ui(), (Vec4ui) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ui div(final UInt b) {
        return Glm.div((Vec4ui) this, (Vec4ui) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ui div(final int b) {
        return Glm.div((Vec4ui) this, (Vec4ui) this, b, b, b, b);
    }

    public Vec4ui div(final UInt bX, final UInt bY, final UInt bZ, final UInt bW) {
        return Glm.div((Vec4ui) this, (Vec4ui) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ui div(final int bX, final int bY, final int bZ, final int bW) {
        return Glm.div((Vec4ui) this, (Vec4ui) this, bX, bY, bZ, bW);
    }

    public Vec4ui div(final Vec4ui b) {
        return Glm.div((Vec4ui) this, (Vec4ui) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ui div(final UInt b, final Vec4ui res) {
        return Glm.div(res, (Vec4ui) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ui div(final int b, final Vec4ui res) {
        return Glm.div(res, (Vec4ui) this, b, b, b, b);
    }

    public Vec4ui div(final UInt bX, final UInt bY, final UInt bZ, final UInt bW, final Vec4ui res) {
        return Glm.div(res, (Vec4ui) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ui div(final int bX, final int bY, final int bZ, final int bW, final Vec4ui res) {
        return Glm.div(res, (Vec4ui) this, bX, bY, bZ, bW);
    }

    public Vec4ui div(final Vec4ui b, final Vec4ui res) {
        return Glm.div(res, (Vec4ui) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ui incr_() {
        return Glm.incr_((Vec4ui) this);
    }

    public Vec4ui incr() {
        return Glm.incr((Vec4ui) this);
    }

    public Vec4ui incr(final Vec4ui res) {
        return Glm.incr(res, (Vec4ui) this);
    }

    public Vec4ui decr_() {
        return Glm.decr_((Vec4ui) this);
    }

    public Vec4ui decr() {
        return Glm.decr((Vec4ui) this);
    }

    public Vec4ui decr(final Vec4ui res) {
        return Glm.decr(res, (Vec4ui) this);
    }
}
