package com.hcl.mapper;

import com.hcl.dto.UserDTO;
import com.hcl.entity.User;

public class UserMapper {
	public static UserDTO mapToUserDTO(User user) {
		UserDTO userDTO = new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
		return userDTO;
	}

	public static User mapToUser(UserDTO userDTO) {
		User user = new User(userDTO.getId(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail());
		return user;
	}

}
