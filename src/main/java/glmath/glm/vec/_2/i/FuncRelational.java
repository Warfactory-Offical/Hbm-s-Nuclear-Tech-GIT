/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2.i;

import glmath.glm.Glm;
import glmath.glm.vec._2.bool.Vec2bool;

/**
 *
 * @author fschaefers
 */
abstract class FuncRelational extends BooleanOperators {

    public boolean any() {
        return Glm.any((Vec2i) this);
    }

    public boolean all() {
        return Glm.all((Vec2i) this);
    }

    public Vec2i not_() {
        return Glm.not((Vec2i) this, new Vec2i());
    }

    public Vec2i not() {
        return Glm.not((Vec2i) this, (Vec2i) this);
    }

    public Vec2bool lessThan__(final Vec2i b) {
        return Glm.lessThan((Vec2i) this, b, new Vec2bool());
    }

    public Vec2bool lessThan(final Vec2i b, final Vec2bool res) {
        return Glm.lessThan((Vec2i) this, b, res);
    }

    public Vec2bool lessThanEqual__(final Vec2i b) {
        return Glm.lessThanEqual((Vec2i) this, b, new Vec2bool());
    }

    public Vec2bool lessThanEqual(final Vec2i b, final Vec2bool res) {
        return Glm.lessThanEqual((Vec2i) this, b, res);
    }

    public Vec2bool greaterThan__(final Vec2i b) {
        return Glm.greaterThan((Vec2i) this, b, new Vec2bool());
    }

    public Vec2bool greaterThan(final Vec2i b, final Vec2bool res) {
        return Glm.greaterThan((Vec2i) this, b, res);
    }

    public Vec2bool greaterThanEqual__(final Vec2i b) {
        return Glm.greaterThanEqual((Vec2i) this, b, new Vec2bool());
    }

    public Vec2bool greaterThanEqual(final Vec2i b, final Vec2bool res) {
        return Glm.greaterThanEqual((Vec2i) this, b, res);
    }

    public Vec2bool equal__(final Vec2i b) {
        return Glm.equal((Vec2i) this, b, new Vec2bool());
    }

    public Vec2bool equal(final Vec2i b, final Vec2bool res) {
        return Glm.equal((Vec2i) this, b, res);
    }

    public Vec2bool notEqual__(final Vec2i b) {
        return Glm.notEqual((Vec2i) this, b, new Vec2bool());
    }

    public Vec2bool notEqual(final Vec2i b, final Vec2bool res) {
        return Glm.notEqual((Vec2i) this, b, res);
    }

    public Vec2i lessThan(final Vec2i b) {
        return Glm.lessThan((Vec2i) this, b, (Vec2i) this);
    }

    public Vec2i lessThan_(final Vec2i b) {
        return Glm.lessThan((Vec2i) this, b, new Vec2i());
    }

    public Vec2i lessThan(final Vec2i b, final Vec2i res) {
        return Glm.lessThan((Vec2i) this, b, res);
    }

    public Vec2i lessThanEqual(final Vec2i b) {
        return Glm.lessThanEqual((Vec2i) this, b, (Vec2i) this);
    }

    public Vec2i lessThanEqual_(final Vec2i b) {
        return Glm.lessThanEqual((Vec2i) this, b, new Vec2i());
    }

    public Vec2i lessThanEqual(final Vec2i b, final Vec2i res) {
        return Glm.lessThanEqual((Vec2i) this, b, res);
    }

    public Vec2i greaterThan(final Vec2i b) {
        return Glm.greaterThan((Vec2i) this, b, (Vec2i) this);
    }

    public Vec2i greaterThan_(final Vec2i b) {
        return Glm.greaterThan((Vec2i) this, b, new Vec2i());
    }

    public Vec2i greaterThan(final Vec2i b, final Vec2i res) {
        return Glm.greaterThan((Vec2i) this, b, res);
    }

    public Vec2i greaterThanEqual(final Vec2i b) {
        return Glm.greaterThanEqual((Vec2i) this, b, (Vec2i) this);
    }

    public Vec2i greaterThanEqual_(final Vec2i b) {
        return Glm.greaterThanEqual((Vec2i) this, b, new Vec2i());
    }

    public Vec2i greaterThanEqual(final Vec2i b, final Vec2i res) {
        return Glm.greaterThanEqual((Vec2i) this, b, res);
    }

    public Vec2i equal(final Vec2i b) {
        return Glm.equal((Vec2i) this, b, (Vec2i) this);
    }

    public Vec2i equal_(final Vec2i b) {
        return Glm.equal((Vec2i) this, b, new Vec2i());
    }

    public Vec2i equal(final Vec2i b, final Vec2i res) {
        return Glm.equal((Vec2i) this, b, res);
    }

    public Vec2i notEqual(final Vec2i b) {
        return Glm.notEqual((Vec2i) this, b, (Vec2i) this);
    }

    public Vec2i notEqual_(final Vec2i b) {
        return Glm.notEqual((Vec2i) this, b, new Vec2i());
    }

    public Vec2i notEqual(final Vec2i b, final Vec2i res) {
        return Glm.notEqual((Vec2i) this, b, res);
    }
}
