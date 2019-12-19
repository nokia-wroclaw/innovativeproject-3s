package com.project.server.repository;

import com.project.server.model.Scan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScanRepository extends JpaRepository<Scan, Long> {
    Optional<Scan> findById(Long id);
}
