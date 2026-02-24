package com.aquariux.crypto_trading.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class TokenPriceDto {
    private String symbol;
    private BigDecimal bid;
    private BigDecimal ask;
}
