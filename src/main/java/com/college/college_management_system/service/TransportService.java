package com.college.college_management_system.service;

import com.college.college_management_system.entity.Transport;
import com.college.college_management_system.repository.TransportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransportService {

    private final TransportRepository transportRepository;

    // Create Transport
    public Transport createTransport(Transport transport) {
        return transportRepository.save(transport);
    }

    // Get All Transports
    public List<Transport> getAllTransports() {
        return transportRepository.findAll();
    }

    // Get Transport By ID
    public Transport getTransportById(Long id) {
        return transportRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Transport not found with ID: " + id));
    }

    // Update Transport
    public Transport updateTransport(Long id, Transport transport) {

        Transport existingTransport = getTransportById(id);

        existingTransport.setVehicleNumber(transport.getVehicleNumber());
        existingTransport.setRouteName(transport.getRouteName());
        existingTransport.setVehicleType(transport.getVehicleType());
        existingTransport.setCapacity(transport.getCapacity());
        existingTransport.setDriverName(transport.getDriverName());
        existingTransport.setDriverPhone(transport.getDriverPhone());
        existingTransport.setPickupPoint(transport.getPickupPoint());
        existingTransport.setPickupTime(transport.getPickupTime());

        return transportRepository.save(existingTransport);
    }

    // Delete Transport
    public void deleteTransport(Long id) {
        Transport transport = getTransportById(id);
        transportRepository.delete(transport);
    }
}
