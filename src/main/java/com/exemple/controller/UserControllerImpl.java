package com.exemple.controller;

import com.exemple.exceptions.ExceptionVO;
import com.exemple.message.Message;
import com.exemple.utils.Util;
import com.exemple.model.UserModel;
import com.exemple.controller.ControllerInterface.UserController;
import com.exemple.dto.UserDTO;
import com.exemple.entity.User;
import com.exemple.exceptions.BusinessRules;
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
        try {
            util.validateLogin(user.getLogin());
            util.validatePassword(user.getPassword());
            util.validateCpf(user.getCpf());
            //faz a cryptografia da senha antes de salvar no banco
            user.setPassword(encoder.encode(user.getPassword()));
            return userModel.save(user);

        } catch (Exception e) {
            throw new BusinessRules(util.getMessage());
        }
    }


        @Override
        public User findUserById (Long id){
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