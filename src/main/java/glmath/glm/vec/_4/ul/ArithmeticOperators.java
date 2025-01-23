/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.ul;

import glmath.glm.Glm;
import glmath.joou.ULong;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 4 * Long.BYTES;

    public ULong x = new ULong(), y = new ULong(), z = new ULong(), w = new ULong();

    public Vec4ul add_(final ULong b) {
        return Glm.add(new Vec4ul(), (Vec4ul) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ul add_(final long b) {
        return Glm.add(new Vec4ul(), (Vec4ul) this, b, b, b, b);
    }

    public Vec4ul add_(final ULong bX, final ULong bY, final ULong bZ, final ULong bW) {
        return Glm.add(new Vec4ul(), (Vec4ul) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ul add_(final long bX, final long bY, final long bZ, final long bW) {
        return Glm.add(new Vec4ul(), (Vec4ul) this, bX, bY, bZ, bW);
    }

    public Vec4ul add_(final Vec4ul b) {
        return Glm.add(new Vec4ul(), (Vec4ul) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ul add(final ULong b) {
        return Glm.add((Vec4ul) this, (Vec4ul) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ul add(final long b) {
        return Glm.add((Vec4ul) this, (Vec4ul) this, b, b, b, b);
    }

    public Vec4ul add(final ULong bX, final ULong bY, final ULong bZ, final ULong bW) {
        return Glm.add((Vec4ul) this, (Vec4ul) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ul add(final long bX, final long bY, final long bZ, final long bW) {
        return Glm.add((Vec4ul) this, (Vec4ul) this, bX, bY, bZ, bW);
    }

    public Vec4ul add(final Vec4ul b) {
        return Glm.add((Vec4ul) this, (Vec4ul) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ul add(final ULong b, final Vec4ul res) {
        return Glm.add(res, (Vec4ul) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ul add(final long b, final Vec4ul res) {
        return Glm.add(res, (Vec4ul) this, b, b, b, b);
    }

    public Vec4ul add(final ULong bX, final ULong bY, final ULong bZ, final ULong bW, final Vec4ul res) {
        return Glm.add(res, (Vec4ul) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ul add(final long bX, final long bY, final long bZ, final long bW, final Vec4ul res) {
        return Glm.add(res, (Vec4ul) this, bX, bY, bZ, bW);
    }

    public Vec4ul add(final Vec4ul b, final Vec4ul res) {
        return Glm.add(res, (Vec4ul) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ul sub_(final ULong b) {
        return Glm.sub(new Vec4ul(), (Vec4ul) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ul sub_(final long b) {
        return Glm.sub(new Vec4ul(), (Vec4ul) this, b, b, b, b);
    }

    public Vec4ul sub_(final ULong bX, final ULong bY, final ULong bZ, final ULong bW) {
        return Glm.sub(new Vec4ul(), (Vec4ul) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ul sub_(final long bX, final long bY, final long bZ, final long bW) {
        return Glm.sub(new Vec4ul(), (Vec4ul) this, bX, bY, bZ, bW);
    }

    public Vec4ul sub_(final Vec4ul b) {
        return Glm.sub(new Vec4ul(), (Vec4ul) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ul sub(final ULong b) {
        return Glm.sub((Vec4ul) this, (Vec4ul) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ul sub(final long b) {
        return Glm.sub((Vec4ul) this, (Vec4ul) this, b, b, b, b);
    }

    public Vec4ul sub(final ULong bX, final ULong bY, final ULong bZ, final ULong bW) {
        return Glm.sub((Vec4ul) this, (Vec4ul) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ul sub(final long bX, final long bY, final long bZ, final long bW) {
        return Glm.sub((Vec4ul) this, (Vec4ul) this, bX, bY, bZ, bW);
    }

    public Vec4ul sub(final Vec4ul b) {
        return Glm.sub((Vec4ul) this, (Vec4ul) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ul sub(final ULong b, final Vec4ul res) {
        return Glm.sub(res, (Vec4ul) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ul sub(final long b, final Vec4ul res) {
        return Glm.sub(res, (Vec4ul) this, b, b, b, b);
    }

    public Vec4ul sub(final ULong bX, final ULong bY, final ULong bZ, final ULong bW, final Vec4ul res) {
        return Glm.sub(res, (Vec4ul) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ul sub(final long bX, final long bY, final long bZ, final long bW, final Vec4ul res) {
        return Glm.sub(res, (Vec4ul) this, bX, bY, bZ, bW);
    }

    public Vec4ul sub(final Vec4ul b, final Vec4ul res) {
        return Glm.sub(res, (Vec4ul) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ul mul_(final ULong b) {
        return Glm.mul(new Vec4ul(), (Vec4ul) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ul mul_(final long b) {
        return Glm.mul(new Vec4ul(), (Vec4ul) this, b, b, b, b);
    }

    public Vec4ul mul_(final ULong bX, final ULong bY, final ULong bZ, final ULong bW) {
        return Glm.mul(new Vec4ul(), (Vec4ul) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ul mul_(final long bX, final long bY, final long bZ, final long bW) {
        return Glm.mul(new Vec4ul(), (Vec4ul) this, bX, bY, bZ, bW);
    }

    public Vec4ul mul_(final Vec4ul b) {
        return Glm.mul(new Vec4ul(), (Vec4ul) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ul mul(final ULong b) {
        return Glm.mul((Vec4ul) this, (Vec4ul) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ul mul(final long b) {
        return Glm.mul((Vec4ul) this, (Vec4ul) this, b, b, b, b);
    }

    public Vec4ul mul(final ULong bX, final ULong bY, final ULong bZ, final ULong bW) {
        return Glm.mul((Vec4ul) this, (Vec4ul) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ul mul(final long bX, final long bY, final long bZ, final long bW) {
        return Glm.mul((Vec4ul) this, (Vec4ul) this, bX, bY, bZ, bW);
    }

    public Vec4ul mul(final Vec4ul b) {
        return Glm.mul((Vec4ul) this, (Vec4ul) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ul mul(final ULong b, final Vec4ul res) {
        return Glm.mul(res, (Vec4ul) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ul mul(final long b, final Vec4ul res) {
        return Glm.mul(res, (Vec4ul) this, b, b, b, b);
    }

    public Vec4ul mul(final ULong bX, final ULong bY, final ULong bZ, final ULong bW, final Vec4ul res) {
        return Glm.mul(res, (Vec4ul) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ul mul(final long bX, final long bY, final long bZ, final long bW, final Vec4ul res) {
        return Glm.mul(res, (Vec4ul) this, bX, bY, bZ, bW);
    }

    public Vec4ul mul(final Vec4ul b, final Vec4ul res) {
        return Glm.mul(res, (Vec4ul) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ul div_(final ULong b) {
        return Glm.div(new Vec4ul(), (Vec4ul) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ul div_(final long b) {
        return Glm.div(new Vec4ul(), (Vec4ul) this, b, b, b, b);
    }

    public Vec4ul div_(final ULong bX, final ULong bY, final ULong bZ, final ULong bW) {
        return Glm.div(new Vec4ul(), (Vec4ul) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ul div_(final long bX, final long bY, final long bZ, final long bW) {
        return Glm.div(new Vec4ul(), (Vec4ul) this, bX, bY, bZ, bW);
    }

    public Vec4ul div_(final Vec4ul b) {
        return Glm.div(new Vec4ul(), (Vec4ul) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ul div(final ULong b) {
        return Glm.div((Vec4ul) this, (Vec4ul) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ul div(final long b) {
        return Glm.div((Vec4ul) this, (Vec4ul) this, b, b, b, b);
    }

    public Vec4ul div(final ULong bX, final ULong bY, final ULong bZ, final ULong bW) {
        return Glm.div((Vec4ul) this, (Vec4ul) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ul div(final long bX, final long bY, final long bZ, final long bW) {
        return Glm.div((Vec4ul) this, (Vec4ul) this, bX, bY, bZ, bW);
    }

    public Vec4ul div(final Vec4ul b) {
        return Glm.div((Vec4ul) this, (Vec4ul) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ul div(final ULong b, final Vec4ul res) {
        return Glm.div(res, (Vec4ul) this, b.value, b.value, b.value, b.value);
    }

    public Vec4ul div(final long b, final Vec4ul res) {
        return Glm.div(res, (Vec4ul) this, b, b, b, b);
    }

    public Vec4ul div(final ULong bX, final ULong bY, final ULong bZ, final ULong bW, final Vec4ul res) {
        return Glm.div(res, (Vec4ul) this, bX.value, bY.value, bZ.value, bW.value);
    }

    public Vec4ul div(final long bX, final long bY, final long bZ, final long bW, final Vec4ul res) {
        return Glm.div(res, (Vec4ul) this, bX, bY, bZ, bW);
    }

    public Vec4ul div(final Vec4ul b, final Vec4ul res) {
        return Glm.div(res, (Vec4ul) this, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public Vec4ul incr_() {
        return Glm.incr_((Vec4ul) this);
    }

    public Vec4ul incr() {
        return Glm.incr((Vec4ul) this);
    }

    public Vec4ul incr(final Vec4ul res) {
        return Glm.incr(res, (Vec4ul) this);
    }

    public Vec4ul decr_() {
        return Glm.decr_((Vec4ul) this);
    }

    public Vec4ul decr() {
        return Glm.decr((Vec4ul) this);
    }

    public Vec4ul decr(final Vec4ul res) {
        return Glm.decr(res, (Vec4ul) this);
    }
}
