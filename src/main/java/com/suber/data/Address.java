package com.suber.data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.*;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "address")
@RepositoryRestResource(exported = false)
public class Address {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "address")
    private String address;
    @Column(name = "postalCode")
    private String postalCode;
    @Column(name = "city")
    private String city;


}
