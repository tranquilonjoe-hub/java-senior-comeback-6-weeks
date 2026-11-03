package com.tranquilonjoe.day07.orderprocessing;

import java.math.BigDecimal;

public class OrderDemo {
    public static void main(String[] args) {
        Order order = new Order(
                100,
                "Nirmal",
                new BigDecimal("1500.00")
        );

        System.out.println(order);
        order.updateStatus(OrderStatus.PROCESSING);
        System.out.println(order);
        order.updateStatus(OrderStatus.COMPLETED);
        System.out.println(order);

        try {
            order.updateStatus(OrderStatus.FAILED);
        } catch (IllegalStateException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        System.out.println(order);

        Order order1 = new Order(
                101,
                "Boss",
                new BigDecimal("1600.00")
        );

        System.out.println(order1);

        try {
            order1.updateStatus(OrderStatus.COMPLETED);
        } catch (IllegalStateException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        System.out.println(order1);

        try {
            new Order(
                    -1,
                    "Nirmal",
                    new BigDecimal("1500.00")
            );

            System.out.println("ERROR: Invalid order was accepted");

        } catch (IllegalArgumentException e) {
            System.out.println("Expected exception: " + e.getMessage());
        }

        try {
            new Order(
                    2,
                    " ",
                    new BigDecimal("1500.00")
            );
            System.out.println("ERROR: Invalid customer name was accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("Expected exception: " + e.getMessage());
        }

        try {
            new Order(
                    3,
                    null,
                    new BigDecimal("1500.00")
            );
            System.out.println("ERROR: Invalid customer name was accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("Expected exception: " + e.getMessage());
        }

        try {
            new Order(
                    4,
                    "Reece",
                    new BigDecimal("0.0")
            );
            System.out.println("ERROR: Invalid amount was accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("Expected exception: " + e.getMessage());
        }

        try {
            new Order(
                    5,
                    "James",
                    new BigDecimal("-100.00")
            );
            System.out.println("ERROR: Invalid amount was accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("Expected exception: " + e.getMessage());
        }

        try {
            new Order(
                    5,
                    "John",
                    null
            );
            System.out.println("ERROR: Invalid amount was accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
    }
}
