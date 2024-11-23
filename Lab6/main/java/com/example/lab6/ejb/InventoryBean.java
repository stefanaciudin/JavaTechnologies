package com.example.lab6.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.example.lab6.entity.Product;

@Stateless
public class InventoryBean {

    @PersistenceContext(unitName = "VRP_DB_Unit")
    private EntityManager em;

    public int getStockLevel(int productId) {
        Product product = em.find(Product.class, productId);
        if (product != null) {
            return product.getQuantity();
        }
        return 0;
    }
}
