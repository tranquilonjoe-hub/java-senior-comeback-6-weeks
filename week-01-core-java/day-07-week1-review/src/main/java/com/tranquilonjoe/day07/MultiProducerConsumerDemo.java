package com.tranquilonjoe.day07;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MultiProducerConsumerDemo {

    private static final int POISON_PILL = -1;

    public static void main(String[] args)
            throws InterruptedException {

        BlockingQueue<Integer> queue =
                new ArrayBlockingQueue<>(3);

        Thread producer1 = new Thread(() -> {
            try {
                queue.put(10);
                queue.put(20);
                queue.put(30);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer-1");

        Thread producer2 = new Thread(() -> {
            try {
                queue.put(40);
                queue.put(50);
                queue.put(60);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer-2");

        Thread consumer1 = new Thread(() -> {
            try {
                while (true) {
                    Integer value = queue.take();
                    if (value == POISON_PILL) {
                        System.out.println(
                                Thread.currentThread().getName()
                                        + " Shutting Down"
                        );
                        break;
                    }
                    System.out.println(
                            Thread.currentThread().getName()
                                    + " processed: " + value
                    );
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer-1");

        Thread consumer2 = new Thread(() -> {
            try {
                while (true) {
                    Integer value = queue.take();
                    if (value == POISON_PILL) {
                        System.out.println(
                                Thread.currentThread().getName()
                                        + " Shutting Down"
                        );
                        break;
                    }
                    System.out.println(
                            Thread.currentThread().getName()
                                    + " processed: " + value
                    );
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer-2");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        producer1.join();
        producer2.join();

        queue.put(POISON_PILL);
        queue.put(POISON_PILL);

        consumer1.join();
        consumer2.join();
    }

}
