package com.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calendar.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	
}

