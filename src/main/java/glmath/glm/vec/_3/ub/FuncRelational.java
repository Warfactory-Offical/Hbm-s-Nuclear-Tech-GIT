/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.ub;

import glmath.glm.Glm;
import glmath.glm.vec._3.bool.Vec3bool;

/**
 *
 * @author GBarbieri
 */
abstract class FuncRelational extends ArithmeticOperators {

    public boolean any() {
        return Glm.any((Vec3ub) this);
    }

    public boolean all() {
        return Glm.all((Vec3ub) this);
    }

    public Vec3ub not_() {
        return Glm.not((Vec3ub) this, new Vec3ub());
    }

    public Vec3ub not() {
        return Glm.not((Vec3ub) this, (Vec3ub) this);
    }

    public Vec3bool lessThan__(final Vec3ub b) {
        return Glm.lessThan((Vec3ub) this, b, new Vec3bool());
    }

    public Vec3bool lessThan(final Vec3ub b, final Vec3bool res) {
        return Glm.lessThan((Vec3ub) this, b, res);
    }

    public Vec3bool lessThanEqual__(final Vec3ub b) {
        return Glm.lessThanEqual((Vec3ub) this, b, new Vec3bool());
    }

    public Vec3bool lessThanEqual(final Vec3ub b, final Vec3bool res) {
        return Glm.lessThanEqual((Vec3ub) this, b, res);
    }

    public Vec3bool greaterThan__(final Vec3ub b) {
        return Glm.greaterThan((Vec3ub) this, b, new Vec3bool());
    }

    public Vec3bool greaterThan(final Vec3ub b, final Vec3bool res) {
        return Glm.greaterThan((Vec3ub) this, b, res);
    }

    public Vec3bool greaterThanEqual__(final Vec3ub b) {
        return Glm.greaterThanEqual((Vec3ub) this, b, new Vec3bool());
    }

    public Vec3bool greaterThanEqual(final Vec3ub b, final Vec3bool res) {
        return Glm.greaterThanEqual((Vec3ub) this, b, res);
    }

    public Vec3bool equal__(final Vec3ub b) {
        return Glm.equal((Vec3ub) this, b, new Vec3bool());
    }

    public Vec3bool equal(final Vec3ub b, final Vec3bool res) {
        return Glm.equal((Vec3ub) this, b, res);
    }

    public Vec3bool notEqual__(final Vec3ub b) {
        return Glm.notEqual((Vec3ub) this, b, new Vec3bool());
    }

    public Vec3bool notEqual(final Vec3ub b, final Vec3bool res) {
        return Glm.notEqual((Vec3ub) this, b, res);
    }

    public Vec3ub lessThan(final Vec3ub b) {
        return Glm.lessThan((Vec3ub) this, b, (Vec3ub) this);
    }

    public Vec3ub lessThan_(final Vec3ub b) {
        return Glm.lessThan((Vec3ub) this, b, new Vec3ub());
    }

    public Vec3ub lessThan(final Vec3ub b, final Vec3ub res) {
        return Glm.lessThan((Vec3ub) this, b, res);
    }

    public Vec3ub lessThanEqual(final Vec3ub b) {
        return Glm.lessThanEqual((Vec3ub) this, b, (Vec3ub) this);
    }

    public Vec3ub lessThanEqual_(final Vec3ub b) {
        return Glm.lessThanEqual((Vec3ub) this, b, new Vec3ub());
    }

    public Vec3ub lessThanEqual(final Vec3ub b, final Vec3ub res) {
        return Glm.lessThanEqual((Vec3ub) this, b, res);
    }

    public Vec3ub greaterThan(final Vec3ub b) {
        return Glm.greaterThan((Vec3ub) this, b, (Vec3ub) this);
    }

    public Vec3ub greaterThan_(final Vec3ub b) {
        return Glm.greaterThan((Vec3ub) this, b, new Vec3ub());
    }

    public Vec3ub greaterThan(final Vec3ub b, final Vec3ub res) {
        return Glm.greaterThan((Vec3ub) this, b, res);
    }

    public Vec3ub greaterThanEqual(final Vec3ub b) {
        return Glm.greaterThanEqual((Vec3ub) this, b, (Vec3ub) this);
    }

    public Vec3ub greaterThanEqual_(final Vec3ub b) {
        return Glm.greaterThanEqual((Vec3ub) this, b, new Vec3ub());
    }

    public Vec3ub greaterThanEqual(final Vec3ub b, final Vec3ub res) {
        return Glm.greaterThanEqual((Vec3ub) this, b, res);
    }

    public Vec3ub equal(final Vec3ub b) {
        return Glm.equal((Vec3ub) this, b, (Vec3ub) this);
    }

    public Vec3ub equal_(final Vec3ub b) {
        return Glm.equal((Vec3ub) this, b, new Vec3ub());
    }

    public Vec3ub equal(final Vec3ub b, final Vec3ub res) {
        return Glm.equal((Vec3ub) this, b, res);
    }

    public Vec3ub notEqual(final Vec3ub b) {
        return Glm.notEqual((Vec3ub) this, b, (Vec3ub) this);
    }

    public Vec3ub notEqual_(final Vec3ub b) {
        return Glm.notEqual((Vec3ub) this, b, new Vec3ub());
    }

    public Vec3ub notEqual(final Vec3ub b, final Vec3ub res) {
        return Glm.notEqual((Vec3ub) this, b, res);
    }
}
