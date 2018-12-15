package com.fpt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fpt.entity.User;
import com.fpt.service.UserService;
import com.fpt.utils.PasswordUtils;

@Controller
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordUtils passwordUtils;

	@RequestMapping(path = "authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> authenticate(@RequestBody User user) throws Exception {
		String token = userService.authenticate(user.getUsername(), passwordUtils.encode(user.getPassword()));
		user = userService.getByUserAndPass(user.getUsername(), passwordUtils.encode(user.getPassword()));
		Map<String, Object> response = new HashMap<>();
		response.put("token", token);
		user.setPassword("");
		response.put("userInfo", user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

//	@RequestMapping(path = "change-password", method = RequestMethod.POST)
//	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) throws Exception {
//		User user = userService.getByUserAndPass(changePasswordDTO.getUsername(),
//				passwordUtils.encode(changePasswordDTO.getCurPassword()));
//		Map<String, Object> response = new HashMap<>();
//		if (user == null) {
//			response.put("message", "tài khoản hoặc mật khẩu sai");
//			response.put("isSuccess", false);
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		}
//		user.setPassword(passwordUtils.encode(changePasswordDTO.getNewPassword()));
//		userService.save(user);
//		response.put("isSuccess", true);
//		response.put("message", "thay đổi password thành công");
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}

	// @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE', 'CUSTOMER')")
	@RequestMapping(path = "profile", method = RequestMethod.GET)
	public ResponseEntity<?> getProfile() throws Exception {
		User profile = userService.getProfile();
		return new ResponseEntity<>(profile, HttpStatus.OK);
	}

	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody User user) throws Exception {
		user.setPassword(passwordUtils.encode(user.getPassword()));
		User curUser = userService.getByUserName(user.getUsername());
		Map<String, Object> message = new HashMap<>();
		if (curUser != null && user.getUserId() == null) {
			message.put("isSuccess", false);
			message.put("error", "Tên tài khoản đã tồn tại");
			return new ResponseEntity<>(message, HttpStatus.OK);
		}
		user = userService.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
