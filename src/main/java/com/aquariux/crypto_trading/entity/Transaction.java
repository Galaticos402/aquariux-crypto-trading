package com.aquariux.crypto_trading.entity;

import com.aquariux.crypto_trading.enums.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private User seller;
    private User buyer;
    private Token token;
    private BigDecimal quantity;
    private BigDecimal price;
    private TransactionType type;
}
