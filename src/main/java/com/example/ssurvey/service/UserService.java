package com.example.ssurvey.service;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ssurvey.domain.RoleType;
import com.example.ssurvey.domain.User;
import com.example.ssurvey.jwt.JwtService;
import com.example.ssurvey.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 생성자를 통한 의존성 주입 (private 'final' 인거에 주입)
public class UserService {
	
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	
	public void join (User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setUserRoletype(RoleType.USER);
		
		userRepository.save(user);
	}

	// 이메일값 보내주면 유저정보 받아주는 메서드
	public User usernameCheck (String username) {
		User loginUserInfo = userRepository.findByUsername(username).get();		
		return loginUserInfo;
	}
	
	
	public ResponseEntity<?> getResponseEntity (String username, String password) {
		
		
    	UsernamePasswordAuthenticationToken upaToken = 
				new UsernamePasswordAuthenticationToken(username, password);
		System.out.println(upaToken);
		//시큐리티.core 임폴트하기
		// 예외발생도 여기서됨 (값이 없으면, 그냥 null로 처리됨)
		// auth  : 인증객체 (안에 들어 있는 값이 로그인 성공한 사람 정보)
		Authentication auth = null;	// 보내준 정보를 토대로 유저서비스 작동되면서 비교할거 비교하고 끝나면 인증객체가 auth에 들어감
		
		try {
			auth = authenticationManager.authenticate(upaToken);
		} catch (Exception e) {
			e.printStackTrace();
		}	
				
		System.out.println(auth);
		String jwt = jwtService.getToken(auth.getName()); // jwt는 인증 성공된 사용자의 정보가 담겨 있는 최종 토큰
		System.out.println(jwt);
		return ResponseEntity.ok()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt) // Bearer 뒤에 한칸 띄우기
				.header(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Authorization")
				.build();	
	        
	    

	}
	
	
	
}