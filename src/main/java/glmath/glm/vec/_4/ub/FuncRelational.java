/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.ub;

import glmath.glm.Glm;
import glmath.glm.vec._4.bool.Vec4bool;

/**
 *
 * @author GBarbieri
 */
abstract class FuncRelational extends ArithmeticOperators {

    public boolean any() {
        return Glm.any((Vec4ub) this);
    }

    public boolean all() {
        return Glm.all((Vec4ub) this);
    }

    public Vec4ub not_() {
        return Glm.not((Vec4ub) this, new Vec4ub());
    }

    public Vec4ub not() {
        return Glm.not((Vec4ub) this, (Vec4ub) this);
    }

    public Vec4bool lessThan__(final Vec4ub b) {
        return Glm.lessThan((Vec4ub) this, b, new Vec4bool());
    }

    public Vec4bool lessThan(final Vec4ub b, final Vec4bool res) {
        return Glm.lessThan((Vec4ub) this, b, res);
    }

    public Vec4bool lessThanEqual__(final Vec4ub b) {
        return Glm.lessThanEqual((Vec4ub) this, b, new Vec4bool());
    }

    public Vec4bool lessThanEqual(final Vec4ub b, final Vec4bool res) {
        return Glm.lessThanEqual((Vec4ub) this, b, res);
    }

    public Vec4bool greaterThan__(final Vec4ub b) {
        return Glm.greaterThan((Vec4ub) this, b, new Vec4bool());
    }

    public Vec4bool greaterThan(final Vec4ub b, final Vec4bool res) {
        return Glm.greaterThan((Vec4ub) this, b, res);
    }

    public Vec4bool greaterThanEqual__(final Vec4ub b) {
        return Glm.greaterThanEqual((Vec4ub) this, b, new Vec4bool());
    }

    public Vec4bool greaterThanEqual(final Vec4ub b, final Vec4bool res) {
        return Glm.greaterThanEqual((Vec4ub) this, b, res);
    }

    public Vec4bool equal__(final Vec4ub b) {
        return Glm.equal((Vec4ub) this, b, new Vec4bool());
    }

    public Vec4bool equal(final Vec4ub b, final Vec4bool res) {
        return Glm.equal((Vec4ub) this, b, res);
    }

    public Vec4bool notEqual__(final Vec4ub b) {
        return Glm.notEqual((Vec4ub) this, b, new Vec4bool());
    }

    public Vec4bool notEqual(final Vec4ub b, final Vec4bool res) {
        return Glm.notEqual((Vec4ub) this, b, res);
    }

    public Vec4ub lessThan(final Vec4ub b) {
        return Glm.lessThan((Vec4ub) this, b, (Vec4ub) this);
    }

    public Vec4ub lessThan_(final Vec4ub b) {
        return Glm.lessThan((Vec4ub) this, b, new Vec4ub());
    }

    public Vec4ub lessThan(final Vec4ub b, final Vec4ub res) {
        return Glm.lessThan((Vec4ub) this, b, res);
    }

    public Vec4ub lessThanEqual(final Vec4ub b) {
        return Glm.lessThanEqual((Vec4ub) this, b, (Vec4ub) this);
    }

    public Vec4ub lessThanEqual_(final Vec4ub b) {
        return Glm.lessThanEqual((Vec4ub) this, b, new Vec4ub());
    }

    public Vec4ub lessThanEqual(final Vec4ub b, final Vec4ub res) {
        return Glm.lessThanEqual((Vec4ub) this, b, res);
    }

    public Vec4ub greaterThan(final Vec4ub b) {
        return Glm.greaterThan((Vec4ub) this, b, (Vec4ub) this);
    }

    public Vec4ub greaterThan_(final Vec4ub b) {
        return Glm.greaterThan((Vec4ub) this, b, new Vec4ub());
    }

    public Vec4ub greaterThan(final Vec4ub b, final Vec4ub res) {
        return Glm.greaterThan((Vec4ub) this, b, res);
    }

    public Vec4ub greaterThanEqual(final Vec4ub b) {
        return Glm.greaterThanEqual((Vec4ub) this, b, (Vec4ub) this);
    }

    public Vec4ub greaterThanEqual_(final Vec4ub b) {
        return Glm.greaterThanEqual((Vec4ub) this, b, new Vec4ub());
    }

    public Vec4ub greaterThanEqual(final Vec4ub b, final Vec4ub res) {
        return Glm.greaterThanEqual((Vec4ub) this, b, res);
    }

    public Vec4ub equal(final Vec4ub b) {
        return Glm.equal((Vec4ub) this, b, (Vec4ub) this);
    }

    public Vec4ub equal_(final Vec4ub b) {
        return Glm.equal((Vec4ub) this, b, new Vec4ub());
    }

    public Vec4ub equal(final Vec4ub b, final Vec4ub res) {
        return Glm.equal((Vec4ub) this, b, res);
    }

    public Vec4ub notEqual(final Vec4ub b) {
        return Glm.notEqual((Vec4ub) this, b, (Vec4ub) this);
    }

    public Vec4ub notEqual_(final Vec4ub b) {
        return Glm.notEqual((Vec4ub) this, b, new Vec4ub());
    }

    public Vec4ub notEqual(final Vec4ub b, final Vec4ub res) {
        return Glm.notEqual((Vec4ub) this, b, res);
    }
}
