package com.fpt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpt.entity.User;
import com.fpt.repository.UserRepository;
import com.fpt.service.UserService;
import com.fpt.utils.PasswordUtils;

@Controller
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordUtils passwordUtils;
	@Autowired
	private UserRepository userRepository;

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
	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getOne(@PathVariable("id")Long id) throws Exception {
		
		return new ResponseEntity<>(userRepository.getOne(id), HttpStatus.OK);
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
	@RequestMapping(path = "all", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUser(Pageable pageable,
			@RequestParam(value="name",required=false,defaultValue="")String name,
			@RequestParam(value="phone",required=false,defaultValue="")String phone,
			@RequestParam(value="role",required=false,defaultValue="")String role){
		return new ResponseEntity<>(userService.getUsers(name, phone, role, pageable),HttpStatus.OK);
	}
	@RequestMapping(path = "/acept", method = RequestMethod.POST)
	public ResponseEntity<?> createAcount(@RequestBody User user) throws Exception {
		 
		User curUser = userService.getByUserName(user.getUsername());
		Map<String, Object> message = new HashMap<>();
		if (curUser != null && user.getUserId() == null) {
			message.put("isSuccess", false);
			message.put("error", "Tên tài khoản đã tồn tại");
			return new ResponseEntity<>(message, HttpStatus.OK);
		}else {
		message.put("isSuccess", true);
		user = userService.save(user);}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@RequestMapping(path = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> UpdateAcount(@PathVariable("id")Long id,@RequestBody User user) throws Exception {
		 
		User curUser = userService.getByUserName(user.getUsername());
		curUser.setAddress(user.getAddress());
		curUser.setEmail(user.getEmail());
		curUser.setFullName(user.getFullName());
		curUser.setPassword(user.getPassword());
		curUser.setPhone(user.getPhone());
		
		
		user = userService.save(curUser);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	@RequestMapping(path = "/acc", method = RequestMethod.GET)
	public ResponseEntity<?> geByUser(@RequestParam("username") String name) throws Exception {
		
		return new ResponseEntity<>(userService.getByUserName(name), HttpStatus.OK);
	}
}
