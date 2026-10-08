package com.tranquilonjoe.day03;

public record StorePickupOrder(
        int orderId,
        String customerName,
        double amount,
        String storeLocation
) implements Order {
    public StorePickupOrder {
        Order.validateOrderId(orderId);
        Order.validateAmount(amount);
        Order.validateRequiredText(customerName, "Customer Name");
        Order.validateRequiredText(storeLocation, "Store Location");
    }
}
