package com.suber.util.mapper;

import com.suber.data.*;
import com.suber.dto.*;
import com.suber.services.impl.CompanyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;

@Component
public final class DataMapper {

    private static DataMapper INSTANCE;

    public static DataMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DataMapper();
        }

        return INSTANCE;
    }

    static Logger logger = LogManager.getLogger(CompanyServiceImpl.class);

    @Autowired
//    static ModelMapper mapper;


    private ModelMapper modelMapper;

    private DataMapper() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }

    public Company convertToEntity(CompanyDTO dto) {
        logger.log(Level.INFO, "convertToEntity - start - companyDTO: " + dto);
        logger.log(Level.INFO, "convertToEntity - before");

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        Company entity = modelMapper.map(dto, Company.class);

        logger.log(Level.INFO, "convertToEntity - after - company: " + entity);
        return entity;
    }

    public CompanyDTO convertToDto(Company entity) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        CompanyDTO dto = modelMapper.map(entity, CompanyDTO.class);
        return dto;
    }

    public Order convertToEntity(OrderDTO dto) {
        Order entity = modelMapper.map(dto, Order.class);
        return entity;
    }

    public OrderDTO convertToDto(Order entity) {
        OrderDTO dto = modelMapper.map(entity, OrderDTO.class);
        return dto;
    }

    public Service convertToEntity(ServiceDTO dto) {
        Service entity = modelMapper.map(dto, Service.class);
        return entity;
    }

    public ServiceDTO convertToDto(Service entity) {
        ServiceDTO dto = modelMapper.map(entity, ServiceDTO.class);
        return dto;
    }

    public Address convertToEntity(AddressDTO dto) {
        Address entity = modelMapper.map(dto, Address.class);
        return entity;
    }

    public AddressDTO convertToDto(Address entity) {
        AddressDTO dto = modelMapper.map(entity, AddressDTO.class);
        return dto;
    }

    public Person convertToEntity(PersonDTO dto) {
        Person entity = modelMapper.map(dto, Person.class);
        return entity;
    }

    public PersonDTO convertToDto(Person entity) {
        PersonDTO dto = modelMapper.map(entity, PersonDTO.class);
        return dto;
    }


}
