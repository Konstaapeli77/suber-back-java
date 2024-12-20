package com.suber.services.impl;

import com.suber.data.Service;
import com.suber.dto.ServiceDTO;
import com.suber.exception.ResourceNotFoundException;
import com.suber.repository.ServiceRepository;
import com.suber.services.ServiceService;
import com.suber.util.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    ServiceRepository serviceRepository;

    public ServiceDTO save(ServiceDTO serviceDto) {
        Service service = new Service();
        service.setName(serviceDto.getName());
        return DataMapper.getInstance().convertToDto(serviceRepository.save(service));
    }

    @Override
    public Optional<ServiceDTO> findById(long id) throws ResourceNotFoundException {
        Optional<Service> service = serviceRepository.findById(id);
        ServiceDTO originalServiceDTO = null;
        if (service.isPresent()) {
            originalServiceDTO = DataMapper.getInstance().convertToDto(service.get());
        } else {
            return Optional.empty();
        }

        Optional<ServiceDTO> serviceDTO= Optional.of(originalServiceDTO);
        return serviceDTO;
    }

    @Override
    public Optional<ServiceDTO> updateService(long id, ServiceDTO updatedService) throws ResourceNotFoundException {
        Optional<Service> service = serviceRepository.findById(id);

        ServiceDTO result = null;
        if (service.isPresent()) {
            Service serviceEntity = service.get();
            serviceEntity.setName(updatedService.getName());
            Service updatedServiceEntity = serviceRepository.save(serviceEntity);
            result = DataMapper.getInstance().convertToDto(updatedServiceEntity);
        } else {
            return Optional.empty();
        }

        Optional<ServiceDTO> serviceDTO= Optional.of(result);
        return serviceDTO;
    }

    @Override
    public List<ServiceDTO> findAll() {
/*
        List<ServiceDTO> servicesDTO = new ArrayList<ServiceDTO>();
        List<Service> services = new ArrayList<Service>();
        serviceRepository.findAll().forEach(services::add);
        for (Service service:services) {
            ServiceDTO serviceDTO = DataMapper.getInstance().convertToDto(service);
            servicesDTO.add(serviceDTO);
        }
        return servicesDTO;
*/
        return serviceRepository.findAll().stream()
                .map(service -> DataMapper.getInstance().convertToDto(service))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public List<ServiceDTO> findByName(String id) {
/*
        List<ServiceDTO> servicesDTO = new ArrayList<ServiceDTO>();
        List<Service> services = new ArrayList<Service>();
        serviceRepository.findByName(id).forEach(services::add);
        for (Service service:services) {
            ServiceDTO serviceDTO = DataMapper.getInstance().convertToDto(service);
            servicesDTO.add(serviceDTO);
        }
        return servicesDTO;
*/
        return serviceRepository.findByName(id).stream()
                .map(service -> DataMapper.getInstance().convertToDto(service))
                .collect(Collectors.toList());
    }

    public void deleteAll() {
        serviceRepository.deleteAll();
    }

}
