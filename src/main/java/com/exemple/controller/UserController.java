package com.exemple.controller;

import com.exemple.model.dto.UserDTO;
import com.exemple.model.entity.User;
import com.exemple.model.exceptions.BusinessRules;
import com.exemple.model.services.UserService;
import com.exemple.model.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private Util util;


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
            return new ResponseEntity(new BusinessRules(util.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/login")
    public ResponseEntity<String> findUserByLoginAndPassword(@Valid String login, String password) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(login);
        userDTO.setPassword(password);

        User user = userService.findUserByLoginAndPassword(userDTO);
        if (user != null) {
            return new ResponseEntity(user, HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/delete-user")
    public ResponseEntity deleteUserByCpf(@RequestBody UserDTO userDTO) {
        User user = new User();
        User userClone = userService.findByCPF(userDTO);
        user.setPassword(userClone.getPassword());
        user.setCpf(userClone.getCpf());
        user.setName(userClone.getName());

        userService.deleteById(user);

        return new ResponseEntity(new BusinessRules(util.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/findUser")
    public ResponseEntity searchUser(@RequestBody String user) {
        UserDTO userResponse = new UserDTO();
        try {
            userResponse.setUser(user);
            userService.findByUser(userResponse);
            return new ResponseEntity(userResponse, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new BusinessRules(util.getMessage());
        }
    }
}


