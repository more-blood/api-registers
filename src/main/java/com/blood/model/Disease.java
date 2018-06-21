package com.blood.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Disease implements Serializable {

    private Long id;
    private String name;
    private String remarks;

}
