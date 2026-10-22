package com.tranquilonjoe.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class VirtualThreadLocalDemo {

    private static final ThreadLocal<String> REQUEST_ID =
            new ThreadLocal<>();

    public static void main(String[] args) throws Exception {

        try (ExecutorService executor =
                     Executors.newVirtualThreadPerTaskExecutor()) {

            List<Future<?>> futures = new ArrayList<>();

            for (int i = 1; i <= 5; i++) {

                final int taskId = i;

                futures.add(executor.submit(() -> {
                    try {
                        REQUEST_ID.set("Request-" + taskId);

                        System.out.printf(
                                "Task %d | Thread: %s | Request ID: %s%n",
                                taskId,
                                Thread.currentThread(),
                                REQUEST_ID.get()
                        );

                        processRequest();

                    } finally {
                        REQUEST_ID.remove();
                    }
                }));
            }
            for (Future<?> future : futures) {
                future.get();
            }
        }
    }

    private static void processRequest() {
        System.out.printf(
                "Processing request: %s | Virtual: %s%n",
                REQUEST_ID.get(),
                Thread.currentThread().isVirtual()
        );
    }
}
