package com.college.college_management_system.controller;

import com.college.college_management_system.entity.Event;
import com.college.college_management_system.service.EventService;
import com.college.college_management_system.service.FileStorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final FileStorageService fileStorageService;

    @PostMapping
    public ResponseEntity<Event> createEvent(
         @Valid   @RequestBody Event event) {

        return new ResponseEntity<>(
                eventService.createEvent(event),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/upload")
    public ResponseEntity<Event> uploadEvent(
            @RequestParam String title,
            @RequestParam(required = false) String description,
            @RequestParam String eventDate,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false) String venue,
            @RequestParam(required = false) String registrationUrl,
            @RequestParam("file") MultipartFile file) throws IOException {

        String imageUrl = fileStorageService.uploadFile(file);

        Event event = new Event();

        event.setTitle(title);
        event.setDescription(description);
        event.setEventDate(LocalDate.parse(eventDate));

        if (startTime != null && !startTime.isBlank()) {
            event.setStartTime(LocalTime.parse(startTime));
        }

        if (endTime != null && !endTime.isBlank()) {
            event.setEndTime(LocalTime.parse(endTime));
        }

        event.setVenue(venue);
        event.setImageUrl(imageUrl);
        event.setRegistrationUrl(registrationUrl);

        return new ResponseEntity<>(
                eventService.createEvent(event),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(
                eventService.getAllEvents()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                eventService.getEventById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Long id,
          @Valid @RequestBody Event event) {

        return ResponseEntity.ok(
                eventService.updateEvent(id, event)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(
            @PathVariable Long id) {

        eventService.deleteEvent(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<Event>> getUpcomingEvents() {
        return ResponseEntity.ok(
                eventService.getUpcomingEvents()
        );
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Event>> getAllEventsSorted() {
        return ResponseEntity.ok(
                eventService.getAllEventsSorted()
        );
    }
}