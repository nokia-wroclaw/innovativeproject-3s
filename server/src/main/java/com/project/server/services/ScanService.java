package com.project.server.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.project.server.controllers.exceptions.ScanNotFoundException;
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

	public Scan getScanById(long id) {
        Optional<Scan> optionalscan = repository.findById(id);
        return (Scan) optionalscan.orElseThrow(() -> new ScanNotFoundException(id));	}

	public void delete(long id) {
        repository.deleteById(id);
	}

}
