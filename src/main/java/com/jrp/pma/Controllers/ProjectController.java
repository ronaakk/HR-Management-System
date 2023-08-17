package com.jrp.pma.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.DTO.TimeChartData;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService proService;
	
	@Autowired
	EmployeeService empService;
	
	// this get mapping request will handle just the /projects routing
	@GetMapping
	public String displayEmployees(Model model) {
		Iterable<Project> projects = proService.findAll();
		// model adds all projects from the repository and sends them to the model, and then to the view
		model.addAttribute("projects", projects);
		
		return "Projects/ListofProjects";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Project aProject = new Project();
		// addAttribute takes the attribute name (has to match the one in html file)
		// and the second argument is the object of the attribute value
		model.addAttribute("aProject", aProject);
		Iterable<Employee> employees = empService.findAll();
		model.addAttribute("allEmployees",employees);
		
		return "Projects/New-Project";
	}
	
	
	@PostMapping("/save")
	public String createProject(@Valid @ModelAttribute("aProject") Project project, BindingResult bindingResult, Model model) {
		boolean submission = bindingResult.hasErrors();
		if (submission) {
			model.addAttribute("aProject", project);
			Iterable<Employee> employees = empService.findAll();
			model.addAttribute("allEmployees", employees);
			
			return "Projects/New-Project";
		}
		
		
		proService.save(project);
		
		// NO longer need the code below as it is now a many to many relationship, 
		
//		// need to match projectID to given employees of specific project from the argument passed
//		Iterable<Employee> chosenEmployees = empRepo.findAllById(employees);
//		for (Employee emp : chosenEmployees) {
//			// setting the project passed into this function to the employee
//			emp.setaProject(project);
//			// saving the projectID in the employee repo
//			empRepo.save(emp);
//		}
		
		// use a redirect whenever saving to DB, to prevent duplicate submissions
		return "redirect:/projects";
	}
	
	// this will send the user to the new-project page once update button is clicked
	@GetMapping("/update")
	public String displayProjectUpdateForm(@RequestParam("id") long id, Model model) {
		
		Project theProject = proService.findByProjectID(id);
		model.addAttribute("aProject", theProject);
		
		Iterable<Employee> employees = empService.findAll();
		model.addAttribute("allEmployees", employees);
		
		return "Projects/New-Project";
		
	}
	
	@GetMapping("/delete")
	public String displayProjectDeleteForm(@RequestParam("id") long id) {
		
		Project theProject = proService.findByProjectID(id);
		proService.delete(theProject);
		
		return "redirect:/projects";
		
	}
	
	@GetMapping("/project-timelines")
	public String displayProjectTimelines(Model model) throws JsonProcessingException {
		
		List<TimeChartData> timeData = proService.getProjectTimeline();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonTimelineString = objectMapper.writeValueAsString(timeData);
		
		model.addAttribute("projectTimeList", jsonTimelineString);
		
		return "Projects/project-timelines";
	}
	
	
	
	
	
	
	
	
	
	
}
