package com.exemple.ViewMB;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.exemple.ControllerBO.ControllerInterface.UserController;
import com.exemple.Entity.DTO.UserDTO;
import com.exemple.Entity.User;
import com.exemple.Exceptions.BusinessRules;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path ="/api/users")
public class UserView {
    private UserController userController;
    public UserView(UserController userController) {
        this.userController = userController;
    }
    @PostMapping(path = "/post")
    public ResponseEntity save(@RequestBody UserDTO dto) {

        User user = new User();
        BeanUtils.copyProperties(dto, user);
        try {
            User userSave = userController.save(user);
            return new ResponseEntity(userSave, HttpStatus.CREATED);
        } catch (BusinessRules e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
