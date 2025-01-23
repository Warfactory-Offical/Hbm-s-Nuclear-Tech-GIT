/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.b;

import glmath.glm.Glm;
import glmath.glm.vec._4.bool.Vec4bool;

/**
 *
 * @author GBarbieri
 */
abstract class FuncRelational extends ArithmeticOperators {

    public boolean any() {
        return Glm.any((Vec4b) this);
    }

    public boolean all() {
        return Glm.all((Vec4b) this);
    }

    public Vec4b not_() {
        return Glm.not((Vec4b) this, new Vec4b());
    }

    public Vec4b not() {
        return Glm.not((Vec4b) this, (Vec4b) this);
    }

    public Vec4bool lessThan__(final Vec4b b) {
        return Glm.lessThan((Vec4b) this, b, new Vec4bool());
    }

    public Vec4bool lessThan(final Vec4b b, final Vec4bool res) {
        return Glm.lessThan((Vec4b) this, b, res);
    }

    public Vec4bool lessThanEqual__(final Vec4b b) {
        return Glm.lessThanEqual((Vec4b) this, b, new Vec4bool());
    }

    public Vec4bool lessThanEqual(final Vec4b b, final Vec4bool res) {
        return Glm.lessThanEqual((Vec4b) this, b, res);
    }

    public Vec4bool greaterThan__(final Vec4b b) {
        return Glm.greaterThan((Vec4b) this, b, new Vec4bool());
    }

    public Vec4bool greaterThan(final Vec4b b, final Vec4bool res) {
        return Glm.greaterThan((Vec4b) this, b, res);
    }

    public Vec4bool greaterThanEqual__(final Vec4b b) {
        return Glm.greaterThanEqual((Vec4b) this, b, new Vec4bool());
    }

    public Vec4bool greaterThanEqual(final Vec4b b, final Vec4bool res) {
        return Glm.greaterThanEqual((Vec4b) this, b, res);
    }

    public Vec4bool equal__(final Vec4b b) {
        return Glm.equal((Vec4b) this, b, new Vec4bool());
    }

    public Vec4bool equal(final Vec4b b, final Vec4bool res) {
        return Glm.equal((Vec4b) this, b, res);
    }

    public Vec4bool notEqual__(final Vec4b b) {
        return Glm.notEqual((Vec4b) this, b, new Vec4bool());
    }

    public Vec4bool notEqual(final Vec4b b, final Vec4bool res) {
        return Glm.notEqual((Vec4b) this, b, res);
    }

    public Vec4b lessThan(final Vec4b b) {
        return Glm.lessThan((Vec4b) this, b, (Vec4b) this);
    }

    public Vec4b lessThan_(final Vec4b b) {
        return Glm.lessThan((Vec4b) this, b, new Vec4b());
    }

    public Vec4b lessThan(final Vec4b b, final Vec4b res) {
        return Glm.lessThan((Vec4b) this, b, res);
    }

    public Vec4b lessThanEqual(final Vec4b b) {
        return Glm.lessThanEqual((Vec4b) this, b, (Vec4b) this);
    }

    public Vec4b lessThanEqual_(final Vec4b b) {
        return Glm.lessThanEqual((Vec4b) this, b, new Vec4b());
    }

    public Vec4b lessThanEqual(final Vec4b b, final Vec4b res) {
        return Glm.lessThanEqual((Vec4b) this, b, res);
    }

    public Vec4b greaterThan(final Vec4b b) {
        return Glm.greaterThan((Vec4b) this, b, (Vec4b) this);
    }

    public Vec4b greaterThan_(final Vec4b b) {
        return Glm.greaterThan((Vec4b) this, b, new Vec4b());
    }

    public Vec4b greaterThan(final Vec4b b, final Vec4b res) {
        return Glm.greaterThan((Vec4b) this, b, res);
    }

    public Vec4b greaterThanEqual(final Vec4b b) {
        return Glm.greaterThanEqual((Vec4b) this, b, (Vec4b) this);
    }

    public Vec4b greaterThanEqual_(final Vec4b b) {
        return Glm.greaterThanEqual((Vec4b) this, b, new Vec4b());
    }

    public Vec4b greaterThanEqual(final Vec4b b, final Vec4b res) {
        return Glm.greaterThanEqual((Vec4b) this, b, res);
    }

    public Vec4b equal(final Vec4b b) {
        return Glm.equal((Vec4b) this, b, (Vec4b) this);
    }

    public Vec4b equal_(final Vec4b b) {
        return Glm.equal((Vec4b) this, b, new Vec4b());
    }

    public Vec4b equal(final Vec4b b, final Vec4b res) {
        return Glm.equal((Vec4b) this, b, res);
    }

    public Vec4b notEqual(final Vec4b b) {
        return Glm.notEqual((Vec4b) this, b, (Vec4b) this);
    }

    public Vec4b notEqual_(final Vec4b b) {
        return Glm.notEqual((Vec4b) this, b, new Vec4b());
    }

    public Vec4b notEqual(final Vec4b b, final Vec4b res) {
        return Glm.notEqual((Vec4b) this, b, res);
    }
}
