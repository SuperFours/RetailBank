package com.banking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.banking.entity.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

	UserAccount findByUserId(Integer userId);

	List<UserAccount> findAllByIdNot(Integer accountId);

	@Query("Select u from UserAccount u WHERE CAST(u.accountNumber AS string) LIKE %:accountNumber%")
	List<UserAccount> findAllByAccountNumber(@Param("accountNumber") String accountNumber);

	Optional<UserAccount> findByAccountNumberAndAccountType(Long accountNumber, String accountTypeMortgage);

	Optional<UserAccount> findByUserIdAndAccountType(Integer userId, String accountTypeMortgage);

}
