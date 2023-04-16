package com.mazasoft.quarkus.panache.page;

import com.mazasoft.quarkus.jdbc.model.Artist;
import com.mazasoft.quarkus.panache.repositories.ArtistRepository;
import io.quarkus.panache.common.Sort;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

@Path("/page/artists")
@Produces(MediaType.TEXT_HTML)
@ApplicationScoped
public class ArtistPage {

  @Inject
  ArtistRepository repository;

  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance artist(Artist artist);

    public static native TemplateInstance artists(Collection<Artist> artists);
  }

  @GET
  @Path("{id}")
  public TemplateInstance showArtistById(@PathParam("id") Long id) {
    return Templates.artist(repository.findById(id));
  }

  @GET
  public TemplateInstance showAllArtists() {
    return Templates.artists(repository.listAll());
  }
}
