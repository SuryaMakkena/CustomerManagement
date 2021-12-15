package com.usecase.CustomerManagement.CustomerService;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.usecase.CustomerManagement.Domain.User;
import com.usecase.CustomerManagement.Entity.UserEntity;
import com.usecase.CustomerManagement.Mapping.CustomerMapper;
import com.usecase.CustomerManagement.Repository.CustomerRepository;
import com.usecase.CustomerManagement.Service.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {
	
	@InjectMocks
	CustomerServiceImpl customerService;
	
	@Mock
	CustomerRepository customerRepository;
	
	@Mock
	CustomerMapper customerMapper;
	
	@Test
	public void getUserTest() {
		Integer id = 1;
		User user = mockUser();
		UserEntity userEntity = mockUserEntity();
		Mockito.when(customerRepository.findById(id)).thenReturn(Optional.of(userEntity));
		Mockito.when(customerMapper.entityToDomain(userEntity)).thenReturn(user);
		User actual = customerService.getUserById(id);
		assertEquals(user, actual);
	}
	
	@Test
	public void createUserTest() {
		User user = mockUser();
		UserEntity userEntity = mockUserEntity();
		Mockito.when(customerRepository.save(userEntity)).thenReturn(userEntity);
		Mockito.when(customerMapper.domainToEntity(user)).thenReturn(userEntity);
		Mockito.when(customerMapper.entityToDomain(userEntity)).thenReturn(user);
		User actual = customerService.saveUser(user);
		assertEquals(user, actual);
	}
	
	@Test
	public void updateUserTest() {
		User user = mockUser();
		UserEntity userEntity = mockUserEntity();
		Integer id = 1;
		Mockito.when(customerRepository.findById(id)).thenReturn(Optional.of(userEntity));
		Mockito.when(customerRepository.save(userEntity)).thenReturn(userEntity);
		Mockito.when(customerMapper.entityToDomain(userEntity)).thenReturn(user);
		User actual = customerService.updateUser(user, id);
		assertEquals(user, actual);
	}
	
	@Test
	public void updateUserByPutTest() {
		User user = mockUser();
		UserEntity userEntity = mockUserEntity();
		Mockito.when(customerRepository.findById(user.getId())).thenReturn(Optional.of(userEntity));
		Mockito.when(customerRepository.save(userEntity)).thenReturn(userEntity);
		Mockito.when(customerMapper.domainToEntity(user)).thenReturn(userEntity);
		Mockito.when(customerMapper.entityToDomain(userEntity)).thenReturn(user);
		User actual = customerService.updateUserByPut(user);
		assertEquals(user, actual);
	}
	
	@Test
	public void deleteUserTest() {
		Integer id = 1;
		UserEntity userEntity = mockUserEntity();
		Mockito.when(customerRepository.findById(id)).thenReturn(Optional.of(userEntity));
		Mockito.doNothing().when(customerRepository).deleteById(id);
		Integer actual = customerService.deleteUser(id);
		assertEquals(id, actual);
	}
	
	private User mockUser() {
		
		User user = new User();
		user.setId(1);
		user.setName("surya");
		user.setEmail("surya4992@gmail.com");
		user.setContactNum("9999999999");
		return user;
		
	}
	
	private UserEntity mockUserEntity() {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(1);
		userEntity.setName("surya");
		userEntity.setEmail("surya4992@gmail.com");
		userEntity.setContactnum("9999999999");
		return userEntity;
		
	}

}
