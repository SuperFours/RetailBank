package com.banking.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewPayeeResponseDto {

	private String message;
	private Integer statusCode;
	List<ViewMaintainPayeeDto> payees;
}
