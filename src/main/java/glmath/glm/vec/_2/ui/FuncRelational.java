/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._2.ui;

import glmath.glm.Glm;
import glmath.glm.vec._2.bool.Vec2bool;

/**
 *
 * @author fschaefers
 */
abstract class FuncRelational extends ArithmeticOperators {

    public boolean any() {
        return Glm.any((Vec2ui) this);
    }

    public boolean all() {
        return Glm.all((Vec2ui) this);
    }

    public Vec2ui not_() {
        return Glm.not((Vec2ui) this, new Vec2ui());
    }

    public Vec2ui not() {
        return Glm.not((Vec2ui) this, (Vec2ui) this);
    }

    public Vec2bool lessThan__(final Vec2ui b) {
        return Glm.lessThan((Vec2ui) this, b, new Vec2bool());
    }

    public Vec2bool lessThan(final Vec2ui b, final Vec2bool res) {
        return Glm.lessThan((Vec2ui) this, b, res);
    }

    public Vec2bool lessThanEqual__(final Vec2ui b) {
        return Glm.lessThanEqual((Vec2ui) this, b, new Vec2bool());
    }

    public Vec2bool lessThanEqual(final Vec2ui b, final Vec2bool res) {
        return Glm.lessThanEqual((Vec2ui) this, b, res);
    }

    public Vec2bool greaterThan__(final Vec2ui b) {
        return Glm.greaterThan((Vec2ui) this, b, new Vec2bool());
    }

    public Vec2bool greaterThan(final Vec2ui b, final Vec2bool res) {
        return Glm.greaterThan((Vec2ui) this, b, res);
    }

    public Vec2bool greaterThanEqual__(final Vec2ui b) {
        return Glm.greaterThanEqual((Vec2ui) this, b, new Vec2bool());
    }

    public Vec2bool greaterThanEqual(final Vec2ui b, final Vec2bool res) {
        return Glm.greaterThanEqual((Vec2ui) this, b, res);
    }

    public Vec2bool equal__(final Vec2ui b) {
        return Glm.equal((Vec2ui) this, b, new Vec2bool());
    }

    public Vec2bool equal(final Vec2ui b, final Vec2bool res) {
        return Glm.equal((Vec2ui) this, b, res);
    }

    public Vec2bool notEqual__(final Vec2ui b) {
        return Glm.notEqual((Vec2ui) this, b, new Vec2bool());
    }

    public Vec2bool notEqual(final Vec2ui b, final Vec2bool res) {
        return Glm.notEqual((Vec2ui) this, b, res);
    }

    public Vec2ui lessThan(final Vec2ui b) {
        return Glm.lessThan((Vec2ui) this, b, (Vec2ui) this);
    }

    public Vec2ui lessThan_(final Vec2ui b) {
        return Glm.lessThan((Vec2ui) this, b, new Vec2ui());
    }

    public Vec2ui lessThan(final Vec2ui b, final Vec2ui res) {
        return Glm.lessThan((Vec2ui) this, b, res);
    }

    public Vec2ui lessThanEqual_(final Vec2ui b) {
        return Glm.lessThanEqual((Vec2ui) this, b, new Vec2ui());
    }

    public Vec2ui lessThanEqual(final Vec2ui b) {
        return Glm.lessThanEqual((Vec2ui) this, b, (Vec2ui) this);
    }

    public Vec2ui lessThanEqual(final Vec2ui b, final Vec2ui res) {
        return Glm.lessThanEqual((Vec2ui) this, b, res);
    }

    public Vec2ui greaterThan(final Vec2ui b) {
        return Glm.greaterThan((Vec2ui) this, b, (Vec2ui) this);
    }

    public Vec2ui greaterThan_(final Vec2ui b) {
        return Glm.greaterThan((Vec2ui) this, b, new Vec2ui());
    }

    public Vec2ui greaterThan(final Vec2ui b, final Vec2ui res) {
        return Glm.greaterThan((Vec2ui) this, b, res);
    }

    public Vec2ui greaterThanEqual(final Vec2ui b) {
        return Glm.greaterThanEqual((Vec2ui) this, b, (Vec2ui) this);
    }

    public Vec2ui greaterThanEqual_(final Vec2ui b) {
        return Glm.greaterThanEqual((Vec2ui) this, b, new Vec2ui());
    }

    public Vec2ui greaterThanEqual(final Vec2ui b, final Vec2ui res) {
        return Glm.greaterThanEqual((Vec2ui) this, b, res);
    }

    public Vec2ui equal(final Vec2ui b) {
        return Glm.equal((Vec2ui) this, b, (Vec2ui) this);
    }

    public Vec2ui equal_(final Vec2ui b) {
        return Glm.equal((Vec2ui) this, b, new Vec2ui());
    }

    public Vec2ui equal(final Vec2ui b, final Vec2ui res) {
        return Glm.equal((Vec2ui) this, b, res);
    }

    public Vec2ui notEqual(final Vec2ui b) {
        return Glm.notEqual((Vec2ui) this, b, (Vec2ui) this);
    }

    public Vec2ui notEqual_(final Vec2ui b) {
        return Glm.notEqual((Vec2ui) this, b, new Vec2ui());
    }

    public Vec2ui notEqual(final Vec2ui b, final Vec2ui res) {
        return Glm.notEqual((Vec2ui) this, b, res);
    }
}
