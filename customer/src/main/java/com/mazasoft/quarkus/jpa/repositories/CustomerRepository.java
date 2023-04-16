package com.mazasoft.quarkus.jpa.repositories;

import com.mazasoft.quarkus.jpa.model.Customer;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class CustomerRepository {

    private final EntityManager entityManager;

    @Inject
    public CustomerRepository(EntityManager entityManager, Logger logger) {
        this.entityManager = entityManager;
    }

    public void persist(Customer customer) {
        entityManager.persist(customer);
    }

    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }
}
