/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2.b;

import glmath.glm.Glm;
import glmath.glm.vec._2.bool.Vec2bool;

/**
 *
 * @author GBarbieri
 */
abstract class FuncRelational extends BooleanOperators {

    public boolean any() {
        return Glm.any((Vec2b) this);
    }

    public boolean all() {
        return Glm.all((Vec2b) this);
    }

    public Vec2b not_() {
        return Glm.not((Vec2b) this, new Vec2b());
    }

    public Vec2b not() {
        return Glm.not((Vec2b) this, (Vec2b) this);
    }

    public Vec2bool lessThan__(final Vec2b b) {
        return Glm.lessThan((Vec2b) this, b, new Vec2bool());
    }

    public Vec2bool lessThan(final Vec2b b, final Vec2bool res) {
        return Glm.lessThan((Vec2b) this, b, res);
    }

    public Vec2bool lessThanEqual__(final Vec2b b) {
        return Glm.lessThanEqual((Vec2b) this, b, new Vec2bool());
    }

    public Vec2bool lessThanEqual(final Vec2b b, final Vec2bool res) {
        return Glm.lessThanEqual((Vec2b) this, b, res);
    }

    public Vec2bool greaterThan__(final Vec2b b) {
        return Glm.greaterThan((Vec2b) this, b, new Vec2bool());
    }

    public Vec2bool greaterThan(final Vec2b b, final Vec2bool res) {
        return Glm.greaterThan((Vec2b) this, b, res);
    }

    public Vec2bool greaterThanEqual__(final Vec2b b) {
        return Glm.greaterThanEqual((Vec2b) this, b, new Vec2bool());
    }

    public Vec2bool greaterThanEqual(final Vec2b b, final Vec2bool res) {
        return Glm.greaterThanEqual((Vec2b) this, b, res);
    }

    public Vec2bool equal__(final Vec2b b) {
        return Glm.equal((Vec2b) this, b, new Vec2bool());
    }

    public Vec2bool equal(final Vec2b b, final Vec2bool res) {
        return Glm.equal((Vec2b) this, b, res);
    }

    public Vec2bool notEqual__(final Vec2b b) {
        return Glm.notEqual((Vec2b) this, b, new Vec2bool());
    }

    public Vec2bool notEqual(final Vec2b b, final Vec2bool res) {
        return Glm.notEqual((Vec2b) this, b, res);
    }

    public Vec2b lessThan(final Vec2b b) {
        return Glm.lessThan((Vec2b) this, b, (Vec2b) this);
    }

    public Vec2b lessThan_(final Vec2b b) {
        return Glm.lessThan((Vec2b) this, b, new Vec2b());
    }

    public Vec2b lessThan(final Vec2b b, final Vec2b res) {
        return Glm.lessThan((Vec2b) this, b, res);
    }

    public Vec2b lessThanEqual(final Vec2b b) {
        return Glm.lessThanEqual((Vec2b) this, b, (Vec2b) this);
    }

    public Vec2b lessThanEqual_(final Vec2b b) {
        return Glm.lessThanEqual((Vec2b) this, b, new Vec2b());
    }

    public Vec2b lessThanEqual(final Vec2b b, final Vec2b res) {
        return Glm.lessThanEqual((Vec2b) this, b, res);
    }

    public Vec2b greaterThan(final Vec2b b) {
        return Glm.greaterThan((Vec2b) this, b, (Vec2b) this);
    }

    public Vec2b greaterThan_(final Vec2b b) {
        return Glm.greaterThan((Vec2b) this, b, new Vec2b());
    }

    public Vec2b greaterThan(final Vec2b b, final Vec2b res) {
        return Glm.greaterThan((Vec2b) this, b, res);
    }

    public Vec2b greaterThanEqual(final Vec2b b) {
        return Glm.greaterThanEqual((Vec2b) this, b, (Vec2b) this);
    }

    public Vec2b greaterThanEqual_(final Vec2b b) {
        return Glm.greaterThanEqual((Vec2b) this, b, new Vec2b());
    }

    public Vec2b greaterThanEqual(final Vec2b b, final Vec2b res) {
        return Glm.greaterThanEqual((Vec2b) this, b, res);
    }

    public Vec2b equal(final Vec2b b) {
        return Glm.equal((Vec2b) this, b, (Vec2b) this);
    }

    public Vec2b equal_(final Vec2b b) {
        return Glm.equal((Vec2b) this, b, new Vec2b());
    }

    public Vec2b equal(final Vec2b b, final Vec2b res) {
        return Glm.equal((Vec2b) this, b, res);
    }

    public Vec2b notEqual(final Vec2b b) {
        return Glm.notEqual((Vec2b) this, b, (Vec2b) this);
    }

    public Vec2b notEqual_(final Vec2b b) {
        return Glm.notEqual((Vec2b) this, b, new Vec2b());
    }

    public Vec2b notEqual(final Vec2b b, final Vec2b res) {
        return Glm.notEqual((Vec2b) this, b, res);
    }
}
