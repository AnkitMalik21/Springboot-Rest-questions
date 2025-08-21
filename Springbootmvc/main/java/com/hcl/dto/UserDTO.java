package com.hcl.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "User first names should not be null or empty")
    private String firstName;
    
    @NotEmpty(message = "User lastName should not be null or empty")
    private String lastName;
    
    @NotEmpty(message="User email should not be null or empty")
    @Email(message = "Email address should be valid")
    private String email;
    
    public UserDTO() {
    	super();
    }
    
    //parameterised constructor
    
	public UserDTO(Long id, @NotEmpty(message = "User first names should not be null or empty") String firstName,
			@NotEmpty(message = "User lastName should not be null or empty") String lastName,
			@NotEmpty(message = "User email should not be null or empty") @Email(message = "Email address should be valid") String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
    
}
