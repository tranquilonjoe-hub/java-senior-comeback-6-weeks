package com.tranquilonjoe.shop.orders.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class PaymentReflectionDemo {

    public static void main(String[] args) throws Exception {

        Class<?> clazz = Class.forName(
                "com.tranquilonjoe.shop.payments.config.PaymentConfig"
        );

        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);

        Object config = constructor.newInstance();

        Field field = clazz.getDeclaredField("apiKey");
        field.setAccessible(true);

        String apiKey = (String) field.get(config);

        System.out.println("API Key: " + apiKey);
    }
}