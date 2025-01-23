/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glmath.glm.vec._4.bool;

/**
 *
 * @author GBarbieri
 */
public class Vec4bool extends FuncRelational {

    public Vec4bool() {
    }

    public Vec4bool(final boolean x, final boolean y, final boolean z, final boolean w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    public Vec4bool set(final boolean x, final boolean y, final boolean z, final boolean w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }
}
