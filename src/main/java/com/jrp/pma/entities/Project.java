package com.jrp.pma.entities;

import java.util.ArrayList;
import java.util.Date;
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
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Project {
	
	@Id
	@SequenceGenerator(name = "project_seq", sequenceName="project_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
	private long projectID;
	
	@NotBlank(message = "Project name cannot be blank")
	private String name;
	
	private String stage; // NOTSTARTED, COMPLETED, INPROGRESS
	
	@NotBlank(message = "Project description cannot be blank")
	private String description;
	

	@DateTimeFormat(pattern = "mm-dd-yyyy")
	private Date startDate;
	
	@DateTimeFormat(pattern = "mm-dd-yyyy")
	private Date endDate;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
			fetch = FetchType.LAZY)
	@JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "projectid"), inverseJoinColumns = @JoinColumn(name = "employeeid"))
	@JsonIgnore
	private List<Employee> employees;

	public Project() {
		
	}
	// projectID not in this constructor, as the database will handle that
	public Project(String name, String stage, String description) {
		super();
		this.name = name;
		this.stage = stage;
		this.description = description;
	}


	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public long getProjectID() {
		return projectID;
	}

	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	// convenience method (to add employees to given list)
		public void addEmployee(Employee emp1) {
			 if (employees == null) {
				employees = new ArrayList<>();
			}
			employees.add(emp1);
		}
	
}
