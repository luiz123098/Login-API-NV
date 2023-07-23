package com.exemple.Repository;

import com.exemple.model.dto.UserDTO;
import com.exemple.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByLogin(String login);
    public User findByPassword(String password);

    public boolean existsByUser(User user);
    public boolean findByCpf(String cpf);
    public void deleteById(Long id);
    Optional<UserDTO> findByUser(String user);

}