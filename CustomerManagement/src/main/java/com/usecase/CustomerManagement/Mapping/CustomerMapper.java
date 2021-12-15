package com.usecase.CustomerManagement.Mapping;

import org.springframework.context.annotation.Configuration;

import com.usecase.CustomerManagement.Controller.UserResponse;
import com.usecase.CustomerManagement.Domain.User;
import com.usecase.CustomerManagement.Entity.UserEntity;

@Configuration
public class CustomerMapper {
	
	public UserEntity domainToEntity(User user) {
		UserEntity userEntity = new UserEntity();
		if(user.getId()!= null) {
			userEntity.setId(user.getId());
		}
		userEntity.setName(user.getName());
		userEntity.setEmail(user.getEmail());
		userEntity.setContactnum(user.getContactNum());
		return userEntity;
	}
	
	public User entityToDomain(UserEntity userEntity) {
		User user = new User();
		user.setId(userEntity.getId());
		user.setName(userEntity.getName());
		user.setEmail(userEntity.getEmail());
		user.setContactNum(userEntity.getContactnum());
		return user;
	}
	
	public UserResponse domainToResponse(User user) {
		UserResponse userRes = new UserResponse();
		userRes.setId(user.getId());
		userRes.setName(user.getName());
		userRes.setEmail(user.getEmail());
		userRes.setContactNum(user.getContactNum());
		return userRes;
	}
	
	public User responseToDomain(UserResponse userResponse) {
		User user = new User();
		user.setName(userResponse.getName());
		user.setEmail(userResponse.getEmail());
		user.setContactNum(userResponse.getContactNum());
		return user;
	}

}
