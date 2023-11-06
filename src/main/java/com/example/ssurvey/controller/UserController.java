package com.example.ssurvey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssurvey.domain.LoginUser;
import com.example.ssurvey.domain.User;
import com.example.ssurvey.jwt.JwtService;
import com.example.ssurvey.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;	// 서비스 의존성주입으로 받아옴
	
	
	
	
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody User user){
		
		userService.join(user);
		return new ResponseEntity<>("회원가입 완료",HttpStatus.OK);

	}
	
	@PostMapping("/login")							// ↓ domain에 만들어둔 로그인 유저 아이디, 비번 객체
	public ResponseEntity<?> login (@RequestBody LoginUser loginUser){
		
		System.out.println(loginUser.getUsername());
		System.out.println(loginUser.getPassword());
		
		return userService.getResponseEntity(loginUser.getUsername(), loginUser.getPassword());
		
	}
}