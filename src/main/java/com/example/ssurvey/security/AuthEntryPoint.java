package com.example.ssurvey.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint{	// AuthenticationEntryPoint : 인증되지 않은 사용자가 접근하려고 할때 인증 실패 관련된 것들을 처리해주는 인터페이스
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,	// response 객체에 값을 넣어주면 상태 코드로 띄워줌 
			AuthenticationException authException) throws IOException, ServletException {
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);	// 인증실패 코드(403) 뱉어줌
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write("인증 실패");
		
	}

}
