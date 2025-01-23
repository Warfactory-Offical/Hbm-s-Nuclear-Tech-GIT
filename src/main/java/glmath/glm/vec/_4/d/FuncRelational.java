/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.d;

import glmath.glm.Glm;
import glmath.glm.vec._4.bool.Vec4bool;

/**
 *
 * @author GBarbieri
 */
abstract class FuncRelational extends ArithmeticOperators {

    public boolean any() {
        return Glm.any((Vec4d) this);
    }

    public boolean all() {
        return Glm.all((Vec4d) this);
    }

    public Vec4d not_() {
        return Glm.not((Vec4d) this, new Vec4d());
    }

    public Vec4d not() {
        return Glm.not((Vec4d) this, (Vec4d) this);
    }

    public Vec4bool lessThan__(final Vec4d b) {
        return Glm.lessThan((Vec4d) this, b, new Vec4bool());
    }

    public Vec4bool lessThan(final Vec4d b, final Vec4bool res) {
        return Glm.lessThan((Vec4d) this, b, res);
    }

    public Vec4bool lessThanEqual__(final Vec4d b) {
        return Glm.lessThanEqual((Vec4d) this, b, new Vec4bool());
    }

    public Vec4bool lessThanEqual(final Vec4d b, final Vec4bool res) {
        return Glm.lessThanEqual((Vec4d) this, b, res);
    }

    public Vec4bool greaterThan__(final Vec4d b) {
        return Glm.greaterThan((Vec4d) this, b, new Vec4bool());
    }

    public Vec4bool greaterThan(final Vec4d b, final Vec4bool res) {
        return Glm.greaterThan((Vec4d) this, b, res);
    }

    public Vec4bool greaterThanEqual__(final Vec4d b) {
        return Glm.greaterThanEqual((Vec4d) this, b, new Vec4bool());
    }

    public Vec4bool greaterThanEqual(final Vec4d b, final Vec4bool res) {
        return Glm.greaterThanEqual((Vec4d) this, b, res);
    }

    public Vec4bool equal__(final Vec4d b) {
        return Glm.equal((Vec4d) this, b, new Vec4bool());
    }

    public Vec4bool equal(final Vec4d b, final Vec4bool res) {
        return Glm.equal((Vec4d) this, b, res);
    }

    public Vec4bool notEqual__(final Vec4d b) {
        return Glm.notEqual((Vec4d) this, b, new Vec4bool());
    }

    public Vec4bool notEqual(final Vec4d b, final Vec4bool res) {
        return Glm.notEqual((Vec4d) this, b, res);
    }

    public Vec4d lessThan(final Vec4d b) {
        return Glm.lessThan((Vec4d) this, b, (Vec4d) this);
    }

    public Vec4d lessThan_(final Vec4d b) {
        return Glm.lessThan((Vec4d) this, b, new Vec4d());
    }

    public Vec4d lessThan(final Vec4d b, final Vec4d res) {
        return Glm.lessThan((Vec4d) this, b, res);
    }

    public Vec4d lessThanEqual(final Vec4d b) {
        return Glm.lessThanEqual((Vec4d) this, b, (Vec4d) this);
    }

    public Vec4d lessThanEqual_(final Vec4d b) {
        return Glm.lessThanEqual((Vec4d) this, b, new Vec4d());
    }

    public Vec4d lessThanEqual(final Vec4d b, final Vec4d res) {
        return Glm.lessThanEqual((Vec4d) this, b, res);
    }

    public Vec4d greaterThan(final Vec4d b) {
        return Glm.greaterThan((Vec4d) this, b, (Vec4d) this);
    }

    public Vec4d greaterThan_(final Vec4d b) {
        return Glm.greaterThan((Vec4d) this, b, new Vec4d());
    }

    public Vec4d greaterThan(final Vec4d b, final Vec4d res) {
        return Glm.greaterThan((Vec4d) this, b, res);
    }

    public Vec4d greaterThanEqual(final Vec4d b) {
        return Glm.greaterThanEqual((Vec4d) this, b, (Vec4d) this);
    }

    public Vec4d greaterThanEqual_(final Vec4d b) {
        return Glm.greaterThanEqual((Vec4d) this, b, new Vec4d());
    }

    public Vec4d greaterThanEqual(final Vec4d b, final Vec4d res) {
        return Glm.greaterThanEqual((Vec4d) this, b, res);
    }

    public Vec4d equal(final Vec4d b) {
        return Glm.equal((Vec4d) this, b, (Vec4d) this);
    }

    public Vec4d equal_(final Vec4d b) {
        return Glm.equal((Vec4d) this, b, new Vec4d());
    }

    public Vec4d equal(final Vec4d b, final Vec4d res) {
        return Glm.equal((Vec4d) this, b, res);
    }

    public Vec4d notEqual(final Vec4d b) {
        return Glm.notEqual((Vec4d) this, b, (Vec4d) this);
    }

    public Vec4d notEqual_(final Vec4d b) {
        return Glm.notEqual((Vec4d) this, b, new Vec4d());
    }

    public Vec4d notEqual(final Vec4d b, final Vec4d res) {
        return Glm.notEqual((Vec4d) this, b, res);
    }
}
