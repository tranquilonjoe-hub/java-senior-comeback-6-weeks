package com.tranquilonjoe.day07.orderprocessing;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;

public class OrderProcessingInterruptionDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch worker1Started = new CountDownLatch(1);
        CountDownLatch allowWorker1ToProceed = new CountDownLatch(1);

        OrderProcessor orderProcessor = new OrderProcessor(
                3,
                worker1Started,
                allowWorker1ToProceed
        );

        Thread worker1 = new Thread(
                orderProcessor::processOrders,
                "order-worker-1"
        );

        Thread worker2 = new Thread(
                orderProcessor::processOrders,
                "order-worker-2"
        );

        worker1.start();
        //worker2.start();

        Order order100 = new Order(
                100,
                "Nirmal",
                new BigDecimal("1500.00")
        );

        orderProcessor.submitOrder(order100);

        worker1Started.await();

        System.out.println(
                "Main: Worker 1 has started processing Order 100"
        );
        worker2.start();
        System.out.println("Main: Interrupting Worker 1");

        worker1.interrupt();
        worker1.join();

        System.out.println(
                "Main: Order 100 status = " + order100.getStatus()
        );

        Order order101 = new Order(
                101,
                "Bob",
                new BigDecimal("1600.00")
        );

        Order order102 = new Order(
                102,
                "Alice",
                new BigDecimal("1700.00")
        );

        orderProcessor.submitOrder(order101);

        try {
            orderProcessor.submitOrder(order101);
        } catch (IllegalStateException e) {
            System.out.println("Main: " + e.getMessage());
        }

        orderProcessor.submitOrder(order102);
        orderProcessor.submitShutdown();
        worker2.join();

        System.out.println("Order 100: " + order100.getStatus());
        System.out.println("Order 101: " + order101.getStatus());
        System.out.println("Order 102: " + order102.getStatus());
    }
}
