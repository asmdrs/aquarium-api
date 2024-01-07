package com.asmdrs.aquariummonitor.controllers;

import com.asmdrs.aquariummonitor.dto.PlantDTO;
import com.asmdrs.aquariummonitor.services.PlantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value ="/plants")
public class PlantController {
    @Autowired
    private PlantsService service;
    private static final Logger LOGGER = Logger.getLogger(PlantController.class.getName());

    @GetMapping(value = "/{id}")
    public ResponseEntity<PlantDTO> findById(@PathVariable Long id) {
        try {
            PlantDTO plantDTO = service.findById(id);
            return ResponseEntity.ok(plantDTO);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Falha ao buscar planta por ID.", e);
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<Page<PlantDTO>> findAll(Pageable pageable) {
        try {
            Page<PlantDTO> plantPage = service.findAll(pageable);
            return ResponseEntity.ok(plantPage);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Falha ao buscar todas plantas.", e);
            throw e;
        }

    }

    @PostMapping
    public ResponseEntity<PlantDTO> insertNewPlant(@RequestBody PlantDTO plant) {
        try {
            PlantDTO plantDTO = service.insert(plant);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(plantDTO.getId()).toUri();
            return ResponseEntity.created(uri).body(plantDTO);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Falha ao inserir nova planta.", e);
            throw e;
        }

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PlantDTO> change(@PathVariable Long id, @RequestBody PlantDTO plantDTO) {
        try {
            plantDTO = service.update(id, plantDTO);
            return ResponseEntity.ok(plantDTO);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Falha ao atualizar planta selecionada.", e);
            throw e;
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePlantById(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Falha ao deletar planta selecionada.", e);
            throw e;
        }
    }
}
