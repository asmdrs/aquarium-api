package com.asmdrs.aquariummonitor.controllers;

import com.asmdrs.aquariummonitor.dto.AquariumDTO;
import com.asmdrs.aquariummonitor.dto.EventDTO;
import com.asmdrs.aquariummonitor.dto.FishDTO;
import com.asmdrs.aquariummonitor.entities.WaterType;
import com.asmdrs.aquariummonitor.services.EventService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@MockitoSettings
class EventControllerTest {
    @Mock
    private EventService eventService;
    @InjectMocks
    private EventController eventController;

    private final AquariumDTO aquariumDTO = new AquariumDTO(123L, "aquarium", 35, LocalDate.of(2020, 3, 12), WaterType.FRESH_WATER);
    private final EventDTO mockEventDto = new EventDTO(123L, "WaterChange", "Changed water", aquariumDTO);

    @Test
    @DisplayName("DADO QUE seja feita uma consulta por Id" +
            "QUANDO houver sucesso na requisição" +
            "ENTÃO deve retornar a entidade correspondente")
    void shouldFindByIdSuccesfully(){
        when(eventService.getById(123L)).thenReturn(mockEventDto);
        ResponseEntity<EventDTO> responseEntity= new ResponseEntity<>(mockEventDto, HttpStatus.OK);


        assertEquals(eventController.findById(123L), responseEntity);
    }

    @Test
    @DisplayName("DADO QUE seja feita uma consulta geral" +
            "QUANDO houver sucesso na requisição" +
            "ENTÃO deve retornar a entidade correspondente")
    void shouldFindByAllSuccesfully(){
        Page<EventDTO> mockEventPage = new PageImpl<>(List.of(mockEventDto));
        ResponseEntity<Page<EventDTO>> responseEntity= new ResponseEntity<>(mockEventPage, HttpStatus.OK);
        Pageable pageable = PageRequest.of(0, 10);

        when(eventService.getAll(pageable)).thenReturn(mockEventPage);
        assertEquals(eventController.findAll(pageable), responseEntity);
    }

    @Test
    @DisplayName("DADO QUE seja feita uma requisição PUT" +
            "QUANDO houver sucesso na requisição" +
            "ENTÃO deve alterar o evento com sucesso")
    void shouldChangeSuccesfully(){
        ResponseEntity<EventDTO> responseEntity= new ResponseEntity<>(mockEventDto, HttpStatus.OK);
        when(eventService.update(123L, mockEventDto)).thenReturn(mockEventDto);

        assertEquals(eventController.change(123L, mockEventDto), responseEntity);
    }

    @Test
    @DisplayName("DADO QUE seja feita uma requisição DELETE" +
            "QUANDO houver sucesso na requisição" +
            "ENTÃO deve deletar o evento com o ID selecionado")
    void shouldDeleteSuccesfully(){
        ResponseEntity<Object> emptyResponseEntity = ResponseEntity.noContent().build();
        assertEquals(eventController.deleteEventById(123L), emptyResponseEntity);
    }
}