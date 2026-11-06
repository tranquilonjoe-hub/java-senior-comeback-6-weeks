package com.tranquilonjoe.day09;

public record Order(
        long id,
        String customerName,
        double amount,
        String status
) {
}
