package com.usecase.CustomerManagement.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

import com.usecase.CustomerManagement.domain.User;
import com.usecase.CustomerManagement.mapping.CustomerMapper;
import com.usecase.CustomerManagement.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "customer/")
public class CustomerManagementController {

    public static final Logger LOG = LoggerFactory.getLogger(CustomerManagementController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    /*@Autowired
    private RabbitTemplate rabbitTemplate;

    @Operation(summary = "Internal endpoint to receive data from Rabbit MQ",
            description = "Internal endpoint to receive data from Rabbit MQ")
    @RabbitListener(queues = "customer_res_queue")
    public void rabbitMQConsumer(@Valid String id) {
        LOG.info("Triggered rabbitMq() method in controller and Listening at RabbitMQ");
        LOG.info("Consuming created Id of User Request from RabbitMQ");
        System.out.println(id);

    }*/

    @Operation(summary = "Create a new Customer User",
            description = "Create a new Customer User with given User details")
    @ApiResponse(responseCode = "201", description = "The User has been created successfully")
    @ApiResponse(responseCode = "400", description = "The request is malformed")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    @ApiResponse(responseCode = "403", description = "The user cannot access this resource")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PostMapping(path = "/new")
    public ResponseEntity<String> createUser(
            @Parameter(description = "Enter the details of new user to be created")
            @RequestBody @Valid UserResponse createRequest) {
        LOG.info("Triggered rabbitMq() method in controller");
        System.out.println(createRequest.toString());
        User userToCreate = customerMapper.responseToDomain(createRequest);
        UserResponse createdUser = customerMapper.domainToResponse(customerService.saveUser(userToCreate));
        LOG.info("Returning Response in createUser() method in controller");
        return new ResponseEntity<String>(createdUser.getId().toString(), HttpStatus.CREATED);

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

        LOG.info("Triggered getUser() method in controller");

        UserResponse userRes = customerMapper.domainToResponse(customerService.getUserById(id));

        LOG.info("Returned Response in getUser() method in controller");
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

        LOG.info("Triggered updateUser() method in controller");
        User userUpdateReq = customerMapper.responseToDomain(updateRequest);
        UserResponse updatedUser = customerMapper.domainToResponse(customerService.updateUser(userUpdateReq, id));
        LOG.info("Returned Response in updateUser() method in controller");
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

        LOG.info("Triggered updateUserByPut() method in controller");
        User userUpdateReq = customerMapper.responseToDomain(updateRequest);
        userUpdateReq.setId(id);
        UserResponse updatedUser = customerMapper.domainToResponse(customerService.updateUserByPut(userUpdateReq));
        LOG.info("Returned Response in updateUserByPut() method in controller");
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
        LOG.info("Triggered deleteUser() method in controller");
        Integer deletedId = customerService.deleteUser(id);
        LOG.info("Returned Response in deleteUser() method in controller");
        return new ResponseEntity<String>("User with " + deletedId + " is Successfully deleted", HttpStatus.ACCEPTED);

    }


}
