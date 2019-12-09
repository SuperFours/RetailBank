package com.banking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.banking.constant.AppConstant;
import com.banking.dto.LoginDto;
import com.banking.dto.LoginResponseDto;
import com.banking.entity.User;
import com.banking.entity.UserAccount;
import com.banking.repository.UserAccountRepository;
import com.banking.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoginServiceImplTest {

	@InjectMocks
	LoginServiceImpl loginServiceImpl;

	@Mock
	UserRepository userRepository;

	@Mock
	UserAccountRepository userAccountRepository;

	LoginDto loginDto = new LoginDto();
	User user = new User();
	UserAccount userAccount = new UserAccount();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		loginDto.setUserName("8675958381");
		loginDto.setPassword("start@123");

		user.setId(1);
		user.setFirstName("Moorthy");

		userAccount.setId(1);
		userAccount.setUserId(user.getId());
		userAccount.setMinimumBalance(500.00);
	}

	@Test
	public void testLogin() {
		when(userRepository.findUserByUserNameAndPassword(loginDto.getUserName(), loginDto.getPassword()))
				.thenReturn(user);
		when(userAccountRepository.findByUserId(user.getId())).thenReturn(userAccount);
		LoginResponseDto response = loginServiceImpl.login(loginDto);
		assertEquals(AppConstant.SUCCESS, response.getStatus());
	}

	@Test
	public void testLoginForFailure() {
		when(userRepository.findUserByUserNameAndPassword(loginDto.getUserName(), loginDto.getPassword()))
				.thenReturn(null);
		LoginResponseDto response = loginServiceImpl.login(loginDto);
		assertEquals(AppConstant.FAILURE, response.getStatus());
	}

}
