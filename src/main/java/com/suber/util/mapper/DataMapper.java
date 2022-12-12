package com.suber.util.mapper;

import com.suber.data.Company;
import com.suber.data.Order;
import com.suber.data.Person;
import com.suber.data.Service;
import com.suber.dto.CompanyDTO;
import com.suber.dto.OrderDTO;
import com.suber.dto.PersonDTO;
import com.suber.dto.ServiceDTO;

public class DataMapper {

    public static Company companyDTOtoCompany(CompanyDTO companyDTO) {
        return Company.builder()
                .id(companyDTO.getId())
                .name(companyDTO.getName())
                .address(companyDTO.getAddress())
                .orders(companyDTO.getOrders())
                .services(companyDTO.getServices())
                .orders(companyDTO.getOrders())
                .businessId(companyDTO.getBusinessId())
                .build();
    }

    public static CompanyDTO companyToDTO(Company company) {
        return CompanyDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .address(company.getAddress())
                .orders(company.getOrders())
                .services(company.getServices())
                .businessId(company.getBusinessId())
                .build();
    }


    public static OrderDTO orderToDTO(Order order) {
        return OrderDTO.builder()
                .customer(order.getCustomer())
                .price(order.getPrice())
                .provider(order.getProvider())
                .reference(order.getReference())
                .id(order.getId())
                .services(order.getServices())
                .address(order.getAddress())
                .build();
    }

    public static Order orderDTOToOrder(OrderDTO order) {
        return Order.builder()
                .customer(order.getCustomer())
                .price(order.getPrice())
                .provider(order.getProvider())
                .reference(order.getReference())
                .id(order.getId())
                .services(order.getServices())
                .address(order.getAddress())
                .build();
    }

    public static PersonDTO personToDTO(Person person) {
        return PersonDTO.builder()
                .address(person.getAddress())
                .firstname(person.getFirstname())
                .lastname(person.getLastname())
                .id(person.getId())
                .orders(person.getOrders())
                .build();
    }

    public static Person personDTOToPerson(PersonDTO person) {
        return Person.builder()
                .address(person.getAddress())
                .firstname(person.getFirstname())
                .lastname(person.getLastname())
                .id(person.getId())
                .orders(person.getOrders())
                .build();
    }

    public static ServiceDTO serviceToDTO(Service service) {
        return ServiceDTO.builder()
                .description(service.getDescription())
                .workers(service.getWorkers())
                .price(service.getPrice())
                .name(service.getName())
                .build();
    }

    public static Service serviceDTOToService(ServiceDTO service) {
        return Service.builder()
                .description(service.getDescription())
                .workers(service.getWorkers())
                .price(service.getPrice())
                .name(service.getName())
                .build();
    }
}
