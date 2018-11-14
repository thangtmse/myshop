package com.fpt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
