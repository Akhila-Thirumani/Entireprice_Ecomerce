package com.ecommerce.service;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    public void addProduct(Product product) throws SQLException {

        validateProduct(product);

        productRepository.save(product);
    }

    public Product getProductById(int productId) throws SQLException {

        if (productId <= 0) {
            throw new IllegalArgumentException("Product ID must be greater than zero");
        }

        return productRepository.findById(productId);
    }

    public List<Product> getAllProducts() throws SQLException {

        return productRepository.findAll();
    }

    public void updateProduct(Product product) throws SQLException {

        validateProduct(product);

        if (product.getProductId() <= 0) {
            throw new IllegalArgumentException("Product ID must be greater than zero");
        }

        productRepository.update(product);
    }

    public void deleteProduct(int productId) throws SQLException {

        if (productId <= 0) {
            throw new IllegalArgumentException("Product ID must be greater than zero");
        }

        productRepository.delete(productId);
    }

    private void validateProduct(Product product) {

        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }

        if (product.getPrice() == null ||
                product.getPrice().signum() < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }

        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Product stock cannot be negative");
        }
    }
}