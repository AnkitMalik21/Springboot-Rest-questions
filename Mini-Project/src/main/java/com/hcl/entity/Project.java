package com.hcl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Project")
public class Project {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long projectId;
     private String projectName;
     private String projectType;
     
     @ManyToMany(mappedBy = "projects")//this is the non owning side , this tell owner side what to search in the field to reach here
     @JsonIgnore // Ignore students when serializing Project
     private Set<Student> students = new HashSet<>();

	 public Long getProjectId() {
		 return projectId;
	 }

	 public void setProjectId(Long projectId) {
		 this.projectId = projectId;
	 }

	 public String getProjectName() {
		 return projectName;
	 }

	 public void setProjectName(String projectName) {
		 this.projectName = projectName;
	 }

	 public String getProjectType() {
		 return projectType;
	 }

	 public void setProjectType(String projectType) {
		 this.projectType = projectType;
	 }

	 public Set<Student> getStudents() {
		 return students;
	 }

	 public void setStudents(Set<Student> students) {
		 this.students = students;
	 }
}
