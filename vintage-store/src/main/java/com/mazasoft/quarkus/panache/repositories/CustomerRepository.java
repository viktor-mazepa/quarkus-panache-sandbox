package com.mazasoft.quarkus.panache.repositories;

import com.mazasoft.quarkus.jpa.model.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

    public Collection<Customer> listOfCustomersByName(String firstName) {
        return list("firstName = '" + firstName + "'", Sort.descending("lastName"));
    }

    public Optional<Customer> findByName(String name) {
        return find("name", name).firstResultOptional();
    }
}
