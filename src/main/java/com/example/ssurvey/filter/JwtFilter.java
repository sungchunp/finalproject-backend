package com.example.ssurvey.filter;


import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.ssurvey.jwt.JwtService;



@Component
public class JwtFilter extends OncePerRequestFilter{	// 컨트롤러로 가기전 동작할 필터임
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// 
		String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);	// 헤더안에 AUTHORIZATION에 들어있는 토큰 꺼내기 (없을수도 있음)
		
		// 토큰에 값이 있으면 로그인된 사용자라고 생각함 (but 시큐리티는 그런게 없음 -> 그래서 인증객체를 만들어서 시큐리티 한테 알려줌)
		if(jwt != null) {
			String username = jwtService.getAuthUser(request);	// 토큰안에 인증된 사용자 이름 추출
			
			// 인증 객체 만들어부림
			Authentication authentication =
					new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList()); //유저네임만 담아주기위해 나머지는 비움
		
			SecurityContextHolder.getContext().setAuthentication(authentication);	//
		}
		
		filterChain.doFilter(request, response); // SecurityConfig 에 결과값 보내줌
		
	}
}
