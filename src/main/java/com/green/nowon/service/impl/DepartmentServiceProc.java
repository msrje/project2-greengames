package com.green.nowon.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.entity.cate.DepartmentEntity;
import com.green.nowon.domain.entity.cate.DepartmentEntityRepository;
import com.green.nowon.service.DepartmentService;

@Service
public class DepartmentServiceProc implements DepartmentService {

	@Autowired
	private DepartmentEntityRepository departmentRepo;
	
	@Override
	public boolean isReg(String text) {
		Optional<DepartmentEntity> result= departmentRepo.findByParentDnoNullAndDname(text);
		if(result.isEmpty())
			return true;
		return false;
	}

	@Override
	public void save(String[] names) {//3차 카테까지만 1차는 그린게임즈 고정
		DepartmentEntity cate1=departmentRepo.findByParentDnoAndDname(null, names[0])
				.orElseGet(()->departmentRepo.save(DepartmentEntity.builder().dname(names[0]).depth(1).parent(null).build()));
		
		DepartmentEntity cate2=departmentRepo.findByParentDnoAndDname(cate1.getDno(), names[1])
				.orElseGet(()->departmentRepo.save(DepartmentEntity.builder().dname(names[1]).depth(2).parent(cate1).build()));
		
		DepartmentEntity cate3=departmentRepo.findByParentDnoAndDname(cate2.getDno(), names[2])
				.orElseGet(()->departmentRepo.save(DepartmentEntity.builder().dname(names[2]).depth(3).parent(cate2).build()));
	}

	/**
	 * 부서들 리스트 카테고리
	 */
	@Override
	public void departmentList(Long parentDno, Model model) {
		System.err.println(">>>>>>>>>>>>>>>"+parentDno);
		if(parentDno.intValue()==0)parentDno=null;//null은 회사명
		System.err.println(">>>>>>>>>>>>>>>"+parentDno);
		model.addAttribute("list", departmentRepo.findByParentDnoOrderByDnameAsc(parentDno));
	}
	@Override
	public void firstDepartment(Model model) {
		System.err.println(model);
		model.addAttribute("list", departmentRepo.findByParentDnoOrderByDnameAsc(null));
		System.err.println(model);
	}

}
