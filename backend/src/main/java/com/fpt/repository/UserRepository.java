package com.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
