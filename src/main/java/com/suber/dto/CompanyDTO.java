package com.suber.dto;

import com.suber.data.Address;
import com.suber.data.Order;
import com.suber.data.Service;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

    private long id;
    private String name;
    private String businessId;
}
