package com.banking.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.constant.AppConstant;
import com.banking.dto.RegisterRequestDto;
import com.banking.dto.RegisterResponseDto;
import com.banking.service.UserService;

/**
 * @description This is the User controller in that user registeration is
 *              implemented to register the user to the retail bank
 *              application.It will allow the user can register the application.
 * 
 * @author Janani.V
 * @since 05-12-2019
 */

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserTransactionController.class);

	@Autowired
	UserService userService;

	/**
	 * @description Register user method is used for register the user to start the
	 *              application . In that we used RegisterRequestDto as requestbody
	 *              it having fields to enough for the registeration purpose.All
	 *              field was valid with the annotation called @valid.
	 * 
	 * @param userRegisterDto class having fields for the register user.
	 * @return RegisterResponseDto it has status message and status code for the
	 *         register method.if it is not correct we will sent bad request to the
	 *         user.
	 */
	@PostMapping
	public ResponseEntity<RegisterResponseDto> registerUser(@Valid @RequestBody RegisterRequestDto userRegisterDto) {
		logger.info("User registration");
		RegisterResponseDto registerResponseDto = userService.registerUser(userRegisterDto);
		if (registerResponseDto.getStatus().equals(AppConstant.SUCCESS)) {
			registerResponseDto.setStatusCode(HttpStatus.OK.value());
		} else {
			registerResponseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
		}

		return new ResponseEntity<>(registerResponseDto, HttpStatus.OK);
	}
}
