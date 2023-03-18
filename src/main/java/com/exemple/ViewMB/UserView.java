package com.exemple.ViewMB;

import com.exemple.Exceptions.BusinessRules;
import com.exemple.ControllerBO.ControllerInterface.UserController;
import com.exemple.Entity.DTO.UserDTO;
import com.exemple.Entity.User;
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

        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setCpf(userDTO.getCpf());
        try {
            User userSave = userController.save(user);
            return new ResponseEntity(userSave, HttpStatus.CREATED);
        } catch (BusinessRules e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        User user = userController.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
