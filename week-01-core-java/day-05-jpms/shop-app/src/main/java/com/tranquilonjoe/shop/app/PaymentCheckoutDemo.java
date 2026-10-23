package com.tranquilonjoe.shop.app;

import com.tranquilonjoe.shop.orders.api.OrderService;

public class PaymentCheckoutDemo {

    public static void main(String[] args) {

        OrderService orderService = new OrderService();

        orderService.checkout(1500.00, "UPI");
        orderService.checkout(2000.00, "CARD");
    }
}