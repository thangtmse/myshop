package com.fpt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fpt.entity.User;
import com.fpt.service.UserService;
import com.fpt.utils.PasswordUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordUtils passwordUtils;

	@RequestMapping(path = "authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> authenticate(@RequestBody User user) throws Exception {
		String token = userService.authenticate(user.getUsername(), passwordUtils.encode(user.getPassword()));
		Map<String, String> response = new HashMap<>();
		response.put("token", token);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE', 'CUSTOMER')")
	@RequestMapping(path = "profile", method = RequestMethod.GET)
	public ResponseEntity<?> getProfile() throws Exception {
		User profile = userService.getProfile();
		return new ResponseEntity<>(profile, HttpStatus.OK);
	}
}
