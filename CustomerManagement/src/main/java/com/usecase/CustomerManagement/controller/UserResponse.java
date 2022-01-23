package com.usecase.CustomerManagement.controller;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {


    @Schema(description = "Output only variable. even if passed it is silently ignored")
    private Integer id;

    @Size(min = 1, max = 256, message = "Max length of name is 256")
    private String name;

    @Email(message = "Not a valid format for email")
    private String email;

    @Size(min = 10, max = 10, message = "Phone number doesn't have exactly 10 digits")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "format for mobile number is not valid")
    private String contactNum;

}
