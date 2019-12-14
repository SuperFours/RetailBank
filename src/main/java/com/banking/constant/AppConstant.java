package com.banking.constant;

/**
 * Constants - unique const values are maintaining here.
 * 
 * @author Govindasamy.C
 * @since 05-12-2019
 */
public final class AppConstant {

	// Httpstatus success and failure messages for common.
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILURE = "FAILURE";
	public static final String SUCCESS_MESSAGE = "Record Created Successfully";
	public static final String NO_RECORD_FOUND = "No Records Found";
	public static final String RECORD_ALREADY_EXISTS = "Record Already Exists";

	// Fund Transfer
	public static final String TRANSACTION_TYPE = "IMPS";
	public static final String FUND_TRANSFER_SUCCESS = "Amount Transfered Sucessfully";
	public static final String FUND_TRANSFER_MIN_BAL = "Please check minimum balance.";
	public static final String FUND_TRANSFER_ERROR = "We are unable to process your transaction at this time. After sometime";

	// Login
	public static final String LOGIN_SUCCESSFULLY = "User Login Successfully";
	public static final String INVALID_LOGIN = "Invalid Username and Password";

	// Transaction
	public static final String GET_TRANSACTION_NO_PREFIX = "T-";
	public static final String OPERATION_SUCCESS = "Transaction Details";
	public static final Integer ZERO = 0;
	public static final String OPERATION_FAILD = "Operation Failed";

	// User Registration
	public static final Double ACCOUNT_MINIMUM_BALANCE = 500.00;
	public static final Double ACCOUNT_BALANCE_AMOUNT = 20000.00;
	public static final String FIRST_NAME_ERROR_MESSAGE = "firstName should be mandatory";
	public static final String EMAIL_ERROR_MESSAGE = "Email address should be mandatory";
	public static final String EMAIL_ADDRESS_ERROR_MESSAGE = "Invalid Email address";
	public static final String MOBILENUMBER_ERROR_MESSAGE = " mobileNo should be mandatory";
	public static final String MOBILE_INVALID_MESSAGE = "Invalid mobile no.";
	public static final String DOB_INVALID = "Invalid dob.";
	public static final String REGISTER_SUCCESS_MESSAGE = "You have successfully registered with Retail Bank.Kindly Log-in to website to view account details.";

	public static final String ADDRESS_ERROR_MESSAGE = "address1 should be mandatory";
	public static final String LOGIN_SUCCESS_MESSAGE = "Login Success";
	public static final String LOGIN_ERROR_MESSAGE = "Login failed";
	public static final String USER_EXIST = "User Already Exist";

	public static final String USER_NOT_FOUND = "No Users Found";
	public static final String AGE_ERROR_MESSAGE = "User should not have less than 18 years to open account";

	//getAccount Number
	public static final String SAVINGS = "saving";
	public static final String MORTGAGE = "mortgage";
	
	//Martgage account
	public static final String ACCOUNT_TYPE_SAVINGS = "Savings";
	public static final String ACCOUNT_TYPE_MORTGAGE = "Mortgage";
	
	public static final String MORTGAGE_ACCOUNT_CREATED = "Mortgage account is created successfully";
	public static final String USER_MORTGAGE_ACCOUNT_EXIST = "User Mortgage Account already exist";

	

	
}
