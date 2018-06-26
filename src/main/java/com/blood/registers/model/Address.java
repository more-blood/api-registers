package com.blood.registers.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "address")
public class Address implements Serializable {

    private Long id;
    private String street;
    private String number;
    private String complement;
    private String district;
    private String city;
    private String country;
}
