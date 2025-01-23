/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm;

import glmath.joou.UByte;
import glmath.joou.UInt;
import glmath.joou.ULong;
import glmath.joou.UShort;

/**
 *
 * @author GBarbieri
 */
public class UMath {

    public static UByte max(final UByte a, final UByte b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    public static UInt max(final UInt a, final UInt b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    public static ULong max(final ULong a, final ULong b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    public static UShort max(final UShort a, final UShort b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    public static UByte min(final UByte a, final UByte b) {
        return a.compareTo(b) < 0 ? a : b;
    }

    public static UInt min(final UInt a, final UInt b) {
        return a.compareTo(b) < 0 ? a : b;
    }

    public static ULong min(final ULong a, final ULong b) {
        return a.compareTo(b) < 0 ? a : b;
    }

    public static UShort min(final UShort a, final UShort b) {
        return a.compareTo(b) < 0 ? a : b;
    }

}
