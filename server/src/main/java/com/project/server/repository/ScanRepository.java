package com.project.server.repository;

import com.project.server.model.Project;
import com.project.server.model.Scan;
import com.project.server.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScanRepository extends JpaRepository<Scan, Long> {
    Optional<Scan> findById(Long id);
}
