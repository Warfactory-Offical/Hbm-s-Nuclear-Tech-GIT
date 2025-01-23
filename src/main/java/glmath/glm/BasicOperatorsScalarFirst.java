/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm;

import glmath.glm.vec._2.Vec2;
import glmath.glm.vec._2.b.Vec2b;
import glmath.glm.vec._2.d.Vec2d;
import glmath.glm.vec._2.i.Vec2i;
import glmath.glm.vec._2.l.Vec2l;
import glmath.glm.vec._2.s.Vec2s;
import glmath.glm.vec._2.ub.Vec2ub;
import glmath.glm.vec._2.ui.Vec2ui;
import glmath.glm.vec._2.ul.Vec2ul;
import glmath.glm.vec._2.us.Vec2us;
import glmath.glm.vec._3.Vec3;
import glmath.glm.vec._3.b.Vec3b;
import glmath.glm.vec._3.d.Vec3d;
import glmath.glm.vec._3.i.Vec3i;
import glmath.glm.vec._3.l.Vec3l;
import glmath.glm.vec._3.s.Vec3s;
import glmath.glm.vec._3.ub.Vec3ub;
import glmath.glm.vec._3.ui.Vec3ui;
import glmath.glm.vec._3.ul.Vec3ul;
import glmath.glm.vec._3.us.Vec3us;
import glmath.glm.vec._4.Vec4;
import glmath.glm.vec._4.b.Vec4b;
import glmath.glm.vec._4.d.Vec4d;
import glmath.glm.vec._4.i.Vec4i;
import glmath.glm.vec._4.l.Vec4l;
import glmath.glm.vec._4.s.Vec4s;
import glmath.glm.vec._4.ub.Vec4ub;
import glmath.glm.vec._4.ui.Vec4ui;
import glmath.glm.vec._4.ul.Vec4ul;
import glmath.glm.vec._4.us.Vec4us;
import glmath.joou.UByte;
import glmath.joou.UInt;
import glmath.joou.ULong;
import glmath.joou.UShort;

/**
 *
 * @author GBarbieri
 */
abstract class BasicOperatorsScalarFirst extends BasicOperators {

    public static Vec2 add_(final float a, final Vec2 b) {
        return BasicOperators.add(new Vec2(), b, a, a);
    }

    public static Vec2 add(final float a, final Vec2 b) {
        return BasicOperators.add(b, b, a, a);
    }

    public static Vec2 add(final Vec2 res, final float a, final Vec2 b) {
        return BasicOperators.add(res, b, a, a);
    }

    public static Vec2 add(final Vec2 res, final float aX, final float aY, final Vec2 b) {
        return BasicOperators.add(res, b, aX, aY);
    }

    public static Vec2b add_(final byte a, final Vec2b b) {
        return BasicOperators.add(new Vec2b(), b, (int) a, a);
    }

    public static Vec2b add_(final int a, final Vec2b b) {
        return BasicOperators.add(new Vec2b(), b, a, a);
    }

    public static Vec2b add(final byte a, final Vec2b b) {
        return BasicOperators.add(b, b, (int) a, a);
    }

    public static Vec2b add(final int a, final Vec2b b) {
        return BasicOperators.add(b, b, a, a);
    }

    public static Vec2b add(final Vec2b res, final byte a, final Vec2b b) {
        return BasicOperators.add(res, b, (int) a, a);
    }

    public static Vec2b add(final Vec2b res, final int a, final Vec2b b) {
        return BasicOperators.add(res, b, a, a);
    }

    public static Vec2b add(final Vec2b res, final byte aX, final byte aY, final Vec2b b) {
        return BasicOperators.add(res, b, (int) aX, aY);
    }

    public static Vec2b add(final Vec2b res, final int aX, final int aY, final Vec2b b) {
        return BasicOperators.add(res, b, aX, aY);
    }

    public static Vec2d add_(final double a, final Vec2d b) {
        return BasicOperators.add(new Vec2d(), b, a, a);
    }

    public static Vec2d add(final double a, final Vec2d b) {
        return BasicOperators.add(b, b, a, a);
    }

    public static Vec2d add(final Vec2d res, final double a, final Vec2d b) {
        return BasicOperators.add(res, b, a, a);
    }

    public static Vec2d add(final Vec2d res, final double aX, final double aY, final Vec2d b) {
        return BasicOperators.add(res, b, aX, aY);
    }

    public static Vec2i add_(final int a, final Vec2i b) {
        return BasicOperators.add(new Vec2i(), b, a, a);
    }

    public static Vec2i add(final int a, final Vec2i b) {
        return BasicOperators.add(b, b, a, a);
    }

    public static Vec2i add(final Vec2i res, final int a, final Vec2i b) {
        return BasicOperators.add(res, b, a, a);
    }

    public static Vec2i add(final Vec2i res, final int aX, final int aY, final Vec2i b) {
        return BasicOperators.add(res, b, aX, aY);
    }

    public static Vec2l add_(final long a, final Vec2l b) {
        return BasicOperators.add(new Vec2l(), b, a, a);
    }

    public static Vec2l add(final long a, final Vec2l b) {
        return BasicOperators.add(b, b, a, a);
    }

    public static Vec2l add(final Vec2l res, final long a, final Vec2l b) {
        return BasicOperators.add(res, b, a, a);
    }

    public static Vec2l add(final Vec2l res, final long aX, final long aY, final Vec2l b) {
        return BasicOperators.add(res, b, aX, aY);
    }

    public static Vec2s add_(final short a, final Vec2s b) {
        return BasicOperators.add(new Vec2s(), b, a, a);
    }

    public static Vec2s add(final short a, final Vec2s b) {
        return BasicOperators.add(b, b, a, a);
    }

    public static Vec2s add(final Vec2s res, final short a, final Vec2s b) {
        return BasicOperators.add(res, b, a, a);
    }

    public static Vec2s add(final Vec2s res, final short aX, final short aY, final Vec2s b) {
        return BasicOperators.add(res, b, aX, aY);
    }

    public static Vec2ub add_(final UByte a, final Vec2ub b) {
        return BasicOperators.add(new Vec2ub(), b, (int) a.value, a.value);
    }

    public static Vec2ub add_(final byte a, final Vec2ub b) {
        return BasicOperators.add(new Vec2ub(), b, (int) a, a);
    }

    public static Vec2ub add_(final int a, final Vec2ub b) {
        return BasicOperators.add(new Vec2ub(), b, a & 0xff, a & 0xff);
    }

    public static Vec2ub add(final UByte a, final Vec2ub b) {
        return BasicOperators.add(b, b, a.value & 0xff, a.value & 0xff);
    }

    public static Vec2ub add(final byte a, final Vec2ub b) {
        return BasicOperators.add(b, b, a & 0xff, a & 0xff);
    }

    public static Vec2ub add(final int a, final Vec2ub b) {
        return BasicOperators.add(b, b, a, a);
    }

    public static Vec2ub add(final Vec2ub res, final UByte a, final Vec2ub b) {
        return BasicOperators.add(res, b, a.value & 0xff, a.value & 0xff);
    }

    public static Vec2ub add(final Vec2ub res, final byte a, final Vec2ub b) {
        return BasicOperators.add(res, b, a & 0xff, a & 0xff);
    }

    public static Vec2ub add(final Vec2ub res, final int a, final Vec2ub b) {
        return BasicOperators.add(res, b, a, a);
    }

    public static Vec2ub add(final Vec2ub res, final UByte aX, final UByte aY, final Vec2ub b) {
        return BasicOperators.add(res, b, aX.value & 0xff, aY.value & 0xff);
    }

    public static Vec2ub add(final Vec2ub res, final byte aX, final byte aY, final Vec2ub b) {
        return BasicOperators.add(res, b, aX & 0xff, aY & 0xff);
    }

    public static Vec2ub add(final Vec2ub res, final int aX, final int aY, final Vec2ub b) {
        return BasicOperators.add(res, b, aX, aY);
    }

    public static Vec2ui add_(final UInt a, final Vec2ui b) {
        return BasicOperators.add(new Vec2ui(), b, a.value, a.value);
    }

    public static Vec2ui add_(final int a, final Vec2ui b) {
        return BasicOperators.add(new Vec2ui(), b, a, a);
    }

    public static Vec2ui add(final UInt a, final Vec2ui b) {
        return BasicOperators.add(b, b, a.value, a.value);
    }

    public static Vec2ui add(final int a, final Vec2ui b) {
        return BasicOperators.add(b, b, a, a);
    }

    public static Vec2ui add(final Vec2ui res, final UInt a, final Vec2ui b) {
        return BasicOperators.add(res, b, a.value, a.value);
    }

    public static Vec2ui add(final Vec2ui res, final int a, final Vec2ui b) {
        return BasicOperators.add(res, b, a, a);
    }

    public static Vec2ui add(final Vec2ui res, final UInt aX, final UInt aY, final Vec2ui b) {
        return BasicOperators.add(res, b, aX.value, aY.value);
    }

    public static Vec2ui add(final Vec2ui res, final int aX, final int aY, final Vec2ui b) {
        return BasicOperators.add(res, b, aX, aY);
    }

    public static Vec2ul add_(final ULong a, final Vec2ul b) {
        return BasicOperators.add(new Vec2ul(), b, a.value, a.value);
    }

    public static Vec2ul add_(final long a, final Vec2ul b) {
        return BasicOperators.add(new Vec2ul(), b, a, a);
    }

    public static Vec2ul add(final ULong a, final Vec2ul b) {
        return BasicOperators.add(b, b, a.value, a.value);
    }

    public static Vec2ul add(final long a, final Vec2ul b) {
        return BasicOperators.add(b, b, a, a);
    }

    public static Vec2ul add(final Vec2ul res, final ULong a, final Vec2ul b) {
        return BasicOperators.add(res, b, a.value, a.value);
    }

    public static Vec2ul add(final Vec2ul res, final long a, final Vec2ul b) {
        return BasicOperators.add(res, b, a, a);
    }

    public static Vec2ul add(final Vec2ul res, final ULong aX, final ULong aY, final Vec2ul b) {
        return BasicOperators.add(res, b, aX.value, aY.value);
    }

    public static Vec2ul add(final Vec2ul res, final long aX, final long aY, final Vec2ul b) {
        return BasicOperators.add(res, b, aX, aY);
    }

    public static Vec2us add_(final UShort a, final Vec2us b) {
        return BasicOperators.add(new Vec2us(), b, a.value & 0xffff, a.value & 0xffff);
    }

    public static Vec2us add_(final short a, final Vec2us b) {
        return BasicOperators.add(new Vec2us(), b, a & 0xffff, a & 0xffff);
    }

    public static Vec2us add_(final int a, final Vec2us b) {
        return BasicOperators.add(new Vec2us(), b, a, a);
    }

    public static Vec2us add(final UShort a, final Vec2us b) {
        return BasicOperators.add(b, b, a.value & 0xffff, a.value & 0xffff);
    }

    public static Vec2us add(final short a, final Vec2us b) {
        return BasicOperators.add(b, b, a & 0xffff, a & 0xffff);
    }

    public static Vec2us add(final int a, final Vec2us b) {
        return BasicOperators.add(b, b, a, a);
    }

    public static Vec2us add(final Vec2us res, final UShort a, final Vec2us b) {
        return BasicOperators.add(res, b, a.value & 0xffff, a.value & 0xffff);
    }

    public static Vec2us add(final Vec2us res, final short a, final Vec2us b) {
        return BasicOperators.add(res, b, a & 0xffff, a & 0xffff);
    }

    public static Vec2us add(final Vec2us res, final int a, final Vec2us b) {
        return BasicOperators.add(res, b, a, a);
    }

    public static Vec2us add(final Vec2us res, final UShort aX, final UShort aY, final Vec2us b) {
        return BasicOperators.add(res, b, aX.value & 0xffff, aY.value & 0xffff);
    }

    public static Vec2us add(final Vec2us res, final short aX, final short aY, final Vec2us b) {
        return BasicOperators.add(res, b, aX & 0xffff, aY & 0xffff);
    }

    public static Vec2us add(final Vec2us res, final int aX, final int aY, final Vec2us b) {
        return BasicOperators.add(res, b, aX, aY);
    }

    public static Vec3 add_(final float a, final Vec3 b) {
        return BasicOperators.add(new Vec3(), b, a, a, a);
    }

    public static Vec3 add(final float a, final Vec3 b) {
        return BasicOperators.add(b, b, a, a, a);
    }

    public static Vec3 add(final Vec3 res, final float a, final Vec3 b) {
        return BasicOperators.add(res, b, a, a, a);
    }

    public static Vec3 add(final Vec3 res, final float aX, final float aY, final float aZ, final Vec3 b) {
        return BasicOperators.add(res, b, aX, aY, aZ);
    }

    public static Vec3b add_(final byte a, final Vec3b b) {
        return BasicOperators.add(new Vec3b(), b, (int) a, a, a);
    }

    public static Vec3b add_(final int a, final Vec3b b) {
        return BasicOperators.add(new Vec3b(), b, a, a, a);
    }

    public static Vec3b add(final byte a, final Vec3b b) {
        return BasicOperators.add(b, b, (int) a, a, a);
    }

    public static Vec3b add(final int a, final Vec3b b) {
        return BasicOperators.add(b, b, a, a, a);
    }

    public static Vec3b add(final Vec3b res, final byte a, final Vec3b b) {
        return BasicOperators.add(res, b, (int) a, a, a);
    }

    public static Vec3b add(final Vec3b res, final int a, final Vec3b b) {
        return BasicOperators.add(res, b, a, a, a);
    }

    public static Vec3b add(final Vec3b res, final byte aX, final byte aY, final byte aZ, final Vec3b b) {
        return BasicOperators.add(res, b, (int) aX, aY, aZ);
    }

    public static Vec3b add(final Vec3b res, final int aX, final int aY, final int aZ, final Vec3b b) {
        return BasicOperators.add(res, b, aX, aY, aZ);
    }

    public static Vec3d add_(final double a, final Vec3d b) {
        return BasicOperators.add(new Vec3d(), b, a, a, a);
    }

    public static Vec3d add(final double a, final Vec3d b) {
        return BasicOperators.add(b, b, a, a, a);
    }

    public static Vec3d add(final Vec3d res, final double a, final Vec3d b) {
        return BasicOperators.add(res, b, a, a, a);
    }

    public static Vec3d add(final Vec3d res, final double aX, final double aY, final double aZ, final Vec3d b) {
        return BasicOperators.add(res, b, aX, aY, aZ);
    }

    public static Vec3i add_(final int a, final Vec3i b) {
        return BasicOperators.add(new Vec3i(), b, a, a, a);
    }

    public static Vec3i add(final int a, final Vec3i b) {
        return BasicOperators.add(b, b, a, a, a);
    }

    public static Vec3i add(final Vec3i res, final int a, final Vec3i b) {
        return BasicOperators.add(res, b, a, a, a);
    }

    public static Vec3i add(final Vec3i res, final int aX, final int aY, final int aZ, final Vec3i b) {
        return BasicOperators.add(res, b, aX, aY, aZ);
    }

    public static Vec3l add_(final long a, final Vec3l b) {
        return BasicOperators.add(new Vec3l(), b, a, a, a);
    }

    public static Vec3l add(final long a, final Vec3l b) {
        return BasicOperators.add(b, b, a, a, a);
    }

    public static Vec3l add(final Vec3l res, final long a, final Vec3l b) {
        return BasicOperators.add(res, b, a, a, a);
    }

    public static Vec3l add(final Vec3l res, final long aX, final long aY, final long aZ, final Vec3l b) {
        return BasicOperators.add(res, b, aX, aY, aZ);
    }

    public static Vec3s add_(final short a, final Vec3s b) {
        return BasicOperators.add(new Vec3s(), b, a, a, a);
    }

    public static Vec3s add(final short a, final Vec3s b) {
        return BasicOperators.add(b, b, a, a, a);
    }

    public static Vec3s add(final Vec3s res, final short a, final Vec3s b) {
        return BasicOperators.add(res, b, a, a, a);
    }

    public static Vec3s add(final Vec3s res, final short aX, final short aY, final short aZ, final Vec3s b) {
        return BasicOperators.add(res, b, aX, aY, aZ);
    }

    public static Vec3ub add_(final UByte a, final Vec3ub b) {
        return BasicOperators.add(new Vec3ub(), b, a.value & 0xff, a.value & 0xff, a.value & 0xff);
    }

    public static Vec3ub add_(final byte a, final Vec3ub b) {
        return BasicOperators.add(new Vec3ub(), b, a & 0xff, a & 0xff, a & 0xff);
    }

    public static Vec3ub add_(final int a, final Vec3ub b) {
        return BasicOperators.add(new Vec3ub(), b, a, a, a);
    }

