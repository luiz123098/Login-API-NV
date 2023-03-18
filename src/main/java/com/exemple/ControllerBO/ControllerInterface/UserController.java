package com.exemple.ControllerBO.ControllerInterface;

import com.exemple.Entity.User;

public interface UserController {

    User save(User user);

    void validateLogin(String login);

    User findUserById(Long id);
}
