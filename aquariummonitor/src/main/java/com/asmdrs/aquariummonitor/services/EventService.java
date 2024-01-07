package com.asmdrs.aquariummonitor.services;

import com.asmdrs.aquariummonitor.dto.EventDTO;
import com.asmdrs.aquariummonitor.entities.Aquarium;
import com.asmdrs.aquariummonitor.entities.Event;
import com.asmdrs.aquariummonitor.repository.AquariumRepository;
import com.asmdrs.aquariummonitor.repository.EventRepository;
import com.asmdrs.aquariummonitor.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private AquariumRepository aquariumRepository;
    private static final Logger LOGGER = Logger.getLogger(EventService.class.getName());
    @Transactional(readOnly = true)
    public Page<EventDTO> getAll(Pageable pageable) {
        try {
            Page<Event> eventList = eventRepository.findAll(pageable);
            return eventList.map( e -> new EventDTO(e));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Recurso -Registro de Eventos- não encontrado.", e);
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public EventDTO getById(Long id){
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(("Recurso não encontrado."))
        );
        return new EventDTO(event);
    }

    @Transactional(readOnly = false)
    public EventDTO insert(EventDTO event) {
        try {
            Event entity = new Event();
            copyDtoToEntity(event, entity);
            Aquarium aquarium = aquariumRepository.getReferenceById(event.getAquarium().getId());

            entity.setAquarium(aquarium);
            entity = eventRepository.save(entity);

            return new EventDTO(entity);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Inserção de recurso -Registro de Eventos- falhou.", e);
            throw e;
        }
    }

    @Transactional(readOnly = false)
    public EventDTO update(Long id, EventDTO event) {
        try {
            Event entity = eventRepository.getReferenceById(id);
            copyDtoToEntity(event, entity);
            entity= eventRepository.save(entity);
            return new EventDTO(entity);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Atualização de recurso -Registro de Eventos- falhou.", e);
            throw e;
        }

    }

    @Transactional
    public void delete(Long id) {
        try {
            eventRepository.deleteById(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Deleção de recurso -Registro de Eventos- falhou.", e);
            throw e;
        }
    }

    private void copyDtoToEntity(EventDTO event, Event entity) {
        entity.setName(event.getName());
        entity.setDescription(event.getDescription());
    }
}