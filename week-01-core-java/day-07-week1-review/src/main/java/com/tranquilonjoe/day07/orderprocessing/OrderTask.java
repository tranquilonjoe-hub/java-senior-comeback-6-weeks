package com.tranquilonjoe.day07.orderprocessing;

public sealed interface OrderTask permits OrderTask.Process, OrderTask.Shutdown {

    record Process(Order order) implements OrderTask {
    }

    record Shutdown() implements OrderTask {
    }
}
