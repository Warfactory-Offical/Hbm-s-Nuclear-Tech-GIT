/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2.ul;

import glmath.glm.Glm;
import glmath.joou.ULong;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 2 * Long.BYTES;

    public ULong x = new ULong(), y = new ULong();

    public Vec2ul add_(final ULong b) {
        return Glm.add(new Vec2ul(), (Vec2ul) this, b.value, b.value);
    }

    public Vec2ul add_(final long b) {
        return Glm.add(new Vec2ul(), (Vec2ul) this, b, b);
    }

    public Vec2ul add_(final ULong bX, final ULong bY) {
        return Glm.add(new Vec2ul(), (Vec2ul) this, bX.value, bY.value);
    }

    public Vec2ul add_(final long bX, final long bY) {
        return Glm.add(new Vec2ul(), (Vec2ul) this, bX, bY);
    }

    public Vec2ul add_(final Vec2ul b) {
        return Glm.add(new Vec2ul(), (Vec2ul) this, b.x.value, b.y.value);
    }

    public Vec2ul add(final ULong b) {
        return Glm.add((Vec2ul) this, (Vec2ul) this, b.value, b.value);
    }

    public Vec2ul add(final long b) {
        return Glm.add((Vec2ul) this, (Vec2ul) this, b, b);
    }

    public Vec2ul add(final ULong bX, final ULong bY) {
        return Glm.add((Vec2ul) this, (Vec2ul) this, bX.value, bY.value);
    }

    public Vec2ul add(final long bX, final long bY) {
        return Glm.add((Vec2ul) this, (Vec2ul) this, bX, bY);
    }

    public Vec2ul add(final Vec2ul b) {
        return Glm.add((Vec2ul) this, (Vec2ul) this, b.x.value, b.y.value);
    }

    public Vec2ul add(final ULong b, final Vec2ul res) {
        return Glm.add(res, (Vec2ul) this, b.value, b.value);
    }

    public Vec2ul add(final long b, final Vec2ul res) {
        return Glm.add(res, (Vec2ul) this, b, b);
    }

    public Vec2ul add(final ULong bX, final ULong bY, final Vec2ul res) {
        return Glm.add(res, (Vec2ul) this, bX.value, bY.value);
    }

    public Vec2ul add(final long bX, final long bY, final Vec2ul res) {
        return Glm.add(res, (Vec2ul) this, bX, bY);
    }

    public Vec2ul add(final Vec2ul b, final Vec2ul res) {
        return Glm.add(res, (Vec2ul) this, b.x.value, b.y.value);
    }

    public Vec2ul sub_(final ULong b) {
        return Glm.sub(new Vec2ul(), (Vec2ul) this, b.value, b.value);
    }

    public Vec2ul sub_(final long b) {
        return Glm.sub(new Vec2ul(), (Vec2ul) this, b, b);
    }

    public Vec2ul sub_(final ULong bX, final ULong bY) {
        return Glm.sub(new Vec2ul(), (Vec2ul) this, bX.value, bY.value);
    }

    public Vec2ul sub_(final long bX, final long bY) {
        return Glm.sub(new Vec2ul(), (Vec2ul) this, bX, bY);
    }

    public Vec2ul sub_(final Vec2ul b) {
        return Glm.sub(new Vec2ul(), (Vec2ul) this, b.x.value, b.y.value);
    }

    public Vec2ul sub(final ULong b) {
        return Glm.sub((Vec2ul) this, (Vec2ul) this, b.value, b.value);
    }

    public Vec2ul sub(final long b) {
        return Glm.sub((Vec2ul) this, (Vec2ul) this, b, b);
    }

    public Vec2ul sub(final ULong bX, final ULong bY) {
        return Glm.sub((Vec2ul) this, (Vec2ul) this, bX.value, bY.value);
    }

    public Vec2ul sub(final long bX, final long bY) {
        return Glm.sub((Vec2ul) this, (Vec2ul) this, bX, bY);
    }

    public Vec2ul sub(final Vec2ul b) {
        return Glm.sub((Vec2ul) this, (Vec2ul) this, b.x.value, b.y.value);
    }

    public Vec2ul sub(final ULong b, final Vec2ul res) {
        return Glm.sub(res, (Vec2ul) this, b.value, b.value);
    }

    public Vec2ul sub(final long b, final Vec2ul res) {
        return Glm.sub(res, (Vec2ul) this, b, b);
    }

    public Vec2ul sub(final ULong bX, final ULong bY, final Vec2ul res) {
        return Glm.sub(res, (Vec2ul) this, bX.value, bY.value);
    }

    public Vec2ul sub(final long bX, final long bY, final Vec2ul res) {
        return Glm.sub(res, (Vec2ul) this, bX, bY);
    }

    public Vec2ul sub(final Vec2ul b, final Vec2ul res) {
        return Glm.sub(res, (Vec2ul) this, b.x.value, b.y.value);
    }

    public Vec2ul mul_(final ULong b) {
        return Glm.mul(new Vec2ul(), (Vec2ul) this, b.value, b.value);
    }

    public Vec2ul mul_(final long b) {
        return Glm.mul(new Vec2ul(), (Vec2ul) this, b, b);
    }

    public Vec2ul mul_(final ULong bX, final ULong bY) {
        return Glm.mul(new Vec2ul(), (Vec2ul) this, bX.value, bY.value);
    }

    public Vec2ul mul_(final long bX, final long bY) {
        return Glm.mul(new Vec2ul(), (Vec2ul) this, bX, bY);
    }

    public Vec2ul mul_(final Vec2ul b) {
        return Glm.mul(new Vec2ul(), (Vec2ul) this, b.x.value, b.y.value);
    }

    public Vec2ul mul(final ULong b) {
        return Glm.mul((Vec2ul) this, (Vec2ul) this, b.value, b.value);
    }

    public Vec2ul mul(final long b) {
        return Glm.mul((Vec2ul) this, (Vec2ul) this, b, b);
    }

    public Vec2ul mul(final ULong bX, final ULong bY) {
        return Glm.mul((Vec2ul) this, (Vec2ul) this, bX.value, bY.value);
    }

    public Vec2ul mul(final long bX, final long bY) {
        return Glm.mul((Vec2ul) this, (Vec2ul) this, bX, bY);
    }

    public Vec2ul mul(final Vec2ul b) {
        return Glm.mul((Vec2ul) this, (Vec2ul) this, b.x.value, b.y.value);
    }

    public Vec2ul mul(final ULong b, final Vec2ul res) {
        return Glm.mul(res, (Vec2ul) this, b.value, b.value);
    }

    public Vec2ul mul(final long b, final Vec2ul res) {
        return Glm.mul(res, (Vec2ul) this, b, b);
    }

    public Vec2ul mul(final ULong bX, final ULong bY, final Vec2ul res) {
        return Glm.mul(res, (Vec2ul) this, bX.value, bY.value);
    }

    public Vec2ul mul(final long bX, final long bY, final Vec2ul res) {
        return Glm.mul(res, (Vec2ul) this, bX, bY);
    }

    public Vec2ul mul(final Vec2ul b, final Vec2ul res) {
        return Glm.mul(res, (Vec2ul) this, b.x.value, b.y.value);
    }

    public Vec2ul div_(final ULong b) {
        return Glm.div(new Vec2ul(), (Vec2ul) this, b.value, b.value);
    }

    public Vec2ul div_(final long b) {
        return Glm.div(new Vec2ul(), (Vec2ul) this, b, b);
    }

    public Vec2ul div_(final ULong bX, final ULong bY) {
        return Glm.div(new Vec2ul(), (Vec2ul) this, bX.value, bY.value);
    }

    public Vec2ul div_(final long bX, final long bY) {
        return Glm.div(new Vec2ul(), (Vec2ul) this, bX, bY);
    }

    public Vec2ul div_(final Vec2ul b) {
        return Glm.div(new Vec2ul(), (Vec2ul) this, b.x.value, b.y.value);
    }

    public Vec2ul div(final ULong b) {
        return Glm.div((Vec2ul) this, (Vec2ul) this, b.value, b.value);
    }

    public Vec2ul div(final long b) {
        return Glm.div((Vec2ul) this, (Vec2ul) this, b, b);
    }

    public Vec2ul div(final ULong bX, final ULong bY) {
        return Glm.div((Vec2ul) this, (Vec2ul) this, bX.value, bY.value);
    }

    public Vec2ul div(final long bX, final long bY) {
        return Glm.div((Vec2ul) this, (Vec2ul) this, bX, bY);
    }

    public Vec2ul div(final Vec2ul b) {
        return Glm.div((Vec2ul) this, (Vec2ul) this, b.x.value, b.y.value);
    }

    public Vec2ul div(final ULong b, final Vec2ul res) {
        return Glm.div(res, (Vec2ul) this, b.value, b.value);
    }

    public Vec2ul div(final long b, final Vec2ul res) {
        return Glm.div(res, (Vec2ul) this, b, b);
    }

    public Vec2ul div(final ULong bX, final ULong bY, final Vec2ul res) {
        return Glm.div(res, (Vec2ul) this, bX.value, bY.value);
    }

    public Vec2ul div(final long bX, final long bY, final Vec2ul res) {
        return Glm.div(res, (Vec2ul) this, bX, bY);
    }

    public Vec2ul div(final Vec2ul b, final Vec2ul res) {
        return Glm.div(res, (Vec2ul) this, b.x.value, b.y.value);
    }

    public Vec2ul incr_() {
        return Glm.incr_((Vec2ul) this);
    }

    public Vec2ul incr() {
        return Glm.incr((Vec2ul) this);
    }

    public Vec2ul incr(final Vec2ul res) {
        return Glm.incr(res, (Vec2ul) this);
    }

    public Vec2ul decr_() {
        return Glm.decr_((Vec2ul) this);
    }

    public Vec2ul decr() {
        return Glm.decr((Vec2ul) this);
    }

    public Vec2ul decr(final Vec2ul res) {
        return Glm.decr(res, (Vec2ul) this);
    }
}
