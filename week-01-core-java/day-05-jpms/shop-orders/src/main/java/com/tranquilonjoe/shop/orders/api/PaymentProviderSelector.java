package com.tranquilonjoe.shop.orders.api;

import com.tranquilonjoe.shop.payments.api.PaymentProvider;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ServiceLoader;

public class PaymentProviderSelector {

    private final Map<String, PaymentProvider> providersByName;

    public PaymentProviderSelector() {

        Map<String, PaymentProvider> providers = new HashMap<>();

        ServiceLoader<PaymentProvider> loader =
                ServiceLoader.load(PaymentProvider.class);

        for (PaymentProvider provider : loader) {

            String name = provider.getProviderName()
                    .toUpperCase(Locale.ROOT);

            if (providers.putIfAbsent(name, provider) != null) {
                throw new IllegalStateException(
                        "Duplicate payment provider: " + name
                );
            }
        }

        this.providersByName = Map.copyOf(providers);
    }

    public PaymentProvider selectProvider(String providerName) {

        if (providerName == null) {
            throw new IllegalArgumentException(
                    "Payment provider name cannot be null"
            );
        }

        PaymentProvider provider = providersByName.get(
                providerName.toUpperCase(Locale.ROOT)
        );

        if (provider == null) {
            throw new IllegalArgumentException(
                    "Unsupported payment provider: " + providerName
            );
        }

        return provider;
    }
}