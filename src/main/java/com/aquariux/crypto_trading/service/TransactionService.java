package com.aquariux.crypto_trading.service;

import com.aquariux.crypto_trading.context.SecurityContext;
import com.aquariux.crypto_trading.dto.transaction.TransactionCreateDto;
import com.aquariux.crypto_trading.entity.Token;
import com.aquariux.crypto_trading.entity.Trader;
import com.aquariux.crypto_trading.entity.Transaction;
import com.aquariux.crypto_trading.entity.Wallet;
import com.aquariux.crypto_trading.enums.TransactionType;
import com.aquariux.crypto_trading.repository.ITokenRepository;
import com.aquariux.crypto_trading.repository.ITraderRepository;
import com.aquariux.crypto_trading.repository.ITransactionRepository;
import com.aquariux.crypto_trading.repository.IWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.RuntimeErrorException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionService {
    @Autowired
    SecurityContext securityContext;
    @Autowired
    IWalletRepository walletRepository;
    @Autowired
    ITokenRepository tokenRepository;
    @Autowired
    ITransactionRepository transactionRepository;
    @Autowired
    ITraderRepository traderRepository;

    private static final List<String> supportedCrypto = List.of("BTCUSDT", "ETHUSDT");

    @Transactional
    public void createTransaction(TransactionCreateDto transactionCreateDto){
        Trader currentTrader = traderRepository.findById(securityContext.getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Unable to get current trader info"));

        Token targetToken = tokenRepository.findById(transactionCreateDto.getTokenId())
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        // Check if the trading crypto is among the supported cryptocurrencies
        if (!supportedCrypto.contains(targetToken.getSymbol().toUpperCase())) {
            throw new RuntimeException("Unsupported token");
        }
        TransactionType type = TransactionType.valueOf(transactionCreateDto.getTransactionType());

        BigDecimal price = type == TransactionType.BUY
                ? targetToken.getBestAsk()
                : targetToken.getBestBid();

        BigDecimal totalAmount = price.multiply(transactionCreateDto.getQuantity());
        Wallet sourceWallet = null;
        Wallet destWallet = null;

        if (type == TransactionType.BUY) {
            // Check if the wallet has existed and belongs to the current trader
             sourceWallet = walletRepository
                    .findByAssetAndTraderIdIgnoreCase(
                            "USDT",
                            securityContext.getCurrentUserId()
                    )
                    .orElseThrow(() -> new RuntimeException("Wallet not found"));

             destWallet = walletRepository
                    .findByAssetAndTraderIdIgnoreCase(
                            targetToken.getSymbol().equalsIgnoreCase("BTCUSDT") ? "BTC" : "ETH",
                            securityContext.getCurrentUserId()
                    )
                    .orElseThrow(() -> new RuntimeException("Wallet not found"));
            // Verify if balance is sufficient to purchase
            if (sourceWallet.getBalance().compareTo(totalAmount) < 0) {
                throw new RuntimeException("Insufficient balance");
            }

            sourceWallet.setBalance(sourceWallet.getBalance().subtract(totalAmount));
            walletRepository.save(sourceWallet);
            destWallet.setBalance(destWallet.getBalance().add(transactionCreateDto.getQuantity()));
            walletRepository.save(destWallet);
        }else{
            // Check if the wallet has existed and belongs to the current trader
            sourceWallet = walletRepository
                    .findByAssetAndTraderIdIgnoreCase(
                            targetToken.getSymbol().equalsIgnoreCase("BTCUSDT") ? "BTC" : "ETH",
                            securityContext.getCurrentUserId()
                    )
                    .orElseThrow(() -> new RuntimeException("Wallet not found"));

            destWallet = walletRepository
                    .findByAssetAndTraderIdIgnoreCase(
                            "USDT",
                            securityContext.getCurrentUserId()
                    )
                    .orElseThrow(() -> new RuntimeException("Wallet not found"));
            if (sourceWallet.getBalance().compareTo(transactionCreateDto.getQuantity()) < 0) {
                throw new RuntimeException("Insufficient balance");
            }

            sourceWallet.setBalance(sourceWallet.getBalance().subtract(transactionCreateDto.getQuantity()));
            walletRepository.save(sourceWallet);
            destWallet.setBalance(destWallet.getBalance().add(totalAmount));
            walletRepository.save(destWallet);
        }


        // Create transaction
        Transaction transaction = Transaction.builder()
                .price(price)
                .token(targetToken)
                .trader(currentTrader)
                .type(type)
                .quantity(transactionCreateDto.getQuantity())
                .build();

        transactionRepository.save(transaction);



    }
}
