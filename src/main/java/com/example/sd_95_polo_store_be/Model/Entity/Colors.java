package com.example.sd_95_polo_store_be.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "Colors")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Colors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    private Integer status;


    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createdate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date updatedate;
}
