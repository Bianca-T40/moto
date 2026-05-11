package com.corso.moto.mapper;

import com.corso.moto.dto.BikeDTO;
import com.corso.moto.entity.Bike;

public class BikeMapper {

    public static BikeDTO toDTO(Bike bike) {
        if (bike == null) return null;

        return new BikeDTO(
                bike.getId(),
                bike.getBrand(),
                bike.getModel(),
                bike.getEngineCc(),
                bike.getType(),
                bike.getYear(),
                bike.getPrice()
        );
    }

    public static Bike toEntity(BikeDTO dto) {
        if (dto == null) return null;

        return new Bike(
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getEngineCc(),
                dto.getType(),
                dto.getYear(),
                dto.getPrice()
        );
    }
}
