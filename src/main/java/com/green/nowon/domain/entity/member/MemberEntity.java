package com.green.nowon.domain.entity.member;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.green.nowon.domain.dto.memberDTO.MemberUpdateDTO;
import com.green.nowon.domain.entity.BaseDateEntity;
import com.green.nowon.security.MyRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 * @author LeeYongJu
 * 직원 관련 DB
 * DB 이름 : GgMember
 * 컬럼 목록 : mno(사번) ,name(이름), id(아이디) , pass(비밀번호) , phone(번호)  
 * , mem_img(프로필)
 * myRole : user(사원) , admin(팀장)
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SequenceGenerator(name = "seq_gen_GgMember", 
		sequenceName = "seq_GgMember", initialValue = 1, allocationSize = 1)
@Table(name = "GgMember")
@Entity
public class MemberEntity extends BaseDateEntity{
	
	@GeneratedValue(generator = "seq_gen_GgMember", strategy = GenerationType.SEQUENCE)
	@Id
	private long mno;//사원번호
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String id;//이메일x ->수정 id로 변경 (domain은 모두 같은 부분을 사용하기 때문에)
	
	@Column(nullable = false)
	private String pass;//비밀번호
	
	@Column(nullable = false)
	private String phone;//번호
	
	@Builder.Default
	@CollectionTable(name = "GgDeploy")
	@Enumerated(EnumType.STRING)//직책
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<MyRole> roles = new HashSet<>();
	
	public MemberEntity addRole(MyRole role) {
		roles.add(role);
		return this;
	}
	
	public MemberEntity update(MemberUpdateDTO dto) {
		this.id=dto.getId();
		this.pass=dto.getPass();
		this.name = dto.getName();
		this.phone = dto.getPhone();
		return this;
	}
	
	
}