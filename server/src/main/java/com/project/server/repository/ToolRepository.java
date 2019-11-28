package com.project.server.repository;

import java.util.List;

import com.project.server.model.User;
import com.project.server.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {

    List<Tool> findByName (String name);
    Tool findById(long id);
}