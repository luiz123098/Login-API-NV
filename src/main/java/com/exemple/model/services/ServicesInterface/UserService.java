package com.exemple.model.services.ServicesInterface;

import com.exemple.model.entity.User;

public interface UserService {

    User save(User user);

    User findUserById(Long id);
}
