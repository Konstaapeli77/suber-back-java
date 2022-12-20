package com.suber.util;

import com.suber.data.*;
import com.suber.dto.*;

import java.math.BigDecimal;
import java.util.List;

public class TestData {

    public static Address getAddress() {
        return Address.builder()
                .city("Helsinki")
                .address("Lummetie 1 A")
                .postalCode("10100")
                .build();
    }

    public static AddressDTO getAddressDTO() {
        return AddressDTO.builder()
                .city("Helsinki")
                .address("Lummetie 1 A")
                .postalCode("10100")
                .build();
    }

    public static List<Service> getServiceList() {

        Service service = Service.builder()
                .description("Lumityöt lapiolla.")
                .price(new BigDecimal(11.0))
                .workers(null)
                .build();
        return List.of(service);
    }

    public static Service getService() {

        Service service = Service.builder()
                .name("Lumitöitä")
                .description("Lumityöt lapiolla.")
                .price(new BigDecimal(11.0))
                .workers(null)
                .build();
        return service;
    }

    public static List<Order> getOrderList() {
        Order order = Order.builder()
                .address(getAddress())
                .reference("23211")
                .customer(null)
                .provider(null)
                .price(new BigDecimal(21))
                .services(getServiceList())
                .build();

        return List.of(order);
    }

    public static Order getOrder() {
        Order order = Order.builder()
                .address(getAddress())
                .reference("23211")
                .customer(null)
                .provider(null)
                .price(new BigDecimal(21))
                .services(getServiceList())
                .build();

        return order;
    }

    public static Company getCompany() {

        return Company.builder()
                .orders(List.of(getOrder()))
                .services(getServiceList())
                .name("Valtor Oy")
                .businessId("12314-1")
                .address(getAddress())
                .build();

    }

    public static CompanyDTO getCompanyDTO() {

        return CompanyDTO.builder()
                .name("Valtor Oy")
                .businessId("12314-1")
                .address(getAddressDTO())
                .build();

    }

    public static OrderDTO getOrderDTO() {

        return OrderDTO.builder()
                .reference("3423")
                .price(new BigDecimal(12))
                .address(getAddressDTO())
                .build();

    }

    public static ServiceDTO getServiceDTO() {
        return ServiceDTO.builder()
                .price(new BigDecimal(123))
                .name("Lumitöitä")
                .description("Short description. Lorem Ipsum. Lorem jne.")
                .build();
    }

    public static PersonDTO getPersonDTO() {
        return PersonDTO.builder()
                .firstname("Mikko")
                .lastname("Mallikas")
                .address(getAddressDTO())
                .build();
    }

    public static Person getPerson() {
        return Person.builder()
                .firstname("Mikko")
                .lastname("Mallikas")
                .address(getAddress())
                .build();
    }

}
