package com.banking.service;

import com.banking.dto.MortgageRequestDto;
import com.banking.dto.ResponseDto;

public interface MortgageService {
	

	ResponseDto createMortgageAccount(MortgageRequestDto mortgageRequestDto);

}
