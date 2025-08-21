package com.hcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.PutExchange;

import com.hcl.entity.Project;
import com.hcl.entity.Student;
import com.hcl.repository.ProjectRepository;
import com.hcl.repository.StudentRepository;
import com.hcl.service.SchoolService;

@RestController
@RequestMapping("/school")
public class SchoolController {

    private final StudentRepository studentRepository;
    @Autowired
    private SchoolService schoolService;

    SchoolController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
   /*
    saveStudent
    saveProject
    getStudentById
    getProjectById
    */
    
    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
    	Student student = schoolService.getStudentById(id);
    	return ResponseEntity.ok(student);
    }
    
    @GetMapping("/project/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id){
    	Project project = schoolService.getProjectById(id);
    	return ResponseEntity.ok(project);
    }
    
    @PostMapping("/student")
    public ResponseEntity<String> saveStudent(@RequestBody Student student){
    	schoolService.saveStudent(student);
    	return ResponseEntity.ok("Student saved successfully");
    }
    
    @PostMapping("/project")
    public ResponseEntity<String> saveProject(@RequestBody Project project){
    	schoolService.saveProject(project);
    	return ResponseEntity.ok("project saved successfully");
    }
    
    @GetMapping("/students/getAll")
    public ResponseEntity<List<Student>> getAllStudent(){
    	List<Student> students = schoolService.getAllStudent();
    	return ResponseEntity.ok(students);
    }
    
    @GetMapping("/projects/getAll")
    public ResponseEntity <List<Project>> getAllProject(){
    	List<Project> projects = schoolService.getAllProject();
    	return ResponseEntity.ok(projects);
    }
    
    @GetMapping("/studentByBranchName/{name}")
    public ResponseEntity<List<Student>> getStudentByBranchName(@PathVariable String name){
    	List<Student> branchList = schoolService.getStudentByBranchName(name);
    	return ResponseEntity.ok(branchList);	
    }
    
    @GetMapping("/projectByType/{name}")
    public ResponseEntity<List<Project>> getProjectByType(@PathVariable String name){
    	List<Project> projectList = schoolService.getProjectByType(name);
    	return ResponseEntity.ok(projectList);
    }
    
    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<Student> updateStudentById(@PathVariable Long id,@RequestBody Student updateStudent){
    	Student student = schoolService.updateStudentById(id, updateStudent);
    	return ResponseEntity.ok(student);
    }
    
    @DeleteMapping("/student/{id}")
    public ResponseEntity<Student> deleteStudentById(@PathVariable Long id){
    	Student student = schoolService.deleteStudentById(id);
    	return ResponseEntity.ok(student);
    }
    
    @DeleteMapping("/deleteStudentFromProject/{studnetid}/{projectId}")
    public ResponseEntity<String> removeStudentFromProject(@PathVariable Long studentId,
    		@PathVariable Long projectId){
        String msg = schoolService.removeStudentFromProject(studentId, projectId);
        return ResponseEntity.ok(msg);
    }
    
    @PostMapping("/assignStudentToProject/{projectId}")
    public ResponseEntity<String> assignMultipleStudentsToProject(@RequestBody List<Long>studentIds,@PathVariable Long projectId){
    	String msg = schoolService.assignMultipleStudentsToProject(studentIds, projectId);
    	return ResponseEntity.ok(msg);
    }
    
}
