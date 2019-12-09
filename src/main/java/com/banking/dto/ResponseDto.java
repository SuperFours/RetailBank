package com.banking.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * FundTransferResponseDto - In this response dto's, we can set the response
 * details of the api. either success/failure values
 * 
 * @author Govindasamy.C
 * @version V1.1
 * @created date - 04-12-2019
 */
@Getter
@Setter
public class ResponseDto {

	private String status;
	private Integer statusCode;
	private String message;

	
}
