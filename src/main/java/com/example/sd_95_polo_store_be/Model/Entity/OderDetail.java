package com.example.sd_95_polo_store_be.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.repository.cdi.Eager;

@Data
@Entity
public class OderDetail extends BaseEntity<OderDetail> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;

    private Float price;

    private Integer status;

    @ManyToOne
    @JoinColumn(name = "oderId")
    private Oders oders;

    @ManyToOne
    @JoinColumn(name = "productDetailId ")
    private ProductDetail productDetail;


    @Override
    protected OderDetail self() {
        return this;
    }
}
