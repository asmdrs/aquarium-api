package com.asmdrs.aquariummonitor.controllers;

import com.asmdrs.aquariummonitor.dto.AquariumDTO;
import com.asmdrs.aquariummonitor.entities.Aquarium;
import com.asmdrs.aquariummonitor.services.AquariumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value ="/aquariums")
public class AquariumController {
    @Autowired
    private AquariumService service;
    private static final Logger LOGGER = Logger.getLogger(AquariumController.class.getName());

    @GetMapping(value = "/{id}")
    public AquariumDTO findById(@PathVariable Long id) {
        try {
            return service.findById(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Falha ao buscar Aquário por ID.", e);
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<List<AquariumDTO>> getAllAquariums() {
        try {
            List<AquariumDTO> aquariums = service.getAllAquariums();
            return new ResponseEntity<>(aquariums, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Ocorreu um erro ao recuperar todos os aquários.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<AquariumDTO> insertNewAquarium(@RequestBody AquariumDTO aquarium) {
        try {
            AquariumDTO aquariumDTO = service.insert(aquarium);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(aquariumDTO.getId()).toUri();
            return ResponseEntity.created(uri).body(aquariumDTO);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao criar novo aquário.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
