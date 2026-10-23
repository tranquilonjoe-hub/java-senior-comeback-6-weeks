package com.tranquilonjoe.shop.payments.providers;

import com.tranquilonjoe.shop.payments.api.PaymentProvider;

public class UpiPaymentProvider implements PaymentProvider {

    @Override
    public String getProviderName() {
        return "UPI";
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Processing UPI payment: ₹" + amount);
        return amount > 0;
    }
}