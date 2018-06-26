package com.blood.registers.service;

import com.blood.registers.model.User;
import com.blood.registers.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    static final Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User create(User newUser) {
        logger.info("[ Service ] ");
        User user = userRepository.save(newUser);
        logger.info("[ Service ]");
        return user;
    }

    public Optional<User> finOne(Long id) {
        logger.info("[ Service ] ");
        Optional<User> user = userRepository.findById(id);
        logger.info("[ Service ]");
        return user;
    }

    public List<User> findAll() {
        logger.info("[ Service ] ");
        List<User> users = userRepository.findAll();
        logger.info("[ Service ]");
        return users;
    }

    public User update(Long id, User modifyUser){
        logger.info("[ Service ] ");
        User user = userRepository.save(modifyUser);
        logger.info("[ Service ]");
        return user;
    }

    public void remove(Long id) {
        logger.info("[ Service ] ");
        userRepository.deleteById(id);
        logger.info("[ Service ] ");
    }
}
