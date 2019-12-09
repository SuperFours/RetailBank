package com.banking.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.banking.constant.AppConstant;

import lombok.Getter;
import lombok.Setter;

/**
 * * The RegisterRequestDto class is a Dto class that has 11 fields
 * 
 * @author Janani
 * @since 2019-12-5
 *
 */
@Getter
@Setter
public class RegisterRequestDto {

	@NotBlank(message = AppConstant.FIRST_NAME_ERROR_MESSAGE)
	private String firstName;

	private String lastName;

	/*
	 * @Pattern(regexp = "yyyy-MM-dd", message = AppConstant.DOB_INVALID)
	 */ private String dob;

	@Email(message = AppConstant.EMAIL_ADDRESS_ERROR_MESSAGE)
	private String emailAddress;

	@NotBlank(message = AppConstant.MOBILENUMBER_ERROR_MESSAGE)
	@Pattern(regexp = "(^$|[0-9]{10})", message = AppConstant.MOBILE_INVALID_MESSAGE)
	private String phone;

	@NotBlank(message = AppConstant.ADDRESS_ERROR_MESSAGE)
	private String address1;

	private String address2;

	private Integer pinCode;

	private String panNumber;

	
}
