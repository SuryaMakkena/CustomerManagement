package com.usecase.CustomerManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usecase.CustomerManagement.entity.UserEntity;

@Repository
public interface CustomerRepository extends JpaRepository<UserEntity, Integer>{

}
