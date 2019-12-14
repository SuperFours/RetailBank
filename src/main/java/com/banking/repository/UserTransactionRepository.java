package com.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.banking.entity.UserTransaction;

public interface UserTransactionRepository extends JpaRepository<UserTransaction, Integer> {

	UserTransaction findByTransactionId(String transactionId);

	List<UserTransaction> findTop5ByUserAccountIdIdOrPayeeAccountIdIdOrderByIdDesc(Integer userAccountId,
			Integer payyeAccountId);

	List<UserTransaction> getAllByUserAccountIdAndTransactionDateBetween(Integer userAccountId, LocalDate startDate,
			LocalDate endDate);
}
