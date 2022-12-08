package com.suber.data;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Company() {
    }

    public Company(String name, String businessId) {
        this.name = name;
        this.businessId = businessId;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "business_id")
    private String businessId;

}
