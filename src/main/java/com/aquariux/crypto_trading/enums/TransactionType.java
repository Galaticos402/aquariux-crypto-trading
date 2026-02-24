package com.aquariux.crypto_trading.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionType {
    SELL(0),
    BUY(1);
    private final int value;
}
