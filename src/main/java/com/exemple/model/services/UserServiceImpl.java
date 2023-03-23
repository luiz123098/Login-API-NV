package com.exemple.model.services;

import com.exemple.model.utils.Util;
import com.exemple.model.UserRepository;
import com.exemple.model.services.ServicesInterface.UserService;
import com.exemple.model.dto.UserDTO;
import com.exemple.model.entity.User;
import com.exemple.model.exceptions.BusinessRules;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private Util util;
    private PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder encoder) {
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
            return userRepository.save(user);

        } catch (Exception e) {
            throw new BusinessRules(util.getMessage());
        }
    }


        @Override
        public User findUserById (Long id){
            if (id == null || id <= 0) {
                throw new BusinessRules("ID inválido: " + id);
            }

            Optional<User> optionalUser = userRepository.findById(id);
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