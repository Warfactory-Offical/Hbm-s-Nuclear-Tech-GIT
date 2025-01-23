/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2.ui;

import glmath.glm.Glm;
import glmath.joou.UInt;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 2 * Integer.BYTES;

    public UInt x = new UInt(), y = new UInt();

    public Vec2ui add_(final UInt b) {
        return Glm.add(new Vec2ui(), (Vec2ui) this, b.value, b.value);
    }

    public Vec2ui add_(final int b) {
        return Glm.add(new Vec2ui(), (Vec2ui) this, b, b);
    }

    public Vec2ui add_(final UInt bX, final UInt bY) {
        return Glm.add(new Vec2ui(), (Vec2ui) this, bX.value, bY.value);
    }

    public Vec2ui add_(final int bX, final int bY) {
        return Glm.add(new Vec2ui(), (Vec2ui) this, bX, bY);
    }

    public Vec2ui add_(final Vec2ui b) {
        return Glm.add(new Vec2ui(), (Vec2ui) this, b.x.value, b.y.value);
    }

    public Vec2ui add(final UInt b) {
        return Glm.add((Vec2ui) this, (Vec2ui) this, b.value, b.value);
    }

    public Vec2ui add(final int b) {
        return Glm.add((Vec2ui) this, (Vec2ui) this, b, b);
    }

    public Vec2ui add(final UInt bX, final UInt bY) {
        return Glm.add((Vec2ui) this, (Vec2ui) this, bX.value, bY.value);
    }

    public Vec2ui add(final int bX, final int bY) {
        return Glm.add((Vec2ui) this, (Vec2ui) this, bX, bY);
    }

    public Vec2ui add(final Vec2ui b) {
        return Glm.add((Vec2ui) this, (Vec2ui) this, b.x.value, b.y.value);
    }

    public Vec2ui add(final UInt b, final Vec2ui res) {
        return Glm.add(res, (Vec2ui) this, b.value, b.value);
    }

    public Vec2ui add(final int b, final Vec2ui res) {
        return Glm.add(res, (Vec2ui) this, b, b);
    }

    public Vec2ui add(final UInt bX, final UInt bY, final Vec2ui res) {
        return Glm.add(res, (Vec2ui) this, bX.value, bY.value);
    }

    public Vec2ui add(final int bX, final int bY, final Vec2ui res) {
        return Glm.add(res, (Vec2ui) this, bX, bY);
    }

    public Vec2ui add(final Vec2ui b, final Vec2ui res) {
        return Glm.add(res, (Vec2ui) this, b.x.value, b.y.value);
    }

    public Vec2ui sub_(final UInt b) {
        return Glm.sub(new Vec2ui(), (Vec2ui) this, b.value, b.value);
    }

    public Vec2ui sub_(final int b) {
        return Glm.sub(new Vec2ui(), (Vec2ui) this, b, b);
    }

    public Vec2ui sub_(final UInt bX, final UInt bY) {
        return Glm.sub(new Vec2ui(), (Vec2ui) this, bX.value, bY.value);
    }

    public Vec2ui sub_(final int bX, final int bY) {
        return Glm.sub(new Vec2ui(), (Vec2ui) this, bX, bY);
    }

    public Vec2ui sub_(final Vec2ui b) {
        return Glm.sub(new Vec2ui(), (Vec2ui) this, b.x.value, b.y.value);
    }

    public Vec2ui sub(final UInt b) {
        return Glm.sub((Vec2ui) this, (Vec2ui) this, b.value, b.value);
    }

    public Vec2ui sub(final int b) {
        return Glm.sub((Vec2ui) this, (Vec2ui) this, b, b);
    }

    public Vec2ui sub(final UInt bX, final UInt bY) {
        return Glm.sub((Vec2ui) this, (Vec2ui) this, bX.value, bY.value);
    }

    public Vec2ui sub(final int bX, final int bY) {
        return Glm.sub((Vec2ui) this, (Vec2ui) this, bX, bY);
    }

    public Vec2ui sub(final Vec2ui b) {
        return Glm.sub((Vec2ui) this, (Vec2ui) this, b.x.value, b.y.value);
    }

    public Vec2ui sub(final UInt b, final Vec2ui res) {
        return Glm.sub(res, (Vec2ui) this, b.value, b.value);
    }

    public Vec2ui sub(final int b, final Vec2ui res) {
        return Glm.sub(res, (Vec2ui) this, b, b);
    }

    public Vec2ui sub(final UInt bX, final UInt bY, final Vec2ui res) {
        return Glm.sub(res, (Vec2ui) this, bX.value, bY.value);
    }

    public Vec2ui sub(final int bX, final int bY, final Vec2ui res) {
        return Glm.sub(res, (Vec2ui) this, bX, bY);
    }

    public Vec2ui sub(final Vec2ui b, final Vec2ui res) {
        return Glm.sub(res, (Vec2ui) this, b.x.value, b.y.value);
    }

    public Vec2ui mul_(final UInt b) {
        return Glm.mul(new Vec2ui(), (Vec2ui) this, b.value, b.value);
    }

    public Vec2ui mul_(final int b) {
        return Glm.mul(new Vec2ui(), (Vec2ui) this, b, b);
    }

    public Vec2ui mul_(final UInt bX, final UInt bY) {
        return Glm.mul(new Vec2ui(), (Vec2ui) this, bX.value, bY.value);
    }

    public Vec2ui mul_(final int bX, final int bY) {
        return Glm.mul(new Vec2ui(), (Vec2ui) this, bX, bY);
    }

    public Vec2ui mul_(final Vec2ui b) {
        return Glm.mul(new Vec2ui(), (Vec2ui) this, b.x.value, b.y.value);
    }

    public Vec2ui mul(final UInt b) {
        return Glm.mul((Vec2ui) this, (Vec2ui) this, b.value, b.value);
    }

    public Vec2ui mul(final int b) {
        return Glm.mul((Vec2ui) this, (Vec2ui) this, b, b);
    }

    public Vec2ui mul(final UInt bX, final UInt bY) {
        return Glm.mul((Vec2ui) this, (Vec2ui) this, bX.value, bY.value);
    }

    public Vec2ui mul(final int bX, final int bY) {
        return Glm.mul((Vec2ui) this, (Vec2ui) this, bX, bY);
    }

    public Vec2ui mul(final Vec2ui b) {
        return Glm.mul((Vec2ui) this, (Vec2ui) this, b.x.value, b.y.value);
    }

    public Vec2ui mul(final UInt b, final Vec2ui res) {
        return Glm.mul(res, (Vec2ui) this, b.value, b.value);
    }

    public Vec2ui mul(final int b, final Vec2ui res) {
        return Glm.mul(res, (Vec2ui) this, b, b);
    }

    public Vec2ui mul(final UInt bX, final UInt bY, final Vec2ui res) {
        return Glm.mul(res, (Vec2ui) this, bX.value, bY.value);
    }

    public Vec2ui mul(final int bX, final int bY, final Vec2ui res) {
        return Glm.mul(res, (Vec2ui) this, bX, bY);
    }

    public Vec2ui mul(final Vec2ui b, final Vec2ui res) {
        return Glm.mul(res, (Vec2ui) this, b.x.value, b.y.value);
    }

    public Vec2ui div_(final UInt b) {
        return Glm.div(new Vec2ui(), (Vec2ui) this, b.value, b.value);
    }

    public Vec2ui div_(final int b) {
        return Glm.div(new Vec2ui(), (Vec2ui) this, b, b);
    }

    public Vec2ui div_(final UInt bX, final UInt bY) {
        return Glm.div(new Vec2ui(), (Vec2ui) this, bX.value, bY.value);
    }

    public Vec2ui div_(final int bX, final int bY) {
        return Glm.div(new Vec2ui(), (Vec2ui) this, bX, bY);
    }

    public Vec2ui div_(final Vec2ui b) {
        return Glm.div(new Vec2ui(), (Vec2ui) this, b.x.value, b.y.value);
    }

    public Vec2ui div(final UInt b) {
        return Glm.div((Vec2ui) this, (Vec2ui) this, b.value, b.value);
    }

    public Vec2ui div(final int b) {
        return Glm.div((Vec2ui) this, (Vec2ui) this, b, b);
    }

    public Vec2ui div(final UInt bX, final UInt bY) {
        return Glm.div((Vec2ui) this, (Vec2ui) this, bX.value, bY.value);
    }

    public Vec2ui div(final int bX, final int bY) {
        return Glm.div((Vec2ui) this, (Vec2ui) this, bX, bY);
    }

    public Vec2ui div(final Vec2ui b) {
        return Glm.div((Vec2ui) this, (Vec2ui) this, b.x.value, b.y.value);
    }

    public Vec2ui div(final UInt b, final Vec2ui res) {
        return Glm.div(res, (Vec2ui) this, b.value, b.value);
    }

    public Vec2ui div(final int b, final Vec2ui res) {
        return Glm.div(res, (Vec2ui) this, b, b);
    }

    public Vec2ui div(final UInt bX, final UInt bY, final Vec2ui res) {
        return Glm.div(res, (Vec2ui) this, bX.value, bY.value);
    }

    public Vec2ui div(final int bX, final int bY, final Vec2ui res) {
        return Glm.div(res, (Vec2ui) this, bX, bY);
    }

    public Vec2ui div(final Vec2ui b, final Vec2ui res) {
        return Glm.div(res, (Vec2ui) this, b.x.value, b.y.value);
    }

    public Vec2ui incr_() {
        return Glm.incr_((Vec2ui) this);
    }

    public Vec2ui incr() {
        return Glm.incr((Vec2ui) this);
    }

    public Vec2ui incr(final Vec2ui res) {
        return Glm.incr(res, (Vec2ui) this);
    }

    public Vec2ui decr_() {
        return Glm.decr_((Vec2ui) this);
    }

    public Vec2ui decr() {
        return Glm.decr((Vec2ui) this);
    }

    public Vec2ui decr(final Vec2ui res) {
        return Glm.decr(res, (Vec2ui) this);
    }
}
