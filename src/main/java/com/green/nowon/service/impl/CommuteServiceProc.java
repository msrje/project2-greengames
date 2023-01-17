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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.attendance.CommuteInsertDTO;
import com.green.nowon.domain.dto.attendance.CommuteUpdateDTO;
import com.green.nowon.domain.dto.commuteMember.CommuteMemberListDTO;
import com.green.nowon.domain.entity.attendance.CommuteEntity;
import com.green.nowon.domain.entity.attendance.CommuteEntityRepository;
import com.green.nowon.domain.entity.board.BoardEntity;
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
	 * 가장 최근에 근무한 날짜 조회
	 */
	@Override
	public void showGTime(Long mno,Model model,CommuteInsertDTO idto) {
		List<CommuteEntity> result = commuteRepo.findAllByMember_mno(mno);
		long cno = 0L;
		if(!result.isEmpty()) {//값이 있으면 실행
			for(CommuteEntity i:result) {
				cno = i.getCno();
			}
			Optional<CommuteEntity> CommuteLastDay = commuteRepo.findById(cno);
	//		System.out.println("최근날짜 확인>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+CommuteLastDay.get().getToday());
			
			
			model.addAttribute("time",commuteRepo.findAllByCno(cno));
		}else {//값이 없으면 새로 저장
			commuteRepo.save(idto.entity().fkSaver(memberRepo.findByMno(mno).get()));
			model.addAttribute("time",commuteRepo.findAllByCno(cno));
		}
	}
	
	@Override
	public long MemberMno(Principal principal) {
		String email = principal.getName();
		
		Optional<MemberEntity> result = memberRepo.findAllById(email);
		long mNo =result.get().getMno();
		//String mNmae=result.get().getName();
//		System.err.println(mNo);
		return mNo;
	}
	/**
	 * ajax를 사용해서 저장된 시간을 보여주기
	 */
	@Transactional
	@Override
	public void showListTime(long mno, Model model) {
		int size=10;
		Sort sort=Sort.by(Direction.DESC, "cno");
		Pageable pageable=PageRequest.of(0, size, sort);
		Page<CommuteEntity> result=commuteRepo.findAllByMember_mno(mno,pageable);
		
		model.addAttribute("p",result);
		model.addAttribute("list", result
				 .stream()
				 .map(CommuteMemberListDTO::new)
				 .collect(Collectors.toList()));
	}
	
}
