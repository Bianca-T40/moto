package com.corso.moto.services;

import com.corso.moto.entity.Bike;
import com.corso.moto.repository.BikeRepository;
import com.corso.moto.services.BikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Genera il costruttore per la Dependency Injection del Repository
public class BikeServiceImpl implements BikeService {

    private final BikeRepository bikeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Bike> findAll() {
        return bikeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Bike findById(Long id) {
        return bikeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Bike save(Bike moto) {
        return bikeRepository.save(moto);
    }

    @Override
    @Transactional
    public Bike update(Long id, Bike moto) {
        Optional<Bike> optionalBike = bikeRepository.findById(id);

        if (optionalBike.isPresent()) {
            Bike existingBike = optionalBike.get();

            // Aggiornamento dei campi
            existingBike.setBrand(moto.getBrand());
            existingBike.setModel(moto.getModel());
            existingBike.setEngineCc(moto.getEngineCc());
            existingBike.setType(moto.getType());
            existingBike.setYear(moto.getYear());
            existingBike.setPrice(moto.getPrice());

            return bikeRepository.save(existingBike);
        }

        return null; // O potresti lanciare una Custom Exception
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (bikeRepository.existsById(id)) {
            bikeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}