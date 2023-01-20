package com.green.nowon.domain.dto.memberDTO;

import com.green.nowon.domain.dto.position.PositionDTO;
import com.green.nowon.domain.entity.cate.DepartmentMemberEntity;

import lombok.Getter;

@Getter
public class DepartmentMemberDTO {
	
	private long dmno;
	private DepartmentDTO department;
	private SalaryListDTO salary;
	private PositionDTO position;
	
	
	public DepartmentMemberDTO(DepartmentMemberEntity e) {
		dmno=e.getDmno();
		department=new DepartmentDTO(e.getDepartment());
		salary=new SalaryListDTO(e.getMember());
		position=new PositionDTO(e.getMember().getPno());
	}

}
