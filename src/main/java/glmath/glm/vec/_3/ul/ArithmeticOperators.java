/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.ul;

import glmath.glm.Glm;
import glmath.joou.ULong;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 3 * Long.BYTES;

    public ULong x = new ULong(), y = new ULong(), z = new ULong();

    public Vec3ul add_(final ULong b) {
        return Glm.add(new Vec3ul(), (Vec3ul) this, b.value, b.value, b.value);
    }

    public Vec3ul add_(final long b) {
        return Glm.add(new Vec3ul(), (Vec3ul) this, b, b, b);
    }

    public Vec3ul add_(final ULong bX, final ULong bY, final ULong bZ) {
        return Glm.add(new Vec3ul(), (Vec3ul) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ul add_(final long bX, final long bY, final long bZ) {
        return Glm.add(new Vec3ul(), (Vec3ul) this, bX, bY, bZ);
    }

    public Vec3ul add_(final Vec3ul b) {
        return Glm.add(new Vec3ul(), (Vec3ul) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ul add(final ULong b) {
        return Glm.add((Vec3ul) this, (Vec3ul) this, b.value, b.value, b.value);
    }

    public Vec3ul add(final long b) {
        return Glm.add((Vec3ul) this, (Vec3ul) this, b, b, b);
    }

    public Vec3ul add(final ULong bX, final ULong bY, final ULong bZ) {
        return Glm.add((Vec3ul) this, (Vec3ul) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ul add(final long bX, final long bY, final long bZ) {
        return Glm.add((Vec3ul) this, (Vec3ul) this, bX, bY, bZ);
    }

    public Vec3ul add(final Vec3ul b) {
        return Glm.add((Vec3ul) this, (Vec3ul) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ul add(final ULong b, final Vec3ul res) {
        return Glm.add(res, (Vec3ul) this, b.value, b.value, b.value);
    }

    public Vec3ul add(final long b, final Vec3ul res) {
        return Glm.add(res, (Vec3ul) this, b, b, b);
    }

    public Vec3ul add(final ULong bX, final ULong bY, final ULong bZ, final Vec3ul res) {
        return Glm.add(res, (Vec3ul) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ul add(final long bX, final long bY, final long bZ, final Vec3ul res) {
        return Glm.add(res, (Vec3ul) this, bX, bY, bZ);
    }

    public Vec3ul add(final Vec3ul b, final Vec3ul res) {
        return Glm.add(res, (Vec3ul) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ul sub_(final ULong b) {
        return Glm.sub(new Vec3ul(), (Vec3ul) this, b.value, b.value, b.value);
    }

    public Vec3ul sub_(final long b) {
        return Glm.sub(new Vec3ul(), (Vec3ul) this, b, b, b);
    }

    public Vec3ul sub_(final ULong bX, final ULong bY, final ULong bZ) {
        return Glm.sub(new Vec3ul(), (Vec3ul) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ul sub_(final long bX, final long bY, final long bZ) {
        return Glm.sub(new Vec3ul(), (Vec3ul) this, bX, bY, bZ);
    }

    public Vec3ul sub_(final Vec3ul b) {
        return Glm.sub(new Vec3ul(), (Vec3ul) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ul sub(final ULong b) {
        return Glm.sub((Vec3ul) this, (Vec3ul) this, b.value, b.value, b.value);
    }

    public Vec3ul sub(final long b) {
        return Glm.sub((Vec3ul) this, (Vec3ul) this, b, b, b);
    }

    public Vec3ul sub(final ULong bX, final ULong bY, final ULong bZ) {
        return Glm.sub((Vec3ul) this, (Vec3ul) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ul sub(final long bX, final long bY, final long bZ) {
        return Glm.sub((Vec3ul) this, (Vec3ul) this, bX, bY, bZ);
    }

    public Vec3ul sub(final Vec3ul b) {
        return Glm.sub((Vec3ul) this, (Vec3ul) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ul sub(final ULong b, final Vec3ul res) {
        return Glm.sub(res, (Vec3ul) this, b.value, b.value, b.value);
    }

    public Vec3ul sub(final long b, final Vec3ul res) {
        return Glm.sub(res, (Vec3ul) this, b, b, b);
    }

    public Vec3ul sub(final ULong bX, final ULong bY, final ULong bZ, final Vec3ul res) {
        return Glm.sub(res, (Vec3ul) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ul sub(final long bX, final long bY, final long bZ, final Vec3ul res) {
        return Glm.sub(res, (Vec3ul) this, bX, bY, bZ);
    }

    public Vec3ul sub(final Vec3ul b, final Vec3ul res) {
        return Glm.sub(res, (Vec3ul) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ul mul_(final ULong b) {
        return Glm.mul(new Vec3ul(), (Vec3ul) this, b.value, b.value, b.value);
    }

    public Vec3ul mul_(final long b) {
        return Glm.mul(new Vec3ul(), (Vec3ul) this, b, b, b);
    }

    public Vec3ul mul_(final ULong bX, final ULong bY, final ULong bZ) {
        return Glm.mul(new Vec3ul(), (Vec3ul) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ul mul_(final long bX, final long bY, final long bZ) {
        return Glm.mul(new Vec3ul(), (Vec3ul) this, bX, bY, bZ);
    }

    public Vec3ul mul_(final Vec3ul b) {
        return Glm.mul(new Vec3ul(), (Vec3ul) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ul mul(final ULong b) {
        return Glm.mul((Vec3ul) this, (Vec3ul) this, b.value, b.value, b.value);
    }

    public Vec3ul mul(final long b) {
        return Glm.mul((Vec3ul) this, (Vec3ul) this, b, b, b);
    }

    public Vec3ul mul(final ULong bX, final ULong bY, final ULong bZ) {
        return Glm.mul((Vec3ul) this, (Vec3ul) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ul mul(final long bX, final long bY, final long bZ) {
        return Glm.mul((Vec3ul) this, (Vec3ul) this, bX, bY, bZ);
    }

    public Vec3ul mul(final Vec3ul b) {
        return Glm.mul((Vec3ul) this, (Vec3ul) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ul mul(final ULong b, final Vec3ul res) {
        return Glm.mul(res, (Vec3ul) this, b.value, b.value, b.value);
    }

    public Vec3ul mul(final long b, final Vec3ul res) {
        return Glm.mul(res, (Vec3ul) this, b, b, b);
    }

    public Vec3ul mul(final ULong bX, final ULong bY, final ULong bZ, final Vec3ul res) {
        return Glm.mul(res, (Vec3ul) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ul mul(final long bX, final long bY, final long bZ, final Vec3ul res) {
        return Glm.mul(res, (Vec3ul) this, bX, bY, bZ);
    }

    public Vec3ul mul(final Vec3ul b, final Vec3ul res) {
        return Glm.mul(res, (Vec3ul) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ul div_(final ULong b) {
        return Glm.div(new Vec3ul(), (Vec3ul) this, b.value, b.value, b.value);
    }

    public Vec3ul div_(final long b) {
        return Glm.div(new Vec3ul(), (Vec3ul) this, b, b, b);
    }

    public Vec3ul div_(final ULong bX, final ULong bY, final ULong bZ) {
        return Glm.div(new Vec3ul(), (Vec3ul) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ul div_(final long bX, final long bY, final long bZ) {
        return Glm.div(new Vec3ul(), (Vec3ul) this, bX, bY, bZ);
    }

    public Vec3ul div_(final Vec3ul b) {
        return Glm.div(new Vec3ul(), (Vec3ul) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ul div(final ULong b) {
        return Glm.div((Vec3ul) this, (Vec3ul) this, b.value, b.value, b.value);
    }

    public Vec3ul div(final long b) {
        return Glm.div((Vec3ul) this, (Vec3ul) this, b, b, b);
    }

    public Vec3ul div(final ULong bX, final ULong bY, final ULong bZ) {
        return Glm.div((Vec3ul) this, (Vec3ul) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ul div(final long bX, final long bY, final long bZ) {
        return Glm.div((Vec3ul) this, (Vec3ul) this, bX, bY, bZ);
    }

    public Vec3ul div(final Vec3ul b) {
        return Glm.div((Vec3ul) this, (Vec3ul) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ul div(final ULong b, final Vec3ul res) {
        return Glm.div(res, (Vec3ul) this, b.value, b.value, b.value);
    }

    public Vec3ul div(final long b, final Vec3ul res) {
        return Glm.div(res, (Vec3ul) this, b, b, b);
    }

    public Vec3ul div(final ULong bX, final ULong bY, final ULong bZ, final Vec3ul res) {
        return Glm.div(res, (Vec3ul) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ul div(final long bX, final long bY, final long bZ, final Vec3ul res) {
        return Glm.div(res, (Vec3ul) this, bX, bY, bZ);
    }

    public Vec3ul div(final Vec3ul b, final Vec3ul res) {
        return Glm.div(res, (Vec3ul) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ul incr_() {
        return Glm.incr_((Vec3ul) this);
    }

    public Vec3ul incr() {
        return Glm.incr((Vec3ul) this);
    }

    public Vec3ul incr(final Vec3ul res) {
        return Glm.incr(res, (Vec3ul) this);
    }

    public Vec3ul decr_() {
        return Glm.decr_((Vec3ul) this);
    }

    public Vec3ul decr() {
        return Glm.decr((Vec3ul) this);
    }

    public Vec3ul decr(final Vec3ul res) {
        return Glm.decr(res, (Vec3ul) this);
    }
}
