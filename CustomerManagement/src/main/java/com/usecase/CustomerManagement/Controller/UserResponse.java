package com.usecase.CustomerManagement.Controller;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	
	private Integer id;
	
	@Size(min = 1, max = 256, message = "Max length of name is 256")
	private String name;
	
	@Email(message = "Not a valid format for email")
	private String email;
	
	@Size(min=10,max=10, message = "Phone number doesn't have exactly 10 digits")
	@Pattern(regexp="(^$|[0-9]{10})", message = "format for mobile number is not valid")
	private String contactNum;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	

}
