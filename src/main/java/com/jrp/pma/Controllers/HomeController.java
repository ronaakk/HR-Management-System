package com.jrp.pma.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.DAO.EmployeeRepository;
import com.jrp.pma.DAO.ProjectRepository;
import com.jrp.pma.DTO.ChartData;
import com.jrp.pma.DTO.EmployeeProject;
import com.jrp.pma.entities.Project;

@Controller
public class HomeController {
	
	@Value("${version}")
	private String ver;
	
	@Autowired
	ProjectRepository Prorepo;
	
	@Autowired
	EmployeeRepository Emprepo;
	
	// this will retrieve the main page
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		model.addAttribute("VersionNumber",ver);
		
		// querying the database for projects
		Iterable<Project> projects = Prorepo.findAll();
		model.addAttribute("projectsList", projects);
		
		// querying the database for project stage
		List<ChartData> projectData = Prorepo.getprojectStage();
		
		// Converting projectData object into a JSON structure for use in JS
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		// Visualizing... [["INPROGRESS",2],["COMPLETED",1],["NOTSTARTED",1]], this is the projectData
		model.addAttribute("projectStatusCnt",jsonString);
		
		// querying the database for employees
		List<EmployeeProject> employeesProjectCnt = Emprepo.employeeProjects();
		model.addAttribute("employeeProjectList", employeesProjectCnt);
		return "Main/Home";
	}

}
