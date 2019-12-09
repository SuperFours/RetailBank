package com.banking.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDto extends ResponseDto{

	private Integer accountId;
	private Long accountNumber;
	private String accountType;

	private String userName;

	
}
