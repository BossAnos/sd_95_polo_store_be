package com.example.sd_95_polo_store_be.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Products")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Products extends BaseEntity<Products> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer status;

    private String description;


    private Integer categoryId;


    private Integer brandId;

    private Integer materialId;

    private Integer discountId;


    @Override
    protected Products self() {
        return null;
    }
}
