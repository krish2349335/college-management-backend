package com.college.college_management_system.repository;

import com.college.college_management_system.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {

    List<Transport> findByRouteNameContainingIgnoreCase(String routeName);

    List<Transport> findByVehicleTypeIgnoreCase(String vehicleType);

    List<Transport> findByDriverNameContainingIgnoreCase(String driverName);
}
