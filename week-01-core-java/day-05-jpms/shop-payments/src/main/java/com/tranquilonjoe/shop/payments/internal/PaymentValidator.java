package com.tranquilonjoe.shop.payments.internal;

public class PaymentValidator {

    public boolean isValid(double amount) {
        return amount > 0;
    }
}
