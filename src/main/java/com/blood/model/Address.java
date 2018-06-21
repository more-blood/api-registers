package com.blood.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Address implements Serializable {

    private Long id;
    private String street;
    private String number;
    private String complement;
    private String district;
    private String city;
    private String country;
}
