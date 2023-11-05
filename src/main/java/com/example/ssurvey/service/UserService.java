package com.example.ssurvey.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ssurvey.domain.RoleType;
import com.example.ssurvey.domain.User;
import com.example.ssurvey.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 생성자를 통한 의존성 주입 (private 'final' 인거에 주입)
public class UserService {
	
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	
	
	public void join (User user) {
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		user.setUserRoletype(RoleType.USER);
		
		userRepository.save(user);
	}

	// 이메일값 보내주면 유저정보 받아주는 메서드
	public User emailCheck (String userEmail) {
		User loginUserInfo = userRepository.findByUserEmail(userEmail);
		
		return loginUserInfo;
	}
	
	
	public String login (String userEmail, String userPassword) {
		
		User loginUserInfo = emailCheck(userEmail); // 이메일보내면 유저정보 받아주는 메서드

		//여기서 보내준 유저 이메일이랑, 비밀번호 확인해야함
		if (loginUserInfo == null) {
	        // 입력한 이메일 유저가 존재하지 않을 경우
	        return "이메일"; //"존재하지 않는 아이디 입니다"
	    } else {
	        // 입력한 이메일이 존재할 경우, 비밀번호 확인 작업
	        if (passwordEncoder.matches(userPassword, loginUserInfo.getUserPassword())) {
	            // 비밀번호가 일치하는 경우
	        	return null;
	        } else {
	            // 비밀번호가 일치하지 않는 경우
	        	return "비밀번호";	
	        }
	    }
		
	}
	
	

}