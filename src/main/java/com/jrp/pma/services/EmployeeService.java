package com.jrp.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrp.pma.DAO.EmployeeRepository;
import com.jrp.pma.DTO.EmployeeProject;
import com.jrp.pma.entities.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository empRepo;
	
	public Employee save(Employee employee) {
		return empRepo.save(employee);
	}
	
	public Iterable<Employee> findAll() {
		return empRepo.findAll();
	}
	
	public List<EmployeeProject> employeeProjects() {
		return empRepo.employeeProjects();
	}

	public Employee findByEmployeeID(long theId) {
		// TODO Auto-generated method stub
		return empRepo.findByEmployeeID(theId);
	}

	public void delete(Employee theEmp) {
		// TODO Auto-generated method stub
		empRepo.delete(theEmp);
	}


	
}
	
	
	
	
	
	
	
	
	
	
	
//	// Field injection
//	@Autowired
//	EmployeeRepository empRepo;
//	
//	// Constructor Injection
//	public EmployeeService(EmployeeRepository empRepo) {
//		this.empRepo = empRepo;
//	}
//
//	// Setter Injection
//	@Autowired
//	public void setEmpRepo(EmployeeRepository empRepo) {
//		this.empRepo = empRepo;
//	}
	

