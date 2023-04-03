package com.exemple.model.services;

import com.exemple.model.utils.Util;
import com.exemple.Repository.UserRepository;
import com.exemple.model.dto.UserDTO;
import com.exemple.model.entity.User;
import com.exemple.model.exceptions.BusinessRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl  {
    @Autowired
    private Util util;
    private PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public User save(User user) {
        try {
            util.validateLogin(user.getLogin());
            util.validatePassword(user.getPassword());
            util.validateCpf(user.getCpf());
            //faz a cryptografia da senha antes de salvar no banco
            user.setPassword(encoder.encode(user.getPassword()));
            return userRepository.save(user);

        } catch (Exception e) {
            throw new BusinessRules(util.getMessage());
        }
    }


        public User findUserByLoginAndPassword (UserDTO userDTO) throws Exception {
            if (userDTO.getLogin() == null || userDTO.getPassword() == null || userDTO.getLogin().isEmpty() || userDTO.getPassword().isEmpty()) {
                throw new Exception("O campo Email ou senha deve ser preenchido inválido");
            }
            User user = new User();
            user.setLogin(userDTO.getLogin());
            user.setPassword(encoder.encode(userDTO.getPassword()));
            if(userRepository.existsByLogin(user.getLogin())) {
                Optional<User> optionalUser = userRepository.findByLoginAndPassword(user.getLogin(), user.getPassword());
                return user;
            }
             return new User();

            }
        }