    public static Vec3ub add(final UByte a, final Vec3ub b) {
        return BasicOperators.add(b, b, a.value & 0xff, a.value & 0xff, a.value & 0xff);
    }

    public static Vec3ub add(final byte a, final Vec3ub b) {
        return BasicOperators.add(b, b, a & 0xff, a & 0xff, a & 0xff);
    }

    public static Vec3ub add(final int a, final Vec3ub b) {
        return BasicOperators.add(b, b, a, a, a);
    }

    public static Vec3ub add(final Vec3ub res, final UByte a, final Vec3ub b) {
        return BasicOperators.add(res, b, a.value & 0xff, a.value & 0xff, a.value & 0xff);
    }

    public static Vec3ub add(final Vec3ub res, final byte a, final Vec3ub b) {
        return BasicOperators.add(res, b, a & 0xff, a & 0xff, a & 0xff);
    }

    public static Vec3ub add(final Vec3ub res, final int a, final Vec3ub b) {
        return BasicOperators.add(res, b, a, a, a);
    }

    public static Vec3ub add(final Vec3ub res, final UByte aX, final UByte aY, final UByte aZ, final Vec3ub b) {
        return BasicOperators.add(res, b, aX.value & 0xff, aY.value & 0xff, aZ.value & 0xff);
    }

    public static Vec3ub add(final Vec3ub res, final byte aX, final byte aY, final byte aZ, final Vec3ub b) {
        return BasicOperators.add(res, b, aX & 0xff, aY & 0xff, aZ & 0xff);
    }

    public static Vec3ub add(final Vec3ub res, final int aX, final int aY, final int aZ, final Vec3ub b) {
        return BasicOperators.add(res, b, aX, aY, aZ);
    }

    public static Vec3ui add_(final UInt a, final Vec3ui b) {
        return BasicOperators.add(new Vec3ui(), b, a.value, a.value, a.value);
    }

    public static Vec3ui add_(final int a, final Vec3ui b) {
        return BasicOperators.add(new Vec3ui(), b, a, a, a);
    }

    public static Vec3ui add(final UInt a, final Vec3ui b) {
        return BasicOperators.add(b, b, a.value, a.value, a.value);
    }

    public static Vec3ui add(final int a, final Vec3ui b) {
        return BasicOperators.add(b, b, a, a, a);
    }

    public static Vec3ui add(final Vec3ui res, final UInt a, final Vec3ui b) {
        return BasicOperators.add(res, b, a.value, a.value, a.value);
    }

    public static Vec3ui add(final Vec3ui res, final int a, final Vec3ui b) {
        return BasicOperators.add(res, b, a, a, a);
    }

    public static Vec3ui add(final Vec3ui res, final UInt aX, final UInt aY, final UInt aZ, final Vec3ui b) {
        return BasicOperators.add(res, b, aX.value, aY.value, aZ.value);
    }

    public static Vec3ui add(final Vec3ui res, final int aX, final int aY, final int aZ, final Vec3ui b) {
        return BasicOperators.add(res, b, aX, aY, aZ);
    }

    public static Vec3ul add_(final ULong a, final Vec3ul b) {
        return BasicOperators.add(new Vec3ul(), b, a.value, a.value, a.value);
    }

    public static Vec3ul add_(final long a, final Vec3ul b) {
        return BasicOperators.add(new Vec3ul(), b, a, a, a);
    }

    public static Vec3ul add(final ULong a, final Vec3ul b) {
        return BasicOperators.add(b, b, a.value, a.value, a.value);
    }

    public static Vec3ul add(final long a, final Vec3ul b) {
        return BasicOperators.add(b, b, a, a, a);
    }

    public static Vec3ul add(final Vec3ul res, final ULong a, final Vec3ul b) {
        return BasicOperators.add(res, b, a.value, a.value, a.value);
    }

    public static Vec3ul add(final Vec3ul res, final long a, final Vec3ul b) {
        return BasicOperators.add(res, b, a, a, a);
    }

    public static Vec3ul add(final Vec3ul res, final ULong aX, final ULong aY, final ULong aZ, final Vec3ul b) {
        return BasicOperators.add(res, b, aX.value, aY.value, aZ.value);
    }

    public static Vec3ul add(final Vec3ul res, final long aX, final long aY, final long aZ, final Vec3ul b) {
        return BasicOperators.add(res, b, aX, aY, aZ);
    }

    public static Vec3us add_(final UShort a, final Vec3us b) {
        return BasicOperators.add(new Vec3us(), b, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff);
    }

    public static Vec3us add_(final short a, final Vec3us b) {
        return BasicOperators.add(new Vec3us(), b, a & 0xffff, a & 0xffff, a & 0xffff);
    }

    public static Vec3us add_(final int a, final Vec3us b) {
        return BasicOperators.add(new Vec3us(), b, a, a, a);
    }

    public static Vec3us add(final UShort a, final Vec3us b) {
        return BasicOperators.add(b, b, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff);
    }

    public static Vec3us add(final short a, final Vec3us b) {
        return BasicOperators.add(b, b, a & 0xffff, a & 0xffff, a & 0xffff);
    }

    public static Vec3us add(final int a, final Vec3us b) {
        return BasicOperators.add(b, b, a, a, a);
    }

    public static Vec3us add(final Vec3us res, final UShort a, final Vec3us b) {
        return BasicOperators.add(res, b, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff);
    }

    public static Vec3us add(final Vec3us res, final short a, final Vec3us b) {
        return BasicOperators.add(res, b, a & 0xffff, a & 0xffff, a & 0xffff);
    }

    public static Vec3us add(final Vec3us res, final int a, final Vec3us b) {
        return BasicOperators.add(res, b, a, a, a);
    }

    public static Vec3us add(final Vec3us res, final UShort aX, final UShort aY, final UShort aZ, final Vec3us b) {
        return BasicOperators.add(res, b, aX.value & 0xffff, aY.value & 0xffff, aZ.value & 0xffff);
    }

    public static Vec3us add(final Vec3us res, final short aX, final short aY, final short aZ, final Vec3us b) {
        return BasicOperators.add(res, b, aX & 0xffff, aY & 0xffff, aZ & 0xffff);
    }

    public static Vec3us add(final Vec3us res, final int aX, final int aY, final int aZ, final Vec3us b) {
        return BasicOperators.add(res, b, aX, aY, aZ);
    }

    public static Vec4 add_(final float a, final Vec4 b) {
        return BasicOperators.add(new Vec4(), b, a, a, a, a);
    }

    public static Vec4 add(final float a, final Vec4 b) {
        return BasicOperators.add(b, b, a, a, a, a);
    }

    public static Vec4 add(final Vec4 res, final float a, final Vec4 b) {
        return BasicOperators.add(res, b, a, a, a, a);
    }

    public static Vec4 add(final Vec4 res, final float aX, final float aY, final float aZ, final float aW, final Vec4 b) {
        return BasicOperators.add(res, b, aX, aY, aZ, aW);
    }

    public static Vec4b add_(final byte a, final Vec4b b) {
        return BasicOperators.add(new Vec4b(), b, (int) a, a, a, a);
    }

    public static Vec4b add_(final int a, final Vec4b b) {
        return BasicOperators.add(new Vec4b(), b, a, a, a, a);
    }

    public static Vec4b add(final byte a, final Vec4b b) {
        return BasicOperators.add(b, b, (int) a, a, a, a);
    }

    public static Vec4b add(final int a, final Vec4b b) {
        return BasicOperators.add(b, b, a, a, a, a);
    }

    public static Vec4b add(final Vec4b res, final byte a, final Vec4b b) {
        return BasicOperators.add(res, b, (int) a, a, a, a);
    }

    public static Vec4b add(final Vec4b res, final int a, final Vec4b b) {
        return BasicOperators.add(res, b, a, a, a, a);
    }

    public static Vec4b add(final Vec4b res, final byte aX, final byte aY, final byte aZ, final byte aW, final Vec4b b) {
        return BasicOperators.add(res, b, (int) aX, aY, aZ, aW);
    }

    public static Vec4b add(final Vec4b res, final int aX, final int aY, final int aZ, final int aW, final Vec4b b) {
        return BasicOperators.add(res, b, aX, aY, aZ, aW);
    }

    public static Vec4d add_(final double a, final Vec4d b) {
        return BasicOperators.add(new Vec4d(), b, a, a, a, a);
    }

    public static Vec4d add(final double a, final Vec4d b) {
        return BasicOperators.add(b, b, a, a, a, a);
    }

    public static Vec4d add(final Vec4d res, final double a, final Vec4d b) {
        return BasicOperators.add(res, b, a, a, a, a);
    }

    public static Vec4d add(final Vec4d res, final double aX, final double aY, final double aZ, final double aW, final Vec4d b) {
        return BasicOperators.add(res, b, aX, aY, aZ, aW);
    }

    public static Vec4i add_(final int a, final Vec4i b) {
        return BasicOperators.add(new Vec4i(), b, a, a, a, a);
    }

    public static Vec4i add(final int a, final Vec4i b) {
        return BasicOperators.add(b, b, a, a, a, a);
    }

    public static Vec4i add(final Vec4i res, final int a, final Vec4i b) {
        return BasicOperators.add(res, b, a, a, a, a);
    }

    public static Vec4i add(final Vec4i res, final int aX, final int aY, final int aZ, final int aW, final Vec4i b) {
        return BasicOperators.add(res, b, aX, aY, aZ, aW);
    }

    public static Vec4l add_(final long a, final Vec4l b) {
        return BasicOperators.add(new Vec4l(), b, a, a, a, a);
    }

    public static Vec4l add(final long a, final Vec4l b) {
        return BasicOperators.add(b, b, a, a, a, a);
    }

    public static Vec4l add(final Vec4l res, final long a, final Vec4l b) {
        return BasicOperators.add(res, b, a, a, a, a);
    }

    public static Vec4l add(final Vec4l res, final long aX, final long aY, final long aZ, final long aW, final Vec4l b) {
        return BasicOperators.add(res, b, aX, aY, aZ, aW);
    }

    public static Vec4s add_(final short a, final Vec4s b) {
        return BasicOperators.add(new Vec4s(), b, a, a, a, a);
    }

    public static Vec4s add(final short a, final Vec4s b) {
        return BasicOperators.add(b, b, a, a, a, a);
    }

    public static Vec4s add(final Vec4s res, final short a, final Vec4s b) {
        return BasicOperators.add(res, b, a, a, a, a);
    }

    public static Vec4s add(final Vec4s res, final short aX, final short aY, final short aZ, final short aW, final Vec4s b) {
        return BasicOperators.add(res, b, aX, aY, aZ, aW);
    }

    public static Vec4ub add_(final UByte a, final Vec4ub b) {
        return BasicOperators.add(new Vec4ub(), b, a.value & 0xff, a.value & 0xff, a.value & 0xff, a.value & 0xff);
    }

    public static Vec4ub add_(final byte a, final Vec4ub b) {
        return BasicOperators.add(new Vec4ub(), b, a & 0xff, a & 0xff, a & 0xff, a & 0xff);
    }

    public static Vec4ub add_(final int a, final Vec4ub b) {
        return BasicOperators.add(new Vec4ub(), b, a, a, a, a);
    }

    public static Vec4ub add(final UByte a, final Vec4ub b) {
        return BasicOperators.add(b, b, a.value & 0xff, a.value & 0xff, a.value & 0xff, a.value & 0xff);
    }

    public static Vec4ub add(final byte a, final Vec4ub b) {
        return BasicOperators.add(b, b, a & 0xff, a & 0xff, a & 0xff, a & 0xff);
    }

    public static Vec4ub add(final int a, final Vec4ub b) {
        return BasicOperators.add(b, b, a, a, a, a);
    }

    public static Vec4ub add(final Vec4ub res, final UByte a, final Vec4ub b) {
        return BasicOperators.add(res, b, a.value & 0xff, a.value & 0xff, a.value & 0xff, a.value & 0xff);
    }

    public static Vec4ub add(final Vec4ub res, final byte a, final Vec4ub b) {
        return BasicOperators.add(res, b, a & 0xff, a & 0xff, a & 0xff, a & 0xff);
    }

    public static Vec4ub add(final Vec4ub res, final int a, final Vec4ub b) {
        return BasicOperators.add(res, b, a, a, a, a);
    }

    public static Vec4ub add(final Vec4ub res, final UByte aX, final UByte aY, final UByte aZ, final UByte aW, final Vec4ub b) {
        return BasicOperators.add(res, b, aX.value & 0xff, aY.value & 0xff, aZ.value & 0xff, aW.value & 0xff);
    }

    public static Vec4ub add(final Vec4ub res, final byte aX, final byte aY, final byte aZ, final byte aW, final Vec4ub b) {
        return BasicOperators.add(res, b, aX & 0xff, aY & 0xff, aZ & 0xff, aW & 0xff);
    }

    public static Vec4ub add(final Vec4ub res, final int aX, final int aY, final int aZ, final int aW, final Vec4ub b) {
        return BasicOperators.add(res, b, aX, aY, aZ, aW);
    }

    public static Vec4ui add_(final UInt a, final Vec4ui b) {
        return BasicOperators.add(new Vec4ui(), b, a.value, a.value, a.value, a.value);
    }

    public static Vec4ui add_(final int a, final Vec4ui b) {
        return BasicOperators.add(new Vec4ui(), b, a, a, a, a);
    }

    public static Vec4ui add(final UInt a, final Vec4ui b) {
        return BasicOperators.add(b, b, a.value, a.value, a.value, a.value);
    }

    public static Vec4ui add(final int a, final Vec4ui b) {
        return BasicOperators.add(b, b, a, a, a, a);
    }

    public static Vec4ui add(final Vec4ui res, final UInt a, final Vec4ui b) {
        return BasicOperators.add(res, b, a.value, a.value, a.value, a.value);
    }

    public static Vec4ui add(final Vec4ui res, final int a, final Vec4ui b) {
        return BasicOperators.add(res, b, a, a, a, a);
    }

    public static Vec4ui add(final Vec4ui res, final UInt aX, final UInt aY, final UInt aZ, final UInt aW, final Vec4ui b) {
        return BasicOperators.add(res, b, aX.value, aY.value, aZ.value, aW.value);
    }

    public static Vec4ui add(final Vec4ui res, final int aX, final int aY, final int aZ, final int aW, final Vec4ui b) {
        return BasicOperators.add(res, b, aX, aY, aZ, aW);
    }

    public static Vec4ul add_(final ULong a, final Vec4ul b) {
        return BasicOperators.add(new Vec4ul(), b, a.value, a.value, a.value, a.value);
    }

    public static Vec4ul add_(final long a, final Vec4ul b) {
        return BasicOperators.add(new Vec4ul(), b, a, a, a, a);
    }

    public static Vec4ul add(final ULong a, final Vec4ul b) {
        return BasicOperators.add(b, b, a.value, a.value, a.value, a.value);
    }

    public static Vec4ul add(final long a, final Vec4ul b) {
        return BasicOperators.add(b, b, a, a, a, a);
    }

    public static Vec4ul add(final Vec4ul res, final ULong a, final Vec4ul b) {
        return BasicOperators.add(res, b, a.value, a.value, a.value, a.value);
    }

    public static Vec4ul add(final Vec4ul res, final long a, final Vec4ul b) {
        return BasicOperators.add(res, b, a, a, a, a);
    }

    public static Vec4ul add(final Vec4ul res, final ULong aX, final ULong aY, final ULong aZ, final ULong aW, final Vec4ul b) {
        return BasicOperators.add(res, b, aX.value, aY.value, aZ.value, aW.value);
    }

    public static Vec4ul add(final Vec4ul res, final long aX, final long aY, final long aZ, final long aW, final Vec4ul b) {
        return BasicOperators.add(res, b, aX, aY, aZ, aW);
    }

    public static Vec4us add_(final UShort a, final Vec4us b) {
        return BasicOperators.add(new Vec4us(), b, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff);
    }

    public static Vec4us add_(final short a, final Vec4us b) {
        return BasicOperators.add(new Vec4us(), b, a & 0xffff, a & 0xffff, a & 0xffff, a & 0xffff);
    }

    public static Vec4us add_(final int a, final Vec4us b) {
        return BasicOperators.add(new Vec4us(), b, a, a, a, a);
    }

    public static Vec4us add(final UShort a, final Vec4us b) {
        return BasicOperators.add(b, b, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff);
    }

    public static Vec4us add(final short a, final Vec4us b) {
        return BasicOperators.add(b, b, a & 0xffff, a & 0xffff, a & 0xffff, a & 0xffff);
    }

    public static Vec4us add(final int a, final Vec4us b) {
        return BasicOperators.add(b, b, a, a, a, a);
    }

