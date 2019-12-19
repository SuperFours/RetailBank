package com.banking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.banking.constant.AppConstant;
import com.banking.dto.FundTransferRequestDto;
import com.banking.dto.ResponseDto;
import com.banking.dto.UserTransactionResponseDto;
import com.banking.entity.UserAccount;
import com.banking.entity.UserTransaction;
import com.banking.service.UserTransactionService;

import javassist.NotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserTransactionControllerTest {

	@InjectMocks
	UserTransactionController userTransactionController;

	@Mock
	UserTransactionService userTransactionService;

	FundTransferRequestDto fundTransferRequestDto = new FundTransferRequestDto();
	ResponseDto fundTransferResponseDto = new ResponseDto();

	UserTransaction userTransaction = new UserTransaction();
	UserTransactionResponseDto userTransactionResponseDto = new UserTransactionResponseDto();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		fundTransferRequestDto.setAccountId(1);
		fundTransferRequestDto.setTransferAmount(2000.00);
		fundTransferRequestDto.setRemarks("For Hospital Expenses");

		UserAccount userAccount = new UserAccount();
		userAccount.setId(123123);

		userTransaction.setTransactionAmount(2500.00);
		userTransaction.setId(1231231);
		userTransaction.setTransactionDate(LocalDate.of(2019, 12, 04));
		userTransaction.setTransactionType("SAVING");

	}

	@Test
	public void testfindRecentFiveTransactions() throws NotFoundException {
		userTransactionResponseDto.setMessage(AppConstant.OPERATION_SUCCESS);
		userTransactionResponseDto.setStatusCode(200);

		when(userTransactionService.findRecentFiveTransactions(userTransaction.getId()))
				.thenReturn(userTransactionResponseDto);

		UserTransactionResponseDto response = userTransactionController
				.getRecentFiveTransactions(userTransaction.getId());
		assertEquals("Transaction Details", response.getMessage());
		assertEquals(200, response.getStatusCode());
	}

	@Test
	public void testfindRecentFiveTransactionsForFailure() throws NotFoundException {
		userTransactionResponseDto.setMessage(AppConstant.OPERATION_FAILD);
		userTransactionResponseDto.setStatusCode(400);

		when(userTransactionService.findRecentFiveTransactions(userTransaction.getId()))
				.thenReturn(userTransactionResponseDto);

		UserTransactionResponseDto response = userTransactionController
				.getRecentFiveTransactions(userTransaction.getId());
		assertEquals("Operation Failed", response.getMessage());
		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void testFindUserTransactionsByMonth() throws NotFoundException {
		userTransactionResponseDto.setMessage(AppConstant.OPERATION_SUCCESS);
		userTransactionResponseDto.setStatusCode(200);

		when(userTransactionService.findUserTransactionsByMonth(userTransaction.getId(), 12, 2019))
				.thenReturn(userTransactionResponseDto);

		UserTransactionResponseDto response = userTransactionController
				.getUserTransactionsByMonth(userTransaction.getId(), 12, 2019);
		assertEquals("Transaction Details", response.getMessage());
		assertEquals(200, response.getStatusCode());
	}

	@Test
	public void testFindUserTransactionsByMonthForFailure() throws NotFoundException {
		userTransactionResponseDto.setMessage(AppConstant.OPERATION_FAILD);
		userTransactionResponseDto.setStatusCode(400);

		when(userTransactionService.findUserTransactionsByMonth(userTransaction.getId(), 12, 2019))
				.thenReturn(userTransactionResponseDto);

		UserTransactionResponseDto response = userTransactionController
				.getUserTransactionsByMonth(userTransaction.getId(), 12, 2019);
		assertEquals("Operation Failed", response.getMessage());
		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void testFundTransfer() throws NotFoundException {
		fundTransferResponseDto.setStatus(AppConstant.SUCCESS);
		fundTransferResponseDto.setMessage(AppConstant.FUND_TRANSFER_SUCCESS);
		fundTransferResponseDto.setStatusCode(200);

		when(userTransactionService.fundTransfer(fundTransferRequestDto)).thenReturn(fundTransferResponseDto);

		ResponseEntity<ResponseDto> response = userTransactionController.fundTransfer(fundTransferRequestDto);
		assertEquals("SUCCESS", response.getBody().getStatus());
		assertEquals(200, response.getBody().getStatusCode());
	}

	@Test
	public void testFundTransferForFailure() throws NotFoundException {
		fundTransferResponseDto.setStatus(AppConstant.FAILURE);
		fundTransferResponseDto.setMessage(AppConstant.FUND_TRANSFER_ERROR);
		fundTransferResponseDto.setStatusCode(400);

		when(userTransactionService.fundTransfer(fundTransferRequestDto)).thenReturn(fundTransferResponseDto);

		ResponseEntity<ResponseDto> response = userTransactionController.fundTransfer(fundTransferRequestDto);
		assertEquals("FAILURE", response.getBody().getStatus());
		assertEquals(400, response.getBody().getStatusCode());
	}

	@Test
	public void testFundTransferForBadRequest() throws NotFoundException {
		when(userTransactionService.fundTransfer(fundTransferRequestDto)).thenReturn(new ResponseDto());

		ResponseEntity<ResponseDto> response = userTransactionController.fundTransfer(fundTransferRequestDto);
		assertEquals("FAILURE", response.getBody().getStatus());
		assertEquals(400, response.getBody().getStatusCode());
	}
}
