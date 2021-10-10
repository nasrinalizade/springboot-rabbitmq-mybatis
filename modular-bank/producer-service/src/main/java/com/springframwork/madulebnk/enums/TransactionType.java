package com.springframwork.madulebnk.enums;

import java.util.Arrays;
import java.util.Objects;

public enum TransactionType {
    OUT(0),
    IN(1);

    private final Integer value;

    private TransactionType(Integer value) {
        this.value = value;
    }

    public static TransactionType findByValue(Integer value) {
        return (TransactionType) Arrays.stream(values()).filter((type) -> {
            return Objects.equals(type.getValue(), value);
        }).findFirst().orElse(null);
    }

    public Integer getValue() {
        return this.value;
    }
}