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
		User user = userService.getUserByEmail(email);
		List<Project> result = userService.getProjects(user.getEmail());
		return result;
	}

	public String addProject(String name) {
		if (repository.findByName(name).isPresent()) {
			throw new ProjectAlreadyExistsException(name);
		} else {
			repository.save(new Project(name));
			return "Project " + name + " created.";
		}
	}

	public Project getProjectById(long id) {
        Optional<Project> optpro = repository.findById(id);
        return (Project) optpro.orElseThrow(() -> new ProjectNotFoundException(id));	}

	public void delete(long id) {
        repository.deleteById(id);
	}

	public String addUserToProject(String email, String projectName) {
		User user = userService.getUserByEmail(email);
		Project project = getProjectByName(projectName);

		project.getUsers().add(user);
		user.getProjects().add(project);

		return "User " + email + " added to " + projectName;
	}

	public String addToolToProject(String toolName, String projectName) {
		Tool tool = toolService.getToolByName(toolName);
		Project project = getProjectByName(projectName);

		project.getTools().add(tool);
		tool.getProject().add(project);

		return "Tool " + tool.getName() + " added to " + project.getName();
	}

	public String removeUserFromProject(String email, String projectName) {
		User user = userService.getUserByEmail(email);
		Project project = getProjectByName(projectName);

		project.getUsers().remove(user);
		user.getProjects().remove(project);

		return "User " + email + " removed from " + projectName;
	}

	public String removeToolFromProject(String toolName, String projectName) {
		Tool tool = toolService.getToolByName(toolName);
		Project project = getProjectByName(projectName);

		project.getTools().remove(tool);
		tool.getProject().remove(project);

		return "Tool " + toolName + " removed from " + projectName;
	}

}
