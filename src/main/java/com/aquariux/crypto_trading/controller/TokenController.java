package com.aquariux.crypto_trading.controller;

import com.aquariux.crypto_trading.dto.response.BaseResponseDto;
import com.aquariux.crypto_trading.dto.token.TokenGetResponseDto;
import com.aquariux.crypto_trading.entity.Token;
import com.aquariux.crypto_trading.repository.ITokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/token")
public class TokenController {
    @Autowired
    ITokenRepository tokenRepository;
    @GetMapping("/{symbol}")
    public ResponseEntity<BaseResponseDto<TokenGetResponseDto>> getTokenBySymbol(@PathVariable String symbol) {
        Token token = tokenRepository.findBySymbolIgnoreCase(symbol)
                .orElse(null);
        TokenGetResponseDto tokenGetResponseDto = null;
        if(!Objects.isNull(token)){
            tokenGetResponseDto = token.toTokenGetResponseDto();
        }

        BaseResponseDto<TokenGetResponseDto> responseDto = BaseResponseDto.<TokenGetResponseDto>builder()
                        .data(tokenGetResponseDto)
                        .status(!Objects.isNull(tokenGetResponseDto))
                        .errorMsg(Objects.isNull(tokenGetResponseDto) ? "Failed to fetch token" : "")
                        .build();
        return ResponseEntity.ok(responseDto);
    }
}
