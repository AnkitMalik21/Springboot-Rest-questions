package com.hcl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.hcl.dto.UserDTO;
import com.hcl.entity.User;

@Mapper(componentModel = "spring")
public interface AutoUserMapper {
	public AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

	UserDTO mapToUserDTO(User user);

	User mapToUser(UserDTO userDTO);
}

/*
 * @Mapper(componentModel = "spring") This tells MapStruct to generate a Spring
 * Bean for this mapper. Use case: You can now @Autowired it in your services
 * just like any Spring component.
 * 
 * @Mapper(componentModel = "spring") This tells MapStruct to generate a Spring
 * Bean for this mapper. Use case: You can now @Autowired it in your services
 * just like any Spring component.
 */
