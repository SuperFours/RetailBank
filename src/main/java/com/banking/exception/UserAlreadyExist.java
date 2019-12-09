package com.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * @author  AKUTHOTA.RAGHU 
 * @version 1.0
 * @since   2019-12-05
 * Here, ResourceNotFoundException - for handling No Record Found Exception
 */

@ResponseStatus(value = HttpStatus.OK)
public class UserAlreadyExist extends RuntimeException {

	private static final long serialVersionUID = 3827765378836892788L;

	public UserAlreadyExist() {
		super();
	}

	public UserAlreadyExist(String userexist) {
		super("User Already Exist");
	}

}
