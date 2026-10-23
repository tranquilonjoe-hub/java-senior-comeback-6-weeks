package com.tranquilonjoe.shop.orders.reflection;

import com.tranquilonjoe.shop.payments.api.PaymentProvider;

import java.util.ServiceLoader;

public class PaymentServiceLoaderDemo {

    public static void main(String[] args) {

        ServiceLoader<PaymentProvider> loader =
                ServiceLoader.load(PaymentProvider.class);

        for (PaymentProvider provider : loader) {

            System.out.println(
                    "Discovered provider: " + provider.getProviderName()
            );

            boolean success = provider.pay(1500.00);

            System.out.println("Payment success: " + success);
        }
    }
}