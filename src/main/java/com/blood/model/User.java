package com.blood.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {

    private Long id;
    private String name;
    private String lastName;
    private String nickName;
    private String identification;

    private String bloodType;

    private List<Disease> diseases;

    private Address address;
}
