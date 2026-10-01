package com.ecommerce.observer;

import com.ecommerce.model.Order;

public interface OrderObserver {

    void update(Order order);
}