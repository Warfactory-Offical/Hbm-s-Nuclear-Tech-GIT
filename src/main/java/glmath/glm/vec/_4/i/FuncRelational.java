/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.i;

import glmath.glm.Glm;
import glmath.glm.vec._4.bool.Vec4bool;

/**
 *
 * @author fschaefers
 */
abstract class FuncRelational extends funcCommon {

    public boolean any() {
        return Glm.any((Vec4i) this);
    }

    public boolean all() {
        return Glm.all((Vec4i) this);
    }
    
    public Vec4i not_() {
        return Glm.not((Vec4i) this, new Vec4i());
    }

    public Vec4i not() {
        return Glm.not((Vec4i) this, (Vec4i) this);
    }
    
    public Vec4bool lessThan__(final Vec4i b) {
        return Glm.lessThan((Vec4i) this, b, new Vec4bool());
    }

    public Vec4bool lessThan(final Vec4i b, final Vec4bool res) {
        return Glm.lessThan((Vec4i) this, b, res);
    }

    public Vec4bool lessThanEqual__(final Vec4i b) {
        return Glm.lessThanEqual((Vec4i) this, b, new Vec4bool());
    }

    public Vec4bool lessThanEqual(final Vec4i b, final Vec4bool res) {
        return Glm.lessThanEqual((Vec4i) this, b, res);
    }

    public Vec4bool greaterThan__(final Vec4i b) {
        return Glm.greaterThan((Vec4i) this, b, new Vec4bool());
    }

    public Vec4bool greaterThan(final Vec4i b, final Vec4bool res) {
        return Glm.greaterThan((Vec4i) this, b, res);
    }

    public Vec4bool greaterThanEqual__(final Vec4i b) {
        return Glm.greaterThanEqual((Vec4i) this, b, new Vec4bool());
    }

    public Vec4bool greaterThanEqual(final Vec4i b, final Vec4bool res) {
        return Glm.greaterThanEqual((Vec4i) this, b, res);
    }

    public Vec4bool equal__(final Vec4i b) {
        return Glm.equal((Vec4i) this, b, new Vec4bool());
    }

    public Vec4bool equal(final Vec4i b, final Vec4bool res) {
        return Glm.equal((Vec4i) this, b, res);
    }

    public Vec4bool notEqual__(final Vec4i b) {
        return Glm.notEqual((Vec4i) this, b, new Vec4bool());
    }

    public Vec4bool notEqual(final Vec4i b, final Vec4bool res) {
        return Glm.notEqual((Vec4i) this, b, res);
    }

    public Vec4i lessThan(final Vec4i b) {
        return Glm.lessThan((Vec4i) this, b, (Vec4i) this);
    }

    public Vec4i lessThan_(final Vec4i b) {
        return Glm.lessThan((Vec4i) this, b, new Vec4i());
    }

    public Vec4i lessThan(final Vec4i b, final Vec4i res) {
        return Glm.lessThan((Vec4i) this, b, res);
    }

    public Vec4i lessThanEqual(final Vec4i b) {
        return Glm.lessThanEqual((Vec4i) this, b, (Vec4i) this);
    }

    public Vec4i lessThanEqual_(final Vec4i b) {
        return Glm.lessThanEqual((Vec4i) this, b, new Vec4i());
    }

    public Vec4i lessThanEqual(final Vec4i b, final Vec4i res) {
        return Glm.lessThanEqual((Vec4i) this, b, res);
    }

    public Vec4i greaterThan(final Vec4i b) {
        return Glm.greaterThan((Vec4i) this, b, (Vec4i) this);
    }

    public Vec4i greaterThan_(final Vec4i b) {
        return Glm.greaterThan((Vec4i) this, b, new Vec4i());
    }

    public Vec4i greaterThan(final Vec4i b, final Vec4i res) {
        return Glm.greaterThan((Vec4i) this, b, res);
    }

    public Vec4i greaterThanEqual(final Vec4i b) {
        return Glm.greaterThanEqual((Vec4i) this, b, (Vec4i) this);
    }

    public Vec4i greaterThanEqual_(final Vec4i b) {
        return Glm.greaterThanEqual((Vec4i) this, b, new Vec4i());
    }

    public Vec4i greaterThanEqual(final Vec4i b, final Vec4i res) {
        return Glm.greaterThanEqual((Vec4i) this, b, res);
    }

    public Vec4i equal(final Vec4i b) {
        return Glm.equal((Vec4i) this, b, (Vec4i) this);
    }

    public Vec4i equal_(final Vec4i b) {
        return Glm.equal((Vec4i) this, b, new Vec4i());
    }

    public Vec4i equal(final Vec4i b, final Vec4i res) {
        return Glm.equal((Vec4i) this, b, res);
    }

    public Vec4i notEqual(final Vec4i b) {
        return Glm.notEqual((Vec4i) this, b, (Vec4i) this);
    }

    public Vec4i notEqual_(final Vec4i b) {
        return Glm.notEqual((Vec4i) this, b, new Vec4i());
    }

    public Vec4i notEqual(final Vec4i b, final Vec4i res) {
        return Glm.notEqual((Vec4i) this, b, res);
    }
}
