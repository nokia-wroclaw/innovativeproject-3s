package com.project.server.services;

import java.util.List;
import java.util.Optional;

import com.project.server.model.Tool;
import com.project.server.model.User;
import com.project.server.services.exceptions.ProjectAlreadyExistsException;
import com.project.server.services.exceptions.ProjectNotFoundException;
import com.project.server.model.Project;
import com.project.server.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ProjectService {
    @Autowired
	ProjectRepository repository;
    @Autowired
	UserService userService;
	@Autowired
    ToolService toolService;

	public List<Project> getProject() {
        return (List<Project>) repository.findAll();
	}

	public Project getProjectByName(String name) {
		Optional<Project> project = repository.findByName(name);
		return (Project) project.orElseThrow(() -> new ProjectNotFoundException(name));
	}

	public List<Project> getProjectByUserEmail(String email) {
		System.out.println("TESTEST test 0 [" + email + "]");
		User user = userService.getUserByEmail(email);
		System.out.println("TESTEST test 1");
		List<Project> result = userService.getProjects(user.getEmail());
		System.out.println("TESTEST test 2");
		return result;
	}

	public Project addProject(Project project) {
		if (repository.findByName(project.getName()).isPresent()) {
			throw new ProjectAlreadyExistsException(project);
		} else {
			return repository.save(project);
		}
	}

	public Project getProjectById(long id) {
        Optional<Project> optpro = repository.findById(id);
        return (Project) optpro.orElseThrow(() -> new ProjectNotFoundException(id));	}

	public void delete(long id) {
        repository.deleteById(id);
	}

	public String addUserToProject(User userToAdd, Project projectToAdd) {
		User user = userService.getUserByEmail(userToAdd.getEmail());
		Project project = getProjectByName(projectToAdd.getName());

		project.getUsers().add(user);
		user.getProjects().add(project);

		return "User " + userToAdd.getEmail() + " added to " + project.getName();
	}

	public String addToolToProject(Tool toolToAdd, Project projectToAdd) {
		Tool tool = toolService.getToolByName(toolToAdd.getName());
		Project project = getProjectByName(projectToAdd.getName());

		project.getTools().add(tool);
		tool.getProject().add(project);

		return "Tool " + tool.getName() + " added to " + project.getName();
	}

}
