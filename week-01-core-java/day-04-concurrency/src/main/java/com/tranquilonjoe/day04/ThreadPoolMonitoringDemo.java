package com.tranquilonjoe.day04;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolMonitoringDemo {

    private static void processPayment(int paymentId) {

        String threadName = Thread.currentThread().getName();

        System.out.printf(
                "Payment %d started on %s%n",
                paymentId,
                threadName
        );

        try {
            Thread.sleep(1000);
            System.out.printf(
                    "Payment %d completed on %s%n",
                    paymentId,
                    threadName
            );
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.printf(
                    "Payment %d is interrupted%n",
                    paymentId
            );
        }
    }

    public static void main(String[] args) {

        AtomicInteger accepted = new AtomicInteger();
        AtomicInteger rejected = new AtomicInteger();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                4,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                new ThreadPoolExecutor.AbortPolicy()
        );

        System.out.println("Executor created");

        Thread monitor = new Thread(() -> {
            try {
                while (!executor.isTerminated()) {
                    System.out.printf("[Monitor] Pool %d | Active %d | Queue %d | Completed %d%n",
                            executor.getPoolSize(),
                            executor.getActiveCount(),
                            executor.getQueue().size(),
                            executor.getCompletedTaskCount()
                    );

                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "pool-monitor");

        monitor.start();

        try {
            for (int i = 1; i <= 10; i++) {
                final int paymentId = i;
                try {
                    executor.execute(() -> processPayment(paymentId));
                    accepted.incrementAndGet();
                    System.out.println("Payment " + paymentId + " accepted");
                } catch (RejectedExecutionException e) {
                    System.out.println("Payment " + paymentId + " rejected");
                    rejected.incrementAndGet();
                }
            }
            System.out.println("Payment submission phase finished");
        } finally {

            executor.shutdown();

            try {
                boolean terminated =
                        executor.awaitTermination(30, TimeUnit.SECONDS);

                System.out.println("Executor terminated: " + terminated);

                if (!terminated) {
                    executor.shutdownNow();
                }

                monitor.interrupt();
                monitor.join();

            } catch (InterruptedException e) {
                executor.shutdownNow();
                monitor.interrupt();
                Thread.currentThread().interrupt();
            }
        }


        System.out.println("\n=== FINAL EXECUTOR SUMMARY ===");

        System.out.println("Accepted: " + accepted.get());

        System.out.println("Rejected: " + rejected.get());

        System.out.println(
                "Terminated: " + executor.isTerminated()
        );

        System.out.println(
                "Completed tasks: " + executor.getCompletedTaskCount()
        );

        System.out.println(
                "Remaining queued tasks: " + executor.getQueue().size()
        );
    }
}
