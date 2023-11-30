package com.asmdrs.aquariummonitor.services;

import com.asmdrs.aquariummonitor.dto.ParametersDTO;
import com.asmdrs.aquariummonitor.entities.Aquarium;
import com.asmdrs.aquariummonitor.entities.Parameters;
import com.asmdrs.aquariummonitor.repository.AquariumRepository;
import com.asmdrs.aquariummonitor.repository.ParameterRepository;
import com.asmdrs.aquariummonitor.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ParameterService {
    @Autowired
    private ParameterRepository parameterRepository;
    @Autowired
    private AquariumRepository aquariumRepository;

    @Transactional(readOnly = true)
    public ParametersDTO findById(Long id){
        Parameters parameters = parameterRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Recurso não encontrado"));
        return new ParametersDTO(parameters);
    }

    @Transactional(readOnly = true)
    public Page<ParametersDTO> findAll (Pageable pageable){
        Page<Parameters> parametersList = parameterRepository.findAll(pageable);
        return parametersList.map(e -> new ParametersDTO(e));
    }

    @Transactional(readOnly = false)
    public ParametersDTO insert(ParametersDTO parametersDTO){
        Parameters entity = new Parameters();
        copyDtoToEntity(parametersDTO, entity);
        Aquarium aquarium = aquariumRepository.getReferenceById(parametersDTO.getAquarium().getId());
        aquarium.setLastReadingDate(LocalDate.now());

        entity.setAquarium(aquarium);
        entity = parameterRepository.save(entity);
        return new ParametersDTO(entity);

    }

    @Transactional(readOnly = false)
    public ParametersDTO update(Long id, ParametersDTO params){
        Parameters entity = parameterRepository.getReferenceById(id);
        copyDtoToEntity(params, entity);
        entity= parameterRepository.save(entity);
        return new ParametersDTO(entity);
    }

    @Transactional(readOnly = false)
    public void delete(Long id){
        parameterRepository.deleteById(id);
    }

    private void copyDtoToEntity(ParametersDTO params, Parameters entity ){
        entity.setTemperature(params.getTemperature());
        entity.setQtdPh(params.getQtdPh());
        entity.setQtdNitrito(params.getQtdNitrito());
        entity.setQtdNitrato(params.getQtdNitrato());
        entity.setValorKh(params.getValorKh());
    }


}
