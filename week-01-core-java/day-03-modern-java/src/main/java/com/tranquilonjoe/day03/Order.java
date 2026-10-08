package com.tranquilonjoe.day03;

public sealed interface Order
        permits OnlineOrder, StorePickupOrder, InternationalOrder {

    int orderId() ;

    String customerName();

    double amount();

    static void validateOrderId(int orderId) {
        if (orderId < 1) {
            throw new IllegalArgumentException("Order id must be a positive value");
        }
    }

    static void validateAmount(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Amount must be a finite positive value");
        }
    }

    static void validateRequiredText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " must not be blank"
            );
        }
    }
}

