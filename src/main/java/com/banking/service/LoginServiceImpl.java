package com.banking.service;

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
 * This is the loginServiceImpl that has 1 method.
 * 
 * @author Janani
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserAccountRepository userAccountRepository;

	/**
	 * This is the login method that used to login the application.
	 */
	@Override
	public LoginResponseDto login(LoginDto loginDto) {
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		User user = userRepository.findUserByUserNameAndPassword(loginDto.getUserName(), loginDto.getPassword());
		if (user != null) {
			UserAccount userAccount = userAccountRepository.findByUserId(user.getId());
			loginResponseDto.setAccountNumber(userAccount.getAccountNumber());
			loginResponseDto.setAccountType(userAccount.getAccountType());
			loginResponseDto.setAccountId(userAccount.getId());
			loginResponseDto.setUserName(user.getFirstName() + " " + user.getLastName());
			loginResponseDto.setStatus(AppConstant.SUCCESS);
			loginResponseDto.setMessage(AppConstant.LOGIN_SUCCESS_MESSAGE);

		} else {
			loginResponseDto.setStatus(AppConstant.FAILURE);
			loginResponseDto.setMessage(AppConstant.LOGIN_ERROR_MESSAGE);
		}

		return loginResponseDto;
	}
}
