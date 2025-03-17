package com.calendar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calendar.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

	List<Event> findByIpAddress(String ipAddress); 
	
}

