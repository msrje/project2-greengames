package com.green.nowon.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.entity.member.MemberEntityRepository2;

import lombok.extern.log4j.Log4j2;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private MemberEntityRepository2 repo;

	public MyUserDetailsService(MemberEntityRepository2 memberRepository) {
		this.repo = memberRepository;

	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new MyUserDetails(repo.findById(username)
				.orElseThrow(()->new UsernameNotFoundException("존재하지 않는 이메일")));
	}


}
