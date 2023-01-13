package com.green.nowon.service.mypage;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.memberDTO.AddressDetailDTO;
import com.green.nowon.domain.dto.memberDTO.MemberDetailDTO;
import com.green.nowon.domain.dto.memberDTO.ProfileDTO;
import com.green.nowon.domain.dto.memberDTO.SalaryListDTO;
import com.green.nowon.domain.entity.member.AddressEntityRepsoitory;
import com.green.nowon.domain.entity.member.MemberEntityRepository;
import com.green.nowon.domain.entity.member.ProfileEntityRepository;

@Service
public class MyPageServiceProcess implements MyPageService{

	
	@Autowired
	private MemberEntityRepository mRepo;
	@Autowired
	private AddressEntityRepsoitory aRepo;
	@Autowired
	private ProfileEntityRepository proRepo;
	
	

	
	@Transactional
	@Override
	public void info(long mno, Model model,Model model2) {

		model.addAttribute("detail",mRepo.findById(mno)
				.map(SalaryListDTO::new)
				.orElseThrow());
		
		model2.addAttribute("mpageaddr",aRepo.findByMember_mno(mno)
				.map(AddressDetailDTO::new)
				.orElseThrow());
	}

	
	@Transactional
	@Override//급여관리리스트 회원정보
	public void list(long mno, Model model) {
		List<SalaryListDTO> result=mRepo.findAll().stream()
				.map(SalaryListDTO::new).collect(Collectors.toList());
		model.addAttribute("srlist", result);
	}
	
	@Transactional
	@Override //급여관리리스트에서 사원의 디테일페이지
	public void salaryInfo(long mno, Model model ,Model model2) {
		model.addAttribute("detail",mRepo.findById(mno)
				.map(SalaryListDTO::new).orElseThrow());
//		model2.addAttribute("deImg",proRepo.findById(mno)
//				.map(ProfileDTO::new).orElseThrow());
	}








	


		
	

}
