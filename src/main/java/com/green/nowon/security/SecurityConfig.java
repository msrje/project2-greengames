package com.green.nowon.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 		http.authorizeRequests(authorize->authorize
 				.antMatchers("/css/**","/images/**","/js/**").permitAll()
 				.antMatchers("/","/members/**").permitAll()
 				.anyRequest().authenticated()
 				)
 				.formLogin(formLogin-> formLogin
 						.loginPage("/")
							.loginProcessingUrl("/")
							.usernameParameter("email")
							.passwordParameter("pass")
							.defaultSuccessUrl("/")
							.failureUrl("/")
							.permitAll()
							)
 				.csrf(csrf->csrf.disable()
		 					)
 		
 		;
 				
 		return http.build();
 	}


}
