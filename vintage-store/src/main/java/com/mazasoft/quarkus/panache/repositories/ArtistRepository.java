package com.mazasoft.quarkus.panache.repositories;

import com.mazasoft.quarkus.jdbc.model.Artist;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;

@ApplicationScoped
public class ArtistRepository implements PanacheRepository<Artist> {

    public Collection<Artist> listOfArtistSorted (){
        return listAll(Sort.descending("name"));
    }


}