    public static Vec4us add(final Vec4us res, final UShort a, final Vec4us b) {
        return BasicOperators.add(res, b, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff);
    }

    public static Vec4us add(final Vec4us res, final short a, final Vec4us b) {
        return BasicOperators.add(res, b, a & 0xffff, a & 0xffff, a & 0xffff, a & 0xffff);
    }

    public static Vec4us add(final Vec4us res, final int a, final Vec4us b) {
        return BasicOperators.add(res, b, a, a, a, a);
    }

    public static Vec4us add(final Vec4us res, final UShort aX, final UShort aY, final UShort aZ, final UShort aW, final Vec4us b) {
        return BasicOperators.add(res, b, aX.value & 0xffff, aY.value & 0xffff, aZ.value & 0xffff, aW.value & 0xffff);
    }

    public static Vec4us add(final Vec4us res, final short aX, final short aY, final short aZ, final short aW, final Vec4us b) {
        return BasicOperators.add(res, b, aX & 0xffff, aY & 0xffff, aZ & 0xffff, aW & 0xffff);
    }

    public static Vec4us add(final Vec4us res, final int aX, final int aY, final int aZ, final int aW, final Vec4us b) {
        return BasicOperators.add(res, b, aX, aY, aZ, aW);
    }

    public static Vec2 sub_(final float a, final Vec2 b) {
        return sub(new Vec2(), a, a, b);
    }

    public static Vec2 sub(final float a, final Vec2 b) {
        return sub(b, a, a, b);
    }

    public static Vec2 sub(final Vec2 res, final float a, final Vec2 b) {
        return sub(res, a, a, b);
    }

    public static Vec2 sub(final Vec2 res, final float aX, final float aY, final Vec2 b) {
        res.x = aX - b.x;
        res.y = aY - b.y;
        return res;
    }

    public static Vec2b sub_(final byte a, final Vec2b b) {
        return sub(new Vec2b(), (int) a, a, b);
    }

    public static Vec2b sub_(final int a, final Vec2b b) {
        return sub(new Vec2b(), a, a, b);
    }

    public static Vec2b sub(final byte a, final Vec2b b) {
        return sub(b, (int) a, a, b);
    }

    public static Vec2b sub(final int a, final Vec2b b) {
        return sub(b, a, a, b);
    }

    public static Vec2b sub(final Vec2b res, final byte a, final Vec2b b) {
        return sub(res, (int) a, a, b);
    }

    public static Vec2b sub(final Vec2b res, final int a, final Vec2b b) {
        return sub(res, a, a, b);
    }

    public static Vec2b sub(final Vec2b res, final byte aX, final byte aY, final Vec2b b) {
        return sub(res, (int) aX, aY, b);
    }

    public static Vec2b sub(final Vec2b res, final int aX, final int aY, final Vec2b b) {
        res.x = (byte) (aX - b.x);
        res.y = (byte) (aY - b.y);
        return res;
    }

    public static Vec2d sub_(final double a, final Vec2d b) {
        return sub(new Vec2d(), a, a, b);
    }

    public static Vec2d sub(final double a, final Vec2d b) {
        return sub(b, a, a, b);
    }

    public static Vec2d sub(final Vec2d res, final double a, final Vec2d b) {
        return sub(res, a, a, b);
    }

    public static Vec2d sub(final Vec2d res, final double aX, final double aY, final Vec2d b) {
        res.x = aX - b.x;
        res.y = aY - b.y;
        return res;
    }

    public static Vec2i sub_(final int a, final Vec2i b) {
        return sub(new Vec2i(), a, a, b);
    }

    public static Vec2i sub(final int a, final Vec2i b) {
        return sub(b, a, a, b);
    }

    public static Vec2i sub(final Vec2i res, final int a, final Vec2i b) {
        return sub(res, a, a, b);
    }

    public static Vec2i sub(final Vec2i res, final int aX, final int aY, final Vec2i b) {
        res.x = aX - b.x;
        res.y = aY - b.y;
        return res;
    }

    public static Vec2l sub_(final long a, final Vec2l b) {
        return sub(new Vec2l(), a, a, b);
    }

    public static Vec2l sub(final long a, final Vec2l b) {
        return sub(b, a, a, b);
    }

    public static Vec2l sub(final Vec2l res, final long a, final Vec2l b) {
        return sub(res, a, a, b);
    }

    public static Vec2l sub(final Vec2l res, final long aX, final long aY, final Vec2l b) {
        res.x = aX - b.x;
        res.y = aY - b.y;
        return res;
    }

    public static Vec2s sub_(final short a, final Vec2s b) {
        return sub(new Vec2s(), a, a, b);
    }

    public static Vec2s sub(final short a, final Vec2s b) {
        return sub(b, a, a, b);
    }

    public static Vec2s sub(final Vec2s res, final short a, final Vec2s b) {
        return sub(res, a, a, b);
    }

    public static Vec2s sub(final Vec2s res, final short aX, final short aY, final Vec2s b) {
        res.x = (short) (aX - b.x);
        res.y = (short) (aY - b.y);
        return res;
    }

    public static Vec2ub sub_(final UByte a, final Vec2ub b) {
        return sub(new Vec2ub(), a.value & 0xff, a.value & 0xff, b);
    }

    public static Vec2ub sub_(final byte a, final Vec2ub b) {
        return sub(new Vec2ub(), a & 0xff, a & 0xff, b);
    }

    public static Vec2ub sub_(final int a, final Vec2ub b) {
        return sub(new Vec2ub(), a, a, b);
    }

    public static Vec2ub sub(final UByte a, final Vec2ub b) {
        return sub(b, a.value & 0xff, a.value & 0xff, b);
    }

    public static Vec2ub sub(final byte a, final Vec2ub b) {
        return sub(b, a & 0xff, a & 0xff, b);
    }

    public static Vec2ub sub(final int a, final Vec2ub b) {
        return sub(b, a, a, b);
    }

    public static Vec2ub sub(final Vec2ub res, final UByte a, final Vec2ub b) {
        return sub(res, a.value & 0xff, a.value & 0xff, b);
    }

    public static Vec2ub sub(final Vec2ub res, final byte a, final Vec2ub b) {
        return sub(res, a & 0xff, a & 0xff, b);
    }

    public static Vec2ub sub(final Vec2ub res, final int a, final Vec2ub b) {
        return sub(res, a, a, b);
    }

    public static Vec2ub sub(final Vec2ub res, final UByte aX, final UByte aY, final Vec2ub b) {
        return sub(res, aX.value & 0xff, aY.value & 0xff, b);
    }

    public static Vec2ub sub(final Vec2ub res, final byte aX, final byte aY, final Vec2ub b) {
        return sub(res, aX & 0xff, aY & 0xff, b);
    }

    public static Vec2ub sub(final Vec2ub res, final int aX, final int aY, final Vec2ub b) {
        res.x.value = (byte) (aX - (b.x.value & 0xff));
        res.y.value = (byte) (aY - (b.y.value & 0xff));
        return res;
    }

    public static Vec2ui sub_(final UInt a, final Vec2ui b) {
        return sub(new Vec2ui(), a.value, a.value, b);
    }

    public static Vec2ui sub_(final int a, final Vec2ui b) {
        return sub(new Vec2ui(), a, a, b);
    }

    public static Vec2ui sub(final UInt a, final Vec2ui b) {
        return sub(b, a.value, a.value, b);
    }

    public static Vec2ui sub(final int a, final Vec2ui b) {
        return sub(b, a, a, b);
    }

    public static Vec2ui sub(final Vec2ui res, final UInt a, final Vec2ui b) {
        return sub(res, a.value, a.value, b);
    }

    public static Vec2ui sub(final Vec2ui res, final int a, final Vec2ui b) {
        return sub(res, a, a, b);
    }

    public static Vec2ui sub(final Vec2ui res, final UInt aX, final UInt aY, final Vec2ui b) {
        return sub(res, aX.value, aY.value, b);
    }

    public static Vec2ui sub(final Vec2ui res, final int aX, final int aY, final Vec2ui b) {
        res.x.value = aX - b.x.value;
        res.y.value = aY - b.y.value;
        return res;
    }

    public static Vec2ul sub_(final ULong a, final Vec2ul b) {
        return sub(new Vec2ul(), a.value, a.value, b);
    }

    public static Vec2ul sub_(final long a, final Vec2ul b) {
        return sub(new Vec2ul(), a, a, b);
    }

    public static Vec2ul sub(final ULong a, final Vec2ul b) {
        return sub(b, a.value, a.value, b);
    }

    public static Vec2ul sub(final long a, final Vec2ul b) {
        return sub(b, a, a, b);
    }

    public static Vec2ul sub(final Vec2ul res, final ULong a, final Vec2ul b) {
        return sub(res, a.value, a.value, b);
    }

    public static Vec2ul sub(final Vec2ul res, final long a, final Vec2ul b) {
        return sub(res, a, a, b);
    }

    public static Vec2ul sub(final Vec2ul res, final ULong aX, final ULong aY, final Vec2ul b) {
        return sub(res, aX.value, aY.value, b);
    }

    public static Vec2ul sub(final Vec2ul res, final long aX, final long aY, final Vec2ul b) {
        res.x.value = aX - b.x.value;
        res.y.value = aY - b.y.value;
        return res;
    }

    public static Vec2us sub_(final UShort a, final Vec2us b) {
        return sub(new Vec2us(), a.value & 0xffff, a.value & 0xffff, b);
    }

    public static Vec2us sub_(final short a, final Vec2us b) {
        return sub(new Vec2us(), a & 0xffff, a & 0xffff, b);
    }

    public static Vec2us sub_(final int a, final Vec2us b) {
        return sub(new Vec2us(), a, a, b);
    }

    public static Vec2us sub(final UShort a, final Vec2us b) {
        return sub(b, a.value & 0xffff, a.value & 0xffff, b);
    }

    public static Vec2us sub(final short a, final Vec2us b) {
        return sub(b, a & 0xffff, a & 0xffff, b);
    }

    public static Vec2us sub(final int a, final Vec2us b) {
        return sub(b, a, a, b);
    }

    public static Vec2us sub(final Vec2us res, final UShort a, final Vec2us b) {
        return sub(res, a.value & 0xffff, a.value & 0xffff, b);
    }

    public static Vec2us sub(final Vec2us res, final short a, final Vec2us b) {
        return sub(res, a & 0xffff, a & 0xffff, b);
    }

    public static Vec2us sub(final Vec2us res, final int a, final Vec2us b) {
        return sub(res, a, a, b);
    }

    public static Vec2us sub(final Vec2us res, final UShort aX, final UShort aY, final Vec2us b) {
        return sub(res, aX.value & 0xffff, aY.value & 0xffff, b);
    }

    public static Vec2us sub(final Vec2us res, final short aX, final short aY, final Vec2us b) {
        return sub(res, aX & 0xffff, aY & 0xffff, b);
    }

    public static Vec2us sub(final Vec2us res, final int aX, final int aY, final Vec2us b) {
        res.x.value = (short) (aX - (b.x.value & 0xffff));
        res.y.value = (short) (aY - (b.y.value & 0xffff));
        return res;
    }

    public static Vec3 sub_(final float a, final Vec3 b) {
        return sub(new Vec3(), a, a, a, b);
    }

    public static Vec3 sub(final float a, final Vec3 b) {
        return sub(b, a, a, a, b);
    }

    public static Vec3 sub(final Vec3 res, final float a, final Vec3 b) {
        return sub(res, a, a, a, b);
    }

    public static Vec3 sub(final Vec3 res, final float aX, final float aY, final float aZ, final Vec3 b) {
        res.x = aX - b.x;
        res.y = aY - b.y;
        res.z = aZ - b.z;
        return res;
    }

    public static Vec3b sub_(final byte a, final Vec3b b) {
        return sub(new Vec3b(), (int) a, a, a, b);
    }

    public static Vec3b sub_(final int a, final Vec3b b) {
        return sub(new Vec3b(), a, a, a, b);
    }

    public static Vec3b sub(final byte a, final Vec3b b) {
        return sub(b, (int) a, a, a, b);
    }

    public static Vec3b sub(final int a, final Vec3b b) {
        return sub(b, a, a, a, b);
    }

    public static Vec3b sub(final Vec3b res, final byte a, final Vec3b b) {
        return sub(res, (int) a, a, a, b);
    }

    public static Vec3b sub(final Vec3b res, final int a, final Vec3b b) {
        return sub(res, a, a, a, b);
    }

    public static Vec3b sub(final Vec3b res, final byte aX, final byte aY, final byte aZ, final Vec3b b) {
        return sub(res, (int) aX, aY, aZ, b);
    }

    public static Vec3b sub(final Vec3b res, final int aX, final int aY, final int aZ, final Vec3b b) {
        res.x = (byte) (aX - b.x);
        res.y = (byte) (aY - b.y);
        res.z = (byte) (aZ - b.z);
        return res;
    }

    public static Vec3d sub_(final double a, final Vec3d b) {
        return sub(new Vec3d(), a, a, a, b);
    }

    public static Vec3d sub(final double a, final Vec3d b) {
        return sub(b, a, a, a, b);
    }

    public static Vec3d sub(final Vec3d res, final double a, final Vec3d b) {
        return sub(res, a, a, a, b);
    }

    public static Vec3d sub(final Vec3d res, final double aX, final double aY, final double aZ, final Vec3d b) {
        res.x = aX - b.x;
        res.y = aY - b.y;
        res.z = aZ - b.z;
        return res;
    }

    public static Vec3i sub_(final int a, final Vec3i b) {
        return sub(new Vec3i(), a, a, a, b);
    }

    public static Vec3i sub(final int a, final Vec3i b) {
        return sub(b, a, a, a, b);
    }

    public static Vec3i sub(final Vec3i res, final int a, final Vec3i b) {
        return sub(res, a, a, a, b);
    }

    public static Vec3i sub(final Vec3i res, final int aX, final int aY, final int aZ, final Vec3i b) {
        res.x = aX - b.x;
        res.y = aY - b.y;
        res.z = aZ - b.z;
        return res;
    }

    public static Vec3l sub_(final long a, final Vec3l b) {
        return sub(new Vec3l(), a, a, a, b);
    }

    public static Vec3l sub(final long a, final Vec3l b) {
        return sub(b, a, a, a, b);
    }

    public static Vec3l sub(final Vec3l res, final long a, final Vec3l b) {
        return sub(res, a, a, a, b);
    }

    public static Vec3l sub(final Vec3l res, final long aX, final long aY, final long aZ, final Vec3l b) {
        res.x = aX - b.x;
        res.y = aY - b.y;
        res.z = aZ - b.z;
        return res;
    }

    public static Vec3s sub_(final short a, final Vec3s b) {
        return sub(new Vec3s(), a, a, a, b);
    }

    public static Vec3s sub(final short a, final Vec3s b) {
        return sub(b, a, a, a, b);
    }

    public static Vec3s sub(final Vec3s res, final short a, final Vec3s b) {
        return sub(res, a, a, a, b);
    }

    public static Vec3s sub(final Vec3s res, final short aX, final short aY, final short aZ, final Vec3s b) {
        res.x = (short) (aX - b.x);
        res.y = (short) (aY - b.y);
        res.z = (short) (aZ - b.z);
        return res;
    }

    public static Vec3ub sub_(final UByte a, final Vec3ub b) {
        return sub(new Vec3ub(), a.value & 0xff, a.value & 0xff, a.value & 0xff, b);
    }

    public static Vec3ub sub_(final byte a, final Vec3ub b) {
        return sub(new Vec3ub(), a & 0xff, a & 0xff, a & 0xff, b);
    }

    public static Vec3ub sub_(final int a, final Vec3ub b) {
        return sub(new Vec3ub(), a, a, a, b);
    }

    public static Vec3ub sub(final UByte a, final Vec3ub b) {
        return sub(b, a.value & 0xff, a.value & 0xff, a.value & 0xff, b);
    }

    public static Vec3ub sub(final byte a, final Vec3ub b) {
        return sub(b, a & 0xff, a & 0xff, a & 0xff, b);
    }

    public static Vec3ub sub(final int a, final Vec3ub b) {
        return sub(b, a, a, a, b);
    }

    public static Vec3ub sub(final Vec3ub res, final UByte a, final Vec3ub b) {
        return sub(res, a.value & 0xff, a.value & 0xff, a.value & 0xff, b);
    }

    public static Vec3ub sub(final Vec3ub res, final byte a, final Vec3ub b) {
        return sub(res, a & 0xff, a & 0xff, a & 0xff, b);
    }

    public static Vec3ub sub(final Vec3ub res, final int a, final Vec3ub b) {
        return sub(res, a, a, a, b);
    }

