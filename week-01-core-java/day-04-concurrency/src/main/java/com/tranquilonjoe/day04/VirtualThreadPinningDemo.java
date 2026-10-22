package com.tranquilonjoe.day04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VirtualThreadPinningDemo {

    private static final Object LOCK = new Object();

    public static void main(String[] args) throws Exception {

        long start = System.nanoTime();

        try (ExecutorService executor =
                     Executors.newVirtualThreadPerTaskExecutor()) {

            Future<?> future = executor.submit(() -> {

                synchronized (LOCK) {
                    System.out.println(
                            "Inside synchronized | Virtual: " +
                                    Thread.currentThread().isVirtual()
                    );
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
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
