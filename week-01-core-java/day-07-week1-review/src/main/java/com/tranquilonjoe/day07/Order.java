package com.tranquilonjoe.day07;

public record Order(
        int orderId,
        int priority,
        long arrivalSequence
) {
}
