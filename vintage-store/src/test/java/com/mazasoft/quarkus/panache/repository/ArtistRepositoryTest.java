package com.mazasoft.quarkus.panache.repository;

import com.mazasoft.quarkus.jdbc.model.Artist;
import com.mazasoft.quarkus.panache.repositories.ArtistRepository;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ArtistRepositoryTest {

    private final ArtistRepository artistRepository;
    private final String artistTestName = "name";
    private final String artistTestBio = "bio";


    @Inject
    public ArtistRepositoryTest(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }


    @Test
    @TestTransaction
    public void shouldCreateAndFindAnArtist() {
        long count = artistRepository.count();

        Artist artist = new Artist(artistTestName, artistTestBio);
        artistRepository.persist(artist);
        assertNotNull(artist.getId());

        assertEquals(count + 1, artistRepository.count());

        Artist artistTest = artistRepository.findById(artist.getId());
        assertEquals(artistTestName, artistTest.getName());

        artistRepository.deleteById(artistTest.getId());

        assertEquals(count, artistRepository.count());

    }

}