package com.beyondtrust.eventforwarder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(EventDTO eventDTO) {
        Event event = mapDtoToEntity(eventDTO);
        return eventRepository.save(event);
    }

    private Event mapDtoToEntity(EventDTO eventDTO) {
        Event event = new Event();
        event.setFormatVersion(eventDTO.getFormatVersion());
        event.setVendor(eventDTO.getVendor());
        event.setProduct(eventDTO.getProduct());
        event.setVersion(eventDTO.getVersion());
        event.setAgentId(eventDTO.getAgentId());
        event.setAgentDesc(eventDTO.getAgentDesc());
        event.setAgentVer(eventDTO.getAgentVer());
        event.setCategory(eventDTO.getCategory());
        event.setSeverity(eventDTO.getSeverity());
        event.setEventId(eventDTO.getEventId());
        event.setEventName(eventDTO.getEventName());
        event.setEventDesc(eventDTO.getEventDesc());
        event.setEventDate(eventDTO.getEventDate());
        event.setSourceHost(eventDTO.getSourceHost());
        event.setOs(eventDTO.getOs());
        event.setSourceIp(eventDTO.getSourceIp());
        event.setEventSubject(eventDTO.getEventSubject());
        event.setEventType(eventDTO.getEventType());
        event.setUser(eventDTO.getUser());
        event.setWorkgroupId(eventDTO.getWorkgroupId());
        event.setWorkgroupDesc(eventDTO.getWorkgroupDesc());
        event.setWorkgroupLocation(eventDTO.getWorkgroupLocation());
        event.setNvps(eventDTO.getNvps().toString());
        return event;
    }
}
