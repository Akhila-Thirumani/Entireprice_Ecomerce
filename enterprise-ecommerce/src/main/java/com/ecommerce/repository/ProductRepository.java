package com.ecommerce.repository;

import com.ecommerce.config.DatabaseConnectionPool;
import com.ecommerce.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private final DatabaseConnectionPool connectionPool =
            DatabaseConnectionPool.getInstance();

    public void save(Product product) throws SQLException {

        String sql = """
                INSERT INTO products (name, category, price, stock)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getName());
            statement.setString(2, product.getCategory());
            statement.setBigDecimal(3, product.getPrice());
            statement.setInt(4, product.getStock());

            statement.executeUpdate();
        }
    }

    public Product findById(int productId) throws SQLException {

        String sql = """
                SELECT product_id, name, category, price, stock
                FROM products
                WHERE product_id = ?
                """;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, productId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return mapProduct(resultSet);
                }
            }
        }

        return null;
    }

    public List<Product> findAll() throws SQLException {

        String sql = """
                SELECT product_id, name, category, price, stock
                FROM products
                ORDER BY product_id
                """;

        List<Product> products = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                products.add(mapProduct(resultSet));
            }
        }

        return products;
    }

    public void update(Product product) throws SQLException {

        String sql = """
                UPDATE products
                SET name = ?, category = ?, price = ?, stock = ?
                WHERE product_id = ?
                """;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getName());
            statement.setString(2, product.getCategory());
            statement.setBigDecimal(3, product.getPrice());
            statement.setInt(4, product.getStock());
            statement.setInt(5, product.getProductId());

            statement.executeUpdate();
        }
    }

    public void delete(int productId) throws SQLException {

        String sql = "DELETE FROM products WHERE product_id = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, productId);

            statement.executeUpdate();
        }
    }

    private Product mapProduct(ResultSet resultSet) throws SQLException {

        Product product = new Product();

        product.setProductId(resultSet.getInt("product_id"));
        product.setName(resultSet.getString("name"));
        product.setCategory(resultSet.getString("category"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setStock(resultSet.getInt("stock"));

        return product;
    }
}