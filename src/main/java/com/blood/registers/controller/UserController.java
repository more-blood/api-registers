package com.blood.registers.controller;

import com.blood.registers.utils.CustomException;
import com.blood.registers.model.User;
import com.blood.registers.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    static final Logger logger = Logger.getLogger(UserController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody User newUser) {
        try {

            logger.info("[ Controller ]Creating user: " + newUser.getName());
            User user = userService.create(newUser);

            logger.info("[ Controller ]User created with success.");
            return new ResponseEntity<>(user, HttpStatus.CREATED);

        } catch (Exception ex) {
            logger.error("[ Controller ] " + ex.getMessage());
            return new ResponseEntity<>(new CustomException(ex.getMessage(), ex, HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable final Long id) {
        try {

            logger.info("[ Controller ] Finding user: " + id);
            Optional<User> user = userService.finOne(id);

            if (!user.isPresent()) {
                logger.warn("[ Controller ] User: " + id + " not found");
                return new ResponseEntity<>(new CustomException("User: " + id + " not found", HttpStatus.NOT_FOUND),
                        HttpStatus.NOT_FOUND);
            }

            logger.info("[ Controller ] User found with success.");
            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error("[ Controller ] " + ex.getMessage());
            return new ResponseEntity<>(new CustomException(ex.getMessage(), ex, HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/users")
    public ResponseEntity<?> findAll() {
        try {

            logger.info("[ Controller ] Finding all users.");
            List<User> users = userService.findAll();

            logger.info("[ Controller ] " + users.size() + " users were found with success");
            return new ResponseEntity<>(users, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error("[ Controller ] " + ex.getMessage());
            return new ResponseEntity<>(new CustomException(ex.getMessage(), ex, HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User modifyUser) {
        try {

            logger.info("[ Controller ] Updating user.");
            User user = userService.update(id, modifyUser);

            logger.info("[ Controller ] User updated with success.");
            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error("[ Controller ] " + ex.getMessage());
            return new ResponseEntity<>(new CustomException(ex.getMessage(), ex, HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable final Long id) {
        try {

            logger.info("[ Controller ] Removing user: " + id);
            userService.remove(id);

            logger.info("[ Controller ] User removed with success");
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception ex) {
            logger.error("[ Controller ] " + ex.getMessage());
            return new ResponseEntity<>(new CustomException(ex.getMessage(), ex, HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
