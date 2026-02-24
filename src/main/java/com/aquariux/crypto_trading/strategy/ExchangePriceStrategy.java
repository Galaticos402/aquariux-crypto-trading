package com.aquariux.crypto_trading.strategy;

import com.aquariux.crypto_trading.dto.TokenPriceDto;

import java.util.Map;

public interface ExchangePriceStrategy {
    String getPlatform();
    Map<String, TokenPriceDto> fetchTokenPrices();
}
