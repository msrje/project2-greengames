package com.green.nowon.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.PhoneInfo;
import com.green.nowon.domain.entity.chatbot.Answer;
import com.green.nowon.domain.entity.chatbot.ChatBotIntention;
import com.green.nowon.domain.entity.chatbot.ChatBotIntentionRepository;
import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.domain.entity.member.MemberEntityRepository;

import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;

@Service
public class KomoranService {
	
	
	@Autowired
	private Komoran komoran;
	
	
	public String nlpAnalyze(String message) {
		
		KomoranResult result=komoran.analyze(message);
		
		//문자에서 명사들만 추출한 목록 중복제거해서 set
		Set<String> nouns=result.getNouns().stream()
				.collect(Collectors.toSet());
		nouns.forEach((noun)->{
			System.out.println(">>>:"+ noun);
		});;//메세지에서 명사추출
		
		String answer="입력한 정보를 찾을수 없습니다.";
		Answer ar=analyzeToken(nouns);
		if(ar!=null) {
			answer=ar.getContent();
			if(ar.getKeyword()!=null) answer += ", "+ar.getKeyword();
			
		}
		return answer;		
	}

	//입력된 목적어를 하나씩 파악하여 DB에서 검색하기위해 decisionTree()메서드로 전달
	private Answer analyzeToken(Set<String> nouns) {
		
		//1차의도가 존재하는지 파악
		for(String token:nouns) {
			
			Optional<ChatBotIntention> result=decisionTree(token, null);
			if(result.isEmpty())continue;
			System.out.println(">>>>1차:"+token);
			Set<String> next=nouns.stream().collect(Collectors.toSet());
			next.remove(token);
			
			
			String keyword=null;
			if(token.contains("번호")) {
				PhoneInfo phone=analyzeTokenIsPhone(next);
				if(phone ==null ) {
					keyword="입력한 사원을 찾을수 없습니다.";
				}else {
					keyword=phone.getDeptName()+" : "+phone.getMemberName()+" "+"연락처 : "+ phone.getPhone();
				}
			}
			
			//2차분석 메서드
			return analyzeToken(next, result).keyword(keyword);
		}
		//분석 명사들이 등록한 의도와 일치하는게 존재하지 않을경우 null
		return null;
	}

	@Autowired
	MemberEntityRepository member;
	//전화문의인경우 DB에서 사원을 을 찾아서 처리
	
	private PhoneInfo analyzeTokenIsPhone(Set<String> next) {
		for(String name : next) {
			Optional<MemberEntity> m=member.findByName(name);
			if(m.isEmpty())continue;
			//존재하면
			//String deptName=m.get().getDept().getDname();//부서 이름 하지만 강사님과 형태가 달름
			//String deptName = m.get().getDepartmentMember().getDepartment().getDname(); 
			String deptName = null;
			String phone=m.get().getPhone();
			String memberName=m.get().getName();
			return PhoneInfo.builder()
			.deptName(deptName)
			.phone(phone)
			.memberName(memberName)
			.build();

		}
		return null;
	}

	//1차의도가 존재하면
	//하위의도가 존재하는지 파악
	private Answer analyzeToken(Set<String> next, Optional<ChatBotIntention> upper) {
		for(String token : next) {
			// 1차의도를 부모로하는 토큰이 존재하는지 파악
			Optional<ChatBotIntention> result=decisionTree(token, upper.get());
			if(result.isEmpty())continue;
			System.out.println(">>>>2차:"+token);
			return result.get().getAnswer();//1차-2차 존재하는경우 답변
		}
		return upper.get().getAnswer();//1차만 존재하는 답변
	}

	@Autowired
	ChatBotIntentionRepository intention;
	//의도가 존재하는지 DB에서 파악
	private Optional<ChatBotIntention> decisionTree(String token, ChatBotIntention upper) {
		return intention.findByNameAndUpper(token, upper); 
	}


}
