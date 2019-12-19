package com.banking.service;

import java.util.List;

import com.banking.dto.AccountBalanceDto;
import com.banking.dto.UserAccountDto;
import com.banking.dto.ViewPayeeResponseDto;

public interface UserAccountService {

	public ViewPayeeResponseDto getAllPayees(String userId);

	public AccountBalanceDto getAccountBalance(Integer userAccountId);
	
	public List<UserAccountDto> getAccounts(String accountNumber);

}
