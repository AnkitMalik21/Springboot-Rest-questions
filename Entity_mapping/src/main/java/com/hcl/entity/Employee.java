package com.hcl.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee {
     @Id
     @GeneratedValue
     private Long id;
     
     private String name;
     
     @ManyToMany(cascade = CascadeType.ALL)
     @JoinTable(
    	name = "employee_project",
    	joinColumns = @JoinColumn(name = "employee_id"),
    	inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    
     private Set<Project> projects = new HashSet<>();
     
     public Employee() {
    	 
     }

	 public Employee(String name) {
		this.name = name;
	 }

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	 public String getName() {
		 return name;
	 }

	 public void setName(String name) {
		 this.name = name;
	 }

	 public Set<Project> getProjects() {
		 return projects;
	 }

	 public void setProjects(Set<Project> projects) {
		 this.projects = projects;
	 }
     
	 public void addProject(Project project) {
		 projects.add(project);
		 project.getEmployees().add(this);
	 }
     
     
    
}
