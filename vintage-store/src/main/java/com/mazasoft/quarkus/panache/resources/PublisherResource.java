package com.mazasoft.quarkus.panache.resources;
import com.mazasoft.quarkus.jdbc.model.Artist;
import com.mazasoft.quarkus.panache.model.Publisher;
import io.quarkus.panache.common.Sort;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Collection;

@Path("/api/publishers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PublisherResource {

    @GET
    public Collection<Publisher> getAllPublisher() {
        return Publisher.listAll(Sort.ascending("name"));
    }

    @GET
    @Path("{id}")
    public Publisher findArtistById(@PathParam("id") Long id) {
        return (Publisher) Publisher.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    @POST
    public Response persistPublisher(Publisher publisher, @Context UriInfo uriInfo) {
        Publisher.persist(publisher);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(publisher.id));
        return Response.created(builder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void deletePublisherById(@PathParam("id") Long id) {
        Publisher.deleteById(id);
    }
}
