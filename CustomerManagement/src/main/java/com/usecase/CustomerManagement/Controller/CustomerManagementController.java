package com.usecase.CustomerManagement.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usecase.CustomerManagement.Domain.User;
import com.usecase.CustomerManagement.Mapping.CustomerMapper;
import com.usecase.CustomerManagement.Service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path = "customer/")
public class CustomerManagementController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerMapper customerMapper;
	
	
	@Operation(summary = "Create a new Customer User",
            description = "Create a new Customer User with given User details")
    @ApiResponse(responseCode = "204", description = "The User has been created successfully")
    @ApiResponse(responseCode = "400", description = "The request is malformed")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    @ApiResponse(responseCode = "403", description = "The user cannot access this resource")
    @ApiResponse(responseCode = "404", description = "Not found")
	@PostMapping(path = "/new")
	public ResponseEntity<UserResponse> createUser(
			@Parameter(description = "Enter the details of new user to be created")
			@RequestBody @Valid UserResponse createRequest) {
		User userToCreate = customerMapper.responseToDomain(createRequest);
		UserResponse createdUser = customerMapper.domainToResponse(customerService.saveUser(userToCreate));
		return new ResponseEntity<UserResponse>(createdUser, HttpStatus.CREATED);
		
	}
	
	@Operation(summary = "Get details of a User",
            description = "Get details of a User with the User Id")
    @ApiResponse(responseCode = "200", description = "The User details has been retrieved successfully")
    @ApiResponse(responseCode = "400", description = "The request is malformed")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    @ApiResponse(responseCode = "403", description = "The user cannot access this resource")
    @ApiResponse(responseCode = "404", description = "Not found")
	@GetMapping(path = "{id}")
	public ResponseEntity<UserResponse> getUser(
			@Parameter(description = "Enter the Id of the user")
			@PathVariable Integer id) {
		UserResponse userRes = customerMapper.domainToResponse(customerService.getUserById(id));
		return new ResponseEntity<UserResponse>(userRes, HttpStatus.OK);
		
	}
	
	
	@Operation(summary = "Update details of a User",
            description = "Update details of a User with the User Id")
    @ApiResponse(responseCode = "204", description = "The User Details has been Updated successfully")
    @ApiResponse(responseCode = "400", description = "The request is malformed")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    @ApiResponse(responseCode = "403", description = "The user cannot access this resource")
    @ApiResponse(responseCode = "404", description = "Not found")
	@PatchMapping(path = "{id}")
	public ResponseEntity<UserResponse> updateUser(
			@Parameter(description = "Enter the Id of the user")
			@PathVariable Integer id,
			@Parameter(description = "Enter only the fields of user to be updated")
			@RequestBody @Valid UserResponse updateRequest) {
		
		User userUpdateReq = customerMapper.responseToDomain(updateRequest);
		UserResponse updatedUser = customerMapper.domainToResponse(customerService.updateUser(userUpdateReq, id));
		return new ResponseEntity<UserResponse>(updatedUser, HttpStatus.CREATED);
		
		
	}
	
	@Operation(summary = "Update details of a User",
            description = "Update details of a User with the User Id")
    @ApiResponse(responseCode = "204", description = "The User Details has been Updated successfully")
    @ApiResponse(responseCode = "400", description = "The request is malformed")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    @ApiResponse(responseCode = "403", description = "The user cannot access this resource")
    @ApiResponse(responseCode = "404", description = "Not found")
	@PutMapping(path = "{id}")
	public ResponseEntity<UserResponse> updateUserByPut(
			@Parameter(description = "Enter the Id of the user")
			@PathVariable Integer id,
			@Parameter(description = "Enter the full details of user to be Updated")
			@Valid @RequestBody UserResponse updateRequest) {
		
		System.out.println(updateRequest.getEmail() + " "+updateRequest.getName()+" "+updateRequest.getContactNum());
		User userUpdateReq = customerMapper.responseToDomain(updateRequest);
		System.out.println(userUpdateReq.getEmail() + " "+userUpdateReq.getName()+" "+userUpdateReq.getContactNum());
		userUpdateReq.setId(id);
		UserResponse updatedUser = customerMapper.domainToResponse(customerService.saveUser(userUpdateReq));
		return new ResponseEntity<UserResponse>(updatedUser, HttpStatus.CREATED);
		
		
	}
	
	@Operation(summary = "Delete a particular User",
            description = "Delete a particular User with the User Id")
    @ApiResponse(responseCode = "202", description = "The User has been deleted successfully")
    @ApiResponse(responseCode = "400", description = "The request is malformed")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    @ApiResponse(responseCode = "403", description = "The user cannot access this resource")
    @ApiResponse(responseCode = "404", description = "Not found")
	@DeleteMapping(path = "{id}")
	public ResponseEntity<String> deleteUser(
			@Parameter(description = "Enter the Id of the user")
			@PathVariable Integer id) {
		customerService.deleteUser(id);
		return new ResponseEntity<String>("User with "+id+ " is Successfully deleted", HttpStatus.ACCEPTED);
		
	}
	
	
	
	

}
