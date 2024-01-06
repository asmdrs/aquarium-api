package com.asmdrs.aquariummonitor.repository;

import com.asmdrs.aquariummonitor.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
