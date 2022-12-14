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
public class ServiceDTO {

    private long id;
    private String name;
    private BigDecimal price;
    private String description;
    private CompanyDTO company;

}
