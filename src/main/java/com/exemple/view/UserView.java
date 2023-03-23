package com.exemple.view;

import com.exemple.exceptions.BusinessRules;
import com.exemple.controller.ControllerInterface.UserController;
import com.exemple.dto.UserDTO;
import com.exemple.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserView {
    @Autowired
    private UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    @PostMapping(path = "/register")
    public ResponseEntity save(@RequestBody UserDTO userDTO) {
        try {
            User user = new User();
            user.setLogin(userDTO.getLogin());
            user.setPassword(userDTO.getPassword());
            user.setName(userDTO.getName());
            user.setCpf(userDTO.getCpf());

            User userSave = userController.save(user);
            return new ResponseEntity(userSave, HttpStatus.CREATED);
        } catch (BusinessRules e) {
            return new ResponseEntity(new BusinessRules("Erro ao realizar o cadastro"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/consult/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        User user = userController.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
