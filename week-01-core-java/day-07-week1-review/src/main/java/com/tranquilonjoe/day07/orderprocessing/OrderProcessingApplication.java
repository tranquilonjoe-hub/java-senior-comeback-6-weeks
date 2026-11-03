package com.tranquilonjoe.day07.orderprocessing;

import java.math.BigDecimal;

public class OrderProcessingApplication {

    public static void main(String[] args) throws InterruptedException {
        OrderService orderService = new OrderService();
        OrderProcessor orderProcessor = new OrderProcessor(3);

        Thread worker1 = new Thread(
                orderProcessor::processOrders,
                "order-worker-1"
        );

        Thread worker2 = new Thread(
                orderProcessor::processOrders,
                "order-worker-2"
        );

        worker1.start();
        worker2.start();

        Order order1 = new Order(
                100,
                "Nirmal",
                new BigDecimal("1500.00")
        );
        Order order2 = new Order(
                101,
                "Bob",
                new BigDecimal("1600.00")
        );
        Order order3 = new Order(
                103,
                "Alice",
                new BigDecimal("1700.00")
        );
        Order order4 = new Order(
                104,
                "David",
                new BigDecimal("1750.00")
        );
        Order order5 = new Order(
                105,
                "Charlie",
                new BigDecimal("1700.00")
        );

        Order order6 = new Order(
                106,
                "Nirmal",
                new BigDecimal("1700.00")
        );
        Order order7 = new Order(
                107,
                "Alice",
                new BigDecimal("1800.00")
        );
        Order order8 = new Order(
                108,
                "Tango",
                new BigDecimal("1900.00")
        );

        orderService.addOrder(order1);
        orderService.addOrder(order2);
        orderService.addOrder(order3);
        orderService.addOrder(order4);
        orderService.addOrder(order5);
        orderService.addOrder(order6);
        orderService.addOrder(order7);
        orderService.addOrder(order8);

        Thread producer = new Thread(() -> {

            for (Order order : orderService.getAllOrders()) {
                try {

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " submitting Order " + order.getOrderId()
                    );

                    orderProcessor.submitOrder(order);

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " submitted Order " + order.getOrderId()
                    );
                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();
                    System.out.println("Producer interrupted. Stopping.");
                    break;
                }
            }
        }, "order-producer");

        producer.start();
        producer.join();

        orderProcessor.submitShutdown();
        orderProcessor.submitShutdown();

        worker1.join();
        worker2.join();

        for (Order order : orderService.getAllOrders()) {
            System.out.println(
                    "Order status for order Id:"
                            + order.getOrderId() + " is " + order.getStatus());
        }
    }
}
