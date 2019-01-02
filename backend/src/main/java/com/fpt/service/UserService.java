package com.fpt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fpt.controller.error.APIException;
import com.fpt.entity.User;
import com.fpt.repository.UserRepository;
import com.fpt.security.TokenProvider;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TokenProvider tokenProvider;

	public User getById(Long userId) {
		return userRepository.getOne(userId);
	}

	public User getByUserAndPass(String username, String password) {
		return userRepository.getUserByUsernameAndPassword(username, password);
	}

	public String authenticate(String username, String password) throws Exception {
		System.out.println(username);
		System.out.println(password);
		List<User> users = userRepository.findAllByUsernameAndPassword(username, password);
		System.out.println(users);
		if (!CollectionUtils.isEmpty(users)) {
			User user = users.get(0);
			return tokenProvider.generateToken(user.getUserId(), true);
		}
		throw new APIException(HttpStatus.UNAUTHORIZED, "Sai tài khoản hoặc mật khẩu");
	}

	public User getProfile() {
		User profile = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userRepository.getOne(profile.getUserId());
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User getByUserName(String username) {
		// TODO Auto-generated method stub
		return userRepository.findUserByUsername(username);
	}
	public Page<User> getUsers(String name,String phone,String role,Pageable pageable){
		return userRepository.findAllByUsernameContainingAndPhoneContainingAndRoleContaining(name, phone, role, pageable);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		User user=userRepository.getOne(id);
		
		 userRepository.delete(user);
	}

	
	}




