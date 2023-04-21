package com.exemple.Repository;

import com.exemple.model.dto.UserDTO;
import com.exemple.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public boolean existsByLogin(String login);
    public boolean existsByUser(String user);
    Optional<UserDTO> findByLoginAndPassword(String login, String password);
    public boolean findByCpf(String cpf);
    public void deleteById(Long id);
    Optional<UserDTO> findByUser(String user);

}