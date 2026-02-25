package com.aquariux.crypto_trading.context;

import org.springframework.stereotype.Component;

@Component
public class SecurityContext {
    public Long getCurrentUserId(){
        // Hard-code for now
        return 1L;
    }
}
