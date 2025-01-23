/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.ul;

import glmath.glm.Glm;
import glmath.glm.vec._3.bool.Vec3bool;

/**
 *
 * @author GBarbieri
 */
abstract class FuncRelational extends ArithmeticOperators {

    public boolean any() {
        return Glm.any((Vec3ul) this);
    }

    public boolean all() {
        return Glm.all((Vec3ul) this);
    }

    public Vec3ul not_() {
        return Glm.not((Vec3ul) this, new Vec3ul());
    }

    public Vec3ul not() {
        return Glm.not((Vec3ul) this, (Vec3ul) this);
    }

    public Vec3bool lessThan__(final Vec3ul b) {
        return Glm.lessThan((Vec3ul) this, b, new Vec3bool());
    }

    public Vec3bool lessThan(final Vec3ul b, final Vec3bool res) {
        return Glm.lessThan((Vec3ul) this, b, res);
    }

    public Vec3bool lessThanEqual__(final Vec3ul b) {
        return Glm.lessThanEqual((Vec3ul) this, b, new Vec3bool());
    }

    public Vec3bool lessThanEqual(final Vec3ul b, final Vec3bool res) {
        return Glm.lessThanEqual((Vec3ul) this, b, res);
    }

    public Vec3bool greaterThan__(final Vec3ul b) {
        return Glm.greaterThan((Vec3ul) this, b, new Vec3bool());
    }

    public Vec3bool greaterThan(final Vec3ul b, final Vec3bool res) {
        return Glm.greaterThan((Vec3ul) this, b, res);
    }

    public Vec3bool greaterThanEqual__(final Vec3ul b) {
        return Glm.greaterThanEqual((Vec3ul) this, b, new Vec3bool());
    }

    public Vec3bool greaterThanEqual(final Vec3ul b, final Vec3bool res) {
        return Glm.greaterThanEqual((Vec3ul) this, b, res);
    }

    public Vec3bool equal__(final Vec3ul b) {
        return Glm.equal((Vec3ul) this, b, new Vec3bool());
    }

    public Vec3bool equal(final Vec3ul b, final Vec3bool res) {
        return Glm.equal((Vec3ul) this, b, res);
    }

    public Vec3bool notEqual__(final Vec3ul b) {
        return Glm.notEqual((Vec3ul) this, b, new Vec3bool());
    }

    public Vec3bool notEqual(final Vec3ul b, final Vec3bool res) {
        return Glm.notEqual((Vec3ul) this, b, res);
    }

    public Vec3ul lessThan(final Vec3ul b) {
        return Glm.lessThan((Vec3ul) this, b, (Vec3ul) this);
    }

    public Vec3ul lessThan_(final Vec3ul b) {
        return Glm.lessThan((Vec3ul) this, b, new Vec3ul());
    }

    public Vec3ul lessThan(final Vec3ul b, final Vec3ul res) {
        return Glm.lessThan((Vec3ul) this, b, res);
    }

    public Vec3ul lessThanEqual(final Vec3ul b) {
        return Glm.lessThanEqual((Vec3ul) this, b, (Vec3ul) this);
    }

    public Vec3ul lessThanEqual_(final Vec3ul b) {
        return Glm.lessThanEqual((Vec3ul) this, b, new Vec3ul());
    }

    public Vec3ul lessThanEqual(final Vec3ul b, final Vec3ul res) {
        return Glm.lessThanEqual((Vec3ul) this, b, res);
    }

    public Vec3ul greaterThan(final Vec3ul b) {
        return Glm.greaterThan((Vec3ul) this, b, (Vec3ul) this);
    }

    public Vec3ul greaterThan_(final Vec3ul b) {
        return Glm.greaterThan((Vec3ul) this, b, new Vec3ul());
    }

    public Vec3ul greaterThan(final Vec3ul b, final Vec3ul res) {
        return Glm.greaterThan((Vec3ul) this, b, res);
    }

    public Vec3ul greaterThanEqual(final Vec3ul b) {
        return Glm.greaterThanEqual((Vec3ul) this, b, (Vec3ul) this);
    }

    public Vec3ul greaterThanEqual_(final Vec3ul b) {
        return Glm.greaterThanEqual((Vec3ul) this, b, new Vec3ul());
    }

    public Vec3ul greaterThanEqual(final Vec3ul b, final Vec3ul res) {
        return Glm.greaterThanEqual((Vec3ul) this, b, res);
    }

    public Vec3ul equal(final Vec3ul b) {
        return Glm.equal((Vec3ul) this, b, (Vec3ul) this);
    }

    public Vec3ul equal_(final Vec3ul b) {
        return Glm.equal((Vec3ul) this, b, new Vec3ul());
    }

    public Vec3ul equal(final Vec3ul b, final Vec3ul res) {
        return Glm.equal((Vec3ul) this, b, res);
    }

    public Vec3ul notEqual(final Vec3ul b) {
        return Glm.notEqual((Vec3ul) this, b, (Vec3ul) this);
    }

    public Vec3ul notEqual_(final Vec3ul b) {
        return Glm.notEqual((Vec3ul) this, b, new Vec3ul());
    }

    public Vec3ul notEqual(final Vec3ul b, final Vec3ul res) {
        return Glm.notEqual((Vec3ul) this, b, res);
    }
}
