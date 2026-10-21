package com.tranquilonjoe.day04;

import java.util.concurrent.RecursiveTask;

public class ArraySumTask extends RecursiveTask<Long> {

    public static final int THRESHOLD = 50_000;;

    private final int[] numbers;
    private final int start;
    private final int end;

    public ArraySumTask(int[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        int length = end - start;

        /*System.out.printf(
                "[%s] Processing range [%d, %d), length=%d%n",
                Thread.currentThread().getName(),
                start,
                end,
                length
        );*/

        // Base case: calculate directly
        if (length <= THRESHOLD) {
            long sum = 0;

            for (int i = start; i < end; i++) {
                sum += numbers[i];
            }

            /*System.out.printf(
                    "[%s] Completed range [%d, %d), sum=%d%n",
                    Thread.currentThread().getName(),
                    start,
                    end,
                    sum
            );*/

            return sum;
        }

        // Divide into two subtasks
        int middle = start + length / 2;
        ArraySumTask left =
                new ArraySumTask(numbers, start, middle);
        ArraySumTask right =
                new ArraySumTask(numbers, middle, end);

        left.fork();

        long rightResult = right.compute();

        long leftResult = left.join();

        return leftResult + rightResult;
    }
}
