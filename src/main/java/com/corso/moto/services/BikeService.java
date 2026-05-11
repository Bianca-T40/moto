package com.corso.moto.services;

import com.corso.moto.entity.Bike;
import java.util.List;

public interface BikeService {
    List<Bike> findAll();

    Bike findById(Long id);

    Bike save(Bike moto);

    Bike update(Long id, Bike moto);

    boolean delete(Long id);
}