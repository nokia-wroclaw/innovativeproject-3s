package com.project.server.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.project.server.model.Scan;
import com.project.server.repository.ScanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ScanService {
    @Autowired ScanRepository repository;

	public Collection<Scan> getScan() {
        return (Collection<Scan>) repository.findAll();
	}

	public void add(Scan scan) {
        repository.save(scan);
	}

	public List<Scan> getScanByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<Scan> getScanByProject(String projectName) {
        return repository.findByProjectName(projectName);
    }

	public void delete(long id) {
        repository.deleteById(id);
	}

}
