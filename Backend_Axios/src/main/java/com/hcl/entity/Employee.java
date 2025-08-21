package com.hcl.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="employeesAxios")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message="Name is required")
    @Size(min =2,max=100,message="Name must be between 2 and 100")
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message ="Email is required")
    @Email(message = "Please provide a valid email")
    @Column(nullable = false,unique = true)
    private String email;
    
    
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @Column(name="date_of_birth",nullable=false)
    private LocalDate dateOfBirth;
    
    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0",inclusive = false,message = "Salary must be greater than 0")
    @Column(nullable = false,precision=10,scale=2)
    private BigDecimal salary;
}
