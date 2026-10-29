package com.tranquilonjoe.day07;

public class CircularQueueDemo {

    public static void main(String[] args) {

        CircularQueue queue = new CircularQueue(3);

        System.out.println("Initially empty: " + queue.isEmpty());

        System.out.println("Offer 10: " + queue.offer(10));
        System.out.println("Offer 20: " + queue.offer(20));
        System.out.println("Offer 30: " + queue.offer(30));

        System.out.println("Queue full: " + queue.isFull());
        System.out.println("Offer 40: " + queue.offer(40));

        System.out.println("Peek: " + queue.peek());
        System.out.println("Poll: " + queue.poll());

        System.out.println("Offer 40 again: " + queue.offer(40));

        System.out.println("Size: " + queue.size());


        CircularQueue testQueue = new CircularQueue(2);

        System.out.println("Poll empty: " + testQueue.poll());
        System.out.println("Peek empty: " + testQueue.peek());

        testQueue.offer(0);
        testQueue.offer(-10);

        System.out.println("Poll zero: " + testQueue.poll());
        System.out.println("Poll negative: " + testQueue.poll());
        System.out.println("Poll again: " + testQueue.poll());

        System.out.println("Empty again: " + testQueue.isEmpty());
        System.out.println("Size: " + testQueue.size());

        try {
            new CircularQueue(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Expected exception: " + e.getMessage());
        }

        CircularQueue wrapQueue = new CircularQueue(3);

        wrapQueue.offer(10);
        wrapQueue.offer(20);
        wrapQueue.offer(30);

        System.out.println(wrapQueue);

        wrapQueue.poll();
        wrapQueue.poll();

        wrapQueue.offer(40);
        wrapQueue.offer(50);

        System.out.println(wrapQueue);

        wrapQueue.poll();
        wrapQueue.offer(60);

        System.out.println(wrapQueue);

        System.out.println("Final size: " + wrapQueue.size());
        System.out.println("Final peek: " + wrapQueue.peek());
    }
}
