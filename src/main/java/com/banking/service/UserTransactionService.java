package com.banking.service;

import com.banking.dto.FundTransferRequestDto;
import com.banking.dto.ResponseDto;
import com.banking.dto.UserTransactionResponseDto;

import javassist.NotFoundException;

public interface UserTransactionService {

	public ResponseDto fundTransfer(FundTransferRequestDto fundTransferRequestDto) throws NotFoundException;
	
	public UserTransactionResponseDto findUserTransactionsByMonth(Integer userAccountId, Integer month, Integer year);

	public UserTransactionResponseDto findRecentFiveTransactions(Integer userAccountId);
	
	public UserTransactionResponseDto findMortgageTransactions(Integer userAccountId);
}
