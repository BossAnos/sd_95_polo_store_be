package com.example.sd_95_polo_store_be.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Table(name = "ProductDetail")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class ProductDetail extends BaseEntity<ProductDetail>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Double cost;

    private Double price;

    private Integer status;

    private String description;

    @Override
    protected ProductDetail self() {
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "productId")
    private Products products;

    @ManyToOne
    @JoinColumn(name = "sizeId")
    private Sizes sizes;

    @ManyToOne
    @JoinColumn(name = "colorId")
    private Colors colors;
}
