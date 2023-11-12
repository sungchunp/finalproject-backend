package com.example.ssurvey.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.ssurvey.domain.RoleType;
import com.example.ssurvey.domain.User;
import com.example.ssurvey.jwt.JwtService;
import com.example.ssurvey.repository.UserRepository;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 생성자를 통한 의존성 주입 (private 'final' 인거에 주입)
public class UserService {
	
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
//	// 생성자
//    public UserService(
//            PasswordEncoder passwordEncoder,
//            UserRepository userRepository,
//            JwtService jwtService,
//            AuthenticationManager authenticationManager
//    ) {
//        this.passwordEncoder = passwordEncoder;
//        this.userRepository = userRepository;
//        this.jwtService = jwtService;
//        this.authenticationManager = authenticationManager;
//    }
	
	@Value("${google.default.password}")
	private String googlePassword;
	
	
	
	public void join (User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setUserRoletype(RoleType.USER);
		
		if(user.getUserType()== null) {
		user.setUserType("email");
		}
		
		userRepository.save(user);
	}

	
	// 유저정보 받아주는 메서드
	public User getUserInfo (String username) {
		User loginUserInfo = userRepository.findByUsername(username).orElse(null);		
		return loginUserInfo;
	}
	
	
	
	public ResponseEntity<?> getResponseEntity (String username, String password) {
		
		UsernamePasswordAuthenticationToken upaToken =
				new UsernamePasswordAuthenticationToken(username, password); // 사용자가 입력한 아이디, 비번값 
		
		// 일치하면 auth에 인증객체가 담기고, 아니면 오류처리됨
		Authentication auth = authenticationManager.authenticate(upaToken);	// DB에 저장된 아이디 비번값과 위에 만들 upaToken(입력 아이디, 비번 값) 비교		
		String jwt = jwtService.getToken(auth.getName());
		
		
		return ResponseEntity.ok()
					.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
					.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
					.build();
		}
	
	
	
	public User googleLogin(String token) {
		RestTemplate restTemplate = new RestTemplate();
		
		String userInfoEndPoint = "https://www.googleapis.com/oauth2/v1/userinfo";	// 여기에 엑세스 토큰 담아서 유저 정보 요청보내면 유저정보 뱉어내줌 저 URL참고
		
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "Bearer " + token);
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(header);
		
		ResponseEntity<String> response = restTemplate.exchange(userInfoEndPoint, HttpMethod.GET, request, String.class);	// ?
		
		// System.out.println(response.getBody());	이거 쓰면 어떤 형식으로 리턴하는지 미리보기 할 수 있음
		
		String userInfo = response.getBody();	// json 형태로 리턴해줌
		Gson gson = new Gson(); // 얘쓰려면 라이브러리 추가해야함
		
		Map<?, ?> data = gson.fromJson(userInfo, Map.class);
		
		User user = new User();
		
		user.setUsername((String) data.get("email"));
		user.setUserRname((String) data.get("name"));
		user.setPassword(googlePassword);
		user.setUserType("google");
		user.setUserRoletype(RoleType.USER);
		
		return user;
		
	}
	
	
}