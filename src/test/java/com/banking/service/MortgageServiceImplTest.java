package com.banking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.banking.constant.AppConstant;
import com.banking.dto.MortgageRequestDto;
import com.banking.dto.ResponseDto;
import com.banking.entity.User;
import com.banking.entity.UserAccount;
import com.banking.repository.UserAccountRepository;
import com.banking.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class MortgageServiceImplTest {

	@InjectMocks
	MortgageServiceImpl mortgageServiceImpl;

	@Mock
	UserAccountRepository userAccountRepository;
	
	@Mock
	UserRepository userRepository;

	MortgageRequestDto mortgageRequestDto = new MortgageRequestDto();
	UserAccount userAccount = new UserAccount();
    User user = new User();
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		mortgageRequestDto.setUserId(1);

		userAccount.setId(3);
		userAccount.setAccountType(AppConstant.ACCOUNT_TYPE_SAVINGS);
		userAccount.setAccountNumber(10687382732L);
		mortgageRequestDto.setPropertyValue(50000.00);
	}

	@Test
	public void testCreateMortgageAccount() {
		when(userAccountRepository.findByUserIdAndAccountType(mortgageRequestDto.getUserId(),
				AppConstant.ACCOUNT_TYPE_MORTGAGE)).thenReturn(Optional.of(userAccount));
		ResponseDto responseDto = mortgageServiceImpl.createMortgageAccount(mortgageRequestDto);
		assertEquals(AppConstant.FAILURE, responseDto.getMessage());
	}

	@Test
	public void testCreateMortgageAccountForSuccess() {
		user.setId(1);
		when(userAccountRepository.findByUserIdAndAccountType(mortgageRequestDto.getUserId(),
				AppConstant.ACCOUNT_TYPE_MORTGAGE)).thenReturn(Optional.ofNullable(null));
		when(userRepository.findById(mortgageRequestDto.getUserId())).thenReturn(Optional.of(user));
		ResponseDto responseDto = mortgageServiceImpl.createMortgageAccount(mortgageRequestDto);
		assertEquals(AppConstant.SUCCESS, responseDto.getMessage());
	}

}
