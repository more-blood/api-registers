package com.blood.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {

    private Long id;
    private String identity;
    private String name;
    private String lastName;
    private String nickName;
    private String dateBirth;

    private Address address;

    private String bloodType;

    private List<Disease> diseases;
}
