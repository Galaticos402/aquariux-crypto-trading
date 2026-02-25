package com.aquariux.crypto_trading.entity;

import com.aquariux.crypto_trading.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Transaction extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trader_id")
    private Trader trader;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "symbol")
    private Token token;
    private BigDecimal quantity;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
}
