package com.example.ssurvey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssurvey.domain.LoginUser;
import com.example.ssurvey.domain.User;
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
		
		// userService에서 로그인 유저 확인 처리 후 각 상황에 따른 메세지 리턴
		String loginResult =  userService.login(loginUser.getUserEmail(), loginUser.getUserPassword());	

		if(loginResult == null) {	// 위에서 처리한 로그인 확인 과정의 리턴값이 null이면 로그인 유저 정보를 보내줌
			User loginUserInfo = userService.emailCheck(loginUser.getUserEmail());
			return new ResponseEntity<>(loginUserInfo, HttpStatus.OK);
		}else {	
			return new ResponseEntity<>(loginResult, HttpStatus.OK); // 로그인 실패 문구 보내줌
		}
	}
}