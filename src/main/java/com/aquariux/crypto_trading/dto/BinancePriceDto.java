package com.aquariux.crypto_trading.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class BinancePriceDto {
    private String symbol;
    private BigDecimal bidPrice;
    private BigDecimal bidQty;
    private BigDecimal askPrice;
    private BigDecimal askQty;
}
