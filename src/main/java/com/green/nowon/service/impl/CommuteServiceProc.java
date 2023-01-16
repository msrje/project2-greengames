package com.green.nowon.service.impl;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.attendance.CommuteInsertDTO;
import com.green.nowon.domain.dto.attendance.CommuteUpdateDTO;
import com.green.nowon.domain.dto.commuteMember.CommuteMemberListDTO;
import com.green.nowon.domain.entity.attendance.CommuteEntity;
import com.green.nowon.domain.entity.attendance.CommuteEntityRepository;
import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.domain.entity.member.MemberEntityRepository;
import com.green.nowon.domain.entity.member.MemberEntityRepository2;
import com.green.nowon.service.attendance.CommuteService;

@Service
public class CommuteServiceProc implements CommuteService {

	@Autowired
	private CommuteEntityRepository commuteRepo;

	@Autowired
	private MemberEntityRepository memberRepo;
	/**
	 * 저장과 동시에 업데이트 기능
	 */
	@Transactional
	@Override
	public void save(long mno, CommuteInsertDTO idto , CommuteUpdateDTO udto) {
		Optional<CommuteEntity> result = findGoTime(mno);
		if(result.isEmpty()) {
			commuteRepo.save(idto.entity().fkSaver(memberRepo.findByMno(mno).get()));
		}else {
			CommuteEntity entity = result.get();
			LocalDateTime start = entity.getGTime();
			LocalDateTime end = LocalDateTime.now();
			long timeset = start.until(end, ChronoUnit.HOURS);
			System.out.println(">>>>>>>>>>>>>>>>>>>>"+timeset);
			entity.update(udto,timeset);
			
			commuteRepo.save(entity);
			
			
			
			/**
			 * 근무시간 계산
			 */
		}
	}
	/**
	 * 오늘자 출근이 있는지 확인하는 메소드 -> 오늘자에서 가장 최근의 근무일자
	 */
	@Override
	public Optional<CommuteEntity> findGoTime(long mno) {
		List<CommuteEntity> result = commuteRepo.findAllByMember_mno(mno);//1.member찾기
		LocalDate today = LocalDate.now();
		long cno = 0;
		for(CommuteEntity i:result) {
			if(i.getToday().equals(today)) {//2.오늘날짜 대조
				cno = i.getCno();
			}
		}
		Optional<CommuteEntity> CommuteToday = commuteRepo.findById(cno);
		
		//System.out.println(CommuteToday.get().getGTime());
		return commuteRepo.findById(cno);
	}
	
	/**
	 * 가장 최근이 근무한 날짜 조회
	 */
	@Override
	public void showGTime(Long mno,Model model) {
		List<CommuteEntity> result = commuteRepo.findAllByMember_mno(mno);
		long cno = 0L;
		for(CommuteEntity i:result) {
			cno = i.getCno();
		}
		Optional<CommuteEntity> CommuteLastDay = commuteRepo.findById(cno);
		System.out.println("최근날짜 확인>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+CommuteLastDay.get().getToday());
		
		
		model.addAttribute("time",commuteRepo.findAllByCno(cno));
	}
	
	@Override
	public long MemberMno(Principal principal) {
		String email = principal.getName();
		
		Optional<MemberEntity> result = memberRepo.findAllById(email);
		long mNo =result.get().getMno();
//		System.err.println(mNo);
		return mNo;
	}
	
	@Override
	public void showListTime(long memberMno, Model model2 , CommuteMemberListDTO dto) {
		 /*List<CommuteMemberListDTO> list = 
				 commuteRepo.findAllByMember_mno(memberMno)
				 .stream()
				 .map(CommuteMemberListDTO::new)
				 .collect(Collectors.toList());
		 model2.addAttribute("list", list);*/
	}
	
	
}
