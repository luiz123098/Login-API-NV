package com.exemple.model.services;

import com.exemple.model.utils.Util;
import com.exemple.Repository.UserRepository;
import com.exemple.model.dto.UserDTO;
import com.exemple.model.entity.User;
import com.exemple.model.exceptions.BusinessRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private Util util;
    private PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;

    public UserService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public User save(User user) {
        try {
            if(util.validateLogin(user.getLogin()) ||
               util.validatePassword(user.getPassword()) ||
               util.validateCpf(user.getCpf())){
                //faz a cryptografia da senha antes de salvar no banco
                user.setPassword(encoder.encode(user.getPassword()));
                return userRepository.save(user);
            }
            else {
                throw new BusinessRules(util.getMessage());
            }
        } catch (Exception e) {
            throw new BusinessRules(util.getMessage());
        }
    }

    public User findUserByLoginAndPassword(UserDTO userDTO) throws Exception {
        try {
            if (userDTO.getLogin() == null || userDTO.getPassword() == null || userDTO.getLogin().isEmpty() || userDTO.getPassword().isEmpty()) {
                throw new Exception("O campo Email ou senha deve ser preenchido inválido");
            }

            User userLogin = userRepository.findByLogin(userDTO.getLogin());

            if (userLogin != null && encoder.matches(userDTO.getPassword(), userLogin.getPassword())) {
                // Login e senha correspondem
                return userLogin;
            }

            return null;
        } catch (Exception e) {
            throw new BusinessRules(util.getMessage());
        }
    }

    public User findByCPF(UserDTO userDTO){
        User userReturn = new User();
        if(userRepository.findByCpf(userDTO.getCpf())){
            userReturn.setId(userDTO.getId());
            userReturn.setName(userDTO.getName());
            userReturn.setPassword(userDTO.getPassword());
            userReturn.setLogin(userDTO.getLogin());
            return userReturn;
        }else{
            throw new BusinessRules(util.getMessage());}
        }
        public void deleteById(User user){
        userRepository.deleteById(user.getId());
        }

        public UserDTO findByUser(UserDTO userDTO){

            userDTO.getUser();
            try {
                User user = new User();
                user.setLogin(userDTO.getLogin());
                user.setPassword(userDTO.getPassword());
                if(util.validateUser(user)){
                    Optional<UserDTO> userDTOReturn = userRepository.findByUser(userDTO.getUser());
                    UserDTO userResponse = userDTOReturn.get();
                    return userResponse;
                } else {
                    throw new BusinessRules(util.getMessage());
                }
            }catch (Exception e) {
                throw new BusinessRules(util.getMessage());
            }
        }




}
