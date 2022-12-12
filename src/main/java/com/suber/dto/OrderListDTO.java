package com.suber.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class OrderListDTO {
    @JsonValue
    private List<OrderDTO> orders;

    public OrderListDTO() {
        orders = new ArrayList<>();
    }
    public OrderListDTO(List<OrderDTO> persons) {
        this.orders = orders;
    }
}
