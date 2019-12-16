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
 * another object of transaction
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
	 * @param fundTransferRequestDto params values are accountId and payeeAccountId
	 *                               with transaction amount.
	 * @return user transaction object values.
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

	/**
	 * @description convert the user account and user values to viewpayeedto
	 *              details.
	 * @param userAccount entity values.
	 * @param user        entity object values.
	 * @return viewpayeedto values are set the accountType, accountId, accountnumber
	 *         and payeename.
	 */
	public static ViewPayeeDto convertTransactionToPayeeDto(UserAccount userAccount, User user) {
		ViewPayeeDto viewPayeeDto = new ViewPayeeDto();
		viewPayeeDto.setAccountType(userAccount.getAccountType());
		viewPayeeDto.setAccountId(userAccount.getId());
		viewPayeeDto.setAccountNumber(userAccount.getAccountNumber());
		viewPayeeDto.setPayeeName(user.getFirstName());
		return viewPayeeDto;
	}
}
