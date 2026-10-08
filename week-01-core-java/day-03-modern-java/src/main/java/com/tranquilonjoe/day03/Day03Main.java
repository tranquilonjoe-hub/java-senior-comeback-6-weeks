package com.tranquilonjoe.day03;

public class Day03Main {

    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor();
        Order onlineOrder = new OnlineOrder(1, "Nirmal", 1000, "Bangalore");
        Order storePickup = new StorePickupOrder(2, "Nirmal", 1000, "Bangalore");
        Order intnlOrder = new InternationalOrder(3, "Bob", 1000, "Bangalore");

        System.out.println(processor.calculateFinalAmount(onlineOrder));
        System.out.println(processor.calculateFinalAmount(storePickup));
        System.out.println(processor.calculateFinalAmount(intnlOrder));

        //Tests
        try {
            new OnlineOrder(-1, "John", 1000, "Bangalore");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            new StorePickupOrder(5, " ", 1000, "Bangalore");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            new InternationalOrder(6, "Nirmal", Double.NaN, "Bangalore");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            new OnlineOrder(7, "Nirmal", 1000, null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            processor.calculateFinalAmount(null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
