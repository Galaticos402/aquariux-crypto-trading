package com.aquariux.crypto_trading.repository;

import com.aquariux.crypto_trading.entity.Token;
import com.aquariux.crypto_trading.entity.Trader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITraderRepository extends JpaRepository<Trader, Long> {
}
