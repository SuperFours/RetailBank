package com.banking.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.banking.constant.AppConstant;
import com.banking.dto.ResponseDto;

import javassist.NotFoundException;

/**
 * @description CustomExceptionHandler - we are handled here the global
 *              exceptions concepts for validations error handled with
 *              handleMethodArgumentNotValid exception user not found exception
 * 
 * @author Govindasamy.C
 * @version V1.1
 * @created date - 04-12-2019
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * @description get the validation filed errors while sending params in the
	 *              method of the request body params. In this case, we can validate
	 *              the each field error values.
	 * @return ResponseEntity object for setting the response values with status,
	 *         errors with body
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());

		// Get all errors for field valid
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());

		body.put("errors", errors);
		return new ResponseEntity<>(body, headers, status);
	}

	/**
	 * @description handleNoRecordFoundException for when check the object is
	 *              present or not, if not present we can throw and handle the
	 *              notfoundexception.
	 * @param ex - ex is the notfoundexception value.
	 * @return responseDto values are success, successcode and massage details of
	 *         response.
	 */
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ResponseDto> handleNoRecordFoundException(NotFoundException ex) {
		ResponseDto fundTransferResponseDto = new ResponseDto();
		fundTransferResponseDto.setMessage(ex.getMessage());
		fundTransferResponseDto.setStatus(AppConstant.FAILURE);
		fundTransferResponseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(fundTransferResponseDto, HttpStatus.NOT_FOUND);
	}
}
