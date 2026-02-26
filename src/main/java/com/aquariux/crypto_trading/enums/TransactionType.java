package com.aquariux.crypto_trading.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TransactionType {
    SELL("SELL"),
    BUY("BUY");
    private final String value;

    public static boolean isValid(String value) {
        return Arrays.stream(values())
                .anyMatch(type -> type.name().equalsIgnoreCase(value));
    }

}
