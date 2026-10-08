package com.tranquilonjoe.day03;

public class OrderProcessor {
    public double calculateFinalAmount(Order order) {

        return switch (order) {
            case null -> throw new IllegalArgumentException("Order type must be specified");
            case OnlineOrder online -> online.amount() + 50.00;
            case StorePickupOrder pickup -> pickup.amount();
            case InternationalOrder intnlOrder -> intnlOrder.amount() + (intnlOrder.amount() * 10) / 100;
        };
    }

}
