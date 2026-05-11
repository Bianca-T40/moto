package com.corso.moto.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor



public class BikeDTO {

   
    private Long id;

     
    private String brand;

     
    private String model;


    private Integer engineCc;

    private String type;
    private Integer year;
    private Double price;
}