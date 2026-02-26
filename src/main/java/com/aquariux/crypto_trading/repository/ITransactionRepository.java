package com.aquariux.crypto_trading.repository;

import com.aquariux.crypto_trading.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByTrader_TraderId(Long traderId, Pageable pageable);
}
