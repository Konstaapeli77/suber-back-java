package com.suber.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
@Builder
public class ServiceListDTO {

    @JsonValue
    private List<ServiceDTO> services;

    public ServiceListDTO() {
        services = new ArrayList<>();
    }
    public ServiceListDTO(List<ServiceDTO> services) {
        this.services = services;
    }

    public List<ServiceDTO> getServices() {
        return services;
    }

}
