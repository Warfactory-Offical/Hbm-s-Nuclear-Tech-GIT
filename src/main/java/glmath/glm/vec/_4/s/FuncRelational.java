/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.s;

import glmath.glm.Glm;
import glmath.glm.vec._4.bool.Vec4bool;

/**
 *
 * @author GBarbieri
 */
abstract class FuncRelational extends ArithmeticOperators {

    public boolean any() {
        return Glm.any((Vec4s) this);
    }

    public boolean all() {
        return Glm.all((Vec4s) this);
    }

    public Vec4s not_() {
        return Glm.not((Vec4s) this, new Vec4s());
    }

    public Vec4s not() {
        return Glm.not((Vec4s) this, (Vec4s) this);
    }

    public Vec4bool lessThan__(final Vec4s b) {
        return Glm.lessThan((Vec4s) this, b, new Vec4bool());
    }

    public Vec4bool lessThan(final Vec4s b, final Vec4bool res) {
        return Glm.lessThan((Vec4s) this, b, res);
    }

    public Vec4bool lessThanEqual__(final Vec4s b) {
        return Glm.lessThanEqual((Vec4s) this, b, new Vec4bool());
    }

    public Vec4bool lessThanEqual(final Vec4s b, final Vec4bool res) {
        return Glm.lessThanEqual((Vec4s) this, b, res);
    }

    public Vec4bool greaterThan__(final Vec4s b) {
        return Glm.greaterThan((Vec4s) this, b, new Vec4bool());
    }

    public Vec4bool greaterThan(final Vec4s b, final Vec4bool res) {
        return Glm.greaterThan((Vec4s) this, b, res);
    }

    public Vec4bool greaterThanEqual__(final Vec4s b) {
        return Glm.greaterThanEqual((Vec4s) this, b, new Vec4bool());
    }

    public Vec4bool greaterThanEqual(final Vec4s b, final Vec4bool res) {
        return Glm.greaterThanEqual((Vec4s) this, b, res);
    }

    public Vec4bool equal__(final Vec4s b) {
        return Glm.equal((Vec4s) this, b, new Vec4bool());
    }

    public Vec4bool equal(final Vec4s a, final Vec4s b, final Vec4bool res) {
        return Glm.equal((Vec4s) this, b, res);
    }

    public Vec4bool notEqual__(final Vec4s b) {
        return Glm.notEqual((Vec4s) this, b, new Vec4bool());
    }

    public Vec4bool notEqual(final Vec4s b, final Vec4bool res) {
        return Glm.notEqual((Vec4s) this, b, res);
    }

    public Vec4s lessThan(final Vec4s b) {
        return Glm.lessThan((Vec4s) this, b, (Vec4s) this);
    }

    public Vec4s lessThan_(final Vec4s b) {
        return Glm.lessThan((Vec4s) this, b, new Vec4s());
    }

    public Vec4s lessThan(final Vec4s b, final Vec4s res) {
        return Glm.lessThan((Vec4s) this, b, res);
    }

    public Vec4s lessThanEqual(final Vec4s b) {
        return Glm.lessThanEqual((Vec4s) this, b, (Vec4s) this);
    }

    public Vec4s lessThanEqual_(final Vec4s b) {
        return Glm.lessThanEqual((Vec4s) this, b, new Vec4s());
    }

    public Vec4s lessThanEqual(final Vec4s b, final Vec4s res) {
        return Glm.lessThanEqual((Vec4s) this, b, res);
    }

    public Vec4s greaterThan(final Vec4s b) {
        return Glm.greaterThan((Vec4s) this, b, (Vec4s) this);
    }

    public Vec4s greaterThan_(final Vec4s b) {
        return Glm.greaterThan((Vec4s) this, b, new Vec4s());
    }

    public Vec4s greaterThan(final Vec4s b, final Vec4s res) {
        return Glm.greaterThan((Vec4s) this, b, res);
    }

    public Vec4s greaterThanEqual(final Vec4s b) {
        return Glm.greaterThanEqual((Vec4s) this, b, (Vec4s) this);
    }

    public Vec4s greaterThanEqual_(final Vec4s b) {
        return Glm.greaterThanEqual((Vec4s) this, b, new Vec4s());
    }

    public Vec4s greaterThanEqual(final Vec4s b, final Vec4s res) {
        return Glm.greaterThanEqual((Vec4s) this, b, res);
    }

    public Vec4s equal(final Vec4s b) {
        return Glm.equal((Vec4s) this, b, (Vec4s) this);
    }

    public Vec4s equal_(final Vec4s b) {
        return Glm.equal((Vec4s) this, b, new Vec4s());
    }

    public Vec4s equal(final Vec4s a, final Vec4s b, final Vec4s res) {
        return Glm.equal((Vec4s) this, b, res);
    }

    public Vec4s notEqual(final Vec4s b) {
        return Glm.notEqual((Vec4s) this, b, (Vec4s) this);
    }

    public Vec4s notEqual_(final Vec4s b) {
        return Glm.notEqual((Vec4s) this, b, new Vec4s());
    }

    public Vec4s notEqual(final Vec4s b, final Vec4s res) {
        return Glm.notEqual((Vec4s) this, b, res);
    }
}
