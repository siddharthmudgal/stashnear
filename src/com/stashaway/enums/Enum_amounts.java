package com.stashaway.enums;

public enum Enum_amounts {

    MINIMUM_AMOUNT(0),

    ;

    private long value;

    public long getValue() {
        return value;
    }

    private Enum_amounts(long value) {
        this.value = value;
    }
}
