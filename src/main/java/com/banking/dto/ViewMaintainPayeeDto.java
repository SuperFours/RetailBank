package com.banking.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewMaintainPayeeDto {

	private Integer accountId;
	private String accountNumber;
	private String nickName;
	private String bankName;
	private String branchName;
}
