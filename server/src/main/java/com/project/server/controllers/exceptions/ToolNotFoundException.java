package com.project.server.controllers.exceptions;

import com.project.server.model.Tool;

public class ToolNotFoundException extends RuntimeException {

    
        public ToolNotFoundException(Tool tool) {
            super("Could not find user {" + tool.getId() + ", " + tool.gettoolname() + ", " + tool.getInfo() + "}");
        }
    
        public ToolNotFoundException(Long id) {
            super("Could not find user {id: " + id + "}");
        }
    }
