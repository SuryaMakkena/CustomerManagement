package com.usecase.CustomerManagement.service;

import com.usecase.CustomerManagement.domain.User;

public interface CustomerService {

    User getUserById(Integer id);

    User saveUser(User user);

    User updateUser(User user, Integer id);

    User updateUserByPut(User user);

    Integer deleteUser(Integer id);

}
