package com.asmdrs.aquariummonitor.controllers;

import com.asmdrs.aquariummonitor.dto.FishDTO;
import com.asmdrs.aquariummonitor.services.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/fish")
public class FishController {
    @Autowired
    private FishService fishService;
    private static final Logger LOGGER = Logger.getLogger(FishController.class.getName());
    @GetMapping(value="/{id}")
    public ResponseEntity<FishDTO> findById(@PathVariable Long id){
        try {
            FishDTO fishDTO = fishService.findById(id);
            return ResponseEntity.ok(fishDTO);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao tentar recuperar peixe por ID.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<FishDTO>> findAll(Pageable pageable){
        try {
            Page<FishDTO> fishDTO = fishService.findAll(pageable);
            return ResponseEntity.ok(fishDTO);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao recuperar todos os peixes.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping ResponseEntity<FishDTO> insertNewFish(@RequestBody FishDTO fish){
        try {
            FishDTO fishDTO = fishService.insert(fish);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                    .buildAndExpand(fishDTO.getId()).toUri();
            return ResponseEntity.created(uri).body(fishDTO);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao inserir novo peixe.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<FishDTO> update(@PathVariable Long id, @RequestBody FishDTO fishDTO){
        try {
            fishDTO = fishService.update(id, fishDTO);
            return ResponseEntity.ok(fishDTO);
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao alterar peixe selecionado.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public  ResponseEntity<Void> deleteById(@PathVariable Long id){
        try {
            fishService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao deletar peixe selecionado.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
