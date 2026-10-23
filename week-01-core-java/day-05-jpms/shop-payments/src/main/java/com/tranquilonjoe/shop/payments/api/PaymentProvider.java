package com.tranquilonjoe.shop.payments.api;

public interface PaymentProvider {

    String getProviderName();

    boolean pay(double amount);
}
