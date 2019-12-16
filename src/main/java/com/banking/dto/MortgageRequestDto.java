package com.banking.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MortgageRequestDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private String accountType;
	private Double propertyValue;
}
