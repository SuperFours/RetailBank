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

	@Query(value = "SELECT * FROM user_transaction WHERE user_account_id = ?1 AND transaction_date Like %?2%", nativeQuery = true)
	List<UserTransaction> findByMatchMonthAndMatchDay(@Param("userAccountId") Integer userAccountId,
			@Param("transactionDate") String transactionDate);
}
