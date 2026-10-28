package com.tranquilonjoe.app;

import com.tranquilonjoe.common.Order;
import com.tranquilonjoe.service.OrderService;

import java.math.BigDecimal;

public class OrderApplication {
    public static void main(String[] args) {
        Order order = new Order(
                101L,
                "John",
                new BigDecimal("1000.00")
        );

        OrderService orderService = new OrderService();

        BigDecimal finalAmount =
                orderService.calculateFinalAmount(order);

        System.out.println("Order ID: " + order.id());
        System.out.println("Customer: " + order.customerName());
        System.out.println("Original Amount: " + order.amount());
        System.out.println("Final Amount: " + finalAmount);
    }
}