    public static Vec3ub sub(final Vec3ub res, final UByte aX, final UByte aY, final UByte aZ, final Vec3ub b) {
        return sub(res, aX.value & 0xff, aY.value & 0xff, aZ.value & 0xff, b);
    }

    public static Vec3ub sub(final Vec3ub res, final byte aX, final byte aY, final byte aZ, final Vec3ub b) {
        return sub(res, aX & 0xff, aY & 0xff, aZ & 0xff, b);
    }

    public static Vec3ub sub(final Vec3ub res, final int aX, final int aY, final int aZ, final Vec3ub b) {
        res.x.value = (byte) (aX - (b.x.value & 0xff));
        res.y.value = (byte) (aY - (b.y.value & 0xff));
        res.z.value = (byte) (aZ - (b.z.value & 0xff));
        return res;
    }

    public static Vec3ui sub_(final UInt a, final Vec3ui b) {
        return sub(new Vec3ui(), a.value, a.value, a.value, b);
    }

    public static Vec3ui sub_(final int a, final Vec3ui b) {
        return sub(new Vec3ui(), a, a, a, b);
    }

    public static Vec3ui sub(final UInt a, final Vec3ui b) {
        return sub(b, a.value, a.value, a.value, b);
    }

    public static Vec3ui sub(final int a, final Vec3ui b) {
        return sub(b, a, a, a, b);
    }

    public static Vec3ui sub(final Vec3ui res, final UInt a, final Vec3ui b) {
        return sub(res, a.value, a.value, a.value, b);
    }

    public static Vec3ui sub(final Vec3ui res, final int a, final Vec3ui b) {
        return sub(res, a, a, a, b);
    }

    public static Vec3ui sub(final Vec3ui res, final UInt aX, final UInt aY, final UInt aZ, final Vec3ui b) {
        return sub(res, aX.value, aY.value, aZ.value, b);
    }

    public static Vec3ui sub(final Vec3ui res, final int aX, final int aY, final int aZ, final Vec3ui b) {
        res.x.value = aX - b.x.value;
        res.y.value = aY - b.y.value;
        res.z.value = aZ - b.z.value;
        return res;
    }

    public static Vec3ul sub_(final ULong a, final Vec3ul b) {
        return sub(new Vec3ul(), a.value, a.value, a.value, b);
    }

    public static Vec3ul sub_(final long a, final Vec3ul b) {
        return sub(new Vec3ul(), a, a, a, b);
    }

    public static Vec3ul sub(final ULong a, final Vec3ul b) {
        return sub(b, a.value, a.value, a.value, b);
    }

    public static Vec3ul sub(final long a, final Vec3ul b) {
        return sub(b, a, a, a, b);
    }

    public static Vec3ul sub(final Vec3ul res, final ULong a, final Vec3ul b) {
        return sub(res, a.value, a.value, a.value, b);
    }

    public static Vec3ul sub(final Vec3ul res, final long a, final Vec3ul b) {
        return sub(res, a, a, a, b);
    }

    public static Vec3ul sub(final Vec3ul res, final ULong aX, final ULong aY, final ULong aZ, final Vec3ul b) {
        return sub(res, aX.value, aY.value, aZ.value, b);
    }

    public static Vec3ul sub(final Vec3ul res, final long aX, final long aY, final long aZ, final Vec3ul b) {
        res.x.value = aX - b.x.value;
        res.y.value = aY - b.y.value;
        res.z.value = aZ - b.z.value;
        return res;
    }

    public static Vec3us sub_(final UShort a, final Vec3us b) {
        return sub(new Vec3us(), a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, b);
    }

    public static Vec3us sub_(final short a, final Vec3us b) {
        return sub(new Vec3us(), a & 0xffff, a & 0xffff, a & 0xffff, b);
    }

    public static Vec3us sub_(final int a, final Vec3us b) {
        return sub(new Vec3us(), a, a, a, b);
    }

    public static Vec3us sub(final UShort a, final Vec3us b) {
        return sub(b, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, b);
    }

    public static Vec3us sub(final short a, final Vec3us b) {
        return sub(b, a & 0xffff, a & 0xffff, a & 0xffff, b);
    }

    public static Vec3us sub(final int a, final Vec3us b) {
        return sub(b, a, a, a, b);
    }

    public static Vec3us sub(final Vec3us res, final UShort a, final Vec3us b) {
        return sub(res, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, b);
    }

    public static Vec3us sub(final Vec3us res, final short a, final Vec3us b) {
        return sub(res, a & 0xffff, a & 0xffff, a & 0xffff, b);
    }

    public static Vec3us sub(final Vec3us res, final int a, final Vec3us b) {
        return sub(res, a, a, a, b);
    }

    public static Vec3us sub(final Vec3us res, final UShort aX, final UShort aY, final UShort aZ, final Vec3us b) {
        return sub(res, aX.value & 0xffff, aY.value & 0xffff, aZ.value & 0xffff, b);
    }

    public static Vec3us sub(final Vec3us res, final short aX, final short aY, final short aZ, final Vec3us b) {
        return sub(res, aX & 0xffff, aY & 0xffff, aZ & 0xffff, b);
    }

    public static Vec3us sub(final Vec3us res, final int aX, final int aY, final int aZ, final Vec3us b) {
        res.x.value = (short) (aX - (b.x.value & 0xffff));
        res.y.value = (short) (aY - (b.y.value & 0xffff));
        res.z.value = (short) (aZ - (b.z.value & 0xffff));
        return res;
    }

    public static Vec4 sub_(final float a, final Vec4 b) {
        return sub(new Vec4(), a, a, a, a, b);
    }

    public static Vec4 sub(final float a, final Vec4 b) {
        return sub(b, a, a, a, a, b);
    }

    public static Vec4 sub(final Vec4 res, final float a, final Vec4 b) {
        return sub(res, a, a, a, a, b);
    }

    public static Vec4 sub(final Vec4 res, final float aX, final float aY, final float aZ, final float aW, final Vec4 b) {
        res.x = aX - b.x;
        res.y = aY - b.y;
        res.z = aZ - b.z;
        res.w = aW - b.w;
        return res;
    }

    public static Vec4b sub_(final byte a, final Vec4b b) {
        return sub(new Vec4b(), (int) a, a, a, a, b);
    }

    public static Vec4b sub_(final int a, final Vec4b b) {
        return sub(new Vec4b(), a, a, a, a, b);
    }

    public static Vec4b sub(final byte a, final Vec4b b) {
        return sub(b, (int) a, a, a, a, b);
    }

    public static Vec4b sub(final int a, final Vec4b b) {
        return sub(b, a, a, a, a, b);
    }

    public static Vec4b sub(final Vec4b res, final byte a, final Vec4b b) {
        return sub(res, (int) a, a, a, a, b);
    }

    public static Vec4b sub(final Vec4b res, final int a, final Vec4b b) {
        return sub(res, a, a, a, a, b);
    }

    public static Vec4b sub(final Vec4b res, final byte aX, final byte aY, final byte aZ, final byte aW, final Vec4b b) {
        return sub(res, (int) aX, aY, aZ, aW, b);
    }

    public static Vec4b sub(final Vec4b res, final int aX, final int aY, final int aZ, final int aW, final Vec4b b) {
        res.x = (byte) (aX - b.x);
        res.y = (byte) (aY - b.y);
        res.z = (byte) (aZ - b.z);
        res.w = (byte) (aW - b.w);
        return res;
    }

    public static Vec4d sub_(final double a, final Vec4d b) {
        return sub(new Vec4d(), a, a, a, a, b);
    }

    public static Vec4d sub(final double a, final Vec4d b) {
        return sub(b, a, a, a, a, b);
    }

    public static Vec4d sub(final Vec4d res, final double a, final Vec4d b) {
        return sub(res, a, a, a, a, b);
    }

    public static Vec4d sub(final Vec4d res, final double aX, final double aY, final double aZ, final double aW, final Vec4d b) {
        res.x = aX - b.x;
        res.y = aY - b.y;
        res.z = aZ - b.z;
        res.w = aW - b.w;
        return res;
    }

    public static Vec4i sub_(final int a, final Vec4i b) {
        return sub(new Vec4i(), a, a, a, a, b);
    }

    public static Vec4i sub(final int a, final Vec4i b) {
        return sub(b, a, a, a, a, b);
    }

    public static Vec4i sub(final Vec4i res, final int a, final Vec4i b) {
        return sub(res, a, a, a, a, b);
    }

    public static Vec4i sub(final Vec4i res, final int aX, final int aY, final int aZ, final int aW, final Vec4i b) {
        res.x = aX - b.x;
        res.y = aY - b.y;
        res.z = aZ - b.z;
        res.w = aW - b.w;
        return res;
    }

    public static Vec4l sub_(final long a, final Vec4l b) {
        return sub(new Vec4l(), a, a, a, a, b);
    }

    public static Vec4l sub(final long a, final Vec4l b) {
        return sub(b, a, a, a, a, b);
    }

    public static Vec4l sub(final Vec4l res, final long a, final Vec4l b) {
        return sub(res, a, a, a, a, b);
    }

    public static Vec4l sub(final Vec4l res, final long aX, final long aY, final long aZ, final long aW, final Vec4l b) {
        res.x = aX - b.x;
        res.y = aY - b.y;
        res.z = aZ - b.z;
        res.w = aW - b.w;
        return res;
    }

    public static Vec4s sub_(final short a, final Vec4s b) {
        return sub(new Vec4s(), a, a, a, a, b);
    }

    public static Vec4s sub(final short a, final Vec4s b) {
        return sub(b, a, a, a, a, b);
    }

    public static Vec4s sub(final Vec4s res, final short a, final Vec4s b) {
        return sub(res, a, a, a, a, b);
    }

    public static Vec4s sub(final Vec4s res, final short aX, final short aY, final short aZ, final short aW, final Vec4s b) {
        res.x = (short) (aX - b.x);
        res.y = (short) (aY - b.y);
        res.z = (short) (aZ - b.z);
        res.w = (short) (aW - b.w);
        return res;
    }

    public static Vec4ub sub_(final UByte a, final Vec4ub b) {
        return sub(new Vec4ub(), a.value & 0xff, a.value & 0xff, a.value & 0xff, a.value & 0xff, b);
    }

    public static Vec4ub sub_(final byte a, final Vec4ub b) {
        return sub(new Vec4ub(), a & 0xff, a & 0xff, a & 0xff, a & 0xff, b);
    }

    public static Vec4ub sub_(final int a, final Vec4ub b) {
        return sub(new Vec4ub(), a, a, a, a, b);
    }

    public static Vec4ub sub(final UByte a, final Vec4ub b) {
        return sub(b, a.value & 0xff, a.value & 0xff, a.value & 0xff, a.value & 0xff, b);
    }

    public static Vec4ub sub(final byte a, final Vec4ub b) {
        return sub(b, a & 0xff, a & 0xff, a & 0xff, a & 0xff, b);
    }

    public static Vec4ub sub(final int a, final Vec4ub b) {
        return sub(b, a, a, a, a, b);
    }

    public static Vec4ub sub(final Vec4ub res, final UByte a, final Vec4ub b) {
        return sub(res, a.value & 0xff, a.value & 0xff, a.value & 0xff, a.value & 0xff, b);
    }

    public static Vec4ub sub(final Vec4ub res, final byte a, final Vec4ub b) {
        return sub(res, a & 0xff, a & 0xff, a & 0xff, a & 0xff, b);
    }

    public static Vec4ub sub(final Vec4ub res, final int a, final Vec4ub b) {
        return sub(res, a, a, a, a, b);
    }

    public static Vec4ub sub(final Vec4ub res, final UByte aX, final UByte aY, final UByte aZ, final UByte aW, final Vec4ub b) {
        return sub(res, aX.value & 0xff, aY.value & 0xff, aZ.value & 0xff, aW.value & 0xff, b);
    }

    public static Vec4ub sub(final Vec4ub res, final byte aX, final byte aY, final byte aZ, final byte aW, final Vec4ub b) {
        return sub(res, aX & 0xff, aY & 0xff, aZ & 0xff, aW & 0xff, b);
    }

    public static Vec4ub sub(final Vec4ub res, final int aX, final int aY, final int aZ, final int aW, final Vec4ub b) {
        res.x.value = (byte) (aX - (b.x.value & 0xff));
        res.y.value = (byte) (aY - (b.y.value & 0xff));
        res.z.value = (byte) (aZ - (b.z.value & 0xff));
        res.w.value = (byte) (aW - (b.w.value & 0xff));
        return res;
    }

    public static Vec4ui sub_(final UInt a, final Vec4ui b) {
        return sub(new Vec4ui(), a.value, a.value, a.value, a.value, b);
    }

    public static Vec4ui sub_(final int a, final Vec4ui b) {
        return sub(new Vec4ui(), a, a, a, a, b);
    }

    public static Vec4ui sub(final UInt a, final Vec4ui b) {
        return sub(b, a.value, a.value, a.value, a.value, b);
    }

    public static Vec4ui sub(final int a, final Vec4ui b) {
        return sub(b, a, a, a, a, b);
    }

    public static Vec4ui sub(final Vec4ui res, final UInt a, final Vec4ui b) {
        return sub(res, a.value, a.value, a.value, a.value, b);
    }

    public static Vec4ui sub(final Vec4ui res, final int a, final Vec4ui b) {
        return sub(res, a, a, a, a, b);
    }

    public static Vec4ui sub(final Vec4ui res, final UInt aX, final UInt aY, final UInt aZ, final UInt aW, final Vec4ui b) {
        return sub(res, aX.value, aY.value, aZ.value, aW.value, b);
    }

    public static Vec4ui sub(final Vec4ui res, final int aX, final int aY, final int aZ, final int aW, final Vec4ui b) {
        res.x.value = aX - b.x.value;
        res.y.value = aY - b.y.value;
        res.z.value = aZ - b.z.value;
        res.w.value = aW - b.w.value;
        return res;
    }

    public static Vec4ul sub_(final ULong a, final Vec4ul b) {
        return sub(new Vec4ul(), a.value, a.value, a.value, a.value, b);
    }

    public static Vec4ul sub_(final long a, final Vec4ul b) {
        return sub(new Vec4ul(), a, a, a, a, b);
    }

    public static Vec4ul sub(final ULong a, final Vec4ul b) {
        return sub(b, a.value, a.value, a.value, a.value, b);
    }

    public static Vec4ul sub(final long a, final Vec4ul b) {
        return sub(b, a, a, a, a, b);
    }

    public static Vec4ul sub(final Vec4ul res, final ULong a, final Vec4ul b) {
        return sub(res, a.value, a.value, a.value, a.value, b);
    }

    public static Vec4ul sub(final Vec4ul res, final long a, final Vec4ul b) {
        return sub(res, a, a, a, a, b);
    }

    public static Vec4ul sub(final Vec4ul res, final ULong aX, final ULong aY, final ULong aZ, final ULong aW, final Vec4ul b) {
        return sub(res, aX.value, aY.value, aZ.value, aW.value, b);
    }

    public static Vec4ul sub(final Vec4ul res, final long aX, final long aY, final long aZ, final long aW, final Vec4ul b) {
        res.x.value = aX - b.x.value;
        res.y.value = aY - b.y.value;
        res.z.value = aZ - b.z.value;
        res.w.value = aW - b.w.value;
        return res;
    }

    public static Vec4us sub_(final UShort a, final Vec4us b) {
        return sub(new Vec4us(), a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, b);
    }

    public static Vec4us sub_(final short a, final Vec4us b) {
        return sub(new Vec4us(), a & 0xffff, a & 0xffff, a & 0xffff, a & 0xffff, b);
    }

    public static Vec4us sub_(final int a, final Vec4us b) {
        return sub(new Vec4us(), a, a, a, a, b);
    }

    public static Vec4us sub(final UShort a, final Vec4us b) {
        return sub(b, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, b);
    }

    public static Vec4us sub(final short a, final Vec4us b) {
        return sub(b, a & 0xffff, a & 0xffff, a & 0xffff, a & 0xffff, b);
    }

    public static Vec4us sub(final int a, final Vec4us b) {
        return sub(b, a, a, a, a, b);
    }

    public static Vec4us sub(final Vec4us res, final UShort a, final Vec4us b) {
        return sub(res, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, b);
    }

    public static Vec4us sub(final Vec4us res, final short a, final Vec4us b) {
        return sub(res, a & 0xffff, a & 0xffff, a & 0xffff, a & 0xffff, b);
    }

    public static Vec4us sub(final Vec4us res, final int a, final Vec4us b) {
        return sub(res, a, a, a, a, b);
    }

    public static Vec4us sub(final Vec4us res, final UShort aX, final UShort aY, final UShort aZ, final UShort aW, final Vec4us b) {
        return sub(res, aX.value & 0xffff, aY.value & 0xffff, aZ.value & 0xffff, aW.value & 0xffff, b);
    }

