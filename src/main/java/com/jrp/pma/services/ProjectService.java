package com.jrp.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrp.pma.DAO.ProjectRepository;
import com.jrp.pma.DTO.ChartData;
import com.jrp.pma.DTO.TimeChartData;
import com.jrp.pma.entities.Project;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository proRepo;
	
	public Project save(Project project) {
		return proRepo.save(project);
	}
	
	public Iterable<Project> findAll() {
		return proRepo.findAll();
	}
	
	public List<ChartData> getProjectStatus() {
		return proRepo.getprojectStage();
	}


	public void delete(Project theProject) {
		// TODO Auto-generated method stub
		proRepo.delete(theProject);
	}

	public Project findByProjectID(long id) {
		// TODO Auto-generated method stub
		return proRepo.findByProjectID(id);
	}

	public List<TimeChartData> getProjectTimeline() {
		return proRepo.getProjectTimeline();
	}
	

}


