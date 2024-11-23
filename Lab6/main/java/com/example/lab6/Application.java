package com.example.lab6;

import com.example.lab6.ejb.OrderBean;
import com.example.lab6.ejb.InventoryBean;
import jakarta.ejb.EJB;

public class Application {

    @EJB
    private static InventoryBean inventoryBean;

    @EJB
    private static OrderBean orderBean;

    public static void main(String[] args) {
        // Check stock for a product
        int productId = 5;
        System.out.println("Stock for product " + productId + ": " + inventoryBean.getStockLevel(productId));

        // Create a new order
        orderBean.addItemToOrder(5, 10);
        boolean orderPlaced = orderBean.placeOrder(1);

        if (orderPlaced) {
            System.out.println("Order placed successfully.");
        } else {
            System.out.println("Order failed due to insufficient stock.");
        }
    }
}

