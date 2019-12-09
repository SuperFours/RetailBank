package com.banking.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description : This the user entity class ,it has 12 fields
 * 
 * @author Janani
 * @since 2019-12-05
 *
 */
@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String firstName;
	private String lastName;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dob;
	private String emailAddress;
	private String phone;
	@Column(name = "address_1")
	private String address1;
	@Column(name = "address_2")
	private String address2;
	private Integer pinCode;
	private String panNumber;
	private String userName;
	private String password;

}
