package com.usecase.CustomerManagement.Mapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.usecase.CustomerManagement.Controller.UserResponse;
import com.usecase.CustomerManagement.Domain.User;
import com.usecase.CustomerManagement.Entity.UserEntity;

@Configuration
public class CustomerMapper {
	
	public static final Logger LOG = LoggerFactory.getLogger(CustomerMapper.class);
	
	public UserEntity domainToEntity(User user) {
		LOG.info("In Customer Mapper mapping domain to Entity");
		UserEntity userEntity = new UserEntity();
		if(user.getId()!= null) {
			userEntity.setId(user.getId());
		}
		userEntity.setName(user.getName());
		userEntity.setEmail(user.getEmail());
		userEntity.setContactnum(user.getContactNum());
		LOG.info("In Customer Mapper mapping completed from domain to Entity");
		return userEntity;
	}
	
	public User entityToDomain(UserEntity userEntity) {
		LOG.info("In Customer Mapper mapping Entity to Domain");
		User user = new User();
		user.setId(userEntity.getId());
		user.setName(userEntity.getName());
		user.setEmail(userEntity.getEmail());
		user.setContactNum(userEntity.getContactnum());
		LOG.info("In Customer Mapper mapping completed from Entity to Domain");
		return user;
	}
	
	public UserResponse domainToResponse(User user) {
		LOG.info("In Customer Mapper mapping domain to Response");
		UserResponse userRes = new UserResponse();
		userRes.setId(user.getId());
		userRes.setName(user.getName());
		userRes.setEmail(user.getEmail());
		userRes.setContactNum(user.getContactNum());
		LOG.info("In Customer Mapper mapping completed from domain to Response");
		return userRes;
	}
	
	public User responseToDomain(UserResponse userResponse) {
		LOG.info("In Customer Mapper mapping Response to Domain");
		User user = new User();
		user.setName(userResponse.getName());
		user.setEmail(userResponse.getEmail());
		user.setContactNum(userResponse.getContactNum());
		LOG.info("In Customer Mapper mapping completed Response to Domain");
		return user;
	}

}
