package com.exemple.controller;

import com.exemple.model.services.ServicesInterface.UserService;
import com.exemple.model.exceptions.BusinessRules;
import com.exemple.model.dto.UserDTO;
import com.exemple.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity save(@RequestBody UserDTO userDTO) {
        try {
            User user = new User();
            user.setLogin(userDTO.getLogin());
            user.setPassword(userDTO.getPassword());
            user.setName(userDTO.getName());
            user.setCpf(userDTO.getCpf());

            User userSave = userService.save(user);
            return new ResponseEntity(userSave, HttpStatus.CREATED);
        } catch (BusinessRules e) {
            return new ResponseEntity(new BusinessRules("Erro ao realizar o cadastro"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/consult/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}