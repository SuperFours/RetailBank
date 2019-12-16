package com.banking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.banking.constant.AppConstant;
import com.banking.dto.MortgageRequestDto;
import com.banking.dto.ResponseDto;
import com.banking.service.MortgageService;

@RunWith(SpringJUnit4ClassRunner.class)
public class MortgageControllerTest {

	@InjectMocks
	MortgageController mortgageController;

	@Mock
	MortgageService mortgageService;
	
	MortgageRequestDto mortgageRequestDto = new MortgageRequestDto();
	ResponseDto responseDto = new ResponseDto();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		mortgageRequestDto.setAccountType(AppConstant.ACCOUNT_TYPE_MORTGAGE);
		mortgageRequestDto.setPropertyValue(500000.00);
	}
	
	@Test
	public void testCreateMortgageaccount() {
		responseDto.setMessage(AppConstant.MORTGAGE_ACCOUNT_CREATED);
		when(mortgageService.createMortgageAccount(mortgageRequestDto)).thenReturn(responseDto);
		ResponseEntity<ResponseDto> response = mortgageController.createMortgageaccount(mortgageRequestDto);
		assertEquals(AppConstant.MORTGAGE_ACCOUNT_CREATED, response.getBody().getMessage());
	}

}
