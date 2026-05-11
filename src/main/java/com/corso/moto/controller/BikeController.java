package com.corso.moto.controller;

import com.corso.moto.dto.BikeDTO;
import com.corso.moto.entity.Bike;
import com.corso.moto.mapper.BikeMapper;
import com.corso.moto.services.BikeService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/moto")
public class BikeController {

    private final BikeService service;

    public BikeController(BikeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BikeDTO>> getAll() {
        List<BikeDTO> list = service.findAll()
                .stream()
                .map(BikeMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BikeDTO> getById(@PathVariable Long id) {
        Bike moto = service.findById(id);
        return (moto != null)
                ? ResponseEntity.ok(BikeMapper.toDTO(moto))
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BikeDTO> create(@Valid @RequestBody BikeDTO dto) {
        Bike saved = service.save(BikeMapper.toEntity(dto));
        return ResponseEntity
                .created(URI.create("/api/moto/" + saved.getId()))
                .body(BikeMapper.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BikeDTO> update(@PathVariable Long id, @Valid @RequestBody BikeDTO dto) {
        Bike updated = service.update(id, BikeMapper.toEntity(dto));
        return (updated != null)
                ? ResponseEntity.ok(BikeMapper.toDTO(updated))
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}