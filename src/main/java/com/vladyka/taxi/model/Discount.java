package com.vladyka.taxi.model;

public enum Discount {
    SILVER(5),
    GOLD(10),
    PLATINUM(15),
    ZERO(0);

    private int value;

    Discount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
