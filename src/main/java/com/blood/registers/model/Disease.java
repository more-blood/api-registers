package com.blood.registers.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "disease")
public class Disease implements Serializable {

    @Id
    private Long id;
    private String name;
    private String remarks;
}
