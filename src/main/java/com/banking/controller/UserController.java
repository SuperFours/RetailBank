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
 * This is the User controller it has 1 method that relate to the user.
 * 
 * @author Janani
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
	 * To register the user.
	 * 
	 * @param userRegisterDto
	 * @return
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
