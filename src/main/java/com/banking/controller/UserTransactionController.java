package com.banking.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.constant.AppConstant;
import com.banking.dto.FundTransferRequestDto;
import com.banking.dto.ResponseDto;
import com.banking.dto.UserTransactionResponseDto;
import com.banking.service.UserTransactionService;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * @description UserTransaction Controller - we can implement the banking
 *              operations like fund transfer, getRecentFiveTransactions for the
 *              savings accounts,getMortgageTransactions for the mortgage
 *              account monthly wise and getUserTransactionsByMonth for the
 *              savings and mortgage accounts.
 * 
 * @author Govindasamy.C
 * @version V1.1
 * @created date - 04-12-2019
 */
@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class UserTransactionController {

	@Autowired
	UserTransactionService userTransactionService;

	/**
	 * @description -> In this method, we are implementing the fund transfer
	 *              operations.
	 * 
	 * @param fundTransferRequestDto -> getting input params -> accountId,
	 *                               payeeAccountId, transferAmount & remarks.
	 * @return ResponseDto -> status, statusCode and message for success/failure
	 *         cases.
	 * @throws NotFoundException
	 */
	@PostMapping
	public ResponseEntity<ResponseDto> fundTransfer(@Valid @RequestBody FundTransferRequestDto fundTransferRequestDto)
			throws NotFoundException {
		log.info("fund transaction ");
		ResponseDto fundTransferResponseDto = userTransactionService.fundTransfer(fundTransferRequestDto);
		// Check the response status is success or not.
		Optional<String> isSuccess = Optional.ofNullable(fundTransferResponseDto.getStatus());
		if (isSuccess.isPresent()) {
			if (fundTransferResponseDto.getStatus().equals(AppConstant.SUCCESS)) {
				fundTransferResponseDto.setStatusCode(HttpStatus.OK.value());
			} else {
				fundTransferResponseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
			}
		} else {
			fundTransferResponseDto.setStatus(AppConstant.FAILURE);
			fundTransferResponseDto.setMessage(AppConstant.FUND_TRANSFER_ERROR);
			fundTransferResponseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
		}
		return new ResponseEntity<>(fundTransferResponseDto, HttpStatus.OK);
	}

	/**
	 * @description In this method, we are getting recent Five transaction for the
	 *              savings accounts.UserAccountId has sent as pathvariable to
	 *              search for the same accounts.
	 * @param userAccountId
	 * @return UserTransactionResponseDto it has amount,account type and user
	 *         details.
	 */

	@GetMapping("/{userAccountId}")
	public UserTransactionResponseDto getRecentFiveTransactions(@PathVariable Integer userAccountId) {
		log.info("getting recent 5 transactions for the savings accounts");
		return userTransactionService.findRecentFiveTransactions(userAccountId);
	}

	/**
	 * @description getMortgageTransactions method used to user can see mortgage
	 *              transactions to the mortgage account in that user can see the
	 *              account type,date and amount.
	 * @param userAccountId sent as path variable.
	 * @return UserTransactionResponseDto class it returing transaction type,amount
	 *         and account type.
	 */

	@GetMapping("mortgageaccounts/{userAccountId}")
	public UserTransactionResponseDto getMortgageTransactions(@PathVariable Integer userAccountId) {
		log.info("getting mortgage transactions");
		return userTransactionService.findMortgageTransactions(userAccountId);
	}

	/**
	 * @description This method is used for to get recent 5 transactions for the
	 *              savings accounts ,onthly and year wise we implemented.we can see
	 *              monthly and year wise last 5 tranactions.
	 * 
	 * @param : Integer userAccountId sent as the pathvariable.
	 * 
	 * @return : UserTransactionResponseDto .we returned this dtoin that user can
	 *         see the each tarnsactions by monthly wise for the accounts
	 * 
	 * @throws : NoResultException for the ecception case.
	 */
	@GetMapping("/users/{userAccountId}")
	public UserTransactionResponseDto getUserTransactionsByMonth(@PathVariable Integer userAccountId,
			@RequestParam("month") Integer month, @RequestParam("year") Integer year) {
		log.info("getting user transaction by monthly wise");
		return userTransactionService.findUserTransactionsByMonth(userAccountId, month, year);
	}
}
