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
            user.setUser(userDTO.getUser());
            user.setCpf(userDTO.getCpf());

            User userSave = userService.save(user);
            return new ResponseEntity(userSave, HttpStatus.CREATED);
        } catch (BusinessRules e) {
            return new ResponseEntity(new BusinessRules(util.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/login")
    public ResponseEntity<String> findUserByLoginAndPassword(@RequestBody UserDTO userDTO) {
        try{
            User user = userService.findUserByLoginAndPassword(userDTO);
            if (user != null) {
                return new ResponseEntity(user, HttpStatus.ACCEPTED);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            throw new BusinessRules(util.getMessage());
        }
    }

    @DeleteMapping(path = "/delete-user")
    public ResponseEntity deleteUserByCpf(@RequestBody UserDTO userDTO) {
        try {
            User user = new User();
            User userClone = userService.findUserByLoginAndPassword(userDTO);
            user.setPassword(userClone.getPassword());
            user.setId(userClone.getId());
            user.setCpf(userClone.getCpf());
            user.setName(userClone.getName());

            if (userService.deleteById(user) == true){
                return new ResponseEntity(new BusinessRules(util.getMessage()), HttpStatus.ACCEPTED);
            }else
                return new ResponseEntity(new BusinessRules(util.getMessage()), HttpStatus.BAD_REQUEST);

        }catch (Exception e){
            throw new BusinessRules(util.getMessage());
        }
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