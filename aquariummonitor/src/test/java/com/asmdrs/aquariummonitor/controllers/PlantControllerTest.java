package com.asmdrs.aquariummonitor.controllers;

import com.asmdrs.aquariummonitor.dto.AquariumDTO;
import com.asmdrs.aquariummonitor.dto.PlantDTO;
import com.asmdrs.aquariummonitor.entities.WaterType;
import com.asmdrs.aquariummonitor.services.PlantsService;
import org.springframework.data.domain.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@MockitoSettings
class PlantControllerTest {
    @Mock
    private PlantsService plantsService;
    @InjectMocks
    private PlantController plantController;

    private final AquariumDTO aquariumDTO = new AquariumDTO(123L, "aquarium", 35, LocalDate.of(2020, 3, 12), WaterType.FRESH_WATER);
    private final PlantDTO mockPlantDto = new PlantDTO(123L, "plant1", "description", aquariumDTO);

    @Test
    @DisplayName("DADO QUE seja feita uma consulta por Id" +
            "QUANDO houver sucesso na requisição" +
            "ENTÃO deve retornar a entidade correspondente")
    void shouldFindByIdSuccesfully(){
        when(plantsService.findById(123L)).thenReturn(mockPlantDto);
        ResponseEntity<PlantDTO> responseEntity= new ResponseEntity<>(mockPlantDto, HttpStatus.OK);


        assertEquals(plantController.findById(123L), responseEntity);
    }


    @Test
    @DisplayName("DADO QUE seja feita uma consulta geral" +
            "QUANDO houver sucesso na requisição" +
            "ENTÃO deve retornar a entidade correspondente")
    void shouldFindByAllSuccesfully(){
        Page<PlantDTO> mockPlantPage = new PageImpl<>(List.of(mockPlantDto));
        ResponseEntity< Page<PlantDTO>> responseEntity= new ResponseEntity<>(mockPlantPage, HttpStatus.OK);
        Pageable pageable = PageRequest.of(0, 10);
        when(plantsService.findAll(pageable)).thenReturn(mockPlantPage);

        assertEquals(plantController.findAll(pageable), responseEntity);
    }

    @Test
    @DisplayName("DADO QUE seja feita uma requisição PUT" +
            "QUANDO houver sucesso na requisição" +
            "ENTÃO deve alterar a Planta com sucesso")
    void shouldChangePlantSuccesfully(){
        ResponseEntity<PlantDTO> responseEntity= new ResponseEntity<>(mockPlantDto, HttpStatus.OK);
        when(plantsService.update(123L, mockPlantDto)).thenReturn(mockPlantDto);

        assertEquals(plantController.change(123L, mockPlantDto), responseEntity);
    }

    @Test
    @DisplayName("DADO QUE seja feita uma requisição DELETE" +
            "QUANDO houver sucesso na requisição" +
            "ENTÃO deve deletar a planta com o ID selecionado")
    void shouldDeleteSuccesfully(){
        ResponseEntity<Object> emptyResponseEntity = ResponseEntity.noContent().build();
        assertEquals(plantController.deletePlantById(123L), emptyResponseEntity);
    }



}