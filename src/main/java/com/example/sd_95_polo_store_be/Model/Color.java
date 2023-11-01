package com.example.sd_95_polo_store_be.Model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Colors")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    private Integer status;


    private String description;
}
