package com.suber.util.mapper;

import com.suber.data.*;
import com.suber.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public final class DataMapper {

    private static DataMapper INSTANCE;

    public static DataMapper getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DataMapper();
        }

        return INSTANCE;
    }

    private ModelMapper modelMapper;

    private DataMapper() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }

    public Company convertToEntity(CompanyDTO dto) {
        Company company = modelMapper.map(dto, Company.class);
        return company;
    }

    public CompanyDTO convertToDto(Company entity) {
        return modelMapper.map(entity, CompanyDTO.class);
    }

    public Order convertToEntity(OrderDTO dto) {
        return modelMapper.map(dto, Order.class);
    }

    public OrderDTO convertToDto(Order entity) {
        return modelMapper.map(entity, OrderDTO.class);
    }

    public Service convertToEntity(ServiceDTO dto) {
        return modelMapper.map(dto, Service.class);
    }

    public ServiceDTO convertToDto(Service entity) {
        return modelMapper.map(entity, ServiceDTO.class);
    }

    public Address convertToEntity(AddressDTO dto) {
        return modelMapper.map(dto, Address.class);
    }

    public AddressDTO convertToDto(Address entity) {
        return modelMapper.map(entity, AddressDTO.class);
    }

    public Person convertToEntity(PersonDTO dto) {
        return modelMapper.map(dto, Person.class);
    }

    public PersonDTO convertToDto(Person entity) {
        return modelMapper.map(entity, PersonDTO.class);
    }


}
