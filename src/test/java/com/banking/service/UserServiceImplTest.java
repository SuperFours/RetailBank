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
import com.banking.dto.RegisterRequestDto;
import com.banking.dto.RegisterResponseDto;
import com.banking.entity.User;
import com.banking.exception.UserAlreadyExist;
import com.banking.repository.UserAccountRepository;
import com.banking.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;

	@Mock
	UserAccountRepository userAccountRepository;

	RegisterRequestDto requestDto = new RegisterRequestDto();
	User user = new User();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		requestDto.setPanNumber("PG672H6726");
		requestDto.setAddress1("Cbe");
		requestDto.setAddress2("Gandhipuram");
		requestDto.setDob("1992-04-23");
		requestDto.setFirstName("Moorthy");
		requestDto.setLastName("Govindasamy");
		requestDto.setPhone("8675958381");
		requestDto.setPinCode(641666);

		user.setId(1);
		user.setFirstName("Moorthy");
	}

	@Test
	public void testRegisterUser() {
		when(userRepository.findUserByPhone(requestDto.getPhone())).thenReturn(null);

		RegisterResponseDto response = userServiceImpl.registerUser(requestDto);
		assertEquals(AppConstant.SUCCESS, response.getStatus());
	}

	@Test(expected = UserAlreadyExist.class)
	public void testRegisterUserForUserExists() {
		when(userRepository.findUserByPhone(requestDto.getPhone())).thenReturn(user);

		RegisterResponseDto response = userServiceImpl.registerUser(requestDto);
		assertEquals(AppConstant.FAILURE, response.getStatus());
	}

}
