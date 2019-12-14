package com.banking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.banking.constant.AppConstant;
import com.banking.dto.AccountBalanceDto;
import com.banking.dto.UserAccountDto;
import com.banking.dto.UserAccountResponseDto;
import com.banking.dto.ViewPayeeDto;
import com.banking.entity.User;
import com.banking.entity.UserAccount;
import com.banking.repository.UserAccountRepository;
import com.banking.repository.UserRepository;
import com.banking.util.ConverterUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * UserAccountServiceImpl class - we can implementing the user account service
 * methods.
 * 
 * @author Govindasamy.C
 * @since 05-12-2019
 */
@Service
@Slf4j
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<ViewPayeeDto> getAllPayees(Integer accountId) {
		List<ViewPayeeDto> viewPayeeDtos = new ArrayList<>();
		List<UserAccount> userAccounts = userAccountRepository.findAllByIdNot(accountId);
		userAccounts.forEach(userAccount -> {
			Optional<User> user = userRepository.findById(userAccount.getUserId());
			if (user.isPresent()) {
				viewPayeeDtos.add(ConverterUtil.convertTransactionToPayeeDto(userAccount, user.get()));
			}
		});
		return viewPayeeDtos;
	}

	/**
	 * get the user account balances.
	 */
	@Override
	public AccountBalanceDto getAccountBalance(Integer userAccountId) {
		AccountBalanceDto accountBalanceDto = new AccountBalanceDto();

		Optional<UserAccount> userAccount = userAccountRepository.findById(userAccountId);
		if (userAccount.isPresent()) {
			accountBalanceDto.setAccountBalance(userAccount.get().getBalanceAmount());

			accountBalanceDto.setStatusCode(HttpStatus.OK.value());
			accountBalanceDto.setMessage(AppConstant.SUCCESS);
			accountBalanceDto.setStatus(AppConstant.SUCCESS);
		} else {
			accountBalanceDto.setStatusCode(HttpStatus.OK.value());
			accountBalanceDto.setStatus(AppConstant.USER_NOT_FOUND);
		}
		return accountBalanceDto;
	}
	
	

	@Override
	public List<UserAccountDto> getAccounts(String accountNumber) {
		List<UserAccountDto> userAccountDto = new ArrayList<>();
		List<UserAccount> userAccounts = userAccountRepository.findAllByAccountNumber(accountNumber);
		userAccountDto = userAccounts.stream()
				.filter(account -> !account.getAccountType().equalsIgnoreCase(AppConstant.MORTGAGE))
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
		return userAccountDto;
	}
	
	/**
	 * 
	 * @param userAccount
	 * @return
	 */
	private UserAccountDto convertEntityToDto(UserAccount userAccount) {
		log.info("converting UserAccount to UserAccountResponseDto" );
		UserAccountDto userAccountDto=new UserAccountDto();
		BeanUtils.copyProperties(userAccount, userAccountDto);
		Optional<User> user = userRepository.findById(userAccount.getUserId());
		if(user.isPresent()) {
			userAccountDto.setUserName(user.get().getFirstName().concat(" ").concat(user.get().getLastName()));
		}
		return userAccountDto;
		
	}

}
