/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.b;

import glmath.glm.Glm;
import glmath.glm.vec._3.bool.Vec3bool;

/**
 *
 * @author GBarbieri
 */
abstract class FuncRelational extends ArithmeticOperators {

    public boolean any() {
        return Glm.any((Vec3b) this);
    }

    public boolean all() {
        return Glm.all((Vec3b) this);
    }

    public Vec3b not_() {
        return Glm.not((Vec3b) this, new Vec3b());
    }

    public Vec3b not() {
        return Glm.not((Vec3b) this, (Vec3b) this);
    }

    public Vec3bool lessThan__(final Vec3b b) {
        return Glm.lessThan((Vec3b) this, b, new Vec3bool());
    }

    public Vec3bool lessThan(final Vec3b b, final Vec3bool res) {
        return Glm.lessThan((Vec3b) this, b, res);
    }

    public Vec3bool lessThanEqual__(final Vec3b b) {
        return Glm.lessThanEqual((Vec3b) this, b, new Vec3bool());
    }

    public Vec3bool lessThanEqual(final Vec3b b, final Vec3bool res) {
        return Glm.lessThanEqual((Vec3b) this, b, res);
    }

    public Vec3bool greaterThan__(final Vec3b b) {
        return Glm.greaterThan((Vec3b) this, b, new Vec3bool());
    }

    public Vec3bool greaterThan(final Vec3b b, final Vec3bool res) {
        return Glm.greaterThan((Vec3b) this, b, res);
    }

    public Vec3bool greaterThanEqual__(final Vec3b b) {
        return Glm.greaterThanEqual((Vec3b) this, b, new Vec3bool());
    }

    public Vec3bool greaterThanEqual(final Vec3b b, final Vec3bool res) {
        return Glm.greaterThanEqual((Vec3b) this, b, res);
    }

    public Vec3bool equal__(final Vec3b b) {
        return Glm.equal((Vec3b) this, b, new Vec3bool());
    }

    public Vec3bool equal(final Vec3b b, final Vec3bool res) {
        return Glm.equal((Vec3b) this, b, res);
    }

    public Vec3bool notEqual__(final Vec3b b) {
        return Glm.notEqual((Vec3b) this, b, new Vec3bool());
    }

    public Vec3bool notEqual(final Vec3b b, final Vec3bool res) {
        return Glm.notEqual((Vec3b) this, b, res);
    }

    public Vec3b lessThan(final Vec3b b) {
        return Glm.lessThan((Vec3b) this, b, (Vec3b) this);
    }

    public Vec3b lessThan_(final Vec3b b) {
        return Glm.lessThan((Vec3b) this, b, new Vec3b());
    }

    public Vec3b lessThan(final Vec3b b, final Vec3b res) {
        return Glm.lessThan((Vec3b) this, b, res);
    }

    public Vec3b lessThanEqual(final Vec3b b) {
        return Glm.lessThanEqual((Vec3b) this, b, (Vec3b) this);
    }

    public Vec3b lessThanEqual_(final Vec3b b) {
        return Glm.lessThanEqual((Vec3b) this, b, new Vec3b());
    }

    public Vec3b lessThanEqual(final Vec3b b, final Vec3b res) {
        return Glm.lessThanEqual((Vec3b) this, b, res);
    }

    public Vec3b greaterThan(final Vec3b b) {
        return Glm.greaterThan((Vec3b) this, b, (Vec3b) this);
    }

    public Vec3b greaterThan_(final Vec3b b) {
        return Glm.greaterThan((Vec3b) this, b, new Vec3b());
    }

    public Vec3b greaterThan(final Vec3b b, final Vec3b res) {
        return Glm.greaterThan((Vec3b) this, b, res);
    }

    public Vec3b greaterThanEqual(final Vec3b b) {
        return Glm.greaterThanEqual((Vec3b) this, b, (Vec3b) this);
    }

    public Vec3b greaterThanEqual_(final Vec3b b) {
        return Glm.greaterThanEqual((Vec3b) this, b, new Vec3b());
    }

    public Vec3b greaterThanEqual(final Vec3b b, final Vec3b res) {
        return Glm.greaterThanEqual((Vec3b) this, b, res);
    }

    public Vec3b equal(final Vec3b b) {
        return Glm.equal((Vec3b) this, b, (Vec3b) this);
    }

    public Vec3b equal_(final Vec3b b) {
        return Glm.equal((Vec3b) this, b, new Vec3b());
    }

    public Vec3b equal(final Vec3b b, final Vec3b res) {
        return Glm.equal((Vec3b) this, b, res);
    }

    public Vec3b notEqual(final Vec3b b) {
        return Glm.notEqual((Vec3b) this, b, (Vec3b) this);
    }

    public Vec3b notEqual_(final Vec3b b) {
        return Glm.notEqual((Vec3b) this, b, new Vec3b());
    }

    public Vec3b notEqual(final Vec3b b, final Vec3b res) {
        return Glm.notEqual((Vec3b) this, b, res);
    }
}
