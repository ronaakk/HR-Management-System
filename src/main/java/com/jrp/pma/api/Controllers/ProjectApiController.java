package com.jrp.pma.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrp.pma.DAO.ProjectRepository;
import com.jrp.pma.entities.Project;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {
	
	@Autowired
	ProjectRepository proRepo;
	
	// will retrieve all projects 
	@GetMapping
	public Iterable<Project> getProjects() {
		return proRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Project getProjectByID(@PathVariable("id") Long id) {
		return proRepo.findById(id).get();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Project create(@RequestBody Project aProject) {
		return proRepo.save(aProject);
	}
	
	// will update the entire entry and cascade delete anything it was a part of 
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public Project update(@RequestBody Project aProject) {
		return proRepo.save(aProject);
	}
	
	// programmatically changing a project's details without cascade deleting everything
	@PatchMapping(path = "/{id}")
	public Project partialUpdate(@PathVariable("id") Long id, @RequestBody Project aProject) {
		// Getting the current project occupying the given id
		Project project = proRepo.findById(id).get();
		
		if (aProject.getStage() != null) {
			project.setStage(aProject.getStage());
		}
		
		if (aProject.getDescription() != null) {
			project.setStage(aProject.getDescription());
		}
		
		if (aProject.getName() != null) {
			project.setStage(aProject.getName());
		}
		
		// saves the new project details
		return proRepo.save(project);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProject(@PathVariable("id") Long id) {
		try {
			proRepo.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			
		}
	}
	

}
