package com.mazasoft.quarkus.panache.resources;

import com.mazasoft.quarkus.jdbc.model.Artist;
import com.mazasoft.quarkus.panache.model.Book;
import com.mazasoft.quarkus.panache.model.CD;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Collection;

@Path("/api/cds")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CdResource {
    @GET
    public Collection<CD> getAllCDs() {
        return CD.listAll();
    }

    @GET
    @Path("{id}")
    public CD findCdById(@PathParam("id") Long id) {
        return (CD) CD.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    @POST
    public Response persistCd(CD cd, @Context UriInfo uriInfo) {
        CD.persist(cd);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(cd.id));
        return Response.created(builder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void deleteCDById(@PathParam("id") Long id) {
        CD.deleteById(id);
    }
}
