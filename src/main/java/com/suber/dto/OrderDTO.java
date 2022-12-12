package com.suber.dto;

import com.suber.data.Address;
import com.suber.data.Company;
import com.suber.data.Person;
import com.suber.data.Service;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private long id;
    private String reference;
    private BigDecimal price;
    private AddressDTO address;

}