package com.usecase.CustomerManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "User")
@Table(name = "User")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;

	@NotNull
	@Size(min = 1, max = 256, message = "Max length of name is 256")
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotNull
	@Column(name = "email", nullable = false)
	private String email;
	
	@Size(min=10,max=10)
	@NotNull
	@Pattern(regexp="(^$|[0-9]{10})")
	@Column(name = "contactnum", nullable = false)
	private String contactNum;

}
