package com.aquariux.crypto_trading.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BaseResponseDto<T> {
    private T data;
    private boolean status;
    private String errorMsg;
}
