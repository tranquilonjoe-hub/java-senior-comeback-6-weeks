package com.tranquilonjoe.day04;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinDemo {

    public static void main(String[] args) {

        int[] numbers = {
                10, 20, 30, 40,
                50, 60, 70, 80
        };

        ForkJoinPool pool = new ForkJoinPool(4);

        try {
            ArraySumTask task =
                    new ArraySumTask(numbers, 0, numbers.length);

            Long result = pool.invoke(task);

            System.out.println("Total sum: "+result);

        } finally {
            pool.shutdown();
        }
    }
}
