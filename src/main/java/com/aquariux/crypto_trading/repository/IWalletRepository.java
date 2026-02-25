package com.aquariux.crypto_trading.repository;

import com.aquariux.crypto_trading.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IWalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByWalletIdAndTraderId(Long walletId, Long traderId);
    Optional<Wallet> findByAssetAndTraderIdIgnoreCase(String asset, Long traderId);
}
