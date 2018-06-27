package com.blood.registers.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "user")
public class User implements Serializable {

    @Id
    private Long id;
    private String identity;
    private String name;
    private String lastName;
    private String nickName;
    private String dateBirth;
    private String picture;

    private float kg;

    private String email;

    private String phoneNumber;

    private Address address;

    private String bloodType;

    private List<Disease> diseases;

    private Boolean enabledDonate;
}
