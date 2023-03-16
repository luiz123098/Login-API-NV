package ControllerBO.ControllerInterface;

import Entity.User;

import javax.transaction.Transactional;

public interface UserController {

    @Transactional
    User save(User user);

    void validateLogin(String login);
}
