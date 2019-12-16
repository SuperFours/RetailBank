package com.banking.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.banking.constant.AppConstant;
import com.banking.dto.MortgageRequestDto;
import com.banking.dto.ResponseDto;
import com.banking.entity.User;
import com.banking.entity.UserAccount;
import com.banking.repository.UserAccountRepository;
import com.banking.repository.UserRepository;

/**
 * @description This MortgageServiceImpl is used to implement the logic to
 *              create mortgage account for the requested users if and only if
 *              user contain already contains savings account
 * @author akuthota.raghu
 * 
 */

@Service
public class MortgageServiceImpl implements MortgageService {

	public static final Logger LOGGER = LoggerFactory.getLogger(MortgageServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserAccountRepository userAccountRepository;

	/**
	 * This method will create the mortgage accounts
	 * 
	 * @param MortgageRequestDto object contains set of properties
	 * @return MortgageResponseDto object contains set of properties
	 */
	@Override
	public ResponseDto createMortgageAccount(MortgageRequestDto mortgageRequestDto) {
		LOGGER.info("Creating mortgage account start for requested user");
		ResponseDto responseDto = new ResponseDto();
		Optional<UserAccount> userAccount = userAccountRepository
				.findByUserIdAndAccountType(mortgageRequestDto.getUserId(), AppConstant.ACCOUNT_TYPE_MORTGAGE);
		if (!userAccount.isPresent()) {

			UserAccount mortgageAccount = new UserAccount();
			Optional<User> user = userRepository.findById(mortgageRequestDto.getUserId());
			if (user.isPresent()) {
				mortgageAccount.setUserId(user.get().getId());
			}

			Long accountNumber = generateAccountNumber();
			mortgageAccount.setAccountNumber(accountNumber);
			mortgageAccount.setAccountType(AppConstant.ACCOUNT_TYPE_MORTGAGE);
			mortgageAccount.setBalanceAmount(-mortgageRequestDto.getPropertyValue());
			mortgageAccount.setCreatedDate(LocalDateTime.now());

			userAccountRepository.save(mortgageAccount);

			LOGGER.info("Created mortgage account for the requested user");

			responseDto.setMessage(AppConstant.SUCCESS);
			responseDto.setStatus(AppConstant.MORTGAGE_ACCOUNT_CREATED);
		} else {
			responseDto.setMessage(AppConstant.FAILURE);
			responseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseDto.setStatus(AppConstant.USER_MORTGAGE_ACCOUNT_EXIST);
			LOGGER.info("User Mortgage Account is already exist ");
		}
		return responseDto;
	}

	/**
	 * @description get the generated account number.
	 * @return Long value of account number
	 */
	private Long generateAccountNumber() {
		String number = RandomStringUtils.random(16, false, true);
		return Long.valueOf(number);
	}
}
