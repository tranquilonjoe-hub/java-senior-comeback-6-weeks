package com.tranquilonjoe.day07;

import java.util.Comparator;
import java.util.PriorityQueue;

public class OrderPriorityQueueDemo {

    public static void main(String[] args) {

        Comparator<Order> orderComparator =
                Comparator.comparingInt(Order::priority)
                        .reversed()
                        .thenComparing(Order::arrivalSequence);

        PriorityQueue<Order> orderQueue = new PriorityQueue<>(orderComparator);

        orderQueue.offer(new Order(101, 2, 1));
        orderQueue.offer(new Order(102, 3, 2));
        orderQueue.offer(new Order(103, 3, 3));
        orderQueue.offer(new Order(104, 1, 4));
        orderQueue.offer(new Order(105, 2, 5));


        while (!orderQueue.isEmpty()) {
            Order order = orderQueue.poll();
            System.out.println("Processing Order: " + order);
        }
    }
}
