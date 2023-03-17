package com.exemple.ModelInterface;

import com.exemple.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserModel extends JpaRepository<User,Long> {

    boolean existsByLogin(String login);

    @Override
    Optional<User> findById(Long id);
}