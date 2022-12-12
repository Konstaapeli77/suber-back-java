package com.suber.services;

import com.suber.data.Company;
import com.suber.data.Person;
import com.suber.dto.OrderDTO;
import com.suber.dto.ServiceDTO;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ServiceService {

    ServiceDTO save(ServiceDTO serviceDTO);

    Optional<ServiceDTO> findById(long id);

    List<ServiceDTO> findAll();

    void deleteById(long id);

    void deleteAll();
}
