package com.mazasoft.quarkus.panache.page;

import com.mazasoft.quarkus.panache.model.Publisher;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/page/publishers")
@Produces(MediaType.TEXT_HTML)
@ApplicationScoped
public class PublisherPage {

  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance publisher(Publisher publisher);

    public static native TemplateInstance publishers(Collection<Publisher> publishers);
  }

  @GET
  @Path("{id}")
  public TemplateInstance showPublisherById(@PathParam("id") Long id) {
    return Templates.publisher(Publisher.findById(id));
  }

  @GET
  public TemplateInstance showAllPublishers() {
    return Templates.publishers(Publisher.listAll());
  }
}
