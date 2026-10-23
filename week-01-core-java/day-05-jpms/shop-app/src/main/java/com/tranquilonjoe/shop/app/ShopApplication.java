package com.tranquilonjoe.shop.app;

import com.tranquilonjoe.shop.orders.api.OrderService;

public class ShopApplication {

    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        orderService.placeOrder(1500.00);
        orderService.placeOrder(-100.00);
    }
}