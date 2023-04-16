package com.mazasoft.quarkus.panache.model;

import com.mazasoft.quarkus.jpa.model.Customer;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "t_purchase_orders")
public class PurchaseOrder extends PanacheEntity {

    @Column(name = "purchase_order_date", nullable = false)
    public LocalDate date;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    public Customer customer;

    @OneToMany(mappedBy = "purchaseOrder",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    public Collection<OrderLine> orderLines;

    @Column(name = "created_date", nullable = false)
    public Instant createdDate;

    public PurchaseOrder() {
        this.createdDate = Instant.now();
        this.orderLines = new ArrayList<>();
        this.date = LocalDate.now();
    }

    public void addOrderLine(OrderLine orderLine){
        this.orderLines.add(orderLine);
        orderLine.purchaseOrder = this;
    }
}
