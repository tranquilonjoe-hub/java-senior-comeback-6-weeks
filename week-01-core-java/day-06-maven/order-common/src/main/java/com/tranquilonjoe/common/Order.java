package com.tranquilonjoe.common;

import java.math.BigDecimal;

public record Order(
        Long id,
        String customerName,
        BigDecimal amount
){}