package com.example.lab55.repository;

import com.example.lab55.entity.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

public class ClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Client create(Client client) {
        entityManager.persist(client);
        return client;
    }

    public Client read(Integer clientId) {
        return entityManager.find(Client.class, clientId);
    }

    @Transactional
    public Client update(Client client) {
        return entityManager.merge(client);
    }

    @Transactional
    public void delete(Integer clientId) {
        Client client = entityManager.find(Client.class, clientId);
        if (client != null) {
            entityManager.remove(client);
        }
    }

    public List<Client> findByName(String name) {
        return entityManager.createNamedQuery("Client.findByName", Client.class)
                .setParameter("name", name)
                .getResultList();
    }
}
