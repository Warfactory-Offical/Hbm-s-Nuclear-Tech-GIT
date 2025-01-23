/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm;

import glmath.glm.vec._2.b.Vec2b;
import glmath.glm.vec._2.i.Vec2i;
import glmath.glm.vec._2.l.Vec2l;
import glmath.glm.vec._2.s.Vec2s;
import glmath.glm.vec._2.ub.Vec2ub;
import glmath.glm.vec._2.ui.Vec2ui;
import glmath.glm.vec._2.ul.Vec2ul;
import glmath.glm.vec._2.us.Vec2us;
import glmath.glm.vec._3.b.Vec3b;
import glmath.glm.vec._3.i.Vec3i;
import glmath.glm.vec._3.l.Vec3l;
import glmath.glm.vec._3.s.Vec3s;
import glmath.glm.vec._3.ub.Vec3ub;
import glmath.glm.vec._3.ui.Vec3ui;
import glmath.glm.vec._3.ul.Vec3ul;
import glmath.glm.vec._3.us.Vec3us;
import glmath.glm.vec._4.b.Vec4b;
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
abstract class SpecialOperators extends BooleansOperators {

    public static Vec2b mod_(final Vec2b a, final byte b) {
        return mod(new Vec2b(), a, (int) b, b);
    }

    public static Vec2b mod_(final Vec2b a, final int b) {
        return mod(new Vec2b(), a, b, b);
    }

    public static Vec2b mod_(final Vec2b a, final Vec2b b) {
        return mod(new Vec2b(), a, (int) b.x, b.y);
    }

    public static Vec2b mod(final Vec2b a, final byte b) {
        return mod(a, a, (int) b, b);
    }

    public static Vec2b mod(final Vec2b a, final int b) {
        return mod(a, a, b, b);
    }

    public static Vec2b mod(final Vec2b a, final Vec2b b) {
        return mod(a, a, (int) b.x, b.y);
    }

    public static Vec2b mod(final Vec2b res, final Vec2b a, final byte b) {
        return mod(res, a, (int) b, b);
    }

    public static Vec2b mod(final Vec2b res, final Vec2b a, final int b) {
        return mod(res, a, b, b);
    }

    public static Vec2b mod(final Vec2b res, final Vec2b a, final Vec2b b) {
        return mod(res, a, (int) b.x, b.y);
    }

    public static Vec2b mod(final Vec2b res, final Vec2b a, final byte bX, final byte bY) {
        return mod(res, a, (int) bX, bY);
    }

    public static Vec2b mod(final Vec2b res, final Vec2b a, final int bX, final int bY) {
        res.x = (byte) (a.x % bX);
        res.y = (byte) (a.y % bY);
        return res;
    }

    public static Vec2i mod_(final Vec2i a, final int b) {
        return mod(new Vec2i(), a, b, b);
    }

    public static Vec2i mod_(final Vec2i a, final Vec2i b) {
        return mod(new Vec2i(), a, b.x, b.y);
    }

    public static Vec2i mod(final Vec2i a, final int b) {
        return mod(a, a, b, b);
    }

    public static Vec2i mod(final Vec2i a, final Vec2i b) {
        return mod(a, a, b.x, b.y);
    }

    public static Vec2i mod(final Vec2i res, final Vec2i a, final int b) {
        return mod(res, a, b, b);
    }

    public static Vec2i mod(final Vec2i res, final Vec2i a, final Vec2i b) {
        return mod(res, a, b.x, b.y);
    }

    public static Vec2i mod(final Vec2i res, final Vec2i a, final int bX, final int bY) {
        res.x = a.x % bX;
        res.y = a.y % bY;
        return res;
    }

    public static Vec2l mod_(final Vec2l a, final long b) {
        return mod(new Vec2l(), a, b, b);
    }

    public static Vec2l mod_(final Vec2l a, final Vec2l b) {
        return mod(new Vec2l(), a, b.x, b.y);
    }

    public static Vec2l mod(final Vec2l a, final long b) {
        return mod(a, a, b, b);
    }

    public static Vec2l mod(final Vec2l a, final Vec2l b) {
        return mod(a, a, b.x, b.y);
    }

    public static Vec2l mod(final Vec2l res, final Vec2l a, final long b) {
        return mod(res, a, b, b);
    }

    public static Vec2l mod(final Vec2l res, final Vec2l a, final Vec2l b) {
        return mod(res, a, b.x, b.y);
    }

    public static Vec2l mod(final Vec2l res, final Vec2l a, final long bX, final long bY) {
        res.x = a.x % bX;
        res.y = a.y % bY;
        return res;
    }

    public static Vec2s mod_(final Vec2s a, final short b) {
        return mod(new Vec2s(), a, (int) b, b);
    }

    public static Vec2s mod_(final Vec2s a, final int b) {
        return mod(new Vec2s(), a, b, b);
    }

    public static Vec2s mod_(final Vec2s a, final Vec2s b) {
        return mod(new Vec2s(), a, (int) b.x, b.y);
    }

    public static Vec2s mod(final Vec2s a, final short b) {
        return mod(a, a, (int) b, b);
    }

    public static Vec2s mod(final Vec2s a, final int b) {
        return mod(a, a, b, b);
    }

    public static Vec2s mod(final Vec2s a, final Vec2s b) {
        return mod(a, a, (int) b.x, b.y);
    }

    public static Vec2s mod(final Vec2s res, final Vec2s a, final short b) {
        return mod(res, a, (int) b, b);
    }

    public static Vec2s mod(final Vec2s res, final Vec2s a, final int b) {
        return mod(res, a, b, b);
    }

    public static Vec2s mod(final Vec2s res, final Vec2s a, final Vec2s b) {
        return mod(res, a, (int) b.x, b.y);
    }

    public static Vec2s mod(final Vec2s res, final Vec2s a, final short bX, final short bY) {
        return mod(res, a, (int) bX, bY);
    }

    public static Vec2s mod(final Vec2s res, final Vec2s a, final int bX, final int bY) {
        res.x = (short) (a.x % bX);
        res.y = (short) (a.y % bY);
        return res;
    }

