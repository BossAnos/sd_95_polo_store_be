package com.example.sd_95_polo_store_be.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

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


    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Categories categories;


    @ManyToOne
    @JoinColumn(name = "brandId")
    private Brands brands;

    @ManyToOne
    @JoinColumn(name = "materialId")
    private Materials materials;


    @Override
    protected Products self() {
        return null;
    }
}
    