package com.mazasoft.quarkus.panache.page;

import com.mazasoft.quarkus.panache.model.Book;
import com.mazasoft.quarkus.panache.model.CD;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/page/items")
@Produces(MediaType.TEXT_HTML)
public class ItemPage {


    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance book(Book book);
        public static native TemplateInstance books(Collection<Book> books);
        public static native TemplateInstance cd(CD cd);
        public static native TemplateInstance cds(Collection<CD> cds);
    }

    @GET
    @Path("/books")
    public TemplateInstance showBooks() {
        return Templates.books(Book.listAll());
    }

    @GET
    @Path("/books/{id}")
    public TemplateInstance showBookById(@PathParam("id") Long id) {
        return Templates.book(Book.findById(id));
    }

    @GET
    @Path("/cds")
    public TemplateInstance showCDs() {
        return Templates.cds(CD.listAll());
    }

    @GET
    @Path("/cds/{id}")
    public TemplateInstance showCdById(@PathParam("id") Long id) {
        return Templates.cd(CD.findById(id));
    }
}
