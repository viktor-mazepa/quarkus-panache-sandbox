package com.mazasoft.quarkus.panache;

import com.mazasoft.quarkus.panache.model.Publisher;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PublisherTest {

    private final String testPublisherName = "TestPublisher";

    @Test
    @TestTransaction
    public void shouldCreateAndFindAnArtist() {
        long count = Publisher.count();
        Publisher publisher = new Publisher(testPublisherName);
        Publisher.persist(publisher);
        assertNotNull(publisher.id);

        assertEquals(count + 1, Publisher.count());

        Publisher testPublisher = Publisher.findById(publisher.id);
        assertEquals(testPublisherName, testPublisher.name);

        Publisher.deleteById(testPublisher.id);
        assertEquals(count, Publisher.count());

    }


}