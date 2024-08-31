package com.hbm.hazard;

public class Hazard {

    public enum Type {
        ASBESTOS,
        BLINDING,
        COAL,
        COLD,
        DIGAMMA,
        EXPLOSIVE,
        HOT,
        HYDROACTIVE,
        RADIATION,
        TOXIC;
    }

    private final Type type;
    private final float strength;

    public Hazard(Type type, float strength) {
        this.type = type;
        this.strength = strength;
    }

    public Type getType() {
        return type;
    }

    public float getStrength() {
        return strength;
    }

    public Hazard multiply(float multiplier) {
        return new Hazard(type, strength * multiplier);
    }

    @Override
    public String toString() {
        return "Hazard{" +
                "type=" + type +
                ", strength=" + strength +
                '}';
    }
}
