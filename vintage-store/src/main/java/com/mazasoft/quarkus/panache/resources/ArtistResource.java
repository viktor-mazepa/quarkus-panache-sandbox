package com.mazasoft.quarkus.panache.resources;

import com.mazasoft.quarkus.jdbc.model.Artist;
import com.mazasoft.quarkus.panache.repositories.ArtistRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Collection;

@Path("/api/artists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArtistResource {

    private final ArtistRepository artistRepository;

    @Inject
    public ArtistResource(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }


    @GET
    public Collection<Artist> getAllArtists() {
        return artistRepository.listOfArtistSorted();
    }

    @GET
    @Path("{id}")
    public Artist findArtistById(@PathParam("id") Long id) {
        return artistRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    @POST
    public Response persistArtist(Artist artist, @Context UriInfo uriInfo) {
        artistRepository.persist(artist);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(artist.getId()));
        return Response.created(builder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void deleteArtistById(@PathParam("id") Long id) {
        artistRepository.deleteById(id);
    }


}
