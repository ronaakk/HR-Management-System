package com.jrp.pma.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jrp.pma.DTO.EmployeeProject;
import com.jrp.pma.entities.Employee;

@RepositoryRestResource(collectionResourceRel = "apiemployees", path = "apiemployees")
//Allows for primary key to be created with Employee class and employeeID
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long> {
	
	
	@Query(nativeQuery = true, value = "SELECT e.first_name AS FirstName, e.last_name AS LastName, COUNT(pe.employeeid) as ProjectCount "
			+ "FROM employee  e LEFT JOIN project_employee pe ON pe.employeeid = e.employeeid "
			+ "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
	public List<EmployeeProject> employeeProjects();

	public Employee findByEmail(String value);

	public Employee findByEmployeeID(long theId);
	

}
