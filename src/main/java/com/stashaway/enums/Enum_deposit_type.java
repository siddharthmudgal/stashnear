package com.stashaway.enums;

public enum Enum_deposit_type {

    MONTHLY(0),
    ONE_TIME(1),
            ;

    private int value;

    public int getValue() {
        return value;
    }

    private Enum_deposit_type(int value) {
        this.value = value;
    }
}
