package com.asmdrs.aquariummonitor.repository;

import com.asmdrs.aquariummonitor.entities.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishRepository extends JpaRepository<Fish, Long> {

}
