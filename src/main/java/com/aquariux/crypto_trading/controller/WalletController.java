package com.aquariux.crypto_trading.controller;

import com.aquariux.crypto_trading.dto.response.BaseResponseDto;
import com.aquariux.crypto_trading.dto.wallet.WalletGetResponseDto;
import com.aquariux.crypto_trading.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    WalletService walletService;

    @GetMapping("/getAll")
    public ResponseEntity<BaseResponseDto<List<WalletGetResponseDto>>> queryBalance() {
        List<WalletGetResponseDto> wallets = walletService.getWallets();
        BaseResponseDto<List<WalletGetResponseDto>> response = BaseResponseDto.<List<WalletGetResponseDto>>builder()
                .data(wallets)
                .status(true)
                .errorMsg("")
                .build();
        return ResponseEntity.ok(response);
    }
}
