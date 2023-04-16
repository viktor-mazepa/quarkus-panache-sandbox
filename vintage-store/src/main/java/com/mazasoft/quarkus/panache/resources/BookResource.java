package com.mazasoft.quarkus.panache.resources;

import com.mazasoft.quarkus.jdbc.model.Artist;
import com.mazasoft.quarkus.panache.model.Book;
import com.mazasoft.quarkus.panache.model.CD;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Collection;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @GET
    public Collection<Book> getAllCDs() {
        return Book.listAll();
    }

    @GET
    @Path("{id}")
    public Book findBookById(@PathParam("id") Long id) {
        return (Book) Book.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    @POST
    public Response persistBook(Book book, @Context UriInfo uriInfo) {
        Book.persist(book);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(book.id));
        return Response.created(builder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void deleteBookById(@PathParam("id") Long id) {
        Book.deleteById(id);
    }
}
