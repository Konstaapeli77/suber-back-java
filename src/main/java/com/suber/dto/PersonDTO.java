package com.suber.dto;

import com.suber.data.Address;
import com.suber.data.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PersonDTO {

    private long id;
    private String firstname;
    private String lastname;
    private Address address;
    private List<Order> orders;
}
