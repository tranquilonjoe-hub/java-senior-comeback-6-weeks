package com.tranquilonjoe.service;

import com.tranquilonjoe.common.Order;

import java.math.BigDecimal;

public class OrderService {

    public BigDecimal calculateFinalAmount(Order order) {

        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        if (order.amount() == null
                || order.amount().signum() < 0) {
            throw new IllegalArgumentException(
                    "Order amount must be non-negative"
            );
        }

        return order.amount().multiply(new BigDecimal("1.18"));
    }
}