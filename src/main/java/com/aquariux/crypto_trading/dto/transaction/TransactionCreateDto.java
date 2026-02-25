package com.aquariux.crypto_trading.dto.transaction;

import com.aquariux.crypto_trading.enums.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class TransactionCreateDto {
    private Long tokenId;
    private String transactionType;
    private BigDecimal quantity;
}
