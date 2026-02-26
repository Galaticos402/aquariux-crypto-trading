package com.aquariux.crypto_trading.dto.wallet;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class WalletGetResponseDto {
    private String asset;
    private BigDecimal balance;
}
