package com.suber.controller.wrapper;

import com.fasterxml.jackson.annotation.JsonValue;
import com.suber.data.Company;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CompanyList {
    @JsonValue
    private List<Company> companies;

    public CompanyList() {
        companies = new ArrayList<>();
    }
    public CompanyList(List<Company> companies) {
        this.companies = companies;
    }
 }