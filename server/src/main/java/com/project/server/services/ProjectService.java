package com.project.server.services;

import java.util.List;
import java.util.Optional;

import com.project.server.controllers.exceptions.ProjectNotFoundException;
import com.project.server.model.Project;
import com.project.server.model.Scan;
import com.project.server.repository.ProjectRepository;
import com.project.server.repository.ScanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ProjectService {
    @Autowired ProjectRepository repository;

	public List<Project> getProject() {
        return (List<Project>) repository.findAll();
	}

	public void add(Project project) {
        repository.save(project);
	}

	public Project getProjectById(long id) {
        Optional<Project> optpro = repository.findById(id);
        return (Project) optpro.orElseThrow(() -> new ProjectNotFoundException(id));	}

	public void delete(long id) {
        repository.deleteById(id);
	}


}
