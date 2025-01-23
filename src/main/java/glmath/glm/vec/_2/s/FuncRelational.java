/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2.s;

import glmath.glm.Glm;
import glmath.glm.vec._2.bool.Vec2bool;

/**
 *
 * @author GBarbieri
 */
abstract class FuncRelational extends ArithmeticOperators {

    public boolean any() {
        return Glm.any((Vec2s) this);
    }

    public boolean all() {
        return Glm.all((Vec2s) this);
    }

    public Vec2s not_() {
        return Glm.not((Vec2s) this, new Vec2s());
    }

    public Vec2s not() {
        return Glm.not((Vec2s) this, (Vec2s) this);
    }

    public Vec2bool lessThan__(final Vec2s b) {
        return Glm.lessThan((Vec2s) this, b, new Vec2bool());
    }

    public Vec2bool lessThan(final Vec2s b, final Vec2bool res) {
        return Glm.lessThan((Vec2s) this, b, res);
    }

    public Vec2bool lessThanEqual__(final Vec2s b) {
        return Glm.lessThanEqual((Vec2s) this, b, new Vec2bool());
    }

    public Vec2bool lessThanEqual(final Vec2s b, final Vec2bool res) {
        return Glm.lessThanEqual((Vec2s) this, b, res);
    }

    public Vec2bool greaterThan__(final Vec2s b) {
        return Glm.greaterThan((Vec2s) this, b, new Vec2bool());
    }

    public Vec2bool greaterThan(final Vec2s b, final Vec2bool res) {
        return Glm.greaterThan((Vec2s) this, b, res);
    }

    public Vec2bool greaterThanEqual__(final Vec2s b) {
        return Glm.greaterThanEqual((Vec2s) this, b, new Vec2bool());
    }

    public Vec2bool greaterThanEqual(final Vec2s b, final Vec2bool res) {
        return Glm.greaterThanEqual((Vec2s) this, b, res);
    }

    public Vec2bool equal__(final Vec2s b) {
        return Glm.equal((Vec2s) this, b, new Vec2bool());
    }

    public Vec2bool equal(final Vec2s a, final Vec2s b, final Vec2bool res) {
        return Glm.equal((Vec2s) this, b, res);
    }

    public Vec2bool notEqual__(final Vec2s b) {
        return Glm.notEqual((Vec2s) this, b, new Vec2bool());
    }

    public Vec2bool notEqual(final Vec2s b, final Vec2bool res) {
        return Glm.notEqual((Vec2s) this, b, res);
    }

    public Vec2s lessThan(final Vec2s b) {
        return Glm.lessThan((Vec2s) this, b, (Vec2s) this);
    }

    public Vec2s lessThan_(final Vec2s b) {
        return Glm.lessThan((Vec2s) this, b, new Vec2s());
    }

    public Vec2s lessThan(final Vec2s b, final Vec2s res) {
        return Glm.lessThan((Vec2s) this, b, res);
    }

    public Vec2s lessThanEqual(final Vec2s b) {
        return Glm.lessThanEqual((Vec2s) this, b, (Vec2s) this);
    }

    public Vec2s lessThanEqual_(final Vec2s b) {
        return Glm.lessThanEqual((Vec2s) this, b, new Vec2s());
    }

    public Vec2s lessThanEqual(final Vec2s b, final Vec2s res) {
        return Glm.lessThanEqual((Vec2s) this, b, res);
    }

    public Vec2s greaterThan(final Vec2s b) {
        return Glm.greaterThan((Vec2s) this, b, (Vec2s) this);
    }

    public Vec2s greaterThan_(final Vec2s b) {
        return Glm.greaterThan((Vec2s) this, b, new Vec2s());
    }

    public Vec2s greaterThan(final Vec2s b, final Vec2s res) {
        return Glm.greaterThan((Vec2s) this, b, res);
    }

    public Vec2s greaterThanEqual(final Vec2s b) {
        return Glm.greaterThanEqual((Vec2s) this, b, (Vec2s) this);
    }

    public Vec2s greaterThanEqual_(final Vec2s b) {
        return Glm.greaterThanEqual((Vec2s) this, b, new Vec2s());
    }

    public Vec2s greaterThanEqual(final Vec2s b, final Vec2s res) {
        return Glm.greaterThanEqual((Vec2s) this, b, res);
    }

    public Vec2s equal(final Vec2s b) {
        return Glm.equal((Vec2s) this, b, (Vec2s) this);
    }

    public Vec2s equal_(final Vec2s b) {
        return Glm.equal((Vec2s) this, b, new Vec2s());
    }

    public Vec2s equal(final Vec2s a, final Vec2s b, final Vec2s res) {
        return Glm.equal((Vec2s) this, b, res);
    }

    public Vec2s notEqual(final Vec2s b) {
        return Glm.notEqual((Vec2s) this, b, (Vec2s) this);
    }

    public Vec2s notEqual_(final Vec2s b) {
        return Glm.notEqual((Vec2s) this, b, new Vec2s());
    }

    public Vec2s notEqual(final Vec2s b, final Vec2s res) {
        return Glm.notEqual((Vec2s) this, b, res);
    }
}
