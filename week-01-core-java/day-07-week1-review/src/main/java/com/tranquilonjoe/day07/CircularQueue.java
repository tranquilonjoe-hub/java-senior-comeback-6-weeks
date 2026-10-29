
package com.tranquilonjoe.day07;

import java.util.Arrays;

public class CircularQueue {

    private final int[] elements;
    private int head;
    private int tail;
    private int size;

    public CircularQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }

        elements = new int[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == elements.length;
    }

    public boolean offer(int value) {
        if (isFull()) return false;

        elements[tail] = value;
        tail = (tail + 1) % elements.length;
        ++size;
        return true;
    }

    public Integer poll() {
        if (isEmpty()) return null;
        int currentHead = elements[head];
        head = (head + 1) % elements.length;
        size--;
        return currentHead;
    }

    public Integer peek() {
        if (isEmpty()) return null;
        return elements[head];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(elements[(head + i) % elements.length]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }
}
