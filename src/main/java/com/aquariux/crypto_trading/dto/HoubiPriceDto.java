package com.aquariux.crypto_trading.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class HoubiPriceDto {
    private String symbol;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private BigDecimal amount;
    private BigDecimal vol;
    private BigDecimal count;
    private BigDecimal bid;
    private BigDecimal bidSize;
    private BigDecimal ask;
    private BigDecimal askSize;
}
