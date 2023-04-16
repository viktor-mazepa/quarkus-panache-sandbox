package com.mazasoft.quarkus.panache.model;

import com.mazasoft.quarkus.panache.model.enumeration.Language;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Book extends Item {

    @Column(length = 50)
    public String isbn;

    @Column(name = "nb_of_pages")
    public Integer numberOfPages;

    @Column(name = "publication_date")
    public LocalDate publicationDate;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    public Language language;

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    public Publisher publisher;
}
