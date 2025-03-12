package com.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.calendar.entity.Event;
import com.calendar.serviceimpl.EventService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/events")
public class EventController {

	@Autowired
	EventService eventService;

	@GetMapping("/getallevents")
	public ResponseEntity<List<Event>> getAllEvents() {
		List<Event> getAllEvents = null;
		try {
			getAllEvents = eventService.getAllEvents();

		} catch (Exception e) {
			System.out.println("getAllEvents Error : " + e);
		}
		return new ResponseEntity<List<Event>>(getAllEvents, HttpStatus.OK);
	}

	@GetMapping("/geteventbyid/{id}")
	public ResponseEntity<Optional<Event>> getEventById(@PathVariable Long id) {
		Optional<Event> getEventById = null;
		try {
			getEventById = eventService.getEventById(id);
		} catch (Exception e) {
			System.out.println("getEventById Error : " + e);
		}
		return new ResponseEntity<Optional<Event>>(getEventById, HttpStatus.OK);
	}

	@PostMapping("/createevent")
	public ResponseEntity<Event> createEvent(@RequestBody Event event,  HttpServletRequest request) {
		Event createdevent = null;
		try {
			createdevent = eventService.saveEvent(event, request);
		} catch (Exception e) {
			System.out.println("createEvent Error : " + e);
		}
		return new ResponseEntity<Event>(createdevent, HttpStatus.ACCEPTED);
	}

	@PutMapping("/updateevent/{id}")
	public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
		Event updatedevent = null;
		try {
			updatedevent = eventService.UpdateEvent(id, updatedEvent);
		} catch (Exception e) {
			System.out.println("updateEvent Error : " + e);
		}
		return new ResponseEntity<Event>(updatedevent, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteevent/{id}")
	public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
		String message = "";
		try {
			message = eventService.deleteEvent(id);
		} catch (Exception e) {
			System.out.println("deleteEvent Error : " + e);
		}
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
