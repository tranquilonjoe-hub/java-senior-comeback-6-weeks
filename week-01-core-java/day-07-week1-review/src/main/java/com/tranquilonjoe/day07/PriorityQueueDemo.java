package com.tranquilonjoe.day07;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueDemo {

    public static void main(String[] args) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.offer(30);
        pq.offer(10);
        pq.offer(20);
        pq.offer(5);
        pq.offer(15);

        System.out.println("Queue: " + pq);
        System.out.println("Peek: " + pq.peek());

        while (!pq.isEmpty()) {
            System.out.println("Processing: " + pq.poll());
        }

        System.out.println();

        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Comparator.reverseOrder());

        maxHeap.offer(30);
        maxHeap.offer(10);
        maxHeap.offer(20);
        maxHeap.offer(5);
        maxHeap.offer(15);

        maxHeap.remove(10);

        while (!maxHeap.isEmpty()) {
            System.out.println("Processing: " + maxHeap.poll());
        }
    }
}
