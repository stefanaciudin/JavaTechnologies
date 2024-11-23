package com.example.lab6.ejb;

import com.example.lab6.entity.Product;
import com.example.lab6.entity.Client;
import com.example.lab6.entity.Order;
import com.example.lab6.entity.OrderItem;

import jakarta.ejb.Stateful;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.Map;

@Stateful
public class OrderBean {

    @PersistenceContext(unitName = "VRP_DB_Unit")
    private EntityManager em;

    @Inject
    private InventoryBean inventoryBean;

    private Map<Integer, Integer> orderItems = new HashMap<>();

    public void addItemToOrder(int productId, int quantity) {
        orderItems.put(productId, quantity);
    }

    @Transactional
    public boolean placeOrder(int clientId) {
        // Fetch the client from the database
        Client client = em.find(Client.class, clientId);
        if (client == null) {
            throw new IllegalArgumentException("Client not found with ID: " + clientId);
        }

        // Verify if there's sufficient stock for each requested product
        for (Map.Entry<Integer, Integer> entry : orderItems.entrySet()) {
            int productId = entry.getKey();
            int requestedQuantity = entry.getValue();

            int currentStock = inventoryBean.getStockLevel(productId);
            if (currentStock < requestedQuantity) {
                return false; // Rollback transaction if any item is out of stock
            }
        }

        // If all items are in stock, proceed to create the order
        Order order = new Order();
        order.setClient(client);  // Use setClient, passing the Client entity
        em.persist(order);

        // Process each product in the order, updating stock and adding OrderItems
        for (Map.Entry<Integer, Integer> entry : orderItems.entrySet()) {
            int productId = entry.getKey();
            int requestedQuantity = entry.getValue();

            Product product = em.find(Product.class, productId);
            product.setQuantity(product.getQuantity() - requestedQuantity); // Update stock

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(requestedQuantity);
            em.persist(orderItem);
        }

        // Clear the order items map after successful order placement
        orderItems.clear();

        return true;
    }
}
