package com.tranquilonjoe.day07.orderprocessing;

import java.math.BigDecimal;
import java.util.Optional;

public class OrderServiceDemo {

    public static void main(String[] args) {

        OrderService orderService = new OrderService();

        Order order = new Order(
                100,
                "Nirmal",
                new BigDecimal("1500.00")
        );

        orderService.addOrder(order);

        orderService.getOrderById(100).
                ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Order not found"));


        orderService.getOrderById(999).
                ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Order not found"));

        Order duplicateOrder = new Order(
                100,
                "Nirmal",
                new BigDecimal("1500.00")
        );

        try {
            orderService.addOrder(duplicateOrder);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            orderService.addOrder(null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

}
