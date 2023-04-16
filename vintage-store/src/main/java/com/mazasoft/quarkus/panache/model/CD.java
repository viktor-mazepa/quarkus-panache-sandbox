package com.mazasoft.quarkus.panache.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class CD extends Item {

    @Column(name = "music_company")
    public String musicCompany;

    @Column(length = 100)
    public String genre;

    @OneToMany(mappedBy = "cd", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    public Collection<Track> tracks;

    public CD() {
        tracks = new ArrayList<>();
    }
}
