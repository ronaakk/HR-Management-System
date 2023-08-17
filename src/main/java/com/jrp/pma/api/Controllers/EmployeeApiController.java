package com.jrp.pma.api.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrp.pma.DAO.EmployeeRepository;
import com.jrp.pma.entities.Employee;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public Iterable<Employee> getEmployees() {
		return empRepo.findAll();
	}
	
	// to get just one employee
	@GetMapping("/{id}")
	public Employee getEmployeeByID(@PathVariable("id") Long id) { 
		// the data that the url will return
		return empRepo.findById(id).get();
	}
	
	// Create a new employee
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee create(@RequestBody @Valid Employee employee) {
		return empRepo.save(employee);
	}
	
	// This will update the employee but will cascade delete anything they are a part of (projects, etc)
	// the consumes parameter is always assumed by Spring, so no need to have it
	@PutMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee update(@RequestBody @Valid Employee employee) { 
		return empRepo.save(employee);
	}
	
	// This will partially update the employee without deleting them from their associations
	@PatchMapping(path = "/{id}", consumes = "application/json")
	public Employee partialUpdate(@PathVariable("id") Long id, @RequestBody @Valid Employee patchEmployee) {
		Employee emp = empRepo.findById(id).get();
		
		if (patchEmployee.getEmail() != null) {
			emp.setEmail(patchEmployee.getEmail());
		}
		
		if (patchEmployee.getFirstName() != null) {
			emp.setFirstName(patchEmployee.getFirstName());
		}
		
		if (patchEmployee.getLastName() != null) {
			emp.setLastName(patchEmployee.getLastName());
		}
		
		return empRepo.save(emp);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmployee(@PathVariable("id") Long id) {
		try {
			empRepo.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			
		}
		
	}
	
	@GetMapping(params = {"page","size"})
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Employee> findPaginatedEmployees(@RequestParam("page") int page, @RequestParam("size") int size) {
		PageRequest pageAndSize = PageRequest.of(page, size);
		return empRepo.findAll(pageAndSize);
	}

}
