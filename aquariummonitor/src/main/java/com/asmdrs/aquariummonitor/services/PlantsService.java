package com.asmdrs.aquariummonitor.services;

import com.asmdrs.aquariummonitor.dto.PlantDTO;
import com.asmdrs.aquariummonitor.entities.Aquarium;
import com.asmdrs.aquariummonitor.entities.Plant;
import com.asmdrs.aquariummonitor.repository.AquariumRepository;
import com.asmdrs.aquariummonitor.repository.PlantRepository;
import com.asmdrs.aquariummonitor.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PlantsService {
    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private AquariumRepository aquariumRepository;
    private static final Logger LOGGER = Logger.getLogger(PlantsService.class.getName());

    @Transactional(readOnly = true)
    public PlantDTO findById(Long id) {
        Plant plant = plantRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Recurso não encontrado"));
        return new PlantDTO(plant);
    }

    @Transactional(readOnly = true)
    public Page<PlantDTO> findAll(Pageable pageable) {
        try {
            Page<Plant> plantList = plantRepository.findAll(pageable);
            return plantList.map(e -> new PlantDTO(e));
        } catch (Exception e){
            LOGGER.log(Level.SEVERE, "Recurso -Todas as Plantas- não encontrado", e);
            throw e;
        }
    }

    @Transactional(readOnly = false)
    public PlantDTO insert(PlantDTO plant) {
        try {
            Plant entity = new Plant();
            copyDtoToEntity(plant, entity);
            Aquarium aquarium = aquariumRepository.getReferenceById(plant.getAquarium().getId());

            entity.setAquarium(aquarium);
            entity = plantRepository.save(entity);

            return new PlantDTO(entity);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Inserção de recurso -Planta- falhou", e);
            throw e;
        }
    }

    @Transactional(readOnly = false)
    public PlantDTO update(Long id,PlantDTO plant) {
        try {
            Plant entity = plantRepository.getReferenceById(id);
            copyDtoToEntity(plant, entity);
            entity = plantRepository.save(entity);
            return new PlantDTO(entity);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Ataulização de recurso -Planta- falhou", e);
            throw e;
        }

    }

    @Transactional()
    public void delete(Long id) {
        try {
            plantRepository.deleteById(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Deleção de recurso -Planta- falhou", e);
            throw e;
        }
    }

    private void copyDtoToEntity(PlantDTO plant, Plant  entity){
        entity.setName(plant.getName());
        entity.setDescription(plant.getDescription());
    }

}