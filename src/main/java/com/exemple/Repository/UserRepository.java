package com.exemple.Repository;

import com.exemple.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public boolean existsByLogin(String login);

    @Override
    Optional<User> findById(Long id);
}