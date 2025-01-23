/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.ul;

import glmath.glm.Glm;
import glmath.glm.vec._4.bool.Vec4bool;

/**
 *
 * @author GBarbieri
 */
abstract class FuncRelational extends ArithmeticOperators {

    public boolean any() {
        return Glm.any((Vec4ul) this);
    }

    public boolean all() {
        return Glm.all((Vec4ul) this);
    }

    public Vec4ul not_() {
        return Glm.not((Vec4ul) this, new Vec4ul());
    }

    public Vec4ul not() {
        return Glm.not((Vec4ul) this, (Vec4ul) this);
    }

    public Vec4bool lessThan__(final Vec4ul b) {
        return Glm.lessThan((Vec4ul) this, b, new Vec4bool());
    }

    public Vec4bool lessThan(final Vec4ul b, final Vec4bool res) {
        return Glm.lessThan((Vec4ul) this, b, res);
    }

    public Vec4bool lessThanEqual__(final Vec4ul b) {
        return Glm.lessThanEqual((Vec4ul) this, b, new Vec4bool());
    }

    public Vec4bool lessThanEqual(final Vec4ul b, final Vec4bool res) {
        return Glm.lessThanEqual((Vec4ul) this, b, res);
    }

    public Vec4bool greaterThan__(final Vec4ul b) {
        return Glm.greaterThan((Vec4ul) this, b, new Vec4bool());
    }

    public Vec4bool greaterThan(final Vec4ul b, final Vec4bool res) {
        return Glm.greaterThan((Vec4ul) this, b, res);
    }

    public Vec4bool greaterThanEqual__(final Vec4ul b) {
        return Glm.greaterThanEqual((Vec4ul) this, b, new Vec4bool());
    }

    public Vec4bool greaterThanEqual(final Vec4ul b, final Vec4bool res) {
        return Glm.greaterThanEqual((Vec4ul) this, b, res);
    }

    public Vec4bool equal__(final Vec4ul b) {
        return Glm.equal((Vec4ul) this, b, new Vec4bool());
    }

    public Vec4bool equal(final Vec4ul b, final Vec4bool res) {
        return Glm.equal((Vec4ul) this, b, res);
    }

    public Vec4bool notEqual__(final Vec4ul b) {
        return Glm.notEqual((Vec4ul) this, b, new Vec4bool());
    }

    public Vec4bool notEqual(final Vec4ul b, final Vec4bool res) {
        return Glm.notEqual((Vec4ul) this, b, res);
    }

    public Vec4ul lessThan(final Vec4ul b) {
        return Glm.lessThan((Vec4ul) this, b, (Vec4ul) this);
    }

    public Vec4ul lessThan_(final Vec4ul b) {
        return Glm.lessThan((Vec4ul) this, b, new Vec4ul());
    }

    public Vec4ul lessThan(final Vec4ul b, final Vec4ul res) {
        return Glm.lessThan((Vec4ul) this, b, res);
    }

    public Vec4ul lessThanEqual(final Vec4ul b) {
        return Glm.lessThanEqual((Vec4ul) this, b, (Vec4ul) this);
    }

    public Vec4ul lessThanEqual_(final Vec4ul b) {
        return Glm.lessThanEqual((Vec4ul) this, b, new Vec4ul());
    }

    public Vec4ul lessThanEqual(final Vec4ul b, final Vec4ul res) {
        return Glm.lessThanEqual((Vec4ul) this, b, res);
    }

    public Vec4ul greaterThan(final Vec4ul b) {
        return Glm.greaterThan((Vec4ul) this, b, (Vec4ul) this);
    }

    public Vec4ul greaterThan_(final Vec4ul b) {
        return Glm.greaterThan((Vec4ul) this, b, new Vec4ul());
    }

    public Vec4ul greaterThan(final Vec4ul b, final Vec4ul res) {
        return Glm.greaterThan((Vec4ul) this, b, res);
    }

    public Vec4ul greaterThanEqual(final Vec4ul b) {
        return Glm.greaterThanEqual((Vec4ul) this, b, (Vec4ul) this);
    }

    public Vec4ul greaterThanEqual_(final Vec4ul b) {
        return Glm.greaterThanEqual((Vec4ul) this, b, new Vec4ul());
    }

    public Vec4ul greaterThanEqual(final Vec4ul b, final Vec4ul res) {
        return Glm.greaterThanEqual((Vec4ul) this, b, res);
    }

    public Vec4ul equal(final Vec4ul b) {
        return Glm.equal((Vec4ul) this, b, (Vec4ul) this);
    }

    public Vec4ul equal_(final Vec4ul b) {
        return Glm.equal((Vec4ul) this, b, new Vec4ul());
    }

    public Vec4ul equal(final Vec4ul b, final Vec4ul res) {
        return Glm.equal((Vec4ul) this, b, res);
    }

    public Vec4ul notEqual(final Vec4ul b) {
        return Glm.notEqual((Vec4ul) this, b, (Vec4ul) this);
    }

    public Vec4ul notEqual_(final Vec4ul b) {
        return Glm.notEqual((Vec4ul) this, b, new Vec4ul());
    }

    public Vec4ul notEqual(final Vec4ul b, final Vec4ul res) {
        return Glm.notEqual((Vec4ul) this, b, res);
    }
}
