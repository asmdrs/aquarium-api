package com.asmdrs.aquariummonitor.controllers;

import com.asmdrs.aquariummonitor.dto.AquariumDTO;
import com.asmdrs.aquariummonitor.entities.WaterType;
import com.asmdrs.aquariummonitor.services.AquariumService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MockitoSettings
class AquariumControllerTest {
    @Mock
    AquariumService aquariumService;
    @InjectMocks AquariumController aquariumController;

    private final AquariumDTO mockDto = new AquariumDTO(
            123L,
            "nome1",
            25,
            LocalDate.of(2020, 3, 12),
            WaterType.FRESH_WATER
    );

    @Test
    @DisplayName("DADO QUE seja feita uma consulta por ID" +
            "QUANDO a busca ocorrer com sucesso" +
            "ENTÃO deve retornar um aquariumDTO")
    void shouldFindByIdSuccesfully(){
        when(aquariumService.findById(123L)).thenReturn(mockDto);
        assertEquals(aquariumController.findById(123L), mockDto);
    }

    @Test
    @DisplayName("DADO QUE seja feita uma consulta geral" +
            "QUANDO a busca ocorrer com sucesso" +
            "ENTÃO deve retornar todos os aquariumDTO")
    void shouldFindAllSuccesfully(){
        when(aquariumService.getAllAquariums()).thenReturn(List.of(mockDto));
        ResponseEntity<List<AquariumDTO>> responseEntity = new ResponseEntity<>(List.of(mockDto), HttpStatus.OK);
        assertEquals(aquariumController.getAllAquariums(), responseEntity);
    }


}