    public static Vec4us sub(final Vec4us res, final short aX, final short aY, final short aZ, final short aW, final Vec4us b) {
        return sub(res, aX & 0xffff, aY & 0xffff, aZ & 0xffff, aW & 0xffff, b);
    }

    public static Vec4us sub(final Vec4us res, final int aX, final int aY, final int aZ, final int aW, final Vec4us b) {
        res.x.value = (short) (aX - (b.x.value & 0xffff));
        res.y.value = (short) (aY - (b.y.value & 0xffff));
        res.z.value = (short) (aZ - (b.z.value & 0xffff));
        res.w.value = (short) (aW - (b.w.value & 0xffff));
        return res;
    }

    public static Vec2 mul_(final float a, final Vec2 b) {
        return BasicOperators.mul(new Vec2(), b, a, a);
    }

    public static Vec2 mul(final float a, final Vec2 b) {
        return BasicOperators.mul(b, b, a, a);
    }

    public static Vec2 mul(final Vec2 res, final float a, final Vec2 b) {
        return BasicOperators.mul(res, b, a, a);
    }

    public static Vec2 mul(final Vec2 res, final float aX, final float aY, final Vec2 b) {
        return BasicOperators.mul(res, b, aX, aY);
    }

    public static Vec2b mul_(final byte a, final Vec2b b) {
        return BasicOperators.mul(new Vec2b(), b, (int) a, a);
    }

    public static Vec2b mul_(final int a, final Vec2b b) {
        return BasicOperators.mul(new Vec2b(), b, a, a);
    }

    public static Vec2b mul(final byte a, final Vec2b b) {
        return BasicOperators.mul(b, b, (int) a, a);
    }

    public static Vec2b mul(final int a, final Vec2b b) {
        return BasicOperators.mul(b, b, a, a);
    }

    public static Vec2b mul(final Vec2b res, final byte a, final Vec2b b) {
        return BasicOperators.mul(res, b, (int) a, a);
    }

    public static Vec2b mul(final Vec2b res, final int a, final Vec2b b) {
        return BasicOperators.mul(res, b, a, a);
    }

    public static Vec2b mul(final Vec2b res, final byte aX, final byte aY, final Vec2b b) {
        return BasicOperators.mul(res, b, (int) aX, aY);
    }

    public static Vec2b mul(final Vec2b res, final int aX, final int aY, final Vec2b b) {
        return BasicOperators.mul(res, b, aX, aY);
    }

    public static Vec2d mul_(final double a, final Vec2d b) {
        return BasicOperators.mul(new Vec2d(), b, a, a);
    }

    public static Vec2d mul(final double a, final Vec2d b) {
        return BasicOperators.mul(b, b, a, a);
    }

    public static Vec2d mul(final Vec2d res, final double a, final Vec2d b) {
        return BasicOperators.mul(res, b, a, a);
    }

    public static Vec2d mul(final Vec2d res, final double aX, final double aY, final Vec2d b) {
        return BasicOperators.mul(res, b, aX, aY);
    }

    public static Vec2i mul_(final int a, final Vec2i b) {
        return BasicOperators.mul(new Vec2i(), b, a, a);
    }

    public static Vec2i mul(final int a, final Vec2i b) {
        return BasicOperators.mul(b, b, a, a);
    }

    public static Vec2i mul(final Vec2i res, final int a, final Vec2i b) {
        return BasicOperators.mul(res, b, a, a);
    }

    public static Vec2i mul(final Vec2i res, final int aX, final int aY, final Vec2i b) {
        return BasicOperators.mul(res, b, aX, aY);
    }

    public static Vec2l mul_(final long a, final Vec2l b) {
        return BasicOperators.mul(new Vec2l(), b, a, a);
    }

    public static Vec2l mul(final long a, final Vec2l b) {
        return BasicOperators.mul(b, b, a, a);
    }

    public static Vec2l mul(final Vec2l res, final long a, final Vec2l b) {
        return BasicOperators.mul(res, b, a, a);
    }

    public static Vec2l mul(final Vec2l res, final long aX, final long aY, final Vec2l b) {
        return BasicOperators.mul(res, b, aX, aY);
    }

    public static Vec2s mul_(final short a, final Vec2s b) {
        return BasicOperators.mul(new Vec2s(), b, a, a);
    }

    public static Vec2s mul(final short a, final Vec2s b) {
        return BasicOperators.mul(b, b, a, a);
    }

    public static Vec2s mul(final Vec2s res, final short a, final Vec2s b) {
        return BasicOperators.mul(res, b, a, a);
    }

    public static Vec2s mul(final Vec2s res, final short aX, final short aY, final Vec2s b) {
        return BasicOperators.mul(res, b, aX, aY);
    }

    public static Vec2ub mul_(final UByte a, final Vec2ub b) {
        return BasicOperators.mul(new Vec2ub(), b, a.value & 0xff, a.value & 0xff);
    }

    public static Vec2ub mul_(final byte a, final Vec2ub b) {
        return BasicOperators.mul(new Vec2ub(), b, a & 0xff, a & 0xff);
    }

    public static Vec2ub mul_(final int a, final Vec2ub b) {
        return BasicOperators.mul(new Vec2ub(), b, a, a);
    }

    public static Vec2ub mul(final UByte a, final Vec2ub b) {
        return BasicOperators.mul(b, b, a.value & 0xff, a.value & 0xff);
    }

    public static Vec2ub mul(final byte a, final Vec2ub b) {
        return BasicOperators.mul(b, b, a & 0xff, a & 0xff);
    }

    public static Vec2ub mul(final int a, final Vec2ub b) {
        return BasicOperators.mul(b, b, a, a);
    }

    public static Vec2ub mul(final Vec2ub res, final UByte a, final Vec2ub b) {
        return BasicOperators.mul(res, b, a.value & 0xff, a.value & 0xff);
    }

    public static Vec2ub mul(final Vec2ub res, final byte a, final Vec2ub b) {
        return BasicOperators.mul(res, b, a & 0xff, a & 0xff);
    }

    public static Vec2ub mul(final Vec2ub res, final int a, final Vec2ub b) {
        return BasicOperators.mul(res, b, a, a);
    }

    public static Vec2ub mul(final Vec2ub res, final UByte aX, final UByte aY, final Vec2ub b) {
        return BasicOperators.mul(res, b, aX.value & 0xff, aY.value & 0xff);
    }

    public static Vec2ub mul(final Vec2ub res, final byte aX, final byte aY, final Vec2ub b) {
        return BasicOperators.mul(res, b, aX & 0xff, aY & 0xff);
    }

    public static Vec2ub mul(final Vec2ub res, final int aX, final int aY, final Vec2ub b) {
        return BasicOperators.mul(res, b, aX, aY);
    }

    public static Vec2ui mul_(final UInt a, final Vec2ui b) {
        return BasicOperators.mul(new Vec2ui(), b, a.value, a.value);
    }

    public static Vec2ui mul_(final int a, final Vec2ui b) {
        return BasicOperators.mul(new Vec2ui(), b, a, a);
    }

    public static Vec2ui mul(final UInt a, final Vec2ui b) {
        return BasicOperators.mul(b, b, a.value, a.value);
    }

    public static Vec2ui mul(final int a, final Vec2ui b) {
        return BasicOperators.mul(b, b, a, a);
    }

    public static Vec2ui mul(final Vec2ui res, final UInt a, final Vec2ui b) {
        return BasicOperators.mul(res, b, a.value, a.value);
    }

    public static Vec2ui mul(final Vec2ui res, final int a, final Vec2ui b) {
        return BasicOperators.mul(res, b, a, a);
    }

    public static Vec2ui mul(final Vec2ui res, final UInt aX, final UInt aY, final Vec2ui b) {
        return BasicOperators.mul(res, b, aX.value, aY.value);
    }

    public static Vec2ui mul(final Vec2ui res, final int aX, final int aY, final Vec2ui b) {
        return BasicOperators.mul(res, b, aX, aY);
    }

    public static Vec2ul mul_(final ULong a, final Vec2ul b) {
        return BasicOperators.mul(new Vec2ul(), b, a.value, a.value);
    }

    public static Vec2ul mul_(final long a, final Vec2ul b) {
        return BasicOperators.mul(new Vec2ul(), b, a, a);
    }

    public static Vec2ul mul(final ULong a, final Vec2ul b) {
        return BasicOperators.mul(b, b, a.value, a.value);
    }

    public static Vec2ul mul(final long a, final Vec2ul b) {
        return BasicOperators.mul(b, b, a, a);
    }

    public static Vec2ul mul(final Vec2ul res, final ULong a, final Vec2ul b) {
        return BasicOperators.mul(res, b, a.value, a.value);
    }

    public static Vec2ul mul(final Vec2ul res, final long a, final Vec2ul b) {
        return BasicOperators.mul(res, b, a, a);
    }

    public static Vec2ul mul(final Vec2ul res, final ULong aX, final ULong aY, final Vec2ul b) {
        return BasicOperators.mul(res, b, aX.value, aY.value);
    }

    public static Vec2ul mul(final Vec2ul res, final long aX, final long aY, final Vec2ul b) {
        return BasicOperators.mul(res, b, aX, aY);
    }

    public static Vec2us mul_(final UShort a, final Vec2us b) {
        return BasicOperators.mul(new Vec2us(), b, a.value & 0xffff, a.value & 0xffff);
    }

    public static Vec2us mul_(final short a, final Vec2us b) {
        return BasicOperators.mul(new Vec2us(), b, a & 0xffff, a & 0xffff);
    }

    public static Vec2us mul_(final int a, final Vec2us b) {
        return BasicOperators.mul(new Vec2us(), b, a, a);
    }

    public static Vec2us mul(final UShort a, final Vec2us b) {
        return BasicOperators.mul(b, b, a.value & 0xffff, a.value & 0xffff);
    }

    public static Vec2us mul(final short a, final Vec2us b) {
        return BasicOperators.mul(b, b, a & 0xffff, a & 0xffff);
    }

    public static Vec2us mul(final int a, final Vec2us b) {
        return BasicOperators.mul(b, b, a, a);
    }

    public static Vec2us mul(final Vec2us res, final UShort a, final Vec2us b) {
        return BasicOperators.mul(res, b, a.value & 0xffff, a.value & 0xffff);
    }

    public static Vec2us mul(final Vec2us res, final short a, final Vec2us b) {
        return BasicOperators.mul(res, b, a & 0xffff, a & 0xffff);
    }

    public static Vec2us mul(final Vec2us res, final int a, final Vec2us b) {
        return BasicOperators.mul(res, b, a, a);
    }

    public static Vec2us mul(final Vec2us res, final UShort aX, final UShort aY, final Vec2us b) {
        return BasicOperators.mul(res, b, aX.value & 0xffff, aY.value & 0xffff);
    }

    public static Vec2us mul(final Vec2us res, final short aX, final short aY, final Vec2us b) {
        return BasicOperators.mul(res, b, aX & 0xffff, aY & 0xffff);
    }

    public static Vec2us mul(final Vec2us res, final int aX, final int aY, final Vec2us b) {
        return BasicOperators.mul(res, b, aX, aY);
    }

    public static Vec3 mul_(final float a, final Vec3 b) {
        return BasicOperators.mul(new Vec3(), b, a, a, a);
    }

    public static Vec3 mul(final float a, final Vec3 b) {
        return BasicOperators.mul(b, b, a, a, a);
    }

    public static Vec3 mul(final Vec3 res, final float a, final Vec3 b) {
        return BasicOperators.mul(res, b, a, a, a);
    }

    public static Vec3 mul(final Vec3 res, final float aX, final float aY, final float aZ, final Vec3 b) {
        return BasicOperators.mul(res, b, aX, aY, aZ);
    }

    public static Vec3b mul_(final byte a, final Vec3b b) {
        return BasicOperators.mul(new Vec3b(), b, (int) a, a, a);
    }

    public static Vec3b mul_(final int a, final Vec3b b) {
        return BasicOperators.mul(new Vec3b(), b, a, a, a);
    }

    public static Vec3b mul(final byte a, final Vec3b b) {
        return BasicOperators.mul(b, b, (int) a, a, a);
    }

    public static Vec3b mul(final int a, final Vec3b b) {
        return BasicOperators.mul(b, b, a, a, a);
    }

    public static Vec3b mul(final Vec3b res, final byte a, final Vec3b b) {
        return BasicOperators.mul(res, b, (int) a, a, a);
    }

    public static Vec3b mul(final Vec3b res, final int a, final Vec3b b) {
        return BasicOperators.mul(res, b, a, a, a);
    }

    public static Vec3b mul(final Vec3b res, final byte aX, final byte aY, final byte aZ, final Vec3b b) {
        return BasicOperators.mul(res, b, (int) aX, aY, aZ);
    }

    public static Vec3b mul(final Vec3b res, final int aX, final int aY, final int aZ, final Vec3b b) {
        return BasicOperators.mul(res, b, aX, aY, aZ);
    }

    public static Vec3d mul_(final double a, final Vec3d b) {
        return BasicOperators.mul(new Vec3d(), b, a, a, a);
    }

    public static Vec3d mul(final double a, final Vec3d b) {
        return BasicOperators.mul(b, b, a, a, a);
    }

    public static Vec3d mul(final Vec3d res, final double a, final Vec3d b) {
        return BasicOperators.mul(res, b, a, a, a);
    }

    public static Vec3d mul(final Vec3d res, final double aX, final double aY, final double aZ, final Vec3d b) {
        return BasicOperators.mul(res, b, aX, aY, aZ);
    }

    public static Vec3i mul_(final int a, final Vec3i b) {
        return BasicOperators.mul(new Vec3i(), b, a, a, a);
    }

    public static Vec3i mul(final int a, final Vec3i b) {
        return BasicOperators.mul(b, b, a, a, a);
    }

    public static Vec3i mul(final Vec3i res, final int a, final Vec3i b) {
        return BasicOperators.mul(res, b, a, a, a);
    }

    public static Vec3i mul(final Vec3i res, final int aX, final int aY, final int aZ, final Vec3i b) {
        return BasicOperators.mul(res, b, aX, aY, aZ);
    }

    public static Vec3l mul_(final long a, final Vec3l b) {
        return BasicOperators.mul(new Vec3l(), b, a, a, a);
    }

    public static Vec3l mul(final long a, final Vec3l b) {
        return BasicOperators.mul(b, b, a, a, a);
    }

    public static Vec3l mul(final Vec3l res, final long a, final Vec3l b) {
        return BasicOperators.mul(res, b, a, a, a);
    }

    public static Vec3l mul(final Vec3l res, final long aX, final long aY, final long aZ, final Vec3l b) {
        return BasicOperators.mul(res, b, aX, aY, aZ);
    }

    public static Vec3s mul_(final short a, final Vec3s b) {
        return BasicOperators.mul(new Vec3s(), b, a, a, a);
    }

    public static Vec3s mul(final short a, final Vec3s b) {
        return BasicOperators.mul(b, b, a, a, a);
    }

    public static Vec3s mul(final Vec3s res, final short a, final Vec3s b) {
        return BasicOperators.mul(res, b, a, a, a);
    }

    public static Vec3s mul(final Vec3s res, final short aX, final short aY, final short aZ, final Vec3s b) {
        return BasicOperators.mul(res, b, aX, aY, aZ);
    }

    public static Vec3ub mul_(final UByte a, final Vec3ub b) {
        return BasicOperators.mul(new Vec3ub(), b, a.value & 0xff, a.value & 0xff, a.value & 0xff);
    }

    public static Vec3ub mul_(final byte a, final Vec3ub b) {
        return BasicOperators.mul(new Vec3ub(), b, a & 0xff, a & 0xff, a & 0xff);
    }

    public static Vec3ub mul_(final int a, final Vec3ub b) {
        return BasicOperators.mul(new Vec3ub(), b, a, a, a);
    }

    public static Vec3ub mul(final UByte a, final Vec3ub b) {
        return BasicOperators.mul(b, b, a.value & 0xff, a.value & 0xff, a.value & 0xff);
    }

    public static Vec3ub mul(final byte a, final Vec3ub b) {
        return BasicOperators.mul(b, b, a & 0xff, a & 0xff, a & 0xff);
    }

    public static Vec3ub mul(final int a, final Vec3ub b) {
        return BasicOperators.mul(b, b, a, a, a);
    }

    public static Vec3ub mul(final Vec3ub res, final UByte a, final Vec3ub b) {
        return BasicOperators.mul(res, b, a.value & 0xff, a.value & 0xff, a.value & 0xff);
    }

    public static Vec3ub mul(final Vec3ub res, final byte a, final Vec3ub b) {
        return BasicOperators.mul(res, b, a & 0xff, a & 0xff, a & 0xff);
    }

