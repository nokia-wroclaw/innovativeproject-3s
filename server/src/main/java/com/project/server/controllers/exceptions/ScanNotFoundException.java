package com.project.server.controllers.exceptions;

import com.project.server.model.Scan;

public class ScanNotFoundException extends RuntimeException {

    
        public ScanNotFoundException(Scan scan) {
            super("Could not find scan {" + scan.getId() + ", " + scan.getTool_id()+ "}");
        }
    
        public ScanNotFoundException(Long id) {
            super("Could not find Scan {id: " + id + "}");
        }
    }
