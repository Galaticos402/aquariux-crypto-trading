package com.aquariux.crypto_trading.jobs;

import com.aquariux.crypto_trading.service.PriceAggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FetchTokenPriceJob {
    @Autowired
    PriceAggregationService priceAggregationService;

    @Scheduled(fixedRate = 10000)
    public void fetchPrice(){
        priceAggregationService.aggregate();
    }
}