    public static Vec3ub mul(final Vec3ub res, final int a, final Vec3ub b) {
        return BasicOperators.mul(res, b, a, a, a);
    }

    public static Vec3ub mul(final Vec3ub res, final UByte aX, final UByte aY, final UByte aZ, final Vec3ub b) {
        return BasicOperators.mul(res, b, aX.value & 0xff, aY.value & 0xff, aZ.value & 0xff);
    }

    public static Vec3ub mul(final Vec3ub res, final byte aX, final byte aY, final byte aZ, final Vec3ub b) {
        return BasicOperators.mul(res, b, aX & 0xff, aY & 0xff, aZ & 0xff);
    }

    public static Vec3ub mul(final Vec3ub res, final int aX, final int aY, final int aZ, final Vec3ub b) {
        return BasicOperators.mul(res, b, aX, aY, aZ);
    }

    public static Vec3ui mul_(final UInt a, final Vec3ui b) {
        return BasicOperators.mul(new Vec3ui(), b, a.value, a.value, a.value);
    }

    public static Vec3ui mul_(final int a, final Vec3ui b) {
        return BasicOperators.mul(new Vec3ui(), b, a, a, a);
    }

    public static Vec3ui mul(final UInt a, final Vec3ui b) {
        return BasicOperators.mul(b, b, a.value, a.value, a.value);
    }

    public static Vec3ui mul(final int a, final Vec3ui b) {
        return BasicOperators.mul(b, b, a, a, a);
    }

    public static Vec3ui mul(final Vec3ui res, final UInt a, final Vec3ui b) {
        return BasicOperators.mul(res, b, a.value, a.value, a.value);
    }

    public static Vec3ui mul(final Vec3ui res, final int a, final Vec3ui b) {
        return BasicOperators.mul(res, b, a, a, a);
    }

    public static Vec3ui mul(final Vec3ui res, final UInt aX, final UInt aY, final UInt aZ, final Vec3ui b) {
        return BasicOperators.mul(res, b, aX.value, aY.value, aZ.value);
    }

    public static Vec3ui mul(final Vec3ui res, final int aX, final int aY, final int aZ, final Vec3ui b) {
        return BasicOperators.mul(res, b, aX, aY, aZ);
    }

    public static Vec3ul mul_(final ULong a, final Vec3ul b) {
        return BasicOperators.mul(new Vec3ul(), b, a.value, a.value, a.value);
    }

    public static Vec3ul mul_(final long a, final Vec3ul b) {
        return BasicOperators.mul(new Vec3ul(), b, a, a, a);
    }

    public static Vec3ul mul(final ULong a, final Vec3ul b) {
        return BasicOperators.mul(b, b, a.value, a.value, a.value);
    }

    public static Vec3ul mul(final long a, final Vec3ul b) {
        return BasicOperators.mul(b, b, a, a, a);
    }

    public static Vec3ul mul(final Vec3ul res, final ULong a, final Vec3ul b) {
        return BasicOperators.mul(res, b, a.value, a.value, a.value);
    }

    public static Vec3ul mul(final Vec3ul res, final long a, final Vec3ul b) {
        return BasicOperators.mul(res, b, a, a, a);
    }

    public static Vec3ul mul(final Vec3ul res, final ULong aX, final ULong aY, final ULong aZ, final Vec3ul b) {
        return BasicOperators.mul(res, b, aX.value, aY.value, aZ.value);
    }

    public static Vec3ul mul(final Vec3ul res, final long aX, final long aY, final long aZ, final Vec3ul b) {
        return BasicOperators.mul(res, b, aX, aY, aZ);
    }

    public static Vec3us mul_(final UShort a, final Vec3us b) {
        return BasicOperators.mul(new Vec3us(), b, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff);
    }

    public static Vec3us mul_(final short a, final Vec3us b) {
        return BasicOperators.mul(new Vec3us(), b, a & 0xffff, a & 0xffff, a & 0xffff);
    }

    public static Vec3us mul_(final int a, final Vec3us b) {
        return BasicOperators.mul(new Vec3us(), b, a, a, a);
    }

    public static Vec3us mul(final UShort a, final Vec3us b) {
        return BasicOperators.mul(b, b, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff);
    }

    public static Vec3us mul(final short a, final Vec3us b) {
        return BasicOperators.mul(b, b, a & 0xffff, a & 0xffff, a & 0xffff);
    }

    public static Vec3us mul(final int a, final Vec3us b) {
        return BasicOperators.mul(b, b, a, a, a);
    }

    public static Vec3us mul(final Vec3us res, final UShort a, final Vec3us b) {
        return BasicOperators.mul(res, b, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff);
    }

    public static Vec3us mul(final Vec3us res, final short a, final Vec3us b) {
        return BasicOperators.mul(res, b, a & 0xffff, a & 0xffff, a & 0xffff);
    }

    public static Vec3us mul(final Vec3us res, final int a, final Vec3us b) {
        return BasicOperators.mul(res, b, a, a, a);
    }

    public static Vec3us mul(final Vec3us res, final UShort aX, final UShort aY, final UShort aZ, final Vec3us b) {
        return BasicOperators.mul(res, b, aX.value & 0xffff, aY.value & 0xffff, aZ.value & 0xffff);
    }

    public static Vec3us mul(final Vec3us res, final short aX, final short aY, final short aZ, final Vec3us b) {
        return BasicOperators.mul(res, b, aX & 0xffff, aY & 0xffff, aZ & 0xffff);
    }

    public static Vec3us mul(final Vec3us res, final int aX, final int aY, final int aZ, final Vec3us b) {
        return BasicOperators.mul(res, b, aX, aY, aZ);
    }

    public static Vec4 mul_(final float a, final Vec4 b) {
        return BasicOperators.mul(new Vec4(), b, a, a, a, a);
    }

    public static Vec4 mul(final float a, final Vec4 b) {
        return BasicOperators.mul(b, b, a, a, a, a);
    }

    public static Vec4 mul(final Vec4 res, final float a, final Vec4 b) {
        return BasicOperators.mul(res, b, a, a, a, a);
    }

    public static Vec4 mul(final Vec4 res, final float aX, final float aY, final float aZ, final float aW, final Vec4 b) {
        return BasicOperators.mul(res, b, aX, aY, aZ, aW);
    }

    public static Vec4b mul_(final byte a, final Vec4b b) {
        return BasicOperators.mul(new Vec4b(), b, (int) a, a, a, a);
    }

    public static Vec4b mul_(final int a, final Vec4b b) {
        return BasicOperators.mul(new Vec4b(), b, a, a, a, a);
    }

    public static Vec4b mul(final byte a, final Vec4b b) {
        return BasicOperators.mul(b, b, (int) a, a, a, a);
    }

    public static Vec4b mul(final int a, final Vec4b b) {
        return BasicOperators.mul(b, b, a, a, a, a);
    }

    public static Vec4b mul(final Vec4b res, final byte a, final Vec4b b) {
        return BasicOperators.mul(res, b, (int) a, a, a, a);
    }

    public static Vec4b mul(final Vec4b res, final int a, final Vec4b b) {
        return BasicOperators.mul(res, b, a, a, a, a);
    }

    public static Vec4b mul(final Vec4b res, final byte aX, final byte aY, final byte aZ, final byte aW, final Vec4b b) {
        return BasicOperators.mul(res, b, (int) aX, aY, aZ, aW);
    }

    public static Vec4b mul(final Vec4b res, final int aX, final int aY, final int aZ, final int aW, final Vec4b b) {
        return BasicOperators.mul(res, b, aX, aY, aZ, aW);
    }

    public static Vec4d mul_(final double a, final Vec4d b) {
        return BasicOperators.mul(new Vec4d(), b, a, a, a, a);
    }

    public static Vec4d mul(final double a, final Vec4d b) {
        return BasicOperators.mul(b, b, a, a, a, a);
    }

    public static Vec4d mul(final Vec4d res, final double a, final Vec4d b) {
        return BasicOperators.mul(res, b, a, a, a, a);
    }

    public static Vec4d mul(final Vec4d res, final double aX, final double aY, final double aZ, final double aW, final Vec4d b) {
        return BasicOperators.mul(res, b, aX, aY, aZ, aW);
    }

    public static Vec4i mul_(final int a, final Vec4i b) {
        return BasicOperators.mul(new Vec4i(), b, a, a, a, a);
    }

    public static Vec4i mul(final int a, final Vec4i b) {
        return BasicOperators.mul(b, b, a, a, a, a);
    }

    public static Vec4i mul(final Vec4i res, final int a, final Vec4i b) {
        return BasicOperators.mul(res, b, a, a, a, a);
    }

    public static Vec4i mul(final Vec4i res, final int aX, final int aY, final int aZ, final int aW, final Vec4i b) {
        return BasicOperators.mul(res, b, aX, aY, aZ, aW);
    }

    public static Vec4l mul_(final long a, final Vec4l b) {
        return BasicOperators.mul(new Vec4l(), b, a, a, a, a);
    }

    public static Vec4l mul(final long a, final Vec4l b) {
        return BasicOperators.mul(b, b, a, a, a, a);
    }

    public static Vec4l mul(final Vec4l res, final long a, final Vec4l b) {
        return BasicOperators.mul(res, b, a, a, a, a);
    }

    public static Vec4l mul(final Vec4l res, final long aX, final long aY, final long aZ, final long aW, final Vec4l b) {
        return BasicOperators.mul(res, b, aX, aY, aZ, aW);
    }

    public static Vec4s mul_(final short a, final Vec4s b) {
        return BasicOperators.mul(new Vec4s(), b, a, a, a, a);
    }

    public static Vec4s mul(final short a, final Vec4s b) {
        return BasicOperators.mul(b, b, a, a, a, a);
    }

    public static Vec4s mul(final Vec4s res, final short a, final Vec4s b) {
        return BasicOperators.mul(res, b, a, a, a, a);
    }

    public static Vec4s mul(final Vec4s res, final short aX, final short aY, final short aZ, final short aW, final Vec4s b) {
        return BasicOperators.mul(res, b, aX, aY, aZ, aW);
    }

    public static Vec4ub mul_(final UByte a, final Vec4ub b) {
        return BasicOperators.mul(new Vec4ub(), b, a.value & 0xff, a.value & 0xff, a.value & 0xff, a.value & 0xff);
    }

    public static Vec4ub mul_(final byte a, final Vec4ub b) {
        return BasicOperators.mul(new Vec4ub(), b, a & 0xff, a & 0xff, a & 0xff, a & 0xff);
    }

    public static Vec4ub mul_(final int a, final Vec4ub b) {
        return BasicOperators.mul(new Vec4ub(), b, a, a, a, a);
    }

    public static Vec4ub mul(final UByte a, final Vec4ub b) {
        return BasicOperators.mul(b, b, a.value & 0xff, a.value & 0xff, a.value & 0xff, a.value & 0xff);
    }

    public static Vec4ub mul(final byte a, final Vec4ub b) {
        return BasicOperators.mul(b, b, a & 0xff, a & 0xff, a & 0xff, a & 0xff);
    }

    public static Vec4ub mul(final int a, final Vec4ub b) {
        return BasicOperators.mul(b, b, a, a, a, a);
    }

    public static Vec4ub mul(final Vec4ub res, final UByte a, final Vec4ub b) {
        return BasicOperators.mul(res, b, a.value & 0xff, a.value & 0xff, a.value & 0xff, a.value & 0xff);
    }

    public static Vec4ub mul(final Vec4ub res, final byte a, final Vec4ub b) {
        return BasicOperators.mul(res, b, a & 0xff, a & 0xff, a & 0xff, a & 0xff);
    }

    public static Vec4ub mul(final Vec4ub res, final int a, final Vec4ub b) {
        return BasicOperators.mul(res, b, a, a, a, a);
    }

    public static Vec4ub mul(final Vec4ub res, final UByte aX, final UByte aY, final UByte aZ, final UByte aW, final Vec4ub b) {
        return BasicOperators.mul(res, b, aX.value & 0xff, aY.value & 0xff, aZ.value & 0xff, aW.value & 0xff);
    }

    public static Vec4ub mul(final Vec4ub res, final byte aX, final byte aY, final byte aZ, final byte aW, final Vec4ub b) {
        return BasicOperators.mul(res, b, aX & 0xff, aY & 0xff, aZ & 0xff, aW & 0xff);
    }

    public static Vec4ub mul(final Vec4ub res, final int aX, final int aY, final int aZ, final int aW, final Vec4ub b) {
        return BasicOperators.mul(res, b, aX, aY, aZ, aW);
    }

    public static Vec4ui mul_(final UInt a, final Vec4ui b) {
        return BasicOperators.mul(new Vec4ui(), b, a.value, a.value, a.value, a.value);
    }

    public static Vec4ui mul_(final int a, final Vec4ui b) {
        return BasicOperators.mul(new Vec4ui(), b, a, a, a, a);
    }

    public static Vec4ui mul(final UInt a, final Vec4ui b) {
        return BasicOperators.mul(b, b, a.value, a.value, a.value, a.value);
    }

    public static Vec4ui mul(final int a, final Vec4ui b) {
        return BasicOperators.mul(b, b, a, a, a, a);
    }

    public static Vec4ui mul(final Vec4ui res, final UInt a, final Vec4ui b) {
        return BasicOperators.mul(res, b, a.value, a.value, a.value, a.value);
    }

    public static Vec4ui mul(final Vec4ui res, final int a, final Vec4ui b) {
        return BasicOperators.mul(res, b, a, a, a, a);
    }

    public static Vec4ui mul(final Vec4ui res, final UInt aX, final UInt aY, final UInt aZ, final UInt aW, final Vec4ui b) {
        return BasicOperators.mul(res, b, aX.value, aY.value, aZ.value, aW.value);
    }

    public static Vec4ui mul(final Vec4ui res, final int aX, final int aY, final int aZ, final int aW, final Vec4ui b) {
        return BasicOperators.mul(res, b, aX, aY, aZ, aW);
    }

    public static Vec4ul mul_(final ULong a, final Vec4ul b) {
        return BasicOperators.mul(new Vec4ul(), b, a.value, a.value, a.value, a.value);
    }

    public static Vec4ul mul_(final long a, final Vec4ul b) {
        return BasicOperators.mul(new Vec4ul(), b, a, a, a, a);
    }

    public static Vec4ul mul(final ULong a, final Vec4ul b) {
        return BasicOperators.mul(b, b, a.value, a.value, a.value, a.value);
    }

    public static Vec4ul mul(final long a, final Vec4ul b) {
        return BasicOperators.mul(b, b, a, a, a, a);
    }

    public static Vec4ul mul(final Vec4ul res, final ULong a, final Vec4ul b) {
        return BasicOperators.mul(res, b, a.value, a.value, a.value, a.value);
    }

    public static Vec4ul mul(final Vec4ul res, final long a, final Vec4ul b) {
        return BasicOperators.mul(res, b, a, a, a, a);
    }

    public static Vec4ul mul(final Vec4ul res, final ULong aX, final ULong aY, final ULong aZ, final ULong aW, final Vec4ul b) {
        return BasicOperators.mul(res, b, aX.value, aY.value, aZ.value, aW.value);
    }

    public static Vec4ul mul(final Vec4ul res, final long aX, final long aY, final long aZ, final long aW, final Vec4ul b) {
        return BasicOperators.mul(res, b, aX, aY, aZ, aW);
    }

    public static Vec4us mul_(final UShort a, final Vec4us b) {
        return BasicOperators.mul(new Vec4us(), b, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff);
    }

    public static Vec4us mul_(final short a, final Vec4us b) {
        return BasicOperators.mul(new Vec4us(), b, a & 0xffff, a & 0xffff, a & 0xffff, a & 0xffff);
    }

    public static Vec4us mul_(final int a, final Vec4us b) {
        return BasicOperators.mul(new Vec4us(), b, a, a, a, a);
    }

    public static Vec4us mul(final UShort a, final Vec4us b) {
        return BasicOperators.mul(b, b, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff);
    }

    public static Vec4us mul(final short a, final Vec4us b) {
        return BasicOperators.mul(b, b, a & 0xffff, a & 0xffff, a & 0xffff, a & 0xffff);
    }

    public static Vec4us mul(final int a, final Vec4us b) {
        return BasicOperators.mul(b, b, a, a, a, a);
    }

    public static Vec4us mul(final Vec4us res, final UShort a, final Vec4us b) {
        return BasicOperators.mul(res, b, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff);
    }

    public static Vec4us mul(final Vec4us res, final short a, final Vec4us b) {
        return BasicOperators.mul(res, b, a & 0xffff, a & 0xffff, a & 0xffff, a & 0xffff);
    }

