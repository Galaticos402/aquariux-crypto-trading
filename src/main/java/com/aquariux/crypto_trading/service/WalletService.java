package com.aquariux.crypto_trading.service;

import com.aquariux.crypto_trading.context.SecurityContext;
import com.aquariux.crypto_trading.dto.wallet.WalletGetResponseDto;
import com.aquariux.crypto_trading.entity.Wallet;
import com.aquariux.crypto_trading.repository.IWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class WalletService {
    @Autowired
    IWalletRepository walletRepository;

    @Autowired
    SecurityContext securityContext;

    @Transactional
    public List<WalletGetResponseDto> getWallets(){
        List<Wallet> wallets = walletRepository
                .findByTrader_TraderId(
                        securityContext.getCurrentUserId()
                )
                .orElse(Collections.emptyList());

        return wallets.stream().map(Wallet::toWalletGetResponseDto).toList();
    }
}
