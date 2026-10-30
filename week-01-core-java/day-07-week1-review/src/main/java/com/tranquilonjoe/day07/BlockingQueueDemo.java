package com.tranquilonjoe.day07;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);

        Thread producer = new Thread(() -> {
            try {
                int[] values = {10, 20, 30, 40};
                for (int value : values) {
                    System.out.println(Thread.currentThread().getName()
                            + " attempting " + value);
                    long start = System.nanoTime();
                    queue.put(value);
                    long elapsedMs =
                            java.util.concurrent.TimeUnit.NANOSECONDS.toMillis(
                                    System.nanoTime() - start
                            );

                    System.out.println(Thread.currentThread().getName()
                            + " inserted " + value
                            + " | waited: " + elapsedMs + " ms");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer interrupted");
            }

        }, "producer");

        Thread consumer = new Thread(() -> {
            try {
                Thread.sleep(2000);

                for (int i = 1; i <= 4; i++) {

                    Integer value = queue.take();

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " processed: " + value
                    );
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer interrupted");
            }
        }, "consumer");

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
