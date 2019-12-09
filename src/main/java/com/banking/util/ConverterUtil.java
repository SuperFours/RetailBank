package com.banking.util;

import java.time.LocalDate;

import com.banking.constant.AppConstant;
import com.banking.dto.FundTransferRequestDto;
import com.banking.dto.ViewPayeeDto;
import com.banking.entity.User;
import com.banking.entity.UserAccount;
import com.banking.entity.UserTransaction;

/**
 * ConverterUtil - ConverterUtil mainly used for converting the objects to
 * another object
 * 
 * @author Govindasamy.C
 * @since - 05-12-2019
 */

public class ConverterUtil {

	private ConverterUtil() {
	}

	/**
	 * convert the FundTransferRequestDto values to UserTransaction entity.
	 * 
	 * @param fundTransferRequestDto
	 * @return UserTransaction object
	 */
	public static UserTransaction convertDtoToTransactionEntity(FundTransferRequestDto fundTransferRequestDto) {
		UserTransaction userTransaction = new UserTransaction();
		userTransaction.setTransactionDate(LocalDate.now());
		userTransaction.setTransactionAmount(fundTransferRequestDto.getTransferAmount());
		userTransaction.setTransactionType(AppConstant.TRANSACTION_TYPE);
		userTransaction.setCurrentBalanceAmount(0.00);
		userTransaction.setRemarks(fundTransferRequestDto.getRemarks());
		return userTransaction;
	}

	public static ViewPayeeDto convertTransactionToPayeeDto(UserAccount userAccount, User user) {
		ViewPayeeDto viewPayeeDto = new ViewPayeeDto();
		viewPayeeDto.setAccountId(userAccount.getId());
		viewPayeeDto.setAccountNumber(userAccount.getAccountNumber());
		viewPayeeDto.setPayeeName(user.getFirstName() + " " + user.getLastName());
		return viewPayeeDto;
	}
}
