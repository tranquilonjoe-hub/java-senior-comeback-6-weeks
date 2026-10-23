package com.tranquilonjoe.shop.payments.api;

import com.tranquilonjoe.shop.payments.internal.PaymentValidator;

public class PaymentService {

    private final PaymentValidator validator = new PaymentValidator();

    public boolean processPayment(double amount) {

        if (!validator.isValid(amount)) {
            System.out.println("Invalid payment amount: ₹" + amount);
            return false;
        }

        System.out.println("Processing payment: ₹" + amount);
        return true;
    }
}