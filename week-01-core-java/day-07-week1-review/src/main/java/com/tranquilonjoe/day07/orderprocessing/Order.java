package com.tranquilonjoe.day07.orderprocessing;

import java.math.BigDecimal;

public class Order {

    private final long orderId;
    private final String customerName;
    private final BigDecimal amount;
    private OrderStatus status = OrderStatus.NEW;

    public Order(long orderId, String customerName, BigDecimal amount) {

        if (orderId <= 0) {
            throw new IllegalArgumentException("Order Id must be greater than zero");
        }

        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer Name must not be null or blank");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must not be null and must be greater than zero");
        }

        this.orderId = orderId;
        this.customerName = customerName.strip();
        this.amount = amount;
    }

    public long getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void updateStatus(OrderStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status must not be null");
        }
        switch (this.status) {
            case NEW -> {
                if (status != OrderStatus.PROCESSING) {
                    throw new IllegalStateException(
                            "Cannot change order status from "
                                    + this.status + " to " + status
                    );
                }
            }
            case PROCESSING -> {
                if (status != OrderStatus.COMPLETED && status != OrderStatus.FAILED) {
                    throw new IllegalStateException(
                            "Cannot change order status from "
                                    + this.status + " to " + status
                    );
                }
            }
            case COMPLETED, FAILED -> throw new IllegalStateException(
                    "Cannot change order status from "
                            + this.status + " to " + status
            );
        }
        this.status = status;

    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}
