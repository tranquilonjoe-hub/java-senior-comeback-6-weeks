module com.tranquilonjoe.shop.orders {
    requires com.tranquilonjoe.shop.payments;

    exports com.tranquilonjoe.shop.orders.api;

    uses com.tranquilonjoe.shop.payments.api.PaymentProvider;
}