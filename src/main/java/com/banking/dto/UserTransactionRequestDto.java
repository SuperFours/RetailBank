package com.banking.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * @author  AKUTHOTA.RAGHU 
 * @version 1.0
 * @since   2019-12-05
 * Here, Constants - UserTransactionRequestDto for input values
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserTransactionRequestDto {

	private String payeeName;
	private Long payeeAccountNumber;
	private String transactionType;
	private LocalDate transactionDate;
	private Double balanceAmount;
	private Double transactionAmount;
	private String remarks;

	
}
