package com.usecase.CustomerManagement.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usecase.CustomerManagement.domain.User;
import com.usecase.CustomerManagement.entity.UserEntity;
import com.usecase.CustomerManagement.exceptions.UserNotFoundException;
import com.usecase.CustomerManagement.mapping.CustomerMapper;
import com.usecase.CustomerManagement.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	public static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerMapper customerMapper;

	public User getUserById(Integer id) {
		LOG.info("In CustomerServiceImpl trigerred method getUserById");
		Optional<UserEntity> userEntity = this.customerRepository.findById(id);
		if(userEntity.isPresent()) {
			return this.customerMapper.entityToDomain(userEntity.get());
		}
		else {
			LOG.info("In CustomerServiceImpl throwing user not found exception");
			throw new UserNotFoundException("we couldn't find a customer record with this Id");
		}
		
	}

	public User saveUser(User user) {
		LOG.info("In CustomerServiceImpl trigerred method saveUser");
		UserEntity userEntity = customerMapper.domainToEntity(user);
		return this.customerMapper.entityToDomain(customerRepository.save(userEntity));
	}

	public User updateUser(User user, Integer id) {
		Optional<UserEntity> optUser = customerRepository.findById(id);
		if(optUser.isPresent()) {
			UserEntity userToUpdate = optUser.get();
			if(user.getName() != null) {
				userToUpdate.setName(user.getName());
			}
			
			if(user.getEmail() != null) {
				userToUpdate.setEmail(user.getEmail());
			}
			
			if(user.getContactNum() != null) {
				userToUpdate.setContactNum(user.getContactNum());
			}
			return this.customerMapper.entityToDomain(customerRepository.save(userToUpdate));
		}
		else {
			LOG.info("In CustomerServiceImpl throwing user not found exception");
			throw new UserNotFoundException("we couldn't find a customer record with this Id");
		}
			
	}
	
	public User updateUserByPut(User user) {
		LOG.info("In CustomerServiceImpl trigerred method updateUserByPut()");
		Optional<UserEntity> optUser = customerRepository.findById(user.getId());
		if(optUser.isPresent()) {
			UserEntity userEntity = customerMapper.domainToEntity(user);
			return this.customerMapper.entityToDomain(customerRepository.save(userEntity));
		}
		else {
			LOG.info("In CustomerServiceImpl throwing user not found exception");
			throw new UserNotFoundException("we couldn't find a customer record with this Id");
		}
		
	}

	public Integer deleteUser(Integer id) {
		Optional<UserEntity> user = customerRepository.findById(id);
		if(user.isPresent()) {
			customerRepository.deleteById(id);
			return id;
		}
		else {
			LOG.info("In CustomerServiceImpl throwing user not found exception");
			throw new UserNotFoundException("we couldn't find a customer record with this Id");
		}
	}

	

}
