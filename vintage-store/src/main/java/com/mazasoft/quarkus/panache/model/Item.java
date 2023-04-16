package com.mazasoft.quarkus.panache.model;

import com.mazasoft.quarkus.jdbc.model.Artist;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "t_items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item extends PanacheEntity {

    @Column(length = 100, nullable = false)
    public String title;

    @Column(length = 3000)
    public String description;

    @Column(nullable = false)
    public BigDecimal price;

    @Column(name = "created_date", nullable = false)
    public Instant creationDate;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    public Artist artist;


/*    @OneToMany(mappedBy = "item")
    public Collection<OrderLine> orderLines;*/

    public Item() {
        this.creationDate = Instant.now();
    }
}
