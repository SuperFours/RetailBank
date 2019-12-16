package com.banking.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.banking.constant.AppConstant;
import com.banking.dto.AccountBalanceDto;
import com.banking.dto.UserAccountDto;
import com.banking.dto.ViewPayeeDto;
import com.banking.entity.User;
import com.banking.entity.UserAccount;
import com.banking.repository.UserAccountRepository;
import com.banking.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserAccountServiceImplTest {

	@InjectMocks
	UserAccountServiceImpl userAccountServiceImpl;

	@Mock
	UserRepository userRepository;

	@Mock
	UserAccountRepository userAccountRepository;

	User user = new User();
	UserAccount userAccount = new UserAccount();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		user.setId(1);

		userAccount.setId(3);
		userAccount.setAccountType(AppConstant.ACCOUNT_TYPE_SAVINGS);
		userAccount.setAccountNumber(10687382732L);
	}

	@Test
	public void testGetAllPayees() {
		List<UserAccount> userAccounts = new ArrayList<>();
		userAccounts.add(userAccount);

		when(userAccountRepository.findAllByIdNot(2)).thenReturn(userAccounts);
		when(userRepository.findById(userAccount.getUserId())).thenReturn(Optional.of(user));

		List<ViewPayeeDto> viewPayees = userAccountServiceImpl.getAllPayees(2);
		assertThat(viewPayees).hasSize(1);
	}

	@Test
	public void testGetAccountBalance() {
		when(userAccountRepository.findById(1)).thenReturn(Optional.of(userAccount));

		AccountBalanceDto response = userAccountServiceImpl.getAccountBalance(1);
		assertEquals(AppConstant.SUCCESS, response.getStatus());
	}

	@Test
	public void testGetAccounts() {
		List<UserAccount> userAccounts = new ArrayList<>();
		userAccounts.add(userAccount);
		when(userAccountRepository.findAllByAccountNumber("653625632")).thenReturn(userAccounts);
		List<UserAccountDto> response = userAccountServiceImpl.getAccounts("653625632");
		assertThat(response).hasSize(1);
	}

}
