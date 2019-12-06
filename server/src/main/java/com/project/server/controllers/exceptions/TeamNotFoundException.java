package com.project.server.controllers.exceptions;

import com.project.server.model.Team;

public class TeamNotFoundException extends RuntimeException {

    
        public TeamNotFoundException(Team team) {
            super("Could not find team {" + team.getId() + ", " + team.getName()+ "}");
        }
    
        public TeamNotFoundException(Long id) {
            super("Could not find team {id: " + id + "}");
        }
    }
