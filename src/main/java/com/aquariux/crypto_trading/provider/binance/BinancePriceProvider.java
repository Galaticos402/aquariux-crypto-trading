package com.aquariux.crypto_trading.provider.binance;

import com.aquariux.crypto_trading.dto.BinancePriceDto;
import com.aquariux.crypto_trading.dto.TokenPriceDto;
import com.aquariux.crypto_trading.provider.PlatformPriceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BinancePriceProvider implements PlatformPriceProvider {
    private static final String BINANCE_PLATFORM = "BINANCE";

    private static final String ENDPOINT = "https://api.binance.com/api/v3/ticker/bookTicker";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String getPlatform() {
        return BINANCE_PLATFORM;
    }

    @Override
    public Map<String, TokenPriceDto> fetchTokenPrices() {
        BinancePriceDto[] binancePriceResponse = restTemplate.getForObject(ENDPOINT, BinancePriceDto[].class);
        if(Objects.isNull(binancePriceResponse))
            return Collections.emptyMap();
        return Arrays.stream(binancePriceResponse).collect(Collectors.toMap(BinancePriceDto::getSymbol, p -> new TokenPriceDto(p.getSymbol(), p.getBidPrice(), p.getAskPrice())));
    }
}
