package com.banking.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.constant.AppConstant;
import com.banking.dto.LoginDto;
import com.banking.dto.LoginResponseDto;
import com.banking.entity.User;
import com.banking.entity.UserAccount;
import com.banking.repository.UserAccountRepository;
import com.banking.repository.UserRepository;

/**
 * @description - Login Service impl class is used for to implement the login
 *              functionalities methods implemented. but, now we are implemented
 *              the user login purpose with userId and password.
 * 
 * @author Janani
 * @since 05-12-2019
 */
@Service
public class LoginServiceImpl implements LoginService {
	public static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserAccountRepository userAccountRepository;

	/**
	 * @description login method, the authoried user can able to login the
	 *              application based on the username and password.And also we are
	 *              handled the user login check sucess and failure cases.
	 * @return loginresponsedto with success and failure massages, if its success
	 *         means we can sent the userId and username for the purpose of session
	 *         values in UI.
	 */
	@Override
	public LoginResponseDto login(LoginDto loginDto) {
		logger.info("user login check....");
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		User user = userRepository.findUserByUserNameAndPassword(loginDto.getUserName(), loginDto.getPassword());
		if (user != null) {
			logger.debug("user login check within the repository call....");
			Optional<UserAccount> userAccount = userAccountRepository.findByUserIdAndAccountType(user.getId(),
					AppConstant.ACCOUNT_TYPE_SAVINGS);
			if (userAccount.isPresent()) {
				loginResponseDto.setAccountNumber(userAccount.get().getAccountNumber());
				loginResponseDto.setAccountType(userAccount.get().getAccountType());
				loginResponseDto.setAccountId(userAccount.get().getId());
				loginResponseDto.setUserName(user.getFirstName().concat(" ").concat(user.getLastName()));
			}
			loginResponseDto.setStatus(AppConstant.SUCCESS);
			loginResponseDto.setMessage(AppConstant.LOGIN_SUCCESS_MESSAGE);

		} else {
			loginResponseDto.setStatus(AppConstant.FAILURE);
			loginResponseDto.setMessage(AppConstant.LOGIN_ERROR_MESSAGE);
		}

		return loginResponseDto;
	}
}
