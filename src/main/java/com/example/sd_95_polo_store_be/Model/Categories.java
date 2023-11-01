package com.example.sd_95_polo_store_be.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "Categories")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    private Integer status;


    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date create_date ;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date update_date ;
}
