package com.example.sd_95_polo_store_be.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date create_date ;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date update_date ;
}
