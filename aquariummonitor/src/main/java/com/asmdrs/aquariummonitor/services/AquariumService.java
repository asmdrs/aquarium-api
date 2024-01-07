package com.asmdrs.aquariummonitor.services;

import com.asmdrs.aquariummonitor.dto.AquariumDTO;
import com.asmdrs.aquariummonitor.entities.Aquarium;
import com.asmdrs.aquariummonitor.repository.AquariumRepository;
import com.asmdrs.aquariummonitor.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AquariumService {
    @Autowired
    private AquariumRepository aquariumRepository;
    private static final Logger LOGGER = Logger.getLogger(AquariumService.class.getName());
    @Transactional(readOnly = true)
    public AquariumDTO findById(Long id){
        Aquarium aquarium = aquariumRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Recurso -Aquário por ID- não encontrado"));;
        return new AquariumDTO(aquarium);
    }

    @Transactional(readOnly = true)
    public List<AquariumDTO> getAllAquariums(){
        try {
            List<Aquarium> aquariumList = aquariumRepository.findAll();
            return aquariumList.stream().map(e -> new AquariumDTO(e)).toList();
        } catch (Exception e){
            LOGGER.log(Level.SEVERE, "Recurso -Todos os Aquários- não encontrado.", e);
            throw e;
        }

    }

    @Transactional
    public AquariumDTO insert (AquariumDTO aquarium){
        try {
            Aquarium entity = new Aquarium();
            copyToEntity(aquarium,entity);
            entity = aquariumRepository.save(entity);
            return new AquariumDTO(entity);
        } catch (Exception e){
            LOGGER.log(Level.SEVERE, "Inserção de recurso -Aquário- falhou.", e);
            throw e;
        }
    }

    private void copyToEntity(AquariumDTO aquarium, Aquarium entity){
        entity.setName(aquarium.getName());
        entity.setVolume(aquarium.getVolume());
        entity.setLastReadingDate(aquarium.getLastReadingDate());
        entity.setWaterType(aquarium.getWaterType());
    }

}
