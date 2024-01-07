package com.asmdrs.aquariummonitor.services;

import com.asmdrs.aquariummonitor.dto.FishDTO;
import com.asmdrs.aquariummonitor.entities.Aquarium;
import com.asmdrs.aquariummonitor.entities.Fish;
import com.asmdrs.aquariummonitor.repository.AquariumRepository;
import com.asmdrs.aquariummonitor.repository.FishRepository;
import com.asmdrs.aquariummonitor.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FishService {
    @Autowired
    private FishRepository fishRepository;

    @Autowired
    private AquariumRepository aquariumRepository;
    private static final Logger LOGGER = Logger.getLogger(FishService.class.getName());
    @Transactional(readOnly = true)
    public FishDTO findById(Long id) {
        Fish fish = fishRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Peixe não encontrado"));
        return new FishDTO(fish);
    }

    @Transactional(readOnly = true)
    public Page<FishDTO> findAll (Pageable pageable) {
        try {
            Page<Fish> fishList = fishRepository.findAll(pageable);
            return  fishList.map(e -> new FishDTO(e));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Recurso -Todos os Peixes- não encontrado", e);
            throw e;
        }

    }

    @Transactional()
    public FishDTO insert(FishDTO fish) {
        try {
            Fish entity = new Fish();

            copyDtoToEntity(fish, entity);
            Aquarium aquarium = aquariumRepository.getReferenceById(fish.getAquarium().getId());
            entity.setAquarium(aquarium);
            entity = fishRepository.save(entity);
            return new FishDTO(entity);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Inserção de recurso -Peixe- falhou", e);
            throw e;
        }

    }

    @Transactional()
    public FishDTO update(Long id, FishDTO fish) {
        try {
            Fish entity = fishRepository.getReferenceById(id);
            copyDtoToEntity(fish, entity);
            entity = fishRepository.save(entity);
            return new FishDTO(entity);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Atualização de recurso -Peixe- falhou", e);
            throw e;
        }

    }

    @Transactional()
    public void delete(Long id){
        try {
            fishRepository.deleteById(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Deleção de recurso -Peixe- falhou", e);
            throw e;
        }
    }

    private void copyDtoToEntity (FishDTO dto, Fish entity){
        entity.setAge(dto.getAge());
        entity.setType(dto.getType());
        entity.setName(dto.getName());
    }

}
