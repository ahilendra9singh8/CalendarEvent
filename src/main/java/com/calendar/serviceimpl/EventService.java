package com.calendar.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendar.entity.Event;
import com.calendar.repository.EventRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class EventService {
	@Autowired
	EventRepository eventRepository;

	public List<Event> getEventsByIpAddress(String ipAddress) {
		List<Event> getEvents = eventRepository.findByIpAddress(ipAddress);
		return getEvents;
	}

	public Event saveEvent(Event event,  HttpServletRequest request) {
		event.setCreatedAt(LocalDateTime.now());
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

