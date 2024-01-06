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

@RestController
@RequestMapping(value ="/aquariums")
public class AquariumController {
    @Autowired
    private AquariumService service;
    @GetMapping(value = "/{id}")
    public AquariumDTO findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<AquariumDTO>> getAllAquariums() {
        List<AquariumDTO> aquariums = service.getAllAquariums();
        return new ResponseEntity<>(aquariums, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AquariumDTO> insertNewAquarium(@RequestBody AquariumDTO aquarium){
        AquariumDTO aquariumDTO = service.insert(aquarium);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(aquariumDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(aquariumDTO);
    }


}
