package com.exemple.ControllerBO.ControllerInterface;

import com.exemple.Entity.User;

import javax.transaction.Transactional;

public interface UserController {

    User save(User user);

    void validateLogin(String login);
}
