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
        try {
            logger.info("[ Service ] ");
            User user = userRepository.save(newUser);

            return user;
        } catch (Exception ex) {
            logger.error("[ Service ] " + ex.getMessage());
            throw ex;
        }
    }

    public Optional<User> finOne(Long id) {
        try {
            logger.info("[ Service ] ");
            Optional<User> user = userRepository.findById(id);

            return user;
        } catch (Exception ex) {
            logger.error("[ Service ] " + ex.getMessage());
            throw ex;
        }
    }

    public List<User> findAll() {
        try {
            logger.info("[ Service ] ");
            List<User> users = userRepository.findAll();

            return users;
        } catch (Exception ex) {
            logger.error("[ Service ] " + ex.getMessage());
            throw ex;
        }
    }

    public User update(Long id, User modifyUser){
        try {
            logger.info("[ Service ] ");
            User user = userRepository.save(modifyUser);

            return user;
        } catch (Exception ex) {
            logger.error("[ Service ] " + ex.getMessage());
            throw ex;
        }
    }

    public void remove(Long id) {
        try {
            logger.info("[ Service ] ");
            userRepository.deleteById(id);

        } catch (Exception ex) {
            logger.error("[ Service ] " + ex.getMessage());
            throw ex;
        }
    }
}
