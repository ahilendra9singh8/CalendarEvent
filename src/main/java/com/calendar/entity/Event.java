package com.calendar.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private Date start;
	private Date end;
//	private String ipAddress;
	private LocalDateTime createdAt;
	private String location;
	private String timezone;

	public Event() {

	}

	public Event(String title, Date start, Date end) {
		this.title = title;
		this.start = start;
		this.end = end;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

//	public String getIpAddress() {
//		return ipAddress;
//	}
//	
//	public void setIpAddress(String ipAddress) {
//		this.ipAddress = ipAddress;
//	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getLocation() {
	    return location;
	}

	public void setLocation(String location) {
	    this.location = location;
	}

	public String getTimezone() {
	    return timezone;
	}

	public void setTimezone(String timezone) {
	    this.timezone = timezone;
	}
}
