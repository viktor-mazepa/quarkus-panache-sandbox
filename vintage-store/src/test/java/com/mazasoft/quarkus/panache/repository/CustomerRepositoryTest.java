package com.mazasoft.quarkus.panache.repository;

import com.mazasoft.quarkus.jpa.model.Customer;;
import com.mazasoft.quarkus.panache.repositories.CustomerRepository;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CustomerRepositoryTest {

    private final CustomerRepository customerRepository;
    private final String testFirstName = "Tester";
    private final String testLastName = "Testerovich";
    private final String testEmail = "tester@gmail.com";


    public CustomerRepositoryTest(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Test
    @TestTransaction
    public void shouldCreateAndFindAnArtist() {
        Customer customer = new Customer(testFirstName, testLastName, testEmail);
        customerRepository.persist(customer);
        assertNotNull(customer.getId());

        Customer testCustomer = customerRepository.findById(customer.getId());
        assertEquals(testFirstName, testCustomer.getFirstName());
        assertEquals(testLastName, testCustomer.getLastName());
        assertEquals(testEmail, testCustomer.getEmail());
        assertNotNull(testCustomer.getCreatedDate());
    }
}