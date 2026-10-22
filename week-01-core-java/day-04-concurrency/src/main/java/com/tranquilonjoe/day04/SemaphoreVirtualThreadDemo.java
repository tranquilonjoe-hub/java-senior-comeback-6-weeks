package com.tranquilonjoe.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreVirtualThreadDemo {

    private static final int TASK_COUNT = 10;

    private static final int MAX_CONCURRENT_CALLS = 3;

    private static final AtomicInteger ACTIVE_CALLS = new AtomicInteger(0);

    private static final AtomicInteger PEAK_CALLS = new AtomicInteger(0);

    private static final Semaphore SEMAPHORE = new Semaphore(MAX_CONCURRENT_CALLS, true);

    public static void main(String[] args) throws Exception {

        long start = System.nanoTime();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {

            List<Future<?>> futures = new ArrayList<>();

            for (int i = 1; i <= TASK_COUNT; i++) {

                final int taskId = i;

                futures.add(executor.submit(() -> {
                    callExternalService(taskId);
                }));
            }
            for (Future<?> future : futures) {
                future.get();
            }
        }
        System.out.println(
                "Peak concurrent calls: " + PEAK_CALLS.get()
        );

        System.out.printf(
                "Total time: %.3f seconds%n",
                (System.nanoTime() - start) / 1_000_000_000.0
        );
    }

    private static void callExternalService(int taskId) {

        boolean acquired = false;

        try {
            System.out.println(
                    "Task " + taskId + " waiting for permit"
            );

            SEMAPHORE.acquire();

            acquired = true;

            int active = ACTIVE_CALLS.incrementAndGet();

            PEAK_CALLS.accumulateAndGet(active, Math::max);
            try {
                System.out.printf(
                        "Task %d STARTED | Active calls: %d%n",
                        taskId, active
                );

                // Simulate a slow external API
                Thread.sleep(1000);
            } finally {
                int remaining = ACTIVE_CALLS.decrementAndGet();

                System.out.printf(
                        "Task %d FINISHED | Active calls: %d%n",
                        taskId, remaining
                );
            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

        } finally {
            if (acquired) {
                SEMAPHORE.release();
            }
        }
    }
}
