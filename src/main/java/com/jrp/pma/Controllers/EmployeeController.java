package com.jrp.pma.Controllers;

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

import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	// this get mapping request will handle just the /employees routing
	@GetMapping
	public String displayEmployees(Model model) {
		// Had to override this method in employee repository to find a list of employees
		Iterable<Employee> employees = empService.findAll();
		// model adds all employees from the repository and sends them to the model, and then to the view
		model.addAttribute("employees", employees);
		// name of the folder and html file where everything resides
		return "employees/ListofEmployees";
	}
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		Employee newEmployee = new Employee();
		// addAttribute takes the attribute name (has to match the one in html file)
		// and the second argument is the object of the attribute value
		model.addAttribute("Employee", newEmployee);
		return "employees/New-Employee";
	}
	
	
	@PostMapping("/save")
	public String createEmployee(@Valid @ModelAttribute("Employee") Employee employee, BindingResult bindingResult, Model model) {
		boolean submission = bindingResult.hasErrors();
		if (submission) {
			model.addAttribute("Employee", employee);
		    return "employees/New-Employee";
		}
	
		empService.save(employee);
		// use a redirect whenever saving to DB, to prevent duplicate submissions
		return "redirect:/employees";	
		
	}
	
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model) {
		
		// finds given employee by their id
		Employee theEmp = empService.findByEmployeeID(theId);
		// will send the given employee from their id to the form, which will allow the user to update its fields
		model.addAttribute("Employee", theEmp);
		return "employees/New-Employee";
	}
	
	@GetMapping("/delete")
	public String displayEmployeeDeletePage(@RequestParam("id") long theId) {
		
		Employee theEmp = empService.findByEmployeeID(theId);
		empService.delete(theEmp);
		return "redirect:/employees";
	}
	

}
