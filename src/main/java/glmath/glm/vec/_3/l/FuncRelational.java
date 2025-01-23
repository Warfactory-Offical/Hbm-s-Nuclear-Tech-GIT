/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.l;

import glmath.glm.Glm;
import glmath.glm.vec._3.bool.Vec3bool;

/**
 *
 * @author GBarbieri
 */
abstract class FuncRelational extends ArithmeticOperators {

    public boolean any() {
        return Glm.any((Vec3l) this);
    }

    public boolean all() {
        return Glm.all((Vec3l) this);
    }

    public Vec3l not_() {
        return Glm.not((Vec3l) this, new Vec3l());
    }

    public Vec3l not() {
        return Glm.not((Vec3l) this, (Vec3l) this);
    }

    public Vec3bool lessThan__(final Vec3l b) {
        return Glm.lessThan((Vec3l) this, b, new Vec3bool());
    }

    public Vec3bool lessThan(final Vec3l a, final Vec3l b, final Vec3bool res) {
        return Glm.lessThan((Vec3l) this, b, res);
    }

    public Vec3bool lessThanEqual__(final Vec3l b) {
        return Glm.lessThanEqual((Vec3l) this, b, new Vec3bool());
    }

    public Vec3bool lessThanEqual(final Vec3l b, final Vec3bool res) {
        return Glm.lessThanEqual((Vec3l) this, b, res);
    }

    public Vec3bool greaterThan__(final Vec3l b) {
        return Glm.greaterThan((Vec3l) this, b, new Vec3bool());
    }

    public Vec3bool greaterThan(final Vec3l b, final Vec3bool res) {
        return Glm.greaterThan((Vec3l) this, b, res);
    }

    public Vec3bool greaterThanEqual__(final Vec3l b) {
        return Glm.greaterThanEqual((Vec3l) this, b, new Vec3bool());
    }

    public Vec3bool greaterThanEqual(final Vec3l b, final Vec3bool res) {
        return Glm.greaterThanEqual((Vec3l) this, b, res);
    }

    public Vec3bool equal__(final Vec3l b) {
        return Glm.equal((Vec3l) this, b, new Vec3bool());
    }

    public Vec3bool equal(final Vec3l b, final Vec3bool res) {
        return Glm.equal((Vec3l) this, b, res);
    }

    public Vec3bool notEqual__(final Vec3l b) {
        return Glm.notEqual((Vec3l) this, b, new Vec3bool());
    }

    public Vec3bool notEqual(final Vec3l b, final Vec3bool res) {
        return Glm.notEqual((Vec3l) this, b, res);
    }

    public Vec3l lessThan(final Vec3l b) {
        return Glm.lessThan((Vec3l) this, b, (Vec3l) this);
    }

    public Vec3l lessThan_(final Vec3l b) {
        return Glm.lessThan((Vec3l) this, b, new Vec3l());
    }

    public Vec3l lessThan(final Vec3l a, final Vec3l b, final Vec3l res) {
        return Glm.lessThan((Vec3l) this, b, res);
    }

    public Vec3l lessThanEqual(final Vec3l b) {
        return Glm.lessThanEqual((Vec3l) this, b, (Vec3l) this);
    }

    public Vec3l lessThanEqual_(final Vec3l b) {
        return Glm.lessThanEqual((Vec3l) this, b, new Vec3l());
    }

    public Vec3l lessThanEqual(final Vec3l b, final Vec3l res) {
        return Glm.lessThanEqual((Vec3l) this, b, res);
    }

    public Vec3l greaterThan(final Vec3l b) {
        return Glm.greaterThan((Vec3l) this, b, (Vec3l) this);
    }

    public Vec3l greaterThan_(final Vec3l b) {
        return Glm.greaterThan((Vec3l) this, b, new Vec3l());
    }

    public Vec3l greaterThan(final Vec3l b, final Vec3l res) {
        return Glm.greaterThan((Vec3l) this, b, res);
    }

    public Vec3l greaterThanEqual(final Vec3l b) {
        return Glm.greaterThanEqual((Vec3l) this, b, (Vec3l) this);
    }

    public Vec3l greaterThanEqual_(final Vec3l b) {
        return Glm.greaterThanEqual((Vec3l) this, b, new Vec3l());
    }

    public Vec3l greaterThanEqual(final Vec3l b, final Vec3l res) {
        return Glm.greaterThanEqual((Vec3l) this, b, res);
    }

    public Vec3l equal(final Vec3l b) {
        return Glm.equal((Vec3l) this, b, (Vec3l) this);
    }

    public Vec3l equal_(final Vec3l b) {
        return Glm.equal((Vec3l) this, b, new Vec3l());
    }

    public Vec3l equal(final Vec3l b, final Vec3l res) {
        return Glm.equal((Vec3l) this, b, res);
    }

    public Vec3l notEqual(final Vec3l b) {
        return Glm.notEqual((Vec3l) this, b, (Vec3l) this);
    }

    public Vec3l notEqual_(final Vec3l b) {
        return Glm.notEqual((Vec3l) this, b, new Vec3l());
    }

    public Vec3l notEqual(final Vec3l b, final Vec3l res) {
        return Glm.notEqual((Vec3l) this, b, res);
    }
}
