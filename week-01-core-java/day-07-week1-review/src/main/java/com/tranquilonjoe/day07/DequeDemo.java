package com.tranquilonjoe.day07;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class DequeDemo {
    public static void main(String[] args) {

        Deque<Integer> queue = new ArrayDeque<>();

        System.out.println("Poll first on empty queue: " + queue.pollFirst());

        process(queue);

        while (!queue.isEmpty()) {
            System.out.println("Polling: " + queue.poll());
        }

        Deque<Integer> stack = new ArrayDeque<>();
        try {
            stack.pop();
        } catch (NoSuchElementException e) {
            System.out.println("Stack is empty");
        }

        stack.push(10);
        stack.push(20);
        stack.push(30);

        while (!stack.isEmpty()) {
            System.out.println("Popping: " + stack.pop());
        }

        Deque<Integer> linkedQueue = new LinkedList<>();

        process(linkedQueue);

        while (!linkedQueue.isEmpty()) {
            System.out.println("LinkedList polling: " + linkedQueue.poll());
        }
    }

    public static void process(Deque<Integer> deque) {
        deque.offerLast(10);
        deque.offerLast(20);
        deque.offerLast(30);
    }
}
