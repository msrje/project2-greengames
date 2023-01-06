package com.green.nowon.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author LeeYongJu
 * 권한 허가 : 모든 페이지("/**") , /css/** ,"/images/**","/js/**"
 * email , pass 로 아이디와 비밀번호 입력
 */

@EnableWebSecurity
public class SecurityConfig {
	
	
	@Bean
 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 		http.authorizeRequests(authorize->authorize
 				.antMatchers("/css/**","/images/**","/js/**").permitAll()
 				.antMatchers("/**").permitAll()
 				.anyRequest().authenticated()
 				)
 				.formLogin(formLogin-> formLogin
 						.loginPage("/login")
							.loginProcessingUrl("/signin")
							.usernameParameter("id")
							.passwordParameter("pass")
							.defaultSuccessUrl("/index")
							.failureUrl("/")
							.permitAll()
							)
 				.csrf(csrf->csrf.disable()
		 					)
 		
 		;
 				
 		return http.build();
 	}


}