    public static Vec2ub mod_(final Vec2ub a, final UByte b) {
        return mod(new Vec2ub(), a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub mod_(final Vec2ub a, final byte b) {
        return mod(new Vec2ub(), a, b & 0xff, b & 0xff);
    }

    public static Vec2ub mod_(final Vec2ub a, final int b) {
        return mod(new Vec2ub(), a, b, b);
    }

    public static Vec2ub mod_(final Vec2ub a, final Vec2ub b) {
        return mod(new Vec2ub(), a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub mod(final Vec2ub a, final UByte b) {
        return mod(a, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub mod(final Vec2ub a, final byte b) {
        return mod(a, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub mod(final Vec2ub a, final int b) {
        return mod(a, a, b, b);
    }

    public static Vec2ub mod(final Vec2ub a, final Vec2ub b) {
        return mod(a, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub mod(final Vec2ub res, final Vec2ub a, final UByte b) {
        return mod(res, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub mod(final Vec2ub res, final Vec2ub a, final byte b) {
        return mod(res, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub mod(final Vec2ub res, final Vec2ub a, final int b) {
        return mod(res, a, b, b);
    }

    public static Vec2ub mod(final Vec2ub res, final Vec2ub a, final Vec2ub b) {
        return mod(res, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub mod(final Vec2ub res, final Vec2ub a, final UByte bX, final UByte bY) {
        return mod(res, a, bX.value & 0xff, bY.value & 0xff);
    }

    public static Vec2ub mod(final Vec2ub res, final Vec2ub a, final byte bX, final byte bY) {
        return mod(res, a, bX & 0xff, bY & 0xff);
    }

    public static Vec2ub mod(final Vec2ub res, final Vec2ub a, final int bX, final int bY) {
        res.x.value = (byte) (Integer.remainderUnsigned(a.x.value & 0xff, bX));
        res.y.value = (byte) (Integer.remainderUnsigned(a.y.value & 0xff, bY));
        return res;
    }

    public static Vec2ui mod_(final Vec2ui a, final UInt b) {
        return mod(new Vec2ui(), a, b.value, b.value);
    }

    public static Vec2ui mod_(final Vec2ui a, final int b) {
        return mod(new Vec2ui(), a, b, b);
    }

    public static Vec2ui mod_(final Vec2ui a, final Vec2ui b) {
        return mod(new Vec2ui(), a, b.x.value, b.y.value);
    }

    public static Vec2ui mod(final Vec2ui a, final UInt b) {
        return mod(a, a, b.value, b.value);
    }

    public static Vec2ui mod(final Vec2ui a, final int b) {
        return mod(a, a, b, b);
    }

    public static Vec2ui mod(final Vec2ui a, final Vec2ui b) {
        return mod(a, a, b.x.value, b.y.value);
    }

    public static Vec2ui mod(final Vec2ui res, final Vec2ui a, final UInt b) {
        return mod(res, a, b.value, b.value);
    }

    public static Vec2ui mod(final Vec2ui res, final Vec2ui a, final int b) {
        return mod(res, a, b, b);
    }

    public static Vec2ui mod(final Vec2ui res, final Vec2ui a, final Vec2ui b) {
        return mod(res, a, b.x.value, b.y.value);
    }

    public static Vec2ui mod(final Vec2ui res, final Vec2ui a, final UInt bX, final UInt bY) {
        return mod(res, a, bX.value, bY.value);
    }

    public static Vec2ui mod(final Vec2ui res, final Vec2ui a, final int bX, final int bY) {
        res.x.value = Integer.remainderUnsigned(a.x.value, bX);
        res.y.value = Integer.remainderUnsigned(a.y.value, bY);
        return res;
    }

    public static Vec2ul mod_(final Vec2ul a, final ULong b) {
        return mod(new Vec2ul(), a, b.value, b.value);
    }

    public static Vec2ul mod_(final Vec2ul a, final long b) {
        return mod(new Vec2ul(), a, b, b);
    }

    public static Vec2ul mod_(final Vec2ul a, final Vec2ul b) {
        return mod(new Vec2ul(), a, b.x.value, b.y.value);
    }

    public static Vec2ul mod(final Vec2ul a, final ULong b) {
        return mod(a, a, b.value, b.value);
    }

    public static Vec2ul mod(final Vec2ul a, final long b) {
        return mod(a, a, b, b);
    }

    public static Vec2ul mod(final Vec2ul a, final Vec2ul b) {
        return mod(a, a, b.x.value, b.y.value);
    }

    public static Vec2ul mod(final Vec2ul res, final Vec2ul a, final ULong b) {
        return mod(res, a, b.value, b.value);
    }

    public static Vec2ul mod(final Vec2ul res, final Vec2ul a, final long b) {
        return mod(res, a, b, b);
    }

    public static Vec2ul mod(final Vec2ul res, final Vec2ul a, final Vec2ul b) {
        return mod(res, a, b.x.value, b.y.value);
    }

    public static Vec2ul mod(final Vec2ul res, final Vec2ul a, final ULong bX, final ULong bY) {
        return mod(res, a, bX.value, bY.value);
    }

    public static Vec2ul mod(final Vec2ul res, final Vec2ul a, final long bX, final long bY) {
        res.x.value = Long.remainderUnsigned(a.x.value, bX);
        res.y.value = Long.remainderUnsigned(a.y.value, bY);
        return res;
    }

    public static Vec2us mod_(final Vec2us a, final UShort b) {
        return mod(new Vec2us(), a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us mod_(final Vec2us a, final short b) {
        return mod(new Vec2us(), a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us mod_(final Vec2us a, final int b) {
        return mod(new Vec2us(), a, b, b);
    }

    public static Vec2us mod_(final Vec2us a, final Vec2us b) {
        return mod(new Vec2us(), a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us mod(final Vec2us a, final UShort b) {
        return mod(a, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us mod(final Vec2us a, final short b) {
        return mod(a, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us mod(final Vec2us a, final int b) {
        return mod(a, a, b, b);
    }

    public static Vec2us mod(final Vec2us a, final Vec2us b) {
        return mod(a, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us mod(final Vec2us res, final Vec2us a, final UShort b) {
        return mod(res, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us mod(final Vec2us res, final Vec2us a, final short b) {
        return mod(res, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us mod(final Vec2us res, final Vec2us a, final int b) {
        return mod(res, a, b, b);
    }

    public static Vec2us mod(final Vec2us res, final Vec2us a, final Vec2us b) {
        return mod(res, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us mod(final Vec2us res, final Vec2us a, final UShort bX, final UShort bY) {
        return mod(res, a, bX.value & 0xffff, bY.value & 0xffff);
    }

    public static Vec2us mod(final Vec2us res, final Vec2us a, final short bX, final short bY) {
        return mod(res, a, bX & 0xffff, bY & 0xffff);
    }

    public static Vec2us mod(final Vec2us res, final Vec2us a, final int bX, final int bY) {
        res.x.value = (short) (Integer.remainderUnsigned(a.x.value & 0xffff, bX));
        res.y.value = (short) (Integer.remainderUnsigned(a.y.value & 0xffff, bY));
        return res;
    }

    public static Vec3b mod_(final Vec3b a, final byte b) {
        return mod(new Vec3b(), a, b, b, b);
    }

    public static Vec3b mod_(final Vec3b a, final int b) {
        return mod(new Vec3b(), a, b, b, b);
    }

    public static Vec3b mod_(final Vec3b a, final Vec3b b) {
        return mod(new Vec3b(), a, b.x, b.y, b.z);
    }

    public static Vec3b mod(final Vec3b a, final byte b) {
        return mod(a, a, b, b, b);
    }

    public static Vec3b mod(final Vec3b a, final int b) {
        return mod(a, a, b, b, b);
    }

    public static Vec3b mod(final Vec3b a, final Vec3b b) {
        return mod(a, a, b.x, b.y, b.z);
    }

    public static Vec3b mod(final Vec3b res, final Vec3b a, final byte b) {
        return mod(res, a, b, b, b);
    }

    public static Vec3b mod(final Vec3b res, final Vec3b a, final int b) {
        return mod(res, a, b, b, b);
    }

    public static Vec3b mod(final Vec3b res, final Vec3b a, final Vec3b b) {
        return mod(res, a, b.x, b.y, b.z);
    }

    public static Vec3b mod(final Vec3b res, final Vec3b a, final byte bX, final byte bY, final byte bZ) {
        return mod(res, a, (int) bX, bY, bZ);
    }

    public static Vec3b mod(final Vec3b res, final Vec3b a, final int bX, final int bY, final int bZ) {
        res.x = (byte) (a.x % bX);
        res.y = (byte) (a.y % bY);
        res.z = (byte) (a.z % bZ);
        return res;
    }

    public static Vec3i mod_(final Vec3i a, final int b) {
        return mod(new Vec3i(), a, b, b, b);
    }

    public static Vec3i mod_(final Vec3i a, final Vec3i b) {
        return mod(new Vec3i(), a, b.x, b.y, b.z);
    }

    public static Vec3i mod(final Vec3i a, final int b) {
        return mod(a, a, b, b, b);
    }

    public static Vec3i mod(final Vec3i a, final Vec3i b) {
        return mod(a, a, b.x, b.y, b.z);
    }

    public static Vec3i mod(final Vec3i res, final Vec3i a, final int b) {
        return mod(res, a, b, b, b);
    }

    public static Vec3i mod(final Vec3i res, final Vec3i a, final Vec3i b) {
        return mod(res, a, b.x, b.y, b.z);
    }

    public static Vec3i mod(final Vec3i res, final Vec3i a, final int bX, final int bY, final int bZ) {
        res.x = a.x % bX;
        res.y = a.y % bY;
        res.z = a.z % bZ;
        return res;
    }

    public static Vec3l mod_(final Vec3l a, final long b) {
        return mod(new Vec3l(), a, b, b, b);
    }

    public static Vec3l mod_(final Vec3l a, final Vec3l b) {
        return mod(new Vec3l(), a, b.x, b.y, b.z);
    }

    public static Vec3l mod(final Vec3l a, final long b) {
        return mod(a, a, b, b, b);
    }

    public static Vec3l mod(final Vec3l a, final Vec3l b) {
        return mod(a, a, b.x, b.y, b.z);
    }

    public static Vec3l mod(final Vec3l res, final Vec3l a, final long b) {
        return mod(res, a, b, b, b);
    }

    public static Vec3l mod(final Vec3l res, final Vec3l a, final Vec3l b) {
        return mod(res, a, b.x, b.y, b.z);
    }

    public static Vec3l mod(final Vec3l res, final Vec3l a, final long bX, final long bY, final long bZ) {
        res.x = a.x % bX;
        res.y = a.y % bY;
        res.z = a.z % bZ;
        return res;
    }

    public static Vec3s mod_(final Vec3s a, final short b) {
        return mod(new Vec3s(), a, b, b, b);
    }

    public static Vec3s mod_(final Vec3s a, final int b) {
        return mod(new Vec3s(), a, b, b, b);
    }

    public static Vec3s mod_(final Vec3s a, final Vec3s b) {
        return mod(new Vec3s(), a, b.x, b.y, b.z);
    }

    public static Vec3s mod(final Vec3s a, final short b) {
        return mod(a, a, b, b, b);
    }

    public static Vec3s mod(final Vec3s a, final int b) {
        return mod(a, a, b, b, b);
    }

    public static Vec3s mod(final Vec3s a, final Vec3s b) {
        return mod(a, a, b.x, b.y, b.z);
    }

    public static Vec3s mod(final Vec3s res, final Vec3s a, final short b) {
        return mod(res, a, b, b, b);
    }

    public static Vec3s mod(final Vec3s res, final Vec3s a, final int b) {
        return mod(res, a, b, b, b);
    }

    public static Vec3s mod(final Vec3s res, final Vec3s a, final Vec3s b) {
        return mod(res, a, b.x, b.y, b.z);
    }

    public static Vec3s mod(final Vec3s res, final Vec3s a, final short bX, final short bY, final short bZ) {
        return mod(res, a, (int) bX, bY, bZ);
    }

    public static Vec3s mod(final Vec3s res, final Vec3s a, final int bX, final int bY, final int bZ) {
        res.x = (short) (a.x % bX);
        res.y = (short) (a.y % bY);
        res.z = (short) (a.z % bZ);
        return res;
    }

    public static Vec3ub mod_(final Vec3ub a, final UByte b) {
        return mod(new Vec3ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub mod_(final Vec3ub a, final byte b) {
        return mod(new Vec3ub(), a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub mod_(final Vec3ub a, final int b) {
        return mod(new Vec3ub(), a, b, b, b);
    }

    public static Vec3ub mod_(final Vec3ub a, final Vec3ub b) {
        return mod(new Vec3ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub mod(final Vec3ub a, final UByte b) {
        return mod(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub mod(final Vec3ub a, final byte b) {
        return mod(a, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub mod(final Vec3ub a, final int b) {
        return mod(a, a, b, b, b);
    }

    public static Vec3ub mod(final Vec3ub a, final Vec3ub b) {
        return mod(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub mod(final Vec3ub res, final Vec3ub a, final UByte b) {
        return mod(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub mod(final Vec3ub res, final Vec3ub a, final byte b) {
        return mod(res, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub mod(final Vec3ub res, final Vec3ub a, final int b) {
        return mod(res, a, b, b, b);
    }

    public static Vec3ub mod(final Vec3ub res, final Vec3ub a, final Vec3ub b) {
        return mod(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub mod(final Vec3ub res, final Vec3ub a, final UByte bX, final UByte bY, final UByte bZ) {
        return mod(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public static Vec3ub mod(final Vec3ub res, final Vec3ub a, final byte bX, final byte bY, final byte bZ) {
        return mod(res, a, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public static Vec3ub mod(final Vec3ub res, final Vec3ub a, final int bX, final int bY, final int bZ) {
        res.x.value = (byte) (Integer.remainderUnsigned(a.x.value & 0xff, bX));
        res.y.value = (byte) (Integer.remainderUnsigned(a.y.value & 0xff, bY));
        res.z.value = (byte) (Integer.remainderUnsigned(a.z.value & 0xff, bZ));
        return res;
    }

    public static Vec3ui mod_(final Vec3ui a, final UInt b) {
        return mod(new Vec3ui(), a, b.value, b.value, b.value);
    }

    public static Vec3ui mod_(final Vec3ui a, final int b) {
        return mod(new Vec3ui(), a, b, b, b);
    }

    public static Vec3ui mod_(final Vec3ui a, final Vec3ui b) {
        return mod(new Vec3ui(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui mod(final Vec3ui a, final UInt b) {
        return mod(a, a, b.value, b.value, b.value);
    }

    public static Vec3ui mod(final Vec3ui a, final int b) {
        return mod(a, a, b, b, b);
    }

    public static Vec3ui mod(final Vec3ui a, final Vec3ui b) {
        return mod(a, a, b.x.value, b.y.value, b.y.value);
    }

    public static Vec3ui mod(final Vec3ui res, final Vec3ui a, final UInt b) {
        return mod(res, a, b.value, b.value, b.value);
    }

    public static Vec3ui mod(final Vec3ui res, final Vec3ui a, final int b) {
        return mod(res, a, b, b, b);
    }

    public static Vec3ui mod(final Vec3ui res, final Vec3ui a, final Vec3ui b) {
        return mod(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui mod(final Vec3ui res, final Vec3ui a, final UInt bX, final UInt bY, final UInt bZ) {
        return mod(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ui mod(final Vec3ui res, final Vec3ui a, final int bX, final int bY, final int bZ) {
        res.x.value = Integer.remainderUnsigned(a.x.value, bX);
        res.y.value = Integer.remainderUnsigned(a.y.value, bY);
        res.z.value = Integer.remainderUnsigned(a.z.value, bZ);
        return res;
    }

    public static Vec3ul mod_(final Vec3ul a, final ULong b) {
        return mod(new Vec3ul(), a, b.value, b.value, b.value);
    }

    public static Vec3ul mod_(final Vec3ul a, final long b) {
        return mod(new Vec3ul(), a, b, b, b);
    }

    public static Vec3ul mod_(final Vec3ul a, final Vec3ul b) {
        return mod(new Vec3ul(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul mod(final Vec3ul a, final ULong b) {
        return mod(a, a, b.value, b.value, b.value);
    }

    public static Vec3ul mod(final Vec3ul a, final long b) {
        return mod(a, a, b, b, b);
    }

    public static Vec3ul mod(final Vec3ul a, final Vec3ul b) {
        return mod(a, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul mod(final Vec3ul res, final Vec3ul a, final ULong b) {
        return mod(res, a, b.value, b.value, b.value);
    }

    public static Vec3ul mod(final Vec3ul res, final Vec3ul a, final long b) {
        return mod(res, a, b, b, b);
    }

    public static Vec3ul mod(final Vec3ul res, final Vec3ul a, final Vec3ul b) {
        return mod(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul mod(final Vec3ul res, final Vec3ul a, final ULong bX, final ULong bY, final ULong bZ) {
        return mod(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ul mod(final Vec3ul res, final Vec3ul a, final long bX, final long bY, final long bZ) {
        res.x.value = Long.remainderUnsigned(a.x.value, bX);
        res.y.value = Long.remainderUnsigned(a.y.value, bY);
        res.z.value = Long.remainderUnsigned(a.z.value, bZ);
        return res;
    }

    public static Vec3us mod_(final Vec3us a, final UShort b) {
        return mod(new Vec3us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us mod_(final Vec3us a, final short b) {
        return mod(new Vec3us(), a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us mod_(final Vec3us a, final int b) {
        return mod(new Vec3us(), a, b, b, b);
    }

    public static Vec3us mod_(final Vec3us a, final Vec3us b) {
        return mod(new Vec3us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us mod(final Vec3us a, final UShort b) {
        return mod(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us mod(final Vec3us a, final short b) {
        return mod(a, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us mod(final Vec3us a, final int b) {
        return mod(a, a, b, b, b);
    }

    public static Vec3us mod(final Vec3us a, final Vec3us b) {
        return mod(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us mod(final Vec3us res, final Vec3us a, final UShort b) {
        return mod(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us mod(final Vec3us res, final Vec3us a, final short b) {
        return mod(res, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us mod(final Vec3us res, final Vec3us a, final int b) {
        return mod(res, a, b, b, b);
    }

    public static Vec3us mod(final Vec3us res, final Vec3us a, final Vec3us b) {
        return mod(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us mod(final Vec3us res, final Vec3us a, final UShort bX, final UShort bY, final UShort bZ) {
        return mod(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public static Vec3us mod(final Vec3us res, final Vec3us a, final short bX, final short bY, final short bZ) {
        return mod(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public static Vec3us mod(final Vec3us res, final Vec3us a, final int bX, final int bY, final int bZ) {
        res.x.value = (short) (Integer.remainderUnsigned(a.x.value & 0xffff, bX));
        res.y.value = (short) (Integer.remainderUnsigned(a.y.value & 0xffff, bY));
        res.z.value = (short) (Integer.remainderUnsigned(a.z.value & 0xffff, bZ));
        return res;
    }

    public static Vec4b mod_(final Vec4b a, final byte b) {
        return mod(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b mod_(final Vec4b a, final int b) {
        return mod(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b mod_(final Vec4b a, final Vec4b b) {
        return mod(new Vec4b(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b mod(final Vec4b a, final byte b) {
        return mod(a, a, b, b, b, b);
    }

    public static Vec4b mod(final Vec4b a, final int b) {
        return mod(a, a, b, b, b, b);
    }

    public static Vec4b mod(final Vec4b a, final Vec4b b) {
        return mod(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b mod(final Vec4b res, final Vec4b a, final byte b) {
        return mod(res, a, b, b, b, b);
    }

    public static Vec4b mod(final Vec4b res, final Vec4b a, final int b) {
        return mod(res, a, b, b, b, b);
    }

    public static Vec4b mod(final Vec4b res, final Vec4b a, final Vec4b b) {
        return mod(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b mod(final Vec4b res, final Vec4b a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return mod(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4b mod(final Vec4b res, final Vec4b a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (byte) (a.x % bX);
        res.y = (byte) (a.y % bY);
        res.z = (byte) (a.z % bZ);
        res.w = (byte) (a.w % bW);
        return res;
    }

    public static Vec4i mod_(final Vec4i a, final int b) {
        return mod(new Vec4i(), a, b, b, b, b);
    }

    public static Vec4i mod_(final Vec4i a, final Vec4i b) {
        return mod(new Vec4i(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i mod(final Vec4i a, final int b) {
        return mod(a, a, b, b, b, b);
    }

    public static Vec4i mod(final Vec4i a, final Vec4i b) {
        return mod(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i mod(final Vec4i res, final Vec4i a, final int b) {
        return mod(res, a, b, b, b, b);
    }

    public static Vec4i mod(final Vec4i res, final Vec4i a, final Vec4i b) {
        return mod(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i mod(final Vec4i res, final Vec4i a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = a.x % bX;
        res.y = a.y % bY;
        res.z = a.z % bZ;
        res.w = a.w % bW;
        return res;
    }

    public static Vec4l mod_(final Vec4l a, final long b) {
        return mod(new Vec4l(), a, b, b, b, b);
    }

    public static Vec4l mod_(final Vec4l a, final Vec4l b) {
        return mod(new Vec4l(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l mod(final Vec4l a, final long b) {
        return mod(a, a, b, b, b, b);
    }

    public static Vec4l mod(final Vec4l a, final Vec4l b) {
        return mod(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l mod(final Vec4l res, final Vec4l a, final long b) {
        return mod(res, a, b, b, b, b);
    }

    public static Vec4l mod(final Vec4l res, final Vec4l a, final Vec4l b) {
        return mod(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l mod(final Vec4l res, final Vec4l a, final long bX, final long bY, final long bZ, final long bW) {
        res.x = a.x % bX;
        res.y = a.y % bY;
        res.z = a.z % bZ;
        res.w = a.w % bW;
        return res;
    }

    public static Vec4s mod_(final Vec4s a, final short b) {
        return mod(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s mod_(final Vec4s a, final int b) {
        return mod(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s mod_(final Vec4s a, final Vec4s b) {
        return mod(new Vec4s(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s mod(final Vec4s a, final short b) {
        return mod(a, a, b, b, b, b);
    }

    public static Vec4s mod(final Vec4s a, final int b) {
        return mod(a, a, b, b, b, b);
    }

    public static Vec4s mod(final Vec4s a, final Vec4s b) {
        return mod(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s mod(final Vec4s res, final Vec4s a, final short b) {
        return mod(res, a, b, b, b, b);
    }

    public static Vec4s mod(final Vec4s res, final Vec4s a, final int b) {
        return mod(res, a, b, b, b, b);
    }

    public static Vec4s mod(final Vec4s res, final Vec4s a, final Vec4s b) {
        return mod(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s mod(final Vec4s res, final Vec4s a, final short bX, final short bY, final short bZ, final short bW) {
        return mod(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4s mod(final Vec4s res, final Vec4s a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (short) (a.x % bX);
        res.y = (short) (a.y % bY);
        res.z = (short) (a.z % bZ);
        res.w = (short) (a.w % bW);
        return res;
    }

    public static Vec4ub mod_(final Vec4ub a, final UByte b) {
        return mod(new Vec4ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub mod_(final Vec4ub a, final byte b) {
        return mod(new Vec4ub(), a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub mod_(final Vec4ub a, final int b) {
        return mod(new Vec4ub(), a, b, b, b, b);
    }

    public static Vec4ub mod_(final Vec4ub a, final Vec4ub b) {
        return mod(new Vec4ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub mod(final Vec4ub a, final UByte b) {
        return mod(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub mod(final Vec4ub a, final byte b) {
        return mod(a, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub mod(final Vec4ub a, final int b) {
        return mod(a, a, b, b, b, b);
    }

    public static Vec4ub mod(final Vec4ub a, final Vec4ub b) {
        return mod(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub mod(final Vec4ub res, final Vec4ub a, final UByte b) {
        return mod(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub mod(final Vec4ub res, final Vec4ub a, final byte b) {
        return mod(res, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub mod(final Vec4ub res, final Vec4ub a, final int b) {
        return mod(res, a, b, b, b, b);
    }

    public static Vec4ub mod(final Vec4ub res, final Vec4ub a, final Vec4ub b) {
        return mod(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub mod(final Vec4ub res, final Vec4ub a, final UByte bX, final UByte bY, final UByte bZ, final UByte bW) {
        return mod(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bZ.value & 0xff);
    }

    public static Vec4ub mod(final Vec4ub res, final Vec4ub a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return mod(res, a, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public static Vec4ub mod(final Vec4ub res, final Vec4ub a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (byte) (Integer.remainderUnsigned(a.x.value & 0xff, bX));
        res.y.value = (byte) (Integer.remainderUnsigned(a.y.value & 0xff, bY));
        res.z.value = (byte) (Integer.remainderUnsigned(a.z.value & 0xff, bZ));
        res.w.value = (byte) (Integer.remainderUnsigned(a.w.value & 0xff, bW));
        return res;
    }

    public static Vec4ui mod_(final Vec4ui a, final UInt b) {
        return mod(new Vec4ui(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui mod_(final Vec4ui a, final int b) {
        return mod(new Vec4ui(), a, b, b, b, b);
    }

    public static Vec4ui mod_(final Vec4ui a, final Vec4ui b) {
        return mod(new Vec4ui(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui mod(final Vec4ui a, final UInt b) {
        return mod(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui mod(final Vec4ui a, final int b) {
        return mod(a, a, b, b, b, b);
    }

    public static Vec4ui mod(final Vec4ui a, final Vec4ui b) {
        return mod(a, a, b.x.value, b.y.value, b.y.value, b.w.value);
    }

    public static Vec4ui mod(final Vec4ui res, final Vec4ui a, final UInt b) {
        return mod(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui mod(final Vec4ui res, final Vec4ui a, final int b) {
        return mod(res, a, b, b, b, b);
    }

    public static Vec4ui mod(final Vec4ui res, final Vec4ui a, final Vec4ui b) {
        return mod(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui mod(final Vec4ui res, final Vec4ui a, final UInt bX, final UInt bY, final UInt bZ, final UInt bW) {
        return mod(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ui mod(final Vec4ui res, final Vec4ui a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = Integer.remainderUnsigned(a.x.value, bX);
        res.y.value = Integer.remainderUnsigned(a.y.value, bY);
        res.z.value = Integer.remainderUnsigned(a.z.value, bZ);
        res.w.value = Integer.remainderUnsigned(a.w.value, bZ);
        return res;
    }

    public static Vec4ul mod_(final Vec4ul a, final ULong b) {
        return mod(new Vec4ul(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul mod_(final Vec4ul a, final long b) {
        return mod(new Vec4ul(), a, b, b, b, b);
    }

    public static Vec4ul mod_(final Vec4ul a, final Vec4ul b) {
        return mod(new Vec4ul(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul mod(final Vec4ul a, final ULong b) {
        return mod(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul mod(final Vec4ul a, final long b) {
        return mod(a, a, b, b, b, b);
    }

    public static Vec4ul mod(final Vec4ul a, final Vec4ul b) {
        return mod(a, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul mod(final Vec4ul res, final Vec4ul a, final ULong b) {
        return mod(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul mod(final Vec4ul res, final Vec4ul a, final long b) {
        return mod(res, a, b, b, b, b);
    }

    public static Vec4ul mod(final Vec4ul res, final Vec4ul a, final Vec4ul b) {
        return mod(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul mod(final Vec4ul res, final Vec4ul a, final ULong bX, final ULong bY, final ULong bZ, final ULong bW) {
        return mod(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ul mod(final Vec4ul res, final Vec4ul a, final long bX, final long bY, final long bZ, final long bW) {
        res.x.value = Long.remainderUnsigned(a.x.value, bX);
        res.y.value = Long.remainderUnsigned(a.y.value, bY);
        res.z.value = Long.remainderUnsigned(a.z.value, bZ);
        res.w.value = Long.remainderUnsigned(a.w.value, bW);
        return res;
    }

    public static Vec4us mod_(final Vec4us a, final UShort b) {
        return mod(new Vec4us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us mod_(final Vec4us a, final short b) {
        return mod(new Vec4us(), a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us mod_(final Vec4us a, final int b) {
        return mod(new Vec4us(), a, b, b, b, b);
    }

    public static Vec4us mod_(final Vec4us a, final Vec4us b) {
        return mod(new Vec4us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us mod(final Vec4us a, final UShort b) {
        return mod(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us mod(final Vec4us a, final short b) {
        return mod(a, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us mod(final Vec4us a, final int b) {
        return mod(a, a, b, b, b, b);
    }

    public static Vec4us mod(final Vec4us a, final Vec4us b) {
        return mod(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us mod(final Vec4us res, final Vec4us a, final UShort b) {
        return mod(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us mod(final Vec4us res, final Vec4us a, final short b) {
        return mod(res, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us mod(final Vec4us res, final Vec4us a, final int b) {
        return mod(res, a, b, b, b, b);
    }

    public static Vec4us mod(final Vec4us res, final Vec4us a, final Vec4us b) {
        return mod(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us mod(final Vec4us res, final Vec4us a, final UShort bX, final UShort bY, final UShort bZ, final UShort bW) {
        return mod(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public static Vec4us mod(final Vec4us res, final Vec4us a, final short bX, final short bY, final short bZ, final short bW) {
        return mod(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public static Vec4us mod(final Vec4us res, final Vec4us a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (short) (Integer.remainderUnsigned(a.x.value & 0xffff, bX));
        res.y.value = (short) (Integer.remainderUnsigned(a.y.value & 0xffff, bY));
        res.z.value = (short) (Integer.remainderUnsigned(a.z.value & 0xffff, bZ));
        res.w.value = (short) (Integer.remainderUnsigned(a.w.value & 0xffff, bW));
        return res;
    }

    public static Vec2b and_(final Vec2b a, final byte b) {
        return and(new Vec2b(), a, (int) b, b);
    }

    public static Vec2b and_(final Vec2b a, final int b) {
        return and(new Vec2b(), a, b, b);
    }

    public static Vec2b and_(final Vec2b a, final Vec2b b) {
        return and(new Vec2b(), a, (int) b.x, b.y);
    }

    public static Vec2b and(final Vec2b a, final byte b) {
        return and(a, a, (int) b, b);
    }

    public static Vec2b and(final Vec2b a, final int b) {
        return and(a, a, b, b);
    }

    public static Vec2b and(final Vec2b a, final Vec2b b) {
        return and(a, a, (int) b.x, b.y);
    }

    public static Vec2b and(final Vec2b res, final Vec2b a, final byte b) {
        return and(res, a, (int) b, b);
    }

    public static Vec2b and(final Vec2b res, final Vec2b a, final int b) {
        return and(res, a, b, b);
    }

    public static Vec2b and(final Vec2b res, final Vec2b a, final Vec2b b) {
        return and(res, a, (int) b.x, b.y);
    }

    public static Vec2b and(final Vec2b res, final Vec2b a, final byte bX, final byte bY) {
        return and(res, a, (int) bX, bY);
    }

    public static Vec2b and(final Vec2b res, final Vec2b a, final int bX, final int bY) {
        res.x = (byte) (a.x & bX);
        res.y = (byte) (a.y & bY);
        return res;
    }

    public static Vec2i and_(final Vec2i a, final int b) {
        return and(new Vec2i(), a, b, b);
    }

    public static Vec2i and_(final Vec2i a, final Vec2i b) {
        return and(new Vec2i(), a, b.x, b.y);
    }

    public static Vec2i and(final Vec2i a, final int b) {
        return and(a, a, b, b);
    }

    public static Vec2i and(final Vec2i a, final Vec2i b) {
        return and(a, a, b.x, b.y);
    }

    public static Vec2i and(final Vec2i res, final Vec2i a, final int b) {
        return and(res, a, b, b);
    }

    public static Vec2i and(final Vec2i res, final Vec2i a, final Vec2i b) {
        return and(res, a, b.x, b.y);
    }

    public static Vec2i and(final Vec2i res, final Vec2i a, final int bX, final int bY) {
        res.x = a.x & bX;
        res.y = a.y & bY;
        return res;
    }

    public static Vec2l and_(final Vec2l a, final long b) {
        return and(new Vec2l(), a, b, b);
    }

    public static Vec2l and_(final Vec2l a, final Vec2l b) {
        return and(new Vec2l(), a, b.x, b.y);
    }

    public static Vec2l and(final Vec2l a, final long b) {
        return and(a, a, b, b);
    }

    public static Vec2l and(final Vec2l a, final Vec2l b) {
        return and(a, a, b.x, b.y);
    }

    public static Vec2l and(final Vec2l res, final Vec2l a, final long b) {
        return and(res, a, b, b);
    }

    public static Vec2l and(final Vec2l res, final Vec2l a, final Vec2l b) {
        return and(res, a, b.x, b.y);
    }

    public static Vec2l and(final Vec2l res, final Vec2l a, final long bX, final long bY) {
        res.x = a.x & bX;
        res.y = a.y & bY;
        return res;
    }

    public static Vec2s and_(final Vec2s a, final short b) {
        return and(new Vec2s(), a, (int) b, b);
    }

    public static Vec2s and_(final Vec2s a, final int b) {
        return and(new Vec2s(), a, b, b);
    }

    public static Vec2s and_(final Vec2s a, final Vec2s b) {
        return and(new Vec2s(), a, (int) b.x, b.y);
    }

    public static Vec2s and(final Vec2s a, final short b) {
        return and(a, a, (int) b, b);
    }

    public static Vec2s and(final Vec2s a, final int b) {
        return and(a, a, b, b);
    }

    public static Vec2s and(final Vec2s a, final Vec2s b) {
        return and(a, a, (int) b.x, b.y);
    }

    public static Vec2s and(final Vec2s res, final Vec2s a, final short b) {
        return and(res, a, (int) b, b);
    }

    public static Vec2s and(final Vec2s res, final Vec2s a, final int b) {
        return and(res, a, b, b);
    }

    public static Vec2s and(final Vec2s res, final Vec2s a, final Vec2s b) {
        return and(res, a, (int) b.x, b.y);
    }

    public static Vec2s and(final Vec2s res, final Vec2s a, final short bX, final short bY) {
        return and(res, a, (int) bX, bY);
    }

    public static Vec2s and(final Vec2s res, final Vec2s a, final int bX, final int bY) {
        res.x = (short) (a.x & bX);
        res.y = (short) (a.y & bY);
        return res;
    }

    public static Vec2ub and_(final Vec2ub a, final UByte b) {
        return and(new Vec2ub(), a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub and_(final Vec2ub a, final byte b) {
        return and(new Vec2ub(), a, b & 0xff, b & 0xff);
    }

    public static Vec2ub and_(final Vec2ub a, final int b) {
        return and(new Vec2ub(), a, b, b);
    }

    public static Vec2ub and_(final Vec2ub a, final Vec2ub b) {
        return and(new Vec2ub(), a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub and(final Vec2ub a, final UByte b) {
        return and(a, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub and(final Vec2ub a, final byte b) {
        return and(a, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub and(final Vec2ub a, final int b) {
        return and(a, a, b, b);
    }

    public static Vec2ub and(final Vec2ub a, final Vec2ub b) {
        return and(a, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub and(final Vec2ub res, final Vec2ub a, final UByte b) {
        return and(res, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub and(final Vec2ub res, final Vec2ub a, final byte b) {
        return and(res, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub and(final Vec2ub res, final Vec2ub a, final int b) {
        return and(res, a, b, b);
    }

    public static Vec2ub and(final Vec2ub res, final Vec2ub a, final Vec2ub b) {
        return and(res, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub and(final Vec2ub res, final Vec2ub a, final UByte bX, final UByte bY) {
        return and(res, a, bX.value & 0xff, bY.value & 0xff);
    }

    public static Vec2ub and(final Vec2ub res, final Vec2ub a, final byte bX, final byte bY) {
        return and(res, a, bX & 0xff, bY & 0xff);
    }

    public static Vec2ub and(final Vec2ub res, final Vec2ub a, final int bX, final int bY) {
        res.x.value = (byte) ((a.x.value & 0xff) & bX);
        res.y.value = (byte) ((a.y.value & 0xff) & bY);
        return res;
    }

    public static Vec2ui and_(final Vec2ui a, final UInt b) {
        return and(new Vec2ui(), a, b.value, b.value);
    }

    public static Vec2ui and_(final Vec2ui a, final int b) {
        return and(new Vec2ui(), a, b, b);
    }

    public static Vec2ui and_(final Vec2ui a, final Vec2ui b) {
        return and(new Vec2ui(), a, b.x.value, b.y.value);
    }

    public static Vec2ui and(final Vec2ui a, final UInt b) {
        return and(a, a, b.value, b.value);
    }

    public static Vec2ui and(final Vec2ui a, final int b) {
        return and(a, a, b, b);
    }

    public static Vec2ui and(final Vec2ui a, final Vec2ui b) {
        return and(a, a, b.x.value, b.y.value);
    }

    public static Vec2ui and(final Vec2ui res, final Vec2ui a, final UInt b) {
        return and(res, a, b.value, b.value);
    }

    public static Vec2ui and(final Vec2ui res, final Vec2ui a, final int b) {
        return and(res, a, b, b);
    }

    public static Vec2ui and(final Vec2ui res, final Vec2ui a, final Vec2ui b) {
        return and(res, a, b.x.value, b.y.value);
    }

    public static Vec2ui and(final Vec2ui res, final Vec2ui a, final UInt bX, final UInt bY) {
        return and(res, a, bX.value, bY.value);
    }

    public static Vec2ui and(final Vec2ui res, final Vec2ui a, final int bX, final int bY) {
        res.x.value = a.x.value & bX;
        res.y.value = a.y.value & bY;
        return res;
    }

    public static Vec2ul and_(final Vec2ul a, final ULong b) {
        return and(new Vec2ul(), a, b.value, b.value);
    }

    public static Vec2ul and_(final Vec2ul a, final long b) {
        return and(new Vec2ul(), a, b, b);
    }

    public static Vec2ul and_(final Vec2ul a, final Vec2ul b) {
        return and(new Vec2ul(), a, b.x.value, b.y.value);
    }

    public static Vec2ul and(final Vec2ul a, final ULong b) {
        return and(a, a, b.value, b.value);
    }

    public static Vec2ul and(final Vec2ul a, final long b) {
        return and(a, a, b, b);
    }

    public static Vec2ul and(final Vec2ul a, final Vec2ul b) {
        return and(a, a, b.x.value, b.y.value);
    }

    public static Vec2ul and(final Vec2ul res, final Vec2ul a, final ULong b) {
        return and(res, a, b.value, b.value);
    }

    public static Vec2ul and(final Vec2ul res, final Vec2ul a, final long b) {
        return and(res, a, b, b);
    }

    public static Vec2ul and(final Vec2ul res, final Vec2ul a, final Vec2ul b) {
        return and(res, a, b.x.value, b.y.value);
    }

    public static Vec2ul and(final Vec2ul res, final Vec2ul a, final ULong bX, final ULong bY) {
        return and(res, a, bX.value, bY.value);
    }

    public static Vec2ul and(final Vec2ul res, final Vec2ul a, final long bX, final long bY) {
        res.x.value = a.x.value & bX;
        res.y.value = a.y.value & bY;
        return res;
    }

    public static Vec2us and_(final Vec2us a, final UShort b) {
        return and(new Vec2us(), a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us and_(final Vec2us a, final short b) {
        return and(new Vec2us(), a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us and_(final Vec2us a, final int b) {
        return and(new Vec2us(), a, b, b);
    }

    public static Vec2us and_(final Vec2us a, final Vec2us b) {
        return and(new Vec2us(), a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us and(final Vec2us a, final UShort b) {
        return and(a, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us and(final Vec2us a, final short b) {
        return and(a, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us and(final Vec2us a, final int b) {
        return and(a, a, b, b);
    }

    public static Vec2us and(final Vec2us a, final Vec2us b) {
        return and(a, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us and(final Vec2us res, final Vec2us a, final UShort b) {
        return and(res, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us and(final Vec2us res, final Vec2us a, final short b) {
        return and(res, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us and(final Vec2us res, final Vec2us a, final int b) {
        return and(res, a, b, b);
    }

    public static Vec2us and(final Vec2us res, final Vec2us a, final Vec2us b) {
        return and(res, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us and(final Vec2us res, final Vec2us a, final UShort bX, final UShort bY) {
        return and(res, a, bX.value & 0xffff, bY.value & 0xffff);
    }

    public static Vec2us and(final Vec2us res, final Vec2us a, final short bX, final short bY) {
        return and(res, a, bX & 0xffff, bY & 0xffff);
    }

    public static Vec2us and(final Vec2us res, final Vec2us a, final int bX, final int bY) {
        res.x.value = (short) ((a.x.value & 0xffff) & bX);
        res.y.value = (short) ((a.y.value & 0xffff) & bY);
        return res;
    }

    public static Vec3b and_(final Vec3b a, final byte b) {
        return and(new Vec3b(), a, b, b, b);
    }

    public static Vec3b and_(final Vec3b a, final int b) {
        return and(new Vec3b(), a, b, b, b);
    }

    public static Vec3b and_(final Vec3b a, final Vec3b b) {
        return and(new Vec3b(), a, b.x, b.y, b.z);
    }

    public static Vec3b and(final Vec3b a, final byte b) {
        return and(a, a, b, b, b);
    }

    public static Vec3b and(final Vec3b a, final int b) {
        return and(a, a, b, b, b);
    }

    public static Vec3b and(final Vec3b a, final Vec3b b) {
        return and(a, a, b.x, b.y, b.z);
    }

    public static Vec3b and(final Vec3b res, final Vec3b a, final byte b) {
        return and(res, a, b, b, b);
    }

    public static Vec3b and(final Vec3b res, final Vec3b a, final int b) {
        return and(res, a, b, b, b);
    }

    public static Vec3b and(final Vec3b res, final Vec3b a, final Vec3b b) {
        return and(res, a, b.x, b.y, b.z);
    }

    public static Vec3b and(final Vec3b res, final Vec3b a, final byte bX, final byte bY, final byte bZ) {
        return and(res, a, (int) bX, bY, bZ);
    }

    public static Vec3b and(final Vec3b res, final Vec3b a, final int bX, final int bY, final int bZ) {
        res.x = (byte) (a.x & bX);
        res.y = (byte) (a.y & bY);
        res.z = (byte) (a.z & bZ);
        return res;
    }

    public static Vec3i and_(final Vec3i a, final int b) {
        return and(new Vec3i(), a, b, b, b);
    }

    public static Vec3i and_(final Vec3i a, final Vec3i b) {
        return and(new Vec3i(), a, b.x, b.y, b.z);
    }

    public static Vec3i and(final Vec3i a, final int b) {
        return and(a, a, b, b, b);
    }

    public static Vec3i and(final Vec3i a, final Vec3i b) {
        return and(a, a, b.x, b.y, b.z);
    }

    public static Vec3i and(final Vec3i res, final Vec3i a, final int b) {
        return and(res, a, b, b, b);
    }

    public static Vec3i and(final Vec3i res, final Vec3i a, final Vec3i b) {
        return and(res, a, b.x, b.y, b.z);
    }

    public static Vec3i and(final Vec3i res, final Vec3i a, final int bX, final int bY, final int bZ) {
        res.x = a.x & bX;
        res.y = a.y & bY;
        res.z = a.z & bZ;
        return res;
    }

    public static Vec3l and_(final Vec3l a, final long b) {
        return and(new Vec3l(), a, b, b, b);
    }

    public static Vec3l and_(final Vec3l a, final Vec3l b) {
        return and(new Vec3l(), a, b.x, b.y, b.z);
    }

    public static Vec3l and(final Vec3l a, final long b) {
        return and(a, a, b, b, b);
    }

    public static Vec3l and(final Vec3l a, final Vec3l b) {
        return and(a, a, b.x, b.y, b.z);
    }

    public static Vec3l and(final Vec3l res, final Vec3l a, final long b) {
        return and(res, a, b, b, b);
    }

    public static Vec3l and(final Vec3l res, final Vec3l a, final Vec3l b) {
        return and(res, a, b.x, b.y, b.z);
    }

    public static Vec3l and(final Vec3l res, final Vec3l a, final long bX, final long bY, final long bZ) {
        res.x = a.x & bX;
        res.y = a.y & bY;
        res.z = a.z & bZ;
        return res;
    }

    public static Vec3s and_(final Vec3s a, final short b) {
        return and(new Vec3s(), a, b, b, b);
    }

    public static Vec3s and_(final Vec3s a, final int b) {
        return and(new Vec3s(), a, b, b, b);
    }

    public static Vec3s and_(final Vec3s a, final Vec3s b) {
        return and(new Vec3s(), a, b.x, b.y, b.z);
    }

    public static Vec3s and(final Vec3s a, final short b) {
        return and(a, a, b, b, b);
    }

    public static Vec3s and(final Vec3s a, final int b) {
        return and(a, a, b, b, b);
    }

    public static Vec3s and(final Vec3s a, final Vec3s b) {
        return and(a, a, b.x, b.y, b.z);
    }

    public static Vec3s and(final Vec3s res, final Vec3s a, final short b) {
        return and(res, a, b, b, b);
    }

    public static Vec3s and(final Vec3s res, final Vec3s a, final int b) {
        return and(res, a, b, b, b);
    }

    public static Vec3s and(final Vec3s res, final Vec3s a, final Vec3s b) {
        return and(res, a, b.x, b.y, b.z);
    }

    public static Vec3s and(final Vec3s res, final Vec3s a, final short bX, final short bY, final short bZ) {
        return and(res, a, (int) bX, bY, bZ);
    }

    public static Vec3s and(final Vec3s res, final Vec3s a, final int bX, final int bY, final int bZ) {
        res.x = (short) (a.x & bX);
        res.y = (short) (a.y & bY);
        res.z = (short) (a.z & bZ);
        return res;
    }

    public static Vec3ub and_(final Vec3ub a, final UByte b) {
        return and(new Vec3ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub and_(final Vec3ub a, final byte b) {
        return and(new Vec3ub(), a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub and_(final Vec3ub a, final int b) {
        return and(new Vec3ub(), a, b, b, b);
    }

    public static Vec3ub and_(final Vec3ub a, final Vec3ub b) {
        return and(new Vec3ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub and(final Vec3ub a, final UByte b) {
        return and(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub and(final Vec3ub a, final byte b) {
        return and(a, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub and(final Vec3ub a, final int b) {
        return and(a, a, b, b, b);
    }

    public static Vec3ub and(final Vec3ub a, final Vec3ub b) {
        return and(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub and(final Vec3ub res, final Vec3ub a, final UByte b) {
        return and(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub and(final Vec3ub res, final Vec3ub a, final byte b) {
        return and(res, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub and(final Vec3ub res, final Vec3ub a, final int b) {
        return and(res, a, b, b, b);
    }

    public static Vec3ub and(final Vec3ub res, final Vec3ub a, final Vec3ub b) {
        return and(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub and(final Vec3ub res, final Vec3ub a, final UByte bX, final UByte bY, final UByte bZ) {
        return and(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public static Vec3ub and(final Vec3ub res, final Vec3ub a, final byte bX, final byte bY, final byte bZ) {
        return and(res, a, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public static Vec3ub and(final Vec3ub res, final Vec3ub a, final int bX, final int bY, final int bZ) {
        res.x.value = (byte) ((a.x.value & 0xff) & bX);
        res.y.value = (byte) ((a.y.value & 0xff) & bY);
        res.z.value = (byte) ((a.z.value & 0xff) & bZ);
        return res;
    }

    public static Vec3ui and_(final Vec3ui a, final UInt b) {
        return and(new Vec3ui(), a, b.value, b.value, b.value);
    }

    public static Vec3ui and_(final Vec3ui a, final int b) {
        return and(new Vec3ui(), a, b, b, b);
    }

    public static Vec3ui and_(final Vec3ui a, final Vec3ui b) {
        return and(new Vec3ui(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui and(final Vec3ui a, final UInt b) {
        return and(a, a, b.value, b.value, b.value);
    }

    public static Vec3ui and(final Vec3ui a, final int b) {
        return and(a, a, b, b, b);
    }

    public static Vec3ui and(final Vec3ui a, final Vec3ui b) {
        return and(a, a, b.x.value, b.y.value, b.y.value);
    }

    public static Vec3ui and(final Vec3ui res, final Vec3ui a, final UInt b) {
        return and(res, a, b.value, b.value, b.value);
    }

    public static Vec3ui and(final Vec3ui res, final Vec3ui a, final int b) {
        return and(res, a, b, b, b);
    }

    public static Vec3ui and(final Vec3ui res, final Vec3ui a, final Vec3ui b) {
        return and(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui and(final Vec3ui res, final Vec3ui a, final UInt bX, final UInt bY, final UInt bZ) {
        return and(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ui and(final Vec3ui res, final Vec3ui a, final int bX, final int bY, final int bZ) {
        res.x.value = a.x.value & bX;
        res.y.value = a.y.value & bY;
        res.z.value = a.z.value & bZ;
        return res;
    }

    public static Vec3ul and_(final Vec3ul a, final ULong b) {
        return and(new Vec3ul(), a, b.value, b.value, b.value);
    }

    public static Vec3ul and_(final Vec3ul a, final long b) {
        return and(new Vec3ul(), a, b, b, b);
    }

    public static Vec3ul and_(final Vec3ul a, final Vec3ul b) {
        return and(new Vec3ul(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul and(final Vec3ul a, final ULong b) {
        return and(a, a, b.value, b.value, b.value);
    }

    public static Vec3ul and(final Vec3ul a, final long b) {
        return and(a, a, b, b, b);
    }

    public static Vec3ul and(final Vec3ul a, final Vec3ul b) {
        return and(a, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul and(final Vec3ul res, final Vec3ul a, final ULong b) {
        return and(res, a, b.value, b.value, b.value);
    }

    public static Vec3ul and(final Vec3ul res, final Vec3ul a, final long b) {
        return and(res, a, b, b, b);
    }

    public static Vec3ul and(final Vec3ul res, final Vec3ul a, final Vec3ul b) {
        return and(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul and(final Vec3ul res, final Vec3ul a, final ULong bX, final ULong bY, final ULong bZ) {
        return and(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ul and(final Vec3ul res, final Vec3ul a, final long bX, final long bY, final long bZ) {
        res.x.value = a.x.value & bX;
        res.y.value = a.y.value & bY;
        res.z.value = a.z.value & bZ;
        return res;
    }

    public static Vec3us and_(final Vec3us a, final UShort b) {
        return and(new Vec3us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us and_(final Vec3us a, final short b) {
        return and(new Vec3us(), a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us and_(final Vec3us a, final int b) {
        return and(new Vec3us(), a, b, b, b);
    }

    public static Vec3us and_(final Vec3us a, final Vec3us b) {
        return and(new Vec3us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us and(final Vec3us a, final UShort b) {
        return and(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us and(final Vec3us a, final short b) {
        return and(a, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us and(final Vec3us a, final int b) {
        return and(a, a, b, b, b);
    }

    public static Vec3us and(final Vec3us a, final Vec3us b) {
        return and(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us and(final Vec3us res, final Vec3us a, final UShort b) {
        return and(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us and(final Vec3us res, final Vec3us a, final short b) {
        return and(res, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us and(final Vec3us res, final Vec3us a, final int b) {
        return and(res, a, b, b, b);
    }

    public static Vec3us and(final Vec3us res, final Vec3us a, final Vec3us b) {
        return and(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us and(final Vec3us res, final Vec3us a, final UShort bX, final UShort bY, final UShort bZ) {
        return and(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public static Vec3us and(final Vec3us res, final Vec3us a, final short bX, final short bY, final short bZ) {
        return and(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public static Vec3us and(final Vec3us res, final Vec3us a, final int bX, final int bY, final int bZ) {
        res.x.value = (short) ((a.x.value & 0xffff) & bX);
        res.y.value = (short) ((a.y.value & 0xffff) & bY);
        res.z.value = (short) ((a.z.value & 0xffff) & bZ);
        return res;
    }

    public static Vec4b and_(final Vec4b a, final byte b) {
        return and(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b and_(final Vec4b a, final int b) {
        return and(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b and_(final Vec4b a, final Vec4b b) {
        return and(new Vec4b(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b and(final Vec4b a, final byte b) {
        return and(a, a, b, b, b, b);
    }

    public static Vec4b and(final Vec4b a, final int b) {
        return and(a, a, b, b, b, b);
    }

    public static Vec4b and(final Vec4b a, final Vec4b b) {
        return and(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b and(final Vec4b res, final Vec4b a, final byte b) {
        return and(res, a, b, b, b, b);
    }

    public static Vec4b and(final Vec4b res, final Vec4b a, final int b) {
        return and(res, a, b, b, b, b);
    }

    public static Vec4b and(final Vec4b res, final Vec4b a, final Vec4b b) {
        return and(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b and(final Vec4b res, final Vec4b a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return and(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4b and(final Vec4b res, final Vec4b a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (byte) (a.x & bX);
        res.y = (byte) (a.y & bY);
        res.z = (byte) (a.z & bZ);
        res.w = (byte) (a.w & bW);
        return res;
    }

    public static Vec4i and_(final Vec4i a, final int b) {
        return and(new Vec4i(), a, b, b, b, b);
    }

    public static Vec4i and_(final Vec4i a, final Vec4i b) {
        return and(new Vec4i(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i and(final Vec4i a, final int b) {
        return and(a, a, b, b, b, b);
    }

    public static Vec4i and(final Vec4i a, final Vec4i b) {
        return and(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i and(final Vec4i res, final Vec4i a, final int b) {
        return and(res, a, b, b, b, b);
    }

    public static Vec4i and(final Vec4i res, final Vec4i a, final Vec4i b) {
        return and(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i and(final Vec4i res, final Vec4i a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = a.x & bX;
        res.y = a.y & bY;
        res.z = a.z & bZ;
        res.w = a.w & bW;
        return res;
    }

    public static Vec4l and_(final Vec4l a, final long b) {
        return and(new Vec4l(), a, b, b, b, b);
    }

    public static Vec4l and_(final Vec4l a, final Vec4l b) {
        return and(new Vec4l(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l and(final Vec4l a, final long b) {
        return and(a, a, b, b, b, b);
    }

    public static Vec4l and(final Vec4l a, final Vec4l b) {
        return and(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l and(final Vec4l res, final Vec4l a, final long b) {
        return and(res, a, b, b, b, b);
    }

    public static Vec4l and(final Vec4l res, final Vec4l a, final Vec4l b) {
        return and(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l and(final Vec4l res, final Vec4l a, final long bX, final long bY, final long bZ, final long bW) {
        res.x = a.x & bX;
        res.y = a.y & bY;
        res.z = a.z & bZ;
        res.w = a.w & bW;
        return res;
    }

    public static Vec4s and_(final Vec4s a, final short b) {
        return and(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s and_(final Vec4s a, final int b) {
        return and(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s and_(final Vec4s a, final Vec4s b) {
        return and(new Vec4s(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s and(final Vec4s a, final short b) {
        return and(a, a, b, b, b, b);
    }

    public static Vec4s and(final Vec4s a, final int b) {
        return and(a, a, b, b, b, b);
    }

    public static Vec4s and(final Vec4s a, final Vec4s b) {
        return and(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s and(final Vec4s res, final Vec4s a, final short b) {
        return and(res, a, b, b, b, b);
    }

    public static Vec4s and(final Vec4s res, final Vec4s a, final int b) {
        return and(res, a, b, b, b, b);
    }

    public static Vec4s and(final Vec4s res, final Vec4s a, final Vec4s b) {
        return and(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s and(final Vec4s res, final Vec4s a, final short bX, final short bY, final short bZ, final short bW) {
        return and(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4s and(final Vec4s res, final Vec4s a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (short) (a.x & bX);
        res.y = (short) (a.y & bY);
        res.z = (short) (a.z & bZ);
        res.w = (short) (a.w & bW);
        return res;
    }

    public static Vec4ub and_(final Vec4ub a, final UByte b) {
        return and(new Vec4ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub and_(final Vec4ub a, final byte b) {
        return and(new Vec4ub(), a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub and_(final Vec4ub a, final int b) {
        return and(new Vec4ub(), a, b, b, b, b);
    }

    public static Vec4ub and_(final Vec4ub a, final Vec4ub b) {
        return and(new Vec4ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub and(final Vec4ub a, final UByte b) {
        return and(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub and(final Vec4ub a, final byte b) {
        return and(a, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub and(final Vec4ub a, final int b) {
        return and(a, a, b, b, b, b);
    }

    public static Vec4ub and(final Vec4ub a, final Vec4ub b) {
        return and(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub and(final Vec4ub res, final Vec4ub a, final UByte b) {
        return and(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub and(final Vec4ub res, final Vec4ub a, final byte b) {
        return and(res, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub and(final Vec4ub res, final Vec4ub a, final int b) {
        return and(res, a, b, b, b, b);
    }

    public static Vec4ub and(final Vec4ub res, final Vec4ub a, final Vec4ub b) {
        return and(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub and(final Vec4ub res, final Vec4ub a, final UByte bX, final UByte bY, final UByte bZ, final UByte bW) {
        return and(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bZ.value & 0xff);
    }

    public static Vec4ub and(final Vec4ub res, final Vec4ub a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return and(res, a, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public static Vec4ub and(final Vec4ub res, final Vec4ub a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (byte) ((a.x.value & 0xff) & bX);
        res.y.value = (byte) ((a.y.value & 0xff) & bY);
        res.z.value = (byte) ((a.z.value & 0xff) & bZ);
        res.w.value = (byte) ((a.w.value & 0xff) & bW);
        return res;
    }

    public static Vec4ui and_(final Vec4ui a, final UInt b) {
        return and(new Vec4ui(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui and_(final Vec4ui a, final int b) {
        return and(new Vec4ui(), a, b, b, b, b);
    }

    public static Vec4ui and_(final Vec4ui a, final Vec4ui b) {
        return and(new Vec4ui(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui and(final Vec4ui a, final UInt b) {
        return and(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui and(final Vec4ui a, final int b) {
        return and(a, a, b, b, b, b);
    }

    public static Vec4ui and(final Vec4ui a, final Vec4ui b) {
        return and(a, a, b.x.value, b.y.value, b.y.value, b.w.value);
    }

    public static Vec4ui and(final Vec4ui res, final Vec4ui a, final UInt b) {
        return and(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui and(final Vec4ui res, final Vec4ui a, final int b) {
        return and(res, a, b, b, b, b);
    }

    public static Vec4ui and(final Vec4ui res, final Vec4ui a, final Vec4ui b) {
        return and(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui and(final Vec4ui res, final Vec4ui a, final UInt bX, final UInt bY, final UInt bZ, final UInt bW) {
        return and(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ui and(final Vec4ui res, final Vec4ui a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = a.x.value & bX;
        res.y.value = a.y.value & bY;
        res.z.value = a.z.value & bZ;
        res.w.value = a.w.value & bW;
        return res;
    }

    public static Vec4ul and_(final Vec4ul a, final ULong b) {
        return and(new Vec4ul(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul and_(final Vec4ul a, final long b) {
        return and(new Vec4ul(), a, b, b, b, b);
    }

    public static Vec4ul and_(final Vec4ul a, final Vec4ul b) {
        return and(new Vec4ul(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul and(final Vec4ul a, final ULong b) {
        return and(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul and(final Vec4ul a, final long b) {
        return and(a, a, b, b, b, b);
    }

    public static Vec4ul and(final Vec4ul a, final Vec4ul b) {
        return and(a, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul and(final Vec4ul res, final Vec4ul a, final ULong b) {
        return and(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul and(final Vec4ul res, final Vec4ul a, final long b) {
        return and(res, a, b, b, b, b);
    }

    public static Vec4ul and(final Vec4ul res, final Vec4ul a, final Vec4ul b) {
        return and(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul and(final Vec4ul res, final Vec4ul a, final ULong bX, final ULong bY, final ULong bZ, final ULong bW) {
        return and(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ul and(final Vec4ul res, final Vec4ul a, final long bX, final long bY, final long bZ, final long bW) {
        res.x.value = a.x.value & bX;
        res.y.value = a.y.value & bY;
        res.z.value = a.z.value & bZ;
        res.w.value = a.w.value & bW;
        return res;
    }

    public static Vec4us and_(final Vec4us a, final UShort b) {
        return and(new Vec4us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us and_(final Vec4us a, final short b) {
        return and(new Vec4us(), a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us and_(final Vec4us a, final int b) {
        return and(new Vec4us(), a, b, b, b, b);
    }

    public static Vec4us and_(final Vec4us a, final Vec4us b) {
        return and(new Vec4us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us and(final Vec4us a, final UShort b) {
        return and(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us and(final Vec4us a, final short b) {
        return and(a, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us and(final Vec4us a, final int b) {
        return and(a, a, b, b, b, b);
    }

    public static Vec4us and(final Vec4us a, final Vec4us b) {
        return and(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us and(final Vec4us res, final Vec4us a, final UShort b) {
        return and(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us and(final Vec4us res, final Vec4us a, final short b) {
        return and(res, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us and(final Vec4us res, final Vec4us a, final int b) {
        return and(res, a, b, b, b, b);
    }

    public static Vec4us and(final Vec4us res, final Vec4us a, final Vec4us b) {
        return and(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us and(final Vec4us res, final Vec4us a, final UShort bX, final UShort bY, final UShort bZ, final UShort bW) {
        return and(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public static Vec4us and(final Vec4us res, final Vec4us a, final short bX, final short bY, final short bZ, final short bW) {
        return and(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public static Vec4us and(final Vec4us res, final Vec4us a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (short) ((a.x.value & 0xffff) & bX);
        res.y.value = (short) ((a.y.value & 0xffff) & bY);
        res.z.value = (short) ((a.z.value & 0xffff) & bZ);
        res.w.value = (short) ((a.w.value & 0xffff) & bW);
        return res;
    }

    public static Vec2b or_(final Vec2b a, final byte b) {
        return or(new Vec2b(), a, (int) b, b);
    }

    public static Vec2b or_(final Vec2b a, final int b) {
        return or(new Vec2b(), a, b, b);
    }

    public static Vec2b or_(final Vec2b a, final Vec2b b) {
        return or(new Vec2b(), a, (int) b.x, b.y);
    }

    public static Vec2b or(final Vec2b a, final byte b) {
        return or(a, a, (int) b, b);
    }

    public static Vec2b or(final Vec2b a, final int b) {
        return or(a, a, b, b);
    }

    public static Vec2b or(final Vec2b a, final Vec2b b) {
        return or(a, a, (int) b.x, b.y);
    }

    public static Vec2b or(final Vec2b res, final Vec2b a, final byte b) {
        return or(res, a, (int) b, b);
    }

    public static Vec2b or(final Vec2b res, final Vec2b a, final int b) {
        return or(res, a, b, b);
    }

    public static Vec2b or(final Vec2b res, final Vec2b a, final Vec2b b) {
        return or(res, a, (int) b.x, b.y);
    }

    public static Vec2b or(final Vec2b res, final Vec2b a, final byte bX, final byte bY) {
        return or(res, a, (int) bX, bY);
    }

    public static Vec2b or(final Vec2b res, final Vec2b a, final int bX, final int bY) {
        res.x = (byte) (a.x | bX);
        res.y = (byte) (a.y | bY);
        return res;
    }

    public static Vec2i or_(final Vec2i a, final int b) {
        return or(new Vec2i(), a, b, b);
    }

    public static Vec2i or_(final Vec2i a, final Vec2i b) {
        return or(new Vec2i(), a, b.x, b.y);
    }

    public static Vec2i or(final Vec2i a, final int b) {
        return or(a, a, b, b);
    }

    public static Vec2i or(final Vec2i a, final Vec2i b) {
        return or(a, a, b.x, b.y);
    }

    public static Vec2i or(final Vec2i res, final Vec2i a, final int b) {
        return or(res, a, b, b);
    }

    public static Vec2i or(final Vec2i res, final Vec2i a, final Vec2i b) {
        return or(res, a, b.x, b.y);
    }

    public static Vec2i or(final Vec2i res, final Vec2i a, final int bX, final int bY) {
        res.x = a.x | bX;
        res.y = a.y | bY;
        return res;
    }

    public static Vec2l or_(final Vec2l a, final long b) {
        return or(new Vec2l(), a, b, b);
    }

    public static Vec2l or_(final Vec2l a, final Vec2l b) {
        return or(new Vec2l(), a, b.x, b.y);
    }

    public static Vec2l or(final Vec2l a, final long b) {
        return or(a, a, b, b);
    }

    public static Vec2l or(final Vec2l a, final Vec2l b) {
        return or(a, a, b.x, b.y);
    }

    public static Vec2l or(final Vec2l res, final Vec2l a, final long b) {
        return or(res, a, b, b);
    }

    public static Vec2l or(final Vec2l res, final Vec2l a, final Vec2l b) {
        return or(res, a, b.x, b.y);
    }

    public static Vec2l or(final Vec2l res, final Vec2l a, final long bX, final long bY) {
        res.x = a.x | bX;
        res.y = a.y | bY;
        return res;
    }

    public static Vec2s or_(final Vec2s a, final short b) {
        return or(new Vec2s(), a, (int) b, b);
    }

    public static Vec2s or_(final Vec2s a, final int b) {
        return or(new Vec2s(), a, b, b);
    }

    public static Vec2s or_(final Vec2s a, final Vec2s b) {
        return or(new Vec2s(), a, (int) b.x, b.y);
    }

    public static Vec2s or(final Vec2s a, final short b) {
        return or(a, a, (int) b, b);
    }

    public static Vec2s or(final Vec2s a, final int b) {
        return or(a, a, b, b);
    }

    public static Vec2s or(final Vec2s a, final Vec2s b) {
        return or(a, a, (int) b.x, b.y);
    }

    public static Vec2s or(final Vec2s res, final Vec2s a, final short b) {
        return or(res, a, (int) b, b);
    }

    public static Vec2s or(final Vec2s res, final Vec2s a, final int b) {
        return or(res, a, b, b);
    }

    public static Vec2s or(final Vec2s res, final Vec2s a, final Vec2s b) {
        return or(res, a, (int) b.x, b.y);
    }

    public static Vec2s or(final Vec2s res, final Vec2s a, final short bX, final short bY) {
        return or(res, a, (int) bX, bY);
    }

    public static Vec2s or(final Vec2s res, final Vec2s a, final int bX, final int bY) {
        res.x = (short) (a.x | bX);
        res.y = (short) (a.y | bY);
        return res;
    }

    public static Vec2ub or_(final Vec2ub a, final UByte b) {
        return or(new Vec2ub(), a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub or_(final Vec2ub a, final byte b) {
        return or(new Vec2ub(), a, b & 0xff, b & 0xff);
    }

    public static Vec2ub or_(final Vec2ub a, final int b) {
        return or(new Vec2ub(), a, b, b);
    }

    public static Vec2ub or_(final Vec2ub a, final Vec2ub b) {
        return or(new Vec2ub(), a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub or(final Vec2ub a, final UByte b) {
        return or(a, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub or(final Vec2ub a, final byte b) {
        return or(a, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub or(final Vec2ub a, final int b) {
        return or(a, a, b, b);
    }

    public static Vec2ub or(final Vec2ub a, final Vec2ub b) {
        return or(a, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub or(final Vec2ub res, final Vec2ub a, final UByte b) {
        return or(res, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub or(final Vec2ub res, final Vec2ub a, final byte b) {
        return or(res, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub or(final Vec2ub res, final Vec2ub a, final int b) {
        return or(res, a, b, b);
    }

    public static Vec2ub or(final Vec2ub res, final Vec2ub a, final Vec2ub b) {
        return or(res, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub or(final Vec2ub res, final Vec2ub a, final UByte bX, final UByte bY) {
        return or(res, a, bX.value & 0xff, bY.value & 0xff);
    }

    public static Vec2ub or(final Vec2ub res, final Vec2ub a, final byte bX, final byte bY) {
        return or(res, a, bX & 0xff, bY & 0xff);
    }

    public static Vec2ub or(final Vec2ub res, final Vec2ub a, final int bX, final int bY) {
        res.x.value = (byte) ((a.x.value & 0xff) | bX);
        res.y.value = (byte) ((a.y.value & 0xff) | bY);
        return res;
    }

    public static Vec2ui or_(final Vec2ui a, final UInt b) {
        return or(new Vec2ui(), a, b.value, b.value);
    }

    public static Vec2ui or_(final Vec2ui a, final int b) {
        return or(new Vec2ui(), a, b, b);
    }

    public static Vec2ui or_(final Vec2ui a, final Vec2ui b) {
        return or(new Vec2ui(), a, b.x.value, b.y.value);
    }

    public static Vec2ui or(final Vec2ui a, final UInt b) {
        return or(a, a, b.value, b.value);
    }

    public static Vec2ui or(final Vec2ui a, final int b) {
        return or(a, a, b, b);
    }

    public static Vec2ui or(final Vec2ui a, final Vec2ui b) {
        return or(a, a, b.x.value, b.y.value);
    }

    public static Vec2ui or(final Vec2ui res, final Vec2ui a, final UInt b) {
        return or(res, a, b.value, b.value);
    }

    public static Vec2ui or(final Vec2ui res, final Vec2ui a, final int b) {
        return or(res, a, b, b);
    }

    public static Vec2ui or(final Vec2ui res, final Vec2ui a, final Vec2ui b) {
        return or(res, a, b.x.value, b.y.value);
    }

    public static Vec2ui or(final Vec2ui res, final Vec2ui a, final UInt bX, final UInt bY) {
        return or(res, a, bX.value, bY.value);
    }

    public static Vec2ui or(final Vec2ui res, final Vec2ui a, final int bX, final int bY) {
        res.x.value = a.x.value | bX;
        res.y.value = a.y.value | bY;
        return res;
    }

    public static Vec2ul or_(final Vec2ul a, final ULong b) {
        return or(new Vec2ul(), a, b.value, b.value);
    }

    public static Vec2ul or_(final Vec2ul a, final long b) {
        return or(new Vec2ul(), a, b, b);
    }

    public static Vec2ul or_(final Vec2ul a, final Vec2ul b) {
        return or(new Vec2ul(), a, b.x.value, b.y.value);
    }

    public static Vec2ul or(final Vec2ul a, final ULong b) {
        return or(a, a, b.value, b.value);
    }

    public static Vec2ul or(final Vec2ul a, final long b) {
        return or(a, a, b, b);
    }

    public static Vec2ul or(final Vec2ul a, final Vec2ul b) {
        return or(a, a, b.x.value, b.y.value);
    }

    public static Vec2ul or(final Vec2ul res, final Vec2ul a, final ULong b) {
        return or(res, a, b.value, b.value);
    }

    public static Vec2ul or(final Vec2ul res, final Vec2ul a, final long b) {
        return or(res, a, b, b);
    }

    public static Vec2ul or(final Vec2ul res, final Vec2ul a, final Vec2ul b) {
        return or(res, a, b.x.value, b.y.value);
    }

    public static Vec2ul or(final Vec2ul res, final Vec2ul a, final ULong bX, final ULong bY) {
        return or(res, a, bX.value, bY.value);
    }

    public static Vec2ul or(final Vec2ul res, final Vec2ul a, final long bX, final long bY) {
        res.x.value = a.x.value | bX;
        res.y.value = a.y.value | bY;
        return res;
    }

    public static Vec2us or_(final Vec2us a, final UShort b) {
        return or(new Vec2us(), a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us or_(final Vec2us a, final short b) {
        return or(new Vec2us(), a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us or_(final Vec2us a, final int b) {
        return or(new Vec2us(), a, b, b);
    }

    public static Vec2us or_(final Vec2us a, final Vec2us b) {
        return or(new Vec2us(), a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us or(final Vec2us a, final UShort b) {
        return or(a, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us or(final Vec2us a, final short b) {
        return or(a, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us or(final Vec2us a, final int b) {
        return or(a, a, b, b);
    }

    public static Vec2us or(final Vec2us a, final Vec2us b) {
        return or(a, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us or(final Vec2us res, final Vec2us a, final UShort b) {
        return or(res, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us or(final Vec2us res, final Vec2us a, final short b) {
        return or(res, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us or(final Vec2us res, final Vec2us a, final int b) {
        return or(res, a, b, b);
    }

    public static Vec2us or(final Vec2us res, final Vec2us a, final Vec2us b) {
        return or(res, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us or(final Vec2us res, final Vec2us a, final UShort bX, final UShort bY) {
        return or(res, a, bX.value & 0xffff, bY.value & 0xffff);
    }

    public static Vec2us or(final Vec2us res, final Vec2us a, final short bX, final short bY) {
        return or(res, a, bX & 0xffff, bY & 0xffff);
    }

    public static Vec2us or(final Vec2us res, final Vec2us a, final int bX, final int bY) {
        res.x.value = (short) ((a.x.value & 0xffff) | bX);
        res.y.value = (short) ((a.y.value & 0xffff) | bY);
        return res;
    }

    public static Vec3b or_(final Vec3b a, final byte b) {
        return or(new Vec3b(), a, b, b, b);
    }

    public static Vec3b or_(final Vec3b a, final int b) {
        return or(new Vec3b(), a, b, b, b);
    }

    public static Vec3b or_(final Vec3b a, final Vec3b b) {
        return or(new Vec3b(), a, b.x, b.y, b.z);
    }

    public static Vec3b or(final Vec3b a, final byte b) {
        return or(a, a, b, b, b);
    }

    public static Vec3b or(final Vec3b a, final int b) {
        return or(a, a, b, b, b);
    }

    public static Vec3b or(final Vec3b a, final Vec3b b) {
        return or(a, a, b.x, b.y, b.z);
    }

    public static Vec3b or(final Vec3b res, final Vec3b a, final byte b) {
        return or(res, a, b, b, b);
    }

    public static Vec3b or(final Vec3b res, final Vec3b a, final int b) {
        return or(res, a, b, b, b);
    }

    public static Vec3b or(final Vec3b res, final Vec3b a, final Vec3b b) {
        return or(res, a, b.x, b.y, b.z);
    }

    public static Vec3b or(final Vec3b res, final Vec3b a, final byte bX, final byte bY, final byte bZ) {
        return or(res, a, (int) bX, bY, bZ);
    }

    public static Vec3b or(final Vec3b res, final Vec3b a, final int bX, final int bY, final int bZ) {
        res.x = (byte) (a.x | bX);
        res.y = (byte) (a.y | bY);
        res.z = (byte) (a.z | bZ);
        return res;
    }

    public static Vec3i or_(final Vec3i a, final int b) {
        return or(new Vec3i(), a, b, b, b);
    }

    public static Vec3i or_(final Vec3i a, final Vec3i b) {
        return or(new Vec3i(), a, b.x, b.y, b.z);
    }

    public static Vec3i or(final Vec3i a, final int b) {
        return or(a, a, b, b, b);
    }

    public static Vec3i or(final Vec3i a, final Vec3i b) {
        return or(a, a, b.x, b.y, b.z);
    }

    public static Vec3i or(final Vec3i res, final Vec3i a, final int b) {
        return or(res, a, b, b, b);
    }

    public static Vec3i or(final Vec3i res, final Vec3i a, final Vec3i b) {
        return or(res, a, b.x, b.y, b.z);
    }

    public static Vec3i or(final Vec3i res, final Vec3i a, final int bX, final int bY, final int bZ) {
        res.x = a.x | bX;
        res.y = a.y | bY;
        res.z = a.z | bZ;
        return res;
    }

    public static Vec3l or_(final Vec3l a, final long b) {
        return or(new Vec3l(), a, b, b, b);
    }

    public static Vec3l or_(final Vec3l a, final Vec3l b) {
        return or(new Vec3l(), a, b.x, b.y, b.z);
    }

    public static Vec3l or(final Vec3l a, final long b) {
        return or(a, a, b, b, b);
    }

    public static Vec3l or(final Vec3l a, final Vec3l b) {
        return or(a, a, b.x, b.y, b.z);
    }

    public static Vec3l or(final Vec3l res, final Vec3l a, final long b) {
        return or(res, a, b, b, b);
    }

    public static Vec3l or(final Vec3l res, final Vec3l a, final Vec3l b) {
        return or(res, a, b.x, b.y, b.z);
    }

    public static Vec3l or(final Vec3l res, final Vec3l a, final long bX, final long bY, final long bZ) {
        res.x = a.x | bX;
        res.y = a.y | bY;
        res.z = a.z | bZ;
        return res;
    }

    public static Vec3s or_(final Vec3s a, final short b) {
        return or(new Vec3s(), a, b, b, b);
    }

    public static Vec3s or_(final Vec3s a, final int b) {
        return or(new Vec3s(), a, b, b, b);
    }

    public static Vec3s or_(final Vec3s a, final Vec3s b) {
        return or(new Vec3s(), a, b.x, b.y, b.z);
    }

    public static Vec3s or(final Vec3s a, final short b) {
        return or(a, a, b, b, b);
    }

    public static Vec3s or(final Vec3s a, final int b) {
        return or(a, a, b, b, b);
    }

    public static Vec3s or(final Vec3s a, final Vec3s b) {
        return or(a, a, b.x, b.y, b.z);
    }

    public static Vec3s or(final Vec3s res, final Vec3s a, final short b) {
        return or(res, a, b, b, b);
    }

    public static Vec3s or(final Vec3s res, final Vec3s a, final int b) {
        return or(res, a, b, b, b);
    }

    public static Vec3s or(final Vec3s res, final Vec3s a, final Vec3s b) {
        return or(res, a, b.x, b.y, b.z);
    }

    public static Vec3s or(final Vec3s res, final Vec3s a, final short bX, final short bY, final short bZ) {
        return or(res, a, (int) bX, bY, bZ);
    }

    public static Vec3s or(final Vec3s res, final Vec3s a, final int bX, final int bY, final int bZ) {
        res.x = (short) (a.x | bX);
        res.y = (short) (a.y | bY);
        res.z = (short) (a.z | bZ);
        return res;
    }

    public static Vec3ub or_(final Vec3ub a, final UByte b) {
        return or(new Vec3ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub or_(final Vec3ub a, final byte b) {
        return or(new Vec3ub(), a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub or_(final Vec3ub a, final int b) {
        return or(new Vec3ub(), a, b, b, b);
    }

    public static Vec3ub or_(final Vec3ub a, final Vec3ub b) {
        return or(new Vec3ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub or(final Vec3ub a, final UByte b) {
        return or(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub or(final Vec3ub a, final byte b) {
        return or(a, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub or(final Vec3ub a, final int b) {
        return or(a, a, b, b, b);
    }

    public static Vec3ub or(final Vec3ub a, final Vec3ub b) {
        return or(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub or(final Vec3ub res, final Vec3ub a, final UByte b) {
        return or(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub or(final Vec3ub res, final Vec3ub a, final byte b) {
        return or(res, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub or(final Vec3ub res, final Vec3ub a, final int b) {
        return or(res, a, b, b, b);
    }

    public static Vec3ub or(final Vec3ub res, final Vec3ub a, final Vec3ub b) {
        return or(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub or(final Vec3ub res, final Vec3ub a, final UByte bX, final UByte bY, final UByte bZ) {
        return or(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public static Vec3ub or(final Vec3ub res, final Vec3ub a, final byte bX, final byte bY, final byte bZ) {
        return or(res, a, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public static Vec3ub or(final Vec3ub res, final Vec3ub a, final int bX, final int bY, final int bZ) {
        res.x.value = (byte) ((a.x.value & 0xff) | bX);
        res.y.value = (byte) ((a.y.value & 0xff) | bY);
        res.z.value = (byte) ((a.z.value & 0xff) | bZ);
        return res;
    }

    public static Vec3ui or_(final Vec3ui a, final UInt b) {
        return or(new Vec3ui(), a, b.value, b.value, b.value);
    }

    public static Vec3ui or_(final Vec3ui a, final int b) {
        return or(new Vec3ui(), a, b, b, b);
    }

    public static Vec3ui or_(final Vec3ui a, final Vec3ui b) {
        return or(new Vec3ui(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui or(final Vec3ui a, final UInt b) {
        return or(a, a, b.value, b.value, b.value);
    }

    public static Vec3ui or(final Vec3ui a, final int b) {
        return or(a, a, b, b, b);
    }

    public static Vec3ui or(final Vec3ui a, final Vec3ui b) {
        return or(a, a, b.x.value, b.y.value, b.y.value);
    }

    public static Vec3ui or(final Vec3ui res, final Vec3ui a, final UInt b) {
        return or(res, a, b.value, b.value, b.value);
    }

    public static Vec3ui or(final Vec3ui res, final Vec3ui a, final int b) {
        return or(res, a, b, b, b);
    }

    public static Vec3ui or(final Vec3ui res, final Vec3ui a, final Vec3ui b) {
        return or(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui or(final Vec3ui res, final Vec3ui a, final UInt bX, final UInt bY, final UInt bZ) {
        return or(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ui or(final Vec3ui res, final Vec3ui a, final int bX, final int bY, final int bZ) {
        res.x.value = a.x.value | bX;
        res.y.value = a.y.value | bY;
        res.z.value = a.z.value | bZ;
        return res;
    }

    public static Vec3ul or_(final Vec3ul a, final ULong b) {
        return or(new Vec3ul(), a, b.value, b.value, b.value);
    }

    public static Vec3ul or_(final Vec3ul a, final long b) {
        return or(new Vec3ul(), a, b, b, b);
    }

    public static Vec3ul or_(final Vec3ul a, final Vec3ul b) {
        return or(new Vec3ul(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul or(final Vec3ul a, final ULong b) {
        return or(a, a, b.value, b.value, b.value);
    }

    public static Vec3ul or(final Vec3ul a, final long b) {
        return or(a, a, b, b, b);
    }

    public static Vec3ul or(final Vec3ul a, final Vec3ul b) {
        return or(a, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul or(final Vec3ul res, final Vec3ul a, final ULong b) {
        return or(res, a, b.value, b.value, b.value);
    }

    public static Vec3ul or(final Vec3ul res, final Vec3ul a, final long b) {
        return or(res, a, b, b, b);
    }

    public static Vec3ul or(final Vec3ul res, final Vec3ul a, final Vec3ul b) {
        return or(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul or(final Vec3ul res, final Vec3ul a, final ULong bX, final ULong bY, final ULong bZ) {
        return or(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ul or(final Vec3ul res, final Vec3ul a, final long bX, final long bY, final long bZ) {
        res.x.value = a.x.value | bX;
        res.y.value = a.y.value | bY;
        res.z.value = a.z.value | bZ;
        return res;
    }

    public static Vec3us or_(final Vec3us a, final UShort b) {
        return or(new Vec3us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us or_(final Vec3us a, final short b) {
        return or(new Vec3us(), a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us or_(final Vec3us a, final int b) {
        return or(new Vec3us(), a, b, b, b);
    }

    public static Vec3us or_(final Vec3us a, final Vec3us b) {
        return or(new Vec3us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us or(final Vec3us a, final UShort b) {
        return or(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us or(final Vec3us a, final short b) {
        return or(a, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us or(final Vec3us a, final int b) {
        return or(a, a, b, b, b);
    }

    public static Vec3us or(final Vec3us a, final Vec3us b) {
        return or(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us or(final Vec3us res, final Vec3us a, final UShort b) {
        return or(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us or(final Vec3us res, final Vec3us a, final short b) {
        return or(res, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us or(final Vec3us res, final Vec3us a, final int b) {
        return or(res, a, b, b, b);
    }

    public static Vec3us or(final Vec3us res, final Vec3us a, final Vec3us b) {
        return or(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us or(final Vec3us res, final Vec3us a, final UShort bX, final UShort bY, final UShort bZ) {
        return or(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public static Vec3us or(final Vec3us res, final Vec3us a, final short bX, final short bY, final short bZ) {
        return or(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public static Vec3us or(final Vec3us res, final Vec3us a, final int bX, final int bY, final int bZ) {
        res.x.value = (short) ((a.x.value & 0xffff) | bX);
        res.y.value = (short) ((a.y.value & 0xffff) | bY);
        res.z.value = (short) ((a.z.value & 0xffff) | bZ);
        return res;
    }

    public static Vec4b or_(final Vec4b a, final byte b) {
        return or(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b or_(final Vec4b a, final int b) {
        return or(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b or_(final Vec4b a, final Vec4b b) {
        return or(new Vec4b(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b or(final Vec4b a, final byte b) {
        return or(a, a, b, b, b, b);
    }

    public static Vec4b or(final Vec4b a, final int b) {
        return or(a, a, b, b, b, b);
    }

    public static Vec4b or(final Vec4b a, final Vec4b b) {
        return or(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b or(final Vec4b res, final Vec4b a, final byte b) {
        return or(res, a, b, b, b, b);
    }

    public static Vec4b or(final Vec4b res, final Vec4b a, final int b) {
        return or(res, a, b, b, b, b);
    }

    public static Vec4b or(final Vec4b res, final Vec4b a, final Vec4b b) {
        return or(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b or(final Vec4b res, final Vec4b a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return or(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4b or(final Vec4b res, final Vec4b a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (byte) (a.x | bX);
        res.y = (byte) (a.y | bY);
        res.z = (byte) (a.z | bZ);
        res.w = (byte) (a.w | bW);
        return res;
    }

    public static Vec4i or_(final Vec4i a, final int b) {
        return or(new Vec4i(), a, b, b, b, b);
    }

    public static Vec4i or_(final Vec4i a, final Vec4i b) {
        return or(new Vec4i(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i or(final Vec4i a, final int b) {
        return or(a, a, b, b, b, b);
    }

    public static Vec4i or(final Vec4i a, final Vec4i b) {
        return or(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i or(final Vec4i res, final Vec4i a, final int b) {
        return or(res, a, b, b, b, b);
    }

    public static Vec4i or(final Vec4i res, final Vec4i a, final Vec4i b) {
        return or(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i or(final Vec4i res, final Vec4i a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = a.x | bX;
        res.y = a.y | bY;
        res.z = a.z | bZ;
        res.w = a.w | bW;
        return res;
    }

    public static Vec4l or_(final Vec4l a, final long b) {
        return or(new Vec4l(), a, b, b, b, b);
    }

    public static Vec4l or_(final Vec4l a, final Vec4l b) {
        return or(new Vec4l(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l or(final Vec4l a, final long b) {
        return or(a, a, b, b, b, b);
    }

    public static Vec4l or(final Vec4l a, final Vec4l b) {
        return or(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l or(final Vec4l res, final Vec4l a, final long b) {
        return or(res, a, b, b, b, b);
    }

    public static Vec4l or(final Vec4l res, final Vec4l a, final Vec4l b) {
        return or(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l or(final Vec4l res, final Vec4l a, final long bX, final long bY, final long bZ, final long bW) {
        res.x = a.x | bX;
        res.y = a.y | bY;
        res.z = a.z | bZ;
        res.w = a.w | bW;
        return res;
    }

    public static Vec4s or_(final Vec4s a, final short b) {
        return or(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s or_(final Vec4s a, final int b) {
        return or(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s or_(final Vec4s a, final Vec4s b) {
        return or(new Vec4s(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s or(final Vec4s a, final short b) {
        return or(a, a, b, b, b, b);
    }

    public static Vec4s or(final Vec4s a, final int b) {
        return or(a, a, b, b, b, b);
    }

    public static Vec4s or(final Vec4s a, final Vec4s b) {
        return or(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s or(final Vec4s res, final Vec4s a, final short b) {
        return or(res, a, b, b, b, b);
    }

    public static Vec4s or(final Vec4s res, final Vec4s a, final int b) {
        return or(res, a, b, b, b, b);
    }

    public static Vec4s or(final Vec4s res, final Vec4s a, final Vec4s b) {
        return or(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s or(final Vec4s res, final Vec4s a, final short bX, final short bY, final short bZ, final short bW) {
        return or(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4s or(final Vec4s res, final Vec4s a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (short) (a.x | bX);
        res.y = (short) (a.y | bY);
        res.z = (short) (a.z | bZ);
        res.w = (short) (a.w | bW);
        return res;
    }

    public static Vec4ub or_(final Vec4ub a, final UByte b) {
        return or(new Vec4ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub or_(final Vec4ub a, final byte b) {
        return or(new Vec4ub(), a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub or_(final Vec4ub a, final int b) {
        return or(new Vec4ub(), a, b, b, b, b);
    }

    public static Vec4ub or_(final Vec4ub a, final Vec4ub b) {
        return or(new Vec4ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub or(final Vec4ub a, final UByte b) {
        return or(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub or(final Vec4ub a, final byte b) {
        return or(a, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub or(final Vec4ub a, final int b) {
        return or(a, a, b, b, b, b);
    }

    public static Vec4ub or(final Vec4ub a, final Vec4ub b) {
        return or(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub or(final Vec4ub res, final Vec4ub a, final UByte b) {
        return or(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub or(final Vec4ub res, final Vec4ub a, final byte b) {
        return or(res, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub or(final Vec4ub res, final Vec4ub a, final int b) {
        return or(res, a, b, b, b, b);
    }

    public static Vec4ub or(final Vec4ub res, final Vec4ub a, final Vec4ub b) {
        return or(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub or(final Vec4ub res, final Vec4ub a, final UByte bX, final UByte bY, final UByte bZ, final UByte bW) {
        return or(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bZ.value & 0xff);
    }

    public static Vec4ub or(final Vec4ub res, final Vec4ub a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return or(res, a, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public static Vec4ub or(final Vec4ub res, final Vec4ub a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (byte) ((a.x.value & 0xff) | bX);
        res.y.value = (byte) ((a.y.value & 0xff) | bY);
        res.z.value = (byte) ((a.z.value & 0xff) | bZ);
        res.w.value = (byte) ((a.w.value & 0xff) | bW);
        return res;
    }

    public static Vec4ui or_(final Vec4ui a, final UInt b) {
        return or(new Vec4ui(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui or_(final Vec4ui a, final int b) {
        return or(new Vec4ui(), a, b, b, b, b);
    }

    public static Vec4ui or_(final Vec4ui a, final Vec4ui b) {
        return or(new Vec4ui(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui or(final Vec4ui a, final UInt b) {
        return or(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui or(final Vec4ui a, final int b) {
        return or(a, a, b, b, b, b);
    }

    public static Vec4ui or(final Vec4ui a, final Vec4ui b) {
        return or(a, a, b.x.value, b.y.value, b.y.value, b.w.value);
    }

    public static Vec4ui or(final Vec4ui res, final Vec4ui a, final UInt b) {
        return or(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui or(final Vec4ui res, final Vec4ui a, final int b) {
        return or(res, a, b, b, b, b);
    }

    public static Vec4ui or(final Vec4ui res, final Vec4ui a, final Vec4ui b) {
        return or(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui or(final Vec4ui res, final Vec4ui a, final UInt bX, final UInt bY, final UInt bZ, final UInt bW) {
        return or(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ui or(final Vec4ui res, final Vec4ui a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = a.x.value | bX;
        res.y.value = a.y.value | bY;
        res.z.value = a.z.value | bZ;
        res.w.value = a.w.value | bW;
        return res;
    }

    public static Vec4ul or_(final Vec4ul a, final ULong b) {
        return or(new Vec4ul(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul or_(final Vec4ul a, final long b) {
        return or(new Vec4ul(), a, b, b, b, b);
    }

    public static Vec4ul or_(final Vec4ul a, final Vec4ul b) {
        return or(new Vec4ul(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul or(final Vec4ul a, final ULong b) {
        return or(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul or(final Vec4ul a, final long b) {
        return or(a, a, b, b, b, b);
    }

    public static Vec4ul or(final Vec4ul a, final Vec4ul b) {
        return or(a, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul or(final Vec4ul res, final Vec4ul a, final ULong b) {
        return or(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul or(final Vec4ul res, final Vec4ul a, final long b) {
        return or(res, a, b, b, b, b);
    }

    public static Vec4ul or(final Vec4ul res, final Vec4ul a, final Vec4ul b) {
        return or(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul or(final Vec4ul res, final Vec4ul a, final ULong bX, final ULong bY, final ULong bZ, final ULong bW) {
        return or(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ul or(final Vec4ul res, final Vec4ul a, final long bX, final long bY, final long bZ, final long bW) {
        res.x.value = a.x.value | bX;
        res.y.value = a.y.value | bY;
        res.z.value = a.z.value | bZ;
        res.w.value = a.w.value | bW;
        return res;
    }

    public static Vec4us or_(final Vec4us a, final UShort b) {
        return or(new Vec4us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us or_(final Vec4us a, final short b) {
        return or(new Vec4us(), a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us or_(final Vec4us a, final int b) {
        return or(new Vec4us(), a, b, b, b, b);
    }

    public static Vec4us or_(final Vec4us a, final Vec4us b) {
        return or(new Vec4us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us or(final Vec4us a, final UShort b) {
        return or(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us or(final Vec4us a, final short b) {
        return or(a, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us or(final Vec4us a, final int b) {
        return or(a, a, b, b, b, b);
    }

    public static Vec4us or(final Vec4us a, final Vec4us b) {
        return or(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us or(final Vec4us res, final Vec4us a, final UShort b) {
        return or(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us or(final Vec4us res, final Vec4us a, final short b) {
        return or(res, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us or(final Vec4us res, final Vec4us a, final int b) {
        return or(res, a, b, b, b, b);
    }

    public static Vec4us or(final Vec4us res, final Vec4us a, final Vec4us b) {
        return or(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us or(final Vec4us res, final Vec4us a, final UShort bX, final UShort bY, final UShort bZ, final UShort bW) {
        return or(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public static Vec4us or(final Vec4us res, final Vec4us a, final short bX, final short bY, final short bZ, final short bW) {
        return or(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public static Vec4us or(final Vec4us res, final Vec4us a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (short) ((a.x.value & 0xffff) | bX);
        res.y.value = (short) ((a.y.value & 0xffff) | bY);
        res.z.value = (short) ((a.z.value & 0xffff) | bZ);
        res.w.value = (short) ((a.w.value & 0xffff) | bW);
        return res;
    }

    public static Vec2b xor_(final Vec2b a, final byte b) {
        return xor(new Vec2b(), a, (int) b, b);
    }

    public static Vec2b xor_(final Vec2b a, final int b) {
        return xor(new Vec2b(), a, b, b);
    }

    public static Vec2b xor_(final Vec2b a, final Vec2b b) {
        return xor(new Vec2b(), a, (int) b.x, b.y);
    }

    public static Vec2b xor(final Vec2b a, final byte b) {
        return xor(a, a, (int) b, b);
    }

    public static Vec2b xor(final Vec2b a, final int b) {
        return xor(a, a, b, b);
    }

    public static Vec2b xor(final Vec2b a, final Vec2b b) {
        return xor(a, a, (int) b.x, b.y);
    }

    public static Vec2b xor(final Vec2b res, final Vec2b a, final byte b) {
        return xor(res, a, (int) b, b);
    }

    public static Vec2b xor(final Vec2b res, final Vec2b a, final int b) {
        return xor(res, a, b, b);
    }

    public static Vec2b xor(final Vec2b res, final Vec2b a, final Vec2b b) {
        return xor(res, a, (int) b.x, b.y);
    }

    public static Vec2b xor(final Vec2b res, final Vec2b a, final byte bX, final byte bY) {
        return xor(res, a, (int) bX, bY);
    }

    public static Vec2b xor(final Vec2b res, final Vec2b a, final int bX, final int bY) {
        res.x = (byte) (a.x ^ bX);
        res.y = (byte) (a.y ^ bY);
        return res;
    }

    public static Vec2i xor_(final Vec2i a, final int b) {
        return xor(new Vec2i(), a, b, b);
    }

    public static Vec2i xor_(final Vec2i a, final Vec2i b) {
        return xor(new Vec2i(), a, b.x, b.y);
    }

    public static Vec2i xor(final Vec2i a, final int b) {
        return xor(a, a, b, b);
    }

    public static Vec2i xor(final Vec2i a, final Vec2i b) {
        return xor(a, a, b.x, b.y);
    }

    public static Vec2i xor(final Vec2i res, final Vec2i a, final int b) {
        return xor(res, a, b, b);
    }

    public static Vec2i xor(final Vec2i res, final Vec2i a, final Vec2i b) {
        return xor(res, a, b.x, b.y);
    }

    public static Vec2i xor(final Vec2i res, final Vec2i a, final int bX, final int bY) {
        res.x = a.x ^ bX;
        res.y = a.y ^ bY;
        return res;
    }

    public static Vec2l xor_(final Vec2l a, final long b) {
        return xor(new Vec2l(), a, b, b);
    }

    public static Vec2l xor_(final Vec2l a, final Vec2l b) {
        return xor(new Vec2l(), a, b.x, b.y);
    }

    public static Vec2l xor(final Vec2l a, final long b) {
        return xor(a, a, b, b);
    }

    public static Vec2l xor(final Vec2l a, final Vec2l b) {
        return xor(a, a, b.x, b.y);
    }

    public static Vec2l xor(final Vec2l res, final Vec2l a, final long b) {
        return xor(res, a, b, b);
    }

    public static Vec2l xor(final Vec2l res, final Vec2l a, final Vec2l b) {
        return xor(res, a, b.x, b.y);
    }

    public static Vec2l xor(final Vec2l res, final Vec2l a, final long bX, final long bY) {
        res.x = a.x ^ bX;
        res.y = a.y ^ bY;
        return res;
    }

    public static Vec2s xor_(final Vec2s a, final short b) {
        return xor(new Vec2s(), a, (int) b, b);
    }

    public static Vec2s xor_(final Vec2s a, final int b) {
        return xor(new Vec2s(), a, b, b);
    }

    public static Vec2s xor_(final Vec2s a, final Vec2s b) {
        return xor(new Vec2s(), a, (int) b.x, b.y);
    }

    public static Vec2s xor(final Vec2s a, final short b) {
        return xor(a, a, (int) b, b);
    }

    public static Vec2s xor(final Vec2s a, final int b) {
        return xor(a, a, b, b);
    }

    public static Vec2s xor(final Vec2s a, final Vec2s b) {
        return xor(a, a, (int) b.x, b.y);
    }

    public static Vec2s xor(final Vec2s res, final Vec2s a, final short b) {
        return xor(res, a, (int) b, b);
    }

    public static Vec2s xor(final Vec2s res, final Vec2s a, final int b) {
        return xor(res, a, b, b);
    }

    public static Vec2s xor(final Vec2s res, final Vec2s a, final Vec2s b) {
        return xor(res, a, (int) b.x, b.y);
    }

    public static Vec2s xor(final Vec2s res, final Vec2s a, final short bX, final short bY) {
        return xor(res, a, (int) bX, bY);
    }

    public static Vec2s xor(final Vec2s res, final Vec2s a, final int bX, final int bY) {
        res.x = (short) (a.x ^ bX);
        res.y = (short) (a.y ^ bY);
        return res;
    }

    public static Vec2ub xor_(final Vec2ub a, final UByte b) {
        return xor(new Vec2ub(), a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub xor_(final Vec2ub a, final byte b) {
        return xor(new Vec2ub(), a, b & 0xff, b & 0xff);
    }

    public static Vec2ub xor_(final Vec2ub a, final int b) {
        return xor(new Vec2ub(), a, b, b);
    }

    public static Vec2ub xor_(final Vec2ub a, final Vec2ub b) {
        return xor(new Vec2ub(), a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub xor(final Vec2ub a, final UByte b) {
        return xor(a, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub xor(final Vec2ub a, final byte b) {
        return xor(a, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub xor(final Vec2ub a, final int b) {
        return xor(a, a, b, b);
    }

    public static Vec2ub xor(final Vec2ub a, final Vec2ub b) {
        return xor(a, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub xor(final Vec2ub res, final Vec2ub a, final UByte b) {
        return xor(res, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub xor(final Vec2ub res, final Vec2ub a, final byte b) {
        return xor(res, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub xor(final Vec2ub res, final Vec2ub a, final int b) {
        return xor(res, a, b, b);
    }

    public static Vec2ub xor(final Vec2ub res, final Vec2ub a, final Vec2ub b) {
        return xor(res, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub xor(final Vec2ub res, final Vec2ub a, final UByte bX, final UByte bY) {
        return xor(res, a, bX.value & 0xff, bY.value & 0xff);
    }

    public static Vec2ub xor(final Vec2ub res, final Vec2ub a, final byte bX, final byte bY) {
        return xor(res, a, bX & 0xff, bY & 0xff);
    }

    public static Vec2ub xor(final Vec2ub res, final Vec2ub a, final int bX, final int bY) {
        res.x.value = (byte) ((a.x.value & 0xff) ^ bX);
        res.y.value = (byte) ((a.y.value & 0xff) ^ bY);
        return res;
    }

    public static Vec2ui xor_(final Vec2ui a, final UInt b) {
        return xor(new Vec2ui(), a, b.value, b.value);
    }

    public static Vec2ui xor_(final Vec2ui a, final int b) {
        return xor(new Vec2ui(), a, b, b);
    }

    public static Vec2ui xor_(final Vec2ui a, final Vec2ui b) {
        return xor(new Vec2ui(), a, b.x.value, b.y.value);
    }

    public static Vec2ui xor(final Vec2ui a, final UInt b) {
        return xor(a, a, b.value, b.value);
    }

    public static Vec2ui xor(final Vec2ui a, final int b) {
        return xor(a, a, b, b);
    }

    public static Vec2ui xor(final Vec2ui a, final Vec2ui b) {
        return xor(a, a, b.x.value, b.y.value);
    }

    public static Vec2ui xor(final Vec2ui res, final Vec2ui a, final UInt b) {
        return xor(res, a, b.value, b.value);
    }

    public static Vec2ui xor(final Vec2ui res, final Vec2ui a, final int b) {
        return xor(res, a, b, b);
    }

    public static Vec2ui xor(final Vec2ui res, final Vec2ui a, final Vec2ui b) {
        return xor(res, a, b.x.value, b.y.value);
    }

    public static Vec2ui xor(final Vec2ui res, final Vec2ui a, final UInt bX, final UInt bY) {
        return xor(res, a, bX.value, bY.value);
    }

    public static Vec2ui xor(final Vec2ui res, final Vec2ui a, final int bX, final int bY) {
        res.x.value = a.x.value ^ bX;
        res.y.value = a.y.value ^ bY;
        return res;
    }

    public static Vec2ul xor_(final Vec2ul a, final ULong b) {
        return xor(new Vec2ul(), a, b.value, b.value);
    }

    public static Vec2ul xor_(final Vec2ul a, final long b) {
        return xor(new Vec2ul(), a, b, b);
    }

    public static Vec2ul xor_(final Vec2ul a, final Vec2ul b) {
        return xor(new Vec2ul(), a, b.x.value, b.y.value);
    }

    public static Vec2ul xor(final Vec2ul a, final ULong b) {
        return xor(a, a, b.value, b.value);
    }

    public static Vec2ul xor(final Vec2ul a, final long b) {
        return xor(a, a, b, b);
    }

    public static Vec2ul xor(final Vec2ul a, final Vec2ul b) {
        return xor(a, a, b.x.value, b.y.value);
    }

    public static Vec2ul xor(final Vec2ul res, final Vec2ul a, final ULong b) {
        return xor(res, a, b.value, b.value);
    }

    public static Vec2ul xor(final Vec2ul res, final Vec2ul a, final long b) {
        return xor(res, a, b, b);
    }

    public static Vec2ul xor(final Vec2ul res, final Vec2ul a, final Vec2ul b) {
        return xor(res, a, b.x.value, b.y.value);
    }

    public static Vec2ul xor(final Vec2ul res, final Vec2ul a, final ULong bX, final ULong bY) {
        return xor(res, a, bX.value, bY.value);
    }

    public static Vec2ul xor(final Vec2ul res, final Vec2ul a, final long bX, final long bY) {
        res.x.value = a.x.value ^ bX;
        res.y.value = a.y.value ^ bY;
        return res;
    }

    public static Vec2us xor_(final Vec2us a, final UShort b) {
        return xor(new Vec2us(), a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us xor_(final Vec2us a, final short b) {
        return xor(new Vec2us(), a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us xor_(final Vec2us a, final int b) {
        return xor(new Vec2us(), a, b, b);
    }

    public static Vec2us xor_(final Vec2us a, final Vec2us b) {
        return xor(new Vec2us(), a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us xor(final Vec2us a, final UShort b) {
        return xor(a, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us xor(final Vec2us a, final short b) {
        return xor(a, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us xor(final Vec2us a, final int b) {
        return xor(a, a, b, b);
    }

    public static Vec2us xor(final Vec2us a, final Vec2us b) {
        return xor(a, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us xor(final Vec2us res, final Vec2us a, final UShort b) {
        return xor(res, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us xor(final Vec2us res, final Vec2us a, final short b) {
        return xor(res, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us xor(final Vec2us res, final Vec2us a, final int b) {
        return xor(res, a, b, b);
    }

    public static Vec2us xor(final Vec2us res, final Vec2us a, final Vec2us b) {
        return xor(res, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us xor(final Vec2us res, final Vec2us a, final UShort bX, final UShort bY) {
        return xor(res, a, bX.value & 0xffff, bY.value & 0xffff);
    }

    public static Vec2us xor(final Vec2us res, final Vec2us a, final short bX, final short bY) {
        return xor(res, a, bX & 0xffff, bY & 0xffff);
    }

    public static Vec2us xor(final Vec2us res, final Vec2us a, final int bX, final int bY) {
        res.x.value = (short) ((a.x.value & 0xffff) ^ bX);
        res.y.value = (short) ((a.y.value & 0xffff) ^ bY);
        return res;
    }

    public static Vec3b xor_(final Vec3b a, final byte b) {
        return xor(new Vec3b(), a, b, b, b);
    }

    public static Vec3b xor_(final Vec3b a, final int b) {
        return xor(new Vec3b(), a, b, b, b);
    }

    public static Vec3b xor_(final Vec3b a, final Vec3b b) {
        return xor(new Vec3b(), a, b.x, b.y, b.z);
    }

    public static Vec3b xor(final Vec3b a, final byte b) {
        return xor(a, a, b, b, b);
    }

    public static Vec3b xor(final Vec3b a, final int b) {
        return xor(a, a, b, b, b);
    }

    public static Vec3b xor(final Vec3b a, final Vec3b b) {
        return xor(a, a, b.x, b.y, b.z);
    }

    public static Vec3b xor(final Vec3b res, final Vec3b a, final byte b) {
        return xor(res, a, b, b, b);
    }

    public static Vec3b xor(final Vec3b res, final Vec3b a, final int b) {
        return xor(res, a, b, b, b);
    }

    public static Vec3b xor(final Vec3b res, final Vec3b a, final Vec3b b) {
        return xor(res, a, b.x, b.y, b.z);
    }

    public static Vec3b xor(final Vec3b res, final Vec3b a, final byte bX, final byte bY, final byte bZ) {
        return xor(res, a, (int) bX, bY, bZ);
    }

    public static Vec3b xor(final Vec3b res, final Vec3b a, final int bX, final int bY, final int bZ) {
        res.x = (byte) (a.x ^ bX);
        res.y = (byte) (a.y ^ bY);
        res.z = (byte) (a.z ^ bZ);
        return res;
    }

    public static Vec3i xor_(final Vec3i a, final int b) {
        return xor(new Vec3i(), a, b, b, b);
    }

    public static Vec3i xor_(final Vec3i a, final Vec3i b) {
        return xor(new Vec3i(), a, b.x, b.y, b.z);
    }

    public static Vec3i xor(final Vec3i a, final int b) {
        return xor(a, a, b, b, b);
    }

    public static Vec3i xor(final Vec3i a, final Vec3i b) {
        return xor(a, a, b.x, b.y, b.z);
    }

    public static Vec3i xor(final Vec3i res, final Vec3i a, final int b) {
        return xor(res, a, b, b, b);
    }

    public static Vec3i xor(final Vec3i res, final Vec3i a, final Vec3i b) {
        return xor(res, a, b.x, b.y, b.z);
    }

    public static Vec3i xor(final Vec3i res, final Vec3i a, final int bX, final int bY, final int bZ) {
        res.x = a.x ^ bX;
        res.y = a.y ^ bY;
        res.z = a.z ^ bZ;
        return res;
    }

    public static Vec3l xor_(final Vec3l a, final long b) {
        return xor(new Vec3l(), a, b, b, b);
    }

    public static Vec3l xor_(final Vec3l a, final Vec3l b) {
        return xor(new Vec3l(), a, b.x, b.y, b.z);
    }

    public static Vec3l xor(final Vec3l a, final long b) {
        return xor(a, a, b, b, b);
    }

    public static Vec3l xor(final Vec3l a, final Vec3l b) {
        return xor(a, a, b.x, b.y, b.z);
    }

    public static Vec3l xor(final Vec3l res, final Vec3l a, final long b) {
        return xor(res, a, b, b, b);
    }

    public static Vec3l xor(final Vec3l res, final Vec3l a, final Vec3l b) {
        return xor(res, a, b.x, b.y, b.z);
    }

    public static Vec3l xor(final Vec3l res, final Vec3l a, final long bX, final long bY, final long bZ) {
        res.x = a.x ^ bX;
        res.y = a.y ^ bY;
        res.z = a.z ^ bZ;
        return res;
    }

    public static Vec3s xor_(final Vec3s a, final short b) {
        return xor(new Vec3s(), a, b, b, b);
    }

    public static Vec3s xor_(final Vec3s a, final int b) {
        return xor(new Vec3s(), a, b, b, b);
    }

    public static Vec3s xor_(final Vec3s a, final Vec3s b) {
        return xor(new Vec3s(), a, b.x, b.y, b.z);
    }

    public static Vec3s xor(final Vec3s a, final short b) {
        return xor(a, a, b, b, b);
    }

    public static Vec3s xor(final Vec3s a, final int b) {
        return xor(a, a, b, b, b);
    }

    public static Vec3s xor(final Vec3s a, final Vec3s b) {
        return xor(a, a, b.x, b.y, b.z);
    }

    public static Vec3s xor(final Vec3s res, final Vec3s a, final short b) {
        return xor(res, a, b, b, b);
    }

    public static Vec3s xor(final Vec3s res, final Vec3s a, final int b) {
        return xor(res, a, b, b, b);
    }

    public static Vec3s xor(final Vec3s res, final Vec3s a, final Vec3s b) {
        return xor(res, a, b.x, b.y, b.z);
    }

    public static Vec3s xor(final Vec3s res, final Vec3s a, final short bX, final short bY, final short bZ) {
        return xor(res, a, (int) bX, bY, bZ);
    }

    public static Vec3s xor(final Vec3s res, final Vec3s a, final int bX, final int bY, final int bZ) {
        res.x = (short) (a.x ^ bX);
        res.y = (short) (a.y ^ bY);
        res.z = (short) (a.z ^ bZ);
        return res;
    }

    public static Vec3ub xor_(final Vec3ub a, final UByte b) {
        return xor(new Vec3ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub xor_(final Vec3ub a, final byte b) {
        return xor(new Vec3ub(), a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub xor_(final Vec3ub a, final int b) {
        return xor(new Vec3ub(), a, b, b, b);
    }

    public static Vec3ub xor_(final Vec3ub a, final Vec3ub b) {
        return xor(new Vec3ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub xor(final Vec3ub a, final UByte b) {
        return xor(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub xor(final Vec3ub a, final byte b) {
        return xor(a, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub xor(final Vec3ub a, final int b) {
        return xor(a, a, b, b, b);
    }

    public static Vec3ub xor(final Vec3ub a, final Vec3ub b) {
        return xor(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub xor(final Vec3ub res, final Vec3ub a, final UByte b) {
        return xor(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub xor(final Vec3ub res, final Vec3ub a, final byte b) {
        return xor(res, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub xor(final Vec3ub res, final Vec3ub a, final int b) {
        return xor(res, a, b, b, b);
    }

    public static Vec3ub xor(final Vec3ub res, final Vec3ub a, final Vec3ub b) {
        return xor(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub xor(final Vec3ub res, final Vec3ub a, final UByte bX, final UByte bY, final UByte bZ) {
        return xor(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public static Vec3ub xor(final Vec3ub res, final Vec3ub a, final byte bX, final byte bY, final byte bZ) {
        return xor(res, a, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public static Vec3ub xor(final Vec3ub res, final Vec3ub a, final int bX, final int bY, final int bZ) {
        res.x.value = (byte) ((a.x.value & 0xff) ^ bX);
        res.y.value = (byte) ((a.y.value & 0xff) ^ bY);
        res.z.value = (byte) ((a.z.value & 0xff) ^ bZ);
        return res;
    }

    public static Vec3ui xor_(final Vec3ui a, final UInt b) {
        return xor(new Vec3ui(), a, b.value, b.value, b.value);
    }

    public static Vec3ui xor_(final Vec3ui a, final int b) {
        return xor(new Vec3ui(), a, b, b, b);
    }

    public static Vec3ui xor_(final Vec3ui a, final Vec3ui b) {
        return xor(new Vec3ui(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui xor(final Vec3ui a, final UInt b) {
        return xor(a, a, b.value, b.value, b.value);
    }

    public static Vec3ui xor(final Vec3ui a, final int b) {
        return xor(a, a, b, b, b);
    }

    public static Vec3ui xor(final Vec3ui a, final Vec3ui b) {
        return xor(a, a, b.x.value, b.y.value, b.y.value);
    }

    public static Vec3ui xor(final Vec3ui res, final Vec3ui a, final UInt b) {
        return xor(res, a, b.value, b.value, b.value);
    }

    public static Vec3ui xor(final Vec3ui res, final Vec3ui a, final int b) {
        return xor(res, a, b, b, b);
    }

    public static Vec3ui xor(final Vec3ui res, final Vec3ui a, final Vec3ui b) {
        return xor(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui xor(final Vec3ui res, final Vec3ui a, final UInt bX, final UInt bY, final UInt bZ) {
        return xor(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ui xor(final Vec3ui res, final Vec3ui a, final int bX, final int bY, final int bZ) {
        res.x.value = a.x.value ^ bX;
        res.y.value = a.y.value ^ bY;
        res.z.value = a.z.value ^ bZ;
        return res;
    }

    public static Vec3ul xor_(final Vec3ul a, final ULong b) {
        return xor(new Vec3ul(), a, b.value, b.value, b.value);
    }

    public static Vec3ul xor_(final Vec3ul a, final long b) {
        return xor(new Vec3ul(), a, b, b, b);
    }

    public static Vec3ul xor_(final Vec3ul a, final Vec3ul b) {
        return xor(new Vec3ul(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul xor(final Vec3ul a, final ULong b) {
        return xor(a, a, b.value, b.value, b.value);
    }

    public static Vec3ul xor(final Vec3ul a, final long b) {
        return xor(a, a, b, b, b);
    }

    public static Vec3ul xor(final Vec3ul a, final Vec3ul b) {
        return xor(a, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul xor(final Vec3ul res, final Vec3ul a, final ULong b) {
        return xor(res, a, b.value, b.value, b.value);
    }

    public static Vec3ul xor(final Vec3ul res, final Vec3ul a, final long b) {
        return xor(res, a, b, b, b);
    }

    public static Vec3ul xor(final Vec3ul res, final Vec3ul a, final Vec3ul b) {
        return xor(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul xor(final Vec3ul res, final Vec3ul a, final ULong bX, final ULong bY, final ULong bZ) {
        return xor(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ul xor(final Vec3ul res, final Vec3ul a, final long bX, final long bY, final long bZ) {
        res.x.value = a.x.value ^ bX;
        res.y.value = a.y.value ^ bY;
        res.z.value = a.z.value ^ bZ;
        return res;
    }

    public static Vec3us xor_(final Vec3us a, final UShort b) {
        return xor(new Vec3us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us xor_(final Vec3us a, final short b) {
        return xor(new Vec3us(), a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us xor_(final Vec3us a, final int b) {
        return xor(new Vec3us(), a, b, b, b);
    }

    public static Vec3us xor_(final Vec3us a, final Vec3us b) {
        return xor(new Vec3us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us xor(final Vec3us a, final UShort b) {
        return xor(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us xor(final Vec3us a, final short b) {
        return xor(a, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us xor(final Vec3us a, final int b) {
        return xor(a, a, b, b, b);
    }

    public static Vec3us xor(final Vec3us a, final Vec3us b) {
        return xor(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us xor(final Vec3us res, final Vec3us a, final UShort b) {
        return xor(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us xor(final Vec3us res, final Vec3us a, final short b) {
        return xor(res, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us xor(final Vec3us res, final Vec3us a, final int b) {
        return xor(res, a, b, b, b);
    }

    public static Vec3us xor(final Vec3us res, final Vec3us a, final Vec3us b) {
        return xor(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us xor(final Vec3us res, final Vec3us a, final UShort bX, final UShort bY, final UShort bZ) {
        return xor(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public static Vec3us xor(final Vec3us res, final Vec3us a, final short bX, final short bY, final short bZ) {
        return xor(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public static Vec3us xor(final Vec3us res, final Vec3us a, final int bX, final int bY, final int bZ) {
        res.x.value = (short) ((a.x.value & 0xffff) ^ bX);
        res.y.value = (short) ((a.y.value & 0xffff) ^ bY);
        res.z.value = (short) ((a.z.value & 0xffff) ^ bZ);
        return res;
    }

    public static Vec4b xor_(final Vec4b a, final byte b) {
        return xor(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b xor_(final Vec4b a, final int b) {
        return xor(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b xor_(final Vec4b a, final Vec4b b) {
        return xor(new Vec4b(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b xor(final Vec4b a, final byte b) {
        return xor(a, a, b, b, b, b);
    }

    public static Vec4b xor(final Vec4b a, final int b) {
        return xor(a, a, b, b, b, b);
    }

    public static Vec4b xor(final Vec4b a, final Vec4b b) {
        return xor(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b xor(final Vec4b res, final Vec4b a, final byte b) {
        return xor(res, a, b, b, b, b);
    }

    public static Vec4b xor(final Vec4b res, final Vec4b a, final int b) {
        return xor(res, a, b, b, b, b);
    }

    public static Vec4b xor(final Vec4b res, final Vec4b a, final Vec4b b) {
        return xor(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b xor(final Vec4b res, final Vec4b a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return xor(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4b xor(final Vec4b res, final Vec4b a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (byte) (a.x ^ bX);
        res.y = (byte) (a.y ^ bY);
        res.z = (byte) (a.z ^ bZ);
        res.w = (byte) (a.w ^ bW);
        return res;
    }

    public static Vec4i xor_(final Vec4i a, final int b) {
        return xor(new Vec4i(), a, b, b, b, b);
    }

    public static Vec4i xor_(final Vec4i a, final Vec4i b) {
        return xor(new Vec4i(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i xor(final Vec4i a, final int b) {
        return xor(a, a, b, b, b, b);
    }

    public static Vec4i xor(final Vec4i a, final Vec4i b) {
        return xor(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i xor(final Vec4i res, final Vec4i a, final int b) {
        return xor(res, a, b, b, b, b);
    }

    public static Vec4i xor(final Vec4i res, final Vec4i a, final Vec4i b) {
        return xor(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i xor(final Vec4i res, final Vec4i a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = a.x ^ bX;
        res.y = a.y ^ bY;
        res.z = a.z ^ bZ;
        res.w = a.w ^ bW;
        return res;
    }

    public static Vec4l xor_(final Vec4l a, final long b) {
        return xor(new Vec4l(), a, b, b, b, b);
    }

    public static Vec4l xor_(final Vec4l a, final Vec4l b) {
        return xor(new Vec4l(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l xor(final Vec4l a, final long b) {
        return xor(a, a, b, b, b, b);
    }

    public static Vec4l xor(final Vec4l a, final Vec4l b) {
        return xor(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l xor(final Vec4l res, final Vec4l a, final long b) {
        return xor(res, a, b, b, b, b);
    }

    public static Vec4l xor(final Vec4l res, final Vec4l a, final Vec4l b) {
        return xor(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l xor(final Vec4l res, final Vec4l a, final long bX, final long bY, final long bZ, final long bW) {
        res.x = a.x ^ bX;
        res.y = a.y ^ bY;
        res.z = a.z ^ bZ;
        res.w = a.w ^ bW;
        return res;
    }

    public static Vec4s xor_(final Vec4s a, final short b) {
        return xor(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s xor_(final Vec4s a, final int b) {
        return xor(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s xor_(final Vec4s a, final Vec4s b) {
        return xor(new Vec4s(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s xor(final Vec4s a, final short b) {
        return xor(a, a, b, b, b, b);
    }

    public static Vec4s xor(final Vec4s a, final int b) {
        return xor(a, a, b, b, b, b);
    }

    public static Vec4s xor(final Vec4s a, final Vec4s b) {
        return xor(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s xor(final Vec4s res, final Vec4s a, final short b) {
        return xor(res, a, b, b, b, b);
    }

    public static Vec4s xor(final Vec4s res, final Vec4s a, final int b) {
        return xor(res, a, b, b, b, b);
    }

    public static Vec4s xor(final Vec4s res, final Vec4s a, final Vec4s b) {
        return xor(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s xor(final Vec4s res, final Vec4s a, final short bX, final short bY, final short bZ, final short bW) {
        return xor(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4s xor(final Vec4s res, final Vec4s a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (short) (a.x ^ bX);
        res.y = (short) (a.y ^ bY);
        res.z = (short) (a.z ^ bZ);
        res.w = (short) (a.w ^ bW);
        return res;
    }

    public static Vec4ub xor_(final Vec4ub a, final UByte b) {
        return xor(new Vec4ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub xor_(final Vec4ub a, final byte b) {
        return xor(new Vec4ub(), a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub xor_(final Vec4ub a, final int b) {
        return xor(new Vec4ub(), a, b, b, b, b);
    }

    public static Vec4ub xor_(final Vec4ub a, final Vec4ub b) {
        return xor(new Vec4ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub xor(final Vec4ub a, final UByte b) {
        return xor(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub xor(final Vec4ub a, final byte b) {
        return xor(a, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub xor(final Vec4ub a, final int b) {
        return xor(a, a, b, b, b, b);
    }

    public static Vec4ub xor(final Vec4ub a, final Vec4ub b) {
        return xor(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub xor(final Vec4ub res, final Vec4ub a, final UByte b) {
        return xor(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub xor(final Vec4ub res, final Vec4ub a, final byte b) {
        return xor(res, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub xor(final Vec4ub res, final Vec4ub a, final int b) {
        return xor(res, a, b, b, b, b);
    }

    public static Vec4ub xor(final Vec4ub res, final Vec4ub a, final Vec4ub b) {
        return xor(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub xor(final Vec4ub res, final Vec4ub a, final UByte bX, final UByte bY, final UByte bZ, final UByte bW) {
        return xor(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bZ.value & 0xff);
    }

    public static Vec4ub xor(final Vec4ub res, final Vec4ub a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return xor(res, a, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public static Vec4ub xor(final Vec4ub res, final Vec4ub a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (byte) ((a.x.value & 0xff) ^ bX);
        res.y.value = (byte) ((a.y.value & 0xff) ^ bY);
        res.z.value = (byte) ((a.z.value & 0xff) ^ bZ);
        res.w.value = (byte) ((a.w.value & 0xff) ^ bW);
        return res;
    }

    public static Vec4ui xor_(final Vec4ui a, final UInt b) {
        return xor(new Vec4ui(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui xor_(final Vec4ui a, final int b) {
        return xor(new Vec4ui(), a, b, b, b, b);
    }

    public static Vec4ui xor_(final Vec4ui a, final Vec4ui b) {
        return xor(new Vec4ui(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui xor(final Vec4ui a, final UInt b) {
        return xor(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui xor(final Vec4ui a, final int b) {
        return xor(a, a, b, b, b, b);
    }

    public static Vec4ui xor(final Vec4ui a, final Vec4ui b) {
        return xor(a, a, b.x.value, b.y.value, b.y.value, b.w.value);
    }

    public static Vec4ui xor(final Vec4ui res, final Vec4ui a, final UInt b) {
        return xor(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui xor(final Vec4ui res, final Vec4ui a, final int b) {
        return xor(res, a, b, b, b, b);
    }

    public static Vec4ui xor(final Vec4ui res, final Vec4ui a, final Vec4ui b) {
        return xor(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui xor(final Vec4ui res, final Vec4ui a, final UInt bX, final UInt bY, final UInt bZ, final UInt bW) {
        return xor(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ui xor(final Vec4ui res, final Vec4ui a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = a.x.value ^ bX;
        res.y.value = a.y.value ^ bY;
        res.z.value = a.z.value ^ bZ;
        res.w.value = a.w.value ^ bW;
        return res;
    }

    public static Vec4ul xor_(final Vec4ul a, final ULong b) {
        return xor(new Vec4ul(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul xor_(final Vec4ul a, final long b) {
        return xor(new Vec4ul(), a, b, b, b, b);
    }

    public static Vec4ul xor_(final Vec4ul a, final Vec4ul b) {
        return xor(new Vec4ul(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul xor(final Vec4ul a, final ULong b) {
        return xor(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul xor(final Vec4ul a, final long b) {
        return xor(a, a, b, b, b, b);
    }

    public static Vec4ul xor(final Vec4ul a, final Vec4ul b) {
        return xor(a, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul xor(final Vec4ul res, final Vec4ul a, final ULong b) {
        return xor(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul xor(final Vec4ul res, final Vec4ul a, final long b) {
        return xor(res, a, b, b, b, b);
    }

    public static Vec4ul xor(final Vec4ul res, final Vec4ul a, final Vec4ul b) {
        return xor(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul xor(final Vec4ul res, final Vec4ul a, final ULong bX, final ULong bY, final ULong bZ, final ULong bW) {
        return xor(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ul xor(final Vec4ul res, final Vec4ul a, final long bX, final long bY, final long bZ, final long bW) {
        res.x.value = a.x.value ^ bX;
        res.y.value = a.y.value ^ bY;
        res.z.value = a.z.value ^ bZ;
        res.w.value = a.w.value ^ bW;
        return res;
    }

    public static Vec4us xor_(final Vec4us a, final UShort b) {
        return xor(new Vec4us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us xor_(final Vec4us a, final short b) {
        return xor(new Vec4us(), a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us xor_(final Vec4us a, final int b) {
        return xor(new Vec4us(), a, b, b, b, b);
    }

    public static Vec4us xor_(final Vec4us a, final Vec4us b) {
        return xor(new Vec4us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us xor(final Vec4us a, final UShort b) {
        return xor(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us xor(final Vec4us a, final short b) {
        return xor(a, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us xor(final Vec4us a, final int b) {
        return xor(a, a, b, b, b, b);
    }

    public static Vec4us xor(final Vec4us a, final Vec4us b) {
        return xor(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us xor(final Vec4us res, final Vec4us a, final UShort b) {
        return xor(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us xor(final Vec4us res, final Vec4us a, final short b) {
        return xor(res, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us xor(final Vec4us res, final Vec4us a, final int b) {
        return xor(res, a, b, b, b, b);
    }

    public static Vec4us xor(final Vec4us res, final Vec4us a, final Vec4us b) {
        return xor(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us xor(final Vec4us res, final Vec4us a, final UShort bX, final UShort bY, final UShort bZ, final UShort bW) {
        return xor(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public static Vec4us xor(final Vec4us res, final Vec4us a, final short bX, final short bY, final short bZ, final short bW) {
        return xor(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public static Vec4us xor(final Vec4us res, final Vec4us a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (short) ((a.x.value & 0xffff) ^ bX);
        res.y.value = (short) ((a.y.value & 0xffff) ^ bY);
        res.z.value = (short) ((a.z.value & 0xffff) ^ bZ);
        res.w.value = (short) ((a.w.value & 0xffff) ^ bW);
        return res;
    }

    public static Vec2b shL_(final Vec2b a, final byte b) {
        return shL(new Vec2b(), a, (int) b, b);
    }

    public static Vec2b shL_(final Vec2b a, final int b) {
        return shL(new Vec2b(), a, b, b);
    }

    public static Vec2b shL_(final Vec2b a, final Vec2b b) {
        return shL(new Vec2b(), a, (int) b.x, b.y);
    }

    public static Vec2b shL(final Vec2b a, final byte b) {
        return shL(a, a, (int) b, b);
    }

    public static Vec2b shL(final Vec2b a, final int b) {
        return shL(a, a, b, b);
    }

    public static Vec2b shL(final Vec2b a, final Vec2b b) {
        return shL(a, a, (int) b.x, b.y);
    }

    public static Vec2b shL(final Vec2b res, final Vec2b a, final byte b) {
        return shL(res, a, (int) b, b);
    }

    public static Vec2b shL(final Vec2b res, final Vec2b a, final int b) {
        return shL(res, a, b, b);
    }

    public static Vec2b shL(final Vec2b res, final Vec2b a, final Vec2b b) {
        return shL(res, a, (int) b.x, b.y);
    }

    public static Vec2b shL(final Vec2b res, final Vec2b a, final byte bX, final byte bY) {
        return shL(res, a, (int) bX, bY);
    }

    public static Vec2b shL(final Vec2b res, final Vec2b a, final int bX, final int bY) {
        res.x = (byte) (a.x << bX);
        res.y = (byte) (a.y << bY);
        return res;
    }

    public static Vec2i shL_(final Vec2i a, final int b) {
        return shL(new Vec2i(), a, b, b);
    }

    public static Vec2i shL_(final Vec2i a, final Vec2i b) {
        return shL(new Vec2i(), a, b.x, b.y);
    }

    public static Vec2i shL(final Vec2i a, final int b) {
        return shL(a, a, b, b);
    }

    public static Vec2i shL(final Vec2i a, final Vec2i b) {
        return shL(a, a, b.x, b.y);
    }

    public static Vec2i shL(final Vec2i res, final Vec2i a, final int b) {
        return shL(res, a, b, b);
    }

    public static Vec2i shL(final Vec2i res, final Vec2i a, final Vec2i b) {
        return shL(res, a, b.x, b.y);
    }

    public static Vec2i shL(final Vec2i res, final Vec2i a, final int bX, final int bY) {
        res.x = a.x << bX;
        res.y = a.y << bY;
        return res;
    }

    public static Vec2l shL_(final Vec2l a, final long b) {
        return shL(new Vec2l(), a, b, b);
    }

    public static Vec2l shL_(final Vec2l a, final Vec2l b) {
        return shL(new Vec2l(), a, b.x, b.y);
    }

    public static Vec2l shL(final Vec2l a, final long b) {
        return shL(a, a, b, b);
    }

    public static Vec2l shL(final Vec2l a, final Vec2l b) {
        return shL(a, a, b.x, b.y);
    }

    public static Vec2l shL(final Vec2l res, final Vec2l a, final long b) {
        return shL(res, a, b, b);
    }

    public static Vec2l shL(final Vec2l res, final Vec2l a, final Vec2l b) {
        return shL(res, a, b.x, b.y);
    }

    public static Vec2l shL(final Vec2l res, final Vec2l a, final long bX, final long bY) {
        res.x = a.x << bX;
        res.y = a.y << bY;
        return res;
    }

    public static Vec2s shL_(final Vec2s a, final short b) {
        return shL(new Vec2s(), a, (int) b, b);
    }

    public static Vec2s shL_(final Vec2s a, final int b) {
        return shL(new Vec2s(), a, b, b);
    }

    public static Vec2s shL_(final Vec2s a, final Vec2s b) {
        return shL(new Vec2s(), a, (int) b.x, b.y);
    }

    public static Vec2s shL(final Vec2s a, final short b) {
        return shL(a, a, (int) b, b);
    }

    public static Vec2s shL(final Vec2s a, final int b) {
        return shL(a, a, b, b);
    }

    public static Vec2s shL(final Vec2s a, final Vec2s b) {
        return shL(a, a, (int) b.x, b.y);
    }

    public static Vec2s shL(final Vec2s res, final Vec2s a, final short b) {
        return shL(res, a, (int) b, b);
    }

    public static Vec2s shL(final Vec2s res, final Vec2s a, final int b) {
        return shL(res, a, b, b);
    }

    public static Vec2s shL(final Vec2s res, final Vec2s a, final Vec2s b) {
        return shL(res, a, (int) b.x, b.y);
    }

    public static Vec2s shL(final Vec2s res, final Vec2s a, final short bX, final short bY) {
        return shL(res, a, (int) bX, bY);
    }

    public static Vec2s shL(final Vec2s res, final Vec2s a, final int bX, final int bY) {
        res.x = (short) (a.x << bX);
        res.y = (short) (a.y << bY);
        return res;
    }

    public static Vec2ub shL_(final Vec2ub a, final UByte b) {
        return shL(new Vec2ub(), a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub shL_(final Vec2ub a, final byte b) {
        return shL(new Vec2ub(), a, b & 0xff, b & 0xff);
    }

    public static Vec2ub shL_(final Vec2ub a, final int b) {
        return shL(new Vec2ub(), a, b, b);
    }

    public static Vec2ub shL_(final Vec2ub a, final Vec2ub b) {
        return shL(new Vec2ub(), a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub shL(final Vec2ub a, final UByte b) {
        return shL(a, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub shL(final Vec2ub a, final byte b) {
        return shL(a, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub shL(final Vec2ub a, final int b) {
        return shL(a, a, b, b);
    }

    public static Vec2ub shL(final Vec2ub a, final Vec2ub b) {
        return shL(a, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub shL(final Vec2ub res, final Vec2ub a, final UByte b) {
        return shL(res, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub shL(final Vec2ub res, final Vec2ub a, final byte b) {
        return shL(res, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub shL(final Vec2ub res, final Vec2ub a, final int b) {
        return shL(res, a, b, b);
    }

    public static Vec2ub shL(final Vec2ub res, final Vec2ub a, final Vec2ub b) {
        return shL(res, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub shL(final Vec2ub res, final Vec2ub a, final UByte bX, final UByte bY) {
        return shL(res, a, bX.value & 0xff, bY.value & 0xff);
    }

    public static Vec2ub shL(final Vec2ub res, final Vec2ub a, final byte bX, final byte bY) {
        return shL(res, a, bX & 0xff, bY & 0xff);
    }

    public static Vec2ub shL(final Vec2ub res, final Vec2ub a, final int bX, final int bY) {
        res.x.value = (byte) ((a.x.value & 0xff) << bX);
        res.y.value = (byte) ((a.y.value & 0xff) << bY);
        return res;
    }

    public static Vec2ui shL_(final Vec2ui a, final UInt b) {
        return shL(new Vec2ui(), a, b.value, b.value);
    }

    public static Vec2ui shL_(final Vec2ui a, final int b) {
        return shL(new Vec2ui(), a, b, b);
    }

    public static Vec2ui shL_(final Vec2ui a, final Vec2ui b) {
        return shL(new Vec2ui(), a, b.x.value, b.y.value);
    }

    public static Vec2ui shL(final Vec2ui a, final UInt b) {
        return shL(a, a, b.value, b.value);
    }

    public static Vec2ui shL(final Vec2ui a, final int b) {
        return shL(a, a, b, b);
    }

    public static Vec2ui shL(final Vec2ui a, final Vec2ui b) {
        return shL(a, a, b.x.value, b.y.value);
    }

    public static Vec2ui shL(final Vec2ui res, final Vec2ui a, final UInt b) {
        return shL(res, a, b.value, b.value);
    }

    public static Vec2ui shL(final Vec2ui res, final Vec2ui a, final int b) {
        return shL(res, a, b, b);
    }

    public static Vec2ui shL(final Vec2ui res, final Vec2ui a, final Vec2ui b) {
        return shL(res, a, b.x.value, b.y.value);
    }

    public static Vec2ui shL(final Vec2ui res, final Vec2ui a, final UInt bX, final UInt bY) {
        return shL(res, a, bX.value, bY.value);
    }

    public static Vec2ui shL(final Vec2ui res, final Vec2ui a, final int bX, final int bY) {
        res.x.value = a.x.value << bX;
        res.y.value = a.y.value << bY;
        return res;
    }

    public static Vec2ul shL_(final Vec2ul a, final ULong b) {
        return shL(new Vec2ul(), a, b.value, b.value);
    }

    public static Vec2ul shL_(final Vec2ul a, final long b) {
        return shL(new Vec2ul(), a, b, b);
    }

    public static Vec2ul shL_(final Vec2ul a, final Vec2ul b) {
        return shL(new Vec2ul(), a, b.x.value, b.y.value);
    }

    public static Vec2ul shL(final Vec2ul a, final ULong b) {
        return shL(a, a, b.value, b.value);
    }

    public static Vec2ul shL(final Vec2ul a, final long b) {
        return shL(a, a, b, b);
    }

    public static Vec2ul shL(final Vec2ul a, final Vec2ul b) {
        return shL(a, a, b.x.value, b.y.value);
    }

    public static Vec2ul shL(final Vec2ul res, final Vec2ul a, final ULong b) {
        return shL(res, a, b.value, b.value);
    }

    public static Vec2ul shL(final Vec2ul res, final Vec2ul a, final long b) {
        return shL(res, a, b, b);
    }

    public static Vec2ul shL(final Vec2ul res, final Vec2ul a, final Vec2ul b) {
        return shL(res, a, b.x.value, b.y.value);
    }

    public static Vec2ul shL(final Vec2ul res, final Vec2ul a, final ULong bX, final ULong bY) {
        return shL(res, a, bX.value, bY.value);
    }

    public static Vec2ul shL(final Vec2ul res, final Vec2ul a, final long bX, final long bY) {
        res.x.value = a.x.value << bX;
        res.y.value = a.y.value << bY;
        return res;
    }

    public static Vec2us shL_(final Vec2us a, final UShort b) {
        return shL(new Vec2us(), a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us shL_(final Vec2us a, final short b) {
        return shL(new Vec2us(), a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us shL_(final Vec2us a, final int b) {
        return shL(new Vec2us(), a, b, b);
    }

    public static Vec2us shL_(final Vec2us a, final Vec2us b) {
        return shL(new Vec2us(), a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us shL(final Vec2us a, final UShort b) {
        return shL(a, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us shL(final Vec2us a, final short b) {
        return shL(a, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us shL(final Vec2us a, final int b) {
        return shL(a, a, b, b);
    }

    public static Vec2us shL(final Vec2us a, final Vec2us b) {
        return shL(a, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us shL(final Vec2us res, final Vec2us a, final UShort b) {
        return shL(res, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us shL(final Vec2us res, final Vec2us a, final short b) {
        return shL(res, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us shL(final Vec2us res, final Vec2us a, final int b) {
        return shL(res, a, b, b);
    }

    public static Vec2us shL(final Vec2us res, final Vec2us a, final Vec2us b) {
        return shL(res, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us shL(final Vec2us res, final Vec2us a, final UShort bX, final UShort bY) {
        return shL(res, a, bX.value & 0xffff, bY.value & 0xffff);
    }

    public static Vec2us shL(final Vec2us res, final Vec2us a, final short bX, final short bY) {
        return shL(res, a, bX & 0xffff, bY & 0xffff);
    }

    public static Vec2us shL(final Vec2us res, final Vec2us a, final int bX, final int bY) {
        res.x.value = (short) ((a.x.value & 0xffff) << bX);
        res.y.value = (short) ((a.y.value & 0xffff) << bY);
        return res;
    }

    public static Vec3b shL_(final Vec3b a, final byte b) {
        return shL(new Vec3b(), a, b, b, b);
    }

    public static Vec3b shL_(final Vec3b a, final int b) {
        return shL(new Vec3b(), a, b, b, b);
    }

    public static Vec3b shL_(final Vec3b a, final Vec3b b) {
        return shL(new Vec3b(), a, b.x, b.y, b.z);
    }

    public static Vec3b shL(final Vec3b a, final byte b) {
        return shL(a, a, b, b, b);
    }

    public static Vec3b shL(final Vec3b a, final int b) {
        return shL(a, a, b, b, b);
    }

    public static Vec3b shL(final Vec3b a, final Vec3b b) {
        return shL(a, a, b.x, b.y, b.z);
    }

    public static Vec3b shL(final Vec3b res, final Vec3b a, final byte b) {
        return shL(res, a, b, b, b);
    }

    public static Vec3b shL(final Vec3b res, final Vec3b a, final int b) {
        return shL(res, a, b, b, b);
    }

    public static Vec3b shL(final Vec3b res, final Vec3b a, final Vec3b b) {
        return shL(res, a, b.x, b.y, b.z);
    }

    public static Vec3b shL(final Vec3b res, final Vec3b a, final byte bX, final byte bY, final byte bZ) {
        return shL(res, a, (int) bX, bY, bZ);
    }

    public static Vec3b shL(final Vec3b res, final Vec3b a, final int bX, final int bY, final int bZ) {
        res.x = (byte) (a.x << bX);
        res.y = (byte) (a.y << bY);
        res.z = (byte) (a.z << bZ);
        return res;
    }

    public static Vec3i shL_(final Vec3i a, final int b) {
        return shL(new Vec3i(), a, b, b, b);
    }

    public static Vec3i shL_(final Vec3i a, final Vec3i b) {
        return shL(new Vec3i(), a, b.x, b.y, b.z);
    }

    public static Vec3i shL(final Vec3i a, final int b) {
        return shL(a, a, b, b, b);
    }

    public static Vec3i shL(final Vec3i a, final Vec3i b) {
        return shL(a, a, b.x, b.y, b.z);
    }

    public static Vec3i shL(final Vec3i res, final Vec3i a, final int b) {
        return shL(res, a, b, b, b);
    }

    public static Vec3i shL(final Vec3i res, final Vec3i a, final Vec3i b) {
        return shL(res, a, b.x, b.y, b.z);
    }

    public static Vec3i shL(final Vec3i res, final Vec3i a, final int bX, final int bY, final int bZ) {
        res.x = a.x << bX;
        res.y = a.y << bY;
        res.z = a.z << bZ;
        return res;
    }

    public static Vec3l shL_(final Vec3l a, final long b) {
        return shL(new Vec3l(), a, b, b, b);
    }

    public static Vec3l shL_(final Vec3l a, final Vec3l b) {
        return shL(new Vec3l(), a, b.x, b.y, b.z);
    }

    public static Vec3l shL(final Vec3l a, final long b) {
        return shL(a, a, b, b, b);
    }

    public static Vec3l shL(final Vec3l a, final Vec3l b) {
        return shL(a, a, b.x, b.y, b.z);
    }

    public static Vec3l shL(final Vec3l res, final Vec3l a, final long b) {
        return shL(res, a, b, b, b);
    }

    public static Vec3l shL(final Vec3l res, final Vec3l a, final Vec3l b) {
        return shL(res, a, b.x, b.y, b.z);
    }

    public static Vec3l shL(final Vec3l res, final Vec3l a, final long bX, final long bY, final long bZ) {
        res.x = a.x << bX;
        res.y = a.y << bY;
        res.z = a.z << bZ;
        return res;
    }

    public static Vec3s shL_(final Vec3s a, final short b) {
        return shL(new Vec3s(), a, b, b, b);
    }

    public static Vec3s shL_(final Vec3s a, final int b) {
        return shL(new Vec3s(), a, b, b, b);
    }

    public static Vec3s shL_(final Vec3s a, final Vec3s b) {
        return shL(new Vec3s(), a, b.x, b.y, b.z);
    }

    public static Vec3s shL(final Vec3s a, final short b) {
        return shL(a, a, b, b, b);
    }

    public static Vec3s shL(final Vec3s a, final int b) {
        return shL(a, a, b, b, b);
    }

    public static Vec3s shL(final Vec3s a, final Vec3s b) {
        return shL(a, a, b.x, b.y, b.z);
    }

    public static Vec3s shL(final Vec3s res, final Vec3s a, final short b) {
        return shL(res, a, b, b, b);
    }

    public static Vec3s shL(final Vec3s res, final Vec3s a, final int b) {
        return shL(res, a, b, b, b);
    }

    public static Vec3s shL(final Vec3s res, final Vec3s a, final Vec3s b) {
        return shL(res, a, b.x, b.y, b.z);
    }

    public static Vec3s shL(final Vec3s res, final Vec3s a, final short bX, final short bY, final short bZ) {
        return shL(res, a, (int) bX, bY, bZ);
    }

    public static Vec3s shL(final Vec3s res, final Vec3s a, final int bX, final int bY, final int bZ) {
        res.x = (short) (a.x << bX);
        res.y = (short) (a.y << bY);
        res.z = (short) (a.z << bZ);
        return res;
    }

    public static Vec3ub shL_(final Vec3ub a, final UByte b) {
        return shL(new Vec3ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub shL_(final Vec3ub a, final byte b) {
        return shL(new Vec3ub(), a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub shL_(final Vec3ub a, final int b) {
        return shL(new Vec3ub(), a, b, b, b);
    }

    public static Vec3ub shL_(final Vec3ub a, final Vec3ub b) {
        return shL(new Vec3ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub shL(final Vec3ub a, final UByte b) {
        return shL(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub shL(final Vec3ub a, final byte b) {
        return shL(a, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub shL(final Vec3ub a, final int b) {
        return shL(a, a, b, b, b);
    }

    public static Vec3ub shL(final Vec3ub a, final Vec3ub b) {
        return shL(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub shL(final Vec3ub res, final Vec3ub a, final UByte b) {
        return shL(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub shL(final Vec3ub res, final Vec3ub a, final byte b) {
        return shL(res, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub shL(final Vec3ub res, final Vec3ub a, final int b) {
        return shL(res, a, b, b, b);
    }

    public static Vec3ub shL(final Vec3ub res, final Vec3ub a, final Vec3ub b) {
        return shL(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub shL(final Vec3ub res, final Vec3ub a, final UByte bX, final UByte bY, final UByte bZ) {
        return shL(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public static Vec3ub shL(final Vec3ub res, final Vec3ub a, final byte bX, final byte bY, final byte bZ) {
        return shL(res, a, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public static Vec3ub shL(final Vec3ub res, final Vec3ub a, final int bX, final int bY, final int bZ) {
        res.x.value = (byte) ((a.x.value & 0xff) << bX);
        res.y.value = (byte) ((a.y.value & 0xff) << bY);
        res.z.value = (byte) ((a.z.value & 0xff) << bZ);
        return res;
    }

    public static Vec3ui shL_(final Vec3ui a, final UInt b) {
        return shL(new Vec3ui(), a, b.value, b.value, b.value);
    }

    public static Vec3ui shL_(final Vec3ui a, final int b) {
        return shL(new Vec3ui(), a, b, b, b);
    }

    public static Vec3ui shL_(final Vec3ui a, final Vec3ui b) {
        return shL(new Vec3ui(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui shL(final Vec3ui a, final UInt b) {
        return shL(a, a, b.value, b.value, b.value);
    }

    public static Vec3ui shL(final Vec3ui a, final int b) {
        return shL(a, a, b, b, b);
    }

    public static Vec3ui shL(final Vec3ui a, final Vec3ui b) {
        return shL(a, a, b.x.value, b.y.value, b.y.value);
    }

    public static Vec3ui shL(final Vec3ui res, final Vec3ui a, final UInt b) {
        return shL(res, a, b.value, b.value, b.value);
    }

    public static Vec3ui shL(final Vec3ui res, final Vec3ui a, final int b) {
        return shL(res, a, b, b, b);
    }

    public static Vec3ui shL(final Vec3ui res, final Vec3ui a, final Vec3ui b) {
        return shL(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui shL(final Vec3ui res, final Vec3ui a, final UInt bX, final UInt bY, final UInt bZ) {
        return shL(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ui shL(final Vec3ui res, final Vec3ui a, final int bX, final int bY, final int bZ) {
        res.x.value = a.x.value << bX;
        res.y.value = a.y.value << bY;
        res.z.value = a.z.value << bZ;
        return res;
    }

    public static Vec3ul shL_(final Vec3ul a, final ULong b) {
        return shL(new Vec3ul(), a, b.value, b.value, b.value);
    }

    public static Vec3ul shL_(final Vec3ul a, final long b) {
        return shL(new Vec3ul(), a, b, b, b);
    }

    public static Vec3ul shL_(final Vec3ul a, final Vec3ul b) {
        return shL(new Vec3ul(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul shL(final Vec3ul a, final ULong b) {
        return shL(a, a, b.value, b.value, b.value);
    }

    public static Vec3ul shL(final Vec3ul a, final long b) {
        return shL(a, a, b, b, b);
    }

    public static Vec3ul shL(final Vec3ul a, final Vec3ul b) {
        return shL(a, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul shL(final Vec3ul res, final Vec3ul a, final ULong b) {
        return shL(res, a, b.value, b.value, b.value);
    }

    public static Vec3ul shL(final Vec3ul res, final Vec3ul a, final long b) {
        return shL(res, a, b, b, b);
    }

    public static Vec3ul shL(final Vec3ul res, final Vec3ul a, final Vec3ul b) {
        return shL(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul shL(final Vec3ul res, final Vec3ul a, final ULong bX, final ULong bY, final ULong bZ) {
        return shL(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ul shL(final Vec3ul res, final Vec3ul a, final long bX, final long bY, final long bZ) {
        res.x.value = a.x.value << bX;
        res.y.value = a.y.value << bY;
        res.z.value = a.z.value << bZ;
        return res;
    }

    public static Vec3us shL_(final Vec3us a, final UShort b) {
        return shL(new Vec3us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us shL_(final Vec3us a, final short b) {
        return shL(new Vec3us(), a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us shL_(final Vec3us a, final int b) {
        return shL(new Vec3us(), a, b, b, b);
    }

    public static Vec3us shL_(final Vec3us a, final Vec3us b) {
        return shL(new Vec3us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us shL(final Vec3us a, final UShort b) {
        return shL(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us shL(final Vec3us a, final short b) {
        return shL(a, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us shL(final Vec3us a, final int b) {
        return shL(a, a, b, b, b);
    }

    public static Vec3us shL(final Vec3us a, final Vec3us b) {
        return shL(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us shL(final Vec3us res, final Vec3us a, final UShort b) {
        return shL(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us shL(final Vec3us res, final Vec3us a, final short b) {
        return shL(res, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us shL(final Vec3us res, final Vec3us a, final int b) {
        return shL(res, a, b, b, b);
    }

    public static Vec3us shL(final Vec3us res, final Vec3us a, final Vec3us b) {
        return shL(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us shL(final Vec3us res, final Vec3us a, final UShort bX, final UShort bY, final UShort bZ) {
        return shL(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public static Vec3us shL(final Vec3us res, final Vec3us a, final short bX, final short bY, final short bZ) {
        return shL(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public static Vec3us shL(final Vec3us res, final Vec3us a, final int bX, final int bY, final int bZ) {
        res.x.value = (short) ((a.x.value & 0xffff) << bX);
        res.y.value = (short) ((a.y.value & 0xffff) << bY);
        res.z.value = (short) ((a.z.value & 0xffff) << bZ);
        return res;
    }

    public static Vec4b shL_(final Vec4b a, final byte b) {
        return shL(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b shL_(final Vec4b a, final int b) {
        return shL(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b shL_(final Vec4b a, final Vec4b b) {
        return shL(new Vec4b(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b shL(final Vec4b a, final byte b) {
        return shL(a, a, b, b, b, b);
    }

    public static Vec4b shL(final Vec4b a, final int b) {
        return shL(a, a, b, b, b, b);
    }

    public static Vec4b shL(final Vec4b a, final Vec4b b) {
        return shL(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b shL(final Vec4b res, final Vec4b a, final byte b) {
        return shL(res, a, b, b, b, b);
    }

    public static Vec4b shL(final Vec4b res, final Vec4b a, final int b) {
        return shL(res, a, b, b, b, b);
    }

    public static Vec4b shL(final Vec4b res, final Vec4b a, final Vec4b b) {
        return shL(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b shL(final Vec4b res, final Vec4b a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return shL(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4b shL(final Vec4b res, final Vec4b a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (byte) (a.x << bX);
        res.y = (byte) (a.y << bY);
        res.z = (byte) (a.z << bZ);
        res.w = (byte) (a.w << bW);
        return res;
    }

    public static Vec4i shL_(final Vec4i a, final int b) {
        return shL(new Vec4i(), a, b, b, b, b);
    }

    public static Vec4i shL_(final Vec4i a, final Vec4i b) {
        return shL(new Vec4i(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i shL(final Vec4i a, final int b) {
        return shL(a, a, b, b, b, b);
    }

    public static Vec4i shL(final Vec4i a, final Vec4i b) {
        return shL(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i shL(final Vec4i res, final Vec4i a, final int b) {
        return shL(res, a, b, b, b, b);
    }

    public static Vec4i shL(final Vec4i res, final Vec4i a, final Vec4i b) {
        return shL(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i shL(final Vec4i res, final Vec4i a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = a.x << bX;
        res.y = a.y << bY;
        res.z = a.z << bZ;
        res.w = a.w << bW;
        return res;
    }

    public static Vec4l shL_(final Vec4l a, final long b) {
        return shL(new Vec4l(), a, b, b, b, b);
    }

    public static Vec4l shL_(final Vec4l a, final Vec4l b) {
        return shL(new Vec4l(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l shL(final Vec4l a, final long b) {
        return shL(a, a, b, b, b, b);
    }

    public static Vec4l shL(final Vec4l a, final Vec4l b) {
        return shL(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l shL(final Vec4l res, final Vec4l a, final long b) {
        return shL(res, a, b, b, b, b);
    }

    public static Vec4l shL(final Vec4l res, final Vec4l a, final Vec4l b) {
        return shL(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l shL(final Vec4l res, final Vec4l a, final long bX, final long bY, final long bZ, final long bW) {
        res.x = a.x << bX;
        res.y = a.y << bY;
        res.z = a.z << bZ;
        res.w = a.w << bW;
        return res;
    }

    public static Vec4s shL_(final Vec4s a, final short b) {
        return shL(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s shL_(final Vec4s a, final int b) {
        return shL(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s shL_(final Vec4s a, final Vec4s b) {
        return shL(new Vec4s(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s shL(final Vec4s a, final short b) {
        return shL(a, a, b, b, b, b);
    }

    public static Vec4s shL(final Vec4s a, final int b) {
        return shL(a, a, b, b, b, b);
    }

    public static Vec4s shL(final Vec4s a, final Vec4s b) {
        return shL(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s shL(final Vec4s res, final Vec4s a, final short b) {
        return shL(res, a, b, b, b, b);
    }

    public static Vec4s shL(final Vec4s res, final Vec4s a, final int b) {
        return shL(res, a, b, b, b, b);
    }

    public static Vec4s shL(final Vec4s res, final Vec4s a, final Vec4s b) {
        return shL(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s shL(final Vec4s res, final Vec4s a, final short bX, final short bY, final short bZ, final short bW) {
        return shL(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4s shL(final Vec4s res, final Vec4s a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (short) (a.x << bX);
        res.y = (short) (a.y << bY);
        res.z = (short) (a.z << bZ);
        res.w = (short) (a.w << bW);
        return res;
    }

    public static Vec4ub shL_(final Vec4ub a, final UByte b) {
        return shL(new Vec4ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub shL_(final Vec4ub a, final byte b) {
        return shL(new Vec4ub(), a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub shL_(final Vec4ub a, final int b) {
        return shL(new Vec4ub(), a, b, b, b, b);
    }

    public static Vec4ub shL_(final Vec4ub a, final Vec4ub b) {
        return shL(new Vec4ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub shL(final Vec4ub a, final UByte b) {
        return shL(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub shL(final Vec4ub a, final byte b) {
        return shL(a, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub shL(final Vec4ub a, final int b) {
        return shL(a, a, b, b, b, b);
    }

    public static Vec4ub shL(final Vec4ub a, final Vec4ub b) {
        return shL(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub shL(final Vec4ub res, final Vec4ub a, final UByte b) {
        return shL(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub shL(final Vec4ub res, final Vec4ub a, final byte b) {
        return shL(res, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub shL(final Vec4ub res, final Vec4ub a, final int b) {
        return shL(res, a, b, b, b, b);
    }

    public static Vec4ub shL(final Vec4ub res, final Vec4ub a, final Vec4ub b) {
        return shL(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub shL(final Vec4ub res, final Vec4ub a, final UByte bX, final UByte bY, final UByte bZ, final UByte bW) {
        return shL(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bZ.value & 0xff);
    }

    public static Vec4ub shL(final Vec4ub res, final Vec4ub a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return shL(res, a, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public static Vec4ub shL(final Vec4ub res, final Vec4ub a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (byte) ((a.x.value & 0xff) << bX);
        res.y.value = (byte) ((a.y.value & 0xff) << bY);
        res.z.value = (byte) ((a.z.value & 0xff) << bZ);
        res.w.value = (byte) ((a.w.value & 0xff) << bW);
        return res;
    }

    public static Vec4ui shL_(final Vec4ui a, final UInt b) {
        return shL(new Vec4ui(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui shL_(final Vec4ui a, final int b) {
        return shL(new Vec4ui(), a, b, b, b, b);
    }

    public static Vec4ui shL_(final Vec4ui a, final Vec4ui b) {
        return shL(new Vec4ui(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui shL(final Vec4ui a, final UInt b) {
        return shL(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui shL(final Vec4ui a, final int b) {
        return shL(a, a, b, b, b, b);
    }

    public static Vec4ui shL(final Vec4ui a, final Vec4ui b) {
        return shL(a, a, b.x.value, b.y.value, b.y.value, b.w.value);
    }

    public static Vec4ui shL(final Vec4ui res, final Vec4ui a, final UInt b) {
        return shL(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui shL(final Vec4ui res, final Vec4ui a, final int b) {
        return shL(res, a, b, b, b, b);
    }

    public static Vec4ui shL(final Vec4ui res, final Vec4ui a, final Vec4ui b) {
        return shL(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui shL(final Vec4ui res, final Vec4ui a, final UInt bX, final UInt bY, final UInt bZ, final UInt bW) {
        return shL(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ui shL(final Vec4ui res, final Vec4ui a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = a.x.value << bX;
        res.y.value = a.y.value << bY;
        res.z.value = a.z.value << bZ;
        res.w.value = a.w.value << bW;
        return res;
    }

    public static Vec4ul shL_(final Vec4ul a, final ULong b) {
        return shL(new Vec4ul(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul shL_(final Vec4ul a, final long b) {
        return shL(new Vec4ul(), a, b, b, b, b);
    }

    public static Vec4ul shL_(final Vec4ul a, final Vec4ul b) {
        return shL(new Vec4ul(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul shL(final Vec4ul a, final ULong b) {
        return shL(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul shL(final Vec4ul a, final long b) {
        return shL(a, a, b, b, b, b);
    }

    public static Vec4ul shL(final Vec4ul a, final Vec4ul b) {
        return shL(a, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul shL(final Vec4ul res, final Vec4ul a, final ULong b) {
        return shL(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul shL(final Vec4ul res, final Vec4ul a, final long b) {
        return shL(res, a, b, b, b, b);
    }

    public static Vec4ul shL(final Vec4ul res, final Vec4ul a, final Vec4ul b) {
        return shL(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul shL(final Vec4ul res, final Vec4ul a, final ULong bX, final ULong bY, final ULong bZ, final ULong bW) {
        return shL(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ul shL(final Vec4ul res, final Vec4ul a, final long bX, final long bY, final long bZ, final long bW) {
        res.x.value = a.x.value << bX;
        res.y.value = a.y.value << bY;
        res.z.value = a.z.value << bZ;
        res.w.value = a.w.value << bW;
        return res;
    }

    public static Vec4us shL_(final Vec4us a, final UShort b) {
        return shL(new Vec4us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us shL_(final Vec4us a, final short b) {
        return shL(new Vec4us(), a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us shL_(final Vec4us a, final int b) {
        return shL(new Vec4us(), a, b, b, b, b);
    }

    public static Vec4us shL_(final Vec4us a, final Vec4us b) {
        return shL(new Vec4us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us shL(final Vec4us a, final UShort b) {
        return shL(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us shL(final Vec4us a, final short b) {
        return shL(a, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us shL(final Vec4us a, final int b) {
        return shL(a, a, b, b, b, b);
    }

    public static Vec4us shL(final Vec4us a, final Vec4us b) {
        return shL(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us shL(final Vec4us res, final Vec4us a, final UShort b) {
        return shL(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us shL(final Vec4us res, final Vec4us a, final short b) {
        return shL(res, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us shL(final Vec4us res, final Vec4us a, final int b) {
        return shL(res, a, b, b, b, b);
    }

    public static Vec4us shL(final Vec4us res, final Vec4us a, final Vec4us b) {
        return shL(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us shL(final Vec4us res, final Vec4us a, final UShort bX, final UShort bY, final UShort bZ, final UShort bW) {
        return shL(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public static Vec4us shL(final Vec4us res, final Vec4us a, final short bX, final short bY, final short bZ, final short bW) {
        return shL(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public static Vec4us shL(final Vec4us res, final Vec4us a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (short) ((a.x.value & 0xffff) << bX);
        res.y.value = (short) ((a.y.value & 0xffff) << bY);
        res.z.value = (short) ((a.z.value & 0xffff) << bZ);
        res.w.value = (short) ((a.w.value & 0xffff) << bW);
        return res;
    }

    public static Vec2b shR_(final Vec2b a, final byte b) {
        return shR(new Vec2b(), a, (int) b, b);
    }

    public static Vec2b shR_(final Vec2b a, final int b) {
        return shR(new Vec2b(), a, b, b);
    }

    public static Vec2b shR_(final Vec2b a, final Vec2b b) {
        return shR(new Vec2b(), a, (int) b.x, b.y);
    }

    public static Vec2b shR(final Vec2b a, final byte b) {
        return shR(a, a, (int) b, b);
    }

    public static Vec2b shR(final Vec2b a, final int b) {
        return shR(a, a, b, b);
    }

    public static Vec2b shR(final Vec2b a, final Vec2b b) {
        return shR(a, a, (int) b.x, b.y);
    }

    public static Vec2b shR(final Vec2b res, final Vec2b a, final byte b) {
        return shR(res, a, (int) b, b);
    }

    public static Vec2b shR(final Vec2b res, final Vec2b a, final int b) {
        return shR(res, a, b, b);
    }

    public static Vec2b shR(final Vec2b res, final Vec2b a, final Vec2b b) {
        return shR(res, a, (int) b.x, b.y);
    }

    public static Vec2b shR(final Vec2b res, final Vec2b a, final byte bX, final byte bY) {
        return shR(res, a, (int) bX, bY);
    }

    public static Vec2b shR(final Vec2b res, final Vec2b a, final int bX, final int bY) {
        res.x = (byte) (a.x >> bX);
        res.y = (byte) (a.y >> bY);
        return res;
    }

    public static Vec2i shR_(final Vec2i a, final int b) {
        return shR(new Vec2i(), a, b, b);
    }

    public static Vec2i shR_(final Vec2i a, final Vec2i b) {
        return shR(new Vec2i(), a, b.x, b.y);
    }

    public static Vec2i shR(final Vec2i a, final int b) {
        return shR(a, a, b, b);
    }

    public static Vec2i shR(final Vec2i a, final Vec2i b) {
        return shR(a, a, b.x, b.y);
    }

    public static Vec2i shR(final Vec2i res, final Vec2i a, final int b) {
        return shR(res, a, b, b);
    }

    public static Vec2i shR(final Vec2i res, final Vec2i a, final Vec2i b) {
        return shR(res, a, b.x, b.y);
    }

    public static Vec2i shR(final Vec2i res, final Vec2i a, final int bX, final int bY) {
        res.x = a.x >> bX;
        res.y = a.y >> bY;
        return res;
    }

    public static Vec2l shR_(final Vec2l a, final long b) {
        return shR(new Vec2l(), a, b, b);
    }

    public static Vec2l shR_(final Vec2l a, final Vec2l b) {
        return shR(new Vec2l(), a, b.x, b.y);
    }

    public static Vec2l shR(final Vec2l a, final long b) {
        return shR(a, a, b, b);
    }

    public static Vec2l shR(final Vec2l a, final Vec2l b) {
        return shR(a, a, b.x, b.y);
    }

    public static Vec2l shR(final Vec2l res, final Vec2l a, final long b) {
        return shR(res, a, b, b);
    }

    public static Vec2l shR(final Vec2l res, final Vec2l a, final Vec2l b) {
        return shR(res, a, b.x, b.y);
    }

    public static Vec2l shR(final Vec2l res, final Vec2l a, final long bX, final long bY) {
        res.x = a.x >> bX;
        res.y = a.y >> bY;
        return res;
    }

    public static Vec2s shR_(final Vec2s a, final short b) {
        return shR(new Vec2s(), a, (int) b, b);
    }

    public static Vec2s shR_(final Vec2s a, final int b) {
        return shR(new Vec2s(), a, b, b);
    }

    public static Vec2s shR_(final Vec2s a, final Vec2s b) {
        return shR(new Vec2s(), a, (int) b.x, b.y);
    }

    public static Vec2s shR(final Vec2s a, final short b) {
        return shR(a, a, (int) b, b);
    }

    public static Vec2s shR(final Vec2s a, final int b) {
        return shR(a, a, b, b);
    }

    public static Vec2s shR(final Vec2s a, final Vec2s b) {
        return shR(a, a, (int) b.x, b.y);
    }

    public static Vec2s shR(final Vec2s res, final Vec2s a, final short b) {
        return shR(res, a, (int) b, b);
    }

    public static Vec2s shR(final Vec2s res, final Vec2s a, final int b) {
        return shR(res, a, b, b);
    }

    public static Vec2s shR(final Vec2s res, final Vec2s a, final Vec2s b) {
        return shR(res, a, (int) b.x, b.y);
    }

    public static Vec2s shR(final Vec2s res, final Vec2s a, final short bX, final short bY) {
        return shR(res, a, (int) bX, bY);
    }

    public static Vec2s shR(final Vec2s res, final Vec2s a, final int bX, final int bY) {
        res.x = (short) (a.x >> bX);
        res.y = (short) (a.y >> bY);
        return res;
    }

    public static Vec2ub shR_(final Vec2ub a, final UByte b) {
        return shR(new Vec2ub(), a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub shR_(final Vec2ub a, final byte b) {
        return shR(new Vec2ub(), a, b & 0xff, b & 0xff);
    }

    public static Vec2ub shR_(final Vec2ub a, final int b) {
        return shR(new Vec2ub(), a, b, b);
    }

    public static Vec2ub shR_(final Vec2ub a, final Vec2ub b) {
        return shR(new Vec2ub(), a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub shR(final Vec2ub a, final UByte b) {
        return shR(a, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub shR(final Vec2ub a, final byte b) {
        return shR(a, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub shR(final Vec2ub a, final int b) {
        return shR(a, a, b, b);
    }

    public static Vec2ub shR(final Vec2ub a, final Vec2ub b) {
        return shR(a, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub shR(final Vec2ub res, final Vec2ub a, final UByte b) {
        return shR(res, a, b.value & 0xff, b.value & 0xff);
    }

    public static Vec2ub shR(final Vec2ub res, final Vec2ub a, final byte b) {
        return shR(res, a, b & 0xff, b & 0xff);
    }

    public static Vec2ub shR(final Vec2ub res, final Vec2ub a, final int b) {
        return shR(res, a, b, b);
    }

    public static Vec2ub shR(final Vec2ub res, final Vec2ub a, final Vec2ub b) {
        return shR(res, a, b.x.value & 0xff, b.y.value & 0xff);
    }

    public static Vec2ub shR(final Vec2ub res, final Vec2ub a, final UByte bX, final UByte bY) {
        return shR(res, a, bX.value & 0xff, bY.value & 0xff);
    }

    public static Vec2ub shR(final Vec2ub res, final Vec2ub a, final byte bX, final byte bY) {
        return shR(res, a, bX & 0xff, bY & 0xff);
    }

    public static Vec2ub shR(final Vec2ub res, final Vec2ub a, final int bX, final int bY) {
        res.x.value = (byte) ((a.x.value & 0xff) >>> bX);
        res.y.value = (byte) ((a.y.value & 0xff) >>> bY);
        return res;
    }

    public static Vec2ui shR_(final Vec2ui a, final UInt b) {
        return shR(new Vec2ui(), a, b.value, b.value);
    }

    public static Vec2ui shR_(final Vec2ui a, final int b) {
        return shR(new Vec2ui(), a, b, b);
    }

    public static Vec2ui shR_(final Vec2ui a, final Vec2ui b) {
        return shR(new Vec2ui(), a, b.x.value, b.y.value);
    }

    public static Vec2ui shR(final Vec2ui a, final UInt b) {
        return shR(a, a, b.value, b.value);
    }

    public static Vec2ui shR(final Vec2ui a, final int b) {
        return shR(a, a, b, b);
    }

    public static Vec2ui shR(final Vec2ui a, final Vec2ui b) {
        return shR(a, a, b.x.value, b.y.value);
    }

    public static Vec2ui shR(final Vec2ui res, final Vec2ui a, final UInt b) {
        return shR(res, a, b.value, b.value);
    }

    public static Vec2ui shR(final Vec2ui res, final Vec2ui a, final int b) {
        return shR(res, a, b, b);
    }

    public static Vec2ui shR(final Vec2ui res, final Vec2ui a, final Vec2ui b) {
        return shR(res, a, b.x.value, b.y.value);
    }

    public static Vec2ui shR(final Vec2ui res, final Vec2ui a, final UInt bX, final UInt bY) {
        return shR(res, a, bX.value, bY.value);
    }

    public static Vec2ui shR(final Vec2ui res, final Vec2ui a, final int bX, final int bY) {
        res.x.value = a.x.value >>> bX;
        res.y.value = a.y.value >>> bY;
        return res;
    }

    public static Vec2ul shR_(final Vec2ul a, final ULong b) {
        return shR(new Vec2ul(), a, b.value, b.value);
    }

    public static Vec2ul shR_(final Vec2ul a, final long b) {
        return shR(new Vec2ul(), a, b, b);
    }

    public static Vec2ul shR_(final Vec2ul a, final Vec2ul b) {
        return shR(new Vec2ul(), a, b.x.value, b.y.value);
    }

    public static Vec2ul shR(final Vec2ul a, final ULong b) {
        return shR(a, a, b.value, b.value);
    }

    public static Vec2ul shR(final Vec2ul a, final long b) {
        return shR(a, a, b, b);
    }

    public static Vec2ul shR(final Vec2ul a, final Vec2ul b) {
        return shR(a, a, b.x.value, b.y.value);
    }

    public static Vec2ul shR(final Vec2ul res, final Vec2ul a, final ULong b) {
        return shR(res, a, b.value, b.value);
    }

    public static Vec2ul shR(final Vec2ul res, final Vec2ul a, final long b) {
        return shR(res, a, b, b);
    }

    public static Vec2ul shR(final Vec2ul res, final Vec2ul a, final Vec2ul b) {
        return shR(res, a, b.x.value, b.y.value);
    }

    public static Vec2ul shR(final Vec2ul res, final Vec2ul a, final ULong bX, final ULong bY) {
        return shR(res, a, bX.value, bY.value);
    }

    public static Vec2ul shR(final Vec2ul res, final Vec2ul a, final long bX, final long bY) {
        res.x.value = a.x.value >>> bX;
        res.y.value = a.y.value >>> bY;
        return res;
    }

    public static Vec2us shR_(final Vec2us a, final UShort b) {
        return shR(new Vec2us(), a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us shR_(final Vec2us a, final short b) {
        return shR(new Vec2us(), a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us shR_(final Vec2us a, final int b) {
        return shR(new Vec2us(), a, b, b);
    }

    public static Vec2us shR_(final Vec2us a, final Vec2us b) {
        return shR(new Vec2us(), a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us shR(final Vec2us a, final UShort b) {
        return shR(a, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us shR(final Vec2us a, final short b) {
        return shR(a, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us shR(final Vec2us a, final int b) {
        return shR(a, a, b, b);
    }

    public static Vec2us shR(final Vec2us a, final Vec2us b) {
        return shR(a, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us shR(final Vec2us res, final Vec2us a, final UShort b) {
        return shR(res, a, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec2us shR(final Vec2us res, final Vec2us a, final short b) {
        return shR(res, a, b & 0xffff, b & 0xffff);
    }

    public static Vec2us shR(final Vec2us res, final Vec2us a, final int b) {
        return shR(res, a, b, b);
    }

    public static Vec2us shR(final Vec2us res, final Vec2us a, final Vec2us b) {
        return shR(res, a, b.x.value & 0xffff, b.y.value & 0xffff);
    }

    public static Vec2us shR(final Vec2us res, final Vec2us a, final UShort bX, final UShort bY) {
        return shR(res, a, bX.value & 0xffff, bY.value & 0xffff);
    }

    public static Vec2us shR(final Vec2us res, final Vec2us a, final short bX, final short bY) {
        return shR(res, a, bX & 0xffff, bY & 0xffff);
    }

    public static Vec2us shR(final Vec2us res, final Vec2us a, final int bX, final int bY) {
        res.x.value = (short) ((a.x.value & 0xffff) >>> bX);
        res.y.value = (short) ((a.y.value & 0xffff) >>> bY);
        return res;
    }

    public static Vec3b shR_(final Vec3b a, final byte b) {
        return shR(new Vec3b(), a, b, b, b);
    }

    public static Vec3b shR_(final Vec3b a, final int b) {
        return shR(new Vec3b(), a, b, b, b);
    }

    public static Vec3b shR_(final Vec3b a, final Vec3b b) {
        return shR(new Vec3b(), a, b.x, b.y, b.z);
    }

    public static Vec3b shR(final Vec3b a, final byte b) {
        return shR(a, a, b, b, b);
    }

    public static Vec3b shR(final Vec3b a, final int b) {
        return shR(a, a, b, b, b);
    }

    public static Vec3b shR(final Vec3b a, final Vec3b b) {
        return shR(a, a, b.x, b.y, b.z);
    }

    public static Vec3b shR(final Vec3b res, final Vec3b a, final byte b) {
        return shR(res, a, b, b, b);
    }

    public static Vec3b shR(final Vec3b res, final Vec3b a, final int b) {
        return shR(res, a, b, b, b);
    }

    public static Vec3b shR(final Vec3b res, final Vec3b a, final Vec3b b) {
        return shR(res, a, b.x, b.y, b.z);
    }

    public static Vec3b shR(final Vec3b res, final Vec3b a, final byte bX, final byte bY, final byte bZ) {
        return shR(res, a, (int) bX, bY, bZ);
    }

    public static Vec3b shR(final Vec3b res, final Vec3b a, final int bX, final int bY, final int bZ) {
        res.x = (byte) (a.x >> bX);
        res.y = (byte) (a.y >> bY);
        res.z = (byte) (a.z >> bZ);
        return res;
    }

    public static Vec3i shR_(final Vec3i a, final int b) {
        return shR(new Vec3i(), a, b, b, b);
    }

    public static Vec3i shR_(final Vec3i a, final Vec3i b) {
        return shR(new Vec3i(), a, b.x, b.y, b.z);
    }

    public static Vec3i shR(final Vec3i a, final int b) {
        return shR(a, a, b, b, b);
    }

    public static Vec3i shR(final Vec3i a, final Vec3i b) {
        return shR(a, a, b.x, b.y, b.z);
    }

    public static Vec3i shR(final Vec3i res, final Vec3i a, final int b) {
        return shR(res, a, b, b, b);
    }

    public static Vec3i shR(final Vec3i res, final Vec3i a, final Vec3i b) {
        return shR(res, a, b.x, b.y, b.z);
    }

    public static Vec3i shR(final Vec3i res, final Vec3i a, final int bX, final int bY, final int bZ) {
        res.x = a.x >> bX;
        res.y = a.y >> bY;
        res.z = a.z >> bZ;
        return res;
    }

    public static Vec3l shR_(final Vec3l a, final long b) {
        return shR(new Vec3l(), a, b, b, b);
    }

    public static Vec3l shR_(final Vec3l a, final Vec3l b) {
        return shR(new Vec3l(), a, b.x, b.y, b.z);
    }

    public static Vec3l shR(final Vec3l a, final long b) {
        return shR(a, a, b, b, b);
    }

    public static Vec3l shR(final Vec3l a, final Vec3l b) {
        return shR(a, a, b.x, b.y, b.z);
    }

    public static Vec3l shR(final Vec3l res, final Vec3l a, final long b) {
        return shR(res, a, b, b, b);
    }

    public static Vec3l shR(final Vec3l res, final Vec3l a, final Vec3l b) {
        return shR(res, a, b.x, b.y, b.z);
    }

    public static Vec3l shR(final Vec3l res, final Vec3l a, final long bX, final long bY, final long bZ) {
        res.x = a.x >> bX;
        res.y = a.y >> bY;
        res.z = a.z >> bZ;
        return res;
    }

    public static Vec3s shR_(final Vec3s a, final short b) {
        return shR(new Vec3s(), a, b, b, b);
    }

    public static Vec3s shR_(final Vec3s a, final int b) {
        return shR(new Vec3s(), a, b, b, b);
    }

    public static Vec3s shR_(final Vec3s a, final Vec3s b) {
        return shR(new Vec3s(), a, b.x, b.y, b.z);
    }

    public static Vec3s shR(final Vec3s a, final short b) {
        return shR(a, a, b, b, b);
    }

    public static Vec3s shR(final Vec3s a, final int b) {
        return shR(a, a, b, b, b);
    }

    public static Vec3s shR(final Vec3s a, final Vec3s b) {
        return shR(a, a, b.x, b.y, b.z);
    }

    public static Vec3s shR(final Vec3s res, final Vec3s a, final short b) {
        return shR(res, a, b, b, b);
    }

    public static Vec3s shR(final Vec3s res, final Vec3s a, final int b) {
        return shR(res, a, b, b, b);
    }

    public static Vec3s shR(final Vec3s res, final Vec3s a, final Vec3s b) {
        return shR(res, a, b.x, b.y, b.z);
    }

    public static Vec3s shR(final Vec3s res, final Vec3s a, final short bX, final short bY, final short bZ) {
        return shR(res, a, (int) bX, bY, bZ);
    }

    public static Vec3s shR(final Vec3s res, final Vec3s a, final int bX, final int bY, final int bZ) {
        res.x = (short) (a.x >> bX);
        res.y = (short) (a.y >> bY);
        res.z = (short) (a.z >> bZ);
        return res;
    }

    public static Vec3ub shR_(final Vec3ub a, final UByte b) {
        return shR(new Vec3ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub shR_(final Vec3ub a, final byte b) {
        return shR(new Vec3ub(), a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub shR_(final Vec3ub a, final int b) {
        return shR(new Vec3ub(), a, b, b, b);
    }

    public static Vec3ub shR_(final Vec3ub a, final Vec3ub b) {
        return shR(new Vec3ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub shR(final Vec3ub a, final UByte b) {
        return shR(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub shR(final Vec3ub a, final byte b) {
        return shR(a, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub shR(final Vec3ub a, final int b) {
        return shR(a, a, b, b, b);
    }

    public static Vec3ub shR(final Vec3ub a, final Vec3ub b) {
        return shR(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub shR(final Vec3ub res, final Vec3ub a, final UByte b) {
        return shR(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec3ub shR(final Vec3ub res, final Vec3ub a, final byte b) {
        return shR(res, a, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec3ub shR(final Vec3ub res, final Vec3ub a, final int b) {
        return shR(res, a, b, b, b);
    }

    public static Vec3ub shR(final Vec3ub res, final Vec3ub a, final Vec3ub b) {
        return shR(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff);
    }

    public static Vec3ub shR(final Vec3ub res, final Vec3ub a, final UByte bX, final UByte bY, final UByte bZ) {
        return shR(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff);
    }

    public static Vec3ub shR(final Vec3ub res, final Vec3ub a, final byte bX, final byte bY, final byte bZ) {
        return shR(res, a, bX & 0xff, bY & 0xff, bZ & 0xff);
    }

    public static Vec3ub shR(final Vec3ub res, final Vec3ub a, final int bX, final int bY, final int bZ) {
        res.x.value = (byte) ((a.x.value & 0xff) >>> bX);
        res.y.value = (byte) ((a.y.value & 0xff) >>> bY);
        res.z.value = (byte) ((a.z.value & 0xff) >>> bZ);
        return res;
    }

    public static Vec3ui shR_(final Vec3ui a, final UInt b) {
        return shR(new Vec3ui(), a, b.value, b.value, b.value);
    }

    public static Vec3ui shR_(final Vec3ui a, final int b) {
        return shR(new Vec3ui(), a, b, b, b);
    }

    public static Vec3ui shR_(final Vec3ui a, final Vec3ui b) {
        return shR(new Vec3ui(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui shR(final Vec3ui a, final UInt b) {
        return shR(a, a, b.value, b.value, b.value);
    }

    public static Vec3ui shR(final Vec3ui a, final int b) {
        return shR(a, a, b, b, b);
    }

    public static Vec3ui shR(final Vec3ui a, final Vec3ui b) {
        return shR(a, a, b.x.value, b.y.value, b.y.value);
    }

    public static Vec3ui shR(final Vec3ui res, final Vec3ui a, final UInt b) {
        return shR(res, a, b.value, b.value, b.value);
    }

    public static Vec3ui shR(final Vec3ui res, final Vec3ui a, final int b) {
        return shR(res, a, b, b, b);
    }

    public static Vec3ui shR(final Vec3ui res, final Vec3ui a, final Vec3ui b) {
        return shR(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ui shR(final Vec3ui res, final Vec3ui a, final UInt bX, final UInt bY, final UInt bZ) {
        return shR(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ui shR(final Vec3ui res, final Vec3ui a, final int bX, final int bY, final int bZ) {
        res.x.value = a.x.value >>> bX;
        res.y.value = a.y.value >>> bY;
        res.z.value = a.z.value >>> bZ;
        return res;
    }

    public static Vec3ul shR_(final Vec3ul a, final ULong b) {
        return shR(new Vec3ul(), a, b.value, b.value, b.value);
    }

    public static Vec3ul shR_(final Vec3ul a, final long b) {
        return shR(new Vec3ul(), a, b, b, b);
    }

    public static Vec3ul shR_(final Vec3ul a, final Vec3ul b) {
        return shR(new Vec3ul(), a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul shR(final Vec3ul a, final ULong b) {
        return shR(a, a, b.value, b.value, b.value);
    }

    public static Vec3ul shR(final Vec3ul a, final long b) {
        return shR(a, a, b, b, b);
    }

    public static Vec3ul shR(final Vec3ul a, final Vec3ul b) {
        return shR(a, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul shR(final Vec3ul res, final Vec3ul a, final ULong b) {
        return shR(res, a, b.value, b.value, b.value);
    }

    public static Vec3ul shR(final Vec3ul res, final Vec3ul a, final long b) {
        return shR(res, a, b, b, b);
    }

    public static Vec3ul shR(final Vec3ul res, final Vec3ul a, final Vec3ul b) {
        return shR(res, a, b.x.value, b.y.value, b.z.value);
    }

    public static Vec3ul shR(final Vec3ul res, final Vec3ul a, final ULong bX, final ULong bY, final ULong bZ) {
        return shR(res, a, bX.value, bY.value, bZ.value);
    }

    public static Vec3ul shR(final Vec3ul res, final Vec3ul a, final long bX, final long bY, final long bZ) {
        res.x.value = a.x.value >>> bX;
        res.y.value = a.y.value >>> bY;
        res.z.value = a.z.value >>> bZ;
        return res;
    }

    public static Vec3us shR_(final Vec3us a, final UShort b) {
        return shR(new Vec3us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us shR_(final Vec3us a, final short b) {
        return shR(new Vec3us(), a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us shR_(final Vec3us a, final int b) {
        return shR(new Vec3us(), a, b, b, b);
    }

    public static Vec3us shR_(final Vec3us a, final Vec3us b) {
        return shR(new Vec3us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us shR(final Vec3us a, final UShort b) {
        return shR(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us shR(final Vec3us a, final short b) {
        return shR(a, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us shR(final Vec3us a, final int b) {
        return shR(a, a, b, b, b);
    }

    public static Vec3us shR(final Vec3us a, final Vec3us b) {
        return shR(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us shR(final Vec3us res, final Vec3us a, final UShort b) {
        return shR(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec3us shR(final Vec3us res, final Vec3us a, final short b) {
        return shR(res, a, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec3us shR(final Vec3us res, final Vec3us a, final int b) {
        return shR(res, a, b, b, b);
    }

    public static Vec3us shR(final Vec3us res, final Vec3us a, final Vec3us b) {
        return shR(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff);
    }

    public static Vec3us shR(final Vec3us res, final Vec3us a, final UShort bX, final UShort bY, final UShort bZ) {
        return shR(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff);
    }

    public static Vec3us shR(final Vec3us res, final Vec3us a, final short bX, final short bY, final short bZ) {
        return shR(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff);
    }

    public static Vec3us shR(final Vec3us res, final Vec3us a, final int bX, final int bY, final int bZ) {
        res.x.value = (short) ((a.x.value & 0xffff) >>> bX);
        res.y.value = (short) ((a.y.value & 0xffff) >>> bY);
        res.z.value = (short) ((a.z.value & 0xffff) >>> bZ);
        return res;
    }

    public static Vec4b shR_(final Vec4b a, final byte b) {
        return shR(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b shR_(final Vec4b a, final int b) {
        return shR(new Vec4b(), a, b, b, b, b);
    }

    public static Vec4b shR_(final Vec4b a, final Vec4b b) {
        return shR(new Vec4b(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b shR(final Vec4b a, final byte b) {
        return shR(a, a, b, b, b, b);
    }

    public static Vec4b shR(final Vec4b a, final int b) {
        return shR(a, a, b, b, b, b);
    }

    public static Vec4b shR(final Vec4b a, final Vec4b b) {
        return shR(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b shR(final Vec4b res, final Vec4b a, final byte b) {
        return shR(res, a, b, b, b, b);
    }

    public static Vec4b shR(final Vec4b res, final Vec4b a, final int b) {
        return shR(res, a, b, b, b, b);
    }

    public static Vec4b shR(final Vec4b res, final Vec4b a, final Vec4b b) {
        return shR(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4b shR(final Vec4b res, final Vec4b a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return shR(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4b shR(final Vec4b res, final Vec4b a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (byte) (a.x >> bX);
        res.y = (byte) (a.y >> bY);
        res.z = (byte) (a.z >> bZ);
        res.w = (byte) (a.w >> bW);
        return res;
    }

    public static Vec4i shR_(final Vec4i a, final int b) {
        return shR(new Vec4i(), a, b, b, b, b);
    }

    public static Vec4i shR_(final Vec4i a, final Vec4i b) {
        return shR(new Vec4i(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i shR(final Vec4i a, final int b) {
        return shR(a, a, b, b, b, b);
    }

    public static Vec4i shR(final Vec4i a, final Vec4i b) {
        return shR(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i shR(final Vec4i res, final Vec4i a, final int b) {
        return shR(res, a, b, b, b, b);
    }

    public static Vec4i shR(final Vec4i res, final Vec4i a, final Vec4i b) {
        return shR(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4i shR(final Vec4i res, final Vec4i a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = a.x >> bX;
        res.y = a.y >> bY;
        res.z = a.z >> bZ;
        res.w = a.w >> bW;
        return res;
    }

    public static Vec4l shR_(final Vec4l a, final long b) {
        return shR(new Vec4l(), a, b, b, b, b);
    }

    public static Vec4l shR_(final Vec4l a, final Vec4l b) {
        return shR(new Vec4l(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l shR(final Vec4l a, final long b) {
        return shR(a, a, b, b, b, b);
    }

    public static Vec4l shR(final Vec4l a, final Vec4l b) {
        return shR(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l shR(final Vec4l res, final Vec4l a, final long b) {
        return shR(res, a, b, b, b, b);
    }

    public static Vec4l shR(final Vec4l res, final Vec4l a, final Vec4l b) {
        return shR(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4l shR(final Vec4l res, final Vec4l a, final long bX, final long bY, final long bZ, final long bW) {
        res.x = a.x >> bX;
        res.y = a.y >> bY;
        res.z = a.z >> bZ;
        res.w = a.w >> bW;
        return res;
    }

    public static Vec4s shR_(final Vec4s a, final short b) {
        return shR(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s shR_(final Vec4s a, final int b) {
        return shR(new Vec4s(), a, b, b, b, b);
    }

    public static Vec4s shR_(final Vec4s a, final Vec4s b) {
        return shR(new Vec4s(), a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s shR(final Vec4s a, final short b) {
        return shR(a, a, b, b, b, b);
    }

    public static Vec4s shR(final Vec4s a, final int b) {
        return shR(a, a, b, b, b, b);
    }

    public static Vec4s shR(final Vec4s a, final Vec4s b) {
        return shR(a, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s shR(final Vec4s res, final Vec4s a, final short b) {
        return shR(res, a, b, b, b, b);
    }

    public static Vec4s shR(final Vec4s res, final Vec4s a, final int b) {
        return shR(res, a, b, b, b, b);
    }

    public static Vec4s shR(final Vec4s res, final Vec4s a, final Vec4s b) {
        return shR(res, a, b.x, b.y, b.z, b.w);
    }

    public static Vec4s shR(final Vec4s res, final Vec4s a, final short bX, final short bY, final short bZ, final short bW) {
        return shR(res, a, (int) bX, bY, bZ, bW);
    }

    public static Vec4s shR(final Vec4s res, final Vec4s a, final int bX, final int bY, final int bZ, final int bW) {
        res.x = (short) (a.x >> bX);
        res.y = (short) (a.y >> bY);
        res.z = (short) (a.z >> bZ);
        res.w = (short) (a.w >> bW);
        return res;
    }

    public static Vec4ub shR_(final Vec4ub a, final UByte b) {
        return shR(new Vec4ub(), a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub shR_(final Vec4ub a, final byte b) {
        return shR(new Vec4ub(), a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub shR_(final Vec4ub a, final int b) {
        return shR(new Vec4ub(), a, b, b, b, b);
    }

    public static Vec4ub shR_(final Vec4ub a, final Vec4ub b) {
        return shR(new Vec4ub(), a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub shR(final Vec4ub a, final UByte b) {
        return shR(a, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub shR(final Vec4ub a, final byte b) {
        return shR(a, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub shR(final Vec4ub a, final int b) {
        return shR(a, a, b, b, b, b);
    }

    public static Vec4ub shR(final Vec4ub a, final Vec4ub b) {
        return shR(a, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub shR(final Vec4ub res, final Vec4ub a, final UByte b) {
        return shR(res, a, b.value & 0xff, b.value & 0xff, b.value & 0xff, b.value & 0xff);
    }

    public static Vec4ub shR(final Vec4ub res, final Vec4ub a, final byte b) {
        return shR(res, a, b & 0xff, b & 0xff, b & 0xff, b & 0xff);
    }

    public static Vec4ub shR(final Vec4ub res, final Vec4ub a, final int b) {
        return shR(res, a, b, b, b, b);
    }

    public static Vec4ub shR(final Vec4ub res, final Vec4ub a, final Vec4ub b) {
        return shR(res, a, b.x.value & 0xff, b.y.value & 0xff, b.z.value & 0xff, b.w.value & 0xff);
    }

    public static Vec4ub shR(final Vec4ub res, final Vec4ub a, final UByte bX, final UByte bY, final UByte bZ, final UByte bW) {
        return shR(res, a, bX.value & 0xff, bY.value & 0xff, bZ.value & 0xff, bZ.value & 0xff);
    }

    public static Vec4ub shR(final Vec4ub res, final Vec4ub a, final byte bX, final byte bY, final byte bZ, final byte bW) {
        return shR(res, a, bX & 0xff, bY & 0xff, bZ & 0xff, bW & 0xff);
    }

    public static Vec4ub shR(final Vec4ub res, final Vec4ub a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (byte) ((a.x.value & 0xff) >>> bX);
        res.y.value = (byte) ((a.y.value & 0xff) >>> bY);
        res.z.value = (byte) ((a.z.value & 0xff) >>> bZ);
        res.w.value = (byte) ((a.w.value & 0xff) >>> bW);
        return res;
    }

    public static Vec4ui shR_(final Vec4ui a, final UInt b) {
        return shR(new Vec4ui(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui shR_(final Vec4ui a, final int b) {
        return shR(new Vec4ui(), a, b, b, b, b);
    }

    public static Vec4ui shR_(final Vec4ui a, final Vec4ui b) {
        return shR(new Vec4ui(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui shR(final Vec4ui a, final UInt b) {
        return shR(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui shR(final Vec4ui a, final int b) {
        return shR(a, a, b, b, b, b);
    }

    public static Vec4ui shR(final Vec4ui a, final Vec4ui b) {
        return shR(a, a, b.x.value, b.y.value, b.y.value, b.w.value);
    }

    public static Vec4ui shR(final Vec4ui res, final Vec4ui a, final UInt b) {
        return shR(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ui shR(final Vec4ui res, final Vec4ui a, final int b) {
        return shR(res, a, b, b, b, b);
    }

    public static Vec4ui shR(final Vec4ui res, final Vec4ui a, final Vec4ui b) {
        return shR(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ui shR(final Vec4ui res, final Vec4ui a, final UInt bX, final UInt bY, final UInt bZ, final UInt bW) {
        return shR(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ui shR(final Vec4ui res, final Vec4ui a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = a.x.value >>> bX;
        res.y.value = a.y.value >>> bY;
        res.z.value = a.z.value >>> bZ;
        res.w.value = a.w.value >>> bW;
        return res;
    }

    public static Vec4ul shR_(final Vec4ul a, final ULong b) {
        return shR(new Vec4ul(), a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul shR_(final Vec4ul a, final long b) {
        return shR(new Vec4ul(), a, b, b, b, b);
    }

    public static Vec4ul shR_(final Vec4ul a, final Vec4ul b) {
        return shR(new Vec4ul(), a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul shR(final Vec4ul a, final ULong b) {
        return shR(a, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul shR(final Vec4ul a, final long b) {
        return shR(a, a, b, b, b, b);
    }

    public static Vec4ul shR(final Vec4ul a, final Vec4ul b) {
        return shR(a, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul shR(final Vec4ul res, final Vec4ul a, final ULong b) {
        return shR(res, a, b.value, b.value, b.value, b.value);
    }

    public static Vec4ul shR(final Vec4ul res, final Vec4ul a, final long b) {
        return shR(res, a, b, b, b, b);
    }

    public static Vec4ul shR(final Vec4ul res, final Vec4ul a, final Vec4ul b) {
        return shR(res, a, b.x.value, b.y.value, b.z.value, b.w.value);
    }

    public static Vec4ul shR(final Vec4ul res, final Vec4ul a, final ULong bX, final ULong bY, final ULong bZ, final ULong bW) {
        return shR(res, a, bX.value, bY.value, bZ.value, bW.value);
    }

    public static Vec4ul shR(final Vec4ul res, final Vec4ul a, final long bX, final long bY, final long bZ, final long bW) {
        res.x.value = a.x.value >>> bX;
        res.y.value = a.y.value >>> bY;
        res.z.value = a.z.value >>> bZ;
        res.w.value = a.w.value >>> bW;
        return res;
    }

    public static Vec4us shR_(final Vec4us a, final UShort b) {
        return shR(new Vec4us(), a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us shR_(final Vec4us a, final short b) {
        return shR(new Vec4us(), a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us shR_(final Vec4us a, final int b) {
        return shR(new Vec4us(), a, b, b, b, b);
    }

    public static Vec4us shR_(final Vec4us a, final Vec4us b) {
        return shR(new Vec4us(), a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us shR(final Vec4us a, final UShort b) {
        return shR(a, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us shR(final Vec4us a, final short b) {
        return shR(a, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us shR(final Vec4us a, final int b) {
        return shR(a, a, b, b, b, b);
    }

    public static Vec4us shR(final Vec4us a, final Vec4us b) {
        return shR(a, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us shR(final Vec4us res, final Vec4us a, final UShort b) {
        return shR(res, a, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff, b.value & 0xffff);
    }

    public static Vec4us shR(final Vec4us res, final Vec4us a, final short b) {
        return shR(res, a, b & 0xffff, b & 0xffff, b & 0xffff, b & 0xffff);
    }

    public static Vec4us shR(final Vec4us res, final Vec4us a, final int b) {
        return shR(res, a, b, b, b, b);
    }

    public static Vec4us shR(final Vec4us res, final Vec4us a, final Vec4us b) {
        return shR(res, a, b.x.value & 0xffff, b.y.value & 0xffff, b.z.value & 0xffff, b.w.value & 0xffff);
    }

    public static Vec4us shR(final Vec4us res, final Vec4us a, final UShort bX, final UShort bY, final UShort bZ, final UShort bW) {
        return shR(res, a, bX.value & 0xffff, bY.value & 0xffff, bZ.value & 0xffff, bW.value & 0xffff);
    }

    public static Vec4us shR(final Vec4us res, final Vec4us a, final short bX, final short bY, final short bZ, final short bW) {
        return shR(res, a, bX & 0xffff, bY & 0xffff, bZ & 0xffff, bW & 0xffff);
    }

    public static Vec4us shR(final Vec4us res, final Vec4us a, final int bX, final int bY, final int bZ, final int bW) {
        res.x.value = (short) ((a.x.value & 0xffff) >>> bX);
        res.y.value = (short) ((a.y.value & 0xffff) >>> bY);
        res.z.value = (short) ((a.z.value & 0xffff) >>> bZ);
        res.w.value = (short) ((a.w.value & 0xffff) >>> bW);
        return res;
    }

    public static Vec2b compl_(final Vec2b a) {
        return compl(new Vec2b(), a);
    }

    public static Vec2b compl(final Vec2b res, final Vec2b a) {
        res.x = (byte) ~(a.x & 0xff);
        res.y = (byte) ~(a.y & 0xff);
        return res;
    }

    public static Vec2i compl_(final Vec2i a) {
        return compl(new Vec2i(), a);
    }

    public static Vec2i compl(final Vec2i res, final Vec2i a) {
        res.x = ~a.x;
        res.y = ~a.y;
        return res;
    }

    public static Vec2l compl_(final Vec2l a, final long b) {
        return compl(new Vec2l(), a);
    }

    public static Vec2l compl(final Vec2l res, final Vec2l a) {
        res.x = ~a.x;
        res.y = ~a.y;
        return res;
    }

    public static Vec2s compl_(final Vec2s a) {
        return compl(new Vec2s(), a);
    }

    public static Vec2s compl(final Vec2s res, final Vec2s a) {
        res.x = (short) ~a.x;
        res.y = (short) ~a.y;
        return res;
    }

    public static Vec2ub compl_(final Vec2ub a) {
        return compl(new Vec2ub(), a);
    }

    public static Vec2ub compl(final Vec2ub res, final Vec2ub a) {
        res.x.value = (byte) ~(a.x.value & 0xff);
        res.y.value = (byte) ~(a.y.value & 0xff);
        return res;
    }

    public static Vec2ui compl_(final Vec2ui a) {
        return compl(new Vec2ui(), a);
    }

    public static Vec2ui compl(final Vec2ui res, final Vec2ui a) {
        res.x.value = ~a.x.value;
        res.y.value = ~a.y.value;
        return res;
    }

    public static Vec2ul compl_(final Vec2ul a) {
        return compl(new Vec2ul(), a);
    }

    public static Vec2ul compl(final Vec2ul res, final Vec2ul a) {
        res.x.value = ~a.x.value;
        res.y.value = ~a.y.value;
        return res;
    }

    public static Vec2us compl_(final Vec2us a) {
        return compl(new Vec2us(), a);
    }

    public static Vec2us compl(final Vec2us res, final Vec2us a) {
        res.x.value = (short) ~(a.x.value & 0xffff);
        res.y.value = (short) ~(a.y.value & 0xffff);
        return res;
    }

    public static Vec3b compl_(final Vec3b a) {
        return compl(new Vec3b(), a);
    }

    public static Vec3b compl(final Vec3b res, final Vec3b a) {
        res.x = (byte) ~a.x;
        res.y = (byte) ~a.y;
        res.z = (byte) ~a.z;
        return res;
    }

    public static Vec3i compl_(final Vec3i a) {
        return compl(new Vec3i(), a);
    }

    public static Vec3i compl(final Vec3i res, final Vec3i a) {
        res.x = ~a.x;
        res.y = ~a.y;
        res.z = ~a.z;
        return res;
    }

    public static Vec3l compl_(final Vec3l a) {
        return compl(new Vec3l(), a);
    }

    public static Vec3l compl(final Vec3l res, final Vec3l a) {
        res.x = ~a.x;
        res.y = ~a.y;
        res.z = ~a.z;
        return res;
    }

    public static Vec3s compl_(final Vec3s a) {
        return compl(new Vec3s(), a);
    }

    public static Vec3s compl(final Vec3s res, final Vec3s a) {
        res.x = (short) ~a.x;
        res.y = (short) ~a.y;
        res.z = (short) ~a.z;
        return res;
    }

    public static Vec3ub compl_(final Vec3ub a) {
        return compl(new Vec3ub(), a);
    }

    public static Vec3ub compl(final Vec3ub res, final Vec3ub a) {
        res.x.value = (byte) ~(a.x.value & 0xff);
        res.y.value = (byte) ~(a.y.value & 0xff);
        res.z.value = (byte) ~(a.z.value & 0xff);
        return res;
    }

    public static Vec3ui compl_(final Vec3ui a) {
        return compl(new Vec3ui(), a);
    }

    public static Vec3ui compl(final Vec3ui res, final Vec3ui a) {
        res.x.value = ~a.x.value;
        res.y.value = ~a.y.value;
        res.z.value = ~a.z.value;
        return res;
    }

    public static Vec3ul compl_(final Vec3ul a) {
        return compl(new Vec3ul(), a);
    }

    public static Vec3ul compl(final Vec3ul res, final Vec3ul a) {
        res.x.value = ~a.x.value;
        res.y.value = ~a.y.value;
        res.z.value = ~a.z.value;
        return res;
    }

    public static Vec3us compl_(final Vec3us a) {
        return compl(new Vec3us(), a);
    }

    public static Vec3us compl(final Vec3us res, final Vec3us a) {
        res.x.value = (short) ~(a.x.value & 0xffff);
        res.y.value = (short) ~(a.y.value & 0xffff);
        res.z.value = (short) ~(a.z.value & 0xffff);
        return res;
    }

    public static Vec4b compl_(final Vec4b a) {
        return compl(new Vec4b(), a);
    }

    public static Vec4b compl(final Vec4b res, final Vec4b a) {
        res.x = (byte) ~a.x;
        res.y = (byte) ~a.y;
        res.z = (byte) ~a.z;
        res.w = (byte) ~a.w;
        return res;
    }

    public static Vec4i compl_(final Vec4i a) {
        return compl(new Vec4i(), a);
    }

    public static Vec4i compl(final Vec4i res, final Vec4i a) {
        res.x = ~a.x;
        res.y = ~a.y;
        res.z = ~a.z;
        res.w = ~a.w;
        return res;
    }

    public static Vec4l compl_(final Vec4l a) {
        return compl(new Vec4l(), a);
    }

    public static Vec4l compl(final Vec4l res, final Vec4l a) {
        res.x = ~a.x;
        res.y = ~a.y;
        res.z = ~a.z;
        res.w = ~a.w;
        return res;
    }

    public static Vec4s compl_(final Vec4s a) {
        return compl(new Vec4s(), a);
    }

    public static Vec4s compl(final Vec4s res, final Vec4s a) {
        res.x = (short) ~a.x;
        res.y = (short) ~a.y;
        res.z = (short) ~a.z;
        res.w = (short) ~a.w;
        return res;
    }

    public static Vec4ub compl_(final Vec4ub a) {
        return compl(new Vec4ub(), a);
    }

    public static Vec4ub compl(final Vec4ub res, final Vec4ub a) {
        res.x.value = (byte) ~(a.x.value & 0xff);
        res.y.value = (byte) ~(a.y.value & 0xff);
        res.z.value = (byte) ~(a.z.value & 0xff);
        res.w.value = (byte) ~(a.w.value & 0xff);
        return res;
    }

    public static Vec4ui compl_(final Vec4ui a) {
        return compl(new Vec4ui(), a);
    }

    public static Vec4ui compl(final Vec4ui res, final Vec4ui a) {
        res.x.value = ~a.x.value;
        res.y.value = ~a.y.value;
        res.z.value = ~a.z.value;
        res.w.value = ~a.w.value;
        return res;
    }

    public static Vec4ul compl_(final Vec4ul a) {
        return compl(new Vec4ul(), a);
    }

    public static Vec4ul compl(final Vec4ul res, final Vec4ul a) {
        res.x.value = ~a.x.value;
        res.y.value = ~a.y.value;
        res.z.value = ~a.z.value;
        res.w.value = ~a.w.value;
        return res;
    }

    public static Vec4us compl_(final Vec4us a) {
        return compl(new Vec4us(), a);
    }

    public static Vec4us compl(final Vec4us res, final Vec4us a) {
        res.x.value = (short) ~(a.x.value & 0xffff);
        res.y.value = (short) ~(a.y.value & 0xffff);
        res.z.value = (short) ~(a.z.value & 0xffff);
        res.w.value = (short) ~(a.w.value & 0xffff);
        return res;
    }

    public static void main(final String[] args) {
        final int a = 0b1100;
        final int b = 0b1001;
        final int c = Integer.remainderUnsigned(a, b);
        System.out.println(Integer.toUnsignedString(c));
    }
}
