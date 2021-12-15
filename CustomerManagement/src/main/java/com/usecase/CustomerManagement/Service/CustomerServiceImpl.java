package com.usecase.CustomerManagement.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usecase.CustomerManagement.Domain.User;
import com.usecase.CustomerManagement.Entity.UserEntity;
import com.usecase.CustomerManagement.Exceptions.UserNotFoundException;
import com.usecase.CustomerManagement.Mapping.CustomerMapper;
import com.usecase.CustomerManagement.Repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerMapper customerMapper;

	public User getUserById(Integer id) {
		Optional<UserEntity> userEntity = this.customerRepository.findById(id);
		if(userEntity.isPresent()) {
			return this.customerMapper.entityToDomain(userEntity.get());
		}
		else {
			throw new UserNotFoundException("we couldn't find a customer record with this Id");
		}
		
	}

	public User saveUser(User user) {
		UserEntity userEntity = customerMapper.domainToEntity(user);
		return this.customerMapper.entityToDomain(customerRepository.save(userEntity));
	}

	public User updateUser(User user, Integer id) {
		UserEntity userToUpdate = customerRepository.getById(id);
		if(user.getName() != null) {
			userToUpdate.setName(user.getName());
		}
		
		if(user.getEmail() != null) {
			userToUpdate.setEmail(user.getEmail());
		}
		
		if(user.getContactNum() != null) {
			userToUpdate.setContactnum(user.getContactNum());
		}
		return this.customerMapper.entityToDomain(customerRepository.save(userToUpdate));
		
	}

	public void deleteUser(Integer id) {
		Optional<UserEntity> user = customerRepository.findById(id);
		if(user.isPresent()) {
			customerRepository.deleteById(id);
		}
	}

}
