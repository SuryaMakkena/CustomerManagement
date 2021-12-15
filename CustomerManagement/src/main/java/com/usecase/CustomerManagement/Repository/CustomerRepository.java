package com.usecase.CustomerManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usecase.CustomerManagement.Entity.UserEntity;

@Repository
public interface CustomerRepository extends JpaRepository<UserEntity, Integer>{

}
