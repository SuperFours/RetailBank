package com.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findUserByPhone(String phone);

	User findUserByUserNameAndPassword(String userName, String password);

}
