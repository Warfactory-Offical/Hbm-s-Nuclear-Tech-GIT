/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.l;

import glmath.glm.Glm;
import glmath.glm.vec._4.bool.Vec4bool;

/**
 *
 * @author GBarbieri
 */
abstract class FuncRelational extends ArithmeticOperators {

    public boolean any() {
        return Glm.any((Vec4l) this);
    }

    public boolean all() {
        return Glm.all((Vec4l) this);
    }

    public Vec4l not_() {
        return Glm.not((Vec4l) this, new Vec4l());
    }

    public Vec4l not() {
        return Glm.not((Vec4l) this, (Vec4l) this);
    }

    public Vec4bool lessThan__(final Vec4l b) {
        return Glm.lessThan((Vec4l) this, b, new Vec4bool());
    }

    public Vec4bool lessThan(final Vec4l a, final Vec4l b, final Vec4bool res) {
        return Glm.lessThan((Vec4l) this, b, res);
    }

    public Vec4bool lessThanEqual__(final Vec4l b) {
        return Glm.lessThanEqual((Vec4l) this, b, new Vec4bool());
    }

    public Vec4bool lessThanEqual(final Vec4l b, final Vec4bool res) {
        return Glm.lessThanEqual((Vec4l) this, b, res);
    }

    public Vec4bool greaterThan__(final Vec4l b) {
        return Glm.greaterThan((Vec4l) this, b, new Vec4bool());
    }

    public Vec4bool greaterThan(final Vec4l b, final Vec4bool res) {
        return Glm.greaterThan((Vec4l) this, b, res);
    }

    public Vec4bool greaterThanEqual__(final Vec4l b) {
        return Glm.greaterThanEqual((Vec4l) this, b, new Vec4bool());
    }

    public Vec4bool greaterThanEqual(final Vec4l b, final Vec4bool res) {
        return Glm.greaterThanEqual((Vec4l) this, b, res);
    }

    public Vec4bool equal__(final Vec4l b) {
        return Glm.equal((Vec4l) this, b, new Vec4bool());
    }

    public Vec4bool equal(final Vec4l b, final Vec4bool res) {
        return Glm.equal((Vec4l) this, b, res);
    }

    public Vec4bool notEqual__(final Vec4l b) {
        return Glm.notEqual((Vec4l) this, b, new Vec4bool());
    }

    public Vec4bool notEqual(final Vec4l b, final Vec4bool res) {
        return Glm.notEqual((Vec4l) this, b, res);
    }

    public Vec4l lessThan(final Vec4l b) {
        return Glm.lessThan((Vec4l) this, b, (Vec4l) this);
    }

    public Vec4l lessThan_(final Vec4l b) {
        return Glm.lessThan((Vec4l) this, b, new Vec4l());
    }

    public Vec4l lessThan(final Vec4l a, final Vec4l b, final Vec4l res) {
        return Glm.lessThan((Vec4l) this, b, res);
    }

    public Vec4l lessThanEqual(final Vec4l b) {
        return Glm.lessThanEqual((Vec4l) this, b, (Vec4l) this);
    }

    public Vec4l lessThanEqual_(final Vec4l b) {
        return Glm.lessThanEqual((Vec4l) this, b, new Vec4l());
    }

    public Vec4l lessThanEqual(final Vec4l b, final Vec4l res) {
        return Glm.lessThanEqual((Vec4l) this, b, res);
    }

    public Vec4l greaterThan(final Vec4l b) {
        return Glm.greaterThan((Vec4l) this, b, (Vec4l) this);
    }

    public Vec4l greaterThan_(final Vec4l b) {
        return Glm.greaterThan((Vec4l) this, b, new Vec4l());
    }

    public Vec4l greaterThan(final Vec4l b, final Vec4l res) {
        return Glm.greaterThan((Vec4l) this, b, res);
    }

    public Vec4l greaterThanEqual(final Vec4l b) {
        return Glm.greaterThanEqual((Vec4l) this, b, (Vec4l) this);
    }

    public Vec4l greaterThanEqual_(final Vec4l b) {
        return Glm.greaterThanEqual((Vec4l) this, b, new Vec4l());
    }

    public Vec4l greaterThanEqual(final Vec4l b, final Vec4l res) {
        return Glm.greaterThanEqual((Vec4l) this, b, res);
    }

    public Vec4l equal(final Vec4l b) {
        return Glm.equal((Vec4l) this, b, (Vec4l) this);
    }

    public Vec4l equal_(final Vec4l b) {
        return Glm.equal((Vec4l) this, b, new Vec4l());
    }

    public Vec4l equal(final Vec4l b, final Vec4l res) {
        return Glm.equal((Vec4l) this, b, res);
    }

    public Vec4l notEqual(final Vec4l b) {
        return Glm.notEqual((Vec4l) this, b, (Vec4l) this);
    }

    public Vec4l notEqual_(final Vec4l b) {
        return Glm.notEqual((Vec4l) this, b, new Vec4l());
    }

    public Vec4l notEqual(final Vec4l b, final Vec4l res) {
        return Glm.notEqual((Vec4l) this, b, res);
    }
}
