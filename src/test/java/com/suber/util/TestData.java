package com.suber.util;

import com.suber.data.Address;
import com.suber.data.Company;
import com.suber.data.Order;
import com.suber.data.Service;

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

    public static List<Service> getService() {

        Service service = Service.builder()
                .description("Lumityöt lapiolla.")
                .price(new BigDecimal(11.0))
                .workers(null)
                .build();
        return List.of(service);
    }

    public static List<Order> getOrder() {
        Order order = Order.builder()
                .address(getAddress())
                .reference("23211")
                .customer(null)
                .provider(null)
                .price(new BigDecimal(21))
                .services(getService())
                .build();

        return List.of(order);
    }

    public static Company getCompany() {
        return null;
        /*
        return Company.builder()
                .orders(getOrder())
                .services(getService())
                .name("Valtor Oy")
                .businessId("12314-1")
                .address(getAddress())
                .build();

         */
    }
}
