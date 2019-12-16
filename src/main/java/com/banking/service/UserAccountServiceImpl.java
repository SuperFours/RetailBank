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
import com.banking.dto.ViewPayeeDto;
import com.banking.entity.User;
import com.banking.entity.UserAccount;
import com.banking.repository.UserAccountRepository;
import com.banking.repository.UserRepository;
import com.banking.util.ConverterUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @description UserAccountServiceImpl class - we can implementing the user
 *              account service methods of get all payee details, get account
 *              balance based on accountId, get all accounts by admin search
 *              value.
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

	/**
	 * @description - get the payee list based on the user login account number.
	 * 
	 * @param user login accountId
	 * @return list of the viewpayee details through the viewpayeedto object.
	 */
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
	 * @description get the user account balance by login user account.
	 * 
	 * @param accountId for login user account
	 * @return accountId and account balance through the accountBalanceDto.
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

	/**
	 * @description search by partial account numbers and get the list of all
	 *              accounts based on search value.
	 * @param accountNumber admin search input value.
	 * @return list of account details by UserAccountResponseDto object.
	 */
	@Override
	public List<UserAccountDto> getAccounts(String accountNumber) {
		List<UserAccount> userAccounts = userAccountRepository.findAllByAccountNumber(accountNumber);
		return userAccounts.stream()
				.filter(account -> !account.getAccountType().equalsIgnoreCase(AppConstant.ACCOUNT_TYPE_MORTGAGE))
				.map(this::convertEntityToDto).collect(Collectors.toList());
	}

	/**
	 * @description convert the useraccount entity values to useraccountDto object
	 *              values based on the response.
	 * @param userAccount params of useraccount entity
	 * @return
	 */
	private UserAccountDto convertEntityToDto(UserAccount userAccount) {
		log.info("converting UserAccount to UserAccountResponseDto");
		UserAccountDto userAccountDto = new UserAccountDto();
		BeanUtils.copyProperties(userAccount, userAccountDto);
		Optional<User> user = userRepository.findById(userAccount.getUserId());
		if (user.isPresent()) {
			userAccountDto.setUserId(user.get().getId());
			userAccountDto.setUserName(user.get().getFirstName().concat(" ").concat(user.get().getLastName()));
		}
		return userAccountDto;

	}

}
