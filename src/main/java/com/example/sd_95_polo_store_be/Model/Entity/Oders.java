package com.example.sd_95_polo_store_be.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
public class Oders extends BaseEntity<Oders> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String phone;
    private String username;
    private String address;
    private String shopping;
    private Float totalPrice;
    private Float shipCost;
    private String note;
    private Integer status;
    OffsetDateTime confirmDate;
    OffsetDateTime successDate;
    OffsetDateTime shipDate;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customers customers;

    @ManyToOne
    @JoinColumn(name = "transactionId")
    private Transactions transactions;

    @Override
    protected Oders self() {
        return this;
    }
}
