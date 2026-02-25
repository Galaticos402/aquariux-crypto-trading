package com.aquariux.crypto_trading.service;

import com.aquariux.crypto_trading.dto.TokenPriceDto;
import com.aquariux.crypto_trading.entity.Token;
import com.aquariux.crypto_trading.provider.binance.BinancePriceProvider;
import com.aquariux.crypto_trading.provider.houbi.HoubiPriceProvider;
import com.aquariux.crypto_trading.repository.ITokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PriceAggregationService {
    @Autowired
    BinancePriceProvider binanceProvider;
    @Autowired
    HoubiPriceProvider houbiProvider;
    @Autowired
    ITokenRepository tokenRepository;
    @Transactional
    public void aggregate(){

        Map<String, TokenPriceDto> binancePrices = binanceProvider.fetchTokenPrices();
        Map<String, TokenPriceDto> houbiPrices = houbiProvider.fetchTokenPrices();

        Set<String> keys = new HashSet<>();
        keys.addAll(binancePrices.keySet());
        keys.addAll(houbiPrices.keySet());

        for(String key : keys){
            TokenPriceDto binancePrice = binancePrices.get(key);
            TokenPriceDto houbiPrice = houbiPrices.get(key);

            BigDecimal bestBid = Stream.of(binancePrice, houbiPrice)
                    .filter(Objects::nonNull)
                    .map(TokenPriceDto::getBid)
                    .max(BigDecimal::compareTo)
                    .orElse(null);

            BigDecimal bestAsk = Stream.of(binancePrice, houbiPrice)
                    .filter(Objects::nonNull)
                    .map(TokenPriceDto::getAsk)
                    .min(BigDecimal::compareTo)
                    .orElse(null);

            Token targetToken = tokenRepository.findBySymbol(key).orElse(null);
            if(Objects.isNull(targetToken)){
                tokenRepository.save(new Token(null, key, bestBid, bestAsk));
            }else{
                targetToken.setBestAsk(bestAsk);
                targetToken.setBestBid(bestBid);
                tokenRepository.save(targetToken);
            }

        }
    }
}
