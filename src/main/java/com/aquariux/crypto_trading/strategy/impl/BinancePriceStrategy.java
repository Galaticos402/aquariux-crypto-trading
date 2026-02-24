package com.aquariux.crypto_trading.strategy.impl;

import com.aquariux.crypto_trading.dto.TokenPriceDto;
import com.aquariux.crypto_trading.strategy.ExchangePriceStrategy;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BinancePriceStrategy implements ExchangePriceStrategy {
    private static final String BINANCE_PLATFORM = "BINANCE";

    @Override
    public String getPlatform() {
        return BINANCE_PLATFORM;
    }

    @Override
    public Map<String, TokenPriceDto> fetchTokenPrices() {
        return Map.of();
    }
}
