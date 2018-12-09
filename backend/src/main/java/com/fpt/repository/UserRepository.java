package com.fpt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllByUsernameAndPassword(String username, String password);

	User getUserByUsernameAndPassword(String username, String password);

	User getUserByUsername(String username);

}
