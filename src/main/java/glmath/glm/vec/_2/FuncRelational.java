/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2;

import glmath.glm.Glm;
import glmath.glm.vec._2.bool.Vec2bool;

/**
 *
 * @author elect
 */
abstract class FuncRelational extends funcGeometric {

    public boolean any() {
        return Glm.any((Vec2) this);
    }

    public boolean all() {
        return Glm.all((Vec2) this);
    }
    
    public Vec2 not_() {
        return Glm.not((Vec2) this, new Vec2());
    }

    public Vec2 not() {
        return Glm.not((Vec2) this, (Vec2) this);
    }

    public Vec2bool lessThan__(final Vec2 b) {
        return Glm.lessThan((Vec2) this, b, new Vec2bool());
    }

    public Vec2bool lessThan(final Vec2 b, final Vec2bool res) {
        return Glm.lessThan((Vec2) this, b, res);
    }

    public Vec2bool lessThanEqual__(final Vec2 b) {
        return Glm.lessThanEqual((Vec2) this, b, new Vec2bool());
    }

    public Vec2bool lessThanEqual(final Vec2 b, final Vec2bool res) {
        return Glm.lessThanEqual((Vec2) this, b, res);
    }

    public Vec2bool greaterThan__(final Vec2 b) {
        return Glm.greaterThan((Vec2) this, b, new Vec2bool());
    }

    public Vec2bool greaterThan(final Vec2 b, final Vec2bool res) {
        return Glm.greaterThan((Vec2) this, b, res);
    }

    public Vec2bool greaterThanEqual__(final Vec2 b) {
        return Glm.greaterThanEqual((Vec2) this, b, new Vec2bool());
    }

    public Vec2bool greaterThanEqual(final Vec2 b, final Vec2bool res) {
        return Glm.greaterThanEqual((Vec2) this, b, res);
    }

    public Vec2bool equal__(final Vec2 b) {
        return Glm.equal((Vec2) this, b, new Vec2bool());
    }

    public Vec2bool equal(final Vec2 b, final Vec2bool res) {
        return Glm.equal((Vec2) this, b, res);
    }

    public Vec2bool notEqual__(final Vec2 b) {
        return Glm.notEqual((Vec2) this, b, new Vec2bool());
    }

    public Vec2bool notEqual(final Vec2 b, final Vec2bool res) {
        return Glm.notEqual((Vec2) this, b, res);
    }

    public Vec2 lessThan(final Vec2 b) {
        return Glm.lessThan((Vec2) this, b, (Vec2) this);
    }

    public Vec2 lessThan_(final Vec2 b) {
        return Glm.lessThan((Vec2) this, b, new Vec2());
    }

    public Vec2 lessThan(final Vec2 b, final Vec2 res) {
        return Glm.lessThan((Vec2) this, b, res);
    }

    public Vec2 lessThanEqual(final Vec2 b) {
        return Glm.lessThanEqual((Vec2) this, b, (Vec2) this);
    }

    public Vec2 lessThanEqual_(final Vec2 b) {
        return Glm.lessThanEqual((Vec2) this, b, new Vec2());
    }

    public Vec2 lessThanEqual(final Vec2 b, final Vec2 res) {
        return Glm.lessThanEqual((Vec2) this, b, res);
    }

    public Vec2 greaterThan(final Vec2 b) {
        return Glm.greaterThan((Vec2) this, b, (Vec2) this);
    }

    public Vec2 greaterThan_(final Vec2 b) {
        return Glm.greaterThan((Vec2) this, b, new Vec2());
    }

    public Vec2 greaterThan(final Vec2 b, final Vec2 res) {
        return Glm.greaterThan((Vec2) this, b, res);
    }

    public Vec2 greaterThanEqual(final Vec2 b) {
        return Glm.greaterThanEqual((Vec2) this, b, (Vec2) this);
    }

    public Vec2 greaterThanEqual_(final Vec2 b) {
        return Glm.greaterThanEqual((Vec2) this, b, new Vec2());
    }

    public Vec2 greaterThanEqual(final Vec2 b, final Vec2 res) {
        return Glm.greaterThanEqual((Vec2) this, b, res);
    }

    public Vec2 equal(final Vec2 b) {
        return Glm.equal((Vec2) this, b, (Vec2) this);
    }

    public Vec2 equal_(final Vec2 b) {
        return Glm.equal((Vec2) this, b, new Vec2());
    }

    public Vec2 equal(final Vec2 b, final Vec2 res) {
        return Glm.equal((Vec2) this, b, res);
    }

    public Vec2 notEqual(final Vec2 b) {
        return Glm.notEqual((Vec2) this, b, (Vec2) this);
    }

    public Vec2 notEqual_(final Vec2 b) {
        return Glm.notEqual((Vec2) this, b, new Vec2());
    }

    public Vec2 notEqual(final Vec2 b, final Vec2 res) {
        return Glm.notEqual((Vec2) this, b, res);
    }
}
