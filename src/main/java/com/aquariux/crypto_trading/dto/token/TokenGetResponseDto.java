package com.aquariux.crypto_trading.dto.token;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class TokenGetResponseDto {
    private String symbol;
    private BigDecimal bestBid;
    private BigDecimal bestAsk;
}
