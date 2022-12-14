package com.suber.services;

import com.suber.dto.ServiceDTO;

import java.util.List;
import java.util.Optional;

public interface ServiceService {

    ServiceDTO save(ServiceDTO serviceDTO);

    Optional<ServiceDTO> findById(long id);

    Optional<ServiceDTO> updateService(long id, ServiceDTO service);

    List<ServiceDTO> findAll();

    List<ServiceDTO> findByName(String name);

    void deleteById(long id);

    void deleteAll();
}
