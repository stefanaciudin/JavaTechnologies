package com.example.lab55.repository;

import com.example.lab55.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public class OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Order create(Order order) {
        entityManager.persist(order);
        return order;
    }

    public Order read(Integer orderId) {
        return entityManager.find(Order.class, orderId);
    }

    @Transactional
    public Order update(Order order) {
        return entityManager.merge(order);
    }

    @Transactional
    public void delete(Integer orderId) {
        Order order = entityManager.find(Order.class, orderId);
        if (order != null) {
            entityManager.remove(order);
        }
    }

    public List<Order> findByDeliveryDate(LocalDate deliveryDate) {
        return entityManager.createNamedQuery("Order.findByDeliveryDate", Order.class)
                .setParameter("deliveryDate", deliveryDate)
                .getResultList();
    }
}
