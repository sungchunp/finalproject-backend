package com.example.ssurvey.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@PostMapping("/join/{username}") // 이메일 중복 체크 
	public ResponseEntity<?> emailCheck (@PathVariable String username){
		User user =  userService.getUserInfo(username);

		
		if(user == null) {
			return new ResponseEntity<>("none" , HttpStatus.OK);
		}else {
			return new ResponseEntity<>(user.getUserType(), HttpStatus.OK);
		}
	}
	
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody User user){
		
		userService.join(user);
		return new ResponseEntity<>("회원가입 완료",HttpStatus.OK);

	}
	
	@PostMapping("/login")							// ↓ domain에 만들어둔 로그인 유저 아이디, 비번 객체
	public ResponseEntity<?> login (@RequestBody LoginUser loginUser){
		
		System.out.println(loginUser);
		
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
		// 구글 로그인 시도하면 access_token이라는 키값에 유저 로그인 정보를 담아서 보내고, 서버에서 받아짐
		User user = userService.googleLogin(accessToken.get("access_token")); //엑세스 토큰이라는 이름의 키를 가진 것의 벨류를 뽑아내는 코드
		// userService.googleLogin에서는 username(이메일), userRname(이름 & 닉네임), userPassword(google123), 유저 타입(구글), 롤타입(유저)
		// 로 저장된 상태의 user객체가 리턴됨
		
		
		// 아래 join 작업에 들어가면 user객체 안에 있는 password가 암호화 되기 때문에 authenticationManager 작업할때 불일치됨
		// ↓ 그래서 유저 비밀번호 따로 빼둔 값 저장함 upaToken에 들어갈 값이 유저가 입력한 비번 값과 일치해야하기때문
		String userPw = user.getPassword();
		
		
		// 기존 디비에 방금 로그인한 사람이 있는지 물어보고 없으면 신규회원이기 떄문에 DB에 저장 (저장은 아래 if문)
		User findUser = userService.getUserInfo(user.getUsername());	
		
		if(findUser==null) {	// null 값이면 회원이 아니기 때문에 if문이 발동됨
			// userService.join에서 받는 user값이 여기 있는 user와 '동일' 주소를 가지고 있어서 
			// join에서 암호화한 비밀번호 값으로 user객체 정보가 바뀜 (call by reference 주소에 의한 참조)
//			userService.join(user);	
			
			// Q. 여기서 리액트 소셜회원가입 페이지로 보낸다면
		 return new ResponseEntity<>(user,HttpStatus.OK);
		}

		
		return userService.getResponseEntity(user.getUsername(), userPw);
	}
	
	
	
	
	@PostMapping("/oauth/join")
	public ResponseEntity<?> SocialJoinInfo (@RequestBody User user){
		
		System.out.println("★ : " + user.getUsername());
		User findUser = userService.getUserInfo(user.getUsername());	
		String userPw = user.getPassword();
		
		System.out.println("★★");
		
		if(findUser==null) {	// null 값이면 회원이 아니기 때문에 if문이 발동됨		
			userService.join(user);	// DB에 저장
		}
		
		System.out.println("★★★");
		return userService.getResponseEntity(user.getUsername(), userPw); // 로그인 처리
	}
	
	
	
	
	
	
	
	
	
	
}