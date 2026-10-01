package com.ecommerce.repository;

import com.ecommerce.config.DatabaseConnectionPool;
import com.ecommerce.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private final DatabaseConnectionPool connectionPool =
            DatabaseConnectionPool.getInstance();

    public void save(Order order) throws SQLException {

        String sql = """
                INSERT INTO orders (user_id, total_amount, status)
                VALUES (?, ?, ?)
                """;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

        	statement.setInt(1, order.getUserId());
            statement.setBigDecimal(2, order.getTotalAmount());
            statement.setString(3, order.getStatus());

            statement.executeUpdate();
        }
    }

    public Order findById(int orderId) throws SQLException {

        String sql = """
                SELECT order_id, user_id, total_amount, status, created_at
                FROM orders
                WHERE order_id = ?
                """;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, orderId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return mapOrder(resultSet);
                }
            }
        }

        return null;
    }

    public List<Order> findAll() throws SQLException {

        String sql = """
                SELECT order_id, user_id, total_amount, status, created_at
                FROM orders
                ORDER BY order_id
                """;

        List<Order> orders = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                orders.add(mapOrder(resultSet));
            }
        }

        return orders;
    }

    public void updateStatus(int orderId, String status) throws SQLException {

        String sql = """
                UPDATE orders
                SET status = ?
                WHERE order_id = ?
                """;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setInt(2, orderId);

            statement.executeUpdate();
        }
    }

    private Order mapOrder(ResultSet resultSet) throws SQLException {

        Order order = new Order();

        order.setOrderId(resultSet.getInt("order_id"));
        order.setUserId(resultSet.getInt("user_id"));
        order.setTotalAmount(resultSet.getBigDecimal("total_amount"));
        order.setStatus(resultSet.getString("status"));

        return order;
    }
}