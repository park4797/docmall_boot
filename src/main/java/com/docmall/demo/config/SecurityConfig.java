package com.docmall.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

	@Bean  // spring mvc에서는 security폴더에 spring-security.xml파일에서 bean생성
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//스프링 시큐리티 설정. 아래 설정을 안하면, 시큐리티에서 제공하는 로그인페이지가 작동한다.
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable();
		
		/*
		 시큐리티 관련 설정을 이곳에서 한다. 
		
		 */
		
		
		return http.build();
	}
}
