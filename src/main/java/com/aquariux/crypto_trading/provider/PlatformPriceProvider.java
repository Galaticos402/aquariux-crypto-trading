package com.aquariux.crypto_trading.provider;

import com.aquariux.crypto_trading.dto.TokenPriceDto;

import java.util.Map;

public interface PlatformPriceProvider {
    String getPlatform();
    Map<String, TokenPriceDto> fetchTokenPrices();
}
