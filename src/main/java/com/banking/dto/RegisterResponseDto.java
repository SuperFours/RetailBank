package com.banking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponseDto extends ResponseDto{

	private String userId;
	private String password;



}
