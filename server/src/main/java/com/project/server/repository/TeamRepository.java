package com.project.server.repository;

import java.util.List;
import java.util.Optional;

import com.project.server.model.Permission;
import com.project.server.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findByName(String type);
    Optional<Team> findById(Long id);
}