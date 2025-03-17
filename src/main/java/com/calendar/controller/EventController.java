package com.calendar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendar.entity.Event;
import com.calendar.serviceimpl.EventService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/events")
public class EventController {

	@Autowired
	EventService eventService;

	@GetMapping("/getallevents")
	public ResponseEntity<List<Event>> getAllEvents(
			@RequestHeader(value = "X-Client-IP", required = false) String ipAddress) {
		List<Event> getAllEvents = null;
		try {
			getAllEvents = eventService.getEventsByIpAddress(ipAddress);
		} catch (Exception e) {
			System.out.println("getAllEvents Error : " + e);
		}
		return new ResponseEntity<List<Event>>(getAllEvents, HttpStatus.OK);
	}

	@PostMapping("/createevent")
	public ResponseEntity<Event> createEvent(@RequestBody Event event, HttpServletRequest request) {
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
