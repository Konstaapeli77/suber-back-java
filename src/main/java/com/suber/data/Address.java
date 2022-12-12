package com.suber.data;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "address")
    private String address;
    @Column(name = "postalCode")
    private String postalCode;
    @Column(name = "city")
    private String city;


}
