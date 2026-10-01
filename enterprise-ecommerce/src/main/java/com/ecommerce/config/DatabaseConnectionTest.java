
package com.ecommerce.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionTest {

    public static void main(String[] args) {

        DatabaseConnectionPool connectionPool =
                DatabaseConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection()) {

            System.out.println("Database connection established successfully.");
            System.out.println("Connection valid: " + connection.isValid(5));

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT DATABASE()")) {

                if (resultSet.next()) {
                    System.out.println("Connected database: "
                            + resultSet.getString(1));
                }
            }

        } catch (SQLException exception) {

            System.err.println("Database connection failed.");
            System.err.println("Error: " + exception.getMessage());

        } finally {

            connectionPool.shutdown();
        }
    }
}

