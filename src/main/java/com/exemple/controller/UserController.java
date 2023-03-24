package com.exemple.controller;

import com.exemple.model.services.ServicesInterface.UserService;
import com.exemple.model.dto.UserDTO;
import com.exemple.model.entity.User;
import com.exemple.model.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private Util util;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<? extends Object> save(@RequestBody UserDTO userDTO, HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = new User();
            user.setLogin(userDTO.getLogin());
            user.setPassword(userDTO.getPassword());
            user.setName(userDTO.getName());
            user.setCpf(userDTO.getCpf());

            User userSave = userService.save(user);
            // redireciona para a página de sucesso
            response.sendRedirect(request.getContextPath() + "/success.html");

            return new ResponseEntity<Object>(userSave, HttpStatus.CREATED);
        } catch (Exception e) {
            String message = util.getMessage();
            return ResponseEntity.badRequest().body(message);
        }
    }


    @GetMapping("/success.html")
    public ResponseEntity<String> showSuccessPage(ResourceLoader resourceLoader) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:/static/success.html");
        String content = new String(Files.readAllBytes(resource.getFile().toPath()));
        return ResponseEntity.ok(content);
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
