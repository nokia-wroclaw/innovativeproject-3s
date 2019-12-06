package com.project.server.controllers.exceptions;

import com.project.server.model.Project;

public class ProjectNotFoundException extends RuntimeException {

    
        public ProjectNotFoundException(Project project) {
            super("Could not find Project {" + project.getId() + ", " + project.getName()+ "}");
        }
    
        public ProjectNotFoundException(Long id) {
            super("Could not find Project {id: " + id + "}");
        }
    }
