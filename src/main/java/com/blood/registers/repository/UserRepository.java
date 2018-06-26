package com.blood.registers.repository;

import com.blood.registers.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository  extends MongoRepository<User, Long> {
}
