package com.mazasoft.quarkus.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.Duration;
import java.time.Instant;

@Entity
@Table(name = "t_tracks")
public class Track extends PanacheEntity {

    @Column(length = 50, nullable = false)
    public String title;

    @Column(nullable = false)
    public Duration duration;

    @Column(name = "created_date")
    public Instant createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonbTransient
    @JoinColumn(name = "cd_id", referencedColumnName = "id")
    public CD cd;

    public Track() {
        this.createdDate = Instant.now();
    }
}
