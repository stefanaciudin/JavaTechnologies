package com.example.lab55.repository;

import com.example.lab55.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class ProductRepository {

    @PersistenceContext
    public EntityManager entityManager;

    @Transactional
    public void create(Product product) {
        entityManager.persist(product);
    }

    public Product read(Integer productId) {
        return entityManager.find(Product.class, productId);
    }

    @Transactional
    public Product update(Product product) {
        return entityManager.merge(product);
    }

    @Transactional
    public void delete(Integer productId) {
        Product product = entityManager.find(Product.class, productId);
        if (product != null) {
            entityManager.remove(product);
        }
    }

    public List<Product> findByName(String name) {
        return entityManager.createNamedQuery("Product.findByName", Product.class)
                .setParameter("name", name)
                .getResultList();
    }
}