package com.project.server.services;

import java.util.List;
import java.util.Optional;

import com.project.server.controllers.exceptions.TeamNotFoundException;
import com.project.server.model.Team;
import com.project.server.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class TeamService  {

    @Autowired TeamRepository repository;

	public List<Team> getTeam() {
        return (List<Team>) repository.findAll();
	}

	public void add(Team team) {
        repository.save(team);
	}

	public Team getTeamById(long id) {
        Optional<Team> optionalusr = repository.findById(id);
        return (Team) optionalusr.orElseThrow(() -> new TeamNotFoundException(id));	}

	public void delete(long id) {
        repository.deleteById(id);
	}


}
