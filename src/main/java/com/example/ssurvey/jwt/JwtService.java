package com.example.ssurvey.jwt;

import java.security.Key;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component	// 컨트롤러의 역할을 하도록 어노테이션 넣기
public class JwtService {	

	// 토큰 만료 시간 (보통 20~30분)
	static final long EXPIRATIONTIME = 24 * 60* 60 *1000; //24시간
	
	// jwt에서 헤더에 사용할 접두어
	static final String PREFIX = "Bearer";
	
	// 서명에 사용될 암호화시킨 key 
	static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	
	
	// jwt(토큰) 발급해주는 메서드
	public String getToken(String username) {
		String token = Jwts.builder()
				.setSubject(username)	// 토큰안에 내용이 들어가는 부분 (jwt 클레임_각종 정보 담는 부분)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))	//현재 시간 기준으로  24 시간 뒤를 날짜로 만들어줌 (jwt_토큰 만료 시간)
				.signWith(key)	// jwt 서명
				.compact();
			
		return token;
	}
	
	// 토큰 추출해서 검사, 사용자 이름을 리턴
	public String getAuthUser(HttpServletRequest request) {
		
		// 헤더 안에 AUTHORIZATION에 들어있는 정보 꺼내기
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);	// 로그인을 안한 사람은 토큰이 없음
		
		if(token != null) {
			
			String user = Jwts.parserBuilder()	// parserBuilder -> 뭉쳐저 잇는걸 깨주는거(분해)
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token.replace(PREFIX, ""))	// bearer 붙은 접두어를 제거함
					.getBody()
					.getSubject();
			if(user != null)
				return user;
		}
		
		return null;
	}
}