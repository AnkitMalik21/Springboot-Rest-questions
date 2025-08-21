package com.hcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dto.EmployeeDto;
import com.hcl.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {
    
	@Autowired
	private EmployeeService service;
	
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
		List<EmployeeDto> employees = service.getAllEmployees();
		return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(employees);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto>getEmployeeById(@PathVariable Long id){
		return service.getEmployeeById(id)
				.map(employee -> ResponseEntity.status(HttpStatusCode.valueOf(200)).body(employee))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto){
		try {
			EmployeeDto createEmployee = service.createEmployee(employeeDto);
			return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(createEmployee);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id,@Valid @RequestBody EmployeeDto employeeDto){
		try {
			EmployeeDto updatedEmployee = service.updateEmployee(id,employeeDto);
			return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(updatedEmployee);
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
		service.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}
}
