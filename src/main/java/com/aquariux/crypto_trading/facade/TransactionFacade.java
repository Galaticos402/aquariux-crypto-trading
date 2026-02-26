package com.aquariux.crypto_trading.facade;

import com.aquariux.crypto_trading.dto.transaction.TransactionCreateDto;
import com.aquariux.crypto_trading.service.TransactionService;
import jakarta.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class TransactionFacade {
    private int MAX_RETRY = 3;
    @Autowired
    TransactionService transactionService;

    public Long safeCreateTransaction(TransactionCreateDto transactionCreateDto, String idempotencyKey){
        for(int i = 0; i < MAX_RETRY; i++){
            try{
                Long transactionId = transactionService.createTransaction(transactionCreateDto, idempotencyKey);
                return transactionId;
            }catch (OptimisticLockException optimisticEx){
                if(i == MAX_RETRY - 1){
                    throw optimisticEx;
                }
                try {
                    long backoff = (long) (100 * Math.pow(2, i));
                    Thread.sleep(backoff);
                }catch (InterruptedException interruptedEx){
                    Thread.currentThread().interrupt();
                }
            }catch (Exception e){
                throw e;
            }
        }
        return null;
    }
}
