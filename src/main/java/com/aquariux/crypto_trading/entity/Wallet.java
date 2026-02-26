package com.aquariux.crypto_trading.entity;

import com.aquariux.crypto_trading.dto.token.TokenGetResponseDto;
import com.aquariux.crypto_trading.dto.wallet.WalletGetResponseDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Wallet extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walletId;
    @Column(nullable = false, unique = true)
    private String asset;
    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name = "trader_id")
    private Trader trader;
    @Version
    private Long version;

    public WalletGetResponseDto toWalletGetResponseDto(){
        WalletGetResponseDto walletGetResponseDto = new WalletGetResponseDto();
        walletGetResponseDto.setAsset(this.asset);
        walletGetResponseDto.setBalance(this.balance);
        return walletGetResponseDto;
    }
}
