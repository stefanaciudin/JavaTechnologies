package com.example.lab3.compulsory;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "productBean")
@SessionScoped
public class ProductBean implements Serializable {
    private List<Product> products;

    public List<Product> getProducts() {

        if (products == null) {
            loadProducts();
        }
        return products;
    }

    private void loadProducts() {
        products = new ArrayList<>();
        System.out.println("Loading products");
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres", "postgres", "meow1234");

             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM products")) {
            System.out.println("Products loaded");

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setDescription(resultSet.getString("description"));
                products.add(product);
            }
            System.out.println("Products loaded successfully: " + products.size()); // Debug statement
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading products from the database."); // Debug statement
        }
    }
}