/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.d;

import glmath.glm.Glm;
import glmath.glm.vec._3.bool.Vec3bool;

/**
 *
 * @author GBarbieri
 */
abstract class FuncRelational extends ArithmeticOperators {

    public boolean any() {
        return Glm.any((Vec3d) this);
    }

    public boolean all() {
        return Glm.all((Vec3d) this);
    }

    public Vec3d not_() {
        return Glm.not((Vec3d) this, new Vec3d());
    }

    public Vec3d not() {
        return Glm.not((Vec3d) this, (Vec3d) this);
    }

    public Vec3bool lessThan__(final Vec3d b) {
        return Glm.lessThan((Vec3d) this, b, new Vec3bool());
    }

    public Vec3bool lessThan(final Vec3d b, final Vec3bool res) {
        return Glm.lessThan((Vec3d) this, b, res);
    }

    public Vec3bool lessThanEqual__(final Vec3d b) {
        return Glm.lessThanEqual((Vec3d) this, b, new Vec3bool());
    }

    public Vec3bool lessThanEqual(final Vec3d b, final Vec3bool res) {
        return Glm.lessThanEqual((Vec3d) this, b, res);
    }

    public Vec3bool greaterThan__(final Vec3d b) {
        return Glm.greaterThan((Vec3d) this, b, new Vec3bool());
    }

    public Vec3bool greaterThan(final Vec3d b, final Vec3bool res) {
        return Glm.greaterThan((Vec3d) this, b, res);
    }

    public Vec3bool greaterThanEqual__(final Vec3d b) {
        return Glm.greaterThanEqual((Vec3d) this, b, new Vec3bool());
    }

    public Vec3bool greaterThanEqual(final Vec3d b, final Vec3bool res) {
        return Glm.greaterThanEqual((Vec3d) this, b, res);
    }

    public Vec3bool equal__(final Vec3d b) {
        return Glm.equal((Vec3d) this, b, new Vec3bool());
    }

    public Vec3bool equal(final Vec3d b, final Vec3bool res) {
        return Glm.equal((Vec3d) this, b, res);
    }

    public Vec3bool notEqual__(final Vec3d b) {
        return Glm.notEqual((Vec3d) this, b, new Vec3bool());
    }

    public Vec3bool notEqual(final Vec3d b, final Vec3bool res) {
        return Glm.notEqual((Vec3d) this, b, res);
    }

    public Vec3d lessThan(final Vec3d b) {
        return Glm.lessThan((Vec3d) this, b, (Vec3d) this);
    }

    public Vec3d lessThan_(final Vec3d b) {
        return Glm.lessThan((Vec3d) this, b, new Vec3d());
    }

    public Vec3d lessThan(final Vec3d b, final Vec3d res) {
        return Glm.lessThan((Vec3d) this, b, res);
    }

    public Vec3d lessThanEqual(final Vec3d b) {
        return Glm.lessThanEqual((Vec3d) this, b, (Vec3d) this);
    }

    public Vec3d lessThanEqual_(final Vec3d b) {
        return Glm.lessThanEqual((Vec3d) this, b, new Vec3d());
    }

    public Vec3d lessThanEqual(final Vec3d b, final Vec3d res) {
        return Glm.lessThanEqual((Vec3d) this, b, res);
    }

    public Vec3d greaterThan(final Vec3d b) {
        return Glm.greaterThan((Vec3d) this, b, (Vec3d) this);
    }

    public Vec3d greaterThan_(final Vec3d b) {
        return Glm.greaterThan((Vec3d) this, b, new Vec3d());
    }

    public Vec3d greaterThan(final Vec3d b, final Vec3d res) {
        return Glm.greaterThan((Vec3d) this, b, res);
    }

    public Vec3d greaterThanEqual(final Vec3d b) {
        return Glm.greaterThanEqual((Vec3d) this, b, (Vec3d) this);
    }

    public Vec3d greaterThanEqual_(final Vec3d b) {
        return Glm.greaterThanEqual((Vec3d) this, b, new Vec3d());
    }

    public Vec3d greaterThanEqual(final Vec3d b, final Vec3d res) {
        return Glm.greaterThanEqual((Vec3d) this, b, res);
    }

    public Vec3d equal(final Vec3d b) {
        return Glm.equal((Vec3d) this, b, (Vec3d) this);
    }

    public Vec3d equal_(final Vec3d b) {
        return Glm.equal((Vec3d) this, b, new Vec3d());
    }

    public Vec3d equal(final Vec3d b, final Vec3d res) {
        return Glm.equal((Vec3d) this, b, res);
    }

    public Vec3d notEqual(final Vec3d b) {
        return Glm.notEqual((Vec3d) this, b, (Vec3d) this);
    }

    public Vec3d notEqual_(final Vec3d b) {
        return Glm.notEqual((Vec3d) this, b, new Vec3d());
    }

    public Vec3d notEqual(final Vec3d b, final Vec3d res) {
        return Glm.notEqual((Vec3d) this, b, res);
    }
}
