/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.ui;

import glmath.glm.Glm;
import glmath.joou.UInt;

/**
 *
 * @author GBarbieri
 */
abstract class ArithmeticOperators {

    public static final int SIZE = 3 * Integer.BYTES;

    public UInt x = new UInt(), y = new UInt(), z = new UInt();

    public Vec3ui add_(final UInt b) {
        return Glm.add(new Vec3ui(), (Vec3ui) this, b.value, b.value, b.value);
    }

    public Vec3ui add_(final int b) {
        return Glm.add(new Vec3ui(), (Vec3ui) this, b, b, b);
    }

    public Vec3ui add_(final UInt bX, final UInt bY, final UInt bZ) {
        return Glm.add(new Vec3ui(), (Vec3ui) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ui add_(final int bX, final int bY, final int bZ) {
        return Glm.add(new Vec3ui(), (Vec3ui) this, bX, bY, bZ);
    }

    public Vec3ui add_(final Vec3ui b) {
        return Glm.add(new Vec3ui(), (Vec3ui) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ui add(final UInt b) {
        return Glm.add((Vec3ui) this, (Vec3ui) this, b.value, b.value, b.value);
    }

    public Vec3ui add(final int b) {
        return Glm.add((Vec3ui) this, (Vec3ui) this, b, b, b);
    }

    public Vec3ui add(final UInt bX, final UInt bY, final UInt bZ) {
        return Glm.add((Vec3ui) this, (Vec3ui) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ui add(final int bX, final int bY, final int bZ) {
        return Glm.add((Vec3ui) this, (Vec3ui) this, bX, bY, bZ);
    }

    public Vec3ui add(final Vec3ui b) {
        return Glm.add((Vec3ui) this, (Vec3ui) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ui add(final UInt b, final Vec3ui res) {
        return Glm.add(res, (Vec3ui) this, b.value, b.value, b.value);
    }

    public Vec3ui add(final int b, final Vec3ui res) {
        return Glm.add(res, (Vec3ui) this, b, b, b);
    }

    public Vec3ui add(final UInt bX, final UInt bY, final UInt bZ, final Vec3ui res) {
        return Glm.add(res, (Vec3ui) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ui add(final int bX, final int bY, final int bZ, final Vec3ui res) {
        return Glm.add(res, (Vec3ui) this, bX, bY, bZ);
    }

    public Vec3ui add(final Vec3ui b, final Vec3ui res) {
        return Glm.add(res, (Vec3ui) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ui sub_(final UInt b) {
        return Glm.sub(new Vec3ui(), (Vec3ui) this, b.value, b.value, b.value);
    }

    public Vec3ui sub_(final int b) {
        return Glm.sub(new Vec3ui(), (Vec3ui) this, b, b, b);
    }

    public Vec3ui sub_(final UInt bX, final UInt bY, final UInt bZ) {
        return Glm.sub(new Vec3ui(), (Vec3ui) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ui sub_(final int bX, final int bY, final int bZ) {
        return Glm.sub(new Vec3ui(), (Vec3ui) this, bX, bY, bZ);
    }

    public Vec3ui sub_(final Vec3ui b) {
        return Glm.sub(new Vec3ui(), (Vec3ui) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ui sub(final UInt b) {
        return Glm.sub((Vec3ui) this, (Vec3ui) this, b.value, b.value, b.value);
    }

    public Vec3ui sub(final int b) {
        return Glm.sub((Vec3ui) this, (Vec3ui) this, b, b, b);
    }

    public Vec3ui sub(final UInt bX, final UInt bY, final UInt bZ) {
        return Glm.sub((Vec3ui) this, (Vec3ui) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ui sub(final int bX, final int bY, final int bZ) {
        return Glm.sub((Vec3ui) this, (Vec3ui) this, bX, bY, bZ);
    }

    public Vec3ui sub(final Vec3ui b) {
        return Glm.sub((Vec3ui) this, (Vec3ui) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ui sub(final UInt b, final Vec3ui res) {
        return Glm.sub(res, (Vec3ui) this, b.value, b.value, b.value);
    }

    public Vec3ui sub(final int b, final Vec3ui res) {
        return Glm.sub(res, (Vec3ui) this, b, b, b);
    }

    public Vec3ui sub(final UInt bX, final UInt bY, final UInt bZ, final Vec3ui res) {
        return Glm.sub(res, (Vec3ui) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ui sub(final int bX, final int bY, final int bZ, final Vec3ui res) {
        return Glm.sub(res, (Vec3ui) this, bX, bY, bZ);
    }

    public Vec3ui sub(final Vec3ui b, final Vec3ui res) {
        return Glm.sub(res, (Vec3ui) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ui mul_(final UInt b) {
        return Glm.mul(new Vec3ui(), (Vec3ui) this, b.value, b.value, b.value);
    }

    public Vec3ui mul_(final int b) {
        return Glm.mul(new Vec3ui(), (Vec3ui) this, b, b, b);
    }

    public Vec3ui mul_(final UInt bX, final UInt bY, final UInt bZ) {
        return Glm.mul(new Vec3ui(), (Vec3ui) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ui mul_(final int bX, final int bY, final int bZ) {
        return Glm.mul(new Vec3ui(), (Vec3ui) this, bX, bY, bZ);
    }

    public Vec3ui mul_(final Vec3ui b) {
        return Glm.mul(new Vec3ui(), (Vec3ui) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ui mul(final UInt b) {
        return Glm.mul((Vec3ui) this, (Vec3ui) this, b.value, b.value, b.value);
    }

    public Vec3ui mul(final int b) {
        return Glm.mul((Vec3ui) this, (Vec3ui) this, b, b, b);
    }

    public Vec3ui mul(final UInt bX, final UInt bY, final UInt bZ) {
        return Glm.mul((Vec3ui) this, (Vec3ui) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ui mul(final int bX, final int bY, final int bZ) {
        return Glm.mul((Vec3ui) this, (Vec3ui) this, bX, bY, bZ);
    }

    public Vec3ui mul(final Vec3ui b) {
        return Glm.mul((Vec3ui) this, (Vec3ui) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ui mul(final UInt b, final Vec3ui res) {
        return Glm.mul(res, (Vec3ui) this, b.value, b.value, b.value);
    }

    public Vec3ui mul(final int b, final Vec3ui res) {
        return Glm.mul(res, (Vec3ui) this, b, b, b);
    }

    public Vec3ui mul(final UInt bX, final UInt bY, final UInt bZ, final Vec3ui res) {
        return Glm.mul(res, (Vec3ui) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ui mul(final int bX, final int bY, final int bZ, final Vec3ui res) {
        return Glm.mul(res, (Vec3ui) this, bX, bY, bZ);
    }

    public Vec3ui mul(final Vec3ui b, final Vec3ui res) {
        return Glm.mul(res, (Vec3ui) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ui div_(final UInt b) {
        return Glm.div(new Vec3ui(), (Vec3ui) this, b.value, b.value, b.value);
    }

    public Vec3ui div_(final int b) {
        return Glm.div(new Vec3ui(), (Vec3ui) this, b, b, b);
    }

    public Vec3ui div_(final UInt bX, final UInt bY, final UInt bZ) {
        return Glm.div(new Vec3ui(), (Vec3ui) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ui div_(final int bX, final int bY, final int bZ) {
        return Glm.div(new Vec3ui(), (Vec3ui) this, bX, bY, bZ);
    }

    public Vec3ui div_(final Vec3ui b) {
        return Glm.div(new Vec3ui(), (Vec3ui) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ui div(final UInt b) {
        return Glm.div((Vec3ui) this, (Vec3ui) this, b.value, b.value, b.value);
    }

    public Vec3ui div(final int b) {
        return Glm.div((Vec3ui) this, (Vec3ui) this, b, b, b);
    }

    public Vec3ui div(final UInt bX, final UInt bY, final UInt bZ) {
        return Glm.div((Vec3ui) this, (Vec3ui) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ui div(final int bX, final int bY, final int bZ) {
        return Glm.div((Vec3ui) this, (Vec3ui) this, bX, bY, bZ);
    }

    public Vec3ui div(final Vec3ui b) {
        return Glm.div((Vec3ui) this, (Vec3ui) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ui div(final UInt b, final Vec3ui res) {
        return Glm.div(res, (Vec3ui) this, b.value, b.value, b.value);
    }

    public Vec3ui div(final int b, final Vec3ui res) {
        return Glm.div(res, (Vec3ui) this, b, b, b);
    }

    public Vec3ui div(final UInt bX, final UInt bY, final UInt bZ, final Vec3ui res) {
        return Glm.div(res, (Vec3ui) this, bX.value, bY.value, bZ.value);
    }

    public Vec3ui div(final int bX, final int bY, final int bZ, final Vec3ui res) {
        return Glm.div(res, (Vec3ui) this, bX, bY, bZ);
    }

    public Vec3ui div(final Vec3ui b, final Vec3ui res) {
        return Glm.div(res, (Vec3ui) this, b.x.value, b.y.value, b.z.value);
    }

    public Vec3ui incr_() {
        return Glm.incr_((Vec3ui) this);
    }

    public Vec3ui incr() {
        return Glm.incr((Vec3ui) this);
    }

    public Vec3ui incr(final Vec3ui res) {
        return Glm.incr(res, (Vec3ui) this);
    }

    public Vec3ui decr_() {
        return Glm.decr_((Vec3ui) this);
    }

    public Vec3ui decr() {
        return Glm.decr((Vec3ui) this);
    }

    public Vec3ui decr(final Vec3ui res) {
        return Glm.decr(res, (Vec3ui) this);
    }
}
