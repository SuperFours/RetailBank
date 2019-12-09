package com.banking.service;

import com.banking.dto.LoginDto;
import com.banking.dto.LoginResponseDto;

@FunctionalInterface
public interface LoginService {

	public LoginResponseDto login(LoginDto userLoginDto);

}
