package com.example.ssurvey.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.ssurvey.filter.JwtFilter;


@Configuration
public class SecurityConfig {

	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Autowired
	private AuthEntryPoint authEntryPoint;	// 인증 실패 관련 작업 있는 클래스
	
	// 시큐리티 관련
	@Bean
	public SecurityFilterChain filerChain(HttpSecurity http) throws Exception{
		http.csrf().disable();	// 클라이언트 사이드 랜더링으로 할때는 csrf 사용 X 그래서 비활성화 해주는게 좋음
		http.cors();	// 크로스 오리진 할거다 미리 적음 (아래 CorsConfigurationSource은 이게 있기 때문에 작동할 수 있는거)
		
		
		http.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);	// 토큰을 통해서 로그인 인증처리를 할거기 때문에 세션 사용안한다고 설정함
		
		http.authorizeHttpRequests()
			.antMatchers(HttpMethod.POST, "/login", "/join", "/oauth/**").permitAll() //권한이 없는 사람(로그인 안한 사람들)들이 요청할 수 있는 허용 범위
			.anyRequest().authenticated();
//			.and()
//			.exceptionHandling()	// 예외발생했을때 잡아줄 핸들링
//			.authenticationEntryPoint(authEntryPoint)
//			.and()
//			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);	// 토큰 분해해서 사용자 정보 뽑아내고, 인증객체 만드는 메서드 호출 (유저네임만 리턴해주는 인증객체임)
		
		return http.build();
		
		
		// 시큐리티가 작동될때 컨트롤러 동작 수행전에 필터가 걸러서 작동함 (컨트롤러 전에 필터가 가로채서 시큐리티작업을 먼저해줌)
		// 프론트와 백의 포트가 다를때 
	}
	
	@Bean
	public CorsConfigurationSource configurationSource () {
		CorsConfiguration config = new CorsConfiguration();	// cors 설정해주는 객체
		
		config.addAllowedOrigin("http://localhost:3000"); // 3000번 포트로 요청이 오는 모든건 허용
		
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();	// 임폴트할때 reactive가 없는걸로
		source.registerCorsConfiguration("/**", config); // (허용범위, 어디에 있는 소스인지)
		
		return source;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
}
