package com.vladyka.taxi.model;

public enum Discount {
    LIGHT,
    MEDIUM,
    STRONG;

    public static int getDiscount(Discount type) {
        switch (type) {
            case LIGHT:
                return 5;
            case MEDIUM:
                return 10;
            case STRONG:
                return 15;
            default:
                return 0;
        }
    }


}
