package com.aquariux.crypto_trading.repository;

import com.aquariux.crypto_trading.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<List<Transaction>> findByTrader_TraderId(Long traderId);
}
