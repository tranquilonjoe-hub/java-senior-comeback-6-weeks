package com.tranquilonjoe.day03;

public record InternationalOrder(
        int orderId,
        String customerName,
        double amount,
        String country
) implements Order {
    public InternationalOrder {
        Order.validateOrderId(orderId);
        Order.validateAmount(amount);
        Order.validateRequiredText(customerName, "Customer Name");
        Order.validateRequiredText(country, "Country");
    }
}
