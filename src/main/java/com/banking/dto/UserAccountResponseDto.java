package com.banking.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccountResponseDto {
	
	private Integer statusCode;
	private String message;
	
	List<UserAccountDto> userAccounts;


}
