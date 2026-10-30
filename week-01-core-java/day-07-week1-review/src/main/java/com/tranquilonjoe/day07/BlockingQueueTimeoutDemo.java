package com.tranquilonjoe.day07;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTimeoutDemo {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
        BlockingQueue<Integer> emptyQueue = new ArrayBlockingQueue<>(2);

        Thread producer = new Thread(() -> {
            try {
                queue.put(10);
                queue.put(20);
                long start = System.nanoTime();
                boolean result = queue.offer(30, 3, TimeUnit.SECONDS);
                long elapsedMs =
                        java.util.concurrent.TimeUnit.NANOSECONDS.toMillis(
                                System.nanoTime() - start
                        );

                System.out.println(Thread.currentThread().getName()
                        + " inserted: " + result
                        + " | waited: " + elapsedMs + " ms");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            try {
                long start = System.nanoTime();
                Integer result = emptyQueue.poll(2, TimeUnit.SECONDS);
                long elapsedMs =
                        java.util.concurrent.TimeUnit.NANOSECONDS.toMillis(
                                System.nanoTime() - start
                        );

                System.out.println(Thread.currentThread().getName()
                        + " removed: " + result
                        + " | waited : " + elapsedMs + " ms");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
