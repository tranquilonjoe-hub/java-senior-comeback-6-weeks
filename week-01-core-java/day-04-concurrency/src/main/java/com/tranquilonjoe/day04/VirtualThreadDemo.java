package com.tranquilonjoe.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class VirtualThreadDemo {

    public static void main(String[] args) throws Exception {

        System.out.println(
                "Available processors: " +
                        Runtime.getRuntime().availableProcessors()
        );

        long start = System.nanoTime();

        try (ExecutorService executor =
                     Executors.newVirtualThreadPerTaskExecutor()) {

            List<Future<?>> futures = new ArrayList<>();

            for (int i = 1; i <= 10; i++) {

                final int taskId = i;

                Future<?> future = executor.submit(() -> {

                    Thread thread = Thread.currentThread();

                    System.out.printf(
                            "Task %d | Virtual: %s | Thread: %s%n",
                            taskId,
                            thread.isVirtual(),
                            thread
                    );
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
                futures.add(future);
            }
            for (Future<?> future : futures) {
                future.get();
            }
        }

        long elapsed = System.nanoTime() - start;

        System.out.printf(
                "All tasks completed in %.3f seconds%n",
                elapsed / 1_000_000_000.0
        );

    }
}
