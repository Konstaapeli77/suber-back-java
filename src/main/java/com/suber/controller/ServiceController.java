package com.suber.controller;

import com.suber.dto.ServiceDTO;
import com.suber.dto.ServiceListDTO;
import com.suber.services.ServiceService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
public class ServiceController {
    
    Logger logger = LogManager.getLogger(PersonController.class);

    @Autowired
    ServiceService serviceService;

    @GetMapping("/services")
    public ResponseEntity<ServiceListDTO> getAllServices() {
        try {
            List<ServiceDTO> services = new ArrayList<ServiceDTO>();

            serviceService.findAll().forEach(services::add);

            if (services.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(new ServiceListDTO(services), HttpStatus.OK);
        } catch (Exception e) {
            logger.log(Level.ERROR, "Error in getAllPersons: " + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/services/{id}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable("id") long id) {

        Optional<ServiceDTO> serviceData = serviceService.findById(id);

        if (serviceData.isPresent()) {
            return new ResponseEntity<>(serviceData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/services")
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceDTO person) {
        try {
            ServiceDTO _service = serviceService
                    .save(person);
            return new ResponseEntity<>(_service, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/services/{id}")
    public ResponseEntity<ServiceDTO> updateService(@PathVariable("id") long id, @RequestBody ServiceDTO service) {

        Optional<ServiceDTO> serviceData = serviceService.updateService(id, service);

        if (serviceData.isPresent()) {
            return new ResponseEntity<>(serviceData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/services/{id}")
    public ResponseEntity<HttpStatus> deleteService(@PathVariable("id") long id) {
        try {
            serviceService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/services")
    public ResponseEntity<HttpStatus> deleteAllServices() {
        try {
            serviceService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/services/name")
    public ResponseEntity<ServiceListDTO> findByName(@PathVariable("name") String name) {
        try {
            List<ServiceDTO> services = serviceService.findByName(name);

            if (services.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new ServiceListDTO(services), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
