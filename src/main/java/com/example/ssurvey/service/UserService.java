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
import org.springframework.util.LinkedMultiValueMap;
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
	
	@Value("${kakao.default.password}")
	private String kakaoPassword;
	
	
	
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
	
	
	public String getKakaoAccessToken(String code) {
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); // 카카오에서 안내된 코드
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
	    body.add("grant_type", "authorization_code");
	    body.add("client_id", "e6b21a4273796d922a495af28898d205"); // 각자 rest api key
	    body.add("redirect_uri", "http://localhost:3000/oauth/kakao");
	    body.add("code", code);
	    
	    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body,header);
	    
	    RestTemplate restTemplate = new RestTemplate();
	    
	    ResponseEntity<String> response = 
	    		restTemplate.exchange("https://kauth.kakao.com/oauth/token",	// 카카오 문서에서 안내된 대로 적기
	    								HttpMethod.POST,
	    								request,
	    								String.class
	    							);
		
	    String json = response.getBody();	// 여기서 부터는 토큰만 따로 추출하기 위한 코드
	    
	    Gson gson = new Gson();
	    Map<?, ?> data = gson.fromJson(json, Map.class);
	    
	    return (String) data.get("access_token");	// 순수 access_Token 문자열만 뽑기
	}
	
	
	
	
	public User kakaoLogin(String accessToken) {
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "Bearer " + accessToken);	// 카카오 문서 안내대로
		header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); // 카카오 문서 안내대로
		

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(header);	// 토큰을 통해 정보를 받아올때는 header만 보내주면 된다고 함(카카오 문서 안내)
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response = 
				restTemplate.exchange(
									"https://kapi.kakao.com/v2/user/me",	//카카오 문서	
									HttpMethod.POST,
									request,
									String.class
									);
		
		String json = response.getBody();
		Gson gson = new Gson();
		Map<?, ?> data = gson.fromJson(json, Map.class);	//문자열을 키벨류값으로 분류해서 map에 저장함
		
		System.out.println("★★★★");
		
		String username = (String)((Map<?, ?>)data.get("properties")).get("nickname");	// map안에 키벨류로 저장된 값에서 nickname만 뽑아내기
		System.out.println(username);
		
		String email = (String)((Map<?, ?>) data.get("kakao_account")).get("email");
		
		/*
		  위에 Map 형태의 data 객체 안에는 아래와 같이 들어있음 여기서 원하는걸 경로에 맞게 뽑으면 됨
		  {
			"id":3100958514,
			"connected_at":"2023-10-17T01:20:30Z",
			"properties":{"nickname":"예원"},
			"kakao_account":
					{
						"profile_nickname_needs_agreement":false,
						"profile":{"nickname":"예원"},
						"has_email":true,
						"email_needs_agreement":false,
						"is_email_valid":true,
						"is_email_verified":true,
						"email":"wwww7708@naver.com"
					}
		   }
	     */
	
		User user = new User();
		user.setUsername(email);
		user.setUserRname(username);
		user.setPassword(kakaoPassword);
		user.setUserType("kakao");
		user.setUserRoletype(RoleType.USER);
		
		return user;
		
	}
	
	
}