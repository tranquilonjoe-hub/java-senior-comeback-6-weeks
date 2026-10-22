package com.tranquilonjoe.day04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class VirtualThreadReentrantLockDemo {

    private static final ReentrantLock LOCK = new ReentrantLock();

    public static void main(String[] args) throws Exception {

        long start = System.nanoTime();

        try (ExecutorService executor =
                     Executors.newVirtualThreadPerTaskExecutor()) {

            Future<?> future = executor.submit(() -> {

                LOCK.lock();
                try {
                    System.out.println(
                            "Inside ReentrantLock | Virtual: " +
                                    Thread.currentThread().isVirtual()
                    );

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    LOCK.unlock();
                }

            });

            future.get();
        }

        System.out.printf(
                "Completed in %.3f seconds%n",
                (System.nanoTime() - start) / 1_000_000_000.0
        );
    }
}
