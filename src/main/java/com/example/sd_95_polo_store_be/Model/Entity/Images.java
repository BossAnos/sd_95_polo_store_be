package com.example.sd_95_polo_store_be.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.repository.cdi.Eager;

@Data
@Eager
public class Images extends BaseEntity<Images> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String url_image;

    private Integer status;

    @ManyToOne
    @JoinColumn(name = "productId ")
    private Products products;

    @Override
    protected Images self() {
        return this;
    }
}
