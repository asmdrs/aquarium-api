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

@RestController
@RequestMapping(value ="/plants")
public class PlantController {
    @Autowired
    private PlantsService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PlantDTO> findById(@PathVariable Long id){
        PlantDTO plantDTO = service.findById(id);
        return ResponseEntity.ok(plantDTO);
    }

    @GetMapping
    public ResponseEntity<Page<PlantDTO>> findAll(Pageable pageable){
        Page<PlantDTO> plantPage = service.findAll(pageable);
        return ResponseEntity.ok(plantPage);
    }

    @PostMapping
    public ResponseEntity<PlantDTO> insertNewPlant(@RequestBody PlantDTO plant){
        PlantDTO plantDTO = service.insert(plant);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(plantDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(plantDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PlantDTO> change(@PathVariable Long id, @RequestBody PlantDTO plantDTO){
        plantDTO = service.update(id, plantDTO);
        return ResponseEntity.ok(plantDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePlantById(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
