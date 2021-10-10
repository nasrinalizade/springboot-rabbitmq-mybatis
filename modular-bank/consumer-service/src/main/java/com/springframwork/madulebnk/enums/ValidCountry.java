package com.springframwork.madulebnk.enums;

import java.util.Arrays;
import java.util.Objects;

public enum ValidCountry {
    EUR(0),
    SEK(1),
    GBP(2),
    USD(3);

    private final Integer value;

    private ValidCountry(Integer value) {
        this.value = value;
    }

    public static ValidCountry findByValue(Integer value) {
        return (ValidCountry) Arrays.stream(values()).filter((type) -> {
            return Objects.equals(type.getValue(), value);
        }).findFirst().orElse(null);
    }

    public Integer getValue() {
        return this.value;
    }
}
