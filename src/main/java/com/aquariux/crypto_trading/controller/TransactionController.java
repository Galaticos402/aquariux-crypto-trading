package com.aquariux.crypto_trading.controller;

import com.aquariux.crypto_trading.dto.response.BaseResponseDto;
import com.aquariux.crypto_trading.dto.transaction.TransactionCreateDto;
import com.aquariux.crypto_trading.dto.transaction.TransactionGetResponseDto;
import com.aquariux.crypto_trading.facade.TransactionFacade;
import com.aquariux.crypto_trading.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    TransactionFacade transactionFacade;
    @Autowired
    TransactionService transactionService;

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
    @GetMapping("/history")
    public ResponseEntity<BaseResponseDto<List<TransactionGetResponseDto>>> queryTransactionHistory(){
        List<TransactionGetResponseDto> history = transactionService.queryTransactionHistory();
        BaseResponseDto<List<TransactionGetResponseDto>> responseDto = BaseResponseDto
                .<List<TransactionGetResponseDto>>builder()
                .data(history)
                .status(true)
                .errorMsg("")
                .build();
        return ResponseEntity.ok(responseDto);
    }
}
