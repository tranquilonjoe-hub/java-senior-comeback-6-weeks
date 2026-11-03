package com.tranquilonjoe.day07.orderprocessing;

import java.util.*;
import java.util.stream.Collectors;

public class OrderService {

    private final Map<Long, Order> orders = new HashMap<>();

    public Order addOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException(
                    "Order must not be null");
        }
        Long orderId = order.getOrderId();
        if (orders.putIfAbsent(orderId, order) != null) {
            throw new IllegalArgumentException(
                    "Order already exists with id: " + orderId);
        }
        return order;
    }

    public Optional<Order> getOrderById(long orderId) {
        if (orderId <= 0) {
            throw new IllegalArgumentException(
                    "Order ID must be valid");
        }
        return Optional.ofNullable(orders.get(orderId));
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

}
