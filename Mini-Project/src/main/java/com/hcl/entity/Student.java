package com.hcl.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="student")
public class Student {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private  Long studentId;
     private String studentName;
     private Long studentAge;
     private String branchName;
     
     @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     @JoinTable(
         name = "student_project",
         joinColumns = @JoinColumn(name = "student_id"),
         inverseJoinColumns = @JoinColumn(name = "project_id")
     )
     //@JsonManagedReference  // Forward reference, serialize normally
     @JsonIgnore
     private Set<Project> projects = new HashSet<>();

	 public Long getStudentId() {
		 return studentId;
	 }

	 public void setStudentId(Long studentId) {
		 this.studentId = studentId;
	 }

	 public String getStudentName() {
		 return studentName;
	 }

	 public void setStudentName(String studentName) {
		 this.studentName = studentName;
	 }

	 public Long getStudentAge() {
		 return studentAge;
	 }

	 public void setStudentAge(Long studentAge) {
		 this.studentAge = studentAge;
	 }

	 public String getBranchName() {
		 return branchName;
	 }

	 public void setBranchName(String branchName) {
		 this.branchName = branchName;
	 }

	 public Set<Project> getProjects() {
		 return projects;
	 }

	 public void setProjects(Set<Project> projects) {
		 this.projects = projects;
	 }   
}
