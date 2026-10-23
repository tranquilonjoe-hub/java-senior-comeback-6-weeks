module com.tranquilonjoe.shop.payments {

    exports com.tranquilonjoe.shop.payments.api;

    opens com.tranquilonjoe.shop.payments.config
            to com.tranquilonjoe.shop.orders;

    provides com.tranquilonjoe.shop.payments.api.PaymentProvider
            with com.tranquilonjoe.shop.payments.providers.UpiPaymentProvider,
                 com.tranquilonjoe.shop.payments.providers.CardPaymentProvider;
}