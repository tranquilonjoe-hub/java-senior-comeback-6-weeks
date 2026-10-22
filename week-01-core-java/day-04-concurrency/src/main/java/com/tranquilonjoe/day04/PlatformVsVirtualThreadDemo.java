package com.tranquilonjoe.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PlatformVsVirtualThreadDemo {

    public static void main(String[] args) throws Exception{

        System.out.println("=== PLATFORM THREADS ===");

        try (ExecutorService executor =
                     Executors.newFixedThreadPool(4)) {
                runTasks(executor);
        }
        System.out.println();

        System.out.println("=== VIRTUAL THREADS ===");

        try(ExecutorService executor =
                    Executors.newVirtualThreadPerTaskExecutor()){
                runTasks(executor);
        }

    }

    private static void runTasks(ExecutorService executor)
            throws Exception {

        long start = System.nanoTime();

        List<Future<?>> futures = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {

            final int taskId = i;

            futures.add(executor.submit(() -> {

                System.out.printf(
                        "Task %d | Virtual: %s | Thread: %s%n",
                        taskId,
                        Thread.currentThread().isVirtual(),
                        Thread.currentThread()
                );

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }));
        }

        for (Future<?> future : futures) {
            future.get();
        }

        long elapsed = System.nanoTime() - start;

        System.out.printf(
                "Completed 10 tasks in %.3f seconds%n",
                elapsed / 1_000_000_000.0
        );

    }
}
