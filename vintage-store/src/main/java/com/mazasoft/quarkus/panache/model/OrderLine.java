package com.mazasoft.quarkus.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "t_purchase_order_lines")
public class OrderLine extends PanacheEntity {

    public Integer quantity;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id", referencedColumnName = "id")
    @JsonbTransient
    public PurchaseOrder purchaseOrder;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    public Item item;

    @Column(name = "created_date", nullable = false)
    public Instant createdDate;

    public OrderLine() {
        this.createdDate = Instant.now();
    }
}
