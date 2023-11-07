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

	// 유저정보 받아주는 메서드
	public User getUserInfo (String username) {
		User loginUserInfo = userRepository.findByUsername(username).get();		
		return loginUserInfo;
	}
	
	
	public ResponseEntity<?> getResponseEntity (String username, String password) {
		
		UsernamePasswordAuthenticationToken upaToken =
				new UsernamePasswordAuthenticationToken(username, password);
		
		Authentication auth = authenticationManager.authenticate(upaToken);
		
		String jwt = jwtService.getToken(auth.getName());
		
		
		return ResponseEntity.ok()
					.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
					.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
					.build();
		}
	
	
	
}