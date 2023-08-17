package com.jrp.pma.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jrp.pma.Validators.UniqueValue;

@Entity
public class Employee {
	
	@Id
	@SequenceGenerator(name = "employee_seq", sequenceName="employee_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
	private long employeeID;
	
	@NotBlank(message = "Please enter a valid first name")
	@Size(min = 2, max = 50)
	private String firstName;
	
	@NotBlank(message = "Please enter a valid last name")
	@Size(min = 1, max = 50)
	private String lastName;
	
	@NotBlank(message = "Please enter a valid email")
	@Email(message = "Please enter a valid email")
	@UniqueValue
	private String email;
	
	
	// these cascade types are industry standard same with fetch type
	// these are here in case anything happens to the parent entity
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
			fetch = FetchType.LAZY)
	@JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "employeeid"), inverseJoinColumns = @JoinColumn(name = "projectid"))
	@JsonIgnore
	private List<Project> aProject;
	
	// Need an empty constructor to pass into model.addAttribute()
	public Employee() {
		
	}
	
	public Employee(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public List<Project> getaProject() {
		return aProject;
	}

	public void setaProject(List<Project> aProject) {
		this.aProject = aProject;
	}

	public long getEmployeeID() {
		return employeeID;
	}
	
	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
}
