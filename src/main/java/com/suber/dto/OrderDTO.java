package com.suber.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

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