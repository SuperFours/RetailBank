package com.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.constant.AppConstant;
import com.banking.dto.LoginDto;
import com.banking.dto.LoginResponseDto;
import com.banking.service.*;

/**
 * This is the login controller that controllers the login service.
 * 
 * @author Janani
 * @since 06/12/2019
 */
@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

	@Autowired
	LoginService loginService;

	/**
	 * This is the login user method,it is used to login the application
	 * 
	 * @param userLoginDto
	 * @return
	 */

	@PostMapping
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
		LoginResponseDto responseDto = loginService.login(loginDto);
		if (responseDto.getStatus().equals(AppConstant.SUCCESS)) {
			responseDto.setStatusCode(HttpStatus.OK.value());
		} else {
			responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
		}

		return new ResponseEntity<>(responseDto, HttpStatus.OK);

	}

}
