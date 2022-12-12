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
public class DataMapper {

    static Logger logger = LogManager.getLogger(CompanyServiceImpl.class);

    @Autowired
    static ModelMapper mapper;

    @Autowired
    static ModelMapper mapperClass;

    public DataMapper() {

    }

    public static Company convertToEntity(CompanyDTO dto) {
        logger.log(Level.INFO, "convertToEntity - start - companyDTO: " + dto);
        logger.log(Level.INFO, "convertToEntity - before");

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        Company entity = modelMapper.map(dto, Company.class);

        logger.log(Level.INFO, "convertToEntity - after - company: " + entity);
        return entity;
    }

    public static CompanyDTO convertToDto(Company entity) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        CompanyDTO dto = modelMapper.map(entity, CompanyDTO.class);
        return dto;
    }

    public static Order convertToEntity(OrderDTO dto) {
        Order entity = mapper.map(dto, Order.class);
        return entity;
    }

    public static OrderDTO convertToDto(Order entity) {
        OrderDTO dto = mapper.map(entity, OrderDTO.class);
        return dto;
    }

    public static Service convertToEntity(ServiceDTO dto) {
        Service entity = mapper.map(dto, Service.class);
        return entity;
    }

    public static ServiceDTO convertToDto(Service entity) {
        ServiceDTO dto = mapper.map(entity, ServiceDTO.class);
        return dto;
    }

    public static Address convertToEntity(AddressDTO dto) {
        Address entity = mapper.map(dto, Address.class);
        return entity;
    }

    public static AddressDTO convertToDto(Address entity) {
        AddressDTO dto = mapper.map(entity, AddressDTO.class);
        return dto;
    }

    public static Person convertToEntity(PersonDTO dto) {
        Person entity = mapper.map(dto, Person.class);
        return entity;
    }

    public static PersonDTO convertToDto(Person entity) {
        PersonDTO dto = mapper.map(entity, PersonDTO.class);
        return dto;
    }


}
