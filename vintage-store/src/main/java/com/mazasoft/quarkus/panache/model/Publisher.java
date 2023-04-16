package com.mazasoft.quarkus.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "t_publishers")
public class Publisher extends PanacheEntity {

    @Column(name = "name", length = 50, nullable = false)
    public String name;

    @Column(name = "created_date", nullable = false)
    public Instant createdDate;

    public Publisher(String name) {
        this.name = name;
        this.createdDate = Instant.now();
    }

    public Publisher() {
        this.createdDate = Instant.now();
    }
}
