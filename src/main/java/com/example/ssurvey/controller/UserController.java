package com.example.ssurvey.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
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
		
		return userService.getResponseEntity(loginUser.getUsername(), loginUser.getPassword());
		
	}
	
	
	@GetMapping("/userInfo")
	public ResponseEntity<?> userInfo(Authentication authentication){	// 인증객체를 메서드로 받기 jwtfilter가 인증객체를 username으로 만들어 뒀기 때문에 사용할 수 있음
		
		
		String username = authentication.getName();	// 로그인 유저이름 뽑고
		User user= userService.getUserInfo(username);	// 이름이 일치하는 유저ㅗ 정보가져옴

		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	@PostMapping("/oauth/google")
	public ResponseEntity<?> googleLogin(@RequestBody Map<String,String> accessToken){
		User user = userService.googleLogin(accessToken.get("access_token")); //엑세스 토큰이라는 이름의 키를 가진 것의 벨류를 뽑아내는 코드
		
		System.out.println(user);
		
		User findUser = userService.getUserInfo(user.getUsername());	// 기존 디비에 방금 로그인한 사람이 있는지 물어보고 없으면 신규회원이기 떄문에 DB에 저장 (저장은 아래 if문)
		
		
		System.out.println("findUser★★★★★★");
		
		
		if(findUser==null) {
			userService.join(user);	// 여기서 저장이 되는데....저장 안시키고 ?
		}
		
		System.out.println("join★★★★★★");
//		return new ResponseEntity<>("추가정보를 입력해주세요",HttpStatus.OK);
		return userService.getResponseEntity(user.getUsername(), user.getPassword());
	}
	
	
	
	
	
	
	
}