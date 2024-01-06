package com.asmdrs.aquariummonitor.controllers;

import com.asmdrs.aquariummonitor.dto.FishDTO;
import com.asmdrs.aquariummonitor.services.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/fish")
public class FishController {
    @Autowired
    private FishService fishService;

    @GetMapping(value="/{id}")
    public ResponseEntity<FishDTO> findById(@PathVariable Long id){
        FishDTO fishDTO = fishService.findById(id);
        return ResponseEntity.ok(fishDTO);
    }

    @GetMapping
    public ResponseEntity<Page<FishDTO>> findAll(Pageable pageable){
        Page<FishDTO> fishDTO = fishService.findAll(pageable);
        return ResponseEntity.ok(fishDTO);
    }

    @PostMapping ResponseEntity<FishDTO> insertNewFish(@RequestBody FishDTO fish){
        FishDTO fishDTO = fishService.insert(fish);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(fishDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(fishDTO);
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<FishDTO> update(@PathVariable Long id, @RequestBody FishDTO fishDTO){
        fishDTO = fishService.update(id, fishDTO);
        return ResponseEntity.ok(fishDTO);
    }

    @DeleteMapping(value = "/{id}")
    public  ResponseEntity<Void> deleteById(@PathVariable Long id){
        fishService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
