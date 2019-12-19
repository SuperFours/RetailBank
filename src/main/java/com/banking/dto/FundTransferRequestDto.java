package com.banking.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * FundTransferRequestDto - In this dto's we can get the values for fund
 * transfer operations.
 * 
 * @author Govindasamy.C
 * @version V1.1
 * @created date - 04-12-2019
 */
@Getter
@Setter
public class FundTransferRequestDto {

	private Integer accountId;
	private String payeeAccountNumber;
	private Double transferAmount;
	private String remarks;

}