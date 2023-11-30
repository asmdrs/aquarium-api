package com.asmdrs.aquariummonitor.controllers;

import com.asmdrs.aquariummonitor.dto.ParametersDTO;
import com.asmdrs.aquariummonitor.services.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value ="/paramaters")
public class ParameterController {
    @Autowired
    private ParameterService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ParametersDTO> findById(@PathVariable Long id){
        ParametersDTO parametersDTO = service.findById(id);
        return ResponseEntity.ok(parametersDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ParametersDTO>> findAll(Pageable pageable){
        Page<ParametersDTO> parametersDTOPage = service.findAll(pageable);
        return ResponseEntity.ok(parametersDTOPage);
    }

    @PostMapping
    public ResponseEntity<ParametersDTO> insertNewParametersReading(@RequestBody ParametersDTO params){
        ParametersDTO parametersDTO = service.insert(params);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(parametersDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(parametersDTO);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ParametersDTO> change(@PathVariable Long id, @RequestBody ParametersDTO params){
        params = service.update(id, params);
        return ResponseEntity.ok(params);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteParameterReadingById(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
