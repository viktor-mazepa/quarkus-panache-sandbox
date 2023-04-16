package com.mazasoft.quarkus.panache.repository;

import com.mazasoft.quarkus.jdbc.model.Artist;
import com.mazasoft.quarkus.jpa.model.Customer;
import com.mazasoft.quarkus.jpa.repositories.CustomerRepository;
import com.mazasoft.quarkus.panache.model.Book;
import com.mazasoft.quarkus.panache.model.OrderLine;
import com.mazasoft.quarkus.panache.model.Publisher;
import com.mazasoft.quarkus.panache.model.PurchaseOrder;
import com.mazasoft.quarkus.panache.model.enumeration.Language;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@QuarkusTest
public class PurchaseOrderRepositoryTest {

    private final String artistTestName = "name";
    private final String artistTestBio = "bio";
    private final String testFirstName = "Tester";
    private final String testLastName = "Testerovich";
    private final String testEmail = "tester@gmail.com";
    private final String testPublisherName = "TestPublisher";

    private final String testBookTitle = "Test Book Title";
    private final String testIsbn = "isbn";

    private final CustomerRepository customerRepository;

    @Inject
    public PurchaseOrderRepositoryTest(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Test
    @TestTransaction
    public void shouldCreateAndFindAnArtist() {
        Artist artist = new Artist(artistTestName,artistTestBio);

        Publisher publisher = new Publisher(testPublisherName);

        Book book = new Book();
        book.title = testBookTitle;
        book.numberOfPages=50;
        book.language= Language.ENGLISH;
        book.publisher = publisher;
        book.artist = artist;
        book.price = BigDecimal.valueOf(100L);
        book.isbn = testIsbn;
        book.publicationDate = LocalDate.now();
        Book.persist(book);

        Customer customer = new Customer(testFirstName, testLastName, testEmail);
        customerRepository.persist(customer);

        OrderLine orderLine = new OrderLine();
        orderLine.item = book;
        orderLine.quantity = 2;

        OrderLine.persist(orderLine);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.customer = customer;
        purchaseOrder.addOrderLine(orderLine);

        PurchaseOrder.persist(purchaseOrder);
    }

}
