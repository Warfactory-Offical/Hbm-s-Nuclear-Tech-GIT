/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2.ul;

import glmath.glm.Glm;
import glmath.glm.vec._2.bool.Vec2bool;

/**
 *
 * @author GBarbieri
 */
abstract class FuncRelational extends ArithmeticOperators {

    public boolean any() {
        return Glm.any((Vec2ul) this);
    }

    public boolean all() {
        return Glm.all((Vec2ul) this);
    }

    public Vec2ul not_() {
        return Glm.not((Vec2ul) this, new Vec2ul());
    }

    public Vec2ul not() {
        return Glm.not((Vec2ul) this, (Vec2ul) this);
    }

    public Vec2bool lessThan__(final Vec2ul b) {
        return Glm.lessThan((Vec2ul) this, b, new Vec2bool());
    }

    public Vec2bool lessThan(final Vec2ul b, final Vec2bool res) {
        return Glm.lessThan((Vec2ul) this, b, res);
    }

    public Vec2bool lessThanEqual__(final Vec2ul b) {
        return Glm.lessThanEqual((Vec2ul) this, b, new Vec2bool());
    }

    public Vec2bool lessThanEqual(final Vec2ul b, final Vec2bool res) {
        return Glm.lessThanEqual((Vec2ul) this, b, res);
    }

    public Vec2bool greaterThan__(final Vec2ul b) {
        return Glm.greaterThan((Vec2ul) this, b, new Vec2bool());
    }

    public Vec2bool greaterThan(final Vec2ul b, final Vec2bool res) {
        return Glm.greaterThan((Vec2ul) this, b, res);
    }

    public Vec2bool greaterThanEqual__(final Vec2ul b) {
        return Glm.greaterThanEqual((Vec2ul) this, b, new Vec2bool());
    }

    public Vec2bool greaterThanEqual(final Vec2ul b, final Vec2bool res) {
        return Glm.greaterThanEqual((Vec2ul) this, b, res);
    }

    public Vec2bool equal__(final Vec2ul b) {
        return Glm.equal((Vec2ul) this, b, new Vec2bool());
    }

    public Vec2bool equal(final Vec2ul b, final Vec2bool res) {
        return Glm.equal((Vec2ul) this, b, res);
    }

    public Vec2bool notEqual__(final Vec2ul b) {
        return Glm.notEqual((Vec2ul) this, b, new Vec2bool());
    }

    public Vec2bool notEqual(final Vec2ul b, final Vec2bool res) {
        return Glm.notEqual((Vec2ul) this, b, res);
    }

    public Vec2ul lessThan(final Vec2ul b) {
        return Glm.lessThan((Vec2ul) this, b, (Vec2ul) this);
    }

    public Vec2ul lessThan_(final Vec2ul b) {
        return Glm.lessThan((Vec2ul) this, b, new Vec2ul());
    }

    public Vec2ul lessThan(final Vec2ul b, final Vec2ul res) {
        return Glm.lessThan((Vec2ul) this, b, res);
    }

    public Vec2ul lessThanEqual(final Vec2ul b) {
        return Glm.lessThanEqual((Vec2ul) this, b, (Vec2ul) this);
    }

    public Vec2ul lessThanEqual_(final Vec2ul b) {
        return Glm.lessThanEqual((Vec2ul) this, b, new Vec2ul());
    }

    public Vec2ul lessThanEqual(final Vec2ul b, final Vec2ul res) {
        return Glm.lessThanEqual((Vec2ul) this, b, res);
    }

    public Vec2ul greaterThan(final Vec2ul b) {
        return Glm.greaterThan((Vec2ul) this, b, (Vec2ul) this);
    }

    public Vec2ul greaterThan_(final Vec2ul b) {
        return Glm.greaterThan((Vec2ul) this, b, new Vec2ul());
    }

    public Vec2ul greaterThan(final Vec2ul b, final Vec2ul res) {
        return Glm.greaterThan((Vec2ul) this, b, res);
    }

    public Vec2ul greaterThanEqual(final Vec2ul b) {
        return Glm.greaterThanEqual((Vec2ul) this, b, (Vec2ul) this);
    }

    public Vec2ul greaterThanEqual_(final Vec2ul b) {
        return Glm.greaterThanEqual((Vec2ul) this, b, new Vec2ul());
    }

    public Vec2ul greaterThanEqual(final Vec2ul b, final Vec2ul res) {
        return Glm.greaterThanEqual((Vec2ul) this, b, res);
    }

    public Vec2ul equal(final Vec2ul b) {
        return Glm.equal((Vec2ul) this, b, (Vec2ul) this);
    }

    public Vec2ul equal_(final Vec2ul b) {
        return Glm.equal((Vec2ul) this, b, new Vec2ul());
    }

    public Vec2ul equal(final Vec2ul b, final Vec2ul res) {
        return Glm.equal((Vec2ul) this, b, res);
    }

    public Vec2ul notEqual(final Vec2ul b) {
        return Glm.notEqual((Vec2ul) this, b, (Vec2ul) this);
    }

    public Vec2ul notEqual_(final Vec2ul b) {
        return Glm.notEqual((Vec2ul) this, b, new Vec2ul());
    }

    public Vec2ul notEqual(final Vec2ul b, final Vec2ul res) {
        return Glm.notEqual((Vec2ul) this, b, res);
    }
}
