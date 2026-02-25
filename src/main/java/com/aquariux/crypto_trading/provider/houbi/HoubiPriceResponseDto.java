package com.aquariux.crypto_trading.provider.houbi;

import com.aquariux.crypto_trading.dto.HoubiPriceDto;
import lombok.Data;

@Data
public class HoubiPriceResponseDto {
    private HoubiPriceDto[] data;
}
