package com.aquariux.crypto_trading.entity;

import com.aquariux.crypto_trading.dto.transaction.TransactionGetResponseDto;
import com.aquariux.crypto_trading.dto.wallet.WalletGetResponseDto;
import com.aquariux.crypto_trading.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(unique = true)
    private String idempotencyKey;

    public TransactionGetResponseDto toTransactionGetResponseDto(){
        TransactionGetResponseDto transactionGetResponseDto = new TransactionGetResponseDto();
        transactionGetResponseDto.setPrice(this.price);
        transactionGetResponseDto.setType(this.type.getValue());
        transactionGetResponseDto.setQuantity(this.quantity);
        transactionGetResponseDto.setSymbol(this.token.getSymbol());
        return transactionGetResponseDto;
    }
}
