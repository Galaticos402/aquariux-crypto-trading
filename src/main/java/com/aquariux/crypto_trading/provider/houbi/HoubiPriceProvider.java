package com.aquariux.crypto_trading.provider.houbi;

import com.aquariux.crypto_trading.dto.HoubiPriceDto;
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
public class HoubiPriceProvider implements PlatformPriceProvider {
    private static final String HOUBI_PLATFORM = "HOUBI";

    private static final String ENDPOINT = "https://api.huobi.pro/market/tickers";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String getPlatform() {
        return HOUBI_PLATFORM;
    }

    @Override
    public Map<String, TokenPriceDto> fetchTokenPrices() {
        HoubiPriceResponseDto houbiPriceResponse = restTemplate.getForObject(ENDPOINT, HoubiPriceResponseDto.class);
        if(Objects.isNull(houbiPriceResponse))
            return Collections.emptyMap();
        return Arrays.stream(houbiPriceResponse.getData()).collect(Collectors.toMap(HoubiPriceDto::getSymbol, p -> new TokenPriceDto(p.getSymbol(), p.getBid(), p.getAsk())));
    }
}
