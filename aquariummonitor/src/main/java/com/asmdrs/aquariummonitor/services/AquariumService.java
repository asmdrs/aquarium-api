package com.asmdrs.aquariummonitor.services;

import com.asmdrs.aquariummonitor.dto.AquariumDTO;
import com.asmdrs.aquariummonitor.entities.Aquarium;
import com.asmdrs.aquariummonitor.repository.AquariumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AquariumService {
    @Autowired
    private AquariumRepository aquariumRepository;
    @Transactional(readOnly = true)
    public AquariumDTO findById(Long id){
        Aquarium aquarium = aquariumRepository.findById(id).get();
        return new AquariumDTO(aquarium);
    }

    @Transactional(readOnly = true)
    public List<AquariumDTO> getAllAquariums(){
        List<Aquarium> aquariumList = aquariumRepository.findAll();
        return aquariumList.stream().map(e -> new AquariumDTO(e)).toList();
            }
}
