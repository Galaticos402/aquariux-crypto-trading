package com.aquariux.crypto_trading.controller;

import com.aquariux.crypto_trading.dto.response.BaseResponseDto;
import com.aquariux.crypto_trading.dto.transaction.TransactionCreateDto;
import com.aquariux.crypto_trading.facade.TransactionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    TransactionFacade transactionFacade;
    @PostMapping
    public ResponseEntity<BaseResponseDto<String>> createTransaction(@RequestBody TransactionCreateDto transactionCreateDto) {
        try{
            transactionFacade.safeCreateTransaction(transactionCreateDto);
            return ResponseEntity.ok(BaseResponseDto.<String>builder().data("SUCCESS").status(true).build());
        }catch (RuntimeException re){
            return ResponseEntity.badRequest().body(BaseResponseDto
                                            .<String>builder()
                                            .data("FAILED")
                                            .status(false)
                                            .errorMsg(re.getMessage())
                                            .build());
        }

    }
}
