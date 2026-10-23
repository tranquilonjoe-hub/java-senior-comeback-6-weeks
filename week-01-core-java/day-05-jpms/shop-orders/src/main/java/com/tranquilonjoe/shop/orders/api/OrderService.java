package com.tranquilonjoe.shop.orders.api;
import com.tranquilonjoe.shop.payments.api.*;

public class OrderService {

    private final PaymentService paymentService = new PaymentService();
    private final PaymentProviderSelector selector =
            new PaymentProviderSelector();

    public void placeOrder(double amount) {
        if (paymentService.processPayment(amount)) {
            System.out.println("Order placed successfully!");
        } else {
            System.out.println("Payment failed. Order rejected.");
        }
    }

    public boolean checkout(double amount, String paymentMethod) {

        PaymentProvider provider =
                selector.selectProvider(paymentMethod);

        boolean success = provider.pay(amount);

        System.out.println(
                success ? "Order placed successfully"
                        : "Order payment failed"
        );

        return success;
    }
}