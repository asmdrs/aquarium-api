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

@Service
public class PlantsService {
    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private AquariumRepository aquariumRepository;

    @Transactional(readOnly = true)
    public PlantDTO findById(Long id) {
        Plant plant = plantRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Recurso não encontrado"));
        return new PlantDTO(plant);
    }

    @Transactional(readOnly = true)
    public Page<PlantDTO> findAll(Pageable pageable) {
        Page<Plant> plantList = plantRepository.findAll(pageable);
        return plantList.map(e -> new PlantDTO(e));

    }

    @Transactional(readOnly = false)
    public PlantDTO insert(PlantDTO plant) {
        Plant entity = new Plant();
        copyDtoToEntity(plant, entity);
        Aquarium aquarium = aquariumRepository.getReferenceById(plant.getAquarium().getId());

        entity.setAquarium(aquarium);
        entity = plantRepository.save(entity);

        return new PlantDTO(entity);
    }

    @Transactional(readOnly = false)
    public PlantDTO update(Long id,PlantDTO plant) {
        Plant entity = plantRepository.getReferenceById(id);
        copyDtoToEntity(plant, entity);
        entity = plantRepository.save(entity);
        return new PlantDTO(entity);
    }

    @Transactional()
    public void delete(Long id) {
        plantRepository.deleteById(id);
    }

    private void copyDtoToEntity(PlantDTO plant, Plant  entity){
        entity.setName(plant.getName());
        entity.setDescription(plant.getDescription());

    }

}