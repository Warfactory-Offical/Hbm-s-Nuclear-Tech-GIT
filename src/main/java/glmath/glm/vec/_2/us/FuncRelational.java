/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2.us;

import glmath.glm.Glm;
import glmath.glm.vec._2.bool.Vec2bool;

/**
 *
 * @author GBarbieri
 */
abstract class FuncRelational extends ArithmeticOperators {

    public boolean any() {
        return Glm.any((Vec2us) this);
    }

    public boolean all() {
        return Glm.all((Vec2us) this);
    }

    public Vec2us not_() {
        return Glm.not((Vec2us) this, new Vec2us());
    }

    public Vec2us not() {
        return Glm.not((Vec2us) this, (Vec2us) this);
    }

    public Vec2bool lessThan__(final Vec2us b) {
        return Glm.lessThan((Vec2us) this, b, new Vec2bool());
    }

    public Vec2bool lessThan(final Vec2us b, final Vec2bool res) {
        return Glm.lessThan((Vec2us) this, b, res);
    }

    public Vec2bool lessThanEqual__(final Vec2us b) {
        return Glm.lessThanEqual((Vec2us) this, b, new Vec2bool());
    }

    public Vec2bool lessThanEqual(final Vec2us b, final Vec2bool res) {
        return Glm.lessThanEqual((Vec2us) this, b, res);
    }

    public Vec2bool greaterThan__(final Vec2us b) {
        return Glm.greaterThan((Vec2us) this, b, new Vec2bool());
    }

    public Vec2bool greaterThan(final Vec2us b, final Vec2bool res) {
        return Glm.greaterThan((Vec2us) this, b, res);
    }

    public Vec2bool greaterThanEqual__(final Vec2us b) {
        return Glm.greaterThanEqual((Vec2us) this, b, new Vec2bool());
    }

    public Vec2bool greaterThanEqual(final Vec2us b, final Vec2bool res) {
        return Glm.greaterThanEqual((Vec2us) this, b, res);
    }

    public Vec2bool equal__(final Vec2us b) {
        return Glm.equal((Vec2us) this, b, new Vec2bool());
    }

    public Vec2bool equal(final Vec2us b, final Vec2bool res) {
        return Glm.equal((Vec2us) this, b, res);
    }

    public Vec2bool notEqual__(final Vec2us b) {
        return Glm.notEqual((Vec2us) this, b, new Vec2bool());
    }

    public Vec2bool notEqual(final Vec2us b, final Vec2bool res) {
        return Glm.notEqual((Vec2us) this, b, res);
    }

    public Vec2us lessThan(final Vec2us b) {
        return Glm.lessThan((Vec2us) this, b, (Vec2us) this);
    }

    public Vec2us lessThan_(final Vec2us b) {
        return Glm.lessThan((Vec2us) this, b, new Vec2us());
    }

    public Vec2us lessThan(final Vec2us b, final Vec2us res) {
        return Glm.lessThan((Vec2us) this, b, res);
    }

    public Vec2us lessThanEqual(final Vec2us b) {
        return Glm.lessThanEqual((Vec2us) this, b, (Vec2us) this);
    }

    public Vec2us lessThanEqual_(final Vec2us b) {
        return Glm.lessThanEqual((Vec2us) this, b, new Vec2us());
    }

    public Vec2us lessThanEqual(final Vec2us b, final Vec2us res) {
        return Glm.lessThanEqual((Vec2us) this, b, res);
    }

    public Vec2us greaterThan(final Vec2us b) {
        return Glm.greaterThan((Vec2us) this, b, (Vec2us) this);
    }

    public Vec2us greaterThan_(final Vec2us b) {
        return Glm.greaterThan((Vec2us) this, b, new Vec2us());
    }

    public Vec2us greaterThan(final Vec2us b, final Vec2us res) {
        return Glm.greaterThan((Vec2us) this, b, res);
    }

    public Vec2us greaterThanEqual(final Vec2us b) {
        return Glm.greaterThanEqual((Vec2us) this, b, (Vec2us) this);
    }

    public Vec2us greaterThanEqual_(final Vec2us b) {
        return Glm.greaterThanEqual((Vec2us) this, b, new Vec2us());
    }

    public Vec2us greaterThanEqual(final Vec2us b, final Vec2us res) {
        return Glm.greaterThanEqual((Vec2us) this, b, res);
    }

    public Vec2us equal(final Vec2us b) {
        return Glm.equal((Vec2us) this, b, (Vec2us) this);
    }

    public Vec2us equal_(final Vec2us b) {
        return Glm.equal((Vec2us) this, b, new Vec2us());
    }

    public Vec2us equal(final Vec2us b, final Vec2us res) {
        return Glm.equal((Vec2us) this, b, res);
    }

    public Vec2us notEqual(final Vec2us b) {
        return Glm.notEqual((Vec2us) this, b, (Vec2us) this);
    }

    public Vec2us notEqual_(final Vec2us b) {
        return Glm.notEqual((Vec2us) this, b, new Vec2us());
    }

    public Vec2us notEqual(final Vec2us b, final Vec2us res) {
        return Glm.notEqual((Vec2us) this, b, res);
    }

}
