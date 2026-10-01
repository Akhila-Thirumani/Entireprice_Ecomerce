package com.ecommerce.service;

import com.ecommerce.model.Order;
import com.ecommerce.repository.OrderRepository;

import java.sql.SQLException;
import java.util.List;

public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService() {
        this.orderRepository = new OrderRepository();
    }

    public void createOrder(Order order) throws SQLException {

        validateOrder(order);

        orderRepository.save(order);
    }

    public Order getOrderById(int orderId) throws SQLException {

        if (orderId <= 0) {
            throw new IllegalArgumentException("Order ID must be greater than zero");
        }

        return orderRepository.findById(orderId);
    }

    public List<Order> getAllOrders() throws SQLException {

        return orderRepository.findAll();
    }

    public void updateOrderStatus(int orderId, String status)
            throws SQLException {

        if (orderId <= 0) {
            throw new IllegalArgumentException(
                    "Order ID must be greater than zero");
        }

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException(
                    "Order status is required");
        }

        orderRepository.updateStatus(orderId, status);
    }

    private void validateOrder(Order order) {

        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        if (order.getUserId() <= 0) {
            throw new IllegalArgumentException(
                    "User ID must be greater than zero");
        }

        if (order.getTotalAmount() == null ||
                order.getTotalAmount().signum() < 0) {
            throw new IllegalArgumentException(
                    "Order total amount cannot be negative");
        }

        if (order.getStatus() == null ||
                order.getStatus().isBlank()) {
            throw new IllegalArgumentException(
                    "Order status is required");
        }
    }
}