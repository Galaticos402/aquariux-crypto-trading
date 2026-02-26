package com.aquariux.crypto_trading.controller;

import com.aquariux.crypto_trading.dto.response.BaseResponseDto;
import com.aquariux.crypto_trading.dto.response.PageResponse;
import com.aquariux.crypto_trading.dto.transaction.TransactionCreateDto;
import com.aquariux.crypto_trading.dto.transaction.TransactionGetResponseDto;
import com.aquariux.crypto_trading.facade.TransactionFacade;
import com.aquariux.crypto_trading.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<BaseResponseDto<Long>> createTransaction(@RequestBody TransactionCreateDto transactionCreateDto) {
        try{
            Long transactionId = transactionFacade.safeCreateTransaction(transactionCreateDto);
            return ResponseEntity.ok(BaseResponseDto.<Long>builder().data(transactionId).status(true).errorMsg("").build());
        }catch (RuntimeException re){
            return ResponseEntity.badRequest().body(BaseResponseDto
                                            .<Long>builder()
                                            .data(null)
                                            .status(false)
                                            .errorMsg(re.getMessage())
                                            .build());
        }
    }
    @GetMapping("/history")
    public ResponseEntity<BaseResponseDto<PageResponse<TransactionGetResponseDto>>> queryTransactionHistory(@RequestParam(defaultValue = "0") int page,
                                                                                                            @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<TransactionGetResponseDto> history = transactionService.queryTransactionHistory(pageable);
        BaseResponseDto<PageResponse<TransactionGetResponseDto>> responseDto = BaseResponseDto
                .<PageResponse<TransactionGetResponseDto>>builder()
                .data(PageResponse.from(history))
                .status(true)
                .errorMsg("")
                .build();
        return ResponseEntity.ok(responseDto);
    }
}
