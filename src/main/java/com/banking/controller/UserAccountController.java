package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dto.AccountBalanceDto;
import com.banking.dto.ViewPayeeDto;
import com.banking.service.UserAccountService;

/**
 * User Account Controller - User account controller -> get the all payee list
 * and get the user account balances.
 * 
 * @author Govindasamy.C
 * @since 05-12-2019
 *
 */
@RestController
@RequestMapping("/users/accounts")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class UserAccountController {

	@Autowired
	private UserAccountService userAccountService;

	/**
	 * get the payee list
	 * @param accountId
	 * @return
	 */
	@GetMapping("/{accountId}")
	public ResponseEntity<List<ViewPayeeDto>> getAllPayees(@PathVariable Integer accountId) {
		List<ViewPayeeDto> viewPayeeDtos = userAccountService.getAllPayees(accountId);
		return new ResponseEntity<>(viewPayeeDtos, HttpStatus.OK);
	}

	/**
	 * get the user account balances.
	 * @param accountId
	 * @return
	 */
	@GetMapping("/{accountId}/balances")
	public ResponseEntity<AccountBalanceDto> getUserAvailableBalance(@PathVariable Integer accountId) {
		AccountBalanceDto accountDto = userAccountService.getAccountBalance(accountId);
		return new ResponseEntity<>(accountDto, HttpStatus.OK);
	}

}
