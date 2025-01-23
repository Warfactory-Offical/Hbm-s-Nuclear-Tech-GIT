/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.ui;

import glmath.glm.Glm;
import glmath.glm.vec._4.bool.Vec4bool;

/**
 *
 * @author fschaefers
 */
abstract class FuncRelational extends ArithmeticOperators {

    public boolean any() {
        return Glm.any((Vec4ui) this);
    }

    public boolean all() {
        return Glm.all((Vec4ui) this);
    }

    public Vec4ui not_() {
        return Glm.not((Vec4ui) this, new Vec4ui());
    }

    public Vec4ui not() {
        return Glm.not((Vec4ui) this, (Vec4ui) this);
    }

    public Vec4bool lessThan__(final Vec4ui b) {
        return Glm.lessThan((Vec4ui) this, b, new Vec4bool());
    }

    public Vec4bool lessThan(final Vec4ui b, final Vec4bool res) {
        return Glm.lessThan((Vec4ui) this, b, res);
    }

    public Vec4bool lessThanEqual__(final Vec4ui b) {
        return Glm.lessThanEqual((Vec4ui) this, b, new Vec4bool());
    }

    public Vec4bool lessThanEqual(final Vec4ui b, final Vec4bool res) {
        return Glm.lessThanEqual((Vec4ui) this, b, res);
    }

    public Vec4bool greaterThan__(final Vec4ui b) {
        return Glm.greaterThan((Vec4ui) this, b, new Vec4bool());
    }

    public Vec4bool greaterThan(final Vec4ui b, final Vec4bool res) {
        return Glm.greaterThan((Vec4ui) this, b, res);
    }

    public Vec4bool greaterThanEqual__(final Vec4ui b) {
        return Glm.greaterThanEqual((Vec4ui) this, b, new Vec4bool());
    }

    public Vec4bool greaterThanEqual(final Vec4ui b, final Vec4bool res) {
        return Glm.greaterThanEqual((Vec4ui) this, b, res);
    }

    public Vec4bool equal__(final Vec4ui b) {
        return Glm.equal((Vec4ui) this, b, new Vec4bool());
    }

    public Vec4bool equal(final Vec4ui b, final Vec4bool res) {
        return Glm.equal((Vec4ui) this, b, res);
    }

    public Vec4bool notEqual__(final Vec4ui b) {
        return Glm.notEqual((Vec4ui) this, b, new Vec4bool());
    }

    public Vec4bool notEqual(final Vec4ui b, final Vec4bool res) {
        return Glm.notEqual((Vec4ui) this, b, res);
    }

    public Vec4ui lessThan(final Vec4ui b) {
        return Glm.lessThan((Vec4ui) this, b, (Vec4ui) this);
    }

    public Vec4ui lessThan_(final Vec4ui b) {
        return Glm.lessThan((Vec4ui) this, b, new Vec4ui());
    }

    public Vec4ui lessThan(final Vec4ui b, final Vec4ui res) {
        return Glm.lessThan((Vec4ui) this, b, res);
    }

    public Vec4ui lessThanEqual(final Vec4ui b) {
        return Glm.lessThanEqual((Vec4ui) this, b, (Vec4ui) this);
    }

    public Vec4ui lessThanEqual_(final Vec4ui b) {
        return Glm.lessThanEqual((Vec4ui) this, b, new Vec4ui());
    }

    public Vec4ui lessThanEqual(final Vec4ui b, final Vec4ui res) {
        return Glm.lessThanEqual((Vec4ui) this, b, res);
    }

    public Vec4ui greaterThan(final Vec4ui b) {
        return Glm.greaterThan((Vec4ui) this, b, (Vec4ui) this);
    }

    public Vec4ui greaterThan_(final Vec4ui b) {
        return Glm.greaterThan((Vec4ui) this, b, new Vec4ui());
    }

    public Vec4ui greaterThan(final Vec4ui b, final Vec4ui res) {
        return Glm.greaterThan((Vec4ui) this, b, res);
    }

    public Vec4ui greaterThanEqual(final Vec4ui b) {
        return Glm.greaterThanEqual((Vec4ui) this, b, (Vec4ui) this);
    }

    public Vec4ui greaterThanEqual_(final Vec4ui b) {
        return Glm.greaterThanEqual((Vec4ui) this, b, new Vec4ui());
    }

    public Vec4ui greaterThanEqual(final Vec4ui b, final Vec4ui res) {
        return Glm.greaterThanEqual((Vec4ui) this, b, res);
    }

    public Vec4ui equal(final Vec4ui b) {
        return Glm.equal((Vec4ui) this, b, (Vec4ui) this);
    }

    public Vec4ui equal_(final Vec4ui b) {
        return Glm.equal((Vec4ui) this, b, new Vec4ui());
    }

    public Vec4ui equal(final Vec4ui b, final Vec4ui res) {
        return Glm.equal((Vec4ui) this, b, res);
    }

    public Vec4ui notEqual(final Vec4ui b) {
        return Glm.notEqual((Vec4ui) this, b, (Vec4ui) this);
    }

    public Vec4ui notEqual_(final Vec4ui b) {
        return Glm.notEqual((Vec4ui) this, b, new Vec4ui());
    }

    public Vec4ui notEqual(final Vec4ui b, final Vec4ui res) {
        return Glm.notEqual((Vec4ui) this, b, res);
    }
}
