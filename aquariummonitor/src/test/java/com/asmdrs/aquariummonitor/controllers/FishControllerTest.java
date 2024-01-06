package com.asmdrs.aquariummonitor.controllers;

import com.asmdrs.aquariummonitor.dto.AquariumDTO;
import com.asmdrs.aquariummonitor.dto.FishDTO;
import com.asmdrs.aquariummonitor.entities.WaterType;
import com.asmdrs.aquariummonitor.services.FishService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@MockitoSettings
class FishControllerTest {
    @Mock
    private FishService fishService;
    @InjectMocks
    private FishController fishController;

    private final AquariumDTO aquariumDTO = new AquariumDTO(123L, "aquarium", 35, LocalDate.of(2020, 3, 12), WaterType.FRESH_WATER);
    private final FishDTO mockFishDto = new FishDTO(123L, "Betta", 1, "Nemo", aquariumDTO);

    @Test
    @DisplayName("DADO QUE seja feita uma consulta por Id" +
            "QUANDO houver sucesso na requisição" +
            "ENTÃO deve retornar a entidade correspondente")
    void shouldFindByIdSuccesfully(){
        when(fishService.findById(123L)).thenReturn(mockFishDto);
        ResponseEntity<FishDTO> responseEntity= new ResponseEntity<>(mockFishDto, HttpStatus.OK);


        assertEquals(fishController.findById(123L), responseEntity);
    }

    @Test
    @DisplayName("DADO QUE seja feita uma consulta geral" +
            "QUANDO houver sucesso na requisição" +
            "ENTÃO deve retornar a entidade correspondente")
    void shouldFindByAllSuccesfully(){
        Page<FishDTO> mockFishPage = new PageImpl<>(List.of(mockFishDto));
        ResponseEntity<Page<FishDTO>> responseEntity= new ResponseEntity<>(mockFishPage, HttpStatus.OK);
        Pageable pageable = PageRequest.of(0, 10);

        when(fishService.findAll(pageable)).thenReturn(mockFishPage);
        assertEquals(fishController.findAll(pageable), responseEntity);
    }

    @Test
    @DisplayName("DADO QUE seja feita uma requisição PUT" +
            "QUANDO houver sucesso na requisição" +
            "ENTÃO deve alterar o peixe com sucesso")
    void shouldChangeSuccesfully(){
        ResponseEntity<FishDTO> responseEntity= new ResponseEntity<>(mockFishDto, HttpStatus.OK);
        when(fishService.update(123L, mockFishDto)).thenReturn(mockFishDto);

        assertEquals(fishController.update(123L, mockFishDto), responseEntity);
    }

    @Test
    @DisplayName("DADO QUE seja feita uma requisição DELETE" +
            "QUANDO houver sucesso na requisição" +
            "ENTÃO deve deletar o peixe com o ID selecionado")
    void shouldDeleteSuccesfully(){
        ResponseEntity<Object> emptyResponseEntity = ResponseEntity.noContent().build();
        assertEquals(fishController.deleteById(123L), emptyResponseEntity);
    }
}