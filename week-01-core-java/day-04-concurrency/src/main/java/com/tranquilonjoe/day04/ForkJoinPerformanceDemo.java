package com.tranquilonjoe.day04;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinPerformanceDemo {

    public static void main(String[] args) {

        int size = 10_000_000;
        int[] numbers = new int[size];

        Arrays.fill(numbers, 1);

        ForkJoinPool pool = new ForkJoinPool(4);

        try {
            // Warm up the JVM
            for (int i = 0; i < 5; i++) {
                sequentialSum(numbers);
                forkJoinSum(pool, numbers);
                parallelStreamSum(numbers);
            }

            int runs = 20;

            double sequentialMs = averageTimeMs(
                    () -> sequentialSum(numbers), runs);

            double forkJoinMs = averageTimeMs(
                    () -> forkJoinSum(pool, numbers), runs);

            double parallelMs = averageTimeMs(
                    () -> parallelStreamSum(numbers), runs);

            System.out.printf("Sequential average: %.3f ms%n", sequentialMs);
            System.out.printf("ForkJoin average: %.3f ms%n", forkJoinMs);
            System.out.printf("Parallel average: %.3f ms%n", parallelMs);

        } finally {
            pool.shutdown();
        }
    }

    private static long sequentialSum(int[] numbers) {
        long sum = 0;

        for (int number : numbers) {
            sum += number;
        }

        return sum;
    }

    private static long forkJoinSum(
            ForkJoinPool pool,
            int[] numbers) {

        return pool.invoke(
                new ArraySumTask(numbers, 0, numbers.length)
        );
    }

    private static long parallelStreamSum(int[] numbers) {
        return Arrays.stream(numbers)
                .parallel()
                .asLongStream()
                .sum();
    }

    private static double averageTimeMs(Runnable task, int runs) {

        long totalTime = 0;

        for (int i = 0; i < runs; i++) {

            long start = System.nanoTime();

            task.run();

            totalTime += System.nanoTime() - start;
        }

        return totalTime / (runs * 1_000_000.0);
    }
}