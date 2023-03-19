package com.exemple.ControllerBO;

import com.exemple.ModelInterface.UserModel;
import com.exemple.Utils.UtilsInterface.Util;
import com.exemple.ControllerBO.ControllerInterface.UserController;
import com.exemple.Entity.DTO.UserDTO;
import com.exemple.Entity.User;
import com.exemple.Exceptions.BusinessRules;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserControllerImpl implements UserController {
    @Autowired
    private Util util;
    private PasswordEncoder encoder;
    @Autowired
    private UserModel userModel;
    public UserControllerImpl(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public User save(User user) {
        if(util.validateLogin(user.getLogin()) && util.validatePassword(user.getPassword()) && util.validateCpf(user.getCpf())) {
            validateLogin(user.getLogin());

            //faz a cryptografia da senha antes de salvar no banco
            user.setPassword(encoder.encode(user.getPassword()));
            return userModel.save(user);
        } else {
            throw new BusinessRules("Ocorreu um erro na validação do usuário");
        }
    }
    @Override
    public void validateLogin(String login) {
        boolean exist = userModel.existsByLogin(login);
        if(exist){
            throw new BusinessRules("Já existe um usuário cadastrado com esse login");
        }
    }

    @Override
    public User findUserById(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessRules("ID inválido: " + id);
        }

        Optional<User> optionalUser = userModel.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userDTO, user);
            return user;
        } else {
            return null;
        }
    }
}