    public static Vec4us mul(final Vec4us res, final int a, final Vec4us b) {
        return BasicOperators.mul(res, b, a, a, a, a);
    }

    public static Vec4us mul(final Vec4us res, final UShort aX, final UShort aY, final UShort aZ, final UShort aW, final Vec4us b) {
        return BasicOperators.mul(res, b, aX.value & 0xffff, aY.value & 0xffff, aZ.value & 0xffff, aW.value & 0xffff);
    }

    public static Vec4us mul(final Vec4us res, final short aX, final short aY, final short aZ, final short aW, final Vec4us b) {
        return BasicOperators.mul(res, b, aX & 0xffff, aY & 0xffff, aZ & 0xffff, aW & 0xffff);
    }

    public static Vec4us mul(final Vec4us res, final int aX, final int aY, final int aZ, final int aW, final Vec4us b) {
        return BasicOperators.mul(res, b, aX, aY, aZ, aW);
    }

    public static Vec2 div_(final float a, final Vec2 b) {
        return div(new Vec2(), a, a, b);
    }

    public static Vec2 div(final float a, final Vec2 b) {
        return div(b, a, a, b);
    }

    public static Vec2 div(final Vec2 res, final float a, final Vec2 b) {
        return div(res, a, a, b);
    }

    public static Vec2 div(final Vec2 res, final float aX, final float aY, final Vec2 b) {
        res.x = aX / b.x;
        res.y = aY / b.y;
        return res;
    }

    public static Vec2b div_(final byte a, final Vec2b b) {
        return div(new Vec2b(), (int) a, a, b);
    }

    public static Vec2b div_(final int a, final Vec2b b) {
        return div(new Vec2b(), a, a, b);
    }

    public static Vec2b div(final byte a, final Vec2b b) {
        return div(b, (int) a, a, b);
    }

    public static Vec2b div(final int a, final Vec2b b) {
        return div(b, a, a, b);
    }

    public static Vec2b div(final Vec2b res, final byte a, final Vec2b b) {
        return div(res, (int) a, a, b);
    }

    public static Vec2b div(final Vec2b res, final int a, final Vec2b b) {
        return div(res, a, a, b);
    }

    public static Vec2b div(final Vec2b res, final byte aX, final byte aY, final Vec2b b) {
        return div(res, (int) aX, aY, b);
    }

    public static Vec2b div(final Vec2b res, final int aX, final int aY, final Vec2b b) {
        res.x = (byte) (aX / b.x);
        res.y = (byte) (aY / b.y);
        return res;
    }

    public static Vec2d div_(final double a, final Vec2d b) {
        return div(new Vec2d(), a, a, b);
    }

    public static Vec2d div(final double a, final Vec2d b) {
        return div(b, a, a, b);
    }

    public static Vec2d div(final Vec2d res, final double a, final Vec2d b) {
        return div(res, a, a, b);
    }

    public static Vec2d div(final Vec2d res, final double aX, final double aY, final Vec2d b) {
        res.x = aX / b.x;
        res.y = aY / b.y;
        return res;
    }

    public static Vec2i div_(final int a, final Vec2i b) {
        return div(new Vec2i(), a, a, b);
    }

    public static Vec2i div(final int a, final Vec2i b) {
        return div(b, a, a, b);
    }

    public static Vec2i div(final Vec2i res, final int a, final Vec2i b) {
        return div(res, a, a, b);
    }

    public static Vec2i div(final Vec2i res, final int aX, final int aY, final Vec2i b) {
        res.x = aX / b.x;
        res.y = aY / b.y;
        return res;
    }

    public static Vec2l div_(final long a, final Vec2l b) {
        return div(new Vec2l(), a, a, b);
    }

    public static Vec2l div(final long a, final Vec2l b) {
        return div(b, a, a, b);
    }

    public static Vec2l div(final Vec2l res, final long a, final Vec2l b) {
        return div(res, a, a, b);
    }

    public static Vec2l div(final Vec2l res, final long aX, final long aY, final Vec2l b) {
        res.x = aX / b.x;
        res.y = aY / b.y;
        return res;
    }

    public static Vec2s div_(final short a, final Vec2s b) {
        return div(new Vec2s(), a, a, b);
    }

    public static Vec2s div(final short a, final Vec2s b) {
        return div(b, a, a, b);
    }

    public static Vec2s div(final Vec2s res, final short a, final Vec2s b) {
        return div(res, a, a, b);
    }

    public static Vec2s div(final Vec2s res, final short aX, final short aY, final Vec2s b) {
        res.x = (short) (aX / b.x);
        res.y = (short) (aY / b.y);
        return res;
    }

    public static Vec2ub div_(final UByte a, final Vec2ub b) {
        return div(new Vec2ub(), a.value & 0xff, a.value & 0xff, b);
    }

    public static Vec2ub div_(final byte a, final Vec2ub b) {
        return div(new Vec2ub(), a & 0xff, a & 0xff, b);
    }

    public static Vec2ub div_(final int a, final Vec2ub b) {
        return div(new Vec2ub(), a, a, b);
    }

    public static Vec2ub div(final UByte a, final Vec2ub b) {
        return div(b, a.value & 0xff, a.value & 0xff, b);
    }

    public static Vec2ub div(final byte a, final Vec2ub b) {
        return div(b, a & 0xff, a & 0xff, b);
    }

    public static Vec2ub div(final int a, final Vec2ub b) {
        return div(b, a, a, b);
    }

    public static Vec2ub div(final Vec2ub res, final UByte a, final Vec2ub b) {
        return div(res, a.value & 0xff, a.value & 0xff, b);
    }

    public static Vec2ub div(final Vec2ub res, final byte a, final Vec2ub b) {
        return div(res, a & 0xff, a & 0xff, b);
    }

    public static Vec2ub div(final Vec2ub res, final int a, final Vec2ub b) {
        return div(res, a, a, b);
    }

    public static Vec2ub div(final Vec2ub res, final UByte aX, final UByte aY, final Vec2ub b) {
        return div(res, aX.value & 0xff, aY.value & 0xff, b);
    }

    public static Vec2ub div(final Vec2ub res, final byte aX, final byte aY, final Vec2ub b) {
        return div(res, aX & 0xff, aY & 0xff, b);
    }

    public static Vec2ub div(final Vec2ub res, final int aX, final int aY, final Vec2ub b) {
        res.x.value = (byte) Integer.divideUnsigned(aX, b.x.value & 0xff);
        res.y.value = (byte) Integer.divideUnsigned(aY, b.y.value & 0xff);
        return res;
    }

    public static Vec2ui div_(final UInt a, final Vec2ui b) {
        return div(new Vec2ui(), a.value, a.value, b);
    }

    public static Vec2ui div_(final int a, final Vec2ui b) {
        return div(new Vec2ui(), a, a, b);
    }

    public static Vec2ui div(final UInt a, final Vec2ui b) {
        return div(b, a.value, a.value, b);
    }

    public static Vec2ui div(final int a, final Vec2ui b) {
        return div(b, a, a, b);
    }

    public static Vec2ui div(final Vec2ui res, final UInt a, final Vec2ui b) {
        return div(res, a.value, a.value, b);
    }

    public static Vec2ui div(final Vec2ui res, final int a, final Vec2ui b) {
        return div(res, a, a, b);
    }

    public static Vec2ui div(final Vec2ui res, final UInt aX, final UInt aY, final Vec2ui b) {
        return div(res, aX.value, aY.value, b);
    }

    public static Vec2ui div(final Vec2ui res, final int aX, final int aY, final Vec2ui b) {
        res.x.value = Integer.divideUnsigned(aX, b.x.value);
        res.y.value = Integer.divideUnsigned(aY, b.y.value);
        return res;
    }

    public static Vec2ul div_(final ULong a, final Vec2ul b) {
        return div(new Vec2ul(), a.value, a.value, b);
    }

    public static Vec2ul div_(final long a, final Vec2ul b) {
        return div(new Vec2ul(), a, a, b);
    }

    public static Vec2ul div(final ULong a, final Vec2ul b) {
        return div(b, a.value, a.value, b);
    }

    public static Vec2ul div(final long a, final Vec2ul b) {
        return div(b, a, a, b);
    }

    public static Vec2ul div(final Vec2ul res, final ULong a, final Vec2ul b) {
        return div(res, a.value, a.value, b);
    }

    public static Vec2ul div(final Vec2ul res, final long a, final Vec2ul b) {
        return div(res, a, a, b);
    }

    public static Vec2ul div(final Vec2ul res, final ULong aX, final ULong aY, final Vec2ul b) {
        return div(res, aX.value, aY.value, b);
    }

    public static Vec2ul div(final Vec2ul res, final long aX, final long aY, final Vec2ul b) {
        res.x.value = Long.divideUnsigned(aX, b.x.value);
        res.y.value = Long.divideUnsigned(aY, b.y.value);
        return res;
    }

    public static Vec2us div_(final UShort a, final Vec2us b) {
        return div(new Vec2us(), a.value & 0xffff, a.value & 0xffff, b);
    }

    public static Vec2us div_(final short a, final Vec2us b) {
        return div(new Vec2us(), a & 0xffff, a & 0xffff, b);
    }

    public static Vec2us div_(final int a, final Vec2us b) {
        return div(new Vec2us(), a, a, b);
    }

    public static Vec2us div(final UShort a, final Vec2us b) {
        return div(b, a.value & 0xffff, a.value & 0xffff, b);
    }

    public static Vec2us div(final short a, final Vec2us b) {
        return div(b, a & 0xffff, a & 0xffff, b);
    }

    public static Vec2us div(final int a, final Vec2us b) {
        return div(b, a, a, b);
    }

    public static Vec2us div(final Vec2us res, final UShort a, final Vec2us b) {
        return div(res, a.value & 0xffff, a.value & 0xffff, b);
    }

    public static Vec2us div(final Vec2us res, final short a, final Vec2us b) {
        return div(res, a & 0xffff, a & 0xffff, b);
    }

    public static Vec2us div(final Vec2us res, final int a, final Vec2us b) {
        return div(res, a, a, b);
    }

    public static Vec2us div(final Vec2us res, final UShort aX, final UShort aY, final Vec2us b) {
        return div(res, aX.value & 0xffff, aY.value & 0xffff, b);
    }

    public static Vec2us div(final Vec2us res, final short aX, final short aY, final Vec2us b) {
        return div(res, aX & 0xffff, aY & 0xffff, b);
    }

    public static Vec2us div(final Vec2us res, final int aX, final int aY, final Vec2us b) {
        res.x.value = (short) Integer.divideUnsigned(aX, b.x.value & 0xffff);
        res.y.value = (short) Integer.divideUnsigned(aY, b.y.value & 0xffff);
        return res;
    }

    public static Vec3 div_(final float a, final Vec3 b) {
        return BasicOperators.div(new Vec3(), b, a, a, a);
    }

    public static Vec3 div(final float a, final Vec3 b) {
        return BasicOperators.div(b, b, a, a, a);
    }

    public static Vec3 div(final Vec3 res, final float a, final Vec3 b) {
        return BasicOperators.div(res, b, a, a, a);
    }

    public static Vec3 div(final Vec3 res, final float aX, final float aY, final float aZ, final Vec3 b) {
        return BasicOperators.div(res, b, aX, aY, aZ);
    }

    public static Vec3b div_(final byte a, final Vec3b b) {
        return BasicOperators.div(new Vec3b(), b, (int) a, a, a);
    }

    public static Vec3b div_(final int a, final Vec3b b) {
        return BasicOperators.div(new Vec3b(), b, a, a, a);
    }

    public static Vec3b div(final byte a, final Vec3b b) {
        return BasicOperators.div(b, b, (int) a, a, a);
    }

    public static Vec3b div(final int a, final Vec3b b) {
        return BasicOperators.div(b, b, a, a, a);
    }

    public static Vec3b div(final Vec3b res, final byte a, final Vec3b b) {
        return BasicOperators.div(res, b, (int) a, a, a);
    }

    public static Vec3b div(final Vec3b res, final int a, final Vec3b b) {
        return BasicOperators.div(res, b, a, a, a);
    }

    public static Vec3b div(final Vec3b res, final byte aX, final byte aY, final byte aZ, final Vec3b b) {
        return BasicOperators.div(res, b, (int) aX, aY, aZ);
    }

    public static Vec3b div(final Vec3b res, final int aX, final int aY, final int aZ, final Vec3b b) {
        return BasicOperators.div(res, b, aX, aY, aZ);
    }

    public static Vec3d div_(final double a, final Vec3d b) {
        return BasicOperators.div(new Vec3d(), b, a, a, a);
    }

    public static Vec3d div(final double a, final Vec3d b) {
        return BasicOperators.div(b, b, a, a, a);
    }

    public static Vec3d div(final Vec3d res, final double a, final Vec3d b) {
        return BasicOperators.div(res, b, a, a, a);
    }

    public static Vec3d div(final Vec3d res, final double aX, final double aY, final double aZ, final Vec3d b) {
        return BasicOperators.div(res, b, aX, aY, aZ);
    }

    public static Vec3i div_(final int a, final Vec3i b) {
        return BasicOperators.div(new Vec3i(), b, a, a, a);
    }

    public static Vec3i div(final int a, final Vec3i b) {
        return BasicOperators.div(b, b, a, a, a);
    }

    public static Vec3i div(final Vec3i res, final int a, final Vec3i b) {
        return BasicOperators.div(res, b, a, a, a);
    }

    public static Vec3i div(final Vec3i res, final int aX, final int aY, final int aZ, final Vec3i b) {
        return BasicOperators.div(res, b, aX, aY, aZ);
    }

    public static Vec3l div_(final long a, final Vec3l b) {
        return BasicOperators.div(new Vec3l(), b, a, a, a);
    }

    public static Vec3l div(final long a, final Vec3l b) {
        return BasicOperators.div(b, b, a, a, a);
    }

    public static Vec3l div(final Vec3l res, final long a, final Vec3l b) {
        return BasicOperators.div(res, b, a, a, a);
    }

    public static Vec3l div(final Vec3l res, final long aX, final long aY, final long aZ, final Vec3l b) {
        return BasicOperators.div(res, b, aX, aY, aZ);
    }

    public static Vec3s div_(final short a, final Vec3s b) {
        return BasicOperators.div(new Vec3s(), b, a, a, a);
    }

    public static Vec3s div(final short a, final Vec3s b) {
        return BasicOperators.div(b, b, a, a, a);
    }

    public static Vec3s div(final Vec3s res, final short a, final Vec3s b) {
        return BasicOperators.div(res, b, a, a, a);
    }

    public static Vec3s div(final Vec3s res, final short aX, final short aY, final short aZ, final Vec3s b) {
        return BasicOperators.div(res, b, aX, aY, aZ);
    }

    public static Vec3ub div_(final UByte a, final Vec3ub b) {
        return div(new Vec3ub(), a.value & 0xff, a.value & 0xff, a.value & 0xff, b);
    }

    public static Vec3ub div_(final byte a, final Vec3ub b) {
        return div(new Vec3ub(), a & 0xff, a & 0xff, a & 0xff, b);
    }

    public static Vec3ub div_(final int a, final Vec3ub b) {
        return div(new Vec3ub(), a, a, a, b);
    }

    public static Vec3ub div(final UByte a, final Vec3ub b) {
        return div(b, a.value & 0xff, a.value & 0xff, a.value & 0xff, b);
    }

    public static Vec3ub div(final byte a, final Vec3ub b) {
        return div(b, a & 0xff, a & 0xff, a & 0xff, b);
    }

    public static Vec3ub div(final int a, final Vec3ub b) {
        return div(b, a, a, a, b);
    }

    public static Vec3ub div(final Vec3ub res, final UByte a, final Vec3ub b) {
        return div(res, a.value & 0xff, a.value & 0xff, a.value & 0xff, b);
    }

    public static Vec3ub div(final Vec3ub res, final byte a, final Vec3ub b) {
        return div(res, a & 0xff, a & 0xff, a & 0xff, b);
    }

    public static Vec3ub div(final Vec3ub res, final int a, final Vec3ub b) {
        return div(res, a, a, a, b);
    }

    public static Vec3ub div(final Vec3ub res, final UByte aX, final UByte aY, final UByte aZ, final Vec3ub b) {
        return div(res, aX.value & 0xff, aY.value & 0xff, aZ.value & 0xff, b);
    }

    public static Vec3ub div(final Vec3ub res, final byte aX, final byte aY, final byte aZ, final Vec3ub b) {
        return div(res, aX & 0xff, aY & 0xff, aZ & 0xff, b);
    }

