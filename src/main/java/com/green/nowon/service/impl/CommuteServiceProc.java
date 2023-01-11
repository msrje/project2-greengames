package com.green.nowon.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.attendance.CommuteInsertDTO;
import com.green.nowon.domain.dto.attendance.CommuteUpdateDTO;
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
	private MemberEntityRepository2 memberRepo;
	/**
	 * 저장과 동시에 업데이트 기능
	 */
	@Transactional
	@Override
	public void save(long mno, CommuteInsertDTO idto , CommuteUpdateDTO udto) {
		Optional<CommuteEntity> result = findGoTime(mno);
		if(result.isEmpty()) {
			commuteRepo.save(idto.entity().fkSaver(memberRepo.findByMno(mno)));
		}else {
			CommuteEntity entity = result.get();
			entity.update(udto);
			commuteRepo.save(entity);
		}
	}
	/**
	 * 오늘자 출근이 있는지 확인하세요.
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
	
}
