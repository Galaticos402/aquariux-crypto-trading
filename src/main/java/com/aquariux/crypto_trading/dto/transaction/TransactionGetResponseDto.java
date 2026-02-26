package com.aquariux.crypto_trading.dto.transaction;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class TransactionGetResponseDto {
    private String symbol;
    private BigDecimal quantity;
    private BigDecimal price;
    private String type;
}
