/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._3;

import glmath.glm.Glm;
import glmath.glm.vec._3.bool.Vec3bool;

/**
 *
 * @author elect
 */
abstract class funcRelational extends funcGeometric {

    public boolean any() {
        return Glm.any((Vec3) this);
    }

    public boolean all() {
        return Glm.all((Vec3) this);
    }
    
    public Vec3 not_() {
        return Glm.not((Vec3) this, new Vec3());
    }

    public Vec3 not() {
        return Glm.not((Vec3) this, (Vec3) this);
    }
    
    public Vec3bool lessThan__(final Vec3 b) {
        return Glm.lessThan((Vec3) this, b, new Vec3bool());
    }

    public Vec3bool lessThan(final Vec3 b, final Vec3bool res) {
        return Glm.lessThan((Vec3) this, b, res);
    }

    public Vec3bool lessThanEqual__(final Vec3 b) {
        return Glm.lessThanEqual((Vec3) this, b, new Vec3bool());
    }

    public Vec3bool lessThanEqual(final Vec3 b, final Vec3bool res) {
        return Glm.lessThanEqual((Vec3) this, b, res);
    }

    public Vec3bool greaterThan__(final Vec3 b) {
        return Glm.greaterThan((Vec3) this, b, new Vec3bool());
    }

    public Vec3bool greaterThan(final Vec3 b, final Vec3bool res) {
        return Glm.greaterThan((Vec3) this, b, res);
    }

    public Vec3bool greaterThanEqual__(final Vec3 b) {
        return Glm.greaterThanEqual((Vec3) this, b, new Vec3bool());
    }

    public Vec3bool greaterThanEqual(final Vec3 b, final Vec3bool res) {
        return Glm.greaterThanEqual((Vec3) this, b, res);
    }

    public Vec3bool equal__(final Vec3 b) {
        return Glm.equal((Vec3) this, b, new Vec3bool());
    }

    public Vec3bool equal(final Vec3 b, final Vec3bool res) {
        return Glm.equal((Vec3) this, b, res);
    }

    public Vec3bool notEqual__(final Vec3 b) {
        return Glm.notEqual((Vec3) this, b, new Vec3bool());
    }

    public Vec3bool notEqual(final Vec3 b, final Vec3bool res) {
        return Glm.notEqual((Vec3) this, b, res);
    }

    public Vec3 lessThan(final Vec3 b) {
        return Glm.lessThan((Vec3) this, b, (Vec3) this);
    }

    public Vec3 lessThan_(final Vec3 b) {
        return Glm.lessThan((Vec3) this, b, new Vec3());
    }

    public Vec3 lessThan(final Vec3 b, final Vec3 res) {
        return Glm.lessThan((Vec3) this, b, res);
    }

    public Vec3 lessThanEqual(final Vec3 b) {
        return Glm.lessThanEqual((Vec3) this, b, (Vec3) this);
    }

    public Vec3 lessThanEqual_(final Vec3 b) {
        return Glm.lessThanEqual((Vec3) this, b, new Vec3());
    }

    public Vec3 lessThanEqual(final Vec3 b, final Vec3 res) {
        return Glm.lessThanEqual((Vec3) this, b, res);
    }

    public Vec3 greaterThan(final Vec3 b) {
        return Glm.greaterThan((Vec3) this, b, (Vec3) this);
    }

    public Vec3 greaterThan_(final Vec3 b) {
        return Glm.greaterThan((Vec3) this, b, new Vec3());
    }

    public Vec3 greaterThan(final Vec3 b, final Vec3 res) {
        return Glm.greaterThan((Vec3) this, b, res);
    }

    public Vec3 greaterThanEqual(final Vec3 b) {
        return Glm.greaterThanEqual((Vec3) this, b, (Vec3) this);
    }

    public Vec3 greaterThanEqual_(final Vec3 b) {
        return Glm.greaterThanEqual((Vec3) this, b, new Vec3());
    }

    public Vec3 greaterThanEqual(final Vec3 b, final Vec3 res) {
        return Glm.greaterThanEqual((Vec3) this, b, res);
    }

    public Vec3 equal(final Vec3 b) {
        return Glm.equal((Vec3) this, b, (Vec3) this);
    }

    public Vec3 equal_(final Vec3 b) {
        return Glm.equal((Vec3) this, b, new Vec3());
    }

    public Vec3 equal(final Vec3 b, final Vec3 res) {
        return Glm.equal((Vec3) this, b, res);
    }

    public Vec3 notEqual(final Vec3 b) {
        return Glm.notEqual((Vec3) this, b, (Vec3) this);
    }

    public Vec3 notEqual_(final Vec3 b) {
        return Glm.notEqual((Vec3) this, b, new Vec3());
    }

    public Vec3 notEqual(final Vec3 b, final Vec3 res) {
        return Glm.notEqual((Vec3) this, b, res);
    }
}
