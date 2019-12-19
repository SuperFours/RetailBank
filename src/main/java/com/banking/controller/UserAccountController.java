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

import com.banking.constant.AppConstant;
import com.banking.dto.AccountBalanceDto;
import com.banking.dto.UserAccountDto;
import com.banking.dto.UserAccountResponseDto;
import com.banking.dto.ViewPayeeResponseDto;
import com.banking.service.UserAccountService;

import lombok.extern.slf4j.Slf4j;

/**
 * @description User Account Controller - User account controller -> get the all
 *              payee list, get the user account balances and search by the
 *              partial account number for all account numbers
 * 
 * @author Govindasamy.C
 * @since 05-12-2019
 *
 */
@RestController
@RequestMapping("/users/accounts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class UserAccountController {

	@Autowired
	private UserAccountService userAccountService;

	/**
	 * @description - get the payee list based on the user login account number.We
	 *              sent the accountId as pathvariable it will check all the
	 *              accounts.If it is payee accounts it will show the list to the
	 *              user.
	 * 
	 * @param user login accountId
	 * @return list of the viewpayee details through the viewpayeedto object.
	 */

	@GetMapping("/{userId}")
	public ResponseEntity<ViewPayeeResponseDto> getAllPayees(@PathVariable String userId) {
		log.info("getting all payees accounts");
		ViewPayeeResponseDto viewPayeeDtos = userAccountService.getAllPayees(userId);
		return new ResponseEntity<>(viewPayeeDtos, HttpStatus.OK);
	}

	/**
	 * @description get the user account balance by login user account.user can see
	 *              the available balance for their account.
	 * 
	 * @param accountId for login user account
	 * @return accountId and account balance through the accountBalanceDto.
	 */

	@GetMapping("/{accountId}/balances")
	public ResponseEntity<AccountBalanceDto> getUserAvailableBalance(@PathVariable Integer accountId) {
		log.info("getting user available balance  from the savings account");
		AccountBalanceDto accountDto = userAccountService.getAccountBalance(accountId);
		return new ResponseEntity<>(accountDto, HttpStatus.OK);
	}

	/**
	 * @description search by partial account numbers and get the list of all
	 *              accounts based on search value.
	 * @param accountNumber admin search input value.
	 * @return list of account details by UserAccountResponseDto object.
	 */
	@GetMapping("/search/{accountNumber}")
	public ResponseEntity<UserAccountResponseDto> searchSavingAccounts(@PathVariable String accountNumber) {
		log.info("searching savings accounts");
		UserAccountResponseDto userAccountResponseDto = new UserAccountResponseDto();
		List<UserAccountDto> userAccounts = userAccountService.getAccounts(accountNumber);
		userAccountResponseDto.setUserAccounts(userAccounts);
		if (userAccounts.isEmpty()) {
			userAccountResponseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
			userAccountResponseDto.setMessage(AppConstant.NO_RECORD_FOUND);
		} else {
			userAccountResponseDto.setStatusCode(HttpStatus.OK.value());
			userAccountResponseDto.setMessage(AppConstant.SUCCESS);
		}
		return new ResponseEntity<>(userAccountResponseDto, HttpStatus.OK);
	}

}
