package com.hcl.entity;

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Project {
     @Id
     @GeneratedValue
     private Long id;
     
     private String projectName;
     
     @ManyToMany(mappedBy = "projects")
     private Set<Employee> employees = new HashSet<>();
     
     public Project() {
    	 
     }
     
     public Project(String projectName) {
    	 this.projectName = projectName;
     }

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	 public String getProjectName() {
		 return projectName;
	 }

	 public void setProjectName(String projectName) {
		 this.projectName = projectName;
	 }

	 public Set<Employee> getEmployees() {
		 return employees;
	 }

	 public void setEmployees(Set<Employee> employees) {
		 this.employees = employees;
	 }
     
     
}
