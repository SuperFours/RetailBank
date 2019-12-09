package com.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.entity.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

	UserAccount findByUserId(Integer userId);

	List<UserAccount> findAllByIdNot(Integer accountId);

}
