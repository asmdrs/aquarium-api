package com.asmdrs.aquariummonitor.controllers;

import com.asmdrs.aquariummonitor.dto.AquariumDTO;
import com.asmdrs.aquariummonitor.dto.ParametersDTO;
import com.asmdrs.aquariummonitor.entities.WaterType;
import com.asmdrs.aquariummonitor.services.ParameterService;
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
class ParameterControllerTest {
    @Mock
    ParameterService parameterService;
    @InjectMocks
    ParameterController parameterController;

    private final AquariumDTO aquariumDTO = new AquariumDTO(123L, "aquarium", 35, LocalDate.of(2020, 3, 12), WaterType.FRESH_WATER);
    private final ParametersDTO mockParametersDto = new ParametersDTO(123L,7,1,1,1, 26.0,aquariumDTO);

    @Test
    @DisplayName("DADO QUE seja feita uma consulta por Id" +
            "QUANDO houver sucesso na requisição" +
            "ENTÃO deve retornar a entidade correspondente")
    void shouldFindByIdSuccesfully(){
        when(parameterService.findById(123L)).thenReturn(mockParametersDto);
        ResponseEntity<ParametersDTO> responseEntity= new ResponseEntity<>(mockParametersDto, HttpStatus.OK);


        assertEquals(parameterController.findById(123L), responseEntity);
    }

    @Test
    @DisplayName("DADO QUE seja feita uma consulta geral" +
            "QUANDO houver sucesso na requisição" +
            "ENTÃO deve retornar a entidade correspondente")
    void shouldFindByAllSuccesfully(){
        Page<ParametersDTO> mockParameterPage = new PageImpl<>(List.of(mockParametersDto));
        ResponseEntity<Page<ParametersDTO>> responseEntity= new ResponseEntity<>(mockParameterPage, HttpStatus.OK);
        Pageable pageable = PageRequest.of(0, 10);

        when(parameterService.findAll(pageable)).thenReturn(mockParameterPage);
        assertEquals(parameterController.findAll(pageable), responseEntity);
    }

    @Test
    @DisplayName("DADO QUE seja feita uma requisição PUT" +
            "QUANDO houver sucesso na requisição" +
            "ENTÃO deve alterar a leitura com sucesso")
    void shouldChangeSuccesfully(){
        ResponseEntity<ParametersDTO> responseEntity= new ResponseEntity<>(mockParametersDto, HttpStatus.OK);
        when(parameterService.update(123L, mockParametersDto)).thenReturn(mockParametersDto);

        assertEquals(parameterController.change(123L, mockParametersDto), responseEntity);
    }

    @Test
    @DisplayName("DADO QUE seja feita uma requisição DELETE" +
            "QUANDO houver sucesso na requisição" +
            "ENTÃO deve deletar a leitura com o ID selecionado")
    void shouldDeleteSuccesfully(){
        ResponseEntity<Object> emptyResponseEntity = ResponseEntity.noContent().build();
        assertEquals(parameterController.deleteParameterReadingById(123L), emptyResponseEntity);
    }
}