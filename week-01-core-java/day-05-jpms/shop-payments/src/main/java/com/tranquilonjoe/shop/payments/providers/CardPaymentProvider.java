package com.tranquilonjoe.shop.payments.providers;

import com.tranquilonjoe.shop.payments.api.PaymentProvider;

public class CardPaymentProvider implements PaymentProvider {

    @Override
    public String getProviderName() {
        return "CARD";
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Processing card payment: " + amount);
        return amount > 0;
    }
}