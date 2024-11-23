package com.example.lab6.ejb;

import com.example.lab6.entity.Order;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Map;
import java.util.HashMap;

@Singleton
@Startup
public class OrderCacheBean {

    @PersistenceContext(unitName = "VRP_DB_Unit")
    private EntityManager em;

    private Map<Integer, Order> orderMap = new HashMap<>();

    public void addOrder(Order order) {
        orderMap.put(order.getOrderId(), order);
    }

    public Map<Integer, Order> getOrderMap() {
        return orderMap;
    }
}

