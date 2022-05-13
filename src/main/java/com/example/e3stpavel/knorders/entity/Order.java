package com.example.e3stpavel.knorders.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Column(name = "order_line", nullable = false)
    private List<OrderLine> orderLine;

    @OneToOne
    @JoinColumn(name = "customer", nullable = false)
    private Customer customer;

    @Column(name = "submission_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date submissionDate;
}