    public static Vec3ub div(final Vec3ub res, final int aX, final int aY, final int aZ, final Vec3ub b) {
        res.x.value = (byte) Integer.divideUnsigned(aX, b.x.value & 0xff);
        res.y.value = (byte) Integer.divideUnsigned(aY, b.y.value & 0xff);
        res.z.value = (byte) Integer.divideUnsigned(aZ, b.z.value & 0xff);
        return res;
    }

    public static Vec3ui div_(final UInt a, final Vec3ui b) {
        return BasicOperators.div(new Vec3ui(), b, a.value, a.value, a.value);
    }

    public static Vec3ui div_(final int a, final Vec3ui b) {
        return BasicOperators.div(new Vec3ui(), b, a, a, a);
    }

    public static Vec3ui div(final UInt a, final Vec3ui b) {
        return BasicOperators.div(b, b, a.value, a.value, a.value);
    }

    public static Vec3ui div(final int a, final Vec3ui b) {
        return BasicOperators.div(b, b, a, a, a);
    }

    public static Vec3ui div(final Vec3ui res, final UInt a, final Vec3ui b) {
        return BasicOperators.div(res, b, a.value, a.value, a.value);
    }

    public static Vec3ui div(final Vec3ui res, final int a, final Vec3ui b) {
        return BasicOperators.div(res, b, a, a, a);
    }

    public static Vec3ui div(final Vec3ui res, final UInt aX, final UInt aY, final UInt aZ, final Vec3ui b) {
        return BasicOperators.div(res, b, aX.value, aY.value, aZ.value);
    }

    public static Vec3ui div(final Vec3ui res, final int aX, final int aY, final int aZ, final Vec3ui b) {
        return BasicOperators.div(res, b, aX, aY, aZ);
    }

    public static Vec3ul div_(final ULong a, final Vec3ul b) {
        return BasicOperators.div(new Vec3ul(), b, a.value, a.value, a.value);
    }

    public static Vec3ul div_(final long a, final Vec3ul b) {
        return BasicOperators.div(new Vec3ul(), b, a, a, a);
    }

    public static Vec3ul div(final ULong a, final Vec3ul b) {
        return BasicOperators.div(b, b, a.value, a.value, a.value);
    }

    public static Vec3ul div(final long a, final Vec3ul b) {
        return BasicOperators.div(b, b, a, a, a);
    }

    public static Vec3ul div(final Vec3ul res, final ULong a, final Vec3ul b) {
        return BasicOperators.div(res, b, a.value, a.value, a.value);
    }

    public static Vec3ul div(final Vec3ul res, final long a, final Vec3ul b) {
        return BasicOperators.div(res, b, a, a, a);
    }

    public static Vec3ul div(final Vec3ul res, final ULong aX, final ULong aY, final ULong aZ, final Vec3ul b) {
        return BasicOperators.div(res, b, aX.value, aY.value, aZ.value);
    }

    public static Vec3ul div(final Vec3ul res, final long aX, final long aY, final long aZ, final Vec3ul b) {
        return BasicOperators.div(res, b, aX, aY, aZ);
    }

    public static Vec3us div_(final UShort a, final Vec3us b) {
        return div(new Vec3us(), a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, b);
    }

    public static Vec3us div_(final short a, final Vec3us b) {
        return div(new Vec3us(), a & 0xffff, a & 0xffff, a & 0xffff, b);
    }

    public static Vec3us div_(final int a, final Vec3us b) {
        return div(new Vec3us(), a, a, a, b);
    }

    public static Vec3us div(final UShort a, final Vec3us b) {
        return div(b, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, b);
    }

    public static Vec3us div(final short a, final Vec3us b) {
        return div(b, a & 0xffff, a & 0xffff, a & 0xffff, b);
    }

    public static Vec3us div(final int a, final Vec3us b) {
        return div(b, a, a, a, b);
    }

    public static Vec3us div(final Vec3us res, final UShort a, final Vec3us b) {
        return div(res, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, b);
    }

    public static Vec3us div(final Vec3us res, final short a, final Vec3us b) {
        return div(res, a & 0xffff, a & 0xffff, a & 0xffff, b);
    }

    public static Vec3us div(final Vec3us res, final int a, final Vec3us b) {
        return div(res, a, a, a, b);
    }

    public static Vec3us div(final Vec3us res, final UShort aX, final UShort aY, final UShort aZ, final Vec3us b) {
        return div(res, aX.value & 0xffff, aY.value & 0xffff, aZ.value & 0xffff, b);
    }

    public static Vec3us div(final Vec3us res, final short aX, final short aY, final short aZ, final Vec3us b) {
        return div(res, aX & 0xffff, aY & 0xffff, aZ & 0xffff, b);
    }

    public static Vec3us div(final Vec3us res, final int aX, final int aY, final int aZ, final Vec3us b) {
        res.x.value = (short) Integer.divideUnsigned(aX, b.x.value & 0xffff);
        res.y.value = (short) Integer.divideUnsigned(aY, b.y.value & 0xffff);
        res.z.value = (short) Integer.divideUnsigned(aZ, b.z.value & 0xffff);
        return res;
    }

    public static Vec4 div_(final float a, final Vec4 b) {
        return BasicOperators.div(new Vec4(), b, a, a, a, a);
    }

    public static Vec4 div(final float a, final Vec4 b) {
        return BasicOperators.div(b, b, a, a, a, a);
    }

    public static Vec4 div(final Vec4 res, final float a, final Vec4 b) {
        return BasicOperators.div(res, b, a, a, a, a);
    }

    public static Vec4 div(final Vec4 res, final float aX, final float aY, final float aZ, final float aW, final Vec4 b) {
        return BasicOperators.div(res, b, aX, aY, aZ, aW);
    }

    public static Vec4b div_(final byte a, final Vec4b b) {
        return BasicOperators.div(new Vec4b(), b, (int) a, a, a, a);
    }

    public static Vec4b div_(final int a, final Vec4b b) {
        return BasicOperators.div(new Vec4b(), b, a, a, a, a);
    }

    public static Vec4b div(final byte a, final Vec4b b) {
        return BasicOperators.div(b, b, (int) a, a, a, a);
    }

    public static Vec4b div(final int a, final Vec4b b) {
        return BasicOperators.div(b, b, a, a, a, a);
    }

    public static Vec4b div(final Vec4b res, final byte a, final Vec4b b) {
        return BasicOperators.div(res, b, (int) a, a, a, a);
    }

    public static Vec4b div(final Vec4b res, final int a, final Vec4b b) {
        return BasicOperators.div(res, b, a, a, a, a);
    }

    public static Vec4b div(final Vec4b res, final byte aX, final byte aY, final byte aZ, final byte aW, final Vec4b b) {
        return BasicOperators.div(res, b, (int) aX, aY, aZ, aW);
    }

    public static Vec4b div(final Vec4b res, final int aX, final int aY, final int aZ, final int aW, final Vec4b b) {
        return BasicOperators.div(res, b, aX, aY, aZ, aW);
    }

    public static Vec4d div_(final double a, final Vec4d b) {
        return BasicOperators.div(new Vec4d(), b, a, a, a, a);
    }

    public static Vec4d div(final double a, final Vec4d b) {
        return BasicOperators.div(b, b, a, a, a, a);
    }

    public static Vec4d div(final Vec4d res, final double a, final Vec4d b) {
        return BasicOperators.div(res, b, a, a, a, a);
    }

    public static Vec4d div(final Vec4d res, final double aX, final double aY, final double aZ, final double aW, final Vec4d b) {
        return BasicOperators.div(res, b, aX, aY, aZ, aW);
    }

    public static Vec4i div_(final int a, final Vec4i b) {
        return BasicOperators.div(new Vec4i(), b, a, a, a, a);
    }

    public static Vec4i div(final int a, final Vec4i b) {
        return BasicOperators.div(b, b, a, a, a, a);
    }

    public static Vec4i div(final Vec4i res, final int a, final Vec4i b) {
        return BasicOperators.div(res, b, a, a, a, a);
    }

    public static Vec4i div(final Vec4i res, final int aX, final int aY, final int aZ, final int aW, final Vec4i b) {
        return BasicOperators.div(res, b, aX, aY, aZ, aW);
    }

    public static Vec4l div_(final long a, final Vec4l b) {
        return BasicOperators.div(new Vec4l(), b, a, a, a, a);
    }

    public static Vec4l div(final long a, final Vec4l b) {
        return BasicOperators.div(b, b, a, a, a, a);
    }

    public static Vec4l div(final Vec4l res, final long a, final Vec4l b) {
        return BasicOperators.div(res, b, a, a, a, a);
    }

    public static Vec4l div(final Vec4l res, final long aX, final long aY, final long aZ, final long aW, final Vec4l b) {
        return BasicOperators.div(res, b, aX, aY, aZ, aW);
    }

    public static Vec4s div_(final short a, final Vec4s b) {
        return BasicOperators.div(new Vec4s(), b, a, a, a, a);
    }

    public static Vec4s div(final short a, final Vec4s b) {
        return BasicOperators.div(b, b, a, a, a, a);
    }

    public static Vec4s div(final Vec4s res, final short a, final Vec4s b) {
        return BasicOperators.div(res, b, a, a, a, a);
    }

    public static Vec4s div(final Vec4s res, final short aX, final short aY, final short aZ, final short aW, final Vec4s b) {
        return BasicOperators.div(res, b, aX, aY, aZ, aW);
    }

    public static Vec4ub div_(final UByte a, final Vec4ub b) {
        return div(new Vec4ub(), b, a.value & 0xff, a.value & 0xff, a.value & 0xff, a.value & 0xff);
    }

    public static Vec4ub div_(final byte a, final Vec4ub b) {
        return div(new Vec4ub(), b, a & 0xff, a & 0xff, a & 0xff, a & 0xff);
    }

    public static Vec4ub div_(final int a, final Vec4ub b) {
        return div(new Vec4ub(), b, a, a, a, a);
    }

    public static Vec4ub div(final UByte a, final Vec4ub b) {
        return div(b, b, a.value & 0xff, a.value & 0xff, a.value & 0xff, a.value & 0xff);
    }

    public static Vec4ub div(final byte a, final Vec4ub b) {
        return div(b, b, a & 0xff, a & 0xff, a & 0xff, a & 0xff);
    }

    public static Vec4ub div(final int a, final Vec4ub b) {
        return div(b, b, a, a, a, a);
    }

    public static Vec4ub div(final Vec4ub res, final UByte a, final Vec4ub b) {
        return div(res, b, a.value & 0xff, a.value & 0xff, a.value & 0xff, a.value & 0xff);
    }

    public static Vec4ub div(final Vec4ub res, final byte a, final Vec4ub b) {
        return div(res, b, a & 0xff, a & 0xff, a & 0xff, a & 0xff);
    }

    public static Vec4ub div(final Vec4ub res, final int a, final Vec4ub b) {
        return div(res, b, a, a, a, a);
    }

    public static Vec4ub div(final Vec4ub res, final UByte aX, final UByte aY, final UByte aZ, final UByte aW, final Vec4ub b) {
        return div(res, b, aX.value & 0xff, aY.value & 0xff, aZ.value & 0xff, aW.value & 0xff);
    }

    public static Vec4ub div(final Vec4ub res, final byte aX, final byte aY, final byte aZ, final byte aW, final Vec4ub b) {
        return div(res, b, aX, aY, aZ, aW);
    }

    public static Vec4ub div(final Vec4ub res, final int aX, final int aY, final int aZ, final int aW, final Vec4ub b) {
        res.x.value = (byte) Integer.divideUnsigned(aX, b.x.value & 0xff);
        res.y.value = (byte) Integer.divideUnsigned(aY, b.y.value & 0xff);
        res.z.value = (byte) Integer.divideUnsigned(aZ, b.z.value & 0xff);
        res.w.value = (byte) Integer.divideUnsigned(aW, b.w.value & 0xff);
        return res;
    }

    public static Vec4ui div_(final UInt a, final Vec4ui b) {
        return BasicOperators.div(new Vec4ui(), b, a.value, a.value, a.value, a.value);
    }

    public static Vec4ui div_(final int a, final Vec4ui b) {
        return BasicOperators.div(new Vec4ui(), b, a, a, a, a);
    }

    public static Vec4ui div(final UInt a, final Vec4ui b) {
        return BasicOperators.div(b, b, a.value, a.value, a.value, a.value);
    }

    public static Vec4ui div(final int a, final Vec4ui b) {
        return BasicOperators.div(b, b, a, a, a, a);
    }

    public static Vec4ui div(final Vec4ui res, final UInt a, final Vec4ui b) {
        return BasicOperators.div(res, b, a.value, a.value, a.value, a.value);
    }

    public static Vec4ui div(final Vec4ui res, final int a, final Vec4ui b) {
        return BasicOperators.div(res, b, a, a, a, a);
    }

    public static Vec4ui div(final Vec4ui res, final UInt aX, final UInt aY, final UInt aZ, final UInt aW, final Vec4ui b) {
        return BasicOperators.div(res, b, aX.value, aY.value, aZ.value, aW.value);
    }

    public static Vec4ui div(final Vec4ui res, final int aX, final int aY, final int aZ, final int aW, final Vec4ui b) {
        return BasicOperators.div(res, b, aX, aY, aZ, aW);
    }

    public static Vec4ul div_(final ULong a, final Vec4ul b) {
        return BasicOperators.div(new Vec4ul(), b, a.value, a.value, a.value, a.value);
    }

    public static Vec4ul div_(final long a, final Vec4ul b) {
        return BasicOperators.div(new Vec4ul(), b, a, a, a, a);
    }

    public static Vec4ul div(final ULong a, final Vec4ul b) {
        return BasicOperators.div(b, b, a.value, a.value, a.value, a.value);
    }

    public static Vec4ul div(final long a, final Vec4ul b) {
        return BasicOperators.div(b, b, a, a, a, a);
    }

    public static Vec4ul div(final Vec4ul res, final ULong a, final Vec4ul b) {
        return BasicOperators.div(res, b, a.value, a.value, a.value, a.value);
    }

    public static Vec4ul div(final Vec4ul res, final long a, final Vec4ul b) {
        return BasicOperators.div(res, b, a, a, a, a);
    }

    public static Vec4ul div(final Vec4ul res, final ULong aX, final ULong aY, final ULong aZ, final ULong aW, final Vec4ul b) {
        return BasicOperators.div(res, b, aX.value, aY.value, aZ.value, aW.value);
    }

    public static Vec4ul div(final Vec4ul res, final long aX, final long aY, final long aZ, final long aW, final Vec4ul b) {
        return BasicOperators.div(res, b, aX, aY, aZ, aW);
    }

    public static Vec4us div_(final UShort a, final Vec4us b) {
        return div(new Vec4us(), a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, b);
    }

    public static Vec4us div_(final short a, final Vec4us b) {
        return div(new Vec4us(), a & 0xffff, a & 0xffff, a & 0xffff, a & 0xffff, b);
    }

    public static Vec4us div_(final int a, final Vec4us b) {
        return div(new Vec4us(), a, a, a, a, b);
    }

    public static Vec4us div(final UShort a, final Vec4us b) {
        return div(b, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, b);
    }

    public static Vec4us div(final short a, final Vec4us b) {
        return div(b, a & 0xffff, a & 0xffff, a & 0xffff, a & 0xffff, b);
    }

    public static Vec4us div(final int a, final Vec4us b) {
        return div(b, a, a, a, a, b);
    }

    public static Vec4us div(final Vec4us res, final UShort a, final Vec4us b) {
        return div(res, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, a.value & 0xffff, b);
    }

    public static Vec4us div(final Vec4us res, final short a, final Vec4us b) {
        return div(res, a & 0xffff, a & 0xffff, a & 0xffff, a & 0xffff, b);
    }

    public static Vec4us div(final Vec4us res, final int a, final Vec4us b) {
        return div(res, a, a, a, a, b);
    }

    public static Vec4us div(final Vec4us res, final UShort aX, final UShort aY, final UShort aZ, final UShort aW, final Vec4us b) {
        return div(res, aX.value & 0xffff, aY.value & 0xffff, aZ.value & 0xffff, aW.value & 0xffff, b);
    }

    public static Vec4us div(final Vec4us res, final short aX, final short aY, final short aZ, final short aW, final Vec4us b) {
        return div(res, aX & 0xffff, aY & 0xffff, aZ & 0xffff, aW & 0xffff, b);
    }

    public static Vec4us div(final Vec4us res, final int aX, final int aY, final int aZ, final int aW, final Vec4us b) {
        res.x.value = (short) Integer.divideUnsigned(aX, b.x.value & 0xffff);
        res.y.value = (short) Integer.divideUnsigned(aY, b.y.value & 0xffff);
        res.z.value = (short) Integer.divideUnsigned(aZ, b.z.value & 0xffff);
        res.w.value = (short) Integer.divideUnsigned(aW, b.w.value & 0xffff);
        return res;
    }
}
