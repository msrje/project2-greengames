package com.green.nowon.service.mypage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.memberDTO.AddressDetailDTO;
import com.green.nowon.domain.dto.memberDTO.DepartmentDTO;
import com.green.nowon.domain.dto.memberDTO.MemberDetailDTO;
import com.green.nowon.domain.dto.memberDTO.ProfileDTO;
import com.green.nowon.domain.dto.memberDTO.SalaryListDTO;
import com.green.nowon.domain.dto.position.PositionDTO;
import com.green.nowon.domain.entity.cate.DepartmentEntity;
import com.green.nowon.domain.entity.cate.DepartmentEntityRepository;
import com.green.nowon.domain.entity.cate.DepartmentMemberEntity;
import com.green.nowon.domain.entity.cate.DepartmentMemberEntityRepository;
import com.green.nowon.domain.entity.cate.PositionEntity;
import com.green.nowon.domain.entity.cate.PositionRepository;
import com.green.nowon.domain.entity.member.AddressEntityRepsoitory;
import com.green.nowon.domain.entity.member.MemberEntity;
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
	@Autowired
	private DepartmentEntityRepository dRepo;
	@Autowired
	private PositionRepository pRepo;
	@Autowired
	private DepartmentMemberEntityRepository dmRepo;


	
	@Transactional
	@Override
	public void info(long mno, Model model,Model model2) {
		//로그인한 회원의 정보 + 회원이 등록한 이미지
		model.addAttribute("detail",mRepo.findById(mno)
				.map(SalaryListDTO::new)
				.orElseThrow());
		//로그인한 회원의 주소정보
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
	public void salaryInfo(long mno, Model model ,Model model2,Model model3) {
		model.addAttribute("detail",mRepo.findById(mno)
				.map(SalaryListDTO::new).orElseThrow());
		
		List<DepartmentDTO> result=dRepo.findAllByDepth(3).stream()
				.map(DepartmentDTO::new).collect(Collectors.toList());
		
		model2.addAttribute("department",result);
		
		List<PositionDTO> aaa=pRepo.findAll().stream()
				.map(PositionDTO::new).collect(Collectors.toList());
		
		model3.addAttribute("position",aaa);
	}
	@Override
	public void update(long mno, long pno,long dno) {
		Optional<MemberEntity> me=mRepo.findById(mno);
		
		me.ifPresent(e -> {
			e.setPno(PositionEntity.builder()
					.pno(pno)
					.build());
			mRepo.save(e);
		});
		
		
		  dmRepo.save(DepartmentMemberEntity.builder()
		  .department(DepartmentEntity.builder() .dno(dno) .build())
		  .member(MemberEntity.builder() .mno(mno) .build()) .build());
		
		
	}


	@Override
	public void update2(long mno) {
		// TODO Auto-generated method stub
		
	}












	


		
	

}
