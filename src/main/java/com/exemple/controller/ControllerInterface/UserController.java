package com.exemple.controller.ControllerInterface;

import com.exemple.Entity.User;

public interface UserController {

    User save(User user);

    User findUserById(Long id);
}
