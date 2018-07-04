package com.blood.registers.repository;

import com.blood.registers.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {

    Optional<User> findByIdentity(String identity);

    List<User> findByBloodType(String bloodType);

}
