package com.project.server.repository;

import java.util.List;

import com.project.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByLogin(String login);
    User findById(long id);
}