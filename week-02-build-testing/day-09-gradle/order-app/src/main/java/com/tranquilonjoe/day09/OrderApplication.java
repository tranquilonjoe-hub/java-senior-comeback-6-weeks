package com.tranquilonjoe.day09;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderApplication {

    public static void main(String[] args) throws JsonProcessingException {

        OrderService orderService = new OrderService();

        Order order = orderService.createOrder(
                1001L,
                "Alice",
                2499.50
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(order);

        System.out.println("Order Management Application - Incremental Compilation Test");
        System.out.println("Serialized Order: " + json);
    }
}