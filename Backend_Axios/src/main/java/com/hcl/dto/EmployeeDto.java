package com.hcl.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

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


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Size(min =2,max=100,message = "Name must be between 2 and 100 character")
    private String name;
    
    @NotBlank(message="Email is required")
    @Email(message="please provide a valid email")
    private String email;
    
    @NotNull(message="Date of birth is required")
    @Past(message="Date of birth must be in the past")
    private LocalDate dateOfBirth;
    
    @NotNull(message = "Salary is required")
    @DecimalMin(value="0.0",inclusive=false,message="Salary must be greater then 0")
    private BigDecimal salary;
}
