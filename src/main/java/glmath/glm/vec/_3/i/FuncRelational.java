/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3.i;

import glmath.glm.Glm;
import glmath.glm.vec._3.bool.Vec3bool;

/**
 *
 * @author fschaefers
 */
abstract class FuncRelational extends funcCommon {

    public boolean any() {
        return Glm.any((Vec3i) this);
    }

    public boolean all() {
        return Glm.all((Vec3i) this);
    }
    
    public Vec3i not_() {
        return Glm.not((Vec3i) this, new Vec3i());
    }

    public Vec3i not() {
        return Glm.not((Vec3i) this, (Vec3i) this);
    }
    
    public Vec3bool lessThan__(final Vec3i b) {
        return Glm.lessThan((Vec3i) this, b, new Vec3bool());
    }

    public Vec3bool lessThan(final Vec3i b, final Vec3bool res) {
        return Glm.lessThan((Vec3i) this, b, res);
    }

    public Vec3bool lessThanEqual__(final Vec3i b) {
        return Glm.lessThanEqual((Vec3i) this, b, new Vec3bool());
    }

    public Vec3bool lessThanEqual(final Vec3i b, final Vec3bool res) {
        return Glm.lessThanEqual((Vec3i) this, b, res);
    }

    public Vec3bool greaterThan__(final Vec3i b) {
        return Glm.greaterThan((Vec3i) this, b, new Vec3bool());
    }

    public Vec3bool greaterThan(final Vec3i b, final Vec3bool res) {
        return Glm.greaterThan((Vec3i) this, b, res);
    }

    public Vec3bool greaterThanEqual__(final Vec3i b) {
        return Glm.greaterThanEqual((Vec3i) this, b, new Vec3bool());
    }

    public Vec3bool greaterThanEqual(final Vec3i b, final Vec3bool res) {
        return Glm.greaterThanEqual((Vec3i) this, b, res);
    }

    public Vec3bool equal__(final Vec3i b) {
        return Glm.equal((Vec3i) this, b, new Vec3bool());
    }

    public Vec3bool equal(final Vec3i b, final Vec3bool res) {
        return Glm.equal((Vec3i) this, b, res);
    }

    public Vec3bool notEqual__(final Vec3i b) {
        return Glm.notEqual((Vec3i) this, b, new Vec3bool());
    }

    public Vec3bool notEqual(final Vec3i b, final Vec3bool res) {
        return Glm.notEqual((Vec3i) this, b, res);
    }

    public Vec3i lessThan(final Vec3i b) {
        return Glm.lessThan((Vec3i) this, b, (Vec3i) this);
    }

    public Vec3i lessThan_(final Vec3i b) {
        return Glm.lessThan((Vec3i) this, b, new Vec3i());
    }

    public Vec3i lessThan(final Vec3i b, final Vec3i res) {
        return Glm.lessThan((Vec3i) this, b, res);
    }

    public Vec3i lessThanEqual(final Vec3i b) {
        return Glm.lessThanEqual((Vec3i) this, b, (Vec3i) this);
    }

    public Vec3i lessThanEqual_(final Vec3i b) {
        return Glm.lessThanEqual((Vec3i) this, b, new Vec3i());
    }

    public Vec3i lessThanEqual(final Vec3i b, final Vec3i res) {
        return Glm.lessThanEqual((Vec3i) this, b, res);
    }

    public Vec3i greaterThan(final Vec3i b) {
        return Glm.greaterThan((Vec3i) this, b, (Vec3i) this);
    }

    public Vec3i greaterThan_(final Vec3i b) {
        return Glm.greaterThan((Vec3i) this, b, new Vec3i());
    }

    public Vec3i greaterThan(final Vec3i b, final Vec3i res) {
        return Glm.greaterThan((Vec3i) this, b, res);
    }

    public Vec3i greaterThanEqual(final Vec3i b) {
        return Glm.greaterThanEqual((Vec3i) this, b, (Vec3i) this);
    }

    public Vec3i greaterThanEqual_(final Vec3i b) {
        return Glm.greaterThanEqual((Vec3i) this, b, new Vec3i());
    }

    public Vec3i greaterThanEqual(final Vec3i b, final Vec3i res) {
        return Glm.greaterThanEqual((Vec3i) this, b, res);
    }

    public Vec3i equal(final Vec3i b) {
        return Glm.equal((Vec3i) this, b, (Vec3i) this);
    }

    public Vec3i equal_(final Vec3i b) {
        return Glm.equal((Vec3i) this, b, new Vec3i());
    }

    public Vec3i equal(final Vec3i b, final Vec3i res) {
        return Glm.equal((Vec3i) this, b, res);
    }

    public Vec3i notEqual(final Vec3i b) {
        return Glm.notEqual((Vec3i) this, b, (Vec3i) this);
    }

    public Vec3i notEqual_(final Vec3i b) {
        return Glm.notEqual((Vec3i) this, b, new Vec3i());
    }

    public Vec3i notEqual(final Vec3i b, final Vec3i res) {
        return Glm.notEqual((Vec3i) this, b, res);
    }
}
