package com.suber.dto;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class
CompanyListDTO {

    //@JsonValue
    private List<CompanyDTO> companies;

    public CompanyListDTO() {
        companies = new ArrayList<>();
    }
    public CompanyListDTO(List<CompanyDTO> companies) {
        this.companies = companies;
    }


}
