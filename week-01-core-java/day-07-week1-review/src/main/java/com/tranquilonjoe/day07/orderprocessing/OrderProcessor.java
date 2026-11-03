package com.tranquilonjoe.day07.orderprocessing;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class OrderProcessor {

    private final BlockingQueue<OrderTask> queue;

    private final CountDownLatch worker1Started;

    private final CountDownLatch allowWorker1ToProceed;

    private final Set<Long> submittedOrderIds =
            ConcurrentHashMap.newKeySet();

    public OrderProcessor(int capacity) {
        this(capacity, null, null);
    }

    public OrderProcessor(
            int capacity,
            CountDownLatch worker1Started,
            CountDownLatch allowWorker1ToProceed) {

        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Capacity must be a positive number");
        }

        this.queue = new ArrayBlockingQueue<>(capacity);
        this.worker1Started = worker1Started;
        this.allowWorker1ToProceed = allowWorker1ToProceed;
    }

    public void submitOrder(Order order) throws InterruptedException {
        Objects.requireNonNull(order, "Order cannot be null");

        if (!submittedOrderIds.add(order.getOrderId())) {
            throw new IllegalStateException(
                    "Order already submitted: " + order.getOrderId()
            );
        }

        try {
            queue.put(new OrderTask.Process(order));
        } catch (InterruptedException e) {
            submittedOrderIds.remove(order.getOrderId());
            throw e;
        }
    }

    public void submitShutdown() throws InterruptedException {
        queue.put(new OrderTask.Shutdown());
    }

    public void processOrders() {
        while (true) {
            try {
                OrderTask orderTask = queue.take();
                switch (orderTask) {
                    case OrderTask.Process process -> {

                        Order order = process.order();

                        try {
                            order.updateStatus(OrderStatus.PROCESSING);

                            if (worker1Started != null
                                    && allowWorker1ToProceed != null
                                    && Thread.currentThread().getName().equals("order-worker-1")) {

                                worker1Started.countDown();
                                allowWorker1ToProceed.await();
                            }

                            System.out.println(
                                    Thread.currentThread().getName()
                                            + " processing Order " + order.getOrderId()
                            );
                            if (order.getOrderId() == 105) {
                                throw new IllegalStateException(
                                        "Simulated payment processing failure");
                            }
                            Thread.sleep(1000);
                            order.updateStatus(OrderStatus.COMPLETED);
                            System.out.println(
                                    Thread.currentThread().getName()
                                            + " completed Order " + order.getOrderId()
                            );
                        } catch (RuntimeException e) {
                            if (order.getStatus() == OrderStatus.PROCESSING) {
                                order.updateStatus(OrderStatus.FAILED);
                            }

                            System.out.println(
                                    Thread.currentThread().getName()
                                            + " failed Order " + order.getOrderId()
                                            + ": " + e.getMessage()
                            );
                        } catch (InterruptedException e) {
                            if (order.getStatus() == OrderStatus.PROCESSING) {
                                order.updateStatus(OrderStatus.FAILED);
                            }

                            System.out.println(
                                    Thread.currentThread().getName()
                                            + " interrupted while processing Order "
                                            + order.getOrderId()
                            );

                            throw e;
                        }
                    }

                    case OrderTask.Shutdown shutdown -> {
                        System.out.println(
                                Thread.currentThread().getName()
                                        + " received shutdown signal"
                        );
                        return;
                    }
                }


            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(
                        Thread.currentThread().getName() + " interrupted. Stopping."
                );
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
