/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.s;

import glmath.glm.Glm;
import glmath.glm.vec._3.bool.Vec3bool;

/**
 *
 * @author GBarbieri
 */
abstract class FuncRelational extends ArithmeticOperators {

    public boolean any() {
        return Glm.any((Vec3s) this);
    }

    public boolean all() {
        return Glm.all((Vec3s) this);
    }

    public Vec3s not_() {
        return Glm.not((Vec3s) this, new Vec3s());
    }

    public Vec3s not() {
        return Glm.not((Vec3s) this, (Vec3s) this);
    }

    public Vec3bool lessThan__(final Vec3s b) {
        return Glm.lessThan((Vec3s) this, b, new Vec3bool());
    }

    public Vec3bool lessThan(final Vec3s b, final Vec3bool res) {
        return Glm.lessThan((Vec3s) this, b, res);
    }

    public Vec3bool lessThanEqual__(final Vec3s b) {
        return Glm.lessThanEqual((Vec3s) this, b, new Vec3bool());
    }

    public Vec3bool lessThanEqual(final Vec3s b, final Vec3bool res) {
        return Glm.lessThanEqual((Vec3s) this, b, res);
    }

    public Vec3bool greaterThan__(final Vec3s b) {
        return Glm.greaterThan((Vec3s) this, b, new Vec3bool());
    }

    public Vec3bool greaterThan(final Vec3s b, final Vec3bool res) {
        return Glm.greaterThan((Vec3s) this, b, res);
    }

    public Vec3bool greaterThanEqual__(final Vec3s b) {
        return Glm.greaterThanEqual((Vec3s) this, b, new Vec3bool());
    }

    public Vec3bool greaterThanEqual(final Vec3s b, final Vec3bool res) {
        return Glm.greaterThanEqual((Vec3s) this, b, res);
    }

    public Vec3bool equal__(final Vec3s b) {
        return Glm.equal((Vec3s) this, b, new Vec3bool());
    }

    public Vec3bool equal(final Vec3s a, final Vec3s b, final Vec3bool res) {
        return Glm.equal((Vec3s) this, b, res);
    }

    public Vec3bool notEqual__(final Vec3s b) {
        return Glm.notEqual((Vec3s) this, b, new Vec3bool());
    }

    public Vec3bool notEqual(final Vec3s b, final Vec3bool res) {
        return Glm.notEqual((Vec3s) this, b, res);
    }

    public Vec3s lessThan(final Vec3s b) {
        return Glm.lessThan((Vec3s) this, b, (Vec3s) this);
    }

    public Vec3s lessThan_(final Vec3s b) {
        return Glm.lessThan((Vec3s) this, b, new Vec3s());
    }

    public Vec3s lessThan(final Vec3s b, final Vec3s res) {
        return Glm.lessThan((Vec3s) this, b, res);
    }

    public Vec3s lessThanEqual(final Vec3s b) {
        return Glm.lessThanEqual((Vec3s) this, b, (Vec3s) this);
    }

    public Vec3s lessThanEqual_(final Vec3s b) {
        return Glm.lessThanEqual((Vec3s) this, b, new Vec3s());
    }

    public Vec3s lessThanEqual(final Vec3s b, final Vec3s res) {
        return Glm.lessThanEqual((Vec3s) this, b, res);
    }

    public Vec3s greaterThan(final Vec3s b) {
        return Glm.greaterThan((Vec3s) this, b, (Vec3s) this);
    }

    public Vec3s greaterThan_(final Vec3s b) {
        return Glm.greaterThan((Vec3s) this, b, new Vec3s());
    }

    public Vec3s greaterThan(final Vec3s b, final Vec3s res) {
        return Glm.greaterThan((Vec3s) this, b, res);
    }

    public Vec3s greaterThanEqual(final Vec3s b) {
        return Glm.greaterThanEqual((Vec3s) this, b, (Vec3s) this);
    }

    public Vec3s greaterThanEqual_(final Vec3s b) {
        return Glm.greaterThanEqual((Vec3s) this, b, new Vec3s());
    }

    public Vec3s greaterThanEqual(final Vec3s b, final Vec3s res) {
        return Glm.greaterThanEqual((Vec3s) this, b, res);
    }

    public Vec3s equal(final Vec3s b) {
        return Glm.equal((Vec3s) this, b, (Vec3s) this);
    }

    public Vec3s equal_(final Vec3s b) {
        return Glm.equal((Vec3s) this, b, new Vec3s());
    }

    public Vec3s equal(final Vec3s a, final Vec3s b, final Vec3s res) {
        return Glm.equal((Vec3s) this, b, res);
    }

    public Vec3s notEqual(final Vec3s b) {
        return Glm.notEqual((Vec3s) this, b, (Vec3s) this);
    }

    public Vec3s notEqual_(final Vec3s b) {
        return Glm.notEqual((Vec3s) this, b, new Vec3s());
    }

    public Vec3s notEqual(final Vec3s b, final Vec3s res) {
        return Glm.notEqual((Vec3s) this, b, res);
    }
}
