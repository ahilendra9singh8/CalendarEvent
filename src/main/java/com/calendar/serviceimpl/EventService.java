package com.calendar.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendar.entity.Event;
import com.calendar.repository.EventRepository;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
	@Autowired
	EventRepository eventRepository;

	public List<Event> getAllEvents() {
		List<Event> getAllEvents = eventRepository.findAll();
		return getAllEvents;
	}

	public Optional<Event> getEventById(Long id) {
		Optional<Event> getEventById = eventRepository.findById(id);
		return getEventById;
	}

	public Event saveEvent(Event event,  HttpServletRequest request) {
		String clientIP = (String) request.getAttribute("clientIP");
		String location = (String) request.getAttribute("location");
		String timezone = (String) request.getAttribute("timezone");
		event.setCreatedAt(LocalDateTime.now());
        event.setIpAddress(clientIP);
        event.setLocation(location);
        event.setTimezone(timezone);
		Event saveEvent = eventRepository.save(event);
		return saveEvent;
	}
	
	public Event UpdateEvent(Long id, Event newevent) {
		Event oldEvent = eventRepository.findById(id).orElseThrow();
		oldEvent.setTitle(newevent.getTitle());
		oldEvent.setStart(newevent.getStart());
		oldEvent.setEnd(newevent.getEnd());
		Event UpdateEvent = eventRepository.save(oldEvent);
		return UpdateEvent;
	}

	public String deleteEvent(Long id) {
		String message = "";
		try {
			eventRepository.deleteById(id);
			message = "Success";
		} catch (Exception e) {
			System.out.println("Error");
			message = "Error";
		}
		return message;
	}

}
