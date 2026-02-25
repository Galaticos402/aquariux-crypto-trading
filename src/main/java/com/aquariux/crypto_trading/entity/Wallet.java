package com.aquariux.crypto_trading.entity;

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
    private String asset;
    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name = "trader_id")
    private Trader owner;
}
