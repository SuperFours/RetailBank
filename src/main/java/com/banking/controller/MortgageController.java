package com.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dto.MortgageRequestDto;
import com.banking.dto.ResponseDto;
import com.banking.service.MortgageService;

/**
 * @description This controller is used to for creating and do the operations
 *              for mortgage for the existing savings account holder
 * @since 13-12-2019
 * @author akuthota.raghu
 * @version v1
 *
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/mortgageaccounts")
public class MortgageController {

	@Autowired
	MortgageService mortgageService;

	/**
	 * @description this method is used to create the mortgage account for existing
	 *              savings account holder
	 * @param mortgageRequestDto object set of input fields
	 * @return MortgageResponseDto object contains response message and status
	 */
	@PostMapping
	public ResponseEntity<ResponseDto> createMortgageaccount(@RequestBody MortgageRequestDto mortgageRequestDto) {
		ResponseDto responseDto = mortgageService.createMortgageAccount(mortgageRequestDto);
		if (responseDto != null) {
			responseDto.setStatusCode(HttpStatus.OK.value());
		}
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}
}
