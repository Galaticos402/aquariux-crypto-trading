package com.aquariux.crypto_trading.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionType {
    SELL("SELL"),
    BUY("BUY");
    private final String value;
}
