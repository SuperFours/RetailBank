package com.banking.service;

import com.banking.dto.RegisterRequestDto;
import com.banking.dto.RegisterResponseDto;

@FunctionalInterface
public interface UserService {

	public RegisterResponseDto registerUser(RegisterRequestDto userRegisterDto);

}
