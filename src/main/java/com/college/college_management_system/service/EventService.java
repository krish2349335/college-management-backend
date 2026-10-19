package com.college.college_management_system.service;

import com.college.college_management_system.entity.Event;
import com.college.college_management_system.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public Event updateEvent(Long id, Event event) {

        Event existingEvent = getEventById(id);

        existingEvent.setTitle(event.getTitle());
        existingEvent.setDescription(event.getDescription());
        existingEvent.setEventDate(event.getEventDate());
        existingEvent.setStartTime(event.getStartTime());
        existingEvent.setEndTime(event.getEndTime());
        existingEvent.setVenue(event.getVenue());
        existingEvent.setImageUrl(event.getImageUrl());
        existingEvent.setRegistrationUrl(event.getRegistrationUrl());

        return eventRepository.save(existingEvent);
    }

    public void deleteEvent(Long id) {
        Event event = getEventById(id);
        eventRepository.delete(event);
    }

    public List<Event> getUpcomingEvents() {
        return eventRepository
                .findByEventDateGreaterThanEqualOrderByEventDateAsc(
                        LocalDate.now()
                );
    }

    public List<Event> getAllEventsSorted() {
        return eventRepository.findAllByOrderByEventDateAsc();
    }
}
