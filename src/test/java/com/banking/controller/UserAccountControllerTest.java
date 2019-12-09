package com.banking.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.banking.constant.AppConstant;
import com.banking.dto.AccountBalanceDto;
import com.banking.dto.ViewPayeeDto;
import com.banking.service.UserAccountService;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserAccountControllerTest {

	@InjectMocks
	UserAccountController userAccountController;

	@Mock
	UserAccountService userAccountService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllPayees() {
		List<ViewPayeeDto> viewPayeeDtos = new ArrayList<>();
		when(userAccountService.getAllPayees(1)).thenReturn(viewPayeeDtos);

		ResponseEntity<List<ViewPayeeDto>> response = userAccountController.getAllPayees(1);
		assertThat(response.getBody()).hasSize(0);
	}
	
	@Test
	public void testGetUserAvailableBalance() {
		AccountBalanceDto accountBalanceDto = new AccountBalanceDto();
		accountBalanceDto.setAccountBalance(1000.00);
		accountBalanceDto.setStatus(AppConstant.SUCCESS);
		when(userAccountService.getAccountBalance(1)).thenReturn(accountBalanceDto);
		
		ResponseEntity<AccountBalanceDto> response = userAccountController.getUserAvailableBalance(1);
		assertEquals(accountBalanceDto.getAccountBalance(), response.getBody().getAccountBalance());
	}

}
