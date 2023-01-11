package com.green.nowon.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.green.nowon.domain.entity.approval.ApprovalEntity;
import com.green.nowon.domain.entity.cate.PositionEntity;
import com.green.nowon.domain.entity.member.MemberEntity;

import lombok.Getter;

@Getter
public class MyUserDetails extends User{
	
	private String id;
	private String name;
	private long mno;
	private boolean admin;
	
	public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
	}
	public MyUserDetails(MemberEntity entity) {
		this(entity.getId(), entity.getPass(), entity.getRoles() //set<MyRole> ---> set<GrantedAuthority>
				.stream()
				.map(Role->new SimpleGrantedAuthority(Role.getRole()) ) //Stream<GrantedAuthority> "ROLE_USER" or "ROLE_ADMIN"
				.collect(Collectors.toSet()));
		
		this.id=entity.getId();
		this.name=entity.getName();
		this.mno=entity.getMno();
		for(MyRole role:entity.getRoles()) {
			if(role.name().equals("ADMIN")) {
				admin=true;
			}
		}
	}
	

}
