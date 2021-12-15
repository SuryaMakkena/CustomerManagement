package com.usecase.CustomerManagement.Service;

import com.usecase.CustomerManagement.Domain.User;

public interface CustomerService {
	
	User getUserById(Integer id);
	User saveUser(User user);
	User updateUser(User user, Integer id);
	void deleteUser(Integer id);

}