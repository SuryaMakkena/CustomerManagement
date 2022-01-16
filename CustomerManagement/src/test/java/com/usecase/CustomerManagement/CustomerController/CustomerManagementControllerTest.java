package com.usecase.CustomerManagement.CustomerController;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.usecase.CustomerManagement.controller.CustomerManagementController;
import com.usecase.CustomerManagement.controller.UserResponse;
import com.usecase.CustomerManagement.domain.User;
import com.usecase.CustomerManagement.mapping.CustomerMapper;
import com.usecase.CustomerManagement.service.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CustomerManagementControllerTest {
	
	@InjectMocks
	CustomerManagementController customerController;
		
	@Mock
	CustomerServiceImpl customerService;
	
	@Mock
	CustomerMapper customerMapper;
	
	@Test
	public void createuserTest(){
		UserResponse userRes = mockUserResponse();
		User user = mockUser();
		ResponseEntity<UserResponse> expected = new ResponseEntity<UserResponse>(userRes, HttpStatus.CREATED);
		Mockito.when(customerService.saveUser(user)).thenReturn(user);
		Mockito.when(customerMapper.responseToDomain(userRes)).thenReturn(user);
		Mockito.when(customerMapper.domainToResponse(user)).thenReturn(userRes);
		ResponseEntity<UserResponse> actual = customerController.createUser(userRes);
		assertEquals(expected, actual);
	}
	
	@Test
	public void getuserTest(){
		UserResponse userRes = mockUserResponse();
		Integer id = 1;
		User user = mockUser();
		ResponseEntity<UserResponse> expected = new ResponseEntity<UserResponse>(userRes, HttpStatus.OK);
		Mockito.when(customerService.getUserById(id)).thenReturn(user);
		Mockito.when(customerMapper.domainToResponse(user)).thenReturn(userRes);
		ResponseEntity<UserResponse> actual = customerController.getUser(id);
		assertEquals(expected, actual);
	}
	
	@Test
	public void updateUserTest(){
		UserResponse userRes = mockUserResponse();
		Integer id = 1;
		User user = mockUser();
		ResponseEntity<UserResponse> expected = new ResponseEntity<UserResponse>(userRes, HttpStatus.CREATED);
		Mockito.when(customerService.updateUser(user, id)).thenReturn(user);
		Mockito.when(customerMapper.responseToDomain(userRes)).thenReturn(user);
		Mockito.when(customerMapper.domainToResponse(user)).thenReturn(userRes);
		ResponseEntity<UserResponse> actual = customerController.updateUser(id, userRes);
		assertEquals(expected, actual);
	}
	
	@Test
	public void updateUserByPutTest(){
		UserResponse userRes = mockUserResponse();
		User user = mockUser();
		Integer id = 1;
		ResponseEntity<UserResponse> expected = new ResponseEntity<UserResponse>(userRes, HttpStatus.CREATED);
		Mockito.when(customerService.updateUserByPut(user)).thenReturn(user);
		Mockito.when(customerMapper.responseToDomain(userRes)).thenReturn(user);
		Mockito.when(customerMapper.domainToResponse(user)).thenReturn(userRes);
		ResponseEntity<UserResponse> actual = customerController.updateUserByPut(id, userRes);
		assertEquals(expected, actual);
	}
	
	@Test
	public void deleteUserTest(){
		Integer id = 1;
		ResponseEntity<String> expected = new ResponseEntity<String>("User with "+id+ " is Successfully deleted", HttpStatus.ACCEPTED);
		Mockito.when(customerService.deleteUser(id)).thenReturn(id);
		ResponseEntity<String> actual = customerController.deleteUser(id);
		assertEquals(expected, actual);
	}
	
	private UserResponse mockUserResponse() {
		UserResponse userRes = new UserResponse();
		userRes.setId(1);
		userRes.setEmail("surya4992@gmail.com");
		userRes.setName("Surya");
		userRes.setContactNum("9999999999");
		return userRes;
	}
	
	private User mockUser() {
		User user = new User();
		user.setId(1);
		user.setName("surya");
		user.setEmail("surya4992@gmail.com");
		user.setContactNum("9999999999");
		return user;
		
	}
	
	
	

}
