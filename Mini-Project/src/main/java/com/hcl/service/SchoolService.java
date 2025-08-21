package com.hcl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.hcl.entity.Project;
import com.hcl.entity.Student;
import com.hcl.repository.ProjectRepository;
import com.hcl.repository.StudentRepository;

@Service
public class SchoolService {
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ProjectRepository projectRepository;

	public String saveStudent(Student student) {
		studentRepository.save(student);
		return "Student saved successfully";
	}

	public String saveProject(Project project) {
		projectRepository.save(project);
		return "project saved successfully";
	}

	public Student getStudentById(Long studentId) {
		return studentRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Didn't Found student with Id: " + studentId));
	}

	public Project getProjectById(Long projectId) {
		return projectRepository.findById(projectId)
				.orElseThrow(() -> new RuntimeException("Didn't Found student with Id: " + projectId));
	}
	
	//retrieve operation
	
	//1 get all students
	public List<Student> getAllStudent(){
		List<Student> list = studentRepository.findAll();
		return list;
	}
	
	//2 get all project
	public List<Project> getAllProject(){
		List<Project> list  = projectRepository.findAll();
		return list;
	}
	
	//3 get Student by branch name
	public List<Student> getStudentByBranchName(String name){
		List<Student> list = studentRepository.findByBranchName(name);
		return list;
	}
	
	//4 get project by type
		public List<Project> getProjectByType(String name){
			List<Project> list = projectRepository.findByProjectTypeIgnoreCase(name);
			return list;
		}
		
    //update Queries
		
	// 5 -> update Student
	/*
	 * public Student updateStudentById(@PathVariable Long id,@RequestBody Student
	 * student) { Optional<Student> studentOptional =
	 * studentRepository.findById(id); if(studentOptional.isPresent()) { Student
	 * studentFound = studentOptional.get();
	 * studentFound.setStudentName(student.getStudentName());
	 * studentFound.setStudentAge(student.getStudentAge());
	 * studentFound.setBranchName(student.getBranchName());
	 * 
	 * studentRepository.save(studentFound); return studentFound; } else { throw new
	 * RuntimeException("Student not found"); } }
	 */
		
		public Student updateStudentById( Long id, Student updateStudent) {
			Student student = studentRepository.findById(id)
					.orElseThrow(()-> new RuntimeException("Error while searching for id: " + id));
			student.setStudentName(updateStudent.getStudentName());
			student.setStudentAge(updateStudent.getStudentAge());
			student.setBranchName(updateStudent.getBranchName());
			student.setProjects(updateStudent.getProjects());
			
			studentRepository.save(student);
			return student;
		}
		
		
		public Project updateProjectById(Long id,Project updateProject) {
			Project project = projectRepository.findById(id)
					.orElseThrow(()-> new RuntimeException("Error while searching for id: " + id));
			project.setProjectName(updateProject.getProjectName());
			project.setProjectType(updateProject.getProjectType());
			project.setStudents(updateProject.getStudents());
			
			
			projectRepository.save(project);
			return project;
		}
		
		//delete method
		
		public Student deleteStudentById(Long id) {
			Student student = studentRepository.findById(id)
					.orElseThrow(()-> new RuntimeException("didn't found student with Id: "+ id));
			Set<Project> projects = student.getProjects();
			for(Project project:projects) {
				project.getStudents().remove(student);
			}
			return student;
		}
		
		public Project deleteProjectById(Long id) {
			Project project = projectRepository.findById(id)
					.orElseThrow(()->new RuntimeException("didn't found student with Id: " + id));
			Set<Student> students = project.getStudents();
			for(Student student : students) {
				project.getStudents().remove(project);
			}
			return project;
			
		}
		
		
		//removeStudentFromProject -- removeProjectFromStudent
		
		public String removeStudentFromProject(Long studentId,Long projectId) {
			Student student = studentRepository.findById(projectId)
				 .orElseThrow(()-> new RuntimeException("didn't found student with the id: " + studentId));
			
			Project project = projectRepository.findById(projectId)
					.orElseThrow(()->new RuntimeException("didn't found student with the id: " + projectId));
			
			project.getStudents().remove(student);
			student.getProjects().remove(project);
			
			return "Student remove successfully";
			
		}
		
		// add
		public String assignMultipleStudentsToProject(List<Long> studentIds, Long projectId) {
		    Project project = projectRepository.findById(projectId)
		        .orElseThrow(() -> new RuntimeException("Did not find project with Id: " + projectId));

		    List<Student> students = studentRepository.findAllById(studentIds);

		    if (students.size() != studentIds.size()) {
		        List<Long> foundIds = students.stream()
		            .map(Student::getStudentId)
		            .collect(Collectors.toList());
		        List<Long> notFound = new ArrayList<>(studentIds);
		        notFound.removeAll(foundIds);
		        throw new RuntimeException("Did not find Students with the Ids: " + notFound);
		    }

		    for (Student student : students) {
		        // Add project to student's projects set (owning side)
		        student.getProjects().add(project);
		        // Also add student to project's student set for bidirectional sync
		        project.getStudents().add(student);
		        // Save owning side entity
		        studentRepository.save(student);
		    }
		    
		    // optional: save project for consistency; not mandatory for relationship persistence
		    projectRepository.save(project);

		    return "Students added successfully to project " + projectId;
		}

		
		
}

/*
 * 
 * Retrieve Operations getAllStudents() - Get all students
 * 
 * getAllProjects() - Get all projects
 * 
 * getStudentsByBranchName(String branchName) - Find students by branch
 * 
 * getProjectsByType(String projectType) - Find projects by type
 * 
 * Update Operations updateStudent(Long studentId, Student updatedStudent) -
 * Update student details
 * 
 * updateProject(Long projectId, Project updatedProject) - Update project
 * details
 * 
 * Delete Operations deleteStudentById(Long studentId) - Delete a student
 * 
 * deleteProjectById(Long projectId) - Delete a project
 * 
 * Relationship Management Methods Assignment Operations
 * assignStudentToProject(Long studentId, Long projectId) - Add student to
 * project
 * 
 * removeStudentFromProject(Long studentId, Long projectId) - Remove student
 * from project
 * 
 * assignMultipleStudentsToProject(List<Long> studentIds, Long projectId) - Bulk
 * assignment
 * 
 * Query Relationship Operations getProjectsByStudentId(Long studentId) - Get
 * all projects for a student
 * 
 * getStudentsByProjectId(Long projectId) - Get all students in a project
 * 
 * getStudentsNotInAnyProject() - Find unassigned students
 * 
 * getProjectsWithNoStudents() - Find projects with no students
 * 
 * Advanced Query Methods Statistical/Count Methods getStudentCountByBranch() -
 * Count students per branch
 * 
 * getProjectCountByType() - Count projects by type
 * 
 * getStudentCountInProject(Long projectId) - Count students in specific project
 * 
 * Search Methods searchStudentsByName(String name) - Search students by name
 * pattern
 * 
 * searchProjectsByName(String name) - Search projects by name pattern
 * 
 * Validation Methods isStudentAssignedToProject(Long studentId, Long projectId)
 * - Check if relationship exists
 * 
 * validateStudentExists(Long studentId) - Check if student exists
 * 
 * validateProjectExists(Long projectId) - Check if project exists
 * 
 * Start with the basic CRUD operations first, then move to relationship
 * management methods. These are the most commonly needed operations in a
 * Many-to-Many relationship scenario.
 */