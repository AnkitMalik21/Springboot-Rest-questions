package com.hcl.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.dto.EmployeeDto;
import com.hcl.entity.Employee;
import com.hcl.repository.EmployeeRepository;

@Service
public class EmployeeService {
      
	private final EmployeeRepository repo;
	
	public EmployeeService(EmployeeRepository repo) {
		this.repo = repo;
	}
    
	public List<EmployeeDto> getAllEmployees(){
	     return repo.findAll()
	    		 .stream()
	    		 .map(this::convertToDto)
	    		 .collect(Collectors.toList());
	}
	
	public Optional<EmployeeDto> getEmployeeById(Long id){
		return repo.findById(id)
				.map(this::convertToDto);
	}
	
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		if(repo.existsByEmail(employeeDto.getEmail())) {
			throw new RuntimeException("Employee with email "+ employeeDto.getEmail() + " already exists");
		}
		
		Employee employee = convertToEnitity(employeeDto);
		Employee savedEmployee = repo.save(employee);
		return convertToDto(savedEmployee);
	}
	
	public EmployeeDto updateEmployee(Long id,EmployeeDto employeeDto) {
		Employee existingEmployee = repo.findById(id)
				.orElseThrow(()-> new RuntimeException("Employee not found with id: "+id));
		
		if(
			!existingEmployee.getEmail().equals(employeeDto.getEmail()) &&
			repo.existsByEmail(employeeDto.getEmail())
		  ) {
			throw new RuntimeException("Employee with email "+ employeeDto.getEmail() +" already exists");
		}
		
		existingEmployee.setName(employeeDto.getName());
		existingEmployee.setEmail(employeeDto.getEmail());
		existingEmployee.setDateOfBirth(employeeDto.getDateOfBirth());
		existingEmployee.setSalary(employeeDto.getSalary());
		
		Employee updatedEmployee = repo.save(existingEmployee);
		
		return convertToDto(updatedEmployee);
	}
	
	public void deleteEmployee(Long id) {
		if(!repo.existsById(id)) {
			throw new RuntimeException("Employee not found with id: "+ id);
		}
		
		repo.deleteById(id);
	}
	
	private Employee convertToEnitity(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		employee.setName(employeeDto.getName());
		employee.setEmail(employeeDto.getEmail());
		employee.setDateOfBirth(employeeDto.getDateOfBirth());
		employee.setSalary(employeeDto.getSalary());
		return employee;
	}
	
	private EmployeeDto convertToDto(Employee employee) {
		return new EmployeeDto(
				employee.getId(),
				employee.getName(),
				employee.getEmail(),
				employee.getDateOfBirth(),
				employee.getSalary()
				);
	}
}
