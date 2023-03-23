package com.exemple.controller.controllerInterface;

import com.exemple.entity.User;

public interface UserController {

    User save(User user);

    User findUserById(Long id);
}
