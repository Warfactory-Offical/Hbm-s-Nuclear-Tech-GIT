/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2.ub;

import glmath.glm.Glm;
import glmath.glm.vec._2.bool.Vec2bool;

/**
 *
 * @author GBarbieri
 */
abstract class FuncRelational extends ArithmeticOperators {

    public boolean any() {
        return Glm.any((Vec2ub) this);
    }

    public boolean all() {
        return Glm.all((Vec2ub) this);
    }

    public Vec2ub not_() {
        return Glm.not((Vec2ub) this, new Vec2ub());
    }

    public Vec2ub not() {
        return Glm.not((Vec2ub) this, (Vec2ub) this);
    }

    public Vec2bool lessThan__(final Vec2ub b) {
        return Glm.lessThan((Vec2ub) this, b, new Vec2bool());
    }

    public Vec2bool lessThan(final Vec2ub b, final Vec2bool res) {
        return Glm.lessThan((Vec2ub) this, b, res);
    }

    public Vec2bool lessThanEqual__(final Vec2ub b) {
        return Glm.lessThanEqual((Vec2ub) this, b, new Vec2bool());
    }

    public Vec2bool lessThanEqual(final Vec2ub b, final Vec2bool res) {
        return Glm.lessThanEqual((Vec2ub) this, b, res);
    }

    public Vec2bool greaterThan__(final Vec2ub b) {
        return Glm.greaterThan((Vec2ub) this, b, new Vec2bool());
    }

    public Vec2bool greaterThan(final Vec2ub b, final Vec2bool res) {
        return Glm.greaterThan((Vec2ub) this, b, res);
    }

    public Vec2bool greaterThanEqual__(final Vec2ub b) {
        return Glm.greaterThanEqual((Vec2ub) this, b, new Vec2bool());
    }

    public Vec2bool greaterThanEqual(final Vec2ub b, final Vec2bool res) {
        return Glm.greaterThanEqual((Vec2ub) this, b, res);
    }

    public Vec2bool equal__(final Vec2ub b) {
        return Glm.equal((Vec2ub) this, b, new Vec2bool());
    }

    public Vec2bool equal(final Vec2ub b, final Vec2bool res) {
        return Glm.equal((Vec2ub) this, b, res);
    }

    public Vec2bool notEqual__(final Vec2ub b) {
        return Glm.notEqual((Vec2ub) this, b, new Vec2bool());
    }

    public Vec2bool notEqual(final Vec2ub b, final Vec2bool res) {
        return Glm.notEqual((Vec2ub) this, b, res);
    }

    public Vec2ub lessThan(final Vec2ub b) {
        return Glm.lessThan((Vec2ub) this, b, (Vec2ub) this);
    }

    public Vec2ub lessThan_(final Vec2ub b) {
        return Glm.lessThan((Vec2ub) this, b, new Vec2ub());
    }

    public Vec2ub lessThan(final Vec2ub b, final Vec2ub res) {
        return Glm.lessThan((Vec2ub) this, b, res);
    }

    public Vec2ub lessThanEqual(final Vec2ub b) {
        return Glm.lessThanEqual((Vec2ub) this, b, (Vec2ub) this);
    }

    public Vec2ub lessThanEqual_(final Vec2ub b) {
        return Glm.lessThanEqual((Vec2ub) this, b, new Vec2ub());
    }

    public Vec2ub lessThanEqual(final Vec2ub b, final Vec2ub res) {
        return Glm.lessThanEqual((Vec2ub) this, b, res);
    }

    public Vec2ub greaterThan(final Vec2ub b) {
        return Glm.greaterThan((Vec2ub) this, b, (Vec2ub) this);
    }

    public Vec2ub greaterThan_(final Vec2ub b) {
        return Glm.greaterThan((Vec2ub) this, b, new Vec2ub());
    }

    public Vec2ub greaterThan(final Vec2ub b, final Vec2ub res) {
        return Glm.greaterThan((Vec2ub) this, b, res);
    }

    public Vec2ub greaterThanEqual(final Vec2ub b) {
        return Glm.greaterThanEqual((Vec2ub) this, b, (Vec2ub) this);
    }

    public Vec2ub greaterThanEqual_(final Vec2ub b) {
        return Glm.greaterThanEqual((Vec2ub) this, b, new Vec2ub());
    }

    public Vec2ub greaterThanEqual(final Vec2ub b, final Vec2ub res) {
        return Glm.greaterThanEqual((Vec2ub) this, b, res);
    }

    public Vec2ub equal(final Vec2ub b) {
        return Glm.equal((Vec2ub) this, b, (Vec2ub) this);
    }

    public Vec2ub equal_(final Vec2ub b) {
        return Glm.equal((Vec2ub) this, b, new Vec2ub());
    }

    public Vec2ub equal(final Vec2ub b, final Vec2ub res) {
        return Glm.equal((Vec2ub) this, b, res);
    }

    public Vec2ub notEqual(final Vec2ub b) {
        return Glm.notEqual((Vec2ub) this, b, (Vec2ub) this);
    }

    public Vec2ub notEqual_(final Vec2ub b) {
        return Glm.notEqual((Vec2ub) this, b, new Vec2ub());
    }

    public Vec2ub notEqual(final Vec2ub b, final Vec2ub res) {
        return Glm.notEqual((Vec2ub) this, b, res);
    }
}
