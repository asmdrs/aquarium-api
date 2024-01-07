package com.asmdrs.aquariummonitor.controllers;

import com.asmdrs.aquariummonitor.dto.EventDTO;
import com.asmdrs.aquariummonitor.services.EventService;
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
@RequestMapping(value="/events")
public class EventController {
    @Autowired
    private EventService service;
    private static final Logger LOGGER = Logger.getLogger(EventController.class.getName());

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventDTO> findById(@PathVariable Long id){
        try {
            EventDTO eventDTO = service.getById(id);
            return ResponseEntity.ok(eventDTO);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao encontrar evento por ID.", e);
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<Page<EventDTO>> findAll(Pageable pageable){
        try {
            Page<EventDTO> eventDTO = service.getAll(pageable);
            return ResponseEntity.ok(eventDTO);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao recuperar todos os eventos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<EventDTO> insertNewEvent(@RequestBody EventDTO event){
        try {
            EventDTO eventDTO = service.insert(event);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(eventDTO.getId()).toUri();
            return ResponseEntity.created(uri).body(eventDTO);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao recuperar todos os eventos.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EventDTO> change(@PathVariable Long id, @RequestBody EventDTO event){
        try {
            event = service.update(id, event);
            return ResponseEntity.ok(event);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao tentar atualizar o evento selecionado.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEventById (@PathVariable Long id){
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao tentar deletar o evento selecionado.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
