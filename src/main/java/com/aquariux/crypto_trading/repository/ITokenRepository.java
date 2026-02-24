package com.aquariux.crypto_trading.repository;

import com.aquariux.crypto_trading.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenRepository extends JpaRepository<Token, Long> {
}
