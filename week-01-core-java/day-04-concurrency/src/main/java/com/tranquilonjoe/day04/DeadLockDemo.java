package com.tranquilonjoe.day04;

public class DeadLockDemo {

    private static final Object LOCK_A = new Object();
    private static final Object LOCK_B = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            synchronized (LOCK_A) {
                System.out.println("Thread-1 acquired LOCK_A");

                sleep(1000);

                System.out.println("Thread-1 waiting for LOCK_B");

                synchronized (LOCK_B) {
                    System.out.println("Thread-1 acquired LOCK_B");
                }
            }

        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            synchronized (LOCK_A) {
                System.out.println("Thread-2 acquired LOCK_A");

                sleep(1000);

                System.out.println("Thread-2 waiting for LOCK_B");

                synchronized (LOCK_B) {
                    System.out.println("Thread-2 acquired LOCK_B");
                }
            }

        }, "Thread-2");

        thread1.start();
        thread2.start();

    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
