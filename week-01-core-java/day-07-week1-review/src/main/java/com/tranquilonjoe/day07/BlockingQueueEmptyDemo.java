package com.tranquilonjoe.day07;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueEmptyDemo {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);

        Thread consumer = new Thread(() -> {
            try {
                System.out.println("Consumer attempting take()");

                long start = System.nanoTime();

                Integer value = queue.take();

                long waitedMs = TimeUnit.NANOSECONDS.toMillis(
                        System.nanoTime() - start
                );

                System.out.println("Consumer received: " + value
                        + " | waited: " + waitedMs + " ms");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "consumer");

        Thread producer = new Thread(() -> {
            try {

                Thread.sleep(2000);
                queue.put(100);
                System.out.println(Thread.currentThread().getName()
                        + " inserted ");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer interrupted");
            }

        }, "Producer");

        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
    }
}
