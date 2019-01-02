package com.fpt.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllByUsernameAndPassword(String username, String password);

	User getUserByUsernameAndPassword(String username, String password);
	
	User findUserByUsername(String username);

//	@Query("from User u where u.username like :name and u.phone =:phone and u.role= :role")
//	public Page<User> findUserByCondition(@Param("name") String name, @Param("phone") String phone,
//			@Param("role") String role, Pageable pageable);
	public Page<User> findAllByUsernameContainingAndPhoneContainingAndRoleContaining(String ussername, String phone, String role,Pageable pageable);
	// public Page<User> findByuserNameOrphoneOrrole(String s,Pageable pageable);
}
