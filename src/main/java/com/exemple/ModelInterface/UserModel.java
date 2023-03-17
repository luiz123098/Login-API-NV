package com.exemple.ModelInterface;

import com.exemple.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserModel extends JpaRepository<User,Long> {

    boolean existsByLogin(String login);

}