package com.tranquilonjoe.day04;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerDemo {

    private static final String STOP = "STOP";

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> orderQueue = new ArrayBlockingQueue<>(2);

        System.out.println("Queue created successfully!");

        Runnable producerTask = () -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    String order = "Order-" + i;
                    orderQueue.put(order);
                    System.out.println(Thread.currentThread()
                            .getName() + " produced: " + order);
                }
                orderQueue.put(STOP);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Production interrupted");
            }
        };

        Runnable consumerTask = () -> {
            try {
                while (true) {
                    String order = orderQueue.take();
                    if (STOP.equals(order)) {
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + " Consumed: " + order);
                    Thread.sleep(1000);
                }
                System.out.println(Thread.currentThread().getName() + " finished");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumption interrupted");
            }
        };

        Thread producer = new Thread(producerTask, "Producer-Thread");
        Thread consumer = new Thread(consumerTask, "Consumer-Thread");


        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
        System.out.println("All orders processed successfully!");
    }
}
