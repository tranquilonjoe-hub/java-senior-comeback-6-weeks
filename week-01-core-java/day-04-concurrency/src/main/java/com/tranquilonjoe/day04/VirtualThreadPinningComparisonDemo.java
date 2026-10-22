package com.tranquilonjoe.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class VirtualThreadPinningComparisonDemo {

    private static final int TASK_COUNT = 20;
    private static final int SLEEP_MS = 1000;

    public static void main(String[] args) throws Exception {

        System.out.println("=== SYNCHRONIZED ===");
        runExperiment(true);

        System.out.println();

        System.out.println("=== REENTRANT LOCK ===");
        runExperiment(false);
    }

    private static void runExperiment(boolean useSynchronized)
            throws Exception {

        long start = System.nanoTime();

        try (ExecutorService executor =
                     Executors.newVirtualThreadPerTaskExecutor()) {

            List<Future<?>> futures = new ArrayList<>();

            for (int i = 0; i < TASK_COUNT; i++) {

                futures.add(executor.submit(() -> {

                    if (useSynchronized) {

                        // Independent monitor for each task
                        Object lock = new Object();

                        synchronized (lock) {
                            sleep();
                        }

                    } else {

                        // Independent lock for each task
                        ReentrantLock lock = new ReentrantLock();

                        lock.lock();

                        try {
                            sleep();
                        } finally {
                            lock.unlock();
                        }
                    }
                }));
            }

            for (Future<?> future : futures) {
                future.get();
            }
        }

        double seconds =
                (System.nanoTime() - start) / 1_000_000_000.0;

        System.out.printf(
                "%s: %.3f seconds%n",
                useSynchronized ? "Synchronized" : "ReentrantLock",
                seconds
        );
    }

    private static void sleep() {
        try {
            Thread.sleep(SLEEP_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

