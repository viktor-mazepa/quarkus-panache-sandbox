package com.mazasoft.quarkus.jdbc;

import com.mazasoft.quarkus.jdbc.model.Artist;
import com.mazasoft.quarkus.jdbc.repositories.ArtistRepository;
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
    public void shouldCreateAndFindAnArtist() {
        Artist artist = new Artist(artistTestName, artistTestBio);
        artistRepository.persist(artist);
        assertNotNull(artist.getId());

        Artist artistTest = artistRepository.findById(artist.getId());
        assertEquals(artistTestName, artistTest.getName());
    }

}