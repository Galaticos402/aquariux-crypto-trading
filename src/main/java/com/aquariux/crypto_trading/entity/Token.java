package com.aquariux.crypto_trading.entity;

import com.aquariux.crypto_trading.dto.response.TokenGetResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Token extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    @Column(nullable = false, unique = true)
    private String symbol;
    private BigDecimal bestBid;
    private BigDecimal bestAsk;

    public TokenGetResponseDto toTokenGetResponseDto(){
        TokenGetResponseDto tokenGetResponseDto = new TokenGetResponseDto();
        tokenGetResponseDto.setSymbol(this.symbol);
        tokenGetResponseDto.setBestBid(this.bestBid);
        tokenGetResponseDto.setBestAsk(this.bestAsk);
        return tokenGetResponseDto;
    }
}
