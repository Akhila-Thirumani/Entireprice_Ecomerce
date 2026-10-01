package com.ecommerce.observer;

import com.ecommerce.model.Order;

public class OrderNotificationService implements OrderObserver {

    @Override
    public void update(Order order) {

        System.out.println(
                "Order notification: Order #" +
                order.getOrderId() +
                " status changed to " +
                order.getStatus()
        );
    }
}