package com.exemple.ControllerBO;

import com.exemple.ControllerBO.ControllerInterface.UserController;
import com.exemple.Entity.User;
import com.exemple.Exceptions.BusinessRules;
import com.exemple.ModelInterface.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class UserControllerImpl implements UserController{
    @Autowired
    private UserModel userModel;

    @Override
    public User save(User user) {
        validateLogin(user.getLogin());
        return userModel.save(user);
    }
    @Override
    public void validateLogin(String login) {
        boolean existe = userModel.existsByLogin(login);
        if(existe){
            throw new BusinessRules("Já existe um usuário cadastrado com esse email");
        }
    }
}