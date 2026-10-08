package com.tranquilonjoe.day03;

public record OnlineOrder(
        int orderId,
        String customerName,
        double amount,
        String deliveryAddress
) implements Order {
    public OnlineOrder {
        Order.validateOrderId(orderId);
        Order.validateAmount(amount);
        Order.validateRequiredText(customerName, "Customer Name");
        Order.validateRequiredText(deliveryAddress, "Delivery Address");
    }
}
