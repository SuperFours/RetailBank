package com.banking.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

/**
 * UserTransaction Entity
 * @author Govindasamy.C
 * @since 05-12-2019
 */
@Entity
@Setter
@Getter
public class UserTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccountId;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "payee_account_id")
	private UserAccount payeeAccountId;

	@Column(name = "transaction_type")
	private String transactionType;

	@Column(name = "transaction_id")
	private String transactionId;

	@Column(name = "transaction_date")
	private LocalDate transactionDate;

	@Column(name = "transaction_amount")
	private Double transactionAmount;

	@Column(name = "current_balance_amount")
	private Double currentBalanceAmount;

	@Column(name = "remarks")
	private String remarks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserAccount getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(UserAccount userAccountId) {
		this.userAccountId = userAccountId;
	}

	public UserAccount getPayeeAccountId() {
		return payeeAccountId;
	}

	public void setPayeeAccountId(UserAccount payeeAccountId) {
		this.payeeAccountId = payeeAccountId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Double getCurrentBalanceAmount() {
		return currentBalanceAmount;
	}

	public void setCurrentBalanceAmount(Double currentBalanceAmount) {
		this.currentBalanceAmount = currentBalanceAmount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
