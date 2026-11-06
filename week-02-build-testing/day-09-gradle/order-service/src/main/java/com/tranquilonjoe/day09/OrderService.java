package com.tranquilonjoe.day09;

public class OrderService {
    public Order createOrder(long id, String customerName, double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Order amount must be greater than zero"
            );
        }

        return new Order(id, customerName, amount, "CREATED");
    }
}
