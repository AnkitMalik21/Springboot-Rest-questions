package com.hcl.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.hcl.dto.UserDTO;
import com.hcl.entity.User;
import com.hcl.exceptions.EmailAlreadyExistsException;
import com.hcl.exceptions.ResourceNotFoundException;
import com.hcl.mapper.AutoUserMapper;
import com.hcl.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	public UserDTO createUser(UserDTO userDTO) throws EmailAlreadyExistsException {
		Optional<User> optionalUserOptional = userRepository.findByEmail(userDTO.getEmail());

		if (optionalUserOptional.isPresent()) {
			throw new EmailAlreadyExistsException("Email address exist");
		}

		User user = AutoUserMapper.MAPPER.mapToUser(userDTO);
		User savedUser = userRepository.save(user);

		UserDTO saveDTO = AutoUserMapper.MAPPER.mapToUserDTO(savedUser);
		return saveDTO;
	}

	public UserDTO getUserById(Long userId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		return AutoUserMapper.MAPPER.mapToUserDTO(user);
	}

	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDTO(user)).collect(Collectors.toList());
	}
	
	public UserDTO updateUser(Long id, UserDTO userDTO) throws ResourceNotFoundException {
	    Optional<User> optionalUser = userRepository.findById(id);

	    if (!optionalUser.isPresent()) {
	        throw new ResourceNotFoundException("User", "id", id);
	    }

	    User existingUser = optionalUser.get();//this return value present
	    existingUser.setEmail(userDTO.getEmail());
	    existingUser.setFirstName(userDTO.getFirstName());
	    existingUser.setLastName(userDTO.getLastName());

	    User updatedUser = userRepository.save(existingUser);

	    return AutoUserMapper.MAPPER.mapToUserDTO(updatedUser);
	}

	public String deleteUser(@PathVariable Long id){
		userRepository.deleteById(id);
		return "User has been delete";
	}
}
