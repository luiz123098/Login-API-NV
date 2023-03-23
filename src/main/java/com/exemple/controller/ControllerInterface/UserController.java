package com.exemple.controller.ControllerInterface;

import com.exemple.entity.User;

public interface UserController {

    User save(User user);

    User findUserById(Long id);